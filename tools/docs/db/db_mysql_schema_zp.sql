-- =====================================================================================================================
-- 前台系统基础表
-- =====================================================================================================================

--
-- 职位
--

DROP TABLE IF EXISTS `zp_job`;

CREATE TABLE `zp_job`
(
    `id`               BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `tenant_id`        BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `title`            VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '职位名称',
    `status`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态',
    `active`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`       DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '创建时间',
    `last_modified_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at` DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '最后修改时间',
    `deleted_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`       DATETIME(3)      NULL COMMENT '删除时间',
    CONSTRAINT `pk_zp_job` PRIMARY KEY (`id`),
    INDEX `ix_zp_job__active` (`active`)
) COMMENT '租户表';

--
-- 沟通表
--

DROP TABLE IF EXISTS `zp_chat`;

CREATE TABLE `zp_chat`
(
    `id`               BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `tenant_id`        BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `title`            VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '职位名称',
    `status`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态',
    `active`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`       DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '创建时间',
    `last_modified_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at` DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '最后修改时间',
    `deleted_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`       DATETIME(3)      NULL COMMENT '删除时间',
    CONSTRAINT `pk_zp_chat` PRIMARY KEY (`id`),
    INDEX `ix_zp_chat__active` (`active`)
) COMMENT '沟通表';

--
-- 沟通表
--

DROP TABLE IF EXISTS `zp_geek`;

CREATE TABLE `zp_geek`
(
    `id`               BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `tenant_id`        BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `title`            VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '职位名称',
    `status`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态',
    `active`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`       DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '创建时间',
    `last_modified_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at` DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '最后修改时间',
    `deleted_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`       DATETIME(3)      NULL COMMENT '删除时间',
    CONSTRAINT `pk_zp_geek` PRIMARY KEY (`id`),
    INDEX `ix_zp_geek__active` (`active`)
) COMMENT '沟通表';
