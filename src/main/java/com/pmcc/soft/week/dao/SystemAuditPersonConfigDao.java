package com.pmcc.soft.week.dao;

import com.pmcc.soft.week.domain.SystemAttachment;
import com.pmcc.soft.week.domain.SystemAuditPersonConfig;
import com.pmcc.soft.week.domain.TaskAttachment;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZhangYanChang on 2016/4/27.
 */
@Repository
public class SystemAuditPersonConfigDao extends SqlSessionDaoSupport {
    public static final String NAME_SPACE = "com/pmcc/soft/week/SystemAuditPersonConfigMapper";

    /**
     * 保存数据
     *
     * @param param
     * @return
     */
    public int insert(SystemAuditPersonConfig param) {
        int res = 0;
        res = this.getSqlSession().insert(NAME_SPACE + ".insert", param);
        return res;
    }

    /**
     * 获取配置人
     *
     * @param businessData
     * @param groupCode
     * @return
     */
    public String findConfigPerson( String businessData, String groupCode) {

        Map<String, String> param = new HashMap<>();
        param.put("businessData", businessData);
        param.put("groupCode", groupCode);
        List<SystemAuditPersonConfig> res = this.getSqlSession().selectList(NAME_SPACE + ".findConfig", param);
        if (res != null && res.size() > 0) {
            return res.get(0).getAuditPerson();
        }
        return null;
    }



    /**
     * 根据业务主键查询审批人
     * @param businessData
     * @return
     */
    public List<SystemAuditPersonConfig> queryAuditPerson(String businessData){
        SystemAuditPersonConfig spc = new SystemAuditPersonConfig();
        spc.setBusinessData(businessData);
        return this.getSqlSession().selectList(NAME_SPACE + ".query", spc);
    }

}
