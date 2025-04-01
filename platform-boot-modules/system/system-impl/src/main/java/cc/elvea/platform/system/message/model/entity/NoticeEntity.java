package cc.elvea.platform.system.message.model.entity;

import cc.elvea.platform.commons.annotations.DateTimeFormat;
import cc.elvea.platform.commons.annotations.JsonFormat;
import cc.elvea.platform.commons.constants.DateTimeConstants;
import cc.elvea.platform.commons.data.jpa.domain.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * @author elvea
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_notice")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class NoticeEntity extends BaseEntity {
    /**
     * 通知标题
     */
    private String subject;
    /**
     * 通知内容
     */
    private String content;
    /**
     * 收件人ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long recipientId;
    /**
     * 发件人ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long senderId;
    /**
     * 是否已读
     */
    private Boolean readInd;
    /**
     * 发送时间
     */
    @JsonFormat(pattern = DateTimeConstants.DEFAULT_FULL_DATE_TIME_PATTERN)
    @DateTimeFormat(pattern = DateTimeConstants.DEFAULT_FULL_DATE_TIME_PATTERN)
    private LocalDateTime readDatetime;
}
