package com.pmcc.soft.week.manager;

import com.pmcc.soft.week.dao.CityIdDao;
import com.pmcc.soft.week.dao.PlanDao;
import com.pmcc.soft.week.domain.CityNum;
import com.pmcc.soft.week.domain.ProjectTask;
import com.pmcc.soft.week.domain.TreeTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2015/10/26.
 */
@Transactional
@Service
public class CityIdManager {
    @Autowired
    private CityIdDao cityIdDao;


    public CityNum findCityId(String cityName){
        return cityIdDao.findCityId(cityName);
    }
}


