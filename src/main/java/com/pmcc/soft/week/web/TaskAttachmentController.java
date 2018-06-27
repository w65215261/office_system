package com.pmcc.soft.week.web;

/**
 * Created by sunyake on 15/8/11.
 */

import com.pmcc.soft.core.common.OnlineUser;
import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.utils.AppUtils;
import com.pmcc.soft.core.utils.DateUtil;
import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.domain.TaskAttachment;
import com.pmcc.soft.week.manager.TaskAttachmentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

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
 * Created by sunyake on 15/11/25.
 */
@Controller
@RequestMapping("taskAttachment")
public class TaskAttachmentController {
    @Autowired
    TaskAttachmentManager taskAttachmentManager;

    /**
     * 上传附件
     * @param fileToUpload
     * @param request
     * @param response
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void upload(
            @RequestParam(value = "fileToUpload", required = false) MultipartFile[] fileToUpload,
            HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
        PersonManage loginUser = (PersonManage)session.getAttribute("loginUser");
        Map<String, Object> res = new HashMap<String, Object>();

        DateUtil du = new DateUtil();
        // 附件实体
        TaskAttachment att = new TaskAttachment();
        // 合同子表主键值
        String uuid = request.getParameter("uuid");
        String projectOid = request.getParameter("projectOid");
        String flag = "";
        String taskOid = "";
        String[] array = uuid.split(",");
        if(array.length>2){
            uuid = array[0];
            flag = array[1];
            taskOid = array[2];
        }

        if(uuid == null || "".equals(uuid)){
            uuid = UUIDGenerator.getUUID();
        }
        if(flag.equals("4")){
            att.setTaskOid(taskOid);
            att.setWorkHourOid(uuid);
        }else if(flag.equals("5")){
            att.setTaskOid(taskOid);
            att.setExperienceOid(uuid);
        }else{
            att.setTaskOid(uuid);
        }

        String kk = new String(fileToUpload[0].getOriginalFilename());
        String[] km = kk.split("\\.");
        kk = km[km.length - 1];
        // 上传到服务器文件名称
        String fname = UUIDGenerator.getUUID();
        String fmn = fname + "." + kk;
        String mm = du.DateToString(new Timestamp(new Date().getTime()),
                "yyyy-MM-dd");
        String fileName = mm + "/" + fmn;
        // 服务器存放附件文件夹
        String path = path = request.getSession().getServletContext().getRealPath("/fileTask");
        String rptPerson = loginUser.getUserCname();//发布人
        att.setFileUrl("fileTask/" + fileName);
        att.setFileName(fileToUpload[0].getOriginalFilename());
        att.setFileMathName(fmn);
        att.setCreateDate(new Date());
        att.setDelFlag("0");
        att.setProjectOid(projectOid);
        att.setRptPerson(rptPerson);
        taskAttachmentManager.insert(att);

        File targetFile = new File(path, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        // 保存
        try {
            fileToUpload[0].transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        res.put("uuid", uuid);

        response.getWriter().write(uuid);
        response.getWriter().flush();
    }

    /**
     * 读取附件
     * @param ma
     * @return
     */
    @RequestMapping(value="read", method=RequestMethod.GET)
    @ResponseBody
    public List<TaskAttachment> read(TaskAttachment ma){
        if(ma.getWorkHourOid() !=null && !ma.getWorkHourOid().equals("")){
            List<TaskAttachment> list = taskAttachmentManager.findWorkHourAttachmentByTaskOid(ma);
            if(list.size()>0){
                list.get(0).setAttachmentReadFlag(ma.getAttachmentReadFlag());
            }
            return list;
        }
        if(ma.getExperienceOid() !=null && !ma.getExperienceOid().equals("")){
            List<TaskAttachment> list = taskAttachmentManager.findExperienceAttachmentByTaskOid(ma);
            if(list.size()>0){
                list.get(0).setAttachmentReadFlag(ma.getAttachmentReadFlag());
            }
            return list;
        }
        List<TaskAttachment> list = taskAttachmentManager.findTaskAttachmentByTaskOid(ma);
        return list;
    }

    /**
     * 删除附件
     * @param ma
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteAttachment", method = RequestMethod.POST)
    @ResponseBody
    public String deleteAttachment(TaskAttachment ma)throws Exception{
        int res = 0;
        int i = taskAttachmentManager.deleteAttachment(ma);
        if (res == i) {
            return "fail";
        } else {

            return "success";
        }
    }
}
