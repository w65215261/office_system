package com.pmcc.soft.week.dao;

import com.pmcc.soft.week.domain.DiySelectDisplay;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by wangbin on 2016/4/11.
 */
@Repository
public class DiySelectDisplayDao extends SqlSessionDaoSupport {
    public static final String NAME_SPACE = "com/pmcc/soft/week/DiySelectDisplayMapper";

    public List<DiySelectDisplay> queryByControlId(DiySelectDisplay diySelectDisplay){
        return this.getSqlSession().selectList(NAME_SPACE + ".queryByControlId", diySelectDisplay);
    }

    /**
     * 手机端获取下拉框
     * @author LvXL
     * @param dsd
     * @return
     */
    public List<DiySelectDisplay> queryCombo(DiySelectDisplay dsd){
        return this.getSqlSession().selectList(NAME_SPACE + ".queryCombo", dsd);
    }
}
