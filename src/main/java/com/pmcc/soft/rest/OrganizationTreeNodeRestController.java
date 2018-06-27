package com.pmcc.soft.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.pmcc.soft.core.common.View;
import com.pmcc.soft.core.organization.domain.*;
import com.pmcc.soft.core.organization.manager.*;
import com.pmcc.soft.core.utils.DateUtil;
import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.domain.Message;
import com.pmcc.soft.week.domain.MessageAttachment;
import com.pmcc.soft.week.manager.MessageAttachmentManager;
import com.pmcc.soft.week.manager.MessageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by asus on 2016/01/05.
 */
@Controller
@RequestMapping("organizationTreeNode")
public class OrganizationTreeNodeRestController {

    @Autowired
    OrganizationTreeNodeManager organizationTreeNodeManager;
    @Autowired
    OrganizationInfoManager organizationInfoManager;
    @Autowired
    OrganPersonRelationManager organPersonRelationManager;
    @Autowired
    PersonManageManager personManageManager;
    @Autowired
    OrganizationRelationManager organizationRelationManager;

    //查询组织机构
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public List<OrganizationTreeNode> query() {
        List<OrganizationTreeNode> list = organizationTreeNodeManager.query();
        list.remove(0);
        for (OrganizationTreeNode or : list) {
            String oid = or.getOrgID();
            OrganizationInfo organizationInfo = organizationInfoManager.queryOrgCname(oid);
            if (organizationInfo != null) {
                or.setOrgCname(organizationInfo.getOrgCname());
            }
        }
        return list;
    }

    @JsonView(View.RestView.class)
    @RequestMapping(value = "/queryPerson", method = RequestMethod.GET)
    @ResponseBody
    public List<OrganPersonRelation> queryPerson(String organizationInfoId) {
        List<OrganPersonRelation> list = organPersonRelationManager.findByOrgId(organizationInfoId);
        for (OrganPersonRelation or : list) {
            String oid = or.getPersonInfoId();
            PersonManage personManage = personManageManager.findPersonCnameByOid(oid);
            if (personManage != null) {
                or.setUserCname(personManage.getUserCname());
            } else {
                or.setUserCname("kong");
            }
        }
        for (int i = 0; i <= list.size() - 1; i++) {
            String userCname = list.get(i).getUserCname();
            if (userCname == "kong" && userCname.equals("kong")) {
                list.remove(i);
                i = 0;
            }
        }
        return list;
    }
    @JsonView(View.RestView.class)
    @RequestMapping(value = "/queryOrg", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> queryOrg(String personId, OrganizationInfo organizationInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        PersonManage personManage = personManageManager.findPersonCnameByOid(personId);
        String orgId = personManage.getCompanyId();

        organizationInfo = organizationInfoManager.queryOrgByOrgId(orgId);
        organizationInfo.setId(orgId);
        map.put("orgList", organizationInfo);
        return map;
    }
    @JsonView(View.RestView.class)
    @RequestMapping(value = "/queryOrgAndPerson", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> queryOrgAndPerson(String orgId,OrganizationInfo organizationInfo){
        Map<String, Object> map = new HashMap<String, Object>();
           String parantId= organizationRelationManager.queryORbyOrgId(orgId).getId();
           List<OrganizationRelation> organizationRelationList=organizationRelationManager.queryOrgRbyOrgFoid(parantId);
                    if(organizationRelationList.size()>0){
                            for(OrganizationRelation organizationRelation1:organizationRelationList){
                                OrganizationInfo organizationInfo1=organizationInfoManager.queryOrgById(organizationRelation1.getOrganizationInfoId());
                                organizationRelation1.setOrgCname(organizationInfo1.getOrgCname());
                            }
                        }
            List<OrganPersonRelation> organPersonRelationList=organPersonRelationManager.findByOrgId(orgId);
                        if(organPersonRelationList.size()>0) {
                            for (OrganPersonRelation organPersonRelation : organPersonRelationList) {
                                PersonManage personManage1 = personManageManager.findPersonCnameByOid(organPersonRelation.getPersonInfoId());
                                organPersonRelation.setUserCname(personManage1.getUserCname());
                            }
                        }
        map.put("orgList",organizationRelationList);
        map.put("personList",organPersonRelationList);
        return map;
    }
}