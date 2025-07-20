-- =====================================================================================================================
-- 前台系统基础表
-- =====================================================================================================================

--
-- 租户表
--

DROP TABLE IF EXISTS `sys_tenant`;

CREATE TABLE `sys_tenant`
(
    `id`               BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `uuid`             VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '用户标识',
    `code`             VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '用户名',
    `display_name`     VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '昵称',
    `status`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态',
    `active`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`       DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '创建时间',
    `last_modified_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at` DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '最后修改时间',
    `deleted_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`       DATETIME(3)      NULL COMMENT '删除时间',
    CONSTRAINT `pk_sys_account` PRIMARY KEY (`id`),
    INDEX `ix_sys_account__active` (`active`),
    INDEX `ix_sys_account__uuid` (`uuid`),
    INDEX `ix_sys_account__code` (`code`)
) COMMENT '租户表';

--
-- 租户账号表
--

DROP TABLE IF EXISTS `sys_tenant_account`;

CREATE TABLE `sys_tenant_account`
(
    `id`               BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `tenant_id`        BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `account_id`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `uuid`             VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '标识',
    `username`         VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '用户名',
    `name`             VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '姓名',
    `display_name`     VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '昵称',
    `status`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态',
    `source`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '来源',
    `active`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`       DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '创建时间',
    `last_modified_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at` DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '最后修改时间',
    `deleted_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`       DATETIME(3)      NULL COMMENT '删除时间',
    CONSTRAINT `pk_sys_tenant_account` PRIMARY KEY (`id`),
    INDEX `sys_tenant_account__active` (`active`),
    INDEX `sys_tenant_account__username` (`username`)
) COMMENT '租户账号表';
