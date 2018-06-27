package com.pmcc.soft.rest;

import com.pmcc.soft.core.common.CommonVariables;
import com.pmcc.soft.core.organization.domain.OrganizationInfo;
import com.pmcc.soft.core.organization.domain.PersonInfoManage;
import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.organization.manager.OrganizationInfoManager;
import com.pmcc.soft.core.organization.manager.PersonInfoManageManager;
import com.pmcc.soft.core.utils.DateUtil;
import com.pmcc.soft.core.utils.SystemParamsUtil;
import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.domain.NoteAttachment;
import com.pmcc.soft.week.domain.Report;
import com.pmcc.soft.week.domain.SystemAttachment;
import com.pmcc.soft.week.manager.SystemAttachmentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangbin on 2016/4/11.
 */
@Controller
@RequestMapping("/attachment")
public class AttachmentRestController {

    @Autowired
    private PersonInfoManageManager personInfoManageManager;
    @Autowired
    private SystemAttachmentManager systemAttachmentManager;
    @Autowired
    private OrganizationInfoManager organizationInfoManager;

    /**
     * 上传附件
     *
     * @param fileToUpload
     * @param request
     * @param response
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void upload(
            @RequestParam(value = "fileToUpload", required = false) MultipartFile[] fileToUpload,
            HttpServletRequest request, HttpServletResponse response) throws IOException {

        String personId = request.getParameter("personId");
        String uuid = request.getParameter("uuid");
        String businessModel = request.getParameter("businessModel");
        String businessType = request.getParameter("businessType");

        PersonInfoManage personInfoManage = new PersonInfoManage();
        personInfoManage.setId(personId);
        PersonInfoManage pm = personInfoManageManager.queryByOid(personInfoManage);
        String userName = pm.getUserCname();//发布人

        OrganizationInfo of = new OrganizationInfo();
        of = organizationInfoManager.queryOrgByOrgId(pm.getCompanyId());

        Map<String, Object> res = new HashMap<String, Object>();
        for (int i = 0; i < fileToUpload.length; i++) {
            String[] fileType = fileToUpload[i].getOriginalFilename().split("\\.");

            DateUtil du = new DateUtil();
            // 附件实体
            SystemAttachment satt = new SystemAttachment();
            satt.setId(UUIDGenerator.getUUID());

            //
            String kk = new String(fileToUpload[i].getOriginalFilename());
            String[] km = kk.split("\\.");
            kk = km[km.length - 1];
            // 上传到服务器文件名称
            String fname = UUIDGenerator.getUUID();
            // 存放在服务器端的文件名
            String fmn = fname + "." + kk;
            // 年月日文件夹
            String mm = du.DateToString(new Timestamp(new Date().getTime()), "yyyy-MM-dd");
            String fileName = mm + "/" + fmn;
            // 服务器存放附件文件夹
            String path = SystemParamsUtil.getProperty("appUploadPath");
            if (CommonVariables.APPROVEL_OVERTIME.equals(businessType)) {
                // 加班
                fileName = "JB/" + fileName;
            } else if (CommonVariables.APPROVEL_ENTRY.equals(businessType)) {
                //入职
                fileName = "RZ/" + fileName;
            } else if (CommonVariables.APPROVEL_POSITIVE.equals(businessType)) {
                //转正
                fileName = "ZZ/" + fileName;
            } else if (CommonVariables.APPROVEL_TURNOVER.equals(businessType)) {
                //离职
                fileName = "LZ/" + fileName;
            } else if (CommonVariables.APPROVEL_SECTORAL_COLLABORATION.equals(businessType)) {
                //部门协作
                fileName = "BMXZ/" + fileName;
            } else if (CommonVariables.APPROVEL_MEETING.equals(businessType)) {
                //会议
                fileName = "HY/" + fileName;
            } else if (CommonVariables.APPROVEL_VEHICLES.equals(businessType)) {
                //用车
                fileName = "YC/" + fileName;
            } else if (CommonVariables.APPROVEL_REIMBURSEMENT.equals(businessType)) {
                //报销
                fileName = "BX/" + fileName;
            } else if (CommonVariables.APPROVEL_LEAVE.equals(businessType)) {
                //请假
                fileName = "QJ/" + fileName;
            }
            satt.setBusinessModel(businessModel);
            satt.setBusinessType(businessType);
            satt.setOrgId(pm.getCompanyId());
            satt.setOrgCode(of.getOrgCode());
            satt.setOrgName(of.getOrgCname());
            satt.setFileUrl("/" + fileName);
            satt.setFileName(fileToUpload[i].getOriginalFilename());
            satt.setFileMathName(fmn);
            satt.setFileType(kk);
            satt.setBusinessData(uuid);
            satt.setDownloadCount(0);
            satt.setRptDate(new Date());
            satt.setDelFlag(0);
            satt.setRptPerson(personId);
            satt.setRptPersonName(userName);
            systemAttachmentManager.insert(satt);

            File targetFile = new File(path, fileName);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            // 保存
            try {
                fileToUpload[i].transferTo(targetFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        res.put("uuid", uuid);
        response.getWriter().write(uuid);
        response.getWriter().flush();
    }


    @RequestMapping(value = "/updateDownloadCount")
    @ResponseBody
    public String updateDownloadCount(SystemAttachment satt) {
        SystemAttachment systemAttachment = systemAttachmentManager.queryById(satt);
        satt.setDownloadCount(systemAttachment.getDownloadCount() + 1);
        int res = 0;
        int rows = systemAttachmentManager.updateDownloadCount(satt);
        if (res == rows) {
            return "fail";
        }
        return "success";
    }

    /**
     * 移动端个人中心上传头像
     *
     * @param fileToUpload
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/uploadUserPhoto", method = RequestMethod.POST)
    @ResponseBody
    public String uploadUserPhoto(
            @RequestParam(value = "fileToUpload", required = false) MultipartFile fileToUpload,
            HttpServletRequest request, HttpServletResponse response) throws IOException {

        String url = "{\"success\":\"false\",\"userPhotoUrl\":\"\"}";

        // 手机端上传参数
        String personId = request.getParameter("personId");// 上传人id
        // 上传人、机构
        PersonInfoManage personInfoManage = new PersonInfoManage();
        personInfoManage.setId(personId);
        PersonInfoManage pm = personInfoManageManager.queryByOid(personInfoManage);
        String userName = pm.getUserCname();//上传人
        OrganizationInfo org = new OrganizationInfo();
        List<OrganizationInfo> orgList = organizationInfoManager.queryOrgByPersonId(personId);
        if (orgList != null && orgList.size() > 0) {
            org = orgList.get(0);
        }


        // 原文件名称
        String originalFilename = new String(fileToUpload.getOriginalFilename());
        String[] arr = originalFilename.split("\\.");
        // 文件类型
        String fileType = arr.length > 1 ? arr[arr.length - 1] : "";
        // 存放在服务器端的文件名
        String serverFileName = fileType == "" ? UUIDGenerator.getUUID() : UUIDGenerator.getUUID() + "." + fileType;
        // 创建文件夹  年-月-日
        String folder = DateUtil.DateToString(new Timestamp(new Date().getTime()), "yyyy-MM-dd");
        // 数据库存放路径：年-月-日/文件名
        String filePath = "/" + folder + "/" + serverFileName;
        // 服务器存放附件文件夹
        String path = SystemParamsUtil.getProperty("userPhotoPath");
        // 附件实体
        SystemAttachment satt = new SystemAttachment();
        String uuid = UUIDGenerator.getUUID();
        satt.setId(uuid);
        satt.setBusinessModel(CommonVariables.BUSINESSMODEL_GRZX);
        satt.setBusinessType(CommonVariables.BUSINESSMODEL_GRZX);
        satt.setOrgId(pm.getCompanyId());
        satt.setOrgCode(org.getOrgCode());
        satt.setOrgName(org.getOrgCname());
        satt.setFileUrl(filePath);
        satt.setFileName(fileToUpload.getOriginalFilename());
        satt.setFileSize(fileToUpload.getSize());
        satt.setFileMathName(serverFileName);
        satt.setFileType(fileType);
        satt.setDownloadCount(0);
        satt.setRptDate(new Date());
        satt.setDelFlag(0);
        satt.setRptPerson(personId);
        satt.setRptPersonName(userName);
        systemAttachmentManager.insert(satt);

        File targetFile = new File(path, filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        try {
            // 上传文件
            fileToUpload.transferTo(targetFile);
            // 头像路径配置的虚拟目录名称
            filePath = SystemParamsUtil.getProperty("userPhotoVirtualPath") + "/" + filePath;
            // 修改人员表路径
            PersonInfoManage person = new PersonInfoManage();
            person.setId(personId);
            person.setUserPhotoUrl(filePath);
            personInfoManageManager.updateApp(person);
            url = "{\"success\":\"true\",\"userPhotoUrl\":\"" + filePath + "\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

}
