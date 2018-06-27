package com.pmcc.soft.week.web;

import com.google.gson.Gson;
import com.pmcc.soft.core.common.OnlineUser;
import com.pmcc.soft.core.organization.domain.OrganPersonRelation;
import com.pmcc.soft.core.organization.domain.OrganizationInfo;
import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.organization.manager.OrganPersonRelationManager;
import com.pmcc.soft.core.organization.manager.OrganizationInfoManager;
import com.pmcc.soft.core.organization.manager.PersonManageManager;
import com.pmcc.soft.core.utils.AppUtils;
import com.pmcc.soft.core.utils.DateUtil;
import com.pmcc.soft.week.domain.CityNum;
import com.pmcc.soft.week.domain.SignInfo;
import com.pmcc.soft.week.manager.CityIdManager;
import com.pmcc.soft.week.manager.CityNumManager;
import com.pmcc.soft.week.manager.SignInfoManager;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;
import java.io.BufferedReader;
import java.net.*;
import java.io.IOException;
import net.sf.json.JSONObject;


import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.text.DateFormat;
/**
 * Created by sunyake on 15/10/27.
 */
@Controller
@RequestMapping(value = "signInfo")
public class SignInfoController {

    @Autowired
    private SignInfoManager signInfoManager;
    @Autowired
    private PersonManageManager personManageManager;
    @Autowired
    private CityNumManager cityNumManager;

    @Autowired
    private OrganPersonRelationManager organPersonRelationManager;
    @Autowired
    private OrganizationInfoManager organizationInfoManager;
    @RequestMapping(value = "query",method = RequestMethod.GET)
    @ResponseBody
    public List<SignInfo> query(SignInfo signInfo,HttpServletRequest request){


        if (signInfo.getSignPersonId() !=null&&!"".equals(signInfo.getSignPersonId())){

            OnlineUser user= AppUtils.getOnlineUser(WebUtils.getSessionId(request));
            if(user!=null) signInfo.setSignPersonId(user.getUserId());
        }


       return signInfoManager.query(signInfo);



    }




    @RequestMapping(value = "insert",method = RequestMethod.POST)
    @ResponseBody
    public String insert(SignInfo signInfo,HttpServletRequest request){
        signInfoManager.insert(signInfo);

            return "1";

    }



    @RequestMapping(value = "show",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView query(){
        ModelAndView mav = new ModelAndView("signInfo/signInfo");
        return mav;
    }

    /**
     * 根据人员查询签到记录
     * @param personManage
     * @param signInfo
     * @param request
     * @param startTime
     * @param endTime
     * @param personId
     * @param personName
     * @return
     */
    @RequestMapping(value = "queryByPerson",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView queryByPerson(PersonManage personManage,SignInfo signInfo,HttpServletRequest request,Date startTime,Date endTime,String personId,String personName){
        ModelAndView mav = new ModelAndView("signInfo/seachAll");
        //得到2个日期之间的所有日期
        Long oneDay = 1000 * 60 * 60 * 24l;
        Long time = startTime.getTime();
        Long endTimes = endTime.getTime();
        //用于存放2个日期之间的所有日期
        List lists=new ArrayList<String>();
        //用于存放签到的时间
        List timeList=new ArrayList<String>();
        while (time <= endTimes) {

            Date d = new Date(time);
            DateFormat df = new SimpleDateFormat("MM-dd");
            System.out.println(df.format(d));
            String dates=df.format(d);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(d);
            int w = calendar1.get(Calendar.DAY_OF_WEEK) - 1;
            String week="";
            if(w==1){
                week="一";
            }
            if(w==2){
                week="二";
            }
            if(w==3){
                week="三";
            }
            if(w==4){
                week="四";
            }
            if(w==5){
                week="五";
            }
            if(w==6){
                week="六";
            }
            if(w==0){
                week="七";
            }
            lists.add(dates+"(周"+week+")");
            //得到一天的开始时间和结束时间
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(d);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            Date start = calendar.getTime();
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.SECOND, -1);
            Date end = calendar.getTime();
            signInfo.setStartTime(start);
            signInfo.setEndTime(end);
            signInfo.setSignPersonId(personId);
            List<SignInfo> timeListOne=signInfoManager.queryOneDay(signInfo);
            if(timeListOne.size()>0) {
                DateFormat ds = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String datetime=ds.format(timeListOne.get(0).getSignTime());
                timeList.add(datetime);
            }else{
                timeList.add("无签到记录");
            }
            System.out.println(start);
            System.out.println(end);
            time += oneDay;
        }
       OrganPersonRelation organPersonRelation=organPersonRelationManager.queryOrg(personId);
       OrganizationInfo organizationInfo= organizationInfoManager.queryOrgCname(organPersonRelation.getOrganizationInfoId());
        personManage=personManageManager.findPersonCnameByOid(personId);
        String duty=personManage.getDuty();
        mav.addObject("orgCname",organizationInfo.getOrgCname());
        mav.addObject("lists",lists);
        mav.addObject("timeList",timeList);
        mav.addObject("personName",personName);
        mav.addObject("duty",duty);
//        mav.addObject("list",list);
//        mav.addObject("totalPage", totalPage);
//        mav.addObject("currentPage", currentPage);
//        mav.addObject("totalRecord",totalRecord);
        return mav;

    }

    /**
     * 用于时间转换
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

    /**
     * 查询天气预报
     * @param request
     * @return
     * @throws IOException
     * @throws NullPointerException
     */
    @RequestMapping(value = "showWeather",method = RequestMethod.GET)
    @ResponseBody
    public String showWeather(HttpServletRequest request)throws IOException ,NullPointerException {
        String cityName=request.getParameter("cityName");
      CityNum city= cityNumManager.findCityId(cityName);
        String cityId=city.getCityId();
//        String cityId = "101180101";
        URLConnection connectionData;
        StringBuilder sb=new StringBuilder();
        BufferedReader br;// 读取data数据流
        JSONObject jsonData;
        JSONObject info;
//        http://weatherapi.market.xiaomi.com/wtr-v2/weather?cityId=101121301
        URL url = new URL("http://weatherapi.market.xiaomi.com/wtr-v2/weather?cityId=" + cityId );

        connectionData = url.openConnection();
        connectionData.setConnectTimeout(1000);
        try {
            br = new BufferedReader(new InputStreamReader(
                    connectionData.getInputStream(), "UTF-8"));
            String line = null;
            while ((line = br.readLine()) != null)
                sb.append(line);
        } catch (SocketTimeoutException e) {
            System.out.println("连接超时");
        } catch (FileNotFoundException e) {
            System.out.println("加载文件出错");
        }

        String datas = sb.toString();
//        jsonData = JSONObject.fromObject(datas);
//          System.out.println(jsonData.toString());
//        info = jsonData.getJSONObject("weatherinfo");

        return datas;
        }

    /**
     * 根据组织查签到记录
     * @param organPersonRelation
     * @param signInfo
     * @param request
     * @param startTime
     * @param endTime
     * @param orgId
     * @param orgName
     * @return
     */
    @RequestMapping(value = "queryByOrg",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView queryByOrg(PersonManage personManage,OrganPersonRelation organPersonRelation,SignInfo signInfo,HttpServletRequest request,Date startTime,Date endTime,String orgId,String orgName) throws ParseException {
        ModelAndView mav = new ModelAndView("signInfo/seachAllAdress");
        organPersonRelation.setInitPage(0);
        organPersonRelation.setOrganizationInfoId(orgId);
        List<OrganPersonRelation>personList= organPersonRelationManager.queryPersonByOrg(organPersonRelation);
        for(OrganPersonRelation org:personList){
            personManage=personManageManager.findPersonCnameByOid(org.getPersonInfoId());
            org.setUserCname(personManage.getUserCname());
            org.setDuty(personManage.getDuty());
        }
        organPersonRelation.setInitPage(1);
        int totalRecord= organPersonRelationManager.queryPersonByOrg(organPersonRelation).size();
        int totalPage=(int) Math.ceil(totalRecord * 1.0 /  organPersonRelation.getRows());
        int currentPage= organPersonRelation.getPage();
        //得到2个日期之间的所有日期
        Long oneDay = 1000 * 60 * 60 * 24l;
        Long time = startTime.getTime();
        Long endTimes = endTime.getTime();
        //用于存放2个日期之间的所有日期
        List lists=new ArrayList<String>();
        //用于存放签到的时间
        List timeList=new ArrayList<String>();

        while (time <= endTimes) {
            Date d = new Date(time);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat dff = new SimpleDateFormat("MM-dd");
            String dates = df.format(d);
            String dates1 = dff.format(d);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(d);
            int w = calendar1.get(Calendar.DAY_OF_WEEK) - 1;
            String week=" ";
            if(w==1){
                week="一";
            }
            if(w==2){
                week="二";
            }
            if(w==3){
                week="三";
            }
            if(w==4){
                week="四";
            }
            if(w==5){
                week="五";
            }
            if(w==6){
                week="六";
            }
            if(w==0){
                week="七";
            }
            lists.add(dates1+"(周"+week+")");
            timeList.add(dates);
            time += oneDay;
        }

        for(OrganPersonRelation opr:personList){
            List<Object> wan = new ArrayList<Object>();
            for(int i =0;i<timeList.size();i++){

                Calendar calendar = Calendar.getInstance();

                Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse(timeList.get(i).toString());
                calendar.setTime(d1);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                Date start = calendar.getTime();
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                calendar.add(Calendar.SECOND, -1);
                Date end = calendar.getTime();
                signInfo.setSignPersonId(opr.getPersonInfoId());
                signInfo.setStartTime(start);
                signInfo.setEndTime(end);
                List<SignInfo> timeListOne = signInfoManager.queryOneDay(signInfo);
                if (timeListOne.size() > 0) {
                    DateFormat ds = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    String datetime = ds.format(timeListOne.get(0).getSignTime());
                    wan.add(datetime);
                } else {
                    wan.add("无签到记录");
                }
            }
            opr.setList(wan);
        }

        mav.addObject("lists",lists);
        mav.addObject("timeList",timeList);
        mav.addObject("orgName",orgName);
        mav.addObject("personList",personList);
        mav.addObject("totalPage",totalPage);
        mav.addObject("currentPage", currentPage);
        mav.addObject("totalRecord",totalRecord);
        return mav;
    }

    @RequestMapping(value = "queryOneDayByPerson",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView queryOneDayByPerson(PersonManage personManage,SignInfo signInfo,HttpServletRequest request,Date startTime,Date endTime,String personId,String personName){
        ModelAndView mav = new ModelAndView("signInfo/seachOneDayAll");
        signInfo.setSignPersonId(personId);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date start = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.SECOND, -1);
        Date end = calendar.getTime();
        signInfo.setStartTime(start);
        signInfo.setEndTime(end);
        signInfo.setInitPage(0);
        List<SignInfo> signInfoList=signInfoManager.query(signInfo);
        signInfo.setInitPage(1);
        int totalRecord= signInfoManager.query(signInfo).size();
        int totalPage=(int) Math.ceil(totalRecord * 1.0 /  signInfo.getRows());
        int currentPage= signInfo.getPage();
        OrganPersonRelation organPersonRelation=organPersonRelationManager.queryOrg(personId);
        OrganizationInfo organizationInfo= organizationInfoManager.queryOrgCname(organPersonRelation.getOrganizationInfoId());
        personManage=personManageManager.findPersonCnameByOid(personId);
        String duty=personManage.getDuty();
        mav.addObject("orgCname",organizationInfo.getOrgCname());
        mav.addObject("lists",signInfoList);
        mav.addObject("personName",personManage.getUserCname());
        mav.addObject("totalPage",totalPage);
        mav.addObject("currentPage", currentPage);
        mav.addObject("totalRecord",totalRecord);
        mav.addObject("duty",duty);
        return mav;
    }

}
