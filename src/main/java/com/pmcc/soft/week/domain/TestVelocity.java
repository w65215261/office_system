package com.pmcc.soft.week.domain;

import com.pmcc.soft.core.common.CommonVariables;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangYanChang on 2016/4/21.
 */
public class TestVelocity {


    //读取控件类型
    public static List<TempleateControl> initData(List<TempleateControl> templeateControls) {
        List<TempleateControl> data = new ArrayList<>();
        for(TempleateControl tc : templeateControls){
            TempleateControl a = new TempleateControl(tc.getControlType(),tc.getTemplateCode(), tc.getControlTitle(), tc.getPlaceHolder(), tc.getControlKey(),tc.getTemplateId(),tc.getIndex());
            data.add(a);
        }
        return data;
    }

    //读取控件类型
    private static List<DiySelectDisplay> initSelect(List<DiySelectDisplay> diySelectDisplays) {
        List<DiySelectDisplay> data = new ArrayList<>();
        for(DiySelectDisplay dsd : diySelectDisplays){
            DiySelectDisplay a = new DiySelectDisplay(dsd.getOptionText(), dsd.getOptionValue());
            data.add(a);
        }
        return data;
    }

    public static VelocityEngine getVelocityEngine() {
        VelocityEngine ve = new VelocityEngine();
        // VE引擎设置PATH地址
        ve.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        ve.setProperty("file.resource.loader.path", "org.apache.velocity.tools.view.WebappResourceLoader");
        // 处理中文问题
        ve.setProperty(Velocity.INPUT_ENCODING, "utf-8");
        ve.setProperty(Velocity.OUTPUT_ENCODING, "utf-8");
        return ve;
    }

    private static void setData(VelocityContext velocityContext, TempleateControl templeateControl,List<DiySelectDisplay> diySelectDisplays) {
        velocityContext.put("controlTitle", templeateControl.getControlTitle());
        velocityContext.put("templateCode", templeateControl.getTemplateCode());
        if (templeateControl.getControlType() == CommonVariables.CONTROL_CALENDAR_RANGE) {
            velocityContext.put("startControlKey", "start" + templeateControl.getControlKey());
            velocityContext.put("endControlKey", "end" + templeateControl.getControlKey());
            velocityContext.put("startPlaceHolder", "开始时间");
            velocityContext.put("endPlaceHolder", "结束时间");
        } else if (templeateControl.getControlType() == CommonVariables.CONTROL_SELECT) {
            velocityContext.put("options", initSelect(diySelectDisplays));
            velocityContext.put("controlKey", templeateControl.getControlKey());
            velocityContext.put("placeHolder", templeateControl.getPlaceHolder());
        } else {
            velocityContext.put("placeHolder", templeateControl.getPlaceHolder());
            velocityContext.put("controlKey", templeateControl.getControlKey());
        }
    }


    private static String getTemplatePath(int type) {
        String path = "";
        if (CommonVariables.CONTROL_TEXT == type) {
            path = "vm/text.vm";
        } else if (CommonVariables.CONTROL_TEXTAREA == type) {
            path = "vm/textarea.vm";
        } else if (CommonVariables.CONTROL_CALENDAR == type) {
            path = "vm/calendar.vm";
        } else if (CommonVariables.CONTROL_SELECT == type) {
            path = "vm/select.vm";
        } else {
            path = "vm/calendarRange.vm";
        }
        return path;
    }

    public static String getHtml(TempleateControl templeateControl,List<DiySelectDisplay> diySelectDisplays) {
        String resHtml = "";
        try {
            VelocityEngine ve = getVelocityEngine();
            // 引擎初始化
            ve.init();
            Template template = ve.getTemplate(getTemplatePath(templeateControl.getControlType()));
            // 获取上下文
            VelocityContext velocityContext = new VelocityContext();
            // 把数据填入上下文
            setData(velocityContext, templeateControl,diySelectDisplays);
            // 获取模板的字符串
            StringWriter wt = new StringWriter();
            template.merge(velocityContext, wt);
            wt.flush();
            resHtml = wt.getBuffer().toString();
            //System.out.print(wt.getBuffer().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resHtml;
    }
}
