package com.sjq.edu.controller;


import com.sjq.commonutils.result.Result;
import com.sjq.edu.entity.EduVideo;
import com.sjq.edu.service.IEduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author kemp
 * @since 2022-04-02
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    private IEduVideoService eduVideoService;

    //添加
    @PostMapping("addVideo")
    public Result addVideo(@RequestBody EduVideo video){
        video.setGmtCreate(LocalDateTime.now());
        video.setGmtModified(LocalDateTime.now());
        boolean save = eduVideoService.save(video);
        if(save){
            return Result.ok();
        }
        return Result.fail();
    }

    //删除
    @DeleteMapping("deleteVideo/{id}")
    public Result deleteVideo(@PathVariable String id){
        boolean b = eduVideoService.removeById(id);
        if(b){
            return Result.ok();
        }
        return Result.fail();
    }

    //修改

}
