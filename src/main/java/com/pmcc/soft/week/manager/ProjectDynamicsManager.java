package com.pmcc.soft.week.manager;

import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.dao.ProjectDynamicsDao;
import com.pmcc.soft.week.domain.ProjectDynamics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by asus on 2015/10/22.
 */
@Transactional
@Service
public class ProjectDynamicsManager {
    @Autowired
    private ProjectDynamicsDao projectDynamicsDao;

    public List<ProjectDynamics> findById(ProjectDynamics pd){
        return  projectDynamicsDao.findById(pd);
    }

    public int insert(ProjectDynamics pd){
        pd.setId(UUIDGenerator.getUUID());
        return projectDynamicsDao.insert(pd);
    }

    public int delete(ProjectDynamics pd){
        return projectDynamicsDao.delete(pd);
    }
}
