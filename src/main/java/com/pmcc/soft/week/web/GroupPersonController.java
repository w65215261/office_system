package com.pmcc.soft.week.web;

import com.pmcc.soft.core.common.OnlineUser;
import com.pmcc.soft.core.utils.AppUtils;
import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.domain.GroupPerson;
import com.pmcc.soft.week.manager.GroupPersonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by sunyongxing
 * on 2015/7/20 0020 17:29
 */
@Controller
@RequestMapping("groupPerson")
public class GroupPersonController {

    @Autowired
    GroupPersonManager groupPersonManager;


}
