package com.pmcc.soft.core.organization.dao;

import java.util.List;

import com.pmcc.soft.core.organization.domain.Material;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;



@Repository
public class MaterialDao extends SqlSessionDaoSupport{

    public static final String NAME_SPACE = "com/pmcc/soft/core/organization/materialMapper";

    //查询所有
    public List<Material> query(Material material) {
        return getSqlSession().selectList(NAME_SPACE + ".query",material);
    }


    //根据id查询
    public Material findMaterial(Material object) {
        String id = object.getId();
        return  getSqlSession().selectOne(NAME_SPACE + ".findById",id);
    }



    public List<Material> queryCommbo(String type) {
        return getSqlSession().selectList(NAME_SPACE + ".queryCommbo",type);
    }

    public List<Material> queryCommboByParentId(String parentId) {

        return getSqlSession().selectList(NAME_SPACE + ".queryCommboByParentId",parentId);
    }




}
