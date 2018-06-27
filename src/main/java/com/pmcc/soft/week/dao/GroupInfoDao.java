package com.pmcc.soft.week.dao;

import com.pmcc.soft.week.domain.GroupInfo;
import org.springframework.stereotype.Repository;

/**
 * Created by sunyongxing
 * on 2015/7/20 0020 16:46
 */
@Repository
public class GroupInfoDao extends BaseDao<GroupInfo> {

    public static final String NAME_SPACE = "com/pmcc/soft/week/groupInfoMapper";

    public GroupInfoDao() {
        super(NAME_SPACE);
    }
}
