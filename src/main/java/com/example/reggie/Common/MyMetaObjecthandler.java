package com.example.reggie.Common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author 董成鹏
 * @date 2023/05/04/ 16:47
 */
@Component
@Slf4j
public class MyMetaObjecthandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        long id = Thread.currentThread().getId();
        log.info("线程的ID为 {}", id);

        log.info("公共字段自动填充【insert】。。。。。");
        log.info(metaObject.toString());

        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("createUser", new Long(1));
        metaObject.setValue("updateUser", new Long(1));
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("公共字段自动填充【update】。。。。。");
        log.info(metaObject.toString());

        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateUser", new Long(1));
    }
}
