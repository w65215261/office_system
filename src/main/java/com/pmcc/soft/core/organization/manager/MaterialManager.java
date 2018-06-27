package com.pmcc.soft.core.organization.manager;

import java.util.List;

import com.pmcc.soft.core.organization.dao.MaterialDao;
import com.pmcc.soft.core.organization.domain.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Transactional
@Service
public class MaterialManager {

	@Autowired
	private MaterialDao dao;


	// 根据id查找用户
	public Material findMaterial(Material material) {
		Material m= dao.findMaterial(material);

		return m;
	}

	// 获取所有用户列表
	public List<Material> query(Material material) {
		return dao.query(material);
	}

	public List<Material> queryCommbo(String type) {
		return dao.queryCommbo(type);
	}

	public List<Material> queryCommboByParentId(String parentId) {
		return dao.queryCommboByParentId(parentId);
	}

}
