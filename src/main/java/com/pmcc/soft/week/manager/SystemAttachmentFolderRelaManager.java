package com.pmcc.soft.week.manager;

import com.pmcc.soft.week.dao.SystemAttachmentFolderRelaDao;
import com.pmcc.soft.week.domain.SystemAttachmentFolderRela;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 文件夹与附件关系表
 * Created by LvXL on 2016/5/9.
 */
@Transactional
@Service
public class SystemAttachmentFolderRelaManager {

    @Autowired
    SystemAttachmentFolderRelaDao systemAttachmentFolderRelaDao;

    public int save(SystemAttachmentFolderRela rela){

        return systemAttachmentFolderRelaDao.insert(rela);
    }

    public int update(SystemAttachmentFolderRela rela){

        return systemAttachmentFolderRelaDao.update(rela);
    }

    public SystemAttachmentFolderRela get(SystemAttachmentFolderRela rela){

        List<SystemAttachmentFolderRela> list = systemAttachmentFolderRelaDao.query(rela);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}
