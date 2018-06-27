package com.pmcc.soft.core.common;


public class CommonVariables {

    //=================================模块类型=============================
    public static final String BUSINESSMODEL_RZ = "0";      // 日志
    public static final String BUSINESSMODEL_SP = "1";        // 审批
    public static final String BUSINESSMODEL_YP = "2";        // 云盘
    public static final String BUSINESSMODEL_GRZX = "3";        // 个人中心

    //=================================日志类型=============================
    public static final String RZ_RI = "0";      // 日报
    public static final String RZ_ZHOU = "1";        // 周报
    public static final String RZ_YUE = "2";        // 月报
    //=============================审批类型=================================
    public static final String APPROVEL_OVERTIME = "JB";                      // 加班
    public static final String APPROVEL_ENTRY = "RZ";                          // 入职
    public static final String APPROVEL_POSITIVE = "ZZ";                       // 转正
    public static final String APPROVEL_TURNOVER = "LZ";                       // 离职
    public static final String APPROVEL_SECTORAL_COLLABORATION = "BMXZ";    // 部门协作
    public static final String APPROVEL_MEETING = "HY";                      // 会议
    public static final String APPROVEL_VEHICLES = "YC";                      // 用车
    public static final String APPROVEL_REIMBURSEMENT = "BX";                  // 报销
    public static final String APPROVEL_LEAVE = "QJ";                          // 请假


    //=================================审核状态=============================

    public static final int APPROVEL_UNDONE = 0;      // 0未开始
    public static final int APPROVEL_PASS = 1;        // 1同意
    public static final int APPROVEL_NO_PASS = 2;    // 2拒绝
    public static final int APPROVEL_RUN = 3;         // 3进行中


    //=================================人员配置分组=============================

    public static final String APPROVEL_GROUP_1TH_CODE = "1th";        // 一级
    public static final String APPROVEL_GROUP_2ND_CODE = "2nd";        // 二级


    //================================控件变量===============================

    public static final int CONTROL_TEXT = 0;
    public static final int CONTROL_TEXTAREA = 1;
    public static final int CONTROL_CALENDAR_RANGE = 2;
    public static final int CONTROL_CALENDAR = 3;
    public static final int CONTROL_SELECT = 4;

    //=============================公共文件夹名称、类型=================================
    public static final String PUBLIC_FOLDER_NAME = "公共文件";
    public static final String PUBLIC_FOLDER_TYPE = "0";
    //=============================公共文件夹总容量(B)=================================
    public static final long PUBLIC_FOLDER_SIZE = 107374182400l;
    //=============================个人文件夹名称、类型=================================
    public static final String PRIVATE_FOLDER_NAME = "我的文件";
    public static final String PRIVATE_FOLDER_TYPE = "1";
    //=============================个人文件夹总容量(B)=================================
    public static final long PRIVATE_FOLDER_SIZE = 2147483648l;

    //=============================手机端文件筛选类型=================================
    public static final String TYPE_ALL = "-1";// 所有
    public static final String TYPE_DOCUMENT = "0";//文档
    public static final String TYPE_ZIP = "1";//压缩包
    public static final String TYPE_PIC = "2";//图片
    public static final String TYPE_VIDEO = "3";//视频
    public static final String TYPE_AUDIO = "4";//音频
    public static final String TYPE_APP = "5";//应用
    public static final String TYPE_OTHER = "6";//其他

    //=============================推送到手机端信息=================================
    public static final String SendPushToAndroid_Title = "移动办公";// 通知标题
    public static final String SendPushToAndroid_Task1Assign_Alert = "待审批";// 审批工作流开始
    public static final String SendPushToAndroid_Check_Yes_Alert = "您的申请已通过";// 审核人点击审核 同意
    public static final String SendPushToAndroid_Check_No_Alert = "您的申请已被拒绝";// 审核人点击审核 拒绝
    public static final String SendPushToAndroid_Report_Alert = "有新的日志提交，请查看";// 日志提交


    public static void main(String[] args) {
        String[] km = "adsfa".split("\\.");
        System.out.println(km[km.length - 1]);
    }
}

