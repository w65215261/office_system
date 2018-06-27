package com.pmcc.soft.week.dao;

import com.pmcc.soft.week.domain.Report;
import com.pmcc.soft.week.domain.TempleateControl;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by wangbin on 2016/4/11.
 */
@Repository
public class TempleateControlDao extends SqlSessionDaoSupport {
    public static final String NAME_SPACE = "com/pmcc/soft/week/TempleateControlMapper";

    public TempleateControl queryControlId(TempleateControl templeateControl){
        return this.getSqlSession().selectOne(NAME_SPACE+".queryControlId",templeateControl);
    }

    /**
     * 根据业务类型编码查询控件，排序
     * @author LvXL
     * @param templeateControl
     * @return
     */
    public List<TempleateControl> queryTempleateControl(TempleateControl templeateControl){

        return this.getSqlSession().selectList(NAME_SPACE+".query",templeateControl);
    }
}
