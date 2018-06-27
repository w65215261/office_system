package com.pmcc.soft.week.web;

/**
 * Created by sunyake on 15/8/11.
 */

import com.pmcc.soft.core.utils.DateUtil;
import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.domain.MessageAttachment;
import com.pmcc.soft.week.manager.MessageAttachmentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by sunyake on 15/7/2.
 */
@Controller
@RequestMapping("messageAttachment")
public class MessageAttachmentController {
    @Autowired
    MessageAttachmentManager messageAttachmentManager;







    /**
     * 列表查询
     * @param att
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> query(MessageAttachment att) {

        Map<String, Object> res = new HashMap<String, Object>();
        List<MessageAttachment> list = new ArrayList<MessageAttachment>();
        att.setInitPage(0);
        if(att.getMessageId() != null){

            list = messageAttachmentManager.query(att);
        }

        res.put("rows", list);
        att.setInitPage(1);
        res.put("total", list.size());
        return res;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void upload(
            @RequestParam(value = "fileToUpload", required = false) MultipartFile[] fileToUpload,
            HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, Object> res = new HashMap<String, Object>();

        DateUtil du = new DateUtil();
        // 附加实体
        MessageAttachment att = new MessageAttachment();
        // 合同子表主键值
        String uuid = request.getParameter("uuid");

        if(uuid == null || "".equals(uuid)){
            uuid = UUIDGenerator.getUUID();
        }
        System.out.println(fileToUpload);
        att.setMessageId(uuid);

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
        String path = path = request.getSession().getServletContext().getRealPath("/fileMessage");
        att.setFileUrl("fileMessage/" + fileName);
        att.setFileName(fileToUpload[0].getOriginalFilename());
        att.setFileMathName(fmn);
        att.setCreateDate(new Date());
        att.setDelFlag("0");
        messageAttachmentManager.insert(att);

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
     * 删除方法
     * @param att
     * @return
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void delete(MessageAttachment att, HttpServletResponse response)throws Exception{

        messageAttachmentManager.delete(att.getId());
        response.getWriter().write("1");
        response.getWriter().flush();

    }
}
