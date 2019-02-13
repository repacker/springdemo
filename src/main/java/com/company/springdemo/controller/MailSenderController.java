package com.company.springdemo.controller;

import com.company.springdemo.common.utils.PropertiesConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @Auther: whs
 * @Date: 2019/2/11 16:07
 * @Description:
 */
@RestController
public class MailSenderController {

    @Autowired
    private JavaMailSender javaMailSender;

    /** 
     * @Description: 该方法发送简单邮件
     * @Param: 
     * @return:
     */ 
    @RequestMapping("/mail")
    public String sendMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        //发件人
        message.setFrom(PropertiesConstant.sender);
        //收件人
        message.setTo(PropertiesConstant.receiver);
        message.setSubject("邮件标题");
        message.setText("邮件内容");
        javaMailSender.send(message);
        return "发送成功";
    }

    /** 
     * @Description: 该方法发送含附件的邮件 
     * @Param: 
     * @return:
     */ 
    @RequestMapping("/mailWithFile")
    public String sendMailWithFile() throws MessagingException {
        //需要创建一个MimeMessageHelper对象，相关参数和简单邮件类似
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(PropertiesConstant.sender);
        helper.setTo(PropertiesConstant.receiver);
        helper.setSubject("邮件标题");
        //将邮件内容设置为html格式
        helper.setText("<html><body><h1>hello world</h1></body></html>", true);
        //定义文件，这是java.main.resources也就是classpach路径下的文件abc.png
        ClassPathResource file = new ClassPathResource("/abc.png");
        //添加附件文件， 设置文件名为abc.png
        helper.addAttachment("abc.png", file);
        javaMailSender.send(mimeMessage);
        return "发送成功";
    }

}
