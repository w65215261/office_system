package com.pmcc.soft.core.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
@Service
public class SendMailUtil {
	/*@Resource
    private JavaMailSenderImpl javaMailSender;
	@Resource
    private SimpleMailMessage simpleMailMessage;
	@Resource
    private TaskExecutor taskExecutor;*/
    /**
     * 构建邮件内容，发送邮件。
     * @param user  用户
     * @param url   链接
     */
    public void send(String email,String text) {
       /* String to = email;
        this.taskExecutor.execute(new SendMailThread(to,null,text));*/
    }
    //    内部线程类，利用线程池异步发邮件。
    private class SendMailThread implements Runnable {
        private String to;
        private String subject;
        private String content;
        private SendMailThread(String to, String subject, String content) {
            super();
            this.to = to;
            this.subject = subject;
            this.content = content;
        }
        @Override
        public void run() {
            sendMail(to, subject, content);
        }
    }
    /**
     * 发送邮件
     * @param to        收件人邮箱
     * @param subject   邮件主题
     * @param content   邮件内容
     */
    public void sendMail(String to, String subject, String content) {
        /*try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
            messageHelper.setFrom(simpleMailMessage.getFrom());

            if (subject != null) {
                messageHelper.setSubject(subject);
            } else {
                messageHelper.setSubject(simpleMailMessage.getSubject());
            }
            messageHelper.setTo(to);
            messageHelper.setText(content, true);
            Properties prop = new Properties();
            prop.put("mail.smtp.auth", "true");
            javaMailSender.setJavaMailProperties(prop);
           javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }*/

    }
}
