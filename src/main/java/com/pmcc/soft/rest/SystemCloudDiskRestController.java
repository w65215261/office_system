package com.pmcc.soft.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.pmcc.soft.core.common.CommonVariables;
import com.pmcc.soft.core.common.View;
import com.pmcc.soft.core.organization.domain.OrganizationInfo;
import com.pmcc.soft.core.organization.domain.PersonInfoManage;
import com.pmcc.soft.core.organization.manager.OrganizationInfoManager;
import com.pmcc.soft.core.organization.manager.PersonInfoManageManager;
import com.pmcc.soft.core.utils.CommonUtils;
import com.pmcc.soft.core.utils.DateUtil;
import com.pmcc.soft.core.utils.SystemParamsUtil;
import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.unit.FileDynamics;
import com.pmcc.soft.week.domain.SystemAttachment;
import com.pmcc.soft.week.domain.SystemAttachmentFolderRela;
import com.pmcc.soft.week.domain.SystemFolder;
import com.pmcc.soft.week.domain.SystemLog;
import com.pmcc.soft.week.manager.SystemAttachmentFolderRelaManager;
import com.pmcc.soft.week.manager.SystemAttachmentManager;
import com.pmcc.soft.week.manager.SystemFolderManager;
import com.pmcc.soft.week.manager.SystemLogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

/**
 * 手机云盘上传
 * Created by LvXL on 2016/5/10.
 */
@Controller
@RequestMapping("/cloudDisk")
public class SystemCloudDiskRestController {

    @Autowired
    SystemFolderManager systemFolderManager;

    @Autowired
    SystemLogManager systemLogManager;

    @Autowired
    SystemAttachmentFolderRelaManager systemAttachmentFolderRelaManager;

    @Autowired
    SystemAttachmentManager systemAttachmentManager;

    @Autowired
    PersonInfoManageManager personInfoManageManager;

    @Autowired
    OrganizationInfoManager organizationInfoManager;

    /**
     * 上传附件：保存附件表、附件文件夹关系表、日志表，修改文件夹表
     *
     * @param fileToUpload
     * @param request
     * @param response
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(
            @RequestParam(value = "fileToUpload", required = false) MultipartFile[] fileToUpload,
            HttpServletRequest request, HttpServletResponse response) throws IOException {

        String res = "{\"success\":\"false\"}";
        // 手机端上传参数
        String personId = request.getParameter("personId");// 上传人id
        String businessModel = request.getParameter("businessModel");//业务模块
        String businessType = request.getParameter("businessType");//业务类型
        String folderId = request.getParameter("folderId");//文件夹ID
        // 日志记录
        String ids = "";
        // 文件大小
        long fileSize = 0;
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
        // 循环上传
        for (int i = 0; i < fileToUpload.length; i++) {
            String[] fileType = fileToUpload[i].getOriginalFilename().split("\\.");

            DateUtil du = new DateUtil();
            // 附件实体
            SystemAttachment satt = new SystemAttachment();
            String uuid = UUIDGenerator.getUUID();
            satt.setId(uuid);
            // 记录表存放附件表主键
            ids += uuid + ",";

            String kk = new String(fileToUpload[i].getOriginalFilename());
            String[] km = kk.split("\\.");
            String extend = km.length > 1 ? km[km.length - 1] : "";
            // 存放在服务器端的文件名
            String fmn = extend == "" ? UUIDGenerator.getUUID() : UUIDGenerator.getUUID() + "." + extend;
            // 年月日文件夹
            String mm = du.DateToString(new Timestamp(new Date().getTime()), "yyyy-MM-dd");
            // 存放路径：年-月-日/上传人中文名/文件名
            String filePath = mm + "/" + pm.getUserEname() + "/" + fmn;
            // 服务器存放附件文件夹
            String path = SystemParamsUtil.getProperty("appCloudDiskUploadPath");
            satt.setBusinessModel(businessModel);
            satt.setBusinessType(businessType);
            satt.setOrgId(pm.getCompanyId());
            satt.setOrgCode(org.getOrgCode());
            satt.setOrgName(org.getOrgCname());
            satt.setFileUrl("/" + filePath);
            satt.setFileName(fileToUpload[i].getOriginalFilename());
            satt.setFileSize(fileToUpload[i].getSize());
            satt.setFileMathName(fmn);
            satt.setFileType(extend);
            satt.setDownloadCount(0);
            satt.setRptDate(new Date());
            satt.setDelFlag(0);
            satt.setRptPerson(personId);
            satt.setRptPersonName(userName);
            systemAttachmentManager.insert(satt);
            // 文件大小
            fileSize += fileToUpload[i].getSize();

            File targetFile = new File(path, filePath);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            // 上传文件
            try {
                fileToUpload[i].transferTo(targetFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 文件夹与附件关系表
            this.saveRela(satt, pm, request);
        }
        // 操作记录
        int i = this.saveSystemLog(personId, ids, folderId, request);
        // 更新文件夹表已用容量
        int j = this.updateFolder(fileSize, request);
        if ((i + j) == 2) {
            res = "{\"success\":\"true\"}";
        }
        return res;
    }

    /**
     * 保存上传信息
     *
     * @param personId
     * @param attachmentIds
     * @param folderId
     * @param request
     * @return
     */
    @RequestMapping(value = "/saveUploadInfo", method = RequestMethod.POST)
    @ResponseBody
    public String saveUploadInfo(String personId, String attachmentIds, String folderId, HttpServletRequest request) {

        String res = "{\"success\":\"false\"}";
        // 操作记录
        int i = this.saveSystemLog(personId, attachmentIds, folderId, request);
        res = "{\"success\":\"true\"}";
        return res;
    }

    /**
     * 循环上传文件
     *
     * @param fileToUpload
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(
            @RequestParam(value = "fileToUpload", required = false) MultipartFile fileToUpload,
            HttpServletRequest request, HttpServletResponse response) throws IOException {

        String res = "{\"success\":\"false\"}";

        // 手机端上传参数
        String personId = request.getParameter("personId");// 上传人id
        String businessModel = request.getParameter("businessModel");//业务模块
        String businessType = request.getParameter("businessType");//业务类型
        // 文件大小
        long fileSize = 0;
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

        // 附件实体
        SystemAttachment satt = new SystemAttachment();
        String uuid = UUIDGenerator.getUUID();
        satt.setId(uuid);

        String kk = new String(fileToUpload.getOriginalFilename());
        String[] km = kk.split("\\.");
        String extend = km.length > 1 ? km[km.length - 1] : "";
        // 存放在服务器端的文件名
        String fmn = extend == "" ? UUIDGenerator.getUUID() : UUIDGenerator.getUUID() + "." + extend;
        // 年月日文件夹
        String mm = DateUtil.DateToString(new Timestamp(new Date().getTime()), "yyyy-MM-dd");
        // 存放路径：年-月-日/上传人中文名/文件名
        String filePath = mm + "/" + pm.getUserEname() + "/" + fmn;
        // 服务器存放附件文件夹
        String path = SystemParamsUtil.getProperty("appCloudDiskUploadPath");
        satt.setBusinessModel(businessModel);
        satt.setBusinessType(businessType);
        satt.setOrgId(pm.getCompanyId());
        satt.setOrgCode(org.getOrgCode());
        satt.setOrgName(org.getOrgCname());
        satt.setFileUrl("/" + filePath);
        satt.setFileName(fileToUpload.getOriginalFilename());
        satt.setFileSize(fileToUpload.getSize());
        satt.setFileMathName(fmn);
        satt.setFileType(extend);
        satt.setDownloadCount(0);
        satt.setRptDate(new Date());
        satt.setDelFlag(0);
        satt.setRptPerson(personId);
        satt.setRptPersonName(userName);
        systemAttachmentManager.insert(satt);

        // 文件大小
        fileSize += fileToUpload.getSize();

        File targetFile = new File(path, filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        // 上传文件
        try {
            fileToUpload.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 文件夹与附件关系表
        int i = this.saveRela(satt, pm, request);

        // 更新文件夹表已用容量
        int j = this.updateFolder(fileSize, request);
        res = "{\"success\":\"true\",\"attachmentIds\":\"" + uuid + "\"}";
        return res;
    }

    /**
     * 保存操作记录
     *
     * @param personId
     * @param fileId
     * @param request
     * @return
     */
    public int saveSystemLog(String personId, String fileId, String folderId, HttpServletRequest request) {

        String operateDesc = request.getParameter("operateDesc");//操作描述
        String mac = request.getParameter("macAddress");//MAC地址
        String imei = request.getParameter("imei");//手机串号
        String businessModel = request.getParameter("businessModel");//业务模块
        String businessType = request.getParameter("businessType");//业务类型
        String source = request.getParameter("source");//来源（0.pc；1.iOS；2.android）
        SystemFolder systemFolder = systemFolderManager.get(folderId);
        String folderType = systemFolder == null ? "" : systemFolder.getFolderType();//文件夹类型
        String folderCode = systemFolder == null ? "" : systemFolder.getFolderCode();//文件夹类型编码

        PersonInfoManage personInfoManage = new PersonInfoManage();
        personInfoManage.setId(personId);
        PersonInfoManage person = personInfoManageManager.queryByOid(personInfoManage);
        OrganizationInfo org = new OrganizationInfo();
        List<OrganizationInfo> orgList = organizationInfoManager.queryOrgByPersonId(personId);
        if (orgList != null && orgList.size() > 0) {
            org = orgList.get(0);
        }

        SystemLog log = CommonUtils.getRequestInfo(request);
        log.setMacAddress(mac);
        log.setImei(imei);
        log.setOperateDesc(operateDesc);
        log.setBusinessType(businessType);
        log.setBusinessModel(businessModel);
        log.setSource(source);
        log.setRptDate(new Date());
        log.setRptPerson(person.getId());
        log.setRptPersonName(person.getUserCname());
        log.setOrgId(org.getId());
        log.setOrgCode(org.getOrgCode());
        log.setCompanyId(person.getCompanyId());
        log.setIds(fileId);
        log.setFolderId(folderId);
        log.setFolderType(folderType);
        log.setFolderCode(folderCode);

        return systemLogManager.save(log);
    }

    /**
     * 保存文件夹与附件关系表
     *
     * @param attachment
     * @param person
     * @param request
     * @return
     */
    public int saveRela(SystemAttachment attachment, PersonInfoManage person, HttpServletRequest request) {

        SystemAttachmentFolderRela rela = new SystemAttachmentFolderRela();
        String folderId = request.getParameter("folderId");//文件夹ID
        String folderName = request.getParameter("folderName");//文件夹名称

        rela.setId(UUIDGenerator.getUUID());
        rela.setAttachmentId(attachment.getId());
        rela.setFileName(attachment.getFileName());
        rela.setFileSize(attachment.getFileSize());
        rela.setFileType(attachment.getFileType());
        rela.setFolderId(folderId);
        rela.setFolderName(folderName);
        rela.setRptPerson(person.getId());
        rela.setRptPersonName(person.getUserCname());
        rela.setRptDate(new Date());
        rela.setDelFlag("0");

        return systemAttachmentFolderRelaManager.save(rela);
    }

    /**
     * 更新已用容量
     *
     * @param fileSize
     * @param request
     */
    public int updateFolder(long fileSize, HttpServletRequest request) {

        String folderId = request.getParameter("folderId");//文件夹ID
        SystemFolder folder = systemFolderManager.get(folderId);
        if (folder != null) {
            folder.setFolderUsedSize(folder.getFolderUsedSize() + fileSize);
        }
        return systemFolderManager.update(folder);
    }

    /**
     * 查询文件夹列表
     *
     * @param personId
     * @param folderType 0：公共；1：个人
     * @return
     */
    @RequestMapping(value = "/queryFolder", method = RequestMethod.GET)
    @ResponseBody
    public List<SystemFolder> queryFolder(String personId, String folderType) {

        return systemFolderManager.queryFolder(personId, folderType);
    }

    /**
     * 查询文件夹中的文件
     * <p/>
     * personId 操作人id
     * folderId 文件夹id
     * orderFlag 排序方式：0：按时间；1：按名称
     * type 文件筛选类型 所有 -1，文档 0，压缩包 1，图片 2， 视频 3， 音频 4，应用 5，其他 6
     * page 页码
     * iDisplayLength 每页条数
     *
     * @return
     */
    @JsonView(View.RestView.class)
    @RequestMapping(value = "/queryFile", method = RequestMethod.GET)
    @ResponseBody
    public List<SystemAttachment> queryFile(String personId, String folderId, String folderType, String orderFlag, String fileName, String type, int page, int iDisplayLength) {

        int iDisplayStart = (page - 1) * iDisplayLength + 1;
        SystemAttachment systemAttachment = new SystemAttachment();
        if(CommonVariables.PRIVATE_FOLDER_TYPE.equals(folderType)){
            // 个人文件夹，只查询自己上传的
            systemAttachment.setPersonId(personId);
        }
        systemAttachment.setFolderId(folderId);
        systemAttachment.setOrdFlag(orderFlag == null ? "0" : orderFlag);
        systemAttachment.setFileName(fileName);
        systemAttachment.setPage(page);
        systemAttachment.setiDisplayLength(iDisplayLength);
        systemAttachment.setiDisplayStart(iDisplayStart);
        systemAttachment.setInitPage(0);
        // 文件筛选
        systemAttachment.setType(type);
        if(CommonVariables.TYPE_ALL.equals(type)){
            // 所有
            systemAttachment.setType(null);
        }else if(CommonVariables.TYPE_DOCUMENT.equals(type)){
            // 文档
            systemAttachment.setTypeList(SystemParamsUtil.getFileType("type_document"));
        }else if(CommonVariables.TYPE_ZIP.equals(type)){
            // 压缩包
            systemAttachment.setTypeList(SystemParamsUtil.getFileType("type_zip"));
        }else if(CommonVariables.TYPE_PIC.equals(type)){
            // 图片
            systemAttachment.setTypeList(SystemParamsUtil.getFileType("type_pic"));
        }else if(CommonVariables.TYPE_VIDEO.equals(type)){
            // 视频
            systemAttachment.setTypeList(SystemParamsUtil.getFileType("type_video"));
        }else if(CommonVariables.TYPE_AUDIO.equals(type)){
            // 音频
            systemAttachment.setTypeList(SystemParamsUtil.getFileType("type_audio"));
        }else if(CommonVariables.TYPE_APP.equals(type)){
            // 应用
            systemAttachment.setTypeList(SystemParamsUtil.getFileType("type_app"));
        }else if(CommonVariables.TYPE_OTHER.equals(type)){
            // 其他
            systemAttachment.setTypeList(SystemParamsUtil.getFileTypeAll());
        }
        // 分页数据
        List<SystemAttachment> list = systemAttachmentManager.queryFile(systemAttachment);
        // 数据总数
        systemAttachment.setInitPage(1);
        List<SystemAttachment> totalList = systemAttachmentManager.queryFile(systemAttachment);
        int total = totalList == null ? 0 : totalList.size();

        if (list != null && list.size() > 0) {
            for (SystemAttachment attachment : list) {
                attachment.setFileUrl("/appCloudDiskUploadPath" + attachment.getFileUrl());
                attachment.setTotal(total);
            }
        }
        return list;
    }

    /**
     * 获取文件动态
     *
     * @param personId
     * @return
     */
    @JsonView(View.RestView.class)
    @RequestMapping(value = "/queryFileDynamics", method = RequestMethod.GET)
    @ResponseBody
    public List<FileDynamics> queryFileDynamics(String personId, int iDisplayLength, int page) {

        List<FileDynamics> resList = new ArrayList<FileDynamics>();
        int iDisplayStart = (page - 1) * iDisplayLength + 1;
        SystemLog log = new SystemLog();
        log.setRptPerson(personId);
        log.setPage(page);
        log.setiDisplayLength(iDisplayLength);
        log.setiDisplayStart(iDisplayStart);// 与H5前台通用
        log.setInitPage(0);// 加分页
        // 分页数据
        List<SystemLog> list = systemLogManager.query(log);
        // 总数
        log.setInitPage(1);
        List<SystemLog> totalList = systemLogManager.query(log);
        int total = totalList == null ? 0 : totalList.size();
        FileDynamics fd = null;
        if (list != null && list.size() > 0) {
            for (SystemLog systemLog : list) {
                fd = new FileDynamics();
                fd.setTotal(total);
                fd.setRptDate(systemLog.getRptDate());
                fd.setOperateDesc(systemLog.getOperateDesc());
                fd.setRptPersonId(systemLog.getRptPerson());
                fd.setRptPersonName(systemLog.getRptPersonName());
                String ids = systemLog.getIds();
                if (ids != null) {
                    List<String> idList = new ArrayList<String>();
                    String[] arr = ids.split(",");
                    if (arr != null && arr.length > 0) {
                        for (String s : arr) {
                            idList.add(s);
                        }
                    }
                    SystemAttachment attachment = new SystemAttachment();
                    attachment.setIds(idList);
                    List<SystemAttachment> attachments = systemAttachmentManager.query(attachment);
                    if (attachments != null && attachments.size() > 0) {
                        for (SystemAttachment systemAttachment : attachments) {
                            systemAttachment.setFileUrl("/appCloudDiskUploadPath" + systemAttachment.getFileUrl());
                        }
                    }
                    fd.setAttachments(attachments);
                }
                resList.add(fd);
            }
        }
        return resList;
    }

    /**
     * 文件重命名：修改附件表、附件文件夹关系表
     *
     * @param fileId
     * @param newName
     * @return
     */
    @RequestMapping(value = "/reName", method = RequestMethod.POST)
    @ResponseBody
    public String reName(String fileId, String newName, String fileType, String folderId, HttpServletRequest request) {

        String res = "{\"success\":\"false\"}";
        // 修改附件表文件名
        SystemAttachment att = new SystemAttachment();
        att.setId(fileId);
        att.setFileName(newName + "." + fileType);
        int i = systemAttachmentManager.update(att);
        // 修改附件文件夹关系表
        SystemAttachmentFolderRela rela = new SystemAttachmentFolderRela();
        rela.setAttachmentId(fileId);
        rela.setFileName(newName + "." + fileType);
        int j = systemAttachmentFolderRelaManager.update(rela);

        // 保存日志记录
        this.saveSystemLog(request.getParameter("personId"), fileId, folderId, request);
        if ((i + j) == 2) {
            res = "{\"success\":\"true\"}";
        }
        return res;
    }

    /**
     * 文件删除(逻辑删除)：删除附件表、删除文件夹关系表、记录操作日志
     *
     * @param fileId
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(String fileId, String personId, String folderId, HttpServletRequest request) {

        String res = "{\"success\":\"false\"}";
        // 删除附件表文件名
        SystemAttachment att = new SystemAttachment();
        att.setId(fileId);
        att.setDelFlag(1);
        int i = systemAttachmentManager.update(att);
        // 删除附件文件夹关系表
        SystemAttachmentFolderRela rela = new SystemAttachmentFolderRela();
        rela.setAttachmentId(fileId);
        rela.setDelFlag("1");
        int j = systemAttachmentFolderRelaManager.update(rela);
        // 更新文件夹大小
        SystemAttachment systemAttachment = new SystemAttachment();
        systemAttachment.setId(fileId);
        systemAttachment = systemAttachmentManager.queryById(systemAttachment);
        int n = this.updateFolder(0-systemAttachment.getFileSize(), request);
        // 保存日志
        int resLog = this.saveSystemLog(personId, fileId, folderId, request);

        if ((i + j) == 2) {
            res = "{\"success\":\"true\"}";
        }
        return res;
    }

}
