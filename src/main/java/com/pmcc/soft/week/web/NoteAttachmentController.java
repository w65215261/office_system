package com.pmcc.soft.week.web;

import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.utils.DateUtil;
import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.domain.NoteAttachment;
import com.pmcc.soft.week.domain.TaskAttachment;
import com.pmcc.soft.week.manager.NoteAttachmentManager;
import org.apache.commons.io.FileUtils;
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
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangbin on 16/3/25.
 */
@Controller
@RequestMapping("noteAttachment")
public class NoteAttachmentController {
    @Autowired
    NoteAttachmentManager noteAttachmentManager;

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
        NoteAttachment att = new NoteAttachment();
        // 合同子表主键值
        String uuid = request.getParameter("uuid");
        att.setNoteOid(uuid);
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
        String path = path = request.getSession().getServletContext().getRealPath("/fileNote");
        String rptPerson = loginUser.getUserCname();//发布人
        att.setFileUrl("fileNote/" + fileName);
        att.setFileName(fileToUpload[0].getOriginalFilename());
        att.setFileMathName(fmn);
        att.setCreateDate(new Date());
        att.setDelFlag("0");
        att.setRptPerson(rptPerson);
        noteAttachmentManager.insert(att);

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
    public List<NoteAttachment> read(NoteAttachment ma){
        List<NoteAttachment> list = noteAttachmentManager.findNoteAttachmentByNoteOid(ma);
        return list;
    }


    @RequestMapping(value = "/downAttachment", method = RequestMethod.GET)
    public void download(HttpServletResponse res,NoteAttachment ma) throws IOException {
        OutputStream os = res.getOutputStream();
        String path = "D:\\work\\cloudDesktop\\classes\\artifacts\\cloudDesktop_war_exploded\\"+ma.getFileUrl();
        File file=new File(path);
        try {
            res.reset();
            res.setContentType("application/octet-stream");
            res.setHeader("Content-Disposition", "attachment; filename=\"" + ma.getFileName() + "\";");
            os.write(FileUtils.readFileToByteArray(file));
            os.flush();
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }

    /**
     * 删除附件
     * @param ma
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteAttachment", method = RequestMethod.POST)
    @ResponseBody
    public String deleteAttachment(NoteAttachment ma)throws Exception{
        int res = 0;
        int i = noteAttachmentManager.deleteAttachment(ma);
        if (res == i) {
            return "fail";
        } else {

            return "success";
        }
    }
}
