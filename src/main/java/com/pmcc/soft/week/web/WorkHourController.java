package com.pmcc.soft.week.web;

import com.pmcc.soft.core.common.OnlineUser;
import com.pmcc.soft.core.organization.domain.OrganPersonRelation;
import com.pmcc.soft.core.organization.domain.PersonInfo;
import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.organization.manager.PersonInfoManager;
import com.pmcc.soft.core.utils.AppUtils;
import com.pmcc.soft.core.utils.DateUtil;
import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.domain.WorkHour;
import com.pmcc.soft.week.manager.WorkHourManager;
import com.pmcc.soft.ydpt.manager.JpushManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.Timestamp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sunyake on 15/7/8.
 */

@Controller
@RequestMapping("workHour")
public class WorkHourController {


    @Autowired
    WorkHourManager workHourManager;
    @Autowired
    PersonInfoManager  personInfoManager;

    @Autowired
    JpushManager jpushManager;

    // public static  final String FINISH="通过";
    //public static final String FAIL = "不通过";
    // public static final String NO_FINISH = "未填报";
    public  static  final int DAY=60;

    /**
     * 显示菜单
     * @return
     */
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String show() {
        return "workHour/workHour";
    }

    @RequestMapping(value = "/showSelect", method = RequestMethod.GET)
    public String showSelect() {
        return "workHour/workHourSelect";
    }

    /**
     * 显示菜单
     * @return
     */
    @RequestMapping(value = "/showSearch", method = RequestMethod.GET)
    public String showSearch()
    {
        return "workHour/workHourSearch";
    }



    @RequestMapping(value = "/queryAll",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> queryAll(WorkHour workHour,HttpServletRequest request){

        String sEcho = request.getParameter("sEcho");
        workHour.setPersonId(null);

        List<WorkHour> workHours = workHourManager.query(workHour);

        for(WorkHour  wh:workHours){
            PersonInfo  personInfo = new PersonInfo();
            personInfo.setId(wh.getPersonId());
            wh.setPersonId(personInfoManager.findPerson(personInfo).getUserName());

        }

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("sEcho",sEcho);
        workHour.setInitPage(1);
        map.put("iTotalRecords",workHourManager.query(workHour).size());
        map.put("iTotalDisplayRecords",workHourManager.query(workHour).size());
        map.put("aaData", workHours);
        return map;

    }

    @RequestMapping(value = "/query",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> query(WorkHour workHour,HttpServletRequest request,HttpSession session){

        String sEcho = request.getParameter("sEcho");


        String personId=workHour.getPersonId();
        if(personId==null||personId.equals("")){
            PersonManage user = (PersonManage)session.getAttribute("loginUser");
            String userId =user.getId();
            workHour.setPersonId(userId);
        }
        List<WorkHour> workHours = workHourManager.query(workHour);
        for(WorkHour  wh:workHours){
            PersonInfo  personInfo = new PersonInfo();
            personInfo.setId(wh.getPersonId());
            //wh.setPersonId(personInfoManager.findPerson(personInfo).getUserName());

        }

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("sEcho",sEcho);
        workHour.setInitPage(1);
        map.put("iTotalRecords",workHourManager.query(workHour).size());
        map.put("iTotalDisplayRecords",workHourManager.query(workHour).size());
        map.put("aaData", workHours);
        return map;

    }


    @RequestMapping(value = "/findById",method = RequestMethod.GET)
    @ResponseBody
    public WorkHour findById(WorkHour workHour,HttpServletRequest request){



        workHour = workHourManager.findById(workHour.getId());
        return workHour;


    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String  saveOrUpdate(WorkHour mr,HttpServletResponse response,HttpServletRequest request,HttpSession session) throws IOException {

        if(mr.getId()==null||"".equals(mr.getId())){
            PersonManage user = (PersonManage)session.getAttribute("loginUser");
            String userId = user.getId();
            mr.setPersonId(userId);
            mr.setCreateTime(new Date());
            mr.setDelFlag("0");
            mr.setBelongsDate(new Date());
            int res = 0;
            int i=workHourManager.insert(mr);
            if(res==i){
                return "fail";
            }else {
                return "success";
            }
        }else{

            int i= workHourManager.update(mr);
            if(i==1){
                return "success";
            }else{
                return "fail";
            }
        }

    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public String delete(WorkHour workHour,HttpServletRequest request) {
        int res = 0;
        String[] ids = request.getParameter("ids").split(",");
        for (int i = 0; i < ids.length; i++) {
            workHour.setId(ids[i]);
            res += workHourManager.delete(ids[i]);
        }
        if(res == ids.length){
            return "success";
        }else{
            return "fail";
        }
    }

    @RequestMapping(value = "/pushMessage", method = RequestMethod.GET)
    @ResponseBody
    public String pushMessage(HttpServletRequest request) {

        jpushManager.sendAllPush();






        return "success";
    }

    /**
     * 计算工时
     * @param workHour
     * @param response
     */
    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public void count(WorkHour workHour,HttpServletResponse response,HttpServletRequest request,HttpSession session) {
        //判断删除标识，如果前台传入参数等于2，则是人员填报页面
        String userId="";
        if(workHour.getDelFlag().equals("2")){
            PersonManage user = (PersonManage)session.getAttribute("loginUser");
            userId = user.getId();
        }else{
            userId=workHour.getPersonId();//如果不是2  就是查询界面，根据传递过来的personid查
        }

        Date date=new Date();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        String color="[";
        for(int i=0;i<=DAY;i++){
            Date newDate=DateUtil.addDay(date,-i);
            int m= workHourManager.queryByDate(userId,newDate);
            int year= DateUtil.getYear(new Timestamp(newDate.getTime()));
            int month= DateUtil.getMonth(new Timestamp(newDate.getTime()))-1;
            int day= DateUtil.getDay(new Timestamp(newDate.getTime()));

            //m等于0，工时合格
            if(m==0){
                if(i==DAY){
                    color+="{allDay:true, start:new Date("+year+","+month+","+day+"),className:[\"event\", \"bg-color-greenLight\"]}";
                }else{
                    color+="{ allDay:true,start:new Date("+year+","+month+","+day+"),className:[\"event\", \"bg-color-greenLight\"]},";
                }
            }//m等于1，工时不合格
            else if(m==1){
                if(i==DAY){
                    color+="{ allDay:true,start:new Date("+year+","+month+","+day+"),className:[\"event\", \"bg-color-yellow\"]}";
                }else{
                    color+="{ allDay:true,start:new Date("+year+","+month+","+day+"),className:[\"event\", \"bg-color-yellow\"]},";
                }

            }//m等于2，工时未填报
            else if(m==2){
                if(i==DAY){
                    color+="{ allDay:true,start:new Date("+year+","+month+","+day+"),className:[\"event\", \"bg-color-red\"]}";
                }else{
                    color+="{ allDay:true,start:new Date("+year+","+month+","+day+"),className:[\"event\", \"bg-color-red\"]},";
                }
            }
        }
        // System.out.println("两天前的日期：" + df.format(new Date(d.getTime() - 2 * 24 * 60 * 60 * 1000)));
        //计算当前登录人的工时
        color +="]";
        try {
            response.getWriter().write(color);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        df.setLenient(false);
        CustomDateEditor editor = new CustomDateEditor(df, true);
        binder.registerCustomEditor(Date.class, editor);
    }



}
