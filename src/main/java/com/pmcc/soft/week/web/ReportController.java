package com.pmcc.soft.week.web;

import com.pmcc.soft.core.organization.domain.OrganPersonRelation;
import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.organization.manager.OrganPersonRelationManager;
import com.pmcc.soft.core.organization.manager.OrganizationInfoManager;
import com.pmcc.soft.core.organization.manager.PersonInfoManager;
import com.pmcc.soft.core.utils.DateUtil;
import com.pmcc.soft.week.domain.Report;
import com.pmcc.soft.week.manager.ReportManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by H on 2015/11/16.
 */
@Controller
@RequestMapping("report")
public class ReportController {

    @Autowired
    ReportManager reportManager;
    @Autowired
    PersonInfoManager personInfoManager;
    @Autowired
    OrganPersonRelationManager organPersonRelationManager;
    @Autowired
    OrganizationInfoManager organizationInfoManager;
    /**
     * 侧边年月
     * @param report
     * @return
     */
    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public ModelAndView report(Report report) {
        ModelAndView ma = new ModelAndView("/report/report");
        String[] s = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};//前台页面日报侧边月份显示
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        ma.addObject("month", s);
        ma.addObject("year", year);
        return ma;
    }

    /**
     *  点击侧边月份刷新日历到相应年月份
     * @param report
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/refreshCalendar", method = RequestMethod.GET)
    public ModelAndView refreshCalendar(Report report, HttpServletRequest request, HttpSession session) {
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String date = year + "-" + month + "-1";
            ModelAndView mc = new ModelAndView("/report/dayReport");
            PersonManage user = (PersonManage) session.getAttribute("loginUser");
            String userId = user.getId();
            report.setRtpPersonId(userId);
            Date d = null;
            try {
                d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            } catch (ParseException e) {
                e.printStackTrace();

            }
            String sDate = new SimpleDateFormat("yyyy-MM-dd").format(d);
            String sDate1 = new SimpleDateFormat("yyyy年MM月dd日").format(d);
            report.setBelongsDate(d);
            report.setType("0");
            //查询判断日报填写界面还是展示界面
            List<Report> reports = reportManager.query(report);
            if (reports.size() > 0) {
                mc.addObject("list", reports);
                mc.addObject("cDate", sDate);
                return mc;
            } else {
                mc.addObject("nullList",reports.size());//空list判断前台页面样式
                mc.addObject("cDate", sDate);
                mc.addObject("nowTime", sDate1);
                return mc;
            }
    }

    /**
     * 查询判断展示或者填写
     * @param report
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/queryFor", method = RequestMethod.GET)
    public ModelAndView show(Report report, HttpServletRequest request, HttpSession session) {
        ModelAndView ma = new ModelAndView("/report/dayReport");
        PersonManage user = (PersonManage) session.getAttribute("loginUser");
        String userId = user.getId();
        report.setRtpPersonId(userId);
        String type = request.getParameter("type");
        report.setType(type);
        Date newDate = DateUtil.getNewDate();
        String nowTime=new SimpleDateFormat("yyyy年MM月dd日").format(newDate);
        report.setRptDate(newDate);
        report.setBelongsDate(newDate);
        //判断每天第一次是进入日报填写界面还是展示界面
        List<Report> reports = reportManager.queryShow(report);
        if (reports.size() > 0) {
            ma.addObject("list", reports);
            return ma;
        } else {
            ma.addObject("nullList",reports.size());
            ma.addObject("nowTime",nowTime);
            return ma;
        }
    }

    /**
     * 点击日历查询数据,已写日报展示,未写局部刷新页面
     * @param report
     * @param session
     * @return
     */
    @RequestMapping(value = "/queryForDay", method = RequestMethod.GET)
    @ResponseBody
    public List showOne(Report report, HttpSession session,HttpServletRequest request) {
        PersonManage user = (PersonManage) session.getAttribute("loginUser");
        String userId = user.getId();
        report.setRtpPersonId(userId);
        //点击日历日期查询数据
        List<Report> reports = reportManager.queryCalendar(report);
        if (reports.size() > 0) {
            return reports;
        } else {
            return null;
        }
    }

    /**
     * 点击日历组件到前后月时刷新当月第一天数据
     * @param report
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/queryForMonth", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> showMonth(Report report, HttpSession session,HttpServletRequest request) {
        Map<String,Object> ma = new HashMap<String, Object>();
        PersonManage user = (PersonManage) session.getAttribute("loginUser");
        String userId = user.getId();
        String sign=request.getParameter("sign");
        String belongsDate=request.getParameter("belongsDate");
        Date date=null;
        try {
            date=new SimpleDateFormat("yyyy-MM-dd").parse(belongsDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar ca=Calendar.getInstance();
        ca.setTime(date);
        if (sign.equals("0")){
            ca.add(Calendar.MONTH,-1);
        }else if (sign.equals("1")){
            ca.add(Calendar.MONTH,+1);
        }
        ca.set(Calendar.DAY_OF_MONTH,1);
        Date belongsDates=ca.getTime();
        String sDate=new SimpleDateFormat("yyyy-MM-dd").format(ca.getTime());
        String sDate1=new SimpleDateFormat("yyyy年MM月dd日").format(ca.getTime());
        report.setBelongsDate(belongsDates);
        report.setRtpPersonId(userId);
        List<Report> reports = reportManager.queryCalendar(report);  //点击日历日期查询数据
//        List list=new ArrayList();
//        list.add(reports);
        ma.put("list", reports);
        ma.put("belongsDate", sDate);
        ma.put("sDate1",sDate1);
        return ma;
    }

    /**
     * 查询保存数据,直接局部刷新
     * @param report
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public List query(Report report, HttpServletRequest request, HttpSession session) {
        PersonManage user = (PersonManage) session.getAttribute("loginUser");
        String userId = user.getId();
        report.setRtpPersonId(userId);
        //根据登录人id和所属时间查询
        List<Report> list = reportManager.query(report);
        return list;
    }

    /**
     * 添加日报
     * @param report
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(Report report, HttpServletRequest request, HttpSession session) {
        PersonManage user = (PersonManage) session.getAttribute("loginUser");
        String userId = user.getId();
        List<OrganPersonRelation> orgList=organPersonRelationManager.findByPersonId(userId);
        report.setOrgId(orgList.get(0).getOrganizationInfoId());
        organizationInfoManager.queryOrgByOrgId(orgList.get(0).getOrganizationInfoId()).getOrgCode();
        report.setOrgCode(organizationInfoManager.queryOrgByOrgId(orgList.get(0).getOrganizationInfoId()).getOrgCode());
        String username = user.getUserCname();
        String belongsDate=request.getParameter("belongsDate");
        Calendar cal = Calendar.getInstance();
        Date weekDate=null;
        try {
            weekDate=new SimpleDateFormat("yyyy-MM-dd").parse(belongsDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.setTime(weekDate);
        cal.setFirstDayOfWeek(Calendar.MONDAY);//周一
        cal.setMinimalDaysInFirstWeek(7);//7天
        int week=cal.get(Calendar.DAY_OF_WEEK)-1;//星期几
        report.setWeek(week);
        report.setRtpPersonId(userId);
        report.setRptPersonName(username);
        Date date = new Date();
        String sdate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        if (belongsDate.equals(sdate)) {
            report.setStatus("0");
        } else {
            report.setStatus("1");
        }
        int res = 0;
        //插入填报数据
        int i = reportManager.insert(report);
        if (res == i) {
            return "fail";
        } else {
            return "success";
        }
    }

    /**
     * 修改填报数据
     * @param report
     * @param request
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String update(Report report, HttpServletRequest request) {

        int res = 0;
        //修改方法
        int i = reportManager.update(report);
        if (res == i) {
            return "fail";
        } else {
            return "success";
        }
    }

    /**
     * 侧拉遮罩层查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    @ResponseBody
    public Report findById(String id) {
        //根据填报id查询
        Report reports = reportManager.findById(id);
        return reports;
    }

    public static final int DAY = 65;//定义颜色加载天数
    /**
     * 日历显示背景颜色
     * @param session
     * @param request
     * @param response
     */
    @RequestMapping(value = "/color", method = RequestMethod.POST)
    @ResponseBody
    public void colorCalendar(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        PersonManage user = (PersonManage) session.getAttribute("loginUser");
        String userId = user.getId();
        Date date = new Date();
        String color = "[";
        for (int i = 0; i <= DAY; i++) {
            Date newDate = DateUtil.addDays(date, -i);
            //通过id,时间查询数据判断m的值
            int m = reportManager.queryByDate(userId, newDate);
            int year = DateUtil.getYear(new Timestamp(newDate.getTime()));
            int month = DateUtil.getMonth(new Timestamp(newDate.getTime())) - 1;
            int day = DateUtil.getDay(new Timestamp(newDate.getTime()));

            //m等于0，日报按时提交
            if (m == 0) {
                if (i == DAY) {
                    color += "{allDay:true, start:new Date(" + year + "," + month + "," + day + "),className:[\"event\", \"bg-color-greenLight\"]}";
                } else {
                    color += "{ allDay:true,start:new Date(" + year + "," + month + "," + day + "),className:[\"event\", \"bg-color-greenLight\"]},";
                }
            }//m等于1，日报逾期提交
            else if (m == 1) {
                if (i == DAY) {
                    color += "{ allDay:true,start:new Date(" + year + "," + month + "," + day + "),className:[\"event\", \"bg-color-#87cefa\"]}";
                } else {
                    color += "{ allDay:true,start:new Date(" + year + "," + month + "," + day + "),className:[\"event\", \"bg-color-#87cefa\"]},";
                }

            }//m等于2，为填报
            else if (m == 2) {
                if (i == DAY) {
                    color += "{ allDay:true,start:new Date(" + year + "," + month + "," + day + "),className:[\"event\", \"bg-color-red\"]}";
                } else {
                    color += "{ allDay:true,start:new Date(" + year + "," + month + "," + day + "),className:[\"event\", \"bg-color-red\"]},";
                }
            }
        }
        color += "]";
        try {
            response.getWriter().write(color);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 周报查询判断展示或者填写
     * @param report
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/showWeek", method = RequestMethod.GET)
    public ModelAndView showWeek(Report report, HttpServletRequest request, HttpSession session) {
        ModelAndView ma = new ModelAndView("/report/reportWeek");
        Date newDate=new Date();
        Calendar cal=Calendar.getInstance();
        cal.setTime(newDate);
        cal.setFirstDayOfWeek(Calendar.MONDAY);//设置起止日为周1
        cal.setMinimalDaysInFirstWeek(7);//设置第一周够几天才能算第一周
        //得到当前有几周
        int weekCount = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);//总周数
        int weekNum=cal.get(Calendar.WEEK_OF_MONTH);//当月第几周
        String nowTime=new SimpleDateFormat("yyyy-MM").format(newDate);
        String times=nowTime+"-0"+weekNum;
        SimpleDateFormat format = new SimpleDateFormat("MM/dd");
        Calendar ca=Calendar.getInstance();
        ca.set(Calendar.DATE, 1);
        int month = ca.get(Calendar.MONTH);
        int count = 0;
        List monSum=new ArrayList();//每月所有周集合
        while (ca.get(Calendar.MONTH) == month) {
            if (ca.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                StringBuilder builder = new StringBuilder();
                builder.append("第");
                builder.append(++count+"周&nbsp;&nbsp;&nbsp;&nbsp;");
                builder.append(format.format(ca.getTime()));
                builder.append(" - ");
                ca.add(Calendar.DATE, 6);
                builder.append(format.format(ca.getTime()));
                monSum.add(builder);
            }
            ca.add(Calendar.DATE, 1);
        }
        Date weekForDate=new Date();
        String weekDate=new SimpleDateFormat("yyyy年MM月").format(weekForDate);
        PersonManage user = (PersonManage) session.getAttribute("loginUser");
        String userId = user.getId();
        String type = request.getParameter("type");
        report.setRtpPersonId(userId);
        report.setType(type);
        report.setWeekDate(weekDate);
        report.setWeekNum(weekNum);
        //判断周报填写界面还是展示界面
        List<Report> reports = reportManager.findByWeek(report);
        if (reports.size() > 0) {
            ma.addObject("weekList", reports);
            ma.addObject("weekNum",weekNum);
            ma.addObject("title",weekForDate);
            ma.addObject("ms",monSum);
            ma.addObject("weekCount",weekCount);
            ma.addObject("nowWeek",times);
            return ma;
        } else {
            ma.addObject("nullWeekList",reports.size());//list集合为空,参数为0
            ma.addObject("weekNum",weekNum);//第几周
            ma.addObject("title",weekForDate);//日历表头
            ma.addObject("ms",monSum);
            ma.addObject("weekCount",weekCount);
            ma.addObject("nowWeek",times);
            return ma;
        }
    }

    /**
     * 添加周报
     * @param report
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/saveWeek", method = RequestMethod.POST)
    @ResponseBody
    public String saveWeek(Report report, HttpServletRequest request, HttpSession session) {
        PersonManage user = (PersonManage) session.getAttribute("loginUser");
        String userId = user.getId();
        String username = user.getUserCname();
        report.setRtpPersonId(userId);
        report.setRptPersonName(username);
        String weekTime=request.getParameter("weekTime");
        Date dates=new Date();
        String sDate=new SimpleDateFormat("yyyy-MM").format(dates);
        String weekNum=request.getParameter("weekNum");
        int s=Integer.parseInt(weekNum);
        Calendar cal=Calendar.getInstance();
        cal.setTime(dates);
        cal.setFirstDayOfWeek(Calendar.MONDAY);//设置起止日为周1
        cal.setMinimalDaysInFirstWeek(7);
        int weekNums=cal.get(Calendar.WEEK_OF_MONTH);//当月第几周
        if (s==weekNums&&sDate.equals(weekTime)) {
            report.setStatus("0");
        } else {
            report.setStatus("1");
        }
        int res = 0;
        //插入填报数据
        int i = reportManager.insert(report);
        if (res == i) {
            return "fail";
        } else {
            return "success";
        }
    }

    /**
     * 保存周报之后查询刷新,点击每周查询
     * @param report
     * @param session
     * @return
     */
    @RequestMapping(value = "/queryWeek", method = RequestMethod.POST)
    @ResponseBody
    public List queryWeek(Report report,HttpSession session) {
        PersonManage user = (PersonManage) session.getAttribute("loginUser");
        String userId = user.getId();
        report.setRtpPersonId(userId);
        //根据登录人id和所属时间查询
        List<Report> list = reportManager.findByWeek(report);
        if (list.size() > 0) {
            return list;
        } else {
            return null;
        }
    }

    /**
     * 周视图跳转时加载每月第一周数据
     * @param report
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/nextMonth",method =RequestMethod.POST)
    @ResponseBody
    public  Map<String,Object>  nextMonth(Report report,HttpServletRequest request,HttpSession session){
        Map<String,Object> ma = new HashMap<String, Object>();
        PersonManage user = (PersonManage) session.getAttribute("loginUser");
        String userId = user.getId();
        String weekDate=request.getParameter("nowTime");
        String sign=request.getParameter("sign");
        Date date=null;
        try {
             date=new SimpleDateFormat("yyyy-MM").parse(weekDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar ca=Calendar.getInstance();
        ca.setTime(date);
        if (sign.equals("1")){
            ca.add(Calendar.MONTH, -1);
        }else if(sign.equals("0")){
            ca.add(Calendar.MONTH, +1);
        }
        Date resultDate=ca.getTime();
        String weekDates=new SimpleDateFormat("yyyy年MM月").format(resultDate);
        String weekDate1=new    SimpleDateFormat("yyyy-MM").format(resultDate);
        report.setWeekDate(weekDates);
        report.setRtpPersonId(userId);
        List<Report> list=reportManager.findByWeek(report);
        ma.put("list",list);
        ma.put("weekDate",weekDate1);
        ma.put("weekDates",weekDates);
        return ma;
    }

    /**
     * 日历周视图
     * @param request
     * @return
     */
    @RequestMapping(value = "/showWeekView",method = RequestMethod.POST)
    @ResponseBody
    public Map showWeekView( HttpServletRequest request){
        HashMap map=new HashMap();
        String time=request.getParameter("nowTime");
        String sign=request.getParameter("sign");
        Date date=null;
        try {
            date=new SimpleDateFormat("yyyy-MM").parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat format = new SimpleDateFormat("MM/dd");
        Calendar ca=Calendar.getInstance();
        ca.setTime(date);
        String i="0";
        if(sign.equals(i)){
            ca.add(Calendar.MONTH, +1);
        }else{
            ca.add(Calendar.MONTH, -1);
        }
        Date resultDate = ca.getTime();
        ca.setTime(resultDate);
        ca.set(Calendar.DATE, 1);
        int month = ca.get(Calendar.MONTH);
        int count = 0;
        List monSum=new ArrayList();
        while (ca.get(Calendar.MONTH) == month) {
            if (ca.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                StringBuilder builder = new StringBuilder();
                builder.append("第");
                builder.append(++count+"周&nbsp;&nbsp;&nbsp;&nbsp;");
                builder.append(format.format(ca.getTime()));
                builder.append(" - ");
                ca.add(Calendar.DATE, 6);
                builder.append(format.format(ca.getTime()));
                monSum.add(builder);
            }
            ca.add(Calendar.DATE, 1);
        }
        map.put("allWeek",monSum);
        map.put("title",resultDate);
        return map;
    }

    /**
     * 给周视图添加颜色
     * @param report
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/showWeekViewColor",method =RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> showWeekViewColor(Report report,HttpServletRequest request,HttpSession session){
        Map<String,Object> map = new HashMap<String, Object>();
        PersonManage user = (PersonManage) session.getAttribute("loginUser");
        String userId = user.getId();
        String time=request.getParameter("nowTime");
        String sign=request.getParameter("sign");
        Date date=null;
        try {
            date=new SimpleDateFormat("yyyy-MM").parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar ca=Calendar.getInstance();
        ca.setFirstDayOfWeek(Calendar.MONDAY);//设置起止日为周1
        ca.setMinimalDaysInFirstWeek(7);//设置第一周够几天才能算第一周
        int weekNum=ca.get(Calendar.WEEK_OF_MONTH);
        ca.setTime(date);
//        String i="0";
        if(sign.equals("0")){
            ca.add(Calendar.MONTH, +1);
        }else if (sign.equals("1")){
            ca.add(Calendar.MONTH, -1);
        }else if (sign.equals("2")){
            ca.add(Calendar.MONTH,0);
        }
        Date resultDate = ca.getTime();
        String weekDate = new SimpleDateFormat("yyyy年MM月").format(resultDate);
        ca.setTime(resultDate);
        ca.setFirstDayOfWeek(Calendar.MONDAY);//设置起止日为周1
        ca.setMinimalDaysInFirstWeek(7);//设置第一周够几天才能算第一周
        int weekCount = ca.getActualMaximum(Calendar.WEEK_OF_MONTH);
        List sList=new ArrayList();
        for(int w=1;w<=weekCount;w++){
            report.setRtpPersonId(userId);
            report.setType("1");
            report.setWeekDate(weekDate);
            report.setWeekNum(w);
            List<Report> list=reportManager.findByWeek(report);
            if (list.size()>0){
                String status=null;
                for (int f=0;f<list.size();f++){
                    String status1=list.get(f).getStatus();
                    status=status1;
                }
                sList.add(status);
            }else{
                sList.add(2);
            }
        }
        map.put("list",sList);
        map.put("weekNum",weekNum);
        map.put("weekDate",weekDate);
        return map;
    }

    /**
     * 点击侧边年月刷新周报页面
     * @param report
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/refreshCalendarWeek", method = RequestMethod.GET)
    public ModelAndView refreshCalendarWeek(Report report, HttpServletRequest request, HttpSession session) {
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        Date dates=new Date();
        String nowDate=new SimpleDateFormat("yyyy-MM").format(dates);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(dates);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);//设置起止日为周1
        calendar.setMinimalDaysInFirstWeek(7);//设置第一周够几天才能算第一周
        int weekNum=calendar.get(Calendar.WEEK_OF_MONTH);//当月第几周
        String times=nowDate+"-0"+weekNum;
        String date = year + "-" + month + "-1";
            ModelAndView ma = new ModelAndView("/report/reportWeek");
            Date newDate=null;
            try {
                newDate=new  SimpleDateFormat("yyyy-MM").parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar cal=Calendar.getInstance();
            cal.setTime(newDate);
            cal.setFirstDayOfWeek(Calendar.MONDAY);//设置起止日为周1
            cal.setMinimalDaysInFirstWeek(7);//设置第一周够几天才能算第一周
            //得到当前有几周
            int weekCount = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);
            SimpleDateFormat format = new SimpleDateFormat("MM/dd");
            Calendar ca=Calendar.getInstance();
            ca.setTime(newDate);
            ca.set(Calendar.DATE, 1);
            int months = ca.get(Calendar.MONTH);
            int count = 0;
            List monSum=new ArrayList();
            while (ca.get(Calendar.MONTH) == months) {
                if (ca.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                    StringBuilder builder = new StringBuilder();
                    builder.append("第");
                    builder.append(++count+"周&nbsp;&nbsp;&nbsp;&nbsp;");
                    builder.append(format.format(ca.getTime()));
                    builder.append(" - ");
                    ca.add(Calendar.DATE, 6);
                    builder.append(format.format(ca.getTime()));
                    monSum.add(builder);
                }
                ca.add(Calendar.DATE, 1);
            }
            String weekDate=new SimpleDateFormat("yyyy年MM月").format(newDate);//
            String weekNums="1";
            PersonManage user = (PersonManage) session.getAttribute("loginUser");
            String userId = user.getId();
            report.setRtpPersonId(userId);
            report.setType("1");
            report.setWeekDate(weekDate);
            report.setWeekNum(1);
            //判断周报填写界面还是展示界面
            List<Report> reports = reportManager.findByWeek(report);
            if (reports.size() > 0) {
                ma.addObject("weekList", reports);
                ma.addObject("weekNum",weekNums);
                ma.addObject("title",newDate);
                ma.addObject("ms",monSum);
                ma.addObject("weekCount",weekCount);
                ma.addObject("nowWeek",times);
                return ma;
            } else {
                ma.addObject("nullWeekList",reports.size());//list集合为空,参数为0
                ma.addObject("weekNum",weekNums);//第几周
                ma.addObject("title",newDate);//日历表头
                ma.addObject("ms",monSum);
                ma.addObject("weekCount",weekCount);
                ma.addObject("nowWeek",times);
                return ma;
            }
    }
    /**
     * 格式化时间
     * @param request
     * @param binder
     * @throws Exception
     */
    @InitBinder
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
        CustomDateEditor editor = new CustomDateEditor(df, true);
        binder.registerCustomEditor(Date.class, editor);
    }

}
