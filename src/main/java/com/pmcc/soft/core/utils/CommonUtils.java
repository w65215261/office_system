package com.pmcc.soft.core.utils;

import com.google.gson.Gson;
import com.pmcc.soft.week.domain.SystemLog;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CommonUtils {

    public static String convertNull(String str) {
        if (str == null) {
            str = "";
        }
        return str.trim();
    }

    /**
     * 字符串乱码判断，并转码
     * 用getBytes(encoding)：返回字符串的一个byte数组
     * 当b[0]为  63时，应该是转码错误
     * A、不乱码的汉字字符串：
     * 1、encoding用GB2312时，每byte是负数；
     * 2、encoding用ISO8859_1时，b[i]全是63。
     * B、乱码的汉字字符串：
     * 1、encoding用ISO8859_1时，每byte也是负数；
     * 2、encoding用GB2312时，b[i]大部分是63。
     * C、英文字符串
     * 1、encoding用ISO8859_1和GB2312时，每byte都大于0；
     * <p/>
     * 总结：给定一个字符串，用getBytes("iso8859_1")
     * 1、如果b[i]有63，不用转码；  A-2
     * 2、如果b[i]全大于0，那么为英文字符串，不用转码；  B-1
     * 3、如果b[i]有小于0的，那么已经乱码，要转码。  C-1
     *
     * @param str
     * @return UTF-8格式的字符串
     */
    public static String toEncoding(String str) {
        if (str == null) return null;
        String retStr = str;
        byte b[];
        try {
            b = str.getBytes("ISO8859_1");
            for (int i = 0; i < b.length; i++) {
                byte b1 = b[i];
                if (b1 == 63) {
                    break;    //1
                } else if (b1 > 0) {
                    continue;//2
                } else if (b1 < 0) {        //不可能为0，0为字符串结束符
                    retStr = new String(b, "UTF-8");
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
            return retStr;
        }
        return retStr;
    }

    /**
     * 从request中获取访问信息
     *
     * @param request
     * @return
     */
    public static SystemLog getRequestInfo(HttpServletRequest request) {

        SystemLog log = new SystemLog();
        // 从request中获取信息
        String ip = getRemoteAddr(request);
        String parameters = "{";
        Map<String, String[]> params = request.getParameterMap();
        System.out.println(request.getParameterNames());
        Iterator it = params.keySet().iterator();
        while (it.hasNext()) {
            String paramName = (String) it.next();
            String paramValue = request.getParameter(paramName);
            //处理参数名与值
            parameters += "'" + paramName + "':'" + paramValue + "',";
        }
        parameters += "}";
        log.setId(UUIDGenerator.getUUID());
        log.setIpAddress(ip);
        log.setParameters(parameters);

        return log;
    }

    /**
     * 获取客户端IP地址.<br>
     * 支持多级反向代理
     *
     * @param request HttpServletRequest
     * @return 客户端真实IP地址
     */
    public static String getRemoteAddr(final HttpServletRequest request) {
        try {
            String remoteAddr = request.getHeader("X-Forwarded-For");
            // 如果通过多级反向代理，X-Forwarded-For的值不止一个，而是一串用逗号分隔的IP值，此时取X-Forwarded-For中第一个非unknown的有效IP字符串
            if (isEffective(remoteAddr) && (remoteAddr.indexOf(",") > -1)) {
                String[] array = remoteAddr.split(",");
                for (String element : array) {
                    if (isEffective(element)) {
                        remoteAddr = element;
                        break;
                    }
                }
            }
            if (!isEffective(remoteAddr)) {
                remoteAddr = request.getHeader("X-Real-IP");
            }
            if (!isEffective(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
            return remoteAddr;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取客户端源端口
     *
     * @param request
     * @return
     */
    public static Long getRemotePort(final HttpServletRequest request) {
        try {
            String port = request.getHeader("remote-port");
            try {
                return Long.parseLong(port);
            } catch (NumberFormatException ex) {
                return 0l;
            }
        } catch (Exception e) {
            return 0l;
        }
    }

    /**
     * 远程地址是否有效.
     *
     * @param remoteAddr 远程地址
     * @return true代表远程地址有效，false代表远程地址无效
     */
    private static boolean isEffective(final String remoteAddr) {
        boolean isEffective = false;
        if ((null != remoteAddr) && (!"".equals(remoteAddr.trim()))
                && (!"unknown".equalsIgnoreCase(remoteAddr.trim()))) {
            isEffective = true;
        }
        return isEffective;
    }

    public String getRemoteHost(javax.servlet.http.HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }

    /**
     * B 转为 KB、MB、GB、TB
     *
     * @param size
     * @return
     */
    public static String getPrintSize(long size) {

        DecimalFormat df = new DecimalFormat("#.00");
        double s = 0;
        if (size < 1024) {
            // 以B为单位
            return String.valueOf(size) + "B";
        } else {
            s = size / (float) 1024;
        }
        if (s < 1024) {
            // 以KB为单位，保留最后2位小数，
            return df.format(s) + "KB";
        } else {
            s = s / 1024;
        }
        if (s < 1024) {
            //以MB为单位，保留最后2位小数，
            return df.format(s) + "MB";
        } else {
            s = s / 1024;
        }
        if (s < 1024) {
            //以GB为单位，保留最后2位小数，
            return df.format(s) + "GB";
        } else {
            s = s / 1024;
            return df.format(s) + "TB";
        }
    }

    /**
     * 获取推送参数
     *
     * @param jsonType
     * @param params
     * @return
     */
    public static Map<String, String> getExtras(String jsonType, Object... params) {
        jsonType = SystemParamsUtil.getProperty(jsonType);
        String json = String.format(jsonType, params);
        return new Gson().fromJson(json, HashMap.class);
    }

    public static void main(String[] args) {

        String str = " 阿斯 adf sdf ";
        System.out.println(str.replaceAll("\\s*",""));
    }

}
