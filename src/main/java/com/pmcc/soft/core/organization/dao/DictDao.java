package com.pmcc.soft.core.organization.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.pmcc.soft.core.organization.domain.Dict;

@Repository
public class DictDao extends SqlSessionDaoSupport{

	public static final String NAME_SPACE = "com/pmcc/soft/core/organization/dictMapper";

	//查询所有
	public List<Dict> query(Dict dict) {
		return getSqlSession().selectList(NAME_SPACE + ".query",dict);
	}

	//查询字典表父节点树
	public List<Dict> queryParent(String parendId) {
		return getSqlSession().selectList(NAME_SPACE + ".queryParent",parendId);
	}

	//根据id查询
	public Dict findDict(Dict object) {
		String id = object.getId();
		return  getSqlSession().selectOne(NAME_SPACE + ".findById",id);
	}

	//增加
	public void insert(Dict object) {
		getSqlSession().insert(NAME_SPACE + ".insert", object);
	}

	//删除
	public int delete(Dict object) {
		int res = 0;
		String  id = object.getId();
		res = getSqlSession().delete(NAME_SPACE + ".delete", id);
		return res;
	}

	//更新
	public void update(Dict object) {
		getSqlSession().update(NAME_SPACE + ".update", object);
	}

	public List<Dict> queryCommbo(String type) {
		return getSqlSession().selectList(NAME_SPACE + ".queryCommbo",type);
	}

	public List<Dict> queryCommboByParentId(String parentId) {

		return getSqlSession().selectList(NAME_SPACE + ".queryCommboByParentId",parentId);
	}
	//根据名称查询
	public Dict findDictByName(String dictName) {
		return  getSqlSession().selectOne(NAME_SPACE + ".findDictByName",dictName);
	}



}
