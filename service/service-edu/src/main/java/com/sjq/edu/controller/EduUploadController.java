package com.sjq.edu.controller;


import com.sjq.commonutils.result.Result;
import com.sjq.edu.entity.EduUpload;
import com.sjq.edu.service.IEduUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kemp
 * @since 2022-04-09
 */
@RestController
@RequestMapping("/eduservice/eduupload")
//@CrossOrigin
public class EduUploadController {

    @Autowired
    private IEduUploadService eduUploadService;

    @PostMapping("saveuploadVo")
    public Result saveuploadVo(@RequestBody EduUpload eduUpload){
        String uploadid = eduUploadService.saveUploadVo(eduUpload);
        if(uploadid == null) return Result.fail("上传失败");
        return Result.ok(uploadid);
    }

    @DeleteMapping("deleteuploadVo/{id}")
    public Result deleteuploadVo(@PathVariable String id){
        boolean b = eduUploadService.removeById(id);
        if (!b){
            return Result.fail();
        }
        return Result.ok();
    }
}
