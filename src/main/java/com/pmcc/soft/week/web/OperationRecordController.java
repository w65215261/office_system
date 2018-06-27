package com.pmcc.soft.week.web;

import com.pmcc.soft.core.organization.manager.OrganizationInfoManager;
import com.pmcc.soft.week.domain.OperationRecord;
import com.pmcc.soft.week.manager.OperationRecordManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by asus on 2015/11/26.
 */
@Controller
@RequestMapping("operationRecord")
public class OperationRecordController {

    @Autowired
    OperationRecordManager operationRecordManager;
    @Autowired
    OrganizationInfoManager organizationInfoManager;

    /**
     * 操作记录的展示
     * @param or
     * @return
     */
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public ModelAndView show(OperationRecord or) {
        ModelAndView mav = new ModelAndView("/project/operationRecord");
        or.setOperationType("0");
        List<OperationRecord> ors = operationRecordManager.findByProjectOid(or);
        List<OperationRecord> operationRecordList = new ArrayList<OperationRecord>();
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
        String dateFlag ="";
        int i = 0;
        for(OperationRecord operationRecord : ors){
            OperationRecord orf = new OperationRecord();
            String content = operationRecord.getOperationContent();
            if(content != null && !content.equals("")){
                if(!content.equals("7")){
                    String[] ct = content.split(",");
                    if(ct[0].equals("0")||ct[0].equals("1")||ct[0].equals("4")||ct[0].equals("6")){
                        orf.setDistinguish(ct[0]);
                        orf.setNewContent(ct[1]);

                    }else{
                        orf.setDistinguish(ct[0]);
                        orf.setNewContent(ct[2]);
                        orf.setOldContent(ct[1]);
                    }
                }else{
                    orf.setDistinguish(content);
                }
            }
            Date dateTime = operationRecord.getOperationTime();
            String date = sdfDate.format(dateTime);
            String time = sdfTime.format(dateTime);
            if(i==0){
                orf.setModifyDate(date);
                dateFlag = date;
            }else {
                if(!dateFlag.equals(date)){
                    orf.setModifyDate(date);
                    dateFlag = date;
                }else{
                    orf.setModifyDate("");
                }
            }
            orf.setOperationPersonName(operationRecord.getOperationPersonName());
            orf.setModifyTime(time);
            operationRecordList.add(orf);
            i++;
        }

        mav.addObject("operationRecords",operationRecordList);
        return mav;
    }
}
