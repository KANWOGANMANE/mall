package com.sjq.acls.service;

import com.sjq.acls.entity.AclUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sjq.acls.entity.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author kemp
 * @since 2022-04-18
 */
public interface IAclUserService extends IService<AclUser> {

    User selectByUsername(String username);
}
