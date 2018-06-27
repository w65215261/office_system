package com.pmcc.soft.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.pmcc.soft.core.common.Blowfish;
import com.pmcc.soft.core.common.View;
import com.pmcc.soft.core.organization.domain.*;
import com.pmcc.soft.core.organization.manager.OrganPersonRelationManager;
import com.pmcc.soft.core.organization.manager.OrganizationInfoManager;
import com.pmcc.soft.core.organization.manager.PersonInfoManageManager;
import com.pmcc.soft.core.organization.manager.PersonManageManager;
import com.pmcc.soft.core.utils.CommonUtils;
import com.pmcc.soft.core.utils.DateUtil;
import com.pmcc.soft.core.utils.EasemobUtils;
import com.pmcc.soft.core.utils.SystemParamsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.*;

/**
 * Created by wangbin on 2016/4/11.
 */

@Controller
@RequestMapping("personInfo")
public class PersonInfoRestController {

    @Autowired
    private PersonInfoManageManager personInfoManageManager;

    @Autowired
    PersonManageManager personManageManager;

    @Autowired
    OrganPersonRelationManager organPersonRelationManager;

    @Autowired
    OrganizationInfoManager organizationInfoManager;

    @JsonView(View.RestView.class)
    @RequestMapping(value = "/queryPerson", method = RequestMethod.POST)
    @ResponseBody
    public List<PersonInfoManage> queryPerson(HttpServletRequest request) {
        PersonInfoManage personInfoManage = new PersonInfoManage();
        personInfoManage.setId(request.getParameter("personId"));
        PersonInfoManage pm = personInfoManageManager.queryByOid(personInfoManage);
        personInfoManage.setCompanyId(pm.getCompanyId());
        // 去除分页
        personInfoManage.setInitPage(1);
        List<PersonInfoManage> list = personInfoManageManager.query(personInfoManage);
        return list;
    }

    /**
     * 获取人员信息(包含人员组织机构)
     *
     * @param personManage
     * @param request
     * @return
     */
    @JsonView(View.RestView.class)
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public PersonInfoManage get(PersonInfoManage personManage, HttpServletRequest request) {

        PersonInfoManage pm = personInfoManageManager.queryByOid(personManage);
        // 去除电话空格
        pm.setTelephone(pm.getTelephone() == null ? "" : pm.getTelephone().replaceAll("\\s*", ""));
        List<OrganizationInfo> orgList = organizationInfoManager.queryOrgByPersonId(pm.getId());
        if (orgList != null && orgList.size() > 0) {
            OrganizationInfo org = orgList.get(0);
            pm.setPersonUintId(org.getId());
            pm.setPersonUnitname(org.getOrgCname());
        }
        return pm;
    }

    /**
     * IOS模糊查询人员列表
     *
     * @param request
     * @return
     */
    @JsonView(View.RestView.class)
    @RequestMapping(value = "/queryByName", method = RequestMethod.GET)
    @ResponseBody
    public List<PersonInfoManage> queryByName(HttpServletRequest request) {

        String name = request.getParameter("name");
        name = CommonUtils.toEncoding(name);
        PersonInfoManage personInfoManage = new PersonInfoManage();
        personInfoManage.setUserCname(name);
        personInfoManage.setUserEname(name);
        // 去除分页
        personInfoManage.setInitPage(1);
        List<PersonInfoManage> list = personInfoManageManager.queryByName(personInfoManage);
        return list;
    }

    /**
     * 根据用户英文名查询人员id
     *
     * @param userName 登录人用户名
     * @param password 登录人密码
     * @param eName    英文名
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public String query(String userName, String password, String eName) {

        String res = "";
        PersonManage person = new PersonManage();
        person.setUserEname(userName);
        PersonManage loginUser = personManageManager.find(person);
        if (loginUser != null) {
            String passwordKey = SystemParamsUtil.getSysConfig().get("passwordKey").toString();
            //加密
            Blowfish bf = new Blowfish(passwordKey);
            String decrypt = bf.decrypt(loginUser.getMd5Pwd());
            if (decrypt.equals(password.trim())) {
                PersonInfoManage personInfoManage = new PersonInfoManage();
                personInfoManage.setUserEname(eName);
                // 去除分页
                personInfoManage.setInitPage(1);
                List<PersonInfoManage> list = personInfoManageManager.query(personInfoManage);
                if (list != null && list.size() > 0) {
                    res = list.get(0).getId();
                }
            }
        }
        return res;
    }

    /**
     * 移动端修改个人基本信息接口
     * 昵称  nickName
     * 性别  userSex
     * 生日  userBrothday
     * 地区  address
     * 手机号  telephone
     *
     * @param person
     * @param request
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public String update(PersonInfoManage person, HttpServletRequest request) {
        String json = "{\"success\":\"false\"}";
        String birthday = request.getParameter("birthday");
        if(birthday != null && !"".equals(birthday)){
            Date date = DateUtil.StringToDateTwo(birthday, "yyyy-MM-dd");
            person.setUserBrothday(date);
        }
        int res = personInfoManageManager.updateApp(person);
        if (res > 0) {
            json = "{\"success\":\"true\"}";
        }
        return json;
    }

    /**
     * 移动端修改个人密码接口，同时修改环信密码
     * 修改密码 md5Pwd
     *
     * @param person
     * @param request
     * @return
     */
    @RequestMapping(value = "updatePwd", method = RequestMethod.POST)
    @ResponseBody
    public String updatePwd(PersonInfoManage person, HttpServletRequest request) throws Exception {
        String json = "{\"success\":\"false\"}";
        int res = 0;
        String md5Pwd = person.getMd5Pwd();
        if(md5Pwd != null && !"".equals(md5Pwd)){
            String passwordKey = SystemParamsUtil.getSysConfig().get("passwordKey").toString();
            //加密，修改项目存放密码
            Blowfish bf = new Blowfish(passwordKey);
            String encrypt = bf.encrypt(md5Pwd);
            person.setMd5Pwd(encrypt);
            res = personInfoManageManager.updateApp(person);
            // 修改环信密码
            EasemobUtils.updatePwd(person.getUserEname(), encrypt);
        }
        if (res > 0) {
            json = "{\"success\":\"true\"}";
        }
        return json;
    }

    public static void main(String[] args) {

        String passwordKey = SystemParamsUtil.getProperty("passwordKey");
        Blowfish bf = new Blowfish(passwordKey);
        String decrypt = bf.encrypt("123123");
        System.out.println(decrypt);
    }

}
