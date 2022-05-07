package com.sjq.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.sjq.commonutils.result.Result;
import com.sjq.commonutils.utils.JwtUtils;
import com.sjq.edu.entity.EduCourse;
import com.sjq.edu.entity.EduCourseCollect;
import com.sjq.edu.service.IEduCourseCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 课程收藏 前端控制器
 * </p>
 *
 * @author kemp
 * @since 2022-05-03
 */
@RestController
@RequestMapping("/eduservice/collect")
public class EduCourseCollectController {

    @Autowired
    private IEduCourseCollectService eduCourseCollectService;

    @PostMapping("course/{courseId}")
    public Result collectCourse(@PathVariable String courseId, HttpServletRequest request){
        String memberid = JwtUtils.getMemberIdByJwtToken(request);
        if(StringUtils.isNotBlank(memberid) && StringUtils.isNotBlank(courseId)){
            boolean b = eduCourseCollectService.saveCollection(courseId, memberid);
            return  b?Result.ok():Result.fail();
        }
        return Result.fail();
    }

    @GetMapping("getcollect/{courseId}")
    public Result getcollect(@PathVariable String courseId, HttpServletRequest request){
        String memberid = JwtUtils.getMemberIdByJwtToken(request);
        if(StringUtils.isNotBlank(memberid) && StringUtils.isNotBlank(courseId)){
            QueryWrapper<EduCourseCollect> collect = new QueryWrapper();
            collect.eq("course_id",courseId);
            collect.eq("member_id",memberid);
            EduCourseCollect one = eduCourseCollectService.getOne(collect);
            if(one != null){
                return Result.ok(one);
            }
        }
        return Result.fail();
    }


    @GetMapping("getUserAllcollection")
    public Result getUserAllcollection(HttpServletRequest request){
        String memberid = JwtUtils.getMemberIdByJwtToken(request);
        if(StringUtils.isNotBlank(memberid)){
            List<EduCourse> res = eduCourseCollectService.selectcollection(memberid);
            if(res != null)
                return Result.ok(res);
        }
        return Result.fail();
    }

}
