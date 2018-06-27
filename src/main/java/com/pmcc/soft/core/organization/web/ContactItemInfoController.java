package com.pmcc.soft.core.organization.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pmcc.soft.core.organization.domain.ContactItemInfo;
import com.pmcc.soft.core.organization.manager.ContactItemInfoManager;

@Controller
@RequestMapping("contactItemInfo")
public class ContactItemInfoController {
	
	@Autowired
	ContactItemInfoManager contactItemInfoManager;
	
	/**
	 * 获取通讯录列表
	 * @param ei
	 * @return
	 */
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	@ResponseBody
	public List<ContactItemInfo> query(ContactItemInfo cii){
		List<ContactItemInfo> contactItemInfos = contactItemInfoManager.query(cii);
		return contactItemInfos;
	}
	
	/**
	 * 保存或修改
	 * @param ei
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	public String saveOrUpdate(ContactItemInfo cii){
		contactItemInfoManager.saveOrUpdate(cii);
		return "../../addressList/addressList";
	}
	
	/**
	 * 删除
	 * @param ei
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(ContactItemInfo cii){
		contactItemInfoManager.delete(cii);
		return "../../addressList/addressList";
	}
	
	/**
	 * 加载一条通讯信息
	 * @param model
	 * @param ei
	 * @return
	 */
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	@ResponseBody
	public ContactItemInfo find(Model model, ContactItemInfo cii) {

		return contactItemInfoManager.find(cii);
	}

}
