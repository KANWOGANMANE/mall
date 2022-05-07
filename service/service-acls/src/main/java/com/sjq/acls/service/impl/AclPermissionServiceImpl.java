package com.sjq.acls.service.impl;

import com.sjq.acls.entity.AclPermission;
import com.sjq.acls.entity.AclUser;
import com.sjq.acls.entity.User;
import com.sjq.acls.mapper.AclPermissionMapper;
import com.sjq.acls.service.IAclPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjq.acls.service.IAclUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author kemp
 * @since 2022-04-18
 */
@Primary
@Service
public class AclPermissionServiceImpl extends ServiceImpl<AclPermissionMapper, AclPermission> implements IAclPermissionService {

    @Autowired
    private IAclUserService userService;

    @Override
    public List<String> selectPermissionValueByUserId(String id) {
        List<String> selectPermissionValueList = null;
        if(this.isSysAdmin(id)) {
            //如果是系统管理员，获取所有权限
            selectPermissionValueList = baseMapper.selectAllPermissionValue();
        } else {
            selectPermissionValueList = baseMapper.selectPermissionValueByUserId(id);
        }
        return selectPermissionValueList;
    }

    private boolean isSysAdmin(String userId) {
        AclUser byId = userService.getById(userId);
        User user = new User();
        BeanUtils.copyProperties(byId,user);
        if(null != user && "admin".equals(user.getUsername())) {
            return true;
        }
        return false;
    }
}
