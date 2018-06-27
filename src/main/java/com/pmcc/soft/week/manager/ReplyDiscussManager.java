package com.pmcc.soft.week.manager;

import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.dao.GroupDiscussDao;
import com.pmcc.soft.week.dao.ReplyDiscussDao;
import com.pmcc.soft.week.domain.GroupDiscuss;
import com.pmcc.soft.week.domain.ReplyDiscuss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by sunyongxing
 * on 2015/7/20 0020 17:48
 */
@Service
@Transactional
public class ReplyDiscussManager {

    @Autowired
    ReplyDiscussDao replyDiscussDao;
    @Autowired
    GroupDiscussDao groupDiscussDao;

    /**
     * 查询所有
     * @param r
     * @return
     */
    public List<ReplyDiscuss> query(ReplyDiscuss r){
        return replyDiscussDao.query(r);
    }

    /**
     * 新增
     * @param r
     * @return
     */
    public int insert(ReplyDiscuss r){
      String  discussId=r.getGroupDiscussOid();

      GroupDiscuss gd=(GroupDiscuss)groupDiscussDao.findById(discussId);
       int replyNumber= gd.getReplyPersonNumber();
        replyNumber++;
        gd.setReplyPersonNumber(replyNumber);
        groupDiscussDao.update(gd);

        r.setId(UUIDGenerator.getUUID());
        r.setDelFlag("0");
        r.setReplyDate(new Date());
        return replyDiscussDao.insert(r);
    }

    /**
     * 删除
     * @param r
     * @return
     */
    public int delete(ReplyDiscuss r){
        String id = r.getId();
        return replyDiscussDao.delete(id);
    }

    /**
     * 通过id查询
     * @param id
     * @return
     */
    public ReplyDiscuss findById(String id){
        ReplyDiscuss replyDiscusses =(ReplyDiscuss) replyDiscussDao.findById(id);
        if (replyDiscusses!=null) {
            return replyDiscusses;
        }
        return null;
    }
}
