package com.pmcc.soft.core.utils;

import net.sf.json.JsonConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;


/**
 * Created by Admin on 2016/1/26.
 */
public class JsonUtils {
    private static Log logger = LogFactory.getLog(JsonUtils.class);

    protected JsonUtils() {
    }

    public static JsonConfig configJson(String datePattern) {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor(datePattern));
        return jsonConfig;
    }

}