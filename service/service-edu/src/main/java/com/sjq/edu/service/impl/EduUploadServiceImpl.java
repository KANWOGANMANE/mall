package com.sjq.edu.service.impl;

import com.sjq.edu.entity.EduUpload;
import com.sjq.edu.mapper.EduUploadMapper;
import com.sjq.edu.service.IEduUploadService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kemp
 * @since 2022-04-09
 */
@Service
public class EduUploadServiceImpl extends ServiceImpl<EduUploadMapper, EduUpload> implements IEduUploadService {


    @Override
    public String saveUploadVo(EduUpload eduUpload) {
        EduUpload save = new EduUpload();
        boolean save1 = false;
        if(eduUpload != null){
            BeanUtils.copyProperties(eduUpload,save);
            save.setGmtCreate(LocalDateTime.now());
            save.setGmtModified(LocalDateTime.now());
            save.setIsDeleted(0);
            save.setOrderId(0);
            save1 = baseMapper.saveUpload(save);
        }
        return save1?save.getId():null;
    }
}
