package com.boy.springbootallmail.controller;

import com.boy.springbootallmail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

/**
 * created by Joshua.H.Brooks on 2020.6月.04.18.30
 */
@RestController
public class MailController {

    @Autowired
    MailService mailService;
    @GetMapping("simple")
    public String simple(){
        mailService.sendSimpleMail();
        return "simple mailed, pls check !";
    }

    @GetMapping("attachment")
    public String Attachment() throws MessagingException {
        mailService.sendAttachFileMail();
        return "attachement mailed, pls check !";
    }

    @GetMapping("ImgMail")
    public String ImgMail() throws MessagingException {
        mailService.sendImgResMail();
        return "Image Resource mailed, pls check !";
    }

    @GetMapping("ThymeleafMail")
    public String ThymeleafMail() throws MessagingException {
        mailService.sendThymeleafMail();
        return "Thymeleaf Mail was sent, pls check !";
    }



}
