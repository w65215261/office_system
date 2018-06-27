package com.pmcc.soft.week.manager;

import com.pmcc.soft.core.organization.dao.PersonManageDao;
import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.dao.GroupDiscussDao;
import com.pmcc.soft.week.domain.GroupDiscuss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by yaonan on 2015/7/17.
 */
@Service
@Transactional
public class GroupDiscussManager {
    @Autowired
    GroupDiscussDao groupDiscussDao;
    @Autowired
    PersonManageDao personManageDao;

    public void insert(GroupDiscuss param){
        param.setId(UUIDGenerator.getUUID());
        param.setDelFlag("0");
        param.setBrowseNumber(0);
        param.setDiscussType("0");
        param.setLockFlag("0");
        param.setModifiedDate(new Date());
        param.setReplyPersonNumber(0);
        param.setRptDate(new Date());


        groupDiscussDao.insert(param);
    }
    public void update(GroupDiscuss param){
        groupDiscussDao.update(param);
    }
    public void delete(GroupDiscuss param){
        groupDiscussDao.delete(param.getId());
    }
    public List<GroupDiscuss> query(GroupDiscuss param){
        List <GroupDiscuss> list=groupDiscussDao.query(param);
        return list;
    }
    public GroupDiscuss findById(GroupDiscuss param){
        return (GroupDiscuss)groupDiscussDao.findById(param.getId());
    }
    public GroupDiscuss queryNew(GroupDiscuss param){
        return groupDiscussDao.queryNew(param);
    }
}
