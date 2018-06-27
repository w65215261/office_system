package com.pmcc.soft.core.organization.manager;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.pmcc.soft.core.organization.domain.OrganPersonRelation;
import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.week.domain.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pmcc.soft.core.organization.dao.OrganizationInfoDao;
import com.pmcc.soft.core.organization.dao.OrganizationRelationDao;
import com.pmcc.soft.core.organization.domain.OrganizationInfo;
import com.pmcc.soft.core.organization.domain.OrganizationRelation;
import com.pmcc.soft.core.utils.UUIDGenerator;

@Transactional
@Service
public class OrganizationInfoManager {

    @Autowired
    private OrganizationInfoDao organizationInfoDao;
    @Autowired
    private OrganizationRelationDao organizationRelationDao;
    @Autowired
    private OrganizationRelationManager organizationRelationManager;
    @Autowired
    private OrganPersonRelationManager organPersonRelationManager;
    @Autowired
    private PersonManageManager personManageManager;


    public void save(OrganizationInfo organizationInfo) {

        String id = organizationInfo.getId();

        if (id != null && !id.trim().equals("")) {
            OrganizationRelation organizationRelation = organizationRelationDao.queryOrgRbyOrgId(id).get(0);
            //查找父机构关系记录
            OrganizationRelation organizationRelationF = organizationRelationDao.queryOrgRbyOrgId(organizationInfo.getFunitOid()).get(0);
            String foid = organizationRelationF.getId();
            organizationRelation.setOrganizationRelationId(foid);
            organizationRelationDao.update(organizationRelation);
            organizationInfoDao.update(organizationInfo);
        } else {
            Timestamp now = Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
            organizationInfo.setId(UUIDGenerator.getUUID());
            organizationInfoDao.insert(organizationInfo);
            //查找父节点
            OrganizationRelation organR = new OrganizationRelation();
            String forgId = organizationInfo.getFunitOid();
            organR = organizationRelationDao.queryOrgRbyOrgId(forgId).get(0);

            OrganizationRelation organRe = new OrganizationRelation();
            organRe.setOrganizationInfoId(organizationInfo.getId());
            organRe.setOrganizationRelationId(organR.getId());
            organRe.setRelation("1");
            organRe.setOrganOrder("1");
            organRe.setCreateDate(now);
            organizationRelationManager.save(organRe);
        }
    }

    public List<OrganizationInfo> query(OrganizationInfo organizationInfo) {

        return organizationInfoDao.query();
    }

    public void delete(OrganizationInfo organizationInfo) {
        //查找出此机构下级单位
        OrganizationRelation organR = new OrganizationRelation();
        organR = organizationRelationDao.queryOrgRbyOrgId(organizationInfo.getId()).get(0);
        List<OrganizationRelation> organRlist = organizationRelationDao.queryOrgRbyOrgFoid(organR.getId());
        for (OrganizationRelation it : organRlist) {
            List<OrganizationRelation> organrlist = organizationRelationDao.queryOrgRbyOrgFoid(it.getId());
            OrganizationInfo organ = new OrganizationInfo();
            organ = organizationInfoDao.queryOrgById(it.getOrganizationInfoId());
            if (organrlist.size() == 0) {
                organizationRelationDao.delete(it);
                organizationInfoDao.delete(organ);
            } else {
                delete(organ);
            }
        }
        //再删除初始单位及关系
        organizationRelationDao.delete(organR);
        organizationInfoDao.delete(organizationInfo);
    }

    public List<OrganizationInfo> queryOrgtree(String id, String flag) {
        List<OrganizationInfo> organList = new ArrayList<OrganizationInfo>();
        if (id.equals("-1")) {
            OrganizationRelation organRe = organizationRelationDao.queryOrgRoot();
            String orgId = organRe.getOrganizationInfoId();
            OrganizationInfo organizationInfo = organizationInfoDao.queryOrgById(orgId);
            //
            List<OrganizationRelation> organReList = organizationRelationDao.queryOrgRbyOrgFoid(organRe.getId());
            if (organReList.size() > 0)
                organizationInfo.setState("closed");
            else
                organizationInfo.setState("open");
            //
            organList.add(organizationInfo);
        } else {
            List<OrganizationRelation> organRList = organizationRelationDao.queryOrgRbyOrgId(id);
            OrganizationRelation organR = organRList.get(0);
            String foid = organR.getId();
            List<OrganizationRelation> organRlist = organizationRelationDao.queryOrgRbyOrgFoid(foid);

            for (OrganizationRelation it : organRlist) {
                String orgId = it.getOrganizationInfoId();
                OrganizationInfo organInfo = new OrganizationInfo();
                if (flag.equals("ORG")) {

                    organInfo.setId(orgId);
                    organInfo.setOrgType("1");
                } else {
                    organInfo.setId(orgId);
                }
                OrganizationInfo organizationInfo = this.find(organInfo);
                //
                if (organizationInfo == null) {
                    continue;
                }
                List<OrganizationRelation> organReList = organizationRelationDao.queryOrgRbyOrgFoid(it.getId());
                if (organReList.size() > 0)
                    organizationInfo.setState("closed");
                else
                    organizationInfo.setState("open");
                //
                organList.add(organizationInfo);
            }
        }
        return organList;
    }

    public OrganizationInfo find(OrganizationInfo organizationInfo) {
        List<OrganizationInfo> queryTwo = organizationInfoDao.queryTwo(organizationInfo);
        if (queryTwo.size() > 0) {
            return queryTwo.get(0);
        } else {
            return null;
        }
    }

    public OrganizationInfo queryOrgById(String oid) {
        return organizationInfoDao.queryOrgById(oid);
    }

    public String qeruyFoidByorgId(String orgId) {
        OrganizationRelation organR = organizationRelationDao.queryOrgRbyOrgId(orgId).get(0);
        String fId = organR.getOrganizationRelationId();
        return organizationRelationDao.queryOrgRById(fId).getOrganizationInfoId();
    }

    public OrganizationInfo findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    //遍历整个组织机构树，生成treenode树用于bootstrap tree view 展示组织机构树
    public TreeNode queryAllTree(TreeNode treeNode, String id) {

        TreeNode node = new TreeNode();
        if (id.equals("-1")) {
            String userId = treeNode.getUserId();
            PersonManage pmg = personManageManager.queryPersonById(userId);
            OrganizationInfo organizationInfo = organizationInfoDao.queryOrgById(pmg.getCompanyId());//找到根节点
            OrganizationRelation organRe = organizationRelationDao.queryOrgRbyOrgId(pmg.getCompanyId()).get(0);
            node.setHref(organizationInfo.getId());
            node.setText(organizationInfo.getOrgCname());
            node.setCode(organizationInfo.getOrgCode());
            //循环根节点的子节点
            List<OrganizationRelation> organReList = organizationRelationDao.queryOrgRbyOrgFoid(organRe.getId());
            if (organReList.size() > 0) {
                for (OrganizationRelation organizationRelation : organReList) {
                    OrganizationInfo info = organizationInfoDao.queryOrgById(organizationRelation.getOrganizationInfoId());
                    TreeNode node1 = new TreeNode(info.getId(), info.getOrgCname(), info.getOrgCode(), "glyphicon glyphicon-tower");
                    node.getNodes().add(node1);
                    queryAllTree(node1, organizationRelation.getOrganizationInfoId());
                }
                List<OrganPersonRelation> oplist = organPersonRelationManager.findByOrgId(organizationInfo.getId());
                for (OrganPersonRelation organPersonRelation : oplist) {

                    PersonManage pm = personManageManager.queryPersonById(organPersonRelation.getPersonInfoId());
                    TreeNode personNode = new TreeNode(pm.getId(), pm.getUserCname(), "glyphicon glyphicon-user");
                    node.getNodes().add(personNode);
                }
                return node;
            } else {
                return node;
            }
        } else {

            List<OrganizationRelation> organRList = organizationRelationDao.queryOrgRbyOrgId(id);
            OrganizationRelation organR = organRList.get(0);
            String foid = organR.getId();
            List<OrganizationRelation> organRlist = organizationRelationDao.queryOrgRbyOrgFoid(foid);

            for (OrganizationRelation it : organRlist) {
                String orgId = it.getOrganizationInfoId();
                OrganizationInfo organizationInfo = organizationInfoDao.queryOrgById(orgId);
                TreeNode nodetmp = new TreeNode(organizationInfo.getId(), organizationInfo.getOrgCname(), organizationInfo.getOrgCode(), "glyphicon glyphicon-tower");
                treeNode.getNodes().add(nodetmp);
                List<OrganizationRelation> organReList = organizationRelationDao.queryOrgRbyOrgFoid(it.getId());
                if (organReList.size() > 0) {

                    queryAllTree(nodetmp, organizationInfo.getId());
                } else {//终止条件
                    List<OrganPersonRelation> oplist = organPersonRelationManager.findByOrgId(it.getOrganizationInfoId());
                    for (OrganPersonRelation organPersonRelation : oplist) {

                        PersonManage pm = personManageManager.queryPersonById(organPersonRelation.getPersonInfoId());
                        if (pm == null) continue;
                        TreeNode personNode = new TreeNode(pm.getId(), pm.getUserCname(), "glyphicon glyphicon-user");
                        nodetmp.getNodes().add(personNode);
                    }
                }
            }
            List<OrganPersonRelation> oplist = organPersonRelationManager.findByOrgId(id);
            for (OrganPersonRelation organPersonRelation : oplist) {

                PersonManage pm = personManageManager.queryPersonById(organPersonRelation.getPersonInfoId());
                TreeNode personNode = new TreeNode(pm.getId(), pm.getUserCname(), "glyphicon glyphicon-user");
                treeNode.getNodes().add(personNode);
            }
        }
        return node;
    }


    //只显示部门不显示人员
    public TreeNode queryAllOrganTree(TreeNode treeNode, String id) {

        TreeNode node = new TreeNode();
        if (id.equals("-1")) {
            String userId = treeNode.getUserId();
            PersonManage pm = personManageManager.queryPersonById(userId);
            OrganizationInfo organizationInfo = organizationInfoDao.queryOrgById(pm.getCompanyId());//找到根节点
            OrganizationRelation organRe = organizationRelationDao.queryOrgRbyOrgId(pm.getCompanyId()).get(0);
            node.setHref(organizationInfo.getId());
            node.setText(organizationInfo.getOrgCname());
            //循环根节点的子节点
            List<OrganizationRelation> organReList = organizationRelationDao.queryOrgRbyOrgFoid(organRe.getId());
            //查找登陆人的所属公司
            if (organReList.size() > 0) {
                for (OrganizationRelation organizationRelation : organReList) {
                    OrganizationInfo info = organizationInfoDao.queryOrgById(organizationRelation.getOrganizationInfoId());
                    TreeNode node1 = new TreeNode(info.getId(), info.getOrgCname(), info.getOrgCode(), "glyphicon glyphicon-tower");
                    node.getNodes().add(node1);
                    queryAllOrganTree(node1, organizationRelation.getOrganizationInfoId());
                }
                return node;
            } else {
                return node;
            }
        } else {

            List<OrganizationRelation> organRList = organizationRelationDao.queryOrgRbyOrgId(id);
            OrganizationRelation organR = organRList.get(0);
            String foid = organR.getId();
            List<OrganizationRelation> organRlist = organizationRelationDao.queryOrgRbyOrgFoid(foid);

            for (OrganizationRelation it : organRlist) {
                String orgId = it.getOrganizationInfoId();
                OrganizationInfo organizationInfo = organizationInfoDao.queryOrgById(orgId);
                TreeNode nodetmp = new TreeNode(organizationInfo.getId(), organizationInfo.getOrgCname());
                treeNode.getNodes().add(nodetmp);
                List<OrganizationRelation> organReList = organizationRelationDao.queryOrgRbyOrgFoid(it.getId());
                if (organReList.size() > 0) {

                    queryAllOrganTree(nodetmp, organizationInfo.getId());
                }
            }
        }
        return node;
    }


    public List<OrganizationInfo> queryOrgByOrgId(OrganizationInfo organizationInfo) {

        return organizationInfoDao.queryOrgByOrgId(organizationInfo);

    }

    /**
     * 机构编码唯一性验证
     *
     * @param orgCode
     * @return
     */
    public List<OrganizationInfo> queryOrgCode(String orgCode) {
        return organizationInfoDao.queryOrgCode(orgCode);
    }

    public OrganizationInfo queryOrgCname(String oid) {
        return organizationInfoDao.queryOrgCname(oid);
    }

    public int insertOrg(OrganizationInfo organizationInfo) {
        int res = 0;
        res = organizationInfoDao.insertOrg(organizationInfo);

        return res;
    }

    public List<OrganizationInfo> queryBussiness(OrganizationInfo organizationInfo) {
        return organizationInfoDao.queryBussiness(organizationInfo);
    }

    public void updateCompany(OrganizationInfo organizationInfo) {
        organizationInfoDao.updateCompany(organizationInfo);
    }

    public OrganizationInfo queryOrgByOrgId(String orgId) {

        return organizationInfoDao.queryOrgByOrgId(orgId);

    }

    public List<OrganizationInfo> queryOrgByPersonId(String personId) {
        OrganizationInfo org = new OrganizationInfo();
        org.setPersonId(personId);
        return organizationInfoDao.queryOrgByPersonId(org);
    }

}
