package com.pmcc.soft.week.web;

import com.pmcc.soft.core.common.OnlineUser;
import com.pmcc.soft.core.utils.AppUtils;
import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.domain.ReplyDiscuss;
import com.pmcc.soft.week.manager.ReplyDiscussManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by sunyongxing
 * on 2015/7/20 0020 17:54
 */
@Service
@Transactional
public class ReplyDiscussController {

    @Autowired
    ReplyDiscussManager replyDiscussManager;

    /**
     * 查询所有
     * @param r
     * @return
     */
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    @ResponseBody
    public List<ReplyDiscuss> query(ReplyDiscuss r){
        return replyDiscussManager.query(r);
    }

    /**
     * 新增或修改
     */
    @RequestMapping(value = "/query",method = RequestMethod.POST)
    public void saveOrUpdate(ReplyDiscuss r,HttpServletRequest request){
        String sessionId = WebUtils.getSessionId(request);
        OnlineUser onlineUser = AppUtils.getOnlineUser(sessionId);
        r.setId(UUIDGenerator.getUUID());
        replyDiscussManager.insert(r);
    }

    /**
     * 删除
     * @param r
     */
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public void delete(ReplyDiscuss r){
        replyDiscussManager.delete(r);
    }

    /**
     * 通过id查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    @ResponseBody
    public ReplyDiscuss findById(String id){
        return replyDiscussManager.findById(id);
    }

}
