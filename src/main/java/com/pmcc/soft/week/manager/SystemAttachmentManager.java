package com.pmcc.soft.week.manager;

import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.dao.SummarizeReportDao;
import com.pmcc.soft.week.dao.SystemAttachmentDao;
import com.pmcc.soft.week.domain.Report;
import com.pmcc.soft.week.domain.SystemAttachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by wangbin on 2016/4/11.
 */
@Transactional
@Service
public class SystemAttachmentManager {
    @Autowired
    private SystemAttachmentDao systemAttachmentDao;

    public int insert(SystemAttachment systemAttachment){
        return systemAttachmentDao.insert(systemAttachment);
    }
    public int deleteById(SystemAttachment systemAttachment){
        return systemAttachmentDao.deleteById(systemAttachment);
    }

    public int update(SystemAttachment systemAttachment){
        return systemAttachmentDao.update(systemAttachment);
    }

    public List<SystemAttachment> queryByReportId(SystemAttachment systemAttachment){
        return systemAttachmentDao.queryByReportId(systemAttachment);
    }

    public List<SystemAttachment> queryByBusinessData(SystemAttachment systemAttachment){
        return systemAttachmentDao.queryByBusinessData(systemAttachment);
    }

    public List<SystemAttachment> query(SystemAttachment systemAttachment){
        return systemAttachmentDao.query(systemAttachment);
    }

    public SystemAttachment queryById(SystemAttachment systemAttachment){
        return systemAttachmentDao.queryById(systemAttachment);
    }
    public int updateDownloadCount(SystemAttachment systemAttachment){
        return systemAttachmentDao.updateDownloadCount(systemAttachment);
    }

    public int updateByTempBusinessData(SystemAttachment systemAttachment){
        return systemAttachmentDao.updateByTempBusinessData(systemAttachment);
    }

    /**
     * 查询文件夹中的文件
     * @author LvXL
     * @return
     */
    public List<SystemAttachment> queryFile(SystemAttachment systemAttachment){
        return systemAttachmentDao.queryFile(systemAttachment);
    }

}
