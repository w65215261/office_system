package com.pmcc.soft.week.manager;

import com.pmcc.soft.week.dao.GroupPersonDao;
import com.pmcc.soft.week.domain.GroupPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sunyongxing
 * on 2015/7/20 0020 17:26
 */
@Service
@Transactional
public class GroupPersonManager {

    @Autowired
    GroupPersonDao groupPersonDao;

    /**
     * 查询所有
     * @param g
     * @return
     */
    public List<GroupPerson> query(GroupPerson g){
        return groupPersonDao.query(g);
    }

    /**
     * 新增
     * @param g
     * @return
     */
    public int insert(GroupPerson g){
        return groupPersonDao.insert(g);
    }

    /**
     * 删除
     * @param g
     * @return
     */
    public int delete(GroupPerson g){
        String id = g.getId();
        return groupPersonDao.delete(id);
    }

    /**
     * 通过id查询
     * @param id
     * @return
     */
    public GroupPerson findById(String id){
        GroupPerson groupPersons =(GroupPerson) groupPersonDao.findById(id);
        if (groupPersons!=null ) {
            return groupPersons;
        }
        return null;
    }
}
