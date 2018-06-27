package com.pmcc.soft.week.web;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pmcc.soft.core.common.CommonVariables;
import com.pmcc.soft.core.common.View;
import com.pmcc.soft.core.organization.domain.OrganizationInfo;
import com.pmcc.soft.core.organization.domain.PersonInfoManage;
import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.organization.manager.OrganizationInfoManager;
import com.pmcc.soft.core.organization.manager.PersonInfoManageManager;
import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.task.simpleflows.manager.SimpleflowsManager;
import com.pmcc.soft.unit.AuditData;
import com.pmcc.soft.unit.ParameterBean;
import com.pmcc.soft.week.domain.*;
import com.pmcc.soft.week.manager.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 审批模块
 * Created by ZhangYanChang on 2016/4/19.
 */
@Controller
@RequestMapping(value = "approval")
public class ApprovalController {

    @Autowired
    ApprovalHeadManager approvalHeadManager;

    @Autowired
    DiySelectDisplayManager diySelectDisplayManager;

    @Autowired
    PersonInfoManageManager personInfoManageManager;

    @Autowired
    OrganizationInfoManager organizationInfoManager;

    @Autowired
    TempleateControlManager templeateControlManager;

    @Autowired
    ApprovalTemplateManager approvalTemplateManager;

    @Autowired
    ApprovalDataManager approvalDataManager;

    @Autowired
    private SimpleflowsManager simpleflowsManager;

    @Autowired
    private SystemAuditManager systemAuditManager;

    @Autowired
    private SystemAttachmentManager systemAttachmentManager;

    @Autowired
    private SystemAuditPersonConfigManager systemAuditPersonConfigManager;


    @RequestMapping(value = "show", method = RequestMethod.GET)
    public ModelAndView show() {
        ModelAndView mav = new ModelAndView("approval/show");
        return mav;
    }

    @RequestMapping(value = "approvalShow", method = RequestMethod.GET)
    public ModelAndView approvalShow() {
        ModelAndView mav = new ModelAndView("approval/approvalShow");
        return mav;
    }


    @RequestMapping(value = "addApproval", method = RequestMethod.GET)
    public ModelAndView addApproval(String templateCode) {
        ModelAndView mav = new ModelAndView("approval/addApproval");
        List<TempleateControl> templeateControls = templeateControlManager.queryTempleateControl(templateCode);
        List<TempleateControl> list = TestVelocity.initData(templeateControls);

        StringBuffer html = new StringBuffer();

        for (TempleateControl templeateControl : list) {
            List<DiySelectDisplay> diySelectDisplays = diySelectDisplayManager.queryCombo(templateCode, String.valueOf(templeateControl.getControlType()), String.valueOf(templeateControl.getIndex()));
            html.append(TestVelocity.getHtml(templeateControl, diySelectDisplays));
            html.append("<br>");
        }
        String uuid = UUIDGenerator.getUUID();
        mav.addObject("templateCode", templateCode);
        mav.addObject("uuid", uuid);
        mav.addObject("resHtml", html.toString());
        return mav;
    }

    /*--------------------------------------------------------------*/

    /**
     * 查看详细
     *
     * @param approvalHeadId
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ModelAndView get(String approvalHeadId, String approvalType, String taskId, String queryFlag) throws ParseException {
        ModelAndView mav = new ModelAndView("approval/details");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ApprovalData approvalData = new ApprovalData();
        SystemAudit systemAudit = new SystemAudit();
        List<SystemAudit> systemAudits = new ArrayList<SystemAudit>();
        List<SystemAuditPersonConfig> systemAuditPersonConfigs = new ArrayList<SystemAuditPersonConfig>();
        approvalData.setApprovalHeadId(approvalHeadId);
        List<ApprovalData> approvalList = approvalDataManager.queryByApprovalHeadId(approvalData);
        Boolean systemAuditFlag = false;
        Boolean systemAuditPersonConfigFlag = false;
        for (ApprovalData ad : approvalList) {
            if (ad.getItemIndex() != null) {
                if (ad.getItemIndex() == 0) {
                    ad.setControlTitle("开始时间");
                } else if (ad.getItemIndex() == 1) {
                    ad.setControlTitle("结束时间");
                }
            }

            if (ad.getControlType() == CommonVariables.CONTROL_CALENDAR_RANGE
                    || ad.getControlType() == CommonVariables.CONTROL_CALENDAR) {
                if (!"".equals(ad.getControlDisplay()) && ad.getControlDisplay() != null) {
                    ad.setControlDisplay(sdf.format(sdf.parse(ad.getControlDisplay())));
                }
            }

            if (!systemAuditPersonConfigFlag) {
                systemAuditPersonConfigs = systemAuditPersonConfigManager.queryAuditPerson(ad.getApprovalHeadId());
                if (systemAuditPersonConfigs != null) {
                    systemAuditPersonConfigFlag = true;
                }
            }

            //是否有审批意见
            if (!systemAuditFlag) {
                systemAudit.setBusinessData(ad.getApprovalHeadId());
                systemAudit.setBusinessType(approvalType);
                systemAudits = systemAuditManager.queryAuditRemark(systemAudit);
                if (systemAudits != null) {
                    systemAuditFlag = true;
                }
            }
        }

        SystemAttachment systemAttachment = new SystemAttachment();
        systemAttachment.setBusinessData(approvalHeadId);
        List<SystemAttachment> list = systemAttachmentManager.queryByBusinessData(systemAttachment);
        for (SystemAttachment systemAttachments : list) {
            systemAttachments.setFileUrl("/uploadPath" + systemAttachments.getFileUrl());
        }
        mav.addObject("systemAuditPersonConfigs", systemAuditPersonConfigs);
        mav.addObject("systemAudits", systemAudits);
        mav.addObject("queryFlag", queryFlag);
        mav.addObject("systemAttachments", list);
        mav.addObject("approvalType", approvalType);
        mav.addObject("taskId", taskId);
        mav.addObject("approvalList", approvalList);
        return mav;
    }

    /**
     * 审批  0未1同意2拒绝3.进行中
     *
     * @param systemAudit
     * @return
     */
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    @ResponseBody
    public String check(SystemAudit systemAudit, HttpSession session) {
        PersonManage user = (PersonManage) session.getAttribute("loginUser");
        String auditPerson = user.getId();
        systemAudit.setAuditPerson(auditPerson);
        String res = "";

        // 保存审批意见
        systemAuditManager.save(systemAudit);
        // 工作流
        simpleflowsManager.executeTask(systemAudit.getTaskId(), systemAudit.getAuditPerson(), systemAudit.getAuditStatus());
        res = "{\"resultCode\":\"success\"}";
        return res;
    }

    /**
     * 查询 1:我发起的,2:我审批的,3:待我审批的
     *
     * @param session
     * @param queryFlag
     * @param request
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> query(ApprovalHead ah, HttpSession session, String queryFlag, HttpServletRequest request) throws ParseException {
        Map<String, Object> ma = new HashMap<String, Object>();
        PersonManage user = (PersonManage) session.getAttribute("loginUser");
        String personId = user.getId();

        String companyId = user.getCompanyId();
        OrganizationInfo organizationInfo = organizationInfoManager.queryOrgById(companyId);
        String orgCode = organizationInfo.getOrgCode();
        ah.setOrgCode(orgCode);

        String sEcho = request.getParameter("sEcho");
        ah.setInitPage(0);
        PersonInfoManage personInfoManage = new PersonInfoManage();
        personInfoManage.setInitPage(1);
        personInfoManage.setCompanyId(user.getCompanyId());
        List<String> ids = new ArrayList<String>();
        List<PersonInfoManage> personInfoManageList = personInfoManageManager.query(personInfoManage);
        for (PersonInfoManage pm : personInfoManageList) {
            ids.add(pm.getId());
        }
        ah.setPersonIdList(ids);
        List<ApprovalHead> list = approvalHeadManager.query(personId, queryFlag, ah);
        String type = ah.getType();
        List<ApprovalHtmlData> approvalHtmlDataList = findHtmlData(list, type);
        ma.put("sEcho", sEcho);
        ah.setInitPage(1);
        int s = 0;
        List<ApprovalHead> approvalHeads = approvalHeadManager.query(personId, queryFlag, ah);
        for (ApprovalHead approvalHead : approvalHeads) {
            s++;
        }
        ma.put("iTotalRecords", s);
        ma.put("iTotalDisplayRecords", s);
        ma.put("aaData", approvalHtmlDataList);
        return ma;
    }

    /**
     * 手机端获取下拉框
     *
     * @param templateCode
     * @param controlType
     * @param request
     * @return
     */
    @JsonView(View.RestView.class)
    @RequestMapping(value = "/queryCombo", method = RequestMethod.GET)
    @ResponseBody
    public List<DiySelectDisplay> queryCombo(String templateCode, String controlType, String optionIndex, HttpServletRequest request) {

        return diySelectDisplayManager.queryCombo(templateCode, controlType, optionIndex);
    }

    /**
     * 保存，需要保存审批业务主表、审批业务数据表、审核人员配置表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(HttpSession session, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("approval/show");
        PersonManage user = (PersonManage) session.getAttribute("loginUser");
        String rptPersonId = user.getId();
        String templateCode = request.getParameter("templateCode");
        String approvalPersonId = request.getParameter("approvalPersonId");
        String[] approvalPersonIds = approvalPersonId.split(",");
        approvalPersonId = "1th|" + approvalPersonIds[0] + "," + "2nd|" + approvalPersonIds[1];
        String uuid = request.getParameter("uuid");
        String startDate = "";
        String endDate = "";
        String controlValue = "";
        String controlText = "";
        List<ApprovalTemporaryData> data = new ArrayList<ApprovalTemporaryData>();
        List<TempleateControl> tcs = templeateControlManager.queryTempleateControl(templateCode);
        ApprovalTemporaryData atd = new ApprovalTemporaryData();
        int index = 0;
        for (TempleateControl templeateControl : tcs) {
            atd = new ApprovalTemporaryData();
            ApprovalTemporaryData approvalTemporaryData = new ApprovalTemporaryData();
            if (templeateControl.getControlType() == CommonVariables.CONTROL_CALENDAR_RANGE) {
                startDate = request.getParameter("start" + templeateControl.getControlKey());
                endDate = request.getParameter("end" + templeateControl.getControlKey());
                atd.setControlTitle(templeateControl.getControlTitle());
                atd.setControlDisplay(startDate);
                atd.setControlKey(templeateControl.getControlKey());
                atd.setControlType(templeateControl.getControlType());
                atd.setControlValue(startDate);
                atd.setIndex(index);
                atd.setRptPersonId(rptPersonId);
                atd.setItemIndex("0");
                data.add(atd);
                approvalTemporaryData.setControlTitle(templeateControl.getControlTitle());
                approvalTemporaryData.setControlDisplay(endDate);
                approvalTemporaryData.setControlKey(templeateControl.getControlKey());
                approvalTemporaryData.setControlType(templeateControl.getControlType());
                approvalTemporaryData.setControlValue(endDate);
                approvalTemporaryData.setIndex(index);
                approvalTemporaryData.setRptPersonId(rptPersonId);
                approvalTemporaryData.setItemIndex("1");
                data.add(approvalTemporaryData);
            } else if (templeateControl.getControlType() == CommonVariables.CONTROL_SELECT) {
                List<DiySelectDisplay> diySelectDisplays = diySelectDisplayManager.queryCombo(
                        templateCode,
                        String.valueOf(templeateControl.getControlType()),
                        String.valueOf(templeateControl.getIndex()));
                controlValue = request.getParameter(templeateControl.getControlKey());
                for (DiySelectDisplay dsd : diySelectDisplays) {
                    if (controlValue.equals(dsd.getOptionValue())) {
                        controlText = dsd.getOptionText();
                    }
                }
                atd.setControlTitle(templeateControl.getControlTitle());
                if ("-1".equals(controlValue)) {
                    atd.setControlDisplay("");
                } else {
                    atd.setControlDisplay(controlText);
                }
                atd.setControlKey(templeateControl.getControlKey());
                atd.setControlType(templeateControl.getControlType());
                atd.setControlValue(controlValue);
                atd.setIndex(index);
                atd.setRptPersonId(rptPersonId);
                atd.setItemIndex("-1");
                data.add(atd);
            } else {
                controlText = request.getParameter(templeateControl.getControlKey());
                atd.setControlTitle(templeateControl.getControlTitle());
                atd.setControlDisplay(controlText);
                atd.setControlKey(templeateControl.getControlKey());
                atd.setControlType(templeateControl.getControlType());
                atd.setControlValue(controlText);
                atd.setIndex(index);
                atd.setRptPersonId(rptPersonId);
                atd.setItemIndex("-1");
                data.add(atd);
            }
            index++;
        }

        String dataBlock = net.sf.json.JSONArray.fromObject(data).toString();

        String res = "";
        // 审批业务主表
        ApprovalHead approvalHead = new ApprovalHead();
        approvalHead.setId(uuid);
        approvalHead.setType(templateCode);
        approvalHead.setDataBlock(dataBlock);
        approvalHead.setRptDate(new Date());
        approvalHead.setSource("0");
        approvalHead.setAuditStatus("0");
        // 人员机构
        if (rptPersonId != null && !"".equals(rptPersonId)) {
            approvalHead.setRptPersonId(rptPersonId);

            PersonInfoManage personInfoManage = new PersonInfoManage();
            personInfoManage.setId(rptPersonId);
            PersonInfoManage pm = personInfoManageManager.queryByOid(personInfoManage);
            if (pm != null) {
                approvalHead.setRptPersonName(pm.getUserCname());
                List<OrganizationInfo> orgList = organizationInfoManager.queryOrgByPersonId(rptPersonId);
                if (orgList != null && orgList.size() > 0) {
                    approvalHead.setOrgCode(orgList.get(0).getOrgCode());
                    approvalHead.setOrgId(orgList.get(0).getId());
                    approvalHead.setOrgName(orgList.get(0).getOrgCname());
                }
            }
        }
        ParameterBean parameterBean = new ParameterBean();
        parameterBean.setDataBlock(dataBlock);
        parameterBean.setGroupAndPerson(approvalPersonId);
        parameterBean.setBusinessModel(1);
        int code = approvalHeadManager.save(approvalHead, parameterBean);
        if (code == 1) {
            // 开始工作流
            simpleflowsManager.startTask(approvalHead.getId(), approvalHead.getRptPersonId(), "1");
            res = "{\"resultCode\":\"success\"}";
        } else {
            res = "{\"resultCode\":\"fail\",\"resultDesc\":\"操作失败\"}";
        }
        return res;
    }

    /**
     * 根据业务类型编码查询控件，排序
     *
     * @param templateCode
     * @return
     */
    @RequestMapping(value = "/queryTempleateControl", method = RequestMethod.GET)
    @ResponseBody
    public List<TempleateControl> queryTempleateControl(String templateCode) {

        return templeateControlManager.queryTempleateControl(templateCode);
    }

    /**
     * 查询审批信息
     *
     * @param oid
     * @return
     */
    @RequestMapping(value = "/queryAuditDataList", method = RequestMethod.GET)
    @ResponseBody
    public List<AuditData> queryAuditDataList(String oid) {

        return approvalHeadManager.queryAuditDataList(oid);
    }

    /**
     * 审批类型下拉框值的查询
     *
     * @return
     */
    @RequestMapping(value = "/queryApprovalType", method = RequestMethod.GET)
    @ResponseBody
    public List<ApprovalTemplate> queryApprovalType() {
        return approvalTemplateManager.query();
    }


    /**
     * 不同审批类型显示不同的表单
     *
     * @param approvalType
     * @return
     */
    @RequestMapping(value = "/chooseApprovalTypeGoHtml", method = RequestMethod.GET)
    public ModelAndView show(String approvalType) {
        ModelAndView mav;
        if ("QJ".equals(approvalType)) {
            mav = new ModelAndView("/approval/include/qj");
        } else if ("BX".equals(approvalType)) {
            mav = new ModelAndView("/approval/include/bx");
        } else if ("JB".equals(approvalType)) {
            mav = new ModelAndView("/approval/include/jb");
        } else if ("BMXZ".equals(approvalType)) {
            mav = new ModelAndView("/approval/include/bmxz");
        } else if ("HY".equals(approvalType)) {
            mav = new ModelAndView("/approval/include/hy");
        } else {
            mav = new ModelAndView("/approval/include/yc");
        }
        mav.addObject("approvalType", approvalType);
        return mav;
    }


    /**
     * 编辑往前台页面传的数据
     *
     * @param list
     * @param type
     * @return
     * @throws ParseException
     */
    public List<ApprovalHtmlData> findHtmlData(List<ApprovalHead> list, String type) throws ParseException {
        List<ApprovalHtmlData> approvalHtmlDataList = new ArrayList<ApprovalHtmlData>();
        ApprovalHtmlData approvalHtmlData = new ApprovalHtmlData();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (ApprovalHead approvalHead : list) {
            ApprovalData approvalData = new ApprovalData();
            approvalData.setApprovalHeadId(approvalHead.getId());
            List<ApprovalData> approvalList = approvalDataManager.queryByApprovalHeadId(approvalData);
            approvalHtmlData = new ApprovalHtmlData();
            if ("QJ".equals(type)) {
                for (ApprovalData ad : approvalList) {
                    if ("type".equals(ad.getControlKey())) {
                        approvalHtmlData.setDetailsIndexFour(ad.getControlDisplay());
                    } else if ("reason".equals(ad.getControlKey())) {
                        approvalHtmlData.setDetailsIndexSix(ad.getControlDisplay());
                    } else if ("daynum".equals(ad.getControlKey())) {
                        approvalHtmlData.setDetailsIndexFive(ad.getControlDisplay());
                    } else if ("date".equals(ad.getControlKey()) && ad.getItemIndex() == 0) {
                        if (ad.getControlDisplay() == null || "".equals(ad.getControlDisplay())) {
                            approvalHtmlData.setDetailsIndexSeven(null);
                        } else {
                            approvalHtmlData.setDetailsIndexSeven(sdf.parse(ad.getControlDisplay()));
                        }

                    } else if ("date".equals(ad.getControlKey()) && ad.getItemIndex() == 1) {
                        if (ad.getControlDisplay() == null || "".equals(ad.getControlDisplay())) {
                            approvalHtmlData.setDetailsIndexEight(null);
                        } else {
                            approvalHtmlData.setDetailsIndexEight(sdf.parse(ad.getControlDisplay()));
                        }

                    }
                }
            } else if ("BX".equals(type)) {
                for (ApprovalData ad : approvalList) {
                    if ("amount".equals(ad.getControlKey())) {
                        approvalHtmlData.setDetailsIndexFour(ad.getControlDisplay());
                    } else if ("type".equals(ad.getControlKey())) {
                        approvalHtmlData.setDetailsIndexFive(ad.getControlDisplay());
                    } else if ("details".equals(ad.getControlKey())) {
                        approvalHtmlData.setDetailsIndexSix(ad.getControlDisplay());
                    }
                }
            } else if ("JB".equals(type)) {
                for (ApprovalData ad : approvalList) {
                    if ("costTime".equals(ad.getControlKey())) {
                        approvalHtmlData.setDetailsIndexFour(ad.getControlDisplay());
                    } else if ("calcMethod".equals(ad.getControlKey())) {
                        approvalHtmlData.setDetailsIndexFive(ad.getControlDisplay());
                    } else if ("reason".equals(ad.getControlKey())) {
                        approvalHtmlData.setDetailsIndexSix(ad.getControlDisplay());
                    } else if ("isHolidays".equals(ad.getControlKey())) {
                        approvalHtmlData.setDetailsIndexNine(ad.getControlDisplay());
                    } else if ("date".equals(ad.getControlKey()) && ad.getItemIndex() == 0) {
                        if (ad.getControlDisplay() == null || "".equals(ad.getControlDisplay())) {
                            approvalHtmlData.setDetailsIndexSeven(null);
                        } else {
                            approvalHtmlData.setDetailsIndexSeven(sdf.parse(ad.getControlDisplay()));
                        }

                    } else if ("date".equals(ad.getControlKey()) && ad.getItemIndex() == 1) {
                        if (ad.getControlDisplay() == null || "".equals(ad.getControlDisplay())) {
                            approvalHtmlData.setDetailsIndexEight(null);
                        } else {
                            approvalHtmlData.setDetailsIndexEight(sdf.parse(ad.getControlDisplay()));
                        }
                    }
                }
            } else if ("BMXZ".equals(type)) {
                for (ApprovalData ad : approvalList) {
                    if ("initiator".equals(ad.getControlKey())) {
                        approvalHtmlData.setDetailsIndexFour(ad.getControlDisplay());
                    } else if ("collaborators".equals(ad.getControlKey())) {
                        approvalHtmlData.setDetailsIndexFive(ad.getControlDisplay());
                    } else if ("reason".equals(ad.getControlKey())) {
                        approvalHtmlData.setDetailsIndexSix(ad.getControlDisplay());
                    } else if ("workMatter".equals(ad.getControlKey())) {
                        approvalHtmlData.setDetailsIndexNine(ad.getControlDisplay());
                    } else if ("expectations".equals(ad.getControlKey())) {
                        approvalHtmlData.setDetailsIndexTen(ad.getControlDisplay());
                    } else if ("completionDate".equals(ad.getControlKey())) {
                        if (ad.getControlDisplay() == null || "".equals(ad.getControlDisplay())) {
                            approvalHtmlData.setDetailsIndexSeven(null);
                        } else {
                            approvalHtmlData.setDetailsIndexSeven(sdf.parse(ad.getControlDisplay()));
                        }
                    }
                }
            } else if ("HY".equals(type)) {
                for (ApprovalData ad : approvalList) {
                    if ("name".equals(ad.getControlKey())) {
                        approvalHtmlData.setDetailsIndexFour(ad.getControlDisplay());
                    } else if ("address".equals(ad.getControlKey())) {
                        approvalHtmlData.setDetailsIndexFive(ad.getControlDisplay());
                    } else if ("organizers".equals(ad.getControlKey())) {
                        approvalHtmlData.setDetailsIndexSix(ad.getControlDisplay());
                    } else if ("date".equals(ad.getControlKey()) && ad.getItemIndex() == 0) {
                        if (ad.getControlDisplay() == null || "".equals(ad.getControlDisplay())) {
                            approvalHtmlData.setDetailsIndexSeven(null);
                        } else {
                            approvalHtmlData.setDetailsIndexSeven(sdf.parse(ad.getControlDisplay()));
                        }
                    } else if ("date".equals(ad.getControlKey()) && ad.getItemIndex() == 1) {
                        if (ad.getControlDisplay() == null || "".equals(ad.getControlDisplay())) {
                            approvalHtmlData.setDetailsIndexEight(null);
                        } else {
                            approvalHtmlData.setDetailsIndexEight(sdf.parse(ad.getControlDisplay()));
                        }
                    }
                }
            } else if ("YC".equals(type)) {
                for (ApprovalData ad : approvalList) {
                    if ("reason".equals(ad.getControlKey())) {
                        approvalHtmlData.setDetailsIndexFour(ad.getControlDisplay());
                    } else if ("startAddress".equals(ad.getControlKey())) {
                        approvalHtmlData.setDetailsIndexFive(ad.getControlDisplay());
                    } else if ("returnAddress".equals(ad.getControlKey())) {
                        approvalHtmlData.setDetailsIndexSix(ad.getControlDisplay());
                    } else if ("date".equals(ad.getControlKey()) && ad.getItemIndex() == 0) {
                        if (ad.getControlDisplay() == null || "".equals(ad.getControlDisplay())) {
                            approvalHtmlData.setDetailsIndexSeven(null);
                        } else {
                            approvalHtmlData.setDetailsIndexSeven(sdf.parse(ad.getControlDisplay()));
                        }
                    } else if ("date".equals(ad.getControlKey()) && ad.getItemIndex() == 1) {
                        if (ad.getControlDisplay() == null || "".equals(ad.getControlDisplay())) {
                            approvalHtmlData.setDetailsIndexEight(null);
                        } else {
                            approvalHtmlData.setDetailsIndexEight(sdf.parse(ad.getControlDisplay()));
                        }
                    }
                }
            }

            approvalHtmlData.setTaskId(approvalHead.getTaskId());
            approvalHtmlData.setApprovalHeadId(approvalHead.getId());
            approvalHtmlData.setDetailsIndexOne(approvalHead.getRptPersonName());
            approvalHtmlData.setDetailsIndexTwo(approvalHead.getRptDate());
            approvalHtmlData.setDetailsIndexThree(approvalHead.getOrgName());
            approvalHtmlData.setDetailsIndexEleven(approvalHead.getAuditStatus());
            approvalHtmlDataList.add(approvalHtmlData);
        }
        return approvalHtmlDataList;
    }

}
