package com.pmcc.soft.week.dao;

import com.pmcc.soft.week.domain.GroupPerson;
import org.springframework.stereotype.Repository;

/**
 * Created by sunyongxing
 * on 2015/7/20 0020 17:24
 */
@Repository
public class GroupPersonDao extends BaseDao<GroupPerson> {

    public static final String NAME_SPACE = "com/pmcc/soft/week/groupInfoMapper";

    public GroupPersonDao() {
        super(NAME_SPACE);
    }
}
