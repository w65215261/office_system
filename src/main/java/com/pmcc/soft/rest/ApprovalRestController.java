package com.pmcc.soft.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.pmcc.soft.core.common.CommonVariables;
import com.pmcc.soft.core.common.View;
import com.pmcc.soft.core.organization.domain.OrganizationInfo;
import com.pmcc.soft.core.organization.domain.PersonInfoManage;
import com.pmcc.soft.core.organization.manager.OrganizationInfoManager;
import com.pmcc.soft.core.organization.manager.PersonInfoManageManager;
import com.pmcc.soft.core.utils.CommonUtils;
import com.pmcc.soft.core.utils.SystemParamsUtil;
import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.task.simpleflows.manager.SimpleflowsManager;
import com.pmcc.soft.unit.AuditData;
import com.pmcc.soft.unit.ParameterBean;
import com.pmcc.soft.week.domain.*;
import com.pmcc.soft.week.manager.*;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 审批业务手机端接口
 * Created by wangbin on 2016/4/25.
 */
@Controller
@RequestMapping("/approval")
public class ApprovalRestController {

    @Autowired
    private TempleateControlManager templeateControlManager;
    @Autowired
    private DiySelectDisplayManager diySelectDisplayManager;
    @Autowired
    private ApprovalHeadManager approvalHeadManager;
    @Autowired
    private PersonInfoManageManager personInfoManageManager;
    @Autowired
    private OrganizationInfoManager organizationInfoManager;

    @Autowired
    private SimpleflowsManager simpleflowsManager;

    @Autowired
    private SystemAuditManager systemAuditManager;
    @Autowired
    private SystemAttachmentManager systemAttachmentManager;
    @Autowired
    private SystemAuditPersonConfigManager systemAuditPersonConfigManager;


    /**
     * 查询自定义下拉框
     *
     * @param templeateControl
     * @return
     */
    @JsonView(View.RestView.class)
    @RequestMapping(value = "/queryDiySelectDisplay", method = RequestMethod.POST)
    @ResponseBody
    public List<DiySelectDisplay> queryDiySelectDisplay(TempleateControl templeateControl) {
        Map<String, Object> map = new HashMap<String, Object>();
        TempleateControl tc = templeateControlManager.queryControlId(templeateControl);
        String controlId = tc.getId();
        DiySelectDisplay dsd = new DiySelectDisplay();
        dsd.setControlId(controlId);
        List<DiySelectDisplay> list = diySelectDisplayManager.queryByControlId(dsd);
        return list;
    }

    /**
     * 获取uuid
     *
     * @return
     */
    @RequestMapping(value = "/createUuid", method = RequestMethod.GET)
    @ResponseBody
    public String createUuid() {
        return UUIDGenerator.getUUID();
    }

    /*-----------------------手机端接口---开始--by LvXL---------------------*/

    /**
     * 根据主键获取数据
     *
     * @param oid     数据主键
     * @param request
     * @return 查询的单条数据
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public ApprovalHead get(String oid, HttpServletRequest request) {

        ApprovalHead ah = approvalHeadManager.get(oid);
        if (ah != null) {
            // 放置图片路劲
            List<String> urlList = new ArrayList<String>();
            SystemAttachment sa = new SystemAttachment();
            sa.setBusinessData(oid);
            List<SystemAttachment> list = systemAttachmentManager.queryByReportId(sa);
            if (list != null && list.size() > 0) {
                for (SystemAttachment systemAttachment : list) {
                    String url = systemAttachment.getFileUrl();
                    // Tomcat配置的虚拟路径名称
                    url = "/appUploadPath" + url;
                    urlList.add(url);
                }
                ah.setUrlList(urlList);
            }
        }
        return ah;
    }

    /**
     * 审批  0未1同意2拒绝3.进行中
     *
     * @param systemAudit
     * @return
     */
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    @ResponseBody
    public String check(SystemAudit systemAudit) {

        String res = "";

        // 保存审批意见
        systemAuditManager.save(systemAudit);
        // 业务主键
        String businessData = systemAudit.getBusinessData();
        ApprovalHead ah = approvalHeadManager.get(businessData);
        String personId = "";
        String smallType = "";
        if (ah != null) {
            personId = ah.getRptPersonId();
            smallType = ah.getType();
        }

        // 工作流
        simpleflowsManager.executeTask(systemAudit.getTaskId(), systemAudit.getAuditPerson(), systemAudit.getAuditStatus());
        //加入推送
        Map<String, String> extras = CommonUtils.getExtras("PushJsonType_SP", businessData, systemAudit.getTaskId(), "3", "ok", "1", smallType);
        String auditStatus = systemAudit.getAuditStatus();
        if ("1".equals(auditStatus)) {
            // 1通过
            // 申请人通知
            // 安卓
            JPush.sendPushToAndroidWithAlias(personId, CommonVariables.SendPushToAndroid_Check_Yes_Alert, CommonVariables.SendPushToAndroid_Title, extras);
            // IOS
            JPush.sendPushToIOSWithAlias(personId,CommonVariables.SendPushToAndroid_Check_Yes_Alert,CommonVariables.SendPushToAndroid_Title, extras);
            //二级审批人通知
            String person2nd = systemAuditPersonConfigManager.findConfigPerson(ah.getId(), CommonVariables.APPROVEL_GROUP_2ND_CODE);
            if (!person2nd.equals(systemAudit.getAuditPerson())) {
                String taskId = approvalHeadManager.query(person2nd, "3", ah.getId());
                Map<String, String> extras2 = CommonUtils.getExtras("PushJsonType_SP", ah.getId(), taskId, "3", "ok", "1", ah.getType());
                // 安卓
                JPush.sendPushToAndroidWithAlias(person2nd, CommonVariables.SendPushToAndroid_Task1Assign_Alert, CommonVariables.SendPushToAndroid_Title, extras2);
                // IOS
                JPush.sendPushToIOSWithAlias(person2nd,CommonVariables.SendPushToAndroid_Task1Assign_Alert,CommonVariables.SendPushToAndroid_Title, extras2);
            }
        } else if ("2".equals(auditStatus)) {
            // 2拒绝
            // 安卓
            JPush.sendPushToAndroidWithAlias(personId, CommonVariables.SendPushToAndroid_Check_No_Alert, CommonVariables.SendPushToAndroid_Title, extras);
            // IOS
            JPush.sendPushToIOSWithAlias(personId,CommonVariables.SendPushToAndroid_Check_No_Alert,CommonVariables.SendPushToAndroid_Title, extras);
        }

        res = "{\"resultCode\":\"success\"}";
        return res;
    }

    /**
     * 查询 1:我发起的,2:我审批的,3:待我审批的
     *
     * @param personId
     * @param queryFlag
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public List<ApprovalHead> query(String personId, String queryFlag) {

        return approvalHeadManager.query(personId, queryFlag);
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
    public List<DiySelectDisplay> queryCombo(String templateCode, String controlType, String controlIndex, HttpServletRequest request) {

        return diySelectDisplayManager.queryCombo(templateCode, controlType, controlIndex);
    }

    /**
     * 保存，需要保存审批业务主表、审批业务数据表、审核人员配置表
     *
     * @param parameterBean
     * @param request
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(ParameterBean parameterBean, HttpServletRequest request) {

        String res = "";
        // 审批业务主表
        ApprovalHead approvalHead = new ApprovalHead();
        approvalHead.setId(parameterBean.getUuid() == null ? UUIDGenerator.getUUID() : parameterBean.getUuid());
        approvalHead.setType(parameterBean.getType());
        approvalHead.setDataBlock(parameterBean.getDataBlock());
        approvalHead.setRptDate(new Date());
        approvalHead.setSource(parameterBean.getSource());
        approvalHead.setAuditStatus("0");
        // 人员机构
        String rptPersonId = parameterBean.getRptPersonId();
        if (rptPersonId != null && !"".equals(rptPersonId)) {
            approvalHead.setRptPersonId(parameterBean.getRptPersonId());

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
        int code = approvalHeadManager.save(approvalHead, parameterBean);
        if (code == 1) {
            // 开始工作流
            simpleflowsManager.startTask(approvalHead.getId(), approvalHead.getRptPersonId(), "1");
            // 推送待办任务

            String person1th = systemAuditPersonConfigManager.findConfigPerson(approvalHead.getId(), CommonVariables.APPROVEL_GROUP_1TH_CODE);

            String taskId = approvalHeadManager.query(person1th, "3", approvalHead.getId());
            //加入推送
            Map<String, String> extras =
                    CommonUtils.getExtras("PushJsonType_SP", approvalHead.getId(), taskId, "3", "ok", "1", approvalHead.getType());
            // 安卓
            JPush.sendPushToAndroidWithAlias(person1th, CommonVariables.SendPushToAndroid_Task1Assign_Alert, CommonVariables.SendPushToAndroid_Title, extras);
            // IOS
            JPush.sendPushToIOSWithAlias(person1th,CommonVariables.SendPushToAndroid_Task1Assign_Alert,CommonVariables.SendPushToAndroid_Title, extras);


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

    /*-----------------------手机端接口---结束-----------------------*/


    @RequestMapping(value = "/testJPush", method = RequestMethod.GET)
    @ResponseBody
    public String testJPush(String alert) {

//        JPush.sendPushToAndroidWithAlias("e1990c606f70470dab028fbf64cc9d6a", alert);

        return "";
    }

}
