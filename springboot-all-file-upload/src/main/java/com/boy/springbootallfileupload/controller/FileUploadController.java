package com.boy.springbootallfileupload.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-08-24 16:15
 */
@RestController //这里的路径就是业务模块的名称
@RequestMapping("/business")
public class FileUploadController {
    private final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
    static SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/"); //作为上传文件存储路径的一部分, 按日期分目录存储

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest req, Model model) {
        String rootPath = getUploadPath();
        String businessPath = FileUploadController.class.getAnnotation(RequestMapping.class).value()[0];
        String datePath = sdf.format(new Date());
        String path = rootPath+businessPath+datePath;
        File folder = new File(path);
        if(!folder.exists()){
            folder.mkdirs();
        }
        String originalFilename = file.getOriginalFilename();
        String destFileName = UUID.randomUUID().toString().replaceAll("-","")+originalFilename.substring(originalFilename.lastIndexOf("."));
        try{
            String destFullPath = folder + destFileName;
            file.transferTo(new File(destFullPath));
            //设置权限
            Set<PosixFilePermission> perms = new HashSet<>();
            perms.add(PosixFilePermission.OWNER_READ);
            perms.add(PosixFilePermission.OWNER_WRITE);
            perms.add(PosixFilePermission.GROUP_READ);
            perms.add(PosixFilePermission.OTHERS_READ);
            Files.setPosixFilePermissions(Paths.get(destFullPath), perms);
            String requestPath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+businessPath+datePath+destFileName;
            return requestPath;
        }catch (IOException e){
            e.printStackTrace();
        }
        return "";

    }

    @RequestMapping(value = "/uploads", method = RequestMethod.POST)
    public String uploads(@RequestParam("files") MultipartFile[] files, HttpServletRequest req, Model model) {

        return "";

    }
    @RequestMapping(value = "/uploads/separately", method = RequestMethod.POST)
    public String uploadsseparately(@RequestParam("file1") MultipartFile file1,@RequestParam("file1") MultipartFile file2,@RequestParam("file1") MultipartFile file3, HttpServletRequest req, Model model) {
        return "";
    }
    @GetMapping(  "/hello")
    public String hello() {
        System.out.println(1/0);
        return "";
    }

    /**
     * 获取当前系统路径
     */
    private String getUploadPath() {
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath().replace("target/classes/","src/main/resources/"));//默认是target/classes/
        } catch (FileNotFoundException e) {
            path = new File("");
        }
        File upload = new File(path.getAbsolutePath(), "static/imgs/");
        //File upload = new File(path.getAbsolutePath(), "webapp/uploads");
        if (!upload.exists()) upload.mkdirs();
        return upload.getAbsolutePath();
    }
}
