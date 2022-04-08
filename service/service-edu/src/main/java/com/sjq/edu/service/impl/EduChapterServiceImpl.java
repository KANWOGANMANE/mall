package com.sjq.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sjq.commonutils.vo.OneChapter;
import com.sjq.commonutils.vo.TwoChapter;
import com.sjq.edu.entity.EduChapter;
import com.sjq.edu.entity.EduVideo;
import com.sjq.edu.mapper.EduChapterMapper;
import com.sjq.edu.service.IEduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjq.edu.service.IEduVideoService;
import com.sjq.servicebase.exception.MyException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author kemp
 * @since 2022-04-02
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements IEduChapterService {

    @Autowired
    private IEduVideoService eduVideoService;

    @Override
    public List<OneChapter> getChapterAndVideoBycid(String courseId) {

        //先查章节、
        QueryWrapper<EduChapter> qw = new QueryWrapper();
        qw.eq("course_id",courseId);
        List<EduChapter> eduChaptersList = baseMapper.selectList(qw);

        //再查小节
        QueryWrapper<EduVideo> qw1 = new QueryWrapper();
        qw.eq("course_id",courseId);
        List<EduVideo> videoList = eduVideoService.list(qw1);

        //封装结果
        List<OneChapter> res = new ArrayList<>();
        for(EduChapter a:eduChaptersList){
            String id = a.getId();//章节id
            OneChapter tmp = new OneChapter();
            BeanUtils.copyProperties(a,tmp);
            List<TwoChapter> lis = new ArrayList<>();
            for(EduVideo b:videoList){
                String chapterid = b.getChapterId();
                if(id.equals(chapterid)){
                    TwoChapter two = new TwoChapter();
                    BeanUtils.copyProperties(b,two);
                    lis.add(two);
                }
            }
            tmp.setChildren(lis);
            res.add(tmp);
        }
        return res;
    }

    @Override
    public boolean deleteChapter(String chapterId) {
        QueryWrapper<EduVideo> qw = new QueryWrapper<>();
        qw.eq("chapter_id",chapterId);
        int i = eduVideoService.count(qw);
        if(i > 0){
            return false;
        }else{
            int deleteById = baseMapper.deleteById(chapterId);
            return deleteById>0;
        }
    }

    @Override
    public boolean removeChapterByCourseId(String cid) {
        QueryWrapper<EduChapter> qw = new QueryWrapper<>();
        qw.eq("course_id",cid);
        int delete = baseMapper.delete(qw);
        return delete>0?true:false;
    }
}
