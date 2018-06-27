package com.pmcc.soft.ydpt.manager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.WebUtils;

import com.hndl.mobileplatform.model.Dlspacemanager;
import com.hndl.mobileplatform.service.DlSpaceManagerService;
import com.hndl.mobileplatform.service.PublicExcuteSqlService;
import com.pmcc.soft.core.common.OnlineUser;
import com.pmcc.soft.core.utils.AppUtils;
import com.pmcc.soft.ydpt.dao.OfMucRoomDao;
import com.pmcc.soft.ydpt.domain.OfMucRoom;

/**
 * Created by sunyongxing
 * on 2015/6/4 0004 12:09
 */
@Service
@Transactional
public class OfMucRoomManager {

    @Autowired
    OfMucRoomDao ofMucRoomDao;
    @Autowired
    DlSpaceManagerService dlSpaceManagerService;
    @Autowired
    PublicExcuteSqlService publicExcuteSqlService;
    /**
     * 新增
     * @param omr
     */
    public int insert(OfMucRoom omr){
        //每次新增前查询roomId并取其最大值，然后加1
        List<OfMucRoom> ofMucRooms = ofMucRoomDao.queryRoomId(omr);
        if (ofMucRooms != null && ofMucRooms.size() > 0){
        	if(ofMucRooms.get(0).getRoomID()<1000000){
        		 omr.setRoomID(1000000);
        	}else{
        		 omr.setRoomID(ofMucRooms.get(0).getRoomID() + 1);
        	}
        } else {
            omr.setRoomID(1000000);
        }
        omr.setCreationDate("00"+new Long(new Date().getTime()).toString());
        omr.setModificationDate("00" + new Long(new Date().getTime()).toString());
        omr.setEmptyDate("00"+new Long(new Date().getTime()).toString());

        
        /**
         * 群组中增加了空间名称空间大小两个input所以要在
         * 空间表中加数据代码如下
         */
        //创建一个Dlspacemanager对象
        Dlspacemanager dsm = new Dlspacemanager();
  
        Long d = Long.parseLong(omr.getSpacesize()); 
        //数据库存储以B 为单位，而前台输入框内是一M 为单位 所以是 输入框的值 X 1024的三次方
		Long	w = (long) (1024 * 1024);
		Long c = d*w;
       
        //空间名字
        if(!omr.getPacename().equals("") && omr.getPacename() != null){
        	dsm.setPacename(omr.getPacename());
        }else{
        	dsm.setPacename(omr.getNaturalName()+"的空间");
        }
        
        //当前时间
        dsm.setCreatetime(String.valueOf(new Date().getTime()));
        //群id
        dsm.setRoomoruserid(omr.getRoomID()+"");
        /**
		 * 感觉这里不能这样写
		 * roomoruser  1是群2是个人
		 * 感觉没有注释的话别人并不是很能看懂
		 */
        dsm.setRoomoruser(1);
        dsm.setSpaceusesize("0");
        //空间大小
        dsm.setSpacesize(c+"");
        ofMucRoomDao.insert(omr);
        return dlSpaceManagerService.inster(dsm);
        
       
    }

    /**
     * 修改
     * @param omr
     */
    public int update(OfMucRoom omr){
        omr.setModificationDate("00"+new Long(new Date().getTime()).toString());
        return ofMucRoomDao.update(omr);
    }

    /**
     * 删除
     * @param omr
     */
    public int delete(OfMucRoom omr){
    	//由于删除群组后所关联的表太多，sql语句删除又只能一个一个来，所以命名太费劲，就简化了
    	String sql1 = "DELETE FROM ofMucAffiliation  WHERE  roomID='"+omr.getRoomID()+"' ";
    	String sql2 = "DELETE FROM dlSpaceManager WHERE roomoruserid='"+omr.getRoomID()+"' ";
    	String sql3 = "DELETE FROM ofMucMember WHERE roomID='"+omr.getRoomID()+"' ";
    	String sql4 = "DELETE FROM dlFilesManager WHERE userorroomid='"+omr.getRoomID()+"' ";
    	String sql5 = "DELETE FROM dlRoomPost WHERE roomid='"+omr.getRoomID()+"' ";
    	String sql6 = "DELETE FROM dlRoomAffiche WHERE roomid='"+omr.getRoomID()+"' ";
    	publicExcuteSqlService.updateBySql(sql1);//删除ofMucAffiliation表
    	publicExcuteSqlService.updateBySql(sql2);//删除dlSpaceManager表
    	publicExcuteSqlService.updateBySql(sql3);//删除ofMucMember表
    	publicExcuteSqlService.updateBySql(sql4);//删除dlFilesManager表
    	publicExcuteSqlService.updateBySql(sql5);//删除dlRoomPost表
    	publicExcuteSqlService.updateBySql(sql6);//删除dlRoomAffiche表
        return ofMucRoomDao.delete(omr);
    }

    /**
     * 查询所有
     * @param omr
     * @return
     */
    public List<OfMucRoom> query(OfMucRoom omr){
        return ofMucRoomDao.query(omr);
    }

    /**
     * 根据id查找
     * @param id
     * @return
     */
    public OfMucRoom findById(String id){
        return ofMucRoomDao.findById(id);
    }

    /**
     * 根据roomID查询
     * @param roomID
     * @return
     */
    public OfMucRoom findByRoomID(int roomID){
        return ofMucRoomDao.findByRoomID(roomID);
    }

}
