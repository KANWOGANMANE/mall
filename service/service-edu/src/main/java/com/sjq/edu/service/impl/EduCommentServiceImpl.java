package com.sjq.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sjq.edu.entity.EduComment;
import com.sjq.edu.mapper.EduCommentMapper;
import com.sjq.edu.service.IEduCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author kemp
 * @since 2022-04-15
 */
@Service
public class EduCommentServiceImpl extends ServiceImpl<EduCommentMapper, EduComment> implements IEduCommentService {

    @Override
    public Map<String, Object> getCourseList(Long page, Long limit, String courseid) {
        Page<EduComment> pageParam = new Page<>(page,limit);
        QueryWrapper<EduComment> qw = new QueryWrapper();
        qw.eq("course_id",courseid);
        baseMapper.selectPage(pageParam,qw);
        List<EduComment> commentList = pageParam.getRecords();
        Map<String, Object> map = new HashMap<>();
        map.put("items", commentList);
        map.put("current", pageParam.getCurrent());
        map.put("pages", pageParam.getPages());
        map.put("size", pageParam.getSize());
        map.put("total", pageParam.getTotal());
        map.put("hasNext", pageParam.hasNext());
        map.put("hasPrevious", pageParam.hasPrevious());
        return map;
    }
}
