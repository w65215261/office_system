package com.pmcc.soft.core.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.pmcc.soft.core.utils.SystemParamsUtil;
import com.sun.jmx.snmp.Timestamp;



public class HttpClientPost {
	
    public static String post(String parameters,String uri,String token,String appId)
    {
    	String url = getURl(uri,token,appId);
//    	System.out.println(url);
//    	HttpClient httpClient = new DefaultHttpClient();
//    	
//    	HttpPost method = new HttpPost(url);
//    	method.addHeader("Content-type", "text/html;charset=UTF-8");
////    	JSONObject json =  new JSONObject();
////		json.put("title", "nimei");
//        StringEntity se;
//        UrlEncodedFormEntity uefEntity;
//		try {
//			uefEntity = new UrlEncodedFormEntity(parameters, "UTF-8");
//			method.setEntity(uefEntity);
//			se = new StringEntity(parameters);
//	        method.setEntity(se);
//	        HttpResponse httpResponse = httpClient.execute(method);
//	        HttpEntity entity = httpResponse.getEntity();
//	        InputStream inputStream = entity.getContent();
//	        byte[] b=new byte[1024];
//			int len = 0;
//			String s = "";
//			while ((len=inputStream.read(b))!=-1) {
//				s+=new String(b,0,len);
//			}
//			return s;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return "异常";
//		}
    	URL url1;
		try {
			url1 = new URL(url);
			HttpURLConnection http = (HttpURLConnection) url1.openConnection();
	        http.setDoOutput(true);  
	        http.setDoInput(true);  
	        http.setRequestMethod("POST");  
	        http.connect();  
	        OutputStreamWriter out = new OutputStreamWriter(http.getOutputStream(), "UTF-8"); 
	        
	        String input = parameters; 
	         
	        out.append(input);  
	        out.flush();  
	        out.close();  
	        BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()));
	        String line;
	        StringBuffer buffer = new StringBuffer();
	        while ((line = reader.readLine()) != null) {
	            buffer.append(line);
	        }
	        reader.close();
	        http.disconnect();
	        return buffer.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "异常";
		}

    }
    public static String getURl(String uri,String token,String appId){
    	
    			
    	//String token = "Ivj6eZRx40+MTx2ZvnG8nA"; 
    	long d = System.currentTimeMillis(); 
    	String timestamp = String.valueOf(d);
    	long round = Math.round(Math.random()*(99999-10000+1)+10000);
    	String nonce = String.valueOf(round);
    	String signature ="";
    	try {
			signature = getSignature(token,timestamp,nonce).toLowerCase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//return "http://weixin.open.ha.cn/SendTemplateMessage.aspx?signature="+signature+"&timestamp="+timestamp+"&nonce="+nonce+"&appid=ddsoft_jyt_001";
    	return uri+"?signature="+signature+"&timestamp="+timestamp+"&nonce="+nonce+"&appid="+appId;
    }
    public static String getSignature(String token, String timestamp,
            String nonce) throws IOException {
        /****
         * 对 jsapi_ticket、 timestamp 和 nonce 按字典排序 对所有待签名参数按照字段名的 ASCII
         * 码从小到大排序（字典序）后，使用 URL 键值对的格式（即key1=value1&key2=value2…）拼接成字符串
         * string1。这里需要注意的是所有参数名均为小写字符。 接下来对 string1 作 sha1 加密，字段名和字段值都采用原始值，不进行
         * URL 转义。即 signature=sha1(string1)。
         * **如果没有按照生成的key1=value&key2=value拼接的话会报错
         */
        String[] paramArr = new String[] { token,timestamp, nonce };
        Arrays.sort(paramArr);
        // 将排序后的结果拼接成一个字符串
        String content = paramArr[0].concat(paramArr[1]).concat(paramArr[2]);
        System.out.println("拼接之后的content为:"+content);
        String gensignature = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            // 对拼接后的字符串进行 sha1 加密
            byte[] digest = md.digest(content.toString().getBytes());
            gensignature = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 将 sha1 加密后的字符串与 signature 进行对比
        if (gensignature != null) {
            return gensignature;// 返回signature
        } else {
            return "false";
        }
        // return (String) (ciphertext != null ? ciphertext: false);
    }
    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }
    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
                'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        String s = new String(tempArr);
        return s;
    }
//    public static void main(String[] args) {
//    	Map<String, Object> jsonMap = new HashMap<String, Object>();
//		// openid
//		jsonMap.put("touser", "oDMxTuA6IzcV2qa5VNGnD5PoGPF4");
//		// 点击详情跳转的url
//		jsonMap.put("url", "对对对");
//		// 标题
//		jsonMap.put("title", "ddddd");
//		// 发布时间
//		jsonMap.put("time", "fffff");
//		// 发布者
//		jsonMap.put("aouther", "cccccc");
//		// 内容摘要
//		jsonMap.put(
//				"remark",
//				"ddd");
//		JSONObject jsonObject = JSONObject.fromObject(jsonMap);
//
//		String result = HttpClientPost.post(jsonObject.toString());
//		System.out.println(result);
//	}
}
