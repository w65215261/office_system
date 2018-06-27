package com.pmcc.soft.core.organization.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pmcc.soft.core.organization.dao.DictDao;
import com.pmcc.soft.core.organization.domain.Dict;
import com.pmcc.soft.core.utils.UUIDGenerator;

@Transactional
@Service
public class DictManager {
	
	@Autowired
	private DictDao dao;
	
			// 添加用户
			public void addDict(Dict dict) {
				if (dict.getId() != null && !dict.getId().equals("")) {
					dict.setId(dict.getId());
				} else {
					dict.setId(UUIDGenerator.getUUID());
				} 
				dao.insert(dict);
			}
		
			// 根据id查找用户
			public Dict findDict(Dict dict) {
				Dict d= dao.findDict(dict);
				
				return d;
			}
			
			// 获取所有用户列表
			public List<Dict> query(Dict dict) {
				return dao.query(dict);
			}
			
			//获取字典表树
			public List<Dict> queryParent(String parendId) {
				return dao.queryParent(parendId);
			}
			
			// 修改用户信息
			public void editDict(Dict dict) {
				dao.update(dict);
			}
			
			// 删除用户
			public int delete(Dict dict) {
				int res = 0;
				res += dao.delete(dict);
				return res;
			}
			
			public List<Dict> queryCommbo(String type) {
				return dao.queryCommbo(type);
			}

			public List<Dict> queryCommboByParentId(String parentId) {
				return dao.queryCommboByParentId(parentId);
			}
	// 根据名称查找
	public Dict findDictByName(String dictName) {
		return  dao.findDictByName(dictName);
	}
}
