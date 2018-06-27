package com.pmcc.soft.week.manager;

import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.dao.SystemAuditPersonConfigDao;
import com.pmcc.soft.week.dao.TaskAttachmentDao;
import com.pmcc.soft.week.domain.SystemAuditPersonConfig;
import com.pmcc.soft.week.domain.TaskAttachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZhangYanChang on 2016/4/27.
 */
@Transactional
@Service
public class SystemAuditPersonConfigManager {

    @Autowired
    SystemAuditPersonConfigDao systemAuditPersonConfigDao;

    public int save(SystemAuditPersonConfig param) {
        param.setId(UUIDGenerator.getUUID());
        return systemAuditPersonConfigDao.insert(param);
    }

    public String findConfigPerson(String businessData, String groupCode) {
        return systemAuditPersonConfigDao.findConfigPerson( businessData, groupCode);
    }

    public List<SystemAuditPersonConfig> queryAuditPerson(String businessData) {
        return systemAuditPersonConfigDao.queryAuditPerson(businessData);
    }

}
