package com.pmcc.soft.ydpt.manager;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import com.hndl.mobileplatform.service.PublicExcuteSqlService;
import com.pmcc.soft.ydpt.dao.OfMucMemberDao;
import com.pmcc.soft.ydpt.domain.OfMucMember;

/**
 * Created by sunyongxing
 * on 2015/6/8 0008 9:17
 */
@Service
@Transactional
public class OfMucMemberManager {

    @Autowired
    OfMucMemberDao ofMucMemberDao;
    @Autowired
    PublicExcuteSqlService publicExcuteSqlService;

    /**
     * 新增
     * @param omm
     */
    public int insert(OfMucMember omm){
        //omr.setCreationDate(new Long(new Date().getTime()).toString());
    	
        return ofMucMemberDao.insert(omm);
    }

    /**
     * 修改
     * @param omm
     */
    public int update(OfMucMember omm){
        //omr.setModificationDate(new Long(new Date().getTime()).toString());
    	
        return ofMucMemberDao.update(omm);
    }

    /**
     * 删除
     * @param omm
     */
    public int delete(OfMucMember omm){
        return ofMucMemberDao.delete(omm);
    }

    /**
     * 查询所有
     * @param omm
     * @return
     */
    public List<OfMucMember> query(OfMucMember omm ){
    	String roomID = omm.getRoomID() + "";
    	String sqls = "SELECT * FROM ofMucAffiliation WHERE roomID ='"+roomID+"'";
        List<Map<String,String>> li = publicExcuteSqlService.executeSql(sqls);  
         List<OfMucMember> list= ofMucMemberDao.query(omm);
      /*   List<OfMucMember> list=new ArrayList<OfMucMember>();*/
        /* for(OfMucMember ofMucMember:OfMuclist){
        	 String ofMucMembersql="select r.DEL_FLAG from  PERSON_INFO r WHERE r.USER_ENAME='"+ofMucMember.getNickname()+"'";
        	 List<Map> ofMucMemberlist=publicExcuteSqlService.executeSql(ofMucMembersql);
        	 if(ofMucMemberlist.get(0).get("DEL_FLAG").toString().equals("0")){
        		 list.add(ofMucMember);
        	 }
         }*/
         for(OfMucMember l : list){
        	 //查询所属部门和电话，放在前台，由于所属部门在person_info表中没有所以需要关联查询
        	 String sql = "SELECT * FROM ORGANIZATION a,PERSON_INFO t WHERE a.OID = (SELECT t.ORGANIZATION_INFO_ID FROM ORGAN_PERSON_RELATION_INFO t WHERE t.PERSON_INFO_ID =(SELECT p.OID FROM PERSON_INFO p WHERE p.USER_ENAME='"+l.getNickname()+"')) AND t.USER_ENAME = '"+l.getNickname()+"' order by   t.SORT desc";
        	 List<Map<String,String>> lists = publicExcuteSqlService.executeSql(sql);
        	 //在实体中加了两个临时字段，只用于显示，org_cname为所属部门的中文名称
        	 l.setDepartment(lists.get(0).get("ORG_CNAME"));
        	 //人员电话
        	 l.setTelephone(lists.get(0).get("TELEPHONE"));
        	 //年龄
        	 l.setAge(lists.get(0).get("AGE"));
        	 //部门电话
        	 l.setOfficephone(lists.get(0).get("OFFICEPHONE"));
        	 //QQ
        	 l.setQq(lists.get(0).get("USER_QQ"));
        	 //邮箱
        	 l.setUserMail(lists.get(0).get("USER_MAIL"));
        	 //职务
        	 l.setDuty(lists.get(0).get("DUTY"));
        	 l.setRemark(lists.get(0).get("REMARK1"));
        	 String sort=String.valueOf(lists.get(0).get("SORT"));
        	 l.setSort(sort);
        	 
        	 if(l.getJid().equals(li.get(0).get("jid"))){
            	 l.setIsOrAdmin("管理员");
             }else{
            	 l.setIsOrAdmin("群成员");
             }
         }
         
         
        
    	return list;
    }

    /**
     * 根据roomID查找
     * @param roomID
     * @return
     */
    public List<OfMucMember> findByRoomId(int roomID){
        return ofMucMemberDao.findByRoomId(roomID);
    }
}


