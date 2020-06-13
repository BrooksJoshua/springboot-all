package com.boy.springbootallmail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.io.File;
import java.util.Date;

/**
 * created by Joshua.H.Brooks on 2020.6月.04.18.30
 */
@Service
public class MailService {

    @Autowired
    JavaMailSender javaMailSender;


    @Autowired
    TemplateEngine templateEngine;

    public void sendSimpleMail() {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setSubject("这是一封测试邮件");
        message.setFrom("19925793244@163.com");
        message.setTo("19925793244@163.com");
       // message.setCc("37xxxxx37@qq.com");
        //message.setBcc("14xxxxx098@qq.com");
        message.setSentDate(new Date());
        message.setText("这是测试邮件的正文.......................");
        javaMailSender.send(message);
    }




    public void sendAttachFileMail() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        helper.setSubject("附件---测试邮件");
        helper.setFrom("19925793244@163.com");
        helper.setTo("williamleelindachong@foxmial.com");
        helper.setCc("2635887273@qq.com");
        //helper.setBcc("@qq.com");
        helper.setSentDate(new Date());
        helper.setText("这是测试附件---邮件的正文");
        //attachment.jpg
        helper.addAttachment("attachment.jpg", new File("D:\\MyData\\liwa2\\Desktop\\1.png"));
        javaMailSender.send(mimeMessage);
    }


    public void sendImgResMail() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("这是一封测试邮件");
        helper.setFrom("19925793244@163.com");
        helper.setTo("williamleelindachong@foxmial.com");
        //helper.setCc("2635887273@qq.com");
        helper.setBcc("2635887273@qq.com");
        helper.setSentDate(new Date());
        helper.setText("<p>hello 大家好，这是一封测试邮件，这封邮件包含两种图片，" +
                "分别如下</p><p>第一张图片：</p><img src='cid:p01'/><p>第二张图片：</p><img src='cid:p02'/>",true);
    helper.addInline("p01", new FileSystemResource(new File("D:\\MyData\\liwa2\\Desktop\\1.png")));
    helper.addInline(
        "p02", new FileSystemResource(new File("D:\\MyData\\liwa2\\Desktop\\1.png")));
        javaMailSender.send(mimeMessage);
    }


    public void sendThymeleafMail() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("这是一封测试邮件--使用thymeleaf模板");
        helper.setFrom("19925793244@163.com");

        helper.setTo("williamleelindachong@foxmial.com");
        //helper.setCc("2635887273@qq.com");
        helper.setBcc("2635887273@qq.com");
        helper.setSentDate(new Date());
        Context context =  new Context();
        context.setVariable("username", "javaboy");
        context.setVariable("num","000001");
        context.setVariable("salary", "99999");
        String process = templateEngine.process("mail.html", context);
        helper.setText(process,true);
        javaMailSender.send(mimeMessage);
    }




}
