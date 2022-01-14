package com.sjq.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sjq.edu.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author kemp
 * @since 2022-01-14
 */
public interface IEduTeacherService extends IService<EduTeacher> {

    public List<EduTeacher> findAllTeachers();

}
