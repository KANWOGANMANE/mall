package com.sjq.edu.mapper;

import com.sjq.edu.entity.EduUpload;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kemp
 * @since 2022-04-09
 */
public interface EduUploadMapper extends BaseMapper<EduUpload> {

    boolean saveUpload(EduUpload save);
}
