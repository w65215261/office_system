package com.pmcc.soft.week.dao;

import com.pmcc.soft.week.domain.GroupDiscuss;
import org.springframework.stereotype.Repository;

/**
 * Created by yaonan on 15/7/17.
 */
@Repository
public class GroupDiscussDao extends BaseDao<GroupDiscuss> {

    public static final String NAME_SPACE = "com/pmcc/soft/week/groupDiscussMapper";

   public GroupDiscussDao(){
       super(NAME_SPACE);
   }
    public GroupDiscuss queryNew(GroupDiscuss groupDiscuss){
        return getSqlSession().selectOne(nameSpace + ".queryNew", groupDiscuss);
    }
}
