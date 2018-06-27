package com.pmcc.soft.core.organization.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pmcc.soft.core.organization.domain.Dict;
import com.pmcc.soft.core.organization.manager.DictManager;

@Controller
@RequestMapping("dict")
public class DictController {

	@Autowired
	DictManager dictManager;
	
	/*
	 * 查询所有
	 */
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> query(Dict dict) {
		Map<String, Object> res = new HashMap<String, Object>();
		dict.setInitPage(0);
		res.put("rows", dictManager.query(dict));
		dict.setInitPage(1);
		res.put("total", dictManager.query(dict).size());
		
		return res;
	}
	
	
	/**
	 * 查询，配置用
	 * 2014-10-27 sunyongxing
	 * @param dict
	 * @return
	 */
	@RequestMapping(value = "/queryObject", method = RequestMethod.GET)
	@ResponseBody
	public Dict queryObject(Dict dict) {
		List<Dict> dList = dictManager.query(dict);
		return dList.get(0);
	}
	
	
	@RequestMapping(value = "/getParentAll", method = RequestMethod.POST)
	@ResponseBody
	public List<Dict> getParentAll(HttpServletRequest request, String id) {
		List<Dict> blist =new ArrayList<Dict>();
		if (id == null) {
			id = "-1";
		}
		List<Dict> clist = dictManager.queryParent(id);
		for (Dict it : clist) {
			it.setId(it.getId());
			it.setText(it.getDictName());
			it.setState("closed");
			blist.add(it);
		}
		return blist;
	}
	
	
	@RequestMapping(value = "/queryCommbo", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String,String>> queryCommbo(String type) {
		List<Map<String,String>> result =new ArrayList<Map<String,String>>();
		List<Dict> list =dictManager.queryCommbo(type);
		Map<String,String> amap=null;
		for (Dict dict : list) {
			amap = new HashMap<String,String>();
			amap.put("id", dict.getId());
			amap.put("text", dict.getDictName());
			result.add(amap);
		}
		return result;
	}
	
	
	@RequestMapping(value = "/queryCommboByParentId", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String,String>> queryCommboByParent(String parentId){
		List<Map<String,String>> result =new ArrayList<Map<String,String>>();
		List<Dict> list =dictManager.queryCommboByParentId(parentId);
		Map<String,String> amap=null;
		for (Dict dict : list) {
			amap = new HashMap<String,String>();
			amap.put("id", dict.getId());
			amap.put("text", dict.getDictName());
			result.add(amap);
		}
		return result;
	}
	@RequestMapping(value = "/queryByParentId", method = RequestMethod.GET)
	@ResponseBody
	public List<Dict> queryByParentId(String parentId){
		List<Dict> list =dictManager.queryCommboByParentId(parentId);
		return list;
	}
	
	/*
	 * 根据id查询
	 */
	@RequestMapping(value = "/findDict", method = RequestMethod.GET)
	@ResponseBody
	public Dict findDict(Dict dict) {
		Dict d = dictManager.findDict(dict);
		Dict di = new Dict();
		di.setId(d.getParentId());
		List<Dict> dictList = dictManager.query(di);
		if (dictList.size()>0) {
			di = dictList.get(0);
			d.setParentName(di.getDictName());
		}
		return d;
	}
	
	/*
	 * 新增
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String addDict(Dict dict) {
		dictManager.addDict(dict);
		return "dict/dict";
		
	}
	/*
	 * 修改
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String editDict(Dict dict) {
		dictManager.editDict(dict);
		return "dict/dict";
	}
	
	/**
	 * 批量删除
	 * 2014-10-16 sunyongxing修改
	 * @param dict
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@ResponseBody
	public String delete(Dict dict,HttpServletRequest request) {
		int res = 1;
		String[] ids = request.getParameter("param").split(",");
		for (int i = 0; i < ids.length; i++) {
			dict.setId(ids[i]);
			res += dictManager.delete(dict);
		}
		if (res == ids.length) {
			return "success";
		}else{
			return "fail";
		}
	}
	
	/**
	 * 显示菜单
	 * @return
	 */
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String show() {
		return "dict/dict";
	}
	
	/**
	 * 跳转到新增界面
	 * @return
	 */
	@RequestMapping(value = "/toAddDict", method = RequestMethod.GET)
	public String toSaveUI() {
		return "dict/dictAdd";
	}
	/**
	 * 跳转到修改界面
	 * @return
	 */
	@RequestMapping(value = "/toUpdateDict", method = RequestMethod.GET)
	public String toUpdateUI() {
		return "dict/dictUpdate";
	}
	
	/**
	 * 校验所填id是否存在
	 * 
	 * 2014-10-29 sunyongxing
	 * @param dict
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/checkId", method = RequestMethod.POST)
	@ResponseBody
	public String  checkId(Dict dict,HttpServletRequest request) {
		//Map<String, Object> res = new HashMap<String, Object>();
		String inputId = request.getParameter("inputId");//接收前台输入的id
		dict.setId(inputId);
		List<Dict> dList = dictManager.query(dict);
		if (dList.size() != 0) {
			return "fail";
		}
		return "success";
	}
}
