//package com.sjq.edu.listener;
//
//import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.ibatis.reflection.MetaObject;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//
///**
// * @Author Kemp
// * @create 2022/4/1 13:55
// */
//@Slf4j
//@Component
//public class RabbitMetaObjectHandler  implements MetaObjectHandler {
//    @Override
//    public void insertFill(MetaObject metaObject) {
//        this.strictInsertFill(metaObject, "gmt_create", LocalDateTime.class, LocalDateTime.now());
//        this.strictUpdateFill(metaObject, "gmt_modified", LocalDateTime.class, LocalDateTime.now());
//    }
//
//    @Override
//    public void updateFill(MetaObject metaObject) {
//        this.strictUpdateFill(metaObject, "gmt_modified", LocalDateTime.class, LocalDateTime.now());
//    }
//}
