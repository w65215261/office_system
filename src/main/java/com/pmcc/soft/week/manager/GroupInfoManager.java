package com.pmcc.soft.week.manager;

import com.pmcc.soft.week.dao.GroupInfoDao;
import com.pmcc.soft.week.domain.GroupInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sunyongxing
 * on 2015/7/20 0020 17:08
 */
@Service
@Transactional
public class GroupInfoManager {

    @Autowired
    GroupInfoDao groupInfoDao;

    /**
     * 查询所有
     * @param g
     * @return
     */
    public List<GroupInfo> query(GroupInfo g){
        return groupInfoDao.query(g);
    }

    /**
     * 新增
     * @param g
     * @return
     */
    public int insert(GroupInfo g){
        return groupInfoDao.insert(g);
    }

    /**
     * 删除
     * @param g
     * @return
     */
    public int delete(GroupInfo g){
        String id = g.getId();
        return groupInfoDao.delete(id);
    }

    public GroupInfo findById(String id){
        GroupInfo groupInfos = (GroupInfo)groupInfoDao.findById(id);
        if (groupInfos!=null ) {
            return groupInfos;
        }
        return null;
    }
}
