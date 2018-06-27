package com.pmcc.soft.core.utils;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pmcc.soft.core.common.OnlineUser;

public class AppUtils {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private static Map<String, Object> onlineUsers = new LinkedHashMap<String, Object>();

	public static  Map<String, Object> getOnlineUsers() {
		return onlineUsers;
	}

	public static void addOnlineUser(String sessionId, OnlineUser online) {
		if (!onlineUsers.containsKey(sessionId)) {
			OnlineUser onlineUser = new OnlineUser();
			onlineUser.setUserId(online.getUserId());
			onlineUser.setName(online.getName());
			onlineUser.setType(online.getType());
			onlineUser.setUnit(online.getUnit());
			onlineUser.setUnitId(online.getUnitId());
			onlineUsers.put(sessionId, onlineUser);
		}
	}

	public static OnlineUser getOnlineUser(String sessionId) {
		return (OnlineUser) onlineUsers.get(sessionId);
	}

	public static void removeOnlineUser(String sessionId) {
		onlineUsers.remove(sessionId);
	}
}
