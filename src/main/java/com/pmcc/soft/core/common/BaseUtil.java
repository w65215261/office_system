package com.pmcc.soft.core.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseUtil {
	public static String getDataFromLong(Long time){
		try {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date(time);
			String strTime = formatter.format(date);
			return strTime;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("时间转换失败");
			return null;
		}
			
	
		
	}
}
