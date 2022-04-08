package com.sjq.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sjq.edu.entity.EduSubject;
import com.sjq.edu.entity.ExcelSubjectData;
import com.sjq.edu.service.IEduSubjectService;

import java.time.LocalDateTime;

/**
 * @Author Kemp
 * @create 2022/4/1 12:26
 */
public class ExcelSubjectListener extends AnalysisEventListener<ExcelSubjectData> {

    private IEduSubjectService eduSubjectService;

    public ExcelSubjectListener(IEduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }


    @Override
    public void invoke(ExcelSubjectData excelSubjectData, AnalysisContext analysisContext) {
        //判断获取的excel数据是否为空
        if(excelSubjectData == null) {
            throw new NullPointerException();
            //throw new ExcelNullException();
        }
        //查询一级分类是否存在
        EduSubject eduSubject = this.existloneSubject(eduSubjectService, excelSubjectData.getLonesubjectName());
        //不存在就添加到数据库
        if(eduSubject == null){
            eduSubject = new EduSubject();
            eduSubject.setParentId("0");
            eduSubject.setGmtCreate(LocalDateTime.now());
            eduSubject.setGmtModified(LocalDateTime.now());
            eduSubject.setTitle(excelSubjectData.getLonesubjectName());
            eduSubjectService.save(eduSubject);
        }
        //获取一级分类id作为二级分类的pid
        String pid = eduSubject.getId();
        //查询耳机分类是否存在
        EduSubject eduSubject1 = this.existltwoSubject(eduSubjectService, excelSubjectData.getLtwosubjectName(), pid);
        //不存在就添加
        if(eduSubject1 == null) {
            eduSubject1 = new EduSubject();
            eduSubject1.setParentId(pid);
            eduSubject1.setGmtCreate(LocalDateTime.now());
            eduSubject1.setGmtModified(LocalDateTime.now());
            eduSubject1.setTitle(excelSubjectData.getLtwosubjectName());
            eduSubjectService.save(eduSubject1);
        }
    }

    //判断科目第一类是否存在
    private EduSubject existloneSubject(IEduSubjectService eduSubjectService,String name){
        QueryWrapper<EduSubject> qw = new QueryWrapper<>();
        qw.eq("title",name);
        qw.eq("parent_id","0");
        EduSubject one = eduSubjectService.getOne(qw);
        return one;
    }
    //判断科目第二类是否存在
    private EduSubject existltwoSubject(IEduSubjectService eduSubjectService,String name,String pid){
        QueryWrapper<EduSubject> qw = new QueryWrapper<>();
        qw.eq("title",name);
        qw.eq("parent_id",pid);
        EduSubject two = eduSubjectService.getOne(qw);
        return two;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
