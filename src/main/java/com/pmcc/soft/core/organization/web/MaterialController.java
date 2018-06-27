package com.pmcc.soft.core.organization.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.pmcc.soft.core.organization.domain.Material;
import com.pmcc.soft.core.organization.manager.MaterialManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("material")
public class MaterialController {

	@Autowired
	MaterialManager materialManager;

	/*
	 * 查询所有
	 */
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> query(Material material) {
		Map<String, Object> res = new HashMap<String, Object>();
		material.setInitPage(0);
		res.put("rows", materialManager.query(material));
		material.setInitPage(1);
		res.put("total", materialManager.query(material).size());
		return res;
	}




	@RequestMapping(value = "/queryCommbo", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String,String>> queryCommbo(String type) {
		List<Map<String,String>> result =new ArrayList<Map<String,String>>();
		List<Material> list =materialManager.queryCommbo(type);
		Map<String,String> amap=null;
		for (Material material : list) {
			amap = new HashMap<String,String>();
			amap.put("id", material.getId());
			amap.put("text", material.getMaterialid());
			result.add(amap);
		}
		return result;
	}

	@RequestMapping(value = "/queryCommboByParentId", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String,String>> queryCommboByParent(String parentId){
		List<Map<String,String>> result =new ArrayList<Map<String,String>>();
		List<Material> list =materialManager.queryCommboByParentId(parentId);
		Map<String,String> amap=null;
		for (Material material : list) {
			amap = new HashMap<String,String>();
			amap.put("id", material.getId());
			amap.put("text", material.getMaterialid());
			result.add(amap);
		}
		return result;
	}

	/*
	 * 根据id查询
	 */
	@RequestMapping(value = "/findMaterial", method = RequestMethod.GET)
	@ResponseBody
	public Material findMaterial(Material material) {
		Material m = materialManager.findMaterial(material);
		Material mi = new Material();

		mi.setId(m.getId());
		List<Material> materialList = materialManager.query(mi);
		if (materialList.size()>0) {
			mi = materialList.get(0);
			m.setMaterialid(mi.getMaterialid());
		}
		return m;
	}

	/**
	 * 显示菜单
	 * @return
	 */
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String show() {
		return "MaterialManagement/material";
	}



}
