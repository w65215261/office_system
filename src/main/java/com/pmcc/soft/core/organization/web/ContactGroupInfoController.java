package com.pmcc.soft.core.organization.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pmcc.soft.core.organization.domain.ContactGroupInfo;
import com.pmcc.soft.core.organization.manager.ContactGroupInfoManager;

@Controller
@RequestMapping("contactGroupInfo")
public class ContactGroupInfoController {
	
	@Autowired
	ContactGroupInfoManager contactGroupInfoManager;
	
	/**
	 * 获取通讯录分组列表
	 * @param ei
	 * @return
	 */
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	@ResponseBody
	public List<ContactGroupInfo> query(ContactGroupInfo cgi){
		List<ContactGroupInfo> contactGroupInfos = contactGroupInfoManager.query(cgi);
		return contactGroupInfos;
	}
	
	/**
	 * 保存或修改
	 * @param ei
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	public String saveOrUpdate(ContactGroupInfo cgi){
		contactGroupInfoManager.saveOrUpdate(cgi);
		return "../../addressList/addressList";
	}
	
	/**
	 * 删除
	 * @param ei
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(ContactGroupInfo cgi){
		contactGroupInfoManager.delete(cgi);
		return "../../addressList/addressList";
	}
	
	/**
	 * 加载一条分组信息
	 * @param model
	 * @param ei
	 * @return
	 */
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	@ResponseBody
	public ContactGroupInfo find(Model model, ContactGroupInfo cgi) {

		return contactGroupInfoManager.find(cgi);
	}

}
