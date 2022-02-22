package com.sjq.servicebase.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * @Author Kemp
 * @create 2022/1/14 22:53
 * 插入新的记录自动填充时间 gmtCreate、gmtModified
 * 更新记录自动更新时间 gmtModified
 */

public class TimeColHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
//        this.strictInsertFill(metaObject,"gmtCreate", LocalDateTime.class, LocalDateTime.now());
//        this.strictInsertFill(metaObject,"gmtModified", LocalDateTime.class, LocalDateTime.now());
        this.setFieldValByName("gmtCreate",new Date(),metaObject);
        this.setFieldValByName("gmtModified",new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //this.strictInsertFill(metaObject,"gmtModified", LocalDateTime.class, LocalDateTime.now());
        this.setFieldValByName("gmtModified",new Date(),metaObject);
    }
}
