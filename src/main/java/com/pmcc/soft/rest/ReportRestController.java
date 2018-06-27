package com.pmcc.soft.rest;

import com.pmcc.soft.core.organization.domain.OrganPersonRelation;
import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.organization.manager.OrganPersonRelationManager;
import com.pmcc.soft.core.organization.manager.PersonInfoManager;
import com.pmcc.soft.core.utils.DateUtil;
import com.pmcc.soft.week.domain.Report;
import com.pmcc.soft.week.manager.ReportManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by sunyake on 15/12/25.
 */
@Controller
@RequestMapping("/restReport")
public class ReportRestController {
    @Autowired
    ReportManager reportManager;
    @Autowired
    PersonInfoManager personInfoManager;


    @Autowired
    OrganPersonRelationManager organPersonRelationManager;


    //查询判断展示或者填写
    @RequestMapping(value = "/queryFor", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView show(Report report,HttpServletRequest request,HttpSession session) {
        ModelAndView ma = new ModelAndView("/report/reportDay");
        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        String userId = user.getId();
        report.setRtpPersonId(userId);
        String type=request.getParameter("type");
        report.setType(type);
        Date newDate= DateUtil.getNewDate();
        report.setRptDate(newDate);
        report.setBelongsDate(newDate);
        //判断每天第一次是进入日报填写界面还是展示界面
        List<Report> reports = reportManager.queryShow(report);
        if (reports.size()>0) {
            ma.addObject("list", reports);
            return ma;
        }else{
            return new ModelAndView("/report/dayReport");
        }
    }

    //点击日历查询数据,已写日报展示,未写转到填写页面
    @RequestMapping(value = "/queryForOne", method = RequestMethod.GET)
    @ResponseBody
    public List showOne(Report report,HttpSession session) {
        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        String userId = user.getId();
        report.setRtpPersonId(userId);
        //判断查询每天数据是进入日报填写界面还是展示界面
        List<Report> reports = reportManager.queryCalendar(report);
        if (reports.size()>0) {
            return reports;
        }else{
            return null;
        }

    }

    //查询保存数据,直接局部刷新
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public List  query(Report report,HttpServletRequest request,HttpSession session){
        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        String userId = user.getId();
        report.setRtpPersonId(userId);
        //根据登录人id和日报所属时间查询
        List<Report> list=reportManager.query(report);
        return list;
    }

    //添加日报
    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(Report report, HttpServletRequest request,HttpSession session) {

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        report.setWeek(week);
        String belongsDate = request.getParameter("belongsDate");
        report.setRptDate(new Date());
        Date date = new Date();
        String sdate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        if (belongsDate.equals(sdate)) {
            report.setStatus("0");
        } else {
            report.setStatus("1");
        }
        List<OrganPersonRelation> orgList=organPersonRelationManager.findByPersonId(report.getRtpPersonId());
        report.setOrgId(orgList.get(0).getOrganizationInfoId());
        int res = 0;
        //插入填报数据
        int i = reportManager.insert(report);
        if (res == i) {
            return "fail";
        } else {
            return "success";
        }
    }

    //修改填报数据
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String update(Report report,HttpServletRequest request){

        int res=0;
        //修改方法
        int i=reportManager.update(report);
        if (res==i) {
            return "fail";
        }else{
            return "success";
        }
    }

    //侧拉遮罩层查询
    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    public Report findById(String id){
        //根据填报id查询
        Report reports=reportManager.findById(id);
        return reports;
    }

    //日历添加背景颜色
    public  static  final int DAY=65;
    @RequestMapping(value = "/color", method = RequestMethod.POST)
    @ResponseBody
    public void count(HttpSession session,HttpServletRequest request,HttpServletResponse response){

        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        String userId = user.getId();
        Date date=new Date();
        String color="[";
        for(int i=0;i<=DAY;i++){
            Date newDate= DateUtil.addDays(date, -i);
            //通过id,时间查询数据判断m的值
            int m= reportManager.queryByDate(userId,newDate);
            int year= DateUtil.getYear(new Timestamp(newDate.getTime()));
            int month= DateUtil.getMonth(new Timestamp(newDate.getTime()))-1;
            int day= DateUtil.getDay(new Timestamp(newDate.getTime()));

            //m等于0，日报按时提交
            if(m==0){
                if(i==DAY){
                    color+="{allDay:true, start:new Date("+year+","+month+","+day+"),className:[\"event\", \"bg-color-greenLight\"]}";
                }else{
                    color+="{ allDay:true,start:new Date("+year+","+month+","+day+"),className:[\"event\", \"bg-color-greenLight\"]},";
                }
            }//m等于1，日报逾期提交
            else if(m==1){
                if(i==DAY){
                    color+="{ allDay:true,start:new Date("+year+","+month+","+day+"),className:[\"event\", \"bg-color-#87cefa\"]}";
                }else{
                    color+="{ allDay:true,start:new Date("+year+","+month+","+day+"),className:[\"event\", \"bg-color-#87cefa\"]},";
                }

            }//m等于2，为填报
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

    //周报
    @RequestMapping(value = "/showWeek", method = RequestMethod.GET)
    public ModelAndView showWeek(Report report,HttpServletRequest request,HttpSession session) {
        ModelAndView ma = new ModelAndView("/report/reportWeek");
        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        String userId = user.getId();
        report.setRtpPersonId(userId);
        String type=request.getParameter("type");
        report.setType(type);
        Date newDate= DateUtil.getNewDate();
        report.setRptDate(newDate);
        report.setBelongsDate(newDate);
        //判断每天第一次是进入周报填写界面还是展示界面
        List<Report> reports = reportManager.queryShow(report);
        if (reports.size()>0) {
            ma.addObject("list", reports);
            return ma;
        }else{
            return new ModelAndView("report/monthReport");
        }
    }

    //格式化时间
    @InitBinder
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
        CustomDateEditor editor = new CustomDateEditor(df, true);
        binder.registerCustomEditor(Date.class, editor);
    }

    /**
     * 根据前台传入的日期,和人员ID返回一个当前月的list(日报ID,状态,日期,提交人ID)
     * @param request
     * @param rtpPersonId 登录人的ID
     * @param date 日期
     * @param report
     * @return
     */
    @RequestMapping(value = "/findDayReportStatus",method = RequestMethod.GET)
    @ResponseBody
    public List<Report> findDayReportStatus(HttpServletRequest request,String rtpPersonId ,Date date,Report report){
        Calendar dayc1 = new GregorianCalendar();
        dayc1.setTime(date);
        //得到传入月的第一天getActualMaximum(dayc1.DAY_OF_MONTH);
        dayc1.set(dayc1.DAY_OF_MONTH, 1);
        Date firstDate = dayc1.getTime();
        int s=dayc1.getActualMaximum(dayc1.DAY_OF_MONTH);
        //得到传入月的最后一天
        dayc1.add(dayc1.DAY_OF_MONTH, s-1);
        Date lastDate = dayc1.getTime();
        report.setFirstDate(firstDate);
        report.setLastDate(lastDate);
        report.setRtpPersonId(rtpPersonId);
        List<Report> dayReportStatusList=reportManager.findDayReportStatus(report);
        return dayReportStatusList;
    }

    /**
     * 根据日报的ID查询日报详情
     * @param request
     * @return
     */
    @RequestMapping(value = "/findReportById",method = RequestMethod.GET)
    @ResponseBody
    public Report findReportById(HttpServletRequest request) {
        String id=request.getParameter("id");
        Report report=reportManager.findById(id);
        return report;
    }
}
