package com.sjq.fdfs.store.controller;

import com.sjq.commonutils.result.Result;
import com.sjq.fdfs.store.entity.DleVideoVo;
import com.sjq.fdfs.store.service.FdfsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Map;


/**
 * @Author Kemp
 * @create 2022/1/24 9:56
 */
@RestController
@RequestMapping("/edufdfs")
//@CrossOrigin
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
        Map<String,String> s = fdfsService.uploadFileVideo(file);
        if(s == null){
            return Result.fail();
        }
        return Result.ok(s);
    }

    @PostMapping("/deleteFile")
    public Result deleteFile(@RequestBody DleVideoVo grouppath){
        fdfsService.deleteFileByGrouppath(grouppath);
        return Result.ok();
    }

    @PostMapping("deleteBath")
    public Result deleteBath(@RequestParam("pathList") List<String> pathList){
        fdfsService.deleteBath(pathList);
        return Result.ok();
    }
}
