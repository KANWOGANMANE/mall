package com.sjq.edu.controller;



import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sjq.commonutils.result.Result;
import com.sjq.commonutils.utils.JwtUtils;
import com.sjq.commonutils.vo.*;
import com.sjq.edu.client.orderClient;
import com.sjq.edu.entity.EduCourse;
import com.sjq.edu.entity.EduTeacher;
import com.sjq.edu.service.IEduChapterService;
import com.sjq.edu.service.IEduCourseService;
import com.sjq.edu.service.IEduTeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
//@CrossOrigin
public class EduCourseController {

    @Autowired
    private IEduCourseService eduCourseService;

    @Autowired
    private IEduChapterService eduChapterService;

    @Autowired
    private IEduTeacherService eduTeacherService;

    @Autowired
    private orderClient orderClient;



    @GetMapping("getCourseList")
    public Result getCourseList(){
        List<EduCourse> list = eduCourseService.list();
        if(list == null) return Result.fail();
        return Result.ok(list);
    }

    @PostMapping("addCourseInfo")
    @CacheEvict(value = "hotcourse",allEntries = true)
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
    @CacheEvict(value = "hotcourse",allEntries = true)
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
    @CacheEvict(value = "hotcourse",allEntries = true)
    public Result publishCourse(@PathVariable String id,@RequestBody String status){
        EduCourse course = new EduCourse();
        course.setGmtModified(LocalDateTime.now());
        course.setId(id);
        String substring = status.substring(0, status.length()-1);
        course.setStatus(substring);
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
    @CacheEvict(value = "hotcourse",allEntries = true)
    public Result deleteCourse(@PathVariable String cid){
        if(eduCourseService.deleteCourse(cid)){
            return Result.ok();
        }
        return Result.fail();
    }

    //查询热门课程 前8个
    @GetMapping("hotcourse")
    public Result hotcourse(){
        List<EduCourse> res =  eduCourseService.getHotCourse();
        if(res == null) return Result.fail();
        return Result.ok(res);
    }

    @PostMapping("getFrontCourseList/{page}/{limit}")
    public Result getFrontCourseList(@PathVariable Long page,
                                     @PathVariable Long limit,
                                     @RequestBody(required = false) CourseFrontVo courseFrontVo){
        Page<EduCourse> pagecourse = new Page(page,limit);
        Map<String,Object> res =  eduCourseService.getCourseFontList(pagecourse,courseFrontVo);
        if(res == null) return Result.fail();
        return Result.ok(res);
    }

    @PostMapping("getCollectCourseList/{page}/{limit}")
    public Result getCollectCourseList(@PathVariable Long page,
                                       @PathVariable Long limit,
                                       @RequestBody(required = false) CourseFrontVo courseFrontVo){
        Page<EduCourse> pagecourse = new Page(page,limit);
        Map<String,Object> res =  eduCourseService.getCourseCollection(pagecourse,courseFrontVo);
        if(res == null) return Result.fail();
        return Result.ok(res);
    }

    @GetMapping("getFrontCourseInfo/{id}")
    public Result getFrontCourseInfo(@PathVariable String id, HttpServletRequest request){
        CourseWebVo webvo =  eduCourseService.getBaseCourseInfo(id);
        List<OneChapter> chapterAndVideo = eduChapterService.getChapterAndVideoBycid(id);
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        Boolean buyCourse = orderClient.isBuyCourse(id, memberId);
        Map<String,Object> res =  new HashMap<>();
        res.put("courseWebVo",webvo);
        res.put("chapterVideoList",chapterAndVideo);
        res.put("isBuy",buyCourse);
        return Result.ok(res);
    }

    @PostMapping("getCourseInfoOrder/{id}")
    public CourseWebVo getCourseInfoOrder(@PathVariable String id){
        EduCourse eduCourse = eduCourseService.getById(id);
        if(eduCourse == null){
            return null;
        }
        EduTeacher teacher = eduTeacherService.getById(eduCourse.getTeacherId());
        CourseWebVo courseWebVo = new CourseWebVo();
        if(teacher != null){
            courseWebVo.setTeacherName(teacher.getName());
        }
        BeanUtils.copyProperties(eduCourse,courseWebVo);
        return courseWebVo;
    }

    @GetMapping("recommend/{id}")
    public Result recommend(@PathVariable String id){
        EduCourse one = eduCourseService.getById(id);
        int recommend = one.getRecommend();
        if(recommend == 0){
            one.setRecommend(1);
        }else{
            one.setRecommend(0);
        }
        eduCourseService.updateById(one);
        return Result.ok();
    }

    @GetMapping("search/{condition}")
    public Result search(@PathVariable String condition){
        List<EduCourse> res = eduCourseService.searchcondition(condition);
        if(res != null && res.size() > 0){
            return Result.ok(res);
        }
        return Result.fail();
    }





}
