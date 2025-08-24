-- =====================================================================================================================
-- 前台系统基础表
-- =====================================================================================================================

--
-- 职位
--

DROP TABLE IF EXISTS `zp_job`;

CREATE TABLE `zp_job`
(
    `id`         BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `tenant_id`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `title`      VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '职位名称',
    `details`    LONGTEXT         NULL COMMENT '职位详情',
    `status`     TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态',
    `active`     TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at` DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '创建时间',
    `updated_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `updated_at` DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '最后修改时间',
    `deleted_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at` DATETIME(3)      NULL COMMENT '删除时间',
    CONSTRAINT `pk_zp_job` PRIMARY KEY (`id`),
    INDEX `ix_zp_job__active` (`active`)
) COMMENT '职位';

--
-- 沟通表
--

DROP TABLE IF EXISTS `zp_chat`;

CREATE TABLE `zp_chat`
(
    `id`         BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `tenant_id`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `title`      VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '职位名称',
    `status`     TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态',
    `active`     TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at` DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '创建时间',
    `updated_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `updated_at` DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '最后修改时间',
    `deleted_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at` DATETIME(3)      NULL COMMENT '删除时间',
    CONSTRAINT `pk_zp_chat` PRIMARY KEY (`id`),
    INDEX `ix_zp_chat__active` (`active`)
) COMMENT '沟通表';

--
-- 沟通明细表
--

DROP TABLE IF EXISTS `zp_chat_item`;

CREATE TABLE `zp_chat_item`
(
    `id`         BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `tenant_id`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `chat_id`    BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '沟通ID',
    `content`    LONGTEXT         NULL COMMENT '内容',
    `status`     TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态',
    `active`     TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at` DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '创建时间',
    `updated_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `updated_at` DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '最后修改时间',
    `deleted_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at` DATETIME(3)      NULL COMMENT '删除时间',
    CONSTRAINT `pk_zp_chat` PRIMARY KEY (`id`),
    INDEX `ix_zp_chat__active` (`active`)
) COMMENT '沟通明细表';
