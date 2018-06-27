package com.pmcc.soft.core.common;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hndl.mobileplatform.service.PublicExcuteSqlService;
import com.pmcc.soft.core.organization.domain.NewsInfo;
import com.pmcc.soft.core.organization.manager.NewsInfoManager;
import com.pmcc.soft.core.utils.SystemParamsUtil;



public class MyTask {
    @Autowired
    NewsInfoManager newsInfoManager;
	@Resource
    private PublicExcuteSqlService publicExcuteSqlService;
	public void show(){  
		NewsInfo ni=new NewsInfo();
		List<NewsInfo> list=newsInfoManager.query(ni);
		for(NewsInfo newsInfo:list){	
			if(newsInfo.getToptime() !=null){
			     long intervalMilli = new Date().getTime()- newsInfo.getToptime().getTime();
			       int aa= (int) (intervalMilli / (24 * 60 * 60 * 1000));
			       String daty = SystemParamsUtil.getSysConfig().get("reducingDay").toString();
			    int   i=Integer.parseInt(daty);
			  if(aa >=i){
				  newsInfo.setToptime(newsInfo.getRptDate());
				  newsInfo.setTopmark("0");
				  newsInfoManager.update(newsInfo);
			  }
			}
		}	
    }  
      
    public void print(){  
        System.out.println("XMl:print run");  
    }  
}
