package com.pmcc.soft.core.common;
import java.util.List;
import java.util.Map;

import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.provider.ProviderManager; 

import com.hndl.mobileplatform.model.Dlnoticemanage;
import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.utils.SystemParamsUtil;
public class SendInfoToAndroid {
	
   

	
	//AccountManager accountManager;

	// Create the configuration for this new connection 创建配置发文件
	public static boolean sendInfo(PersonManage currentpersonManagemanage,Dlnoticemanage manage,List<Map> listPersion,String param){
		 XMPPConnection.DEBUG_ENABLED = true;  //调试开关
		String xmppHost = SystemParamsUtil.getSysConfig().get("xmppHost").toString(); //服务器IP
		int xmppPort = Integer.valueOf(SystemParamsUtil.getSysConfig().get("xmppPort").toString());             //服务器端口
		ConnectionConfiguration connConfig = new ConnectionConfiguration(xmppHost, xmppPort);

		connConfig.setSecurityMode(SecurityMode.required);

		connConfig.setSASLAuthenticationEnabled(false);

		connConfig.setCompressionEnabled(false);
		// 允许自动连接
		connConfig.setReconnectionAllowed(true);
		connConfig.setSendPresence(true);
		//connConfig.setSendPresence(false);//不要告诉服务器自己的状态

		//创建连接
		XMPPConnection connection = new XMPPConnection(connConfig);

		//xmppManager.setConnection(connection);

		try {

			// Connect to the server 连接服务器
			connection.connect();
			String passwordKey = SystemParamsUtil.getSysConfig().get("passwordKey").toString();
			//加密
			Blowfish bf = new Blowfish(passwordKey);
			String decrypt = bf.decrypt(currentpersonManagemanage.getMd5Pwd().trim());
			 
			connection.login(currentpersonManagemanage.getUserEname(), decrypt);
			for(Map map : listPersion){
				Message newmsg = new Message();
				//System.out.println("USER:"+ connection.getUser()); 
				
				newmsg.setTo(map.get("USER_ENAME")+param);//  2012-20130101KZ");
				newmsg.setSubject(manage.getTitle());
				newmsg.setBody(manage.getContent());
				newmsg.setProperty("flag", "tongzhi");   //自定义属性 flag=tongzhi  是通知消息
				newmsg.setType(Message.Type.normal);// normal支持离线 
				//通知，在在线接收，广播是可以离线接收，在offline表中存储，chat是聊天消息


				//发送消息
				connection.sendPacket(newmsg);
			}
			




			System.out.println("successLRL");

		} catch (XMPPException e) {
		//throw new IllegalStateException(e);
			//Log.e(LOGTAG, "XMPP connection failed", e);
			System.out.println("errorLRL");

		}
		finally{
		//断开连接
			connection.disconnect();
			System.out.println("finallyLRL");
		
		}
			return true;
		}
	
}
