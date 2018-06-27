package com.pmcc.soft.week.dao;

import com.pmcc.soft.week.domain.SystemAttachmentFolderRela;
import com.pmcc.soft.week.domain.SystemFolder;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 文件夹与附件关系表
 * Created by LvXL on 2016/5/9.
 */
@Repository
public class SystemAttachmentFolderRelaDao extends SqlSessionDaoSupport {

    public static final String NAME_SPACE = "com/pmcc/soft/week/SystemAttachmentFolderRelaMapper";

    public List<SystemAttachmentFolderRela> query(SystemAttachmentFolderRela safr){
        return this.getSqlSession().selectList(NAME_SPACE + ".query", safr);
    }

    public int insert(SystemAttachmentFolderRela safr){
        return this.getSqlSession().insert(NAME_SPACE + ".insert", safr);
    }

    public int update(SystemAttachmentFolderRela rela) {
        return this.getSqlSession().update(NAME_SPACE + ".update", rela);
    }

}
