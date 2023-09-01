package cn.elvea.platform.commons.core.data.mybatis.handler;

import cn.elvea.platform.commons.core.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * @author elvea
 * @see MetaObjectHandler
 * @since 0.0.1
 */
@Slf4j
public class CustomMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        this.strictInsertFill(metaObject, "createdAt", LocalDateTime.class, now);
        this.strictInsertFill(metaObject, "createdBy", Long.class, getCurUserId());
        this.strictInsertFill(metaObject, "lastModifiedAt", LocalDateTime.class, now);
        this.strictInsertFill(metaObject, "lastModifiedBy", Long.class, getCurUserId());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "lastModifiedAt", LocalDateTime.class, LocalDateTime.now());
        this.strictUpdateFill(metaObject, "lastModifiedBy", Long.class, getCurUserId());
    }

    private Long getCurUserId() {
        return SecurityUtils.getUserId();
    }

}
