package com.pmcc.soft.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by yongxingsun
 * on 2015/3/26 0026 16:11
 */
public class ReadProperties {
    private static String softOrManageDepartment;
    private static String computerLine;


        static {
            Properties prop = new Properties();
            //InputStream in = Object.class.getResourceAsStream("/WEB-INF/classes/application.properties");
            //InputStream in = Object.class.getResourceAsStream("/WEB-INF/classes/application.properties");
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties");
            try {
                prop.load(in);
                softOrManageDepartment = prop.getProperty("softOrManageDepartment").trim();
                computerLine = prop.getProperty("computerLine").trim();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * 私有构造方法，不需要创建对象
         */
        private ReadProperties() {
        }

    public static String getComputerLine() {
        return computerLine;
    }

    public static void setComputerLine(String computerLine) {
        ReadProperties.computerLine = computerLine;
    }

    public static String getSoftOrManageDepartment() {
        return softOrManageDepartment;
    }

    public static void setSoftOrManageDepartment(String softOrManageDepartment) {
        ReadProperties.softOrManageDepartment = softOrManageDepartment;
    }

   public static void main(String args[]){
        String a = "1";
        //System.out.println(getSoftOrManageDepartment());
        if (getSoftOrManageDepartment().indexOf(a)>0){

            System.out.println(2);

        }else if (getComputerLine().indexOf(a)>0)
//            System.out.println(getSoftOrManageDepartment());
            System.out.println(3);
        }
    }
