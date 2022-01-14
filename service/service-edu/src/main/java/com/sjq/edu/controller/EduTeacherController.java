package com.sjq.edu.controller;

import com.sjq.commonutils.ServiceResult;
import com.sjq.edu.entity.EduTeacher;
import com.sjq.edu.service.IEduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author kemp
 * @since 2022-01-14
 */
@RestController
@RequestMapping("/edu/teacher")
public class EduTeacherController {

    @Autowired
    private IEduTeacherService iEduTeacherService;

    @GetMapping("/findAllTeachers")
    public ServiceResult selAllTeacher(){
        List<EduTeacher> findall = iEduTeacherService.findAllTeachers();
        if(findall.size()>0 || !findall.isEmpty()) {
            return ServiceResult.ok(findall);
        }
        return ServiceResult.failed();
    }




}
