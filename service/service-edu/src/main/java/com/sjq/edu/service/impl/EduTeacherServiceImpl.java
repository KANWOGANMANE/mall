package com.sjq.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sjq.edu.entity.EduTeacher;
import com.sjq.edu.mapper.EduTeacherMapper;
import com.sjq.edu.service.IEduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author kemp
 * @since 2022-01-14
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements IEduTeacherService {

    @Autowired
    private EduTeacherMapper eduTeacherMapper;

    @Override
    public List<EduTeacher> findAllTeachers() {
        QueryWrapper<EduTeacher> qw = new QueryWrapper();
        List<EduTeacher> eduTeachers = eduTeacherMapper.selectList(qw);

        return eduTeachers;
    }
}
