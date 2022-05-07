package com.sjq.edu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sjq.edu.entity.EduCourse;
import com.sjq.edu.service.IEduCourseService;
import com.sjq.edu.service.IEduTeacherService;
import com.sjq.commonutils.result.Result;
import com.sjq.commonutils.vo.EduTeacherVo;
import com.sjq.edu.entity.EduTeacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Kemp
 * @since 2022-01-14
 */
@RestController
@RequestMapping("/eduservice")
//@CrossOrigin
public class EduTeacherController {

    private static final Logger logger = LoggerFactory.getLogger(EduTeacherController.class);

    @Autowired
    private IEduTeacherService iEduTeacherService;

    @Autowired
    private IEduCourseService iEduCourseService;

    @GetMapping("/findAllTeachers")
    public Result selAllTeacher(){
        List<EduTeacher> findall = iEduTeacherService.findAllTeachers();
        if(findall.size()>0 || !findall.isEmpty()) {
            return Result.ok(findall);
        }
        return Result.fail("查找所有教师信息失败!");
    }

    @PostMapping("/insertTeacher")
    public Result insertTeacher(@RequestBody EduTeacherVo vo){
        if(iEduTeacherService.insertTeacher(vo)){
            return Result.ok("插入成功！");
        }
        return Result.fail("插入失败!");
    }

    @DeleteMapping("/delTeacher/{id}")
    @CacheEvict(value = "hotteacher",allEntries = true)
    public Result delTeacher(@PathVariable("id") String id){
        boolean b = iEduTeacherService.removeById(id);
        if(b){
            return Result.ok();
        }else{
            return  Result.fail();
        }
    }

    /**
     * Mybatis-plus分页插件总结：
     * 1.Page实现Ipage接口
     * 2.创建Page的时候会调用父类构造方法，先把基类创建出来，
     * 3.通过Service.page(Page,QueryWarper)传入Page对象和QueryWarper会返回一个Ipage
     * 4.通过参数Page对象可以拿到返回结果的数据
     * 5.
     * @param current
     * @param limit
     * @return
     */
    @GetMapping("/pageTeachers/{current}/{limit}")
    public Result pageTeachers(@PathVariable("current")long current,
                               @PathVariable("limit")long limit){
        return Result.ok(iEduTeacherService.page(new Page<EduTeacher>(current,limit), null));
    }

    @PostMapping("/pageTeacherCondition/{current}/{limit}")
    public Result pageTeacherCondition(@PathVariable("current")long current,
                                       @PathVariable("limit")long limit,
                                       @RequestBody(required = false) EduTeacherVo eduTeacherVo){

        IPage<EduTeacher> page = new Page<>(current,limit);
        IPage iPage = iEduTeacherService.pageTeacherCondition(page, eduTeacherVo);

        return Result.ok(iPage);
    }

    @GetMapping("/findTeacher/{id}")
    public Result findTeacherById(@PathVariable("id") String id){
        EduTeacher res =  new EduTeacher();
        if(id!=null){
             res = iEduTeacherService.getById(id);
             if(res != null){
                 return Result.ok(res);
             }
             return Result.fail("查询失败");
        }
        return Result.fail("查询失败");
    }

    @PostMapping("/updateTeacher")
    @CacheEvict(value = "hotteacher",allEntries = true)
    public Result UpdateTeacher(@RequestBody EduTeacherVo vo){
        if(vo!=null){
            EduTeacher tacher = new EduTeacher();
            BeanUtils.copyProperties(vo,tacher);
            tacher.setGmtModified(LocalDateTime.now());
            if(tacher.getAvatar() == null || tacher.getAvatar().length()==0){
                tacher.setAvatar("http://192.168.72.138:8888/group1/M00/00/00/wKhIimJHz2OAc-mmAAAqXMYAv_428.webp");
            }
            boolean b = iEduTeacherService.updateById(tacher);
            if(b){
                return Result.ok("修改成功！");
            }
        }
        return Result.fail("修改失败!");
    }

    //查询热门讲师前4个
    @GetMapping("hotteacher")
    public Result hotteacher(){
        List<EduTeacher> res =  iEduTeacherService.getHotTeacher();
        if(res == null) return Result.fail();
        return Result.ok(res);
    }

    @PostMapping("getTeacherFrontList/{page}/{limit}")
    public Result getTeacherFrontList(@PathVariable Long page,@PathVariable Long limit){
        Page<EduTeacher> pageTeacher = new Page<>(page,limit);
        Map<String,Object> res = iEduTeacherService.getTeacherFrontList(pageTeacher);
        if(res == null) return Result.fail();
        return Result.ok(res);
    }

    @GetMapping("getTeacherFrontInfo/{id}")
    public Result getTeacherFrontInfo(@PathVariable String id){
        //查讲师信息
        EduTeacher teacher = iEduTeacherService.getById(id);
        //查讲师课程
        QueryWrapper<EduCourse> qw = new QueryWrapper<>();
        qw.eq("teacher_id",id);
        List<EduCourse> list = iEduCourseService.list(qw);
        Map<String,Object> map = new HashMap<>();
        map.put("teacher",teacher);
        map.put("courseList",list);
        return Result.ok(map);
    }




















}
