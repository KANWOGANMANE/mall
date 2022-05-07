package com.sjq.acls.mapper;

import com.sjq.acls.entity.AclPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author kemp
 * @since 2022-04-18
 */
public interface AclPermissionMapper extends BaseMapper<AclPermission> {

    List<String> selectAllPermissionValue();

    List<String> selectPermissionValueByUserId(String id);
}
