package com.sjq.edu.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sjq.commonutils.vo.CourseFrontVo;
import com.sjq.commonutils.vo.CoursePublishVo;
import com.sjq.commonutils.vo.CourseVo;
import com.sjq.commonutils.vo.CourseWebVo;
import com.sjq.edu.entity.EduCourse;
import com.sjq.edu.entity.EduCourseDescription;
import com.sjq.edu.entity.EduTeacher;
import com.sjq.edu.mapper.EduCourseMapper;
import com.sjq.edu.service.IEduChapterService;
import com.sjq.edu.service.IEduCourseDescriptionService;
import com.sjq.edu.service.IEduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjq.edu.service.IEduVideoService;
import com.sjq.servicebase.exception.MyException;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author kemp
 * @since 2022-04-02
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements IEduCourseService {

    @Autowired
    private IEduCourseDescriptionService eduCourseDescriptionService;

    @Autowired
    private IEduVideoService eduVideoService;

    @Autowired
    private IEduChapterService eduChapterService;

    @Override
    public String saveCourseInfo(CourseVo courseVo) {
        //添加课程基本信息
        EduCourse courseInfo = new EduCourse();
        courseInfo.setGmtCreate(LocalDateTime.now());
        courseInfo.setIsDeleted(0);
        courseInfo.setGmtModified(LocalDateTime.now());
        BeanUtils.copyProperties(courseVo,courseInfo);
        int insert = baseMapper.insert(courseInfo);
        //添加课程描述
        if(insert <= 0){
            throw new MyException("20001","添加课程信息失败");
        }
        String courseid = courseInfo.getId();
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setId(courseid);
        courseDescription.setGmtCreate(LocalDateTime.now());
        courseDescription.setGmtModified(LocalDateTime.now());
        courseDescription.setDescription(courseVo.getDescription());
        eduCourseDescriptionService.save(courseDescription);
        return courseid;
    }

    @Override
    public CourseVo getCourseInfo(@PathVariable String courseId) {
        //查询课程表
        EduCourse eduCourse = baseMapper.selectById(courseId);
        if(eduCourse == null){
            return null;
        }
        CourseVo res = new CourseVo();
        BeanUtils.copyProperties(eduCourse,res);
        //查询描述表
        EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getById(courseId);
        if(eduCourseDescription != null){
            res.setDescription(eduCourseDescription.getDescription());
        }
        return res;
    }

    @Override
    public boolean updateCourseInfo(CourseVo courseVo) {
        EduCourse c = new EduCourse();
        c.setGmtModified(LocalDateTime.now());
        BeanUtils.copyProperties(courseVo,c);
        int i = baseMapper.updateById(c);
        if(i == 0) return false;
        EduCourseDescription description = new EduCourseDescription();
        description.setGmtModified(LocalDateTime.now());
        BeanUtils.copyProperties(courseVo,description);
        eduCourseDescriptionService.updateById(description);
        return true;
    }

    @Override
    public CoursePublishVo publicshCourseInfo(String id) {
        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(id);
        return publishCourseInfo;
    }

    @Override
    public IPage pageCourseCondition(IPage<EduCourse> page, EduCourse eduCourse) {
        QueryWrapper<EduCourse> qw = new QueryWrapper();
        String title = eduCourse.getTitle();
        String status = eduCourse.getStatus();
        if(StringUtils.isNotBlank(title)){
            qw.like("title",title);
        }
        if(StringUtils.isNotBlank(status)){
            qw.eq("status",status);
        }
        IPage<EduCourse> page1 = this.page(page, qw);
        return page1;
    }

    @Override
    public boolean deleteCourse(String cid) {
        //1删除课程小节
        boolean isDeleteVideo = eduVideoService.removeVideoByCourseId(cid);

        //2删除课程章节
        boolean isDeleteChapter = eduChapterService.removeChapterByCourseId(cid);

        //3删除课程描述
        boolean isDeleteDescription = eduCourseDescriptionService.removeById(cid);

        //4删除课程
        boolean isDeleteCourse = baseMapper.deleteById(cid)>0?true:false;

//        return isDeleteVideo && isDeleteChapter && isDeleteDescription && isDeleteCourse;
        return true;
    }

    @Override
    @Cacheable(value = "hotcourse"  ,key="'selectHotCourseList'")
    public List<EduCourse> getHotCourse() {
        QueryWrapper<EduCourse> qw = new QueryWrapper<>();
        qw.eq("status","Normal");
        qw.orderByDesc("id");
        qw.last("limit 8");
        List<EduCourse> list = this.list(qw);
        return list;
    }

    @Override
    public Map<String, Object> getCourseFontList(Page<EduCourse> pagecourse, CourseFrontVo courseFrontVo) {
        QueryWrapper<EduCourse> qw = new QueryWrapper<>();
        if(StringUtils.isNotBlank(courseFrontVo.getSubjectParentId())){
            qw.eq("subject_parent_id",courseFrontVo.getSubjectParentId());
        }
        if(StringUtils.isNotBlank(courseFrontVo.getSubjectId())){
            qw.eq("subject_id",courseFrontVo.getSubjectId());
        }
        if(StringUtils.isNotBlank(courseFrontVo.getBuyCountSort())){
            qw.orderByDesc("buy_count");
        }
        if(StringUtils.isNotBlank(courseFrontVo.getGmtCreateSort())){
            qw.orderByDesc("gmt_create");
        }
        if(StringUtils.isNotBlank(courseFrontVo.getPriceSort())){
            qw.orderByDesc("price");
        }
        baseMapper.selectPage(pagecourse, qw);
        List<EduCourse> records = pagecourse.getRecords();
        long current = pagecourse.getCurrent();
        long pages = pagecourse.getPages();
        long size = pagecourse.getSize();
        long total = pagecourse.getTotal();
        boolean hasNext = pagecourse.hasNext();//下一页
        boolean hasPrevious = pagecourse.hasPrevious();//上一页

        //把分页数据获取出来，放到map集合
        Map<String, Object> map = new HashMap<>();
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
    public CourseWebVo getBaseCourseInfo(String id) {
        return baseMapper.getBaseCourseInfo(id);
    }

    @Override
    public void commentcountadd(String id) {
        EduCourse course = baseMapper.selectById(id);
        Long commentCount = course.getCommentCount();
        course.setCommentCount(commentCount+1);
        baseMapper.updateById(course);
    }

    @Override
    public void viewcountadd(String id) {
        EduCourse course = baseMapper.selectById(id);
        Long viewCount = course.getViewCount();
        course.setViewCount(viewCount + 1);
        baseMapper.updateById(course);
    }

    @Override
    public Map<String, Object> getCourseCollection(Page<EduCourse> pagecourse, CourseFrontVo courseFrontVo) {
        return null;
    }
}
