package com.pmcc.soft.week.dao;

import com.pmcc.soft.week.domain.Report;
import com.pmcc.soft.week.domain.SystemAttachment;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by wangbin on 2016/4/11.
 */
@Repository
public class SystemAttachmentDao extends SqlSessionDaoSupport {
    public static final String NAME_SPACE = "com/pmcc/soft/week/SystemAttachmentMapper";

    public int insert(SystemAttachment systemAttachment) {
        int res = 0;
        res = this.getSqlSession().insert(NAME_SPACE + ".insert", systemAttachment);
        return res;
    }
    public int deleteById(SystemAttachment systemAttachment) {
        int res = 0;
        res = this.getSqlSession().insert(NAME_SPACE + ".deleteById", systemAttachment);
        return res;
    }

    public int update(SystemAttachment systemAttachment) {
        return this.getSqlSession().update(NAME_SPACE + ".update", systemAttachment);
    }

    public List<SystemAttachment> queryByReportId(SystemAttachment systemAttachment) {
        return this.getSqlSession().selectList(NAME_SPACE + ".queryByReportId", systemAttachment);
    }

    public List<SystemAttachment> queryByBusinessData(SystemAttachment systemAttachment) {
        return this.getSqlSession().selectList(NAME_SPACE + ".queryByBusinessData", systemAttachment);
    }

    public List<SystemAttachment> query(SystemAttachment systemAttachment) {
        return this.getSqlSession().selectList(NAME_SPACE + ".query", systemAttachment);
    }

    public SystemAttachment queryById(SystemAttachment systemAttachment) {
        return this.getSqlSession().selectOne(NAME_SPACE + ".queryById", systemAttachment);
    }

    public int updateDownloadCount(SystemAttachment systemAttachment) {
        int res = 0;
        res = this.getSqlSession().update(NAME_SPACE + ".updateDownloadCount", systemAttachment);
        return res;
    }

    public int updateByTempBusinessData(SystemAttachment systemAttachment){
        int res = 0;
        res = this.getSqlSession().update(NAME_SPACE + ".updateByTempBusinessData", systemAttachment);
        return res;
    }

    /**
     * 查询文件夹中的文件
     * @author LvXL
     * @param att
     * @return
     */
    public List<SystemAttachment> queryFile(SystemAttachment att) {
        return this.getSqlSession().selectList(NAME_SPACE + ".queryFile", att);
    }
}
