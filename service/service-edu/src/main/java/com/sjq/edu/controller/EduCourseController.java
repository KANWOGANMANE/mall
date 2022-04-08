package com.sjq.edu.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sjq.commonutils.result.Result;
import com.sjq.commonutils.vo.CoursePublishVo;
import com.sjq.commonutils.vo.CourseVo;
import com.sjq.edu.entity.EduCourse;
import com.sjq.edu.entity.EduTeacher;
import com.sjq.edu.service.IEduCourseService;
import com.sjq.servicebase.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author kemp
 * @since 2022-04-02
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private IEduCourseService eduCourseService;

    @GetMapping("getCourseList")
    public Result getCourseList(){
        List<EduCourse> list = eduCourseService.list();
        if(list == null) return Result.fail();
        return Result.ok(list);
    }

    @PostMapping("addCourseInfo")
    public Result addCourseInfo(@RequestBody CourseVo courseVo){
        String id = eduCourseService.saveCourseInfo(courseVo);
        return Result.ok(id);
    }

    @GetMapping("getCourseInfo/{courseId}")
    public Result getCourseInfo(@PathVariable String courseId){
        CourseVo res = eduCourseService.getCourseInfo(courseId);
        if(res == null){
            return Result.fail("查询失败!");
        }
        return Result.ok(res);
    }

    @PostMapping("updateCourseInfo")
    public Result updateCourseInfo(@RequestBody CourseVo courseVo) {
        if (eduCourseService.updateCourseInfo(courseVo)) {
            return Result.ok();
        }
        return Result.fail("修改失败");
    }

    @GetMapping("getPublicshCourseInfo/{id}")
    public Result getPublicshCourseInfo(@PathVariable String id){
        CoursePublishVo res =  eduCourseService.publicshCourseInfo(id);
        if(res == null) return Result.fail("查询失败");
        return Result.ok(res);
    }

    @PostMapping("publishCourse/{id}")
    public Result publishCourse(@PathVariable String id){
        EduCourse course = new EduCourse();
        course.setGmtModified(LocalDateTime.now());
        course.setId(id);
        course.setStatus("Normal");
        boolean b = eduCourseService.updateById(course);
        return b ? Result.ok():Result.fail();
    }

    @PostMapping("pageCourseCondition/{current}/{limit}")
    public Result pageCourseCondition(@RequestBody(required = false) EduCourse eduCourse,
                                      @PathVariable("current") Long current,
                                      @PathVariable("limit") Long limit){
        IPage<EduCourse> page = new Page<>(current,limit);
        IPage iPage = eduCourseService.pageCourseCondition(page,eduCourse);
        if(iPage == null) return Result.fail();
        return Result.ok(page);
    }

    @DeleteMapping("deleteCourse/{cid}")
    public Result deleteCourse(@PathVariable String cid){
        if(eduCourseService.deleteCourse(cid)){
            return Result.ok();
        }
        return Result.fail();
    }

}
