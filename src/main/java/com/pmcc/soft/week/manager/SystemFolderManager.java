package com.pmcc.soft.week.manager;

import com.pmcc.soft.core.common.CommonVariables;
import com.pmcc.soft.core.organization.dao.OrganizationInfoDao;
import com.pmcc.soft.core.organization.dao.PersonInfoManageDao;
import com.pmcc.soft.core.organization.domain.OrganizationInfo;
import com.pmcc.soft.core.organization.domain.PersonInfoManage;
import com.pmcc.soft.core.utils.SystemParamsUtil;
import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.dao.SystemFolderDao;
import com.pmcc.soft.week.domain.SystemFolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 文件夹表
 * Created by LvXL on 2016/5/9.
 */
@Transactional
@Service
public class SystemFolderManager {

    @Autowired
    SystemFolderDao systemFolderDao;

    @Autowired
    PersonInfoManageDao personInfoManageDao;

    @Autowired
    OrganizationInfoDao organizationInfoDao;

    public int update(SystemFolder folder){
        return systemFolderDao.update(folder);
    }

    /**
     * 通过id获取对象
     * @param id
     * @return
     */
    public SystemFolder get(String id){
        if(id != null && !"".equals(id)){
            SystemFolder folder = new SystemFolder(id);
            List<SystemFolder> list = systemFolderDao.query(folder);
            if(list != null && list.size() > 0){
                return list.get(0);
            }
        }
        return null;
    }

    /**
     * 判断默认的根文件夹是否存在，如果不存在新增
     * @param personId
     * @param folderType 0：公共文件夹；1：个人文件夹
     * @return  true : 已存在； false：不存在，需要创建
     */
    public boolean judgeFolder(String personId, String folderType){

        boolean res = false;
        if("0".equals(folderType)){
            // 公共文件夹
            PersonInfoManage person = personInfoManageDao.queryByOid(new PersonInfoManage(personId));
            if(person != null){
                SystemFolder sf = new SystemFolder();
//                sf.setCompanyId(person.getCompanyId());
                sf.setFolderType("0");
                List<SystemFolder> list = systemFolderDao.query(sf);
                if(list != null && list.size() > 0){
                    res = true;
                }
            }
        }else if("1".equals(folderType)){
            // 个人文件夹
            SystemFolder sf = new SystemFolder();
            sf.setRptPerson(personId);
            sf.setFolderType("1");
            List<SystemFolder> list = systemFolderDao.query(sf);
            if(list != null && list.size() > 0){
                res = true;
            }
        }
        return res;
    }

    /**
     * 查询文件夹列表
     * @param personId
     * @param folderType
     * @return
     */
    public List<SystemFolder> queryFolder(String personId, String folderType){

        List<SystemFolder> list = new ArrayList<SystemFolder>();
        // 判断文件夹是否存在
        boolean res = this.judgeFolder(personId, folderType);
        if(!res){
            // 不存在，需要新增
            this.newFolder(personId, folderType);
        }
        // 查询返回
        SystemFolder sf = new SystemFolder();
        if("0".equals(folderType)){
            // 公共
            sf.setFolderType(folderType);
        }else if("1".equals(folderType)){
            // 个人
            sf.setRptPerson(personId);
            sf.setFolderType(folderType);
        }
        list = systemFolderDao.query(sf);
        return list;
    }

    /**
     * 新增文件夹
     * @param personId
     * @param folderType
     */
    public void newFolder(String personId, String folderType){
        // 新增
        PersonInfoManage person = personInfoManageDao.queryByOid(new PersonInfoManage(personId));
        SystemFolder sf = null;
        if("0".equals(folderType)){

            // 公共文件夹
            sf = new SystemFolder();
            sf.setId(UUIDGenerator.getUUID());
            sf.setFolderName(CommonVariables.PUBLIC_FOLDER_NAME);
            sf.setFolderCode("001");
            sf.setFolderType(folderType);
            sf.setFolderTotalSize(CommonVariables.PUBLIC_FOLDER_SIZE);
            sf.setFolderUsedSize(0l);
            sf.setRptPerson(personId);
            sf.setRptPersonName(person.getUserCname());
            sf.setCompanyId(person.getCompanyId());
            sf.setRptDate(new Date());
            sf.setDelFlag("0");

        }else if("1".equals(folderType)){
            // 创建人
            // 个人文件夹
            sf = new SystemFolder();
            sf.setId(UUIDGenerator.getUUID());
            sf.setFolderName(CommonVariables.PRIVATE_FOLDER_NAME);
            sf.setFolderCode("001");
            sf.setFolderType(folderType);
            sf.setFolderTotalSize(CommonVariables.PRIVATE_FOLDER_SIZE);
            sf.setFolderUsedSize(0l);
            sf.setRptPerson(personId);
            sf.setRptPersonName(person.getUserCname());
            sf.setRptDate(new Date());
            if(person != null){
                sf.setRptPerson(personId);
                sf.setRptPersonName(person.getUserCname());
                sf.setRptDate(new Date());
                sf.setCompanyId(person.getCompanyId());
                sf.setDelFlag("0");
                // 机构
                OrganizationInfo info = new OrganizationInfo();
                info.setPersonId(personId);
                List<OrganizationInfo> orgList = organizationInfoDao.queryOrgByPersonId(info);
                if(orgList != null && orgList.size() > 0){
                    OrganizationInfo org = orgList.get(0);
                    sf.setOrgId(org.getId());
                    sf.setOrgName(org.getOrgCname());
                    sf.setOrgCode(org.getOrgCode());
                }
            }
        }
        systemFolderDao.insert(sf);
    }

}
