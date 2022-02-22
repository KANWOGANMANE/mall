package com.sjq.eduteacher.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sjq.commonutils.result.Result;
import com.sjq.commonutils.vo.EduTeacherVo;
import com.sjq.eduteacher.entity.EduTeacher;
import com.sjq.eduteacher.service.IEduTeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Kemp
 * @since 2022-01-14
 */
@RestController
@RequestMapping("/edu/teacher")
public class EduTeacherController {

    private static final Logger logger = LoggerFactory.getLogger(EduTeacherController.class);

    @Autowired
    private IEduTeacherService iEduTeacherService;

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
    public Result delTeacher(@PathVariable("id") Long id){
        if(id!=null){
            if(iEduTeacherService.removeById(id)){
                return Result.ok("删除成功!");
            }
        }
        return Result.fail("删除失败!");
    }

    /**
     * Mybatis-plus分页插件总结：
     * 1.Page实习Ipage接口
     * 2.创建Page的时候会条用父类构造方法，先把基类创建出来，
     * 3.通过Service.page(Page,QueryWarper)传入Page对象和QueryWarper会返回一个Ipage
     * 4.通过参数Page对象可以拿到返回结果的数据
     * 5.
     * @param current
     * @param size
     * @return
     */
    @GetMapping("/pageTeachers/{current}/{limit}")
    public Result pageTeachers(@PathVariable("current")long current,
                               @PathVariable("limit")long size){
        return Result.ok(iEduTeacherService.page(new Page<EduTeacher>(current,size), null));
    }

    @GetMapping("/pageTeacherCondition/{current}/{size}")
    public Result pageTeacherCondition(@PathVariable("current")long current,
                                       @PathVariable("size")long size,
                                       EduTeacherVo eduTeacherVo){
        IPage<EduTeacher> page = new Page<>(current,size);
        IPage iPage = iEduTeacherService.pageTeacherCondition(page, eduTeacherVo);

        return Result.ok(iPage);
    }

    @GetMapping("/findTeacher/{id}")
    public Result findTeacherById(@PathVariable("id") String id){
        if(id!=null){
            EduTeacher id1 = iEduTeacherService.getById(id);
            if(id1!=null) {
                return Result.ok(id1);
            }
        }
        return Result.fail("查询失败！");
    }

    @PostMapping("/updateTeacher")
    public Result UpdateTeacher(@RequestBody EduTeacherVo vo){
        if(vo!=null){
            EduTeacher tacher = new EduTeacher();
            BeanUtils.copyProperties(vo,tacher);
            boolean b = iEduTeacherService.updateById(tacher);
            if(b){
                return Result.ok("修改成功！");
            }
        }
        return Result.fail("修改失败!");
    }














}
