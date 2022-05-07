package com.sjq.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sjq.edu.entity.EduCourse;
import com.sjq.edu.entity.EduCourseCollect;
import com.sjq.edu.mapper.EduCourseCollectMapper;
import com.sjq.edu.service.IEduCourseCollectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 课程收藏 服务实现类
 * </p>
 *
 * @author kemp
 * @since 2022-05-03
 */
@Service
public class EduCourseCollectServiceImpl extends ServiceImpl<EduCourseCollectMapper, EduCourseCollect> implements IEduCourseCollectService {

    @Override
    public boolean saveCollection(String courseId, String memberid) {
        EduCourseCollect c = new EduCourseCollect();
        QueryWrapper<EduCourseCollect> collect = new QueryWrapper();
        collect.eq("course_id",courseId);
        collect.eq("member_id",memberid);
        EduCourseCollect eduCourseCollect = baseMapper.selectOne(collect);
        if(eduCourseCollect == null){
            c.setCourseId(courseId);
            c.setMemberId(memberid);
            c.setGmtCreate(LocalDateTime.now());
            c.setGmtModified(LocalDateTime.now());
            baseMapper.insert(c);
            return true;  //收藏成功
        }else{
            baseMapper.delete(collect);
            return false;   //取消收藏
        }
    }

    @Override
    public List<EduCourse> selectcollection(String memberid) {
        List<EduCourse> eduCourseCollects = baseMapper.selectcollection(memberid);
        return eduCourseCollects;
    }
}
