package com.sjq.edu.service;

import com.sjq.edu.entity.EduUpload;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kemp
 * @since 2022-04-09
 */
public interface IEduUploadService extends IService<EduUpload> {

    String saveUploadVo(EduUpload eduUpload);
}
