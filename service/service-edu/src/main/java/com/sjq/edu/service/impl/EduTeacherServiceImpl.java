package com.sjq.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.sjq.edu.mapper.EduTeacherMapper;
import com.sjq.commonutils.vo.EduTeacherVo;
import com.sjq.edu.entity.EduTeacher;
import com.sjq.edu.service.IEduTeacherService;
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
        String begin = eduTeacherVo.getBegin();
        String end = eduTeacherVo.getEnd();
        if(StringUtils.isNotBlank(name)){
            qw.like("name",name);
        }
        if(level!=null){
            qw.eq("level",level);
        }
        if(StringUtils.isNotBlank(begin)){
            qw.ge("gmt_create",begin);
        }
        if(StringUtils.isNotBlank(end)){
            qw.le("gmt_create",end);
        }
        qw.orderByDesc("gmt_create");

        return this.page(page,qw);
    }

    @Override
    public boolean insertTeacher(EduTeacherVo eduTeacherVo) {
        EduTeacher eduTeacher = new EduTeacher();
        BeanUtils.copyProperties(eduTeacherVo,eduTeacher);
        eduTeacher.setGmtCreate(LocalDateTime.now());
        eduTeacher.setGmtModified(LocalDateTime.now());
        eduTeacher.setIsDeleted(false);
        if(eduTeacher.getAvatar() == null || eduTeacher.getAvatar().length()==0){
            eduTeacher.setAvatar("http://192.168.72.138:8888/group1/M00/00/00/wKhIimJHz2OAc-mmAAAqXMYAv_428.webp");
        }
        if(this.save(eduTeacher)){
            return true;
        }
        return false;
    }

    @Override
    public EduTeacher getByids(String id) {
        Long ids = Long.parseLong(id);
        EduTeacher res = eduTeacherMapper.getoneByids(ids);
        return res;
    }
}
