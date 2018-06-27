package com.pmcc.soft.week.manager;

import com.pmcc.soft.week.dao.SummarizeReportDao;
import com.pmcc.soft.week.dao.TempleateControlDao;
import com.pmcc.soft.week.domain.Report;
import com.pmcc.soft.week.domain.TempleateControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by wangbin on 2016/4/11.
 */
@Transactional
@Service
public class TempleateControlManager {
    @Autowired
    private TempleateControlDao templeateControlDao;

    public TempleateControl queryControlId(TempleateControl templeateControl) {
        return templeateControlDao.queryControlId(templeateControl);
    }

    /**
     * 根据业务类型编码查询控件，排序
     * @author LvXL
     * @param templateCode
     * @return
     */
    public List<TempleateControl> queryTempleateControl(String templateCode){

        TempleateControl tc = new TempleateControl();
        tc.setTemplateCode(templateCode);
        return templeateControlDao.queryTempleateControl(tc);
    }

}
