package com.sjq.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sjq.commonutils.vo.OneSubject;
import com.sjq.commonutils.vo.TwoSubject;
import com.sjq.edu.entity.EduSubject;
import com.sjq.edu.entity.ExcelSubjectData;
import com.sjq.edu.listener.ExcelSubjectListener;
import com.sjq.edu.mapper.EduSubjectMapper;
import com.sjq.edu.service.IEduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjq.servicebase.exception.MyException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author kemp
 * @since 2022-04-01
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements IEduSubjectService {

    @Override
    public void saveSubject(MultipartFile file,IEduSubjectService edusubjectService) {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, ExcelSubjectData.class,new ExcelSubjectListener(edusubjectService)).sheet().doRead();
        }catch (Exception e){
            //自定义异常
            throw new MyException("20001","添加科目失败");
        }
    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        //查询所有一级分类
        QueryWrapper<EduSubject> oneqw = new QueryWrapper();
        oneqw.eq("parent_id","0");
        List<EduSubject> oneeduSubjects = baseMapper.selectList(oneqw);

        //查询所有二级分类
        QueryWrapper<EduSubject> twoqw = new QueryWrapper();
        twoqw.ne("parent_id","0");
        List<EduSubject> twoeduSubjects = baseMapper.selectList(twoqw);

        //封装结果
        List<OneSubject> resList = new ArrayList<>();
        for(EduSubject i:oneeduSubjects){
            String id = i.getId();
            OneSubject a = new OneSubject();
            BeanUtils.copyProperties(i,a);
            List<TwoSubject> resinone = new ArrayList<>();
            for(EduSubject j:twoeduSubjects){
                String pid = j.getParentId();
                if(pid.equals(id)){
                    TwoSubject m = new TwoSubject();
                    BeanUtils.copyProperties(j,m);
                    resinone.add(m);
                }
            }
            a.setChildren(resinone);
            resList.add(a);
        }
        return resList;
    }
}
