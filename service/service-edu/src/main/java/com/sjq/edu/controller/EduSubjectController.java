package com.sjq.edu.controller;


import com.sjq.commonutils.result.Result;
import com.sjq.commonutils.vo.OneSubject;
import com.sjq.edu.service.IEduSubjectService;
import com.sjq.edu.service.impl.EduSubjectServiceImpl;
import com.sjq.servicebase.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author kemp
 * @since 2022-04-01
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private IEduSubjectService edusubjectService;

    @PostMapping("addSubject")
    public Result addSubject(MultipartFile file){
        try {
            edusubjectService.saveSubject(file,edusubjectService);
        }catch (Exception e){
            throw new MyException("20001","添加科目失败");
        }
        return Result.ok();
    }

    @GetMapping("getAllSubject")
    public Result getAllSubject(){
        List<OneSubject> res = edusubjectService.getAllOneTwoSubject();
        return Result.ok(res);
    }
}
