package com.pmcc.soft.week.web;

import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.organization.manager.AuthorityUserInfoManager;
import com.pmcc.soft.core.organization.manager.PersonManageManager;
import com.pmcc.soft.core.utils.CommonUtils;
import com.pmcc.soft.core.utils.EncryptMD5;
import com.pmcc.soft.week.domain.WorkHour;
import com.pmcc.soft.week.manager.WorkHourManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by sunyake on 15/7/24.
 */

@RestController
@RequestMapping("restWorkHour")
public class WorkHourRestController {


    @Autowired
    WorkHourManager workHourManager;

    @Autowired
    PersonManageManager personManageManager;

    @Autowired
    AuthorityUserInfoManager authorityUserInfoManager;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
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

        String md5pwd = EncryptMD5.getMD5(passWord.trim().getBytes());
        if (loginUser.getMd5Pwd().equals(md5pwd)) {

            model.put("loginFlag", "1");
            model.put("personId", loginUser.getId());


            return model;
        } else {
            model.put("loginMsg", "0");
            return model;
        }


    }


    /**
     * 查询
     *
     * @param workHour
     * @return
     */

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public List<WorkHour> query(WorkHour workHour) {
//       Date date=new   Date();//取时间
//        Calendar   calendar   =   new GregorianCalendar();
//        calendar.setTime(date);
//        calendar.add(calendar.DATE,-4);//把日期往后增加一天.整数往后推,负数往前移动
//        date=calendar.getTime();
//        workHour.setBelongsDate(date);
        List<WorkHour> workHourList = workHourManager.query(workHour);
        // JSONArray json = JSONArray.fromObject(workHourList);
        return workHourList;
    }

    /**
     * 插入新数据
     *
     * @param workHour
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(WorkHour workHour) {
        //不知道什么原因，传过来的时间都多了12个小时，所以要减少12个时辰

        workHour.setStartTime(new Date(workHour.getStart()));
        workHour.setEndTime(new Date(workHour.getEnd()));
        workHour.setCreateTime(new Date());

        workHourManager.insert(workHour);
        return "success";
    }

    /**
     * 更新
     *
     * @param workHour
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(WorkHour workHour) {
        //不知道什么原因，传过来的时间都多了12个小时，所以要减少12个时辰

        workHour.setStartTime(new Date(workHour.getStart()));
        workHour.setEndTime(new Date(workHour.getEnd()));
        workHour.setCreateTime(new Date());
        workHourManager.update(workHour);
        return "success";
    }

    /**
     * 删除
     *
     * @param workHour
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(WorkHour workHour) {
        workHourManager.delete(workHour.getId());
        return "success";
    }


    @InitBinder
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
        CustomDateEditor editor = new CustomDateEditor(df, true);
        binder.registerCustomEditor(Date.class, editor);
    }

}
