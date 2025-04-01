package cc.elvea.platform.commons.data.mybatis.handler;

import cc.elvea.platform.commons.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * @author elvea
 * @see MetaObjectHandler
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
        this.setFieldValByName("lastModifiedAt", LocalDateTime.now(), metaObject);
        this.setFieldValByName("lastModifiedBy", getCurUserId(), metaObject);
    }

    private Long getCurUserId() {
        return SecurityUtils.getUid();
    }

}
