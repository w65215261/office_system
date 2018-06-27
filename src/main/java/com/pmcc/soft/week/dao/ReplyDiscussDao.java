package com.pmcc.soft.week.dao;

import com.pmcc.soft.week.domain.ReplyDiscuss;
import org.springframework.stereotype.Repository;

/**
 * Created by sunyongxing
 * on 2015/7/20 0020 17:44
 */
@Repository
public class ReplyDiscussDao extends BaseDao<ReplyDiscuss> {

    public static final String NAME_SPACE = "com/pmcc/soft/week/replyDiscussMapper";

    public ReplyDiscussDao() {
        super(NAME_SPACE);
    }
}
