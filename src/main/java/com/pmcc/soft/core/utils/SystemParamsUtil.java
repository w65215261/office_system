package com.pmcc.soft.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class SystemParamsUtil {
	private final static String filePath = "systemParamsConfig.properties";
	private static Map configMap = new HashMap();
	private static Properties prop = new Properties();
	
	public static void initSysconfig() {
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		InputStream in = null;
		in = cl.getResourceAsStream(filePath);
		try {
			prop.load(in);
			
			Iterator it = prop.keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				
				configMap.put(key, prop.get(key));
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	public static Map getSysConfig() {
		initSysconfig();
		return configMap;
	}

	public static String getProperty(String property) {
		initSysconfig();
		return prop.getProperty(property).trim();
	}

	/**
	 * 获取所有文件扩展名
	 * @return
     */
	public static List<String> getFileTypeAll() {

		List<String> list = new ArrayList<String>();
		String[] arr = new String[]{"type_document","type_zip","type_pic","type_video","type_audio","type_app"};
		for(int i = 0; i < arr.length; i++){
			List<String> res = getFileType(arr[i]);
			list.addAll(res);
		}
		return list;
	}

	/**
	 * 获取某种文件扩展名
	 * @param property
	 * @return
     */
	public static List<String> getFileType(String property) {

		List<String> list = new ArrayList<String>();
		initSysconfig();
		String type = prop.getProperty(property).trim();
		String[] arr = type == null ? null : type.split(",");
		if(arr != null && arr.length > 0){
			for (String s : arr) {
				list.add(s);
			}
		}
		return list;
	}

	public static void main(String[] args) {
		System.out.println(getFileTypeAll().size());
	}
}
