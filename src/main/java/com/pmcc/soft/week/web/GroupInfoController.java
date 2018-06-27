package com.pmcc.soft.week.web;

import com.pmcc.soft.core.common.OnlineUser;
import com.pmcc.soft.core.utils.AppUtils;
import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.domain.GroupInfo;
import com.pmcc.soft.week.manager.GroupInfoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by sunyongxing
 * on 2015/7/20 0020 17:15
 */
@Controller
@RequestMapping("groupInfo")
public class GroupInfoController {

    @Autowired
    GroupInfoManager groupInfoManager;

    /**
     * 查询所有
     * @param g
     * @return
     */
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    @ResponseBody
    public List<GroupInfo> query(GroupInfo g){
        return groupInfoManager.query(g);
    }

    /**
     * 新增或修改
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public void saveOrUpdate(GroupInfo g,HttpServletRequest request){
        String sessionId = WebUtils.getSessionId(request);
        OnlineUser onlineUser = AppUtils.getOnlineUser(sessionId);
        g.setId(UUIDGenerator.getUUID());
        g.setRptPerson(onlineUser.getUserId());
        g.setRptDate(new Date());
        groupInfoManager.insert(g);
    }

    /**
     * 删除
     * @param g
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public void delete(GroupInfo g){
        groupInfoManager.delete(g);
    }

    /**
     * 通过id查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/findById",method = RequestMethod.GET)
    @ResponseBody
    public GroupInfo findById(String id){
        return groupInfoManager.findById(id);
    }
}
