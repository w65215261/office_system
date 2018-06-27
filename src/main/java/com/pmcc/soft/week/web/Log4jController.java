package com.pmcc.soft.week.web;


import junit.framework.Test;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.logging.Logger;


/**
 * Created by sunyake on 15/10/27.
 */
@Controller
@RequestMapping(value = "log4j")
public class Log4jController {

    private static Logger logger = Logger.getLogger(Log4jController.class.getName());

    public void log4j(String message){

    InetAddress ia=null;
    try {
        ia=ia.getLocalHost();

        String localname=ia.getHostName();
        String localip=ia.getHostAddress();
        logger.info("电脑IP:"+localip+"电脑名称1:"+localname+message);
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

    }
}
