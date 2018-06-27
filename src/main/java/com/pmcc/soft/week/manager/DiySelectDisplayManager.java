package com.pmcc.soft.week.manager;

import com.pmcc.soft.week.dao.DiySelectDisplayDao;
import com.pmcc.soft.week.domain.DiySelectDisplay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 自定义下拉框
 * Created by wangbin on 2016/4/25.
 */
@Transactional
@Service
public class DiySelectDisplayManager {
    @Autowired
    private DiySelectDisplayDao diySelectDisplayDao;

    public List<DiySelectDisplay> queryByControlId(DiySelectDisplay diySelectDisplay) {
        return diySelectDisplayDao.queryByControlId(diySelectDisplay);
    }

    /**
     * 手机端获取下拉框
     * @author LvXL
     * @param templateCode
     * @param controlType
     * @return
     */
    public List<DiySelectDisplay> queryCombo(String templateCode, String controlType, String optionIndex){

        DiySelectDisplay diySelectDisplay = new DiySelectDisplay();
        diySelectDisplay.setControlType(controlType);
        diySelectDisplay.setTemplateCode(templateCode);
        diySelectDisplay.setControlIndex(optionIndex);

        List<DiySelectDisplay> list = diySelectDisplayDao.queryCombo(diySelectDisplay);
        return list;
    }

}
