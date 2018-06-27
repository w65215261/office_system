package com.pmcc.soft.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.pmcc.soft.core.common.OnlineUser;
import com.pmcc.soft.core.common.View;
import com.pmcc.soft.core.organization.domain.OrganPersonRelation;
import com.pmcc.soft.core.organization.domain.OrganizationRelation;
import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.organization.manager.*;
import com.pmcc.soft.core.utils.AppUtils;
import com.pmcc.soft.core.utils.DateUtil;
import com.pmcc.soft.week.domain.SignInfo;
import com.pmcc.soft.week.domain.SignInfoReport;
import com.pmcc.soft.week.domain.SignInfoTran;
import com.pmcc.soft.week.manager.SignInfoManager;
import com.pmcc.soft.week.manager.SignInfoReportManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by sunyake on 15/10/27.
 * 手机端接口
 */
@Controller
@RequestMapping(value = "signInfo")
public class SignInfoRestController {

    @Autowired
    private SignInfoManager signInfoManager;

    @Autowired
    private OrganizationInfoManager organizationInfoManager;
    @Autowired
    OrganizationRelationManager organizationRelationManager;
    @Autowired
    PersonManageManager personManageManager;
    @Autowired
    OrganPersonRelationManager organPersonRelationManager;
    @Autowired
    private SignInfoReportManager signInfoReportManager;

    @RequestMapping(value = "query")
    @ResponseBody
    public List<SignInfo> query(SignInfo signInfo,HttpServletRequest request){


        if (signInfo.getSignPersonId() ==null&&!"".equals(signInfo.getSignPersonId())){

            OnlineUser user= AppUtils.getOnlineUser(WebUtils.getSessionId(request));
            if(user!=null) signInfo.setSignPersonId(user.getUserId());
        }


        return signInfoManager.query(signInfo);



    }

    /**
     * 团队内所有人，今天的签到信息
     * @param signInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "today")
    @ResponseBody
    public List<SignInfoTran> queryTodaySignInfo(SignInfo signInfo,HttpServletRequest request){
                //取出当前登录人，同组织机构下的组织机构-人员信息
                String personId=signInfo.getSignPersonId();
                //PersonManage personManage=  personManageManager.queryPersonById(personId);
                 List<OrganPersonRelation> organPersonRelationList1=  organPersonRelationManager.findByPersonId(personId);
                 String orgId="";
                 //获取登录人的组织机构信息
                  if(organPersonRelationList1!=null &&organPersonRelationList1.size()!=0){
                    orgId=organPersonRelationList1.get(0).getOrganizationInfoId();
                 }
                 PersonManage pm=new PersonManage();
                 pm.setId(orgId);
                pm.setInitPage(1);
                 List<PersonManage> personManageList=   personManageManager.queryRelation(pm);

                //设置查询条件为当天的开始时间和结束时间
                Date date=new Date();
                Timestamp[] time= DateUtil.getBetweenDayTime(new Timestamp(date.getTime()));
                signInfo.setStartTime(time[0]);
                signInfo.setEndTime(time[1]);
                 List<SignInfoTran> signInfoTrans=new ArrayList<SignInfoTran>();
        for(PersonManage personManage:personManageList){
            SignInfoTran signInfoTran=new SignInfoTran();
            signInfoTran.setSignPersonId(personManage.getId());
            signInfoTran.setSignPersonName(personManage.getUserCname());
            signInfo.setSignPersonId(personManage.getId());
            List<SignInfo> signInfoList= signInfoManager.query(signInfo);
            if(signInfoList==null || signInfoList.size()==0){
                //未签到
                signInfoTran.setSignState("0");

            }else{
                //已签到
                signInfoTran.setSignState("1");
                signInfoTran.setX(signInfoList.get(0).getX());
                signInfoTran.setY(signInfoList.get(0).getY());

            }
            signInfoTrans.add(signInfoTran);
        }






        return signInfoTrans;

    }





    @RequestMapping(value = "insert",method = RequestMethod.POST)
    @ResponseBody
    public Integer insert(SignInfo signInfo,HttpServletRequest request){
        signInfoManager.insert(signInfo);
        //查询在本地登陆次数
        int count=0;
        SignInfo signInfo1=new SignInfo();
        signInfo1.setInitPage(1);
        signInfo1.setSignPersonId(signInfo.getSignPersonId());
       List<SignInfo> signInfoList= signInfoManager.query(signInfo1);

        for(SignInfo sinfo :signInfoList){
           if(calDistance(signInfo,sinfo)<500.0){
               count++;
           }
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date start = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.SECOND, -1);
        Date end = calendar.getTime();
        SignInfoReport signInfoReport=new SignInfoReport();
        signInfoReport.setStartTime(start);
        signInfoReport.setLastSignAddress(signInfo.getSignAddress());
        signInfoReport.setSignFlag("1");
        signInfoReport.setEndTime(end);
        signInfoReport.setSignPersonId(signInfo.getSignPersonId());
        Date date=new Date();
        signInfoReport.setLastSignTime(date);
        int res=signInfoReportManager.updateSignInfoReport(signInfoReport);
        System.out.println("res11111111+="+res);
        return count;
    }

    @RequestMapping(value = "querySignCount",method = RequestMethod.POST)
    @ResponseBody
    public Integer querySignCount(SignInfo signInfo,HttpServletRequest request){
        //查询在本地登陆次数
        int count=0;
        SignInfo signInfo1=new SignInfo();
        signInfo1.setInitPage(1);
        signInfo1.setSignPersonId(signInfo.getSignPersonId());
        List<SignInfo> signInfoList= signInfoManager.query(signInfo1);

        for(SignInfo sinfo :signInfoList){
            if(calDistance(signInfo,sinfo)<500.0){
                count++;
            }
        }
        return count;
    }


    private double calDistance(SignInfo signInfoSrc,SignInfo signInfoDes){
        double x=Math.abs(signInfoSrc.getX()-signInfoDes.getX());
        double y=Math.abs(signInfoSrc.getY()-signInfoDes.getY());
        return Math.sqrt(x*x+y*y);
    }


    @JsonView(View.RestView.class)
    @RequestMapping(value = "organPerson")
    @ResponseBody
    public List<PersonManage> queryOrganPerson(SignInfo signInfo,HttpServletRequest request){


        //取出当前登录人，同组织机构下的组织机构-人员信息
        String personId=signInfo.getSignPersonId();
        //PersonManage personManage=  personManageManager.queryPersonById(personId);
        List<OrganPersonRelation> organPersonRelationList1=  organPersonRelationManager.findByPersonId(personId);
        String orgId="";
        //获取登录人的组织机构信息
        if(organPersonRelationList1!=null &&organPersonRelationList1.size()!=0){
            orgId=organPersonRelationList1.get(0).getOrganizationInfoId();
        }
        PersonManage pm=new PersonManage();
        pm.setId(orgId);
        pm.setInitPage(1);
        return personManageManager.queryRelation(pm);

    }

    @RequestMapping(value = "history")
    @ResponseBody
    public List<SignInfo> queryHistory(SignInfo signInfo,HttpServletRequest request){
        signInfo.setInitPage(1);
               List<SignInfo> signInfoList= signInfoManager.query(signInfo);
        for(SignInfo si:signInfoList){
            si.setSignPersonName(personManageManager.queryPersonById(si.getSignPersonId()).getUserCname());
        }
        return signInfoList;
    }



    @InitBinder
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setLenient(false);
        CustomDateEditor editor = new CustomDateEditor(df, true);
        binder.registerCustomEditor(Date.class, editor);
    }



}

