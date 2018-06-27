package com.pmcc.soft.rest;

import com.pmcc.soft.core.common.Blowfish;
import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.organization.manager.PersonManageManager;
import com.pmcc.soft.core.utils.CommonUtils;
import com.pmcc.soft.core.utils.EncryptMD5;
import com.pmcc.soft.core.utils.SystemParamsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sunyake on 15/10/27.
 */

@RestController
@RequestMapping(value = "login")
public class loginRestController {
    @Autowired
    PersonManageManager personManageManager;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(HttpServletRequest request, String userName, String passWord) {
        Map<String, Object> model = new HashMap<String, Object>();

        if ("".equals(CommonUtils.convertNull(userName))) {
            model.put("loginFlag", "0");
            return model;
        }
        if ("".equals(CommonUtils.convertNull(passWord))) {
            model.put("loginFlag", "0");
            return model;
        }
        PersonManage person = new PersonManage();
        person.setUserEname(userName);
        PersonManage loginUser = personManageManager.find(person);
        if (loginUser == null) {
            model.put("loginFlag", "0");
            return model;
        }

        String passwordKey = SystemParamsUtil.getSysConfig().get("passwordKey").toString();
        //加密
        Blowfish bf = new Blowfish(passwordKey);
        String decrypt = bf.decrypt(loginUser.getMd5Pwd());
        if (decrypt.equals(passWord.trim())) {
            //登录权限判断
            model.put("loginFlag", "1");
            model.put("personId", loginUser.getId());
            model.put("personName",loginUser.getUserCname());
            model.put("password",loginUser.getMd5Pwd());
            return model;
        } else {
            model.put("loginMsg", "0");
            return model;
        }
    }

}
