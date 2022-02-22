package com.sjq.eduteacher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.sjq.commonutils.vo.EduTeacherVo;
import com.sjq.eduteacher.entity.EduTeacher;
import com.sjq.eduteacher.mapper.EduTeacherMapper;
import com.sjq.eduteacher.service.IEduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author Kemp
 * @since 2022-01-14
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements IEduTeacherService {

    @Resource
    private EduTeacherMapper eduTeacherMapper;

    @Override
    public List<EduTeacher> findAllTeachers() {
        QueryWrapper<EduTeacher> qw = new QueryWrapper();
        List<EduTeacher> eduTeachers = eduTeacherMapper.selectList(qw);

        return eduTeachers;
    }

    @Override
    public IPage pageTeacherCondition(IPage page, EduTeacherVo eduTeacherVo) {
        QueryWrapper<EduTeacher> qw = new QueryWrapper();
        String name = eduTeacherVo.getName();
        Integer level = eduTeacherVo.getLevel();

        if(StringUtils.isNotEmpty(name)){
            qw.like("name",name);
        }
        if(level!=null){
            qw.eq("level",level);
        }

        return this.page(page,qw);
    }

    @Override
    public boolean insertTeacher(EduTeacherVo eduTeacherVo) {
        EduTeacher eduTeacher = new EduTeacher();
        BeanUtils.copyProperties(eduTeacherVo,eduTeacher);
        eduTeacher.setGmtCreate(LocalDateTime.now());
        eduTeacher.setGmtModified(LocalDateTime.now());
        eduTeacher.setIsDeleted(false);
        if(this.save(eduTeacher)){
            return true;
        }
        return false;
    }
}
