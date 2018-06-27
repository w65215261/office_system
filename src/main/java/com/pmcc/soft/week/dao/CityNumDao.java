package com.pmcc.soft.week.dao;

import com.pmcc.soft.week.domain.CityNum;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * Created by asus on 2015/10/26.
 */
@Repository
public class CityNumDao extends SqlSessionDaoSupport {
    public static final String NAME_SPACE = "com/pmcc/soft/week/CityIdMapper";


    /**
     * @param cityName
     * @return
     */
    public CityNum findCityId(String cityName){
        return this.getSqlSession().selectOne(NAME_SPACE + ".findCityId", cityName);
    }


}
