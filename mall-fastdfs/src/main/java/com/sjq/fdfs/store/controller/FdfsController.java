package com.sjq.fdfs.store.controller;

import com.sjq.commonutils.result.Result;
import com.sjq.fdfs.store.service.FdfsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * @Author Kemp
 * @create 2022/1/24 9:56
 */
@RestController
@RequestMapping("/edufdfs")
@CrossOrigin
public class FdfsController {

    private static final Logger logger = LoggerFactory.getLogger(FdfsController.class);
    @Autowired
    private FdfsService fdfsService;

    @PostMapping("/uploadavatar")
    public Result uploadFileavatar(MultipartFile file){
        String s = fdfsService.uploadFileavatar(file);
        if(s == null){
            return Result.fail();
        }
        return Result.ok(s);
    }

    @PostMapping("/uploadvideo")
    public Result uploadFileVideo(MultipartFile file){
        String s = fdfsService.uploadFileVideo(file);
        if(s == null){
            return Result.fail();
        }
        return Result.ok(s);
    }



}
