package com.sjq.acls.service;

import com.sjq.acls.entity.AclPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author kemp
 * @since 2022-04-18
 */
public interface IAclPermissionService extends IService<AclPermission> {

    List<String> selectPermissionValueByUserId(String id);
}
