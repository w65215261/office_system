package com.pmcc.soft.week.manager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pmcc.soft.core.common.CommonVariables;
import com.pmcc.soft.core.organization.dao.OrganizationInfoDao;
import com.pmcc.soft.core.organization.dao.PersonInfoManageDao;
import com.pmcc.soft.core.organization.domain.OrganizationInfo;
import com.pmcc.soft.core.organization.domain.PersonInfoManage;
import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.task.simpleflows.manager.SimpleflowsManager;
import com.pmcc.soft.unit.AuditData;
import com.pmcc.soft.unit.ParameterBean;
import com.pmcc.soft.week.dao.ApprovalDataDao;
import com.pmcc.soft.week.dao.ApprovalHeadDao;
import com.pmcc.soft.week.dao.SystemAuditDao;
import com.pmcc.soft.week.dao.SystemAuditPersonConfigDao;
import com.pmcc.soft.week.domain.ApprovalData;
import com.pmcc.soft.week.domain.ApprovalHead;
import com.pmcc.soft.week.domain.SystemAudit;
import com.pmcc.soft.week.domain.SystemAuditPersonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 * 审批业务主表
 * Created by wangbin on 2016/4/25.
 */
@Transactional
@Service
public class ApprovalHeadManager {

    @Autowired
    private ApprovalHeadDao approvalHeadDao;

    @Autowired
    private ApprovalDataDao approvalDataDao;

    @Autowired
    PersonInfoManageDao personInfoManageDao;

    @Autowired
    private OrganizationInfoDao organizationInfoDao;

    @Autowired
    private SystemAuditPersonConfigDao systemAuditPersonConfigDao;

    @Autowired
    private SimpleflowsManager simpleflowsManager;

    @Autowired
    private SystemAuditDao systemAuditDao;


    /**
     * 保存，需要保存审批业务主表、审批业务数据表、审核人员配置表
     *
     * @param approvalHead
     * @param parameterBean
     * @return
     */
    public int save(ApprovalHead approvalHead, ParameterBean parameterBean) {

        // 审批业务主表
        int res = approvalHeadDao.save(approvalHead);
        // 审批业务数据表
        List<ApprovalData> list =
                new Gson().fromJson(parameterBean.getDataBlock(), new TypeToken<List<ApprovalData>>() {
                }.getType());
        if (list != null && list.size() > 0) {
            for (ApprovalData data : list) {

                data.setId(UUIDGenerator.getUUID());
                data.setRptPersonId(approvalHead.getRptPersonId());
                data.setRptDate(approvalHead.getRptDate());
                data.setApprovalHeadId(approvalHead.getId());
                approvalDataDao.save(data);
            }
        }
        // 审核人员配置
        this.saveSystemAuditPersonConfig(approvalHead, parameterBean);
        return res;
    }

    /**
     * 审核人员配置
     *
     * @param approvalHead
     * @param parameterBean
     */
    public void saveSystemAuditPersonConfig(ApprovalHead approvalHead, ParameterBean parameterBean) {

        // 一组code|人员一id,二组code|人员二id,三组code|人员三id
        String groupAndPerson = parameterBean.getGroupAndPerson();
        if (groupAndPerson != null) {
            String[] arr = groupAndPerson.split(",");
            for (int i = 0; i < arr.length; i++) {
                String str = arr[i];
                if (str != null) {
                    String[] arrStr = str.split("\\|");
                    if (arrStr.length == 2) {
                        SystemAuditPersonConfig config = new SystemAuditPersonConfig();
                        config.setId(UUIDGenerator.getUUID());
                        config.setBusinessModel(parameterBean.getBusinessModel());
                        config.setBusinessType(approvalHead.getType());
                        config.setBusinessData(approvalHead.getId());
                        config.setGroupCode(arrStr[0]);
                        config.setAuditPerson(arrStr[1]);
                        config.setRptDate(new Date());
                        config.setRptPersonId(approvalHead.getRptPersonId());
                        config.setRptPersonName(approvalHead.getRptPersonName());
                        // 审批人员
                        PersonInfoManage personInfoManage = personInfoManageDao.queryByOid(new PersonInfoManage(arrStr[1]));
                        config.setAuditPersonName(personInfoManage.getUserCname());
                        if (personInfoManage != null) {
                            List<OrganizationInfo> orgList = organizationInfoDao.queryOrgByPersonId(new OrganizationInfo(arrStr[1], ""));
                            if (orgList != null && orgList.size() > 0) {
                                OrganizationInfo org = orgList.get(0);
                                if (org != null) {
                                    config.setAuditOrgId(org.getId());
                                    config.setAuditOrgCode(org.getOrgCode());
                                    config.setAuditOrgName(org.getOrgCname());
                                }
                            }
                        }
                        systemAuditPersonConfigDao.insert(config);
                    }
                }
            }
        }
    }

    /**
     * 通过oid获取详细信息
     *
     * @param oid
     * @return
     * @author LvXL
     */
    public ApprovalHead get(String oid) {
        return approvalHeadDao.get(new ApprovalHead(oid));
    }

    /**
     * 同意或拒绝
     *
     * @param oid
     * @param auditStatus
     * @return
     * @author LvXL
     */
    public int check(String oid, String auditStatus) {

        ApprovalHead ah = new ApprovalHead();
        ah.setId(oid);
        ah.setAuditStatus(auditStatus);

        return approvalHeadDao.check(ah);
    }

    /**
     * 同意或拒绝
     *
     * @param oid
     * @param auditStatus
     * @return
     * @author LvXL
     */
    public int check(String oid, String auditStatus, String workflowId) {
        ApprovalHead ah = new ApprovalHead();
        ah.setId(oid);
        ah.setAuditStatus(auditStatus);
        ah.setWorkFlowId(workflowId);
        return approvalHeadDao.check(ah);
    }

    public String query(String personId, String queryFlag, String headId) {
        List<ApprovalHead> res = this.query(personId, queryFlag);
        for (ApprovalHead re : res) {
            if (headId.equals(re.getId())) {
                return re.getTaskId();
            }
        }

        return null;
    }

    /**
     * 查询所有personId创建的数据(移动端)
     * 1:我发起的,2:我审批的,3:待我审批的
     *
     * @param personId
     * @return
     * @author LvXL
     */
    public List<ApprovalHead> query(String personId, String queryFlag) {

        List<ApprovalHead> list = new ArrayList<ApprovalHead>();
        if (queryFlag != null && "1".equals(queryFlag)) {
            // 我发起的
            ApprovalHead ah = new ApprovalHead();
            ah.setRptPersonId(personId);
            list = approvalHeadDao.query(ah);

        } else if (queryFlag != null && "2".equals(queryFlag)) {
            // 我审批的
            SystemAudit systemAudit = new SystemAudit();
            systemAudit.setAuditPerson(personId);
            systemAudit.setBusinessModel(1);
            List<SystemAudit> res = systemAuditDao.query(systemAudit);
            List<String> ids = new ArrayList<String>();
            for (SystemAudit re : res) {
                ids.add(re.getBusinessData());
            }
            if (ids != null && ids.size() > 0) {
                ApprovalHead param = new ApprovalHead();
                param.setIdList(ids);
                list = approvalHeadDao.query(param);
            }
        } else if (queryFlag != null && "3".equals(queryFlag)) {
            // 待我审批的
            List<Map<String, String>> mapList = simpleflowsManager.workTasks(personId);
            if (mapList != null && mapList.size() > 0) {
                List<String> ids = new ArrayList<String>();
                Map<String, String> tmp = new HashMap<String, String>();
                for (Map<String, String> map : mapList) {
                    ids.add(map.get("headId"));
                    tmp.put(map.get("headId"), map.get("taskId"));
                }
                ApprovalHead param = new ApprovalHead();
                param.setIdList(ids);
                list = approvalHeadDao.query(param);
                // set taskId for workflow
                for (ApprovalHead approvalHead : list) {
                    approvalHead.setTaskId(tmp.get(approvalHead.getId()));
                }
            }
        }
        return list;
    }

    /**
     * 查询所有personId创建的数据(PC端)
     * 1:我发起的,2:我审批的,3:待我审批的
     *
     * @param personId
     * @return
     * @author LvXL
     */
    public List<ApprovalHead> query(String personId, String queryFlag, ApprovalHead param) {

        List<ApprovalHead> list = new ArrayList<ApprovalHead>();
        if (queryFlag != null && "1".equals(queryFlag)) {
            // 我发起的
            param.setRptPersonId(personId);
            list = approvalHeadDao.query(param);

        } else if (queryFlag != null && "2".equals(queryFlag)) {
            // 我审批的
            SystemAudit systemAudit = new SystemAudit();
            systemAudit.setAuditPerson(personId);
            systemAudit.setBusinessModel(1);
            List<SystemAudit> res = systemAuditDao.query(systemAudit);
            List<String> ids = new ArrayList<String>();
            for (SystemAudit re : res) {
                ids.add(re.getBusinessData());
            }
            if (ids != null && ids.size() > 0) {
                param.setIdList(ids);
                list = approvalHeadDao.query(param);
            }
        } else if (queryFlag != null && "3".equals(queryFlag)) {
            // 待我审批的
            List<Map<String, String>> mapList = simpleflowsManager.workTasks(personId);
            if (mapList != null && mapList.size() > 0) {
                List<String> ids = new ArrayList<String>();
                Map<String, String> tmp = new HashMap<String, String>();
                for (Map<String, String> map : mapList) {
                    ids.add(map.get("headId"));
                    tmp.put(map.get("headId"), map.get("taskId"));
                }
                param.setIdList(ids);
                list = approvalHeadDao.query(param);
                // set taskId for workflow
                for (ApprovalHead approvalHead : list) {
                    approvalHead.setTaskId(tmp.get(approvalHead.getId()));
                }
            }
        } else if (queryFlag != null && "4".equals(queryFlag)) {
            list = approvalHeadDao.query(param);
        }
        return list;
    }

    /**
     * 查询审批信息
     *
     * @param oid
     * @return
     */
    public List<AuditData> queryAuditDataList(String oid) {

        List<AuditData> resList = new ArrayList<AuditData>();
        // 审批配置表
        List<SystemAuditPersonConfig> list = systemAuditPersonConfigDao.queryAuditPerson(oid);
        if (list != null && list.size() >= 2) {
            AuditData ad = null;
            for (SystemAuditPersonConfig config : list) {

                ad = new AuditData();
                ad.setGroupCode(config.getGroupCode());
                ad.setAuditPerson(config.getAuditPerson());
                ad.setAuditPersonName(config.getAuditPersonName());

                // 查询审批表
                SystemAudit sa = new SystemAudit();
                sa.setBusinessData(oid);
                sa.setAuditPerson(config.getAuditPerson());
                List<SystemAudit> audits = systemAuditDao.queryAuditPerson(sa);
                if (audits != null && audits.size() >= 1) {
                    // 已开始审批
                    ad.setAuditDate(audits.get(0).getAuditDate());
                    ad.setAuditStatus(audits.get(0).getAuditStatus());
                    ad.setAuditRemark(audits.get(0).getAuditRemark());
                } else {
                    // 还未开始审批
                    if (CommonVariables.APPROVEL_GROUP_2ND_CODE.equals(config.getGroupCode())) {
                        // 二级审批准状态，未开始
                        // 一级未审批
                        AuditData first = resList.get(0);
                        if (first.getAuditStatus().equals(CommonVariables.APPROVEL_RUN + "")) {
                            ad.setAuditStatus(CommonVariables.APPROVEL_UNDONE + "");
                        } else if (first.getAuditStatus().equals(CommonVariables.APPROVEL_PASS + "")) {
                            ad.setAuditStatus(CommonVariables.APPROVEL_RUN + "");
                        } else if (first.getAuditStatus().equals(CommonVariables.APPROVEL_NO_PASS + "")) {
                            ad.setAuditStatus(CommonVariables.APPROVEL_UNDONE + "");
                        }
                    } else {
                        // 一级审批状态，进行中
                        ad.setAuditStatus(CommonVariables.APPROVEL_RUN + "");
                    }
                    ad.setAuditRemark("");
                }
                resList.add(ad);
            }
        }
        return resList;
    }

}
