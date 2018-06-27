package com.pmcc.soft.week.manager;

import com.pmcc.soft.week.dao.CityIdDao;
import com.pmcc.soft.week.dao.CityNumDao;
import com.pmcc.soft.week.domain.CityNum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by asus on 2015/10/26.
 */
@Transactional
@Service
public class CityNumManager {
    @Autowired
    private CityNumDao cityNumDao;


    public CityNum findCityId(String cityName){
        return cityNumDao.findCityId(cityName);
    }
}


