package com.sjq.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sjq.edu.entity.EduVideo;
import com.sjq.edu.mapper.EduVideoMapper;
import com.sjq.edu.service.IEduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author kemp
 * @since 2022-04-02
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements IEduVideoService {

    @Override
    public boolean removeVideoByCourseId(String cid) {
        QueryWrapper<EduVideo> qw = new QueryWrapper();
        qw.eq("course_id",cid);
        int delete = baseMapper.delete(qw);
        //TODO 删除视频资源
        return delete>0?true:false;
    }
}
