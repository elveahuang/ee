package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 业务分组类型
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum BizGroupTypeEnum implements BaseEnum<String> {
    // ======================================================
    // 静态类型
    // ======================================================
    ACTIVE_TYPE("ACTIVE_TYPE", "启用状态类型"),
    STATUS_TYPE("STATUS_TYPE", "发布状态类型"),
    SOURCE_TYPE("SOURCE_TYPE", "数据来源类型"),
    USER_STATUS_TYPE("USER_STATUS_TYPE", "用户状态类型"),
    PAY_STATUS_TYPE("PAY_STATUS_TYPE", "用户状态类型"),
    ENTITY_TYPE("ENTITY_TYPE", "实体类型"),
    LABEL_GROUP_TYPE("LABEL_GROUP_TYPE", "文本分组类型"),
    LABEL_TYPE("LABEL_TYPE", "文本类型"),
    AUTHORITY_TYPE("AUTHORITY_TYPE", "权限类型"),
    AUTHORITY_GROUP_TYPE("AUTHORITY_GROUP_TYPE", "权限分组类型"),
    BIZ_SCOPE_TYPE("BIZ_SCOPE_TYPE", "业务范围类型"),
    DATA_SCOPE_TYPE("DATA_SCOPE_TYPE", "数据范围类型"),
    CONFIG_TYPE("CONFIG_TYPE", "数据范围类型"),
    // ======================================================
    // 业务类型
    // ======================================================
    ENTITY_AUTHORITY_TYPE("ENTITY_AUTHORITY_TYPE", "实体权限关联业务类型"),
    ENTITY_PACKAGE_TYPE("ENTITY_PACKAGE_TYPE", "实体套餐关联业务类型"),
    ENTITY_OPEN_ID_TYPE("ENTITY_OPEN_ID_TYPE", "实体套餐关联业务类型"),
    ENTITY_RELATION_TYPE("ENTITY_RELATION_TYPE", "实体关联业务类型"),
    PACKAGE_TYPE("PACKAGE_TYPE", "套餐业务类型"),
    PACKAGE_ITEM_TYPE("PACKAGE_ITEM_TYPE", "套餐项业务类型"),
    ATTACHMENT_TYPE("ATTACHMENT_TYPE", "附件业务类型"),
    ATTACHMENT_RELATION_TYPE("ATTACHMENT_RELATION_TYPE", "附件业务类型"),
    CATALOG_TYPE("CATALOG_TYPE", "目录业务类型"),
    CATALOG_RELATION_TYPE("CATALOG_RELATION_TYPE", "目录业务类型"),
    DICT_TYPE("DICT_TYPE", "字典业务类型"),
    DICT_RELATION_TYPE("DICT_RELATION_TYPE", "字典关联业务类型"),
    TAG_TYPE("TAG_TYPE", "标签业务类型"),
    TAG_RELATION_TYPE("TAG_RELATION_TYPE", "标签关联业务类型"),
    ADDRESS_TYPE("ADDRESS_TYPE", "地址业务类型"),
    ADDRESS_RELATION_TYPE("ADDRESS_RELATION_TYPE", "地址关联业务类型"),
    MESSAGE_TYPE("MESSAGE_TYPE", "订单业务类型"),
    MESSAGE_TEMPLATE_TYPE("MESSAGE_TEMPLATE_TYPE", "消息模板业务类型"),
    MESSAGE_TARGET_TYPE("MESSAGE_TARGET_TYPE", "消息模板业务类型"),
    MESSAGE_STATUS_TYPE("MESSAGE_STATUS_TYPE", "消息状态业务类型"),
    MESSAGE_USER_TYPE("MESSAGE_USER_TYPE", "消息状态业务类型"),
    MESSAGE_SOURCE_TYPE("MESSAGE_SOURCE_TYPE", "消息状态业务类型"),
    CHAT_MESSAGE_TYPE("CHAT_MESSAGE_TYPE", "聊天消息业务类型"),
    CHAT_MESSAGE_CONTENT_TYPE("CHAT_MESSAGE_CONTENT_TYPE", "聊天消息内容业务类型"),
    AI_CHAT_TYPE("AI_CHAT_TYPE", "AI聊天业务类型"),
    AI_CHAT_CONTENT_TYPE("AI_CHAT_CONTENT_TYPE", "AI聊天业务类型"),
    ORDER_TYPE("ORDER_TYPE", "订单业务类型"),
    ORDER_STATUS_TYPE("ORDER_STATUS_TYPE", "订单状态业务类型"),
    ACCOUNT_VIP_LOG_TYPE("ACCOUNT_VIP_LOG_TYPE", "支付业务类型"),
    VIP_ITEM_DATA_VALUE_TYPE("VIP_ITEM_DATA_VALUE_TYPE", "支付业务类型"),
    PAY_TYPE("PAY_TYPE", "支付业务类型"),
    NONE("NONE", "NONE");

    private final String value;
    private final String description;
}
