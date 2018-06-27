package com.pmcc.soft.ydpt.web;

import com.pmcc.soft.core.organization.domain.Dict;
import com.pmcc.soft.ydpt.domain.OfMucRoom;
import com.pmcc.soft.ydpt.domain.OfUser;
import com.pmcc.soft.ydpt.manager.OfMucRoomManager;
import com.pmcc.soft.ydpt.manager.OfUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZhanYanChang on 2015/6/8.
 */
@RequestMapping("ofUser")
@Controller
public class OfUserController {

    @Autowired
    OfUserManager ofUserManager;

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> query(String username) throws UnsupportedEncodingException {
        String name = username;
        OfUser param = new OfUser();
        if (name != null && !"".equals(name)){
            name = new String(name.getBytes("ISO-8859-1"),"UTF-8");
            param.setUsername(name);
        }

        Map<String, Object> res = new HashMap<String, Object>();
        param.setInitPage(0);
        res.put("rows", ofUserManager.query(param));
        param.setInitPage(1);
        res.put("total", ofUserManager.query(param).size());
        return res;
    }

}
