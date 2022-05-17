package com.sjq.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sjq.edu.entity.EduCourse;
import com.sjq.edu.mapper.EduTeacherMapper;
import com.sjq.commonutils.vo.EduTeacherVo;
import com.sjq.edu.entity.EduTeacher;
import com.sjq.edu.service.IEduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Integer sort = eduTeacherVo.getSort();
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
        qw.orderByDesc("sort");
//        qw.orderByDesc("gmt_create");

        return this.page(page,qw);
    }

    @Override
    @CacheEvict(value = "hotteacher",allEntries = true)
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

    @Override
    @Cacheable(value = "hotteacher",key = "'selectHotTeacherList'")
    public List<EduTeacher> getHotTeacher() {
        QueryWrapper<EduTeacher> qw = new QueryWrapper<>();
        qw.orderByDesc("id");
        qw.last("limit 4");
        List<EduTeacher> list = this.list(qw);
        return list;
    }

    @Override
    public Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageTeacher) {
        QueryWrapper<EduTeacher> qw = new QueryWrapper();
        qw.orderByDesc("id");
        baseMapper.selectPage(pageTeacher, qw);
        List<EduTeacher> records = pageTeacher.getRecords();
        long current = pageTeacher.getCurrent();
        long pages = pageTeacher.getPages();
        long size = pageTeacher.getSize();
        long total = pageTeacher.getTotal();
        boolean hasNext = pageTeacher.hasNext();//下一页
        boolean hasPrevious = pageTeacher.hasPrevious();//上一页

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        return map;
    }

    @Override
    public List<EduCourse> getCourseListByTeacher(String condition) {
        List<EduCourse> res = baseMapper.selectteachercourse("%" + condition + "%");
        return res;
    }
}
