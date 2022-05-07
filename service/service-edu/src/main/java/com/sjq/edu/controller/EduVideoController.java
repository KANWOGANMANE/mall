package com.sjq.edu.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.sjq.commonutils.result.Result;
import com.sjq.commonutils.vo.DleVideoVo;
import com.sjq.commonutils.vo.OneChapter;
import com.sjq.edu.client.fdfsClient;
import com.sjq.edu.entity.EduUpload;
import com.sjq.edu.entity.EduVideo;
import com.sjq.edu.service.IEduCourseService;
import com.sjq.edu.service.IEduUploadService;
import com.sjq.edu.service.IEduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
//@CrossOrigin
public class EduVideoController {

    @Autowired
    private IEduVideoService eduVideoService;

    @Autowired
    private com.sjq.edu.client.fdfsClient fdfsClient;

    @Autowired
    private IEduUploadService eduUploadService;

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

    //删除小节要把视频也删除
    @DeleteMapping("deleteVideo/{id}")
    public Result deleteVideo(@PathVariable String id){
        EduVideo eduVideo = eduVideoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        if(StringUtils.isNotBlank(videoSourceId)){
            EduUpload eduUpload = eduUploadService.getById(videoSourceId);
            DleVideoVo v = new DleVideoVo(eduUpload.getFgroup(),eduUpload.getFpath());
            fdfsClient.deleteFile(v);
            eduUploadService.removeById(videoSourceId);
        }
        boolean b = eduVideoService.removeById(id);
        if(b){
            return Result.ok();
        }
        return Result.fail();
    }

    //根据chapter的视频id获取视频
    @GetMapping("getVideoByid/{id}")
    public Result getVideoByid(@PathVariable String id){
        Map<String,String> res = new HashMap<String,String>();
        EduVideo eduVideo = eduVideoService.getById(id);
        if(eduVideo != null){
            res.put("title",eduVideo.getTitle());
            res.put("sort",eduVideo.getSort().toString());
            res.put("isfree",eduVideo.getIsFree().toString());
            res.put("vsid",eduVideo.getVideoSourceId());
            res.put("cid",eduVideo.getChapterId());
        }
        String videoSourceId = eduVideo.getVideoSourceId();
        if(StringUtils.isNotBlank(videoSourceId)){
            EduUpload eduUpload = eduUploadService.getById(videoSourceId);
            if(eduUpload != null){
                res.put("fullpath",eduUpload.getFullpath());
                res.put("originalname",eduUpload.getFname());
            }
        }
        return Result.ok(res);
    }

    @GetMapping("getVidoToPlayByid/{id}")
    public Result getVidoToPlayByid(@PathVariable String id){
        if(StringUtils.isNotBlank(id)){
            EduUpload eduUpload = eduUploadService.getById(id);
            eduVideoService.viewCountadd(id);
            if(eduUpload != null){
                return Result.ok(eduUpload);
            }
        }
        return Result.fail();
    }

    @GetMapping("getCourseList/{id}")
    public Result getCourseList(@PathVariable String id){
        List<OneChapter> courseListByVid = eduVideoService.getCourseListByVid(id);
        if(courseListByVid == null) return Result.fail();
        return Result.ok(courseListByVid);
    }



}
