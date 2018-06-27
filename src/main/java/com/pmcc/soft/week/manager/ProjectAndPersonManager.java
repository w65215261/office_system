package com.pmcc.soft.week.manager;

import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.dao.ProjectPersonDao;
import com.pmcc.soft.week.domain.Project;
import com.pmcc.soft.week.domain.ProjectPersonRela;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by asus on 2015/11/26.
 */
@Transactional
@Service
public class ProjectAndPersonManager {

    @Autowired
    ProjectPersonDao projectPersonDao;


    public int insert(ProjectPersonRela ppr){
        ppr.setOid(UUIDGenerator.getUUID());
        return projectPersonDao.insert(ppr);
    }

    public int delete(ProjectPersonRela ppr){
        return projectPersonDao.delete(ppr);
    }

    public List<ProjectPersonRela> findByProjectOid(ProjectPersonRela ppr) {
        return projectPersonDao.findByProjectOid(ppr);

    }

    public ProjectPersonRela findByOid(ProjectPersonRela ppr) {
        return projectPersonDao.findByOid(ppr);

    }
    public List<ProjectPersonRela> findByPersonOid(ProjectPersonRela ppr) {
        return projectPersonDao.findByPersonOid(ppr);
    }

    /**
     * 查找登录人参与的正在进行中的项目
     * @param projectPersonRela
     * @return
     */
    public List<ProjectPersonRela>queryProjectPersonRela(ProjectPersonRela projectPersonRela){
        return  projectPersonDao.queryProjectPersonRela(projectPersonRela);
    }

    /**
     * 查找登录人参与的已经完成的项目
     * @param projectPersonRela
     * @return
     */
    public List<ProjectPersonRela>queryProjectPersonRelaStatus(ProjectPersonRela projectPersonRela){
        return  projectPersonDao.queryProjectPersonRelaStatus(projectPersonRela);
    }
}
