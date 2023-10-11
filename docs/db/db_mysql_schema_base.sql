-- =====================================================================================================================
-- 核心基础表
-- =====================================================================================================================

--
-- 权限表
--

DROP TABLE IF EXISTS `sys_authority`;

CREATE TABLE `sys_authority`
(
    `id`               BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `parent_id`        BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'Parent ID',
    `code`             VARCHAR(100)     NOT NULL DEFAULT '' COMMENT '编码',
    `title`            VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '标题',
    `label`            VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '多语言文本',
    `description`      VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '备注',
    `authority_type`   VARCHAR(50)      NOT NULL DEFAULT '' COMMENT '权限类型',
    `sort_order`       INT UNSIGNED     NOT NULL DEFAULT 999 COMMENT '序号',
    `status`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态',
    `source`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '来源',
    `active`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`       DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `last_modified_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at` DATETIME         NOT NULL DEFAULT NOW() COMMENT '最后修改时间',
    `deleted_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`       DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT `pk_sys_authority` PRIMARY KEY (`id`),
    INDEX `ix_sys_authority__parent_id` (`parent_id`),
    INDEX `ix_sys_authority__code` (`code`)
) COMMENT '权限表';

--
-- 角色表
--

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role`
(
    `id`               BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `code`             VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '编号',
    `title`            VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '标题',
    `label`            VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '文本',
    `data_score_type`  varchar(100)     NOT NULL DEFAULT '' COMMENT '数据范围',
    `description`      VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '备注',
    `status`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态',
    `source`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '来源',
    `active`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`       DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `last_modified_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at` DATETIME         NOT NULL DEFAULT NOW() COMMENT '最后修改时间',
    `deleted_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`       DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT `pk_sys_role` PRIMARY KEY (`id`),
    INDEX `ix_sys_role__code` (`code`)
) COMMENT '角色表';

--
-- 组织表
--

DROP TABLE IF EXISTS `sys_organization`;

CREATE TABLE `sys_organization`
(
    `id`               BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `code`             VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '编号',
    `label`            VARCHAR(250)     NOT NULL DEFAULT '' COMMENT '文本',
    `title`            VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '标题',
    `root_ind`         TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否顶层',
    `default_ind`      TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否默认',
    `description`      VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '备注',
    `status`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态',
    `source`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '来源',
    `active`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`       DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `last_modified_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at` DATETIME         NOT NULL DEFAULT NOW() COMMENT '最后修改时间',
    `deleted_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`       DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT `pk_sys_organization` PRIMARY KEY (`id`),
    INDEX `ix_sys_organization__code` (`code`)
) COMMENT '组织表';

--
-- 岗位表
--

DROP TABLE IF EXISTS `sys_position`;

CREATE TABLE `sys_position`
(
    `id`               BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `code`             VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '编号',
    `label`            VARCHAR(250)     NOT NULL DEFAULT '' COMMENT '文本',
    `title`            VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '标题',
    `description`      VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '备注',
    `root_ind`         TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否顶层',
    `default_ind`      TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否默认',
    `status`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态',
    `source`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '来源',
    `active`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`       DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `last_modified_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at` DATETIME         NOT NULL DEFAULT NOW() COMMENT '最后修改时间',
    `deleted_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`       DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT `pk_sys_position` PRIMARY KEY (`id`),
    INDEX `ix_sys_position__code` (`code`)
) COMMENT '岗位表';

--
-- 用户表
--

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user`
(
    `id`                   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `username`             VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '用户名',
    `name`                 VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '姓名',
    `display_name`         VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '昵称',
    `email`                VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '电子邮箱',
    `mobile_country_code`  VARCHAR(10)      NOT NULL DEFAULT '' COMMENT '手机国家区号',
    `mobile_number`        VARCHAR(50)      NOT NULL DEFAULT '' COMMENT '手机号码',
    `password`             VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '密码',
    `id_card_type`         VARCHAR(50)      NOT NULL DEFAULT '' COMMENT '证件类型',
    `id_card_no`           VARCHAR(50)      NOT NULL DEFAULT '' COMMENT '证件号码',
    `sex`                  VARCHAR(10)      NOT NULL DEFAULT '' COMMENT '性别',
    `birthday`             VARCHAR(10)      NOT NULL DEFAULT '' COMMENT '生日',
    `description`          VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '备注',
    `last_login_status`    VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '最后登录状态',
    `last_login_at`        VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '最后登录时间',
    `password_expire_at`   VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '密码过期时间',
    `password_error_at`    VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '最后一次输入错误密码的时间',
    `password_error_count` INT UNSIGNED     NOT NULL DEFAULT 0 COMMENT '输入错误密码的次数',
    `status`               TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态',
    `source`               TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '来源',
    `active`               TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`           BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`           DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `last_modified_by`     BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at`     DATETIME         NOT NULL DEFAULT NOW() COMMENT '最后修改时间',
    `deleted_by`           BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`           DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT `pk_sys_user` PRIMARY KEY (`id`),
    INDEX `ix_sys_user__active` (`active`),
    INDEX `ix_sys_user__username` (`username`),
    INDEX `ix_sys_user__email` (`email`),
    INDEX `ix_sys_user__mobile` (`mobile_country_code`, `mobile_number`)
) COMMENT '用户表';

--
-- 角色-权限关联表
--

DROP TABLE IF EXISTS `sys_role_authority`;

CREATE TABLE `sys_role_authority`
(
    `id`           BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `role_id`      BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '角色ID',
    `authority_id` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '权限ID',
    `active`       TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`   DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    CONSTRAINT `pk_sys_role_authority` PRIMARY KEY (`id`),
    INDEX `ix_sys_role_authority__role_id` (`role_id`),
    INDEX `ix_sys_role_authority__authority_id` (`authority_id`)
) COMMENT '角色-权限关联表';

--
-- 用户-角色关联表
--

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role`
(
    `id`         BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `role_id`    BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '角色ID',
    `user_id`    BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '用户ID',
    `active`     TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at` DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    CONSTRAINT `pk_sys_user_role` PRIMARY KEY (`id`),
    INDEX `ix_sys_user_role__role_id` (`role_id`),
    INDEX `ix_sys_user_role__user_id` (`user_id`)
) COMMENT '用户-角色关联表';

--
-- 实体关联表
--

DROP TABLE IF EXISTS `sys_entity_relation`;

CREATE TABLE `sys_entity_relation`
(
    `id`             BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `ancestor_id`    BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '祖先ID',
    `entity_id`      BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '实体ID',
    `parent_ind`     TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否直接上级',
    `relation_type`  VARCHAR(50)      NOT NULL DEFAULT '' COMMENT '关联类型',
    `relation_path`  VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '关联路径',
    `relation_index` VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '关联层级',
    `active`         TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`     BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`     DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    CONSTRAINT `pk_sys_entity_relation` PRIMARY KEY (`id`),
    INDEX `ix_sys_entity_relation__ancestor_id` (`ancestor_id`),
    INDEX `ix_sys_entity_relation__entity_id` (`entity_id`),
    INDEX `ix_sys_entity_relation__relation_type` (`relation_type`)
) COMMENT '实体关联表';

--
-- 用户登录会话记录
--

DROP TABLE IF EXISTS `sys_user_session`;

CREATE TABLE `sys_user_session`
(
    `id`                   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `user_id`              BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'User ID',
    `session_id`           VARCHAR(50)      NOT NULL DEFAULT '' COMMENT 'Session ID',
    `username`             VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '用户名',
    `host`                 VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '用户登录主机',
    `ua`                   VARCHAR(255)     NOT NULL DEFAULT '' COMMENT 'User Agent',
    `client_id`            VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '客户端编号',
    `client_version`       VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '客户端版本',
    `start_datetime`       DATETIME         NOT NULL DEFAULT NOW() COMMENT '会话开始时间',
    `last_access_datetime` DATETIME         NOT NULL DEFAULT NOW() COMMENT '最近访问时间',
    `end_datetime`         DATETIME         NULL COMMENT '会话结束时间',
    `success`              TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否成功登录',
    `active`               TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`           BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`           DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `last_modified_by`     BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at`     DATETIME         NOT NULL DEFAULT NOW() COMMENT '最后修改时间',
    `deleted_by`           BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`           DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT `pk_sys_user_session` PRIMARY KEY (`id`),
    INDEX `ix_sys_user_session__user_id` (`user_id`),
    INDEX `ix_sys_user_session__session_id` (`session_id`),
    INDEX `ix_sys_user_session__username` (`username`)
) COMMENT '用户登录会话记录';

--
-- 系统设置表
--

DROP TABLE IF EXISTS `sys_config`;

CREATE TABLE `sys_config`
(
    `id`               BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `title`            VARCHAR(100)     NOT NULL DEFAULT '' COMMENT '标题',
    `label`            VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '文本',
    `config_group`     VARCHAR(100)     NOT NULL DEFAULT '' COMMENT '分组',
    `config_key`       VARCHAR(100)     NOT NULL DEFAULT '' COMMENT '参数名',
    `config_value`     VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '参数值',
    `default_value`    VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '默认值',
    `description`      VARCHAR(250)     NOT NULL DEFAULT '' COMMENT '备注',
    `help`             VARCHAR(250)     NOT NULL DEFAULT '' COMMENT '帮助信息',
    `source`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '数据来源',
    `active`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`       DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `last_modified_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at` DATETIME         NOT NULL DEFAULT NOW() COMMENT '最后修改时间',
    `deleted_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`       DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT `pk_sys_config` PRIMARY KEY (`id`),
    INDEX `ix_sys_config__key` (`config_key`),
    INDEX `ix_sys_config__group` (`config_group`)
) COMMENT '系统设置表';

--
-- 语言表
--

DROP TABLE IF EXISTS `sys_lang`;

CREATE TABLE `sys_lang`
(
    `id`          BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `code`        VARCHAR(50)      NOT NULL DEFAULT '' COMMENT '编号',
    `lang`        VARCHAR(50)      NOT NULL DEFAULT '' COMMENT '语言编码',
    `country`     VARCHAR(50)      NOT NULL DEFAULT '' COMMENT '地区编码',
    `label`       VARCHAR(250)     NOT NULL DEFAULT '' COMMENT '文本',
    `description` VARCHAR(250)     NOT NULL DEFAULT '' COMMENT '备注',
    `default_ind` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '默认语言',
    `active`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    CONSTRAINT `pk_sys_lang` PRIMARY KEY (`id`),
    INDEX `ix_sys_lang__code` (`code`)
) COMMENT '语言表';

--
-- 多语言文本表
--

DROP TABLE IF EXISTS `sys_label`;

CREATE TABLE `sys_label`
(
    `id`                     BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `group`                  VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '分组',
    `code`                   VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '多语言标识',
    `zh_label`               VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '简体中文',
    `zh_label_static_ind`    TINYINT          NOT NULL DEFAULT 0 COMMENT '简体中文文本是否是静态文本，不自动翻译',
    `zh_tw_label`            VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '繁体中文',
    `zh_tw_label_static_ind` TINYINT          NOT NULL DEFAULT 0 COMMENT '简体中文文本是否是静态文本，不自动翻译',
    `en_label`               VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '英语',
    `en_label_static_ind`    TINYINT          NOT NULL DEFAULT 0 COMMENT '英语文本是否是静态文本，不自动翻译',
    `fr_label`               VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '法语',
    `fr_label_static_ind`    TINYINT          NOT NULL DEFAULT 0 COMMENT '法语文本是否是静态文本，不自动翻译',
    `ja_label`               VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '日语',
    `ja_label_static_ind`    TINYINT          NOT NULL DEFAULT 0 COMMENT '日语文本是否是静态文本，不自动翻译',
    `active`                 TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`             BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`             DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `last_modified_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at`       DATETIME         NOT NULL DEFAULT NOW() COMMENT '最后修改时间',
    `deleted_by`             BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`             DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT `pk_sys_label` PRIMARY KEY (`id`),
    INDEX `ix_sys_label__group` (`group`),
    INDEX `ix_sys_label__code` (`code`)
) COMMENT '多语言文本表';

--
-- 实体多语言文本表
--

DROP TABLE IF EXISTS `sys_entity_label`;

CREATE TABLE `sys_entity_label`
(
    `id`               BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `class_name`       VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '分组',
    `property_name`    VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '多语言标识',
    `zh_label`         VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '简体中文',
    `zh_tw_label`      VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '繁体中文',
    `en_label`         VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '英语',
    `fr_label`         VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '法语',
    `ja_label`         VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '日语',
    `active`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`       DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `last_modified_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at` DATETIME         NOT NULL DEFAULT NOW() COMMENT '最后修改时间',
    `deleted_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`       DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT `pk_sys_entity_label` PRIMARY KEY (`id`),
    INDEX `ix_sys_entity_label__code` (`class_name`, `property_name`)
) COMMENT '实体多语言文本表';

--
-- 目录类型表
--

DROP TABLE IF EXISTS `sys_catalog_type`;

CREATE TABLE `sys_catalog_type`
(
    `id`               BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `code`             VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '编号',
    `title`            VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '名称',
    `description`      VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '备注',
    `status`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '发布状态',
    `active`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_at`       DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `created_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `last_modified_at` DATETIME         NOT NULL DEFAULT NOW() COMMENT '最后修改时间',
    `last_modified_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    CONSTRAINT `pk_sys_catalog_type` PRIMARY KEY (`id`),
    INDEX `ix_sys_catalog_type__code` (`code`)
) COMMENT '目录类型表';

--
-- 目录表
--

DROP TABLE IF EXISTS `sys_catalog`;

CREATE TABLE `sys_catalog`
(
    `id`               BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `type_id`          BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '分类类型ID',
    `code`             VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '编号',
    `title`            VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '标题',
    `description`      VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '简介',
    `root_ind`         TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否顶层',
    `source`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '数据来源',
    `active`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`       DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `last_modified_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at` DATETIME         NOT NULL DEFAULT NOW() COMMENT '最后修改时间',
    `deleted_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`       DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT `pk_sys_catalog` PRIMARY KEY (`id`),
    INDEX `ix_sys_catalog__type_id` (`type_id`),
    INDEX `ix_sys_catalog__code` (`code`)
) COMMENT '目录表';

--
-- 目录分类关联表
--

DROP TABLE IF EXISTS `sys_catalog_relation`;

CREATE TABLE `sys_catalog_relation`
(
    `id`             BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `ancestor_id`    BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '祖先ID',
    `entity_id`      BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '实体ID',
    `parent_ind`     TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否直接上级',
    `relation_type`  VARCHAR(50)      NOT NULL DEFAULT '' COMMENT '关联类型',
    `relation_path`  VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '关联路径',
    `relation_index` VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '关联层级',
    `active`         TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`     BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`     DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    CONSTRAINT `pk_sys_catalog_relation` PRIMARY KEY (`id`),
    INDEX `ix_sys_catalog_relation__relation_type` (`relation_type`),
    INDEX `ix_sys_catalog_relation__ancestor_id` (`ancestor_id`),
    INDEX `ix_sys_catalog_relation__entity_id` (`entity_id`)
) COMMENT '目录分类关联表';

--
-- 字典类型表
--

DROP TABLE IF EXISTS `sys_dictionary_type`;

CREATE TABLE `sys_dictionary_type`
(
    `id`               BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `code`             VARCHAR(100)     NOT NULL DEFAULT '' COMMENT '编号',
    `title`            VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '标题',
    `label`            VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '文本',
    `description`      VARCHAR(250)     NOT NULL DEFAULT '' COMMENT '备注',
    `source`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '数据来源',
    `active`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`       DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `last_modified_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at` DATETIME         NOT NULL DEFAULT NOW() COMMENT '最后修改时间',
    `deleted_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`       DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT `pk_sys_dictionary_type` PRIMARY KEY (`id`)
) COMMENT '字典类型表';

--
-- 字典分组表
--

DROP TABLE IF EXISTS `sys_dictionary_group`;

CREATE TABLE `sys_dictionary_group`
(
    `id`                 BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `dictionary_type_id` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '字典类型ID',
    `sort_order`         INT UNSIGNED     NOT NULL DEFAULT 999 COMMENT '序号',
    `code`               VARCHAR(100)     NOT NULL DEFAULT '' COMMENT '编号',
    `title`              VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '标题',
    `label`              VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '文本',
    `description`        VARCHAR(250)     NOT NULL DEFAULT '' COMMENT '备注',
    `source`             TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '数据来源',
    `active`             TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`         BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`         DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `last_modified_by`   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at`   DATETIME         NOT NULL DEFAULT NOW() COMMENT '最后修改时间',
    `deleted_by`         BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`         DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT `pk_sys_dictionary_group` PRIMARY KEY (`id`),
    INDEX `ix_sys_dictionary_group__dictionary_type_id` (`dictionary_type_id`)
) COMMENT '字典分组表';

--
-- 字典明细表
--

DROP TABLE IF EXISTS `sys_dictionary_item`;

CREATE TABLE `sys_dictionary_item`
(
    `id`                  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `dictionary_type_id`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '字典类型ID',
    `dictionary_group_id` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '字典分组ID',
    `sort_order`          INT UNSIGNED     NOT NULL DEFAULT 999 COMMENT '序号',
    `source`              TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '数据来源',
    `active`              TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`          BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`          DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `last_modified_by`    BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at`    DATETIME         NOT NULL DEFAULT NOW() COMMENT '最后修改时间',
    `deleted_by`          BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`          DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT `pk_sys_dictionary_item` PRIMARY KEY (`id`),
    INDEX `ix_sys_dictionary_item__dictionary_type_id` (`dictionary_type_id`),
    INDEX `ix_sys_dictionary_item__dictionary_group_id` (`dictionary_group_id`)
) COMMENT '字典明细表';

--
-- 字典关联表
--

DROP TABLE IF EXISTS `sys_dictionary_relation`;

CREATE TABLE `sys_dictionary_relation`
(
    `id`                  BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT 'ID',
    `dictionary_type_id`  BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '字典类型ID',
    `dictionary_group_id` BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '字典分组ID',
    `dictionary_item_id`  BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '字典明细ID',
    `target_type`         VARCHAR(50)     NOT NULL DEFAULT '' COMMENT '目标类型',
    `target_id`           BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '目标实体',
    `created_by`          BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`          DATETIME        NOT NULL DEFAULT NOW() COMMENT '创建时间',
    CONSTRAINT `pk_sys_dictionary_relation` PRIMARY KEY (`id`),
    INDEX `ix_sys_dictionary_relation__dictionary_type_id` (`dictionary_type_id`),
    INDEX `ix_sys_dictionary_relation__dictionary_group_id` (`dictionary_group_id`),
    INDEX `ix_sys_dictionary_relation__dictionary_item_id` (`dictionary_item_id`),
    INDEX `ix_sys_dictionary_relation__target_type` (`target_type`),
    INDEX `ix_sys_dictionary_relation__target_id` (`target_id`)
) COMMENT '字典关联表';

--
-- 标签类型表
--

DROP TABLE IF EXISTS `sys_tag_type`;

CREATE TABLE `sys_tag_type`
(
    `id`               BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `code`             VARCHAR(100)     NOT NULL DEFAULT '' COMMENT '编号',
    `title`            VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '标题',
    `label`            VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '文本',
    `description`      VARCHAR(250)     NOT NULL DEFAULT '' COMMENT '备注',
    `source`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '数据来源',
    `active`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`       DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `last_modified_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at` DATETIME         NOT NULL DEFAULT NOW() COMMENT '最后修改时间',
    `deleted_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`       DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT `pk_sys_tag_type` PRIMARY KEY (`id`),
    INDEX `ix_sys_tag_type__code` (`code`)
) COMMENT '标签类型表';

--
-- 标签表
--

DROP TABLE IF EXISTS `sys_tag`;

CREATE TABLE `sys_tag`
(
    `id`               BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `tag_type_id`      BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '标签类型ID',
    `title`            VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '标题',
    `description`      VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '备注',
    `sort_order`       INT UNSIGNED     NOT NULL DEFAULT 999 COMMENT '序号',
    `source`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '数据来源',
    `active`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`       DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `last_modified_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at` DATETIME         NOT NULL DEFAULT NOW() COMMENT '最后修改时间',
    `deleted_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`       DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT `pk_sys_tag` PRIMARY KEY (`id`),
    INDEX `ix_sys_tag__tag_type_id` (`tag_type_id`)
) COMMENT '标签表';

--
-- 标签关联表
--

DROP TABLE IF EXISTS `sys_tag_relation`;

CREATE TABLE `sys_tag_relation`
(
    `id`          BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT 'ID',
    `tag_type_id` BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '标签类型ID',
    `tag_id`      BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '标签ID',
    `target_type` VARCHAR(50)     NOT NULL DEFAULT '' COMMENT '目标类型',
    `target_id`   BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '目标实体',
    `created_by`  BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME        NOT NULL DEFAULT NOW() COMMENT '创建时间',
    CONSTRAINT `pk_sys_tag_relation` PRIMARY KEY (`id`),
    INDEX `ix_sys_tag_relation__tag_type_id` (`tag_type_id`),
    INDEX `ix_sys_tag_relation__tag_id` (`tag_id`),
    INDEX `ix_sys_tag_relation__target_type` (`target_type`),
    INDEX `ix_sys_tag_relation__target_id` (`target_id`)
) COMMENT '标签关联表';

--
-- 附件类型表
--

DROP TABLE IF EXISTS `sys_attachment_type`;

CREATE TABLE `sys_attachment_type`
(
    `id`               BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `code`             VARCHAR(100)     NOT NULL DEFAULT '' COMMENT '编号',
    `title`            VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '标题',
    `label`            VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '文本',
    `description`      VARCHAR(250)     NOT NULL DEFAULT '' COMMENT '备注',
    `source`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '数据来源',
    `active`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`       DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `last_modified_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at` DATETIME         NOT NULL DEFAULT NOW() COMMENT '最后修改时间',
    `deleted_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`       DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT `pk_sys_attachment_type` PRIMARY KEY (`id`),
    INDEX `ix_sys_attachment_type__code` (`code`)
) COMMENT '附件类型表';

--
-- 附件表
--

DROP TABLE IF EXISTS `sys_attachment`;

CREATE TABLE `sys_attachment`
(
    `id`                BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `attachment_type`   VARCHAR(180)     NOT NULL DEFAULT '' COMMENT '附件类型',
    `original_filename` VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '原始文件名',
    `filename`          VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '文件名',
    `size`              BIGINT           NOT NULL DEFAULT 0 COMMENT '文件大小',
    `url`               VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '文件链接',
    `extra`             VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '附加信息',
    `file_key`          VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '文件标识',
    `storage_type`      VARCHAR(100)     NOT NULL DEFAULT '' COMMENT '文件大小',
    `access_type`       VARCHAR(100)     NOT NULL DEFAULT '' COMMENT '文件访问类型',
    `active`            TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`        BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`        DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `last_modified_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '最后修改时间',
    `deleted_by`        BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`        DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT `pk_sys_attachment` PRIMARY KEY (`id`),
    INDEX `ix_sys_attachment__type` (`attachment_type`)
) COMMENT '附件表';

--
-- 附件关联表
--

DROP TABLE IF EXISTS `sys_attachment_relation`;

CREATE TABLE `sys_attachment_relation`
(
    `id`            BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT 'ID',
    `attachment_id` BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '附件ID',
    `target_type`   VARCHAR(50)     NOT NULL DEFAULT '' COMMENT '目标类型',
    `target_id`     BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '目标实体',
    `created_by`    BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`    DATETIME        NOT NULL DEFAULT NOW() COMMENT '创建时间',
    CONSTRAINT `pk_sys_attachment_relation` PRIMARY KEY (`id`),
    INDEX `ix_sys_attachment_relation__attachment_id` (`attachment_id`),
    INDEX `ix_sys_attachment_relation__target_type` (`target_type`),
    INDEX `ix_sys_attachment_relation__target_id` (`target_id`)
) COMMENT '附件关联表';

--
-- 系统操作日志表
--

DROP TABLE IF EXISTS `sys_operation_log`;

CREATE TABLE `sys_operation_log`
(
    `id`                    BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `class_name`            VARCHAR(250) COMMENT '类名',
    `method_name`           VARCHAR(250) COMMENT '方法名',
    `request_ip`            VARCHAR(250) COMMENT '请求IP',
    `request_ua`            VARCHAR(250) COMMENT '请求UA',
    `request_uri`           TEXT COMMENT '请求地址',
    `http_method`           VARCHAR(250) COMMENT '请求类型',
    `request_params`        TEXT COMMENT '请求参数',
    `request_header_params` TEXT COMMENT '请求头',
    `annotation_params`     TEXT COMMENT '注解参数',
    `start_time`            DATETIME         NULL COMMENT '开始时间',
    `end_time`              DATETIME         NULL COMMENT '结束时间',
    `exec_time`             LONG COMMENT '执行时长',
    `details`               TEXT COMMENT '日志详情',
    `exception`             TEXT COMMENT '异常信息',
    `active`                TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`            BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`            DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    CONSTRAINT `pk_sys_operation_log` PRIMARY KEY (`id`)
) COMMENT '系统操作日志表';

--
-- 验证码发送日志表
--

DROP TABLE IF EXISTS `sys_captcha_log`;

CREATE TABLE `sys_captcha_log`
(
    `id`                  BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT 'ID',
    `captcha_type`        VARCHAR(50)     NOT NULL DEFAULT '' COMMENT '验证码类型',
    `captcha_key`         VARCHAR(150)    NOT NULL DEFAULT '' COMMENT '验证码标识',
    `captcha_value`       VARCHAR(150)    NOT NULL DEFAULT '' COMMENT '验证码',
    `email`               VARCHAR(150)    NOT NULL DEFAULT '' COMMENT '电子邮箱',
    `mobile_country_code` VARCHAR(150)    NOT NULL DEFAULT '' COMMENT '手机区位码',
    `mobile_number`       VARCHAR(150)    NOT NULL DEFAULT 0 COMMENT '手机号码',
    `created_by`          BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`          DATETIME        NOT NULL DEFAULT NOW() COMMENT '创建时间',
    CONSTRAINT `pk_sys_captcha_log` PRIMARY KEY (`id`)
) COMMENT '验证码发送日志表';

--
-- 消息类型表
--

DROP TABLE IF EXISTS `sys_message_type`;

CREATE TABLE `sys_message_type`
(
    `id`               BIGINT UNSIGNED  NOT NULL COMMENT 'ID',
    `code`             VARCHAR(150)     NOT NULL COMMENT '编号',
    `label`            VARCHAR(150)     NOT NULL COMMENT '多语言文本',
    `title`            VARCHAR(255)     NOT NULL COMMENT '标题',
    `description`      TEXT COMMENT '描述备注',
    `status`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '发布状态',
    `active`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`       DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `last_modified_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at` DATETIME         NOT NULL DEFAULT NOW() COMMENT '最后修改时间',
    `deleted_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`       DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT `pk_sys_message_type` PRIMARY KEY (`id`)
);
ALTER TABLE `sys_message_type`
    COMMENT '消息类型表';

--
-- 消息模版类型表
--

DROP TABLE IF EXISTS `sys_message_template_type`;

CREATE TABLE `sys_message_template_type`
(
    `id`               BIGINT UNSIGNED  NOT NULL COMMENT 'ID',
    `code`             VARCHAR(150)     NOT NULL COMMENT '编号',
    `label`            VARCHAR(150)     NOT NULL COMMENT '多语言文本',
    `title`            VARCHAR(255)     NOT NULL COMMENT '标题',
    `description`      TEXT             NULL COMMENT '描述备注',
    `status`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '发布状态',
    `active`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`       DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `last_modified_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at` DATETIME         NOT NULL DEFAULT NOW() COMMENT '最后修改时间',
    `deleted_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`       DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT `pk_sys_message_template_type` PRIMARY KEY (`id`)
);
ALTER TABLE `sys_message_type`
    COMMENT '消息模版类型表';

--
-- 消息模板表
--

DROP TABLE IF EXISTS `sys_message_template`;

CREATE TABLE `sys_message_template`
(
    `id`               BIGINT UNSIGNED  NOT NULL COMMENT 'ID',
    `type_id`          BIGINT UNSIGNED  NOT NULL COMMENT '消息类型ID',
    `template_type_id` BIGINT UNSIGNED  NOT NULL COMMENT '模板类型ID',
    `content`          TEXT             NOT NULL COMMENT '模板',
    `status`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '发布状态',
    `active`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`       DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `last_modified_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at` DATETIME         NOT NULL DEFAULT NOW() COMMENT '最后修改时间',
    `deleted_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`       DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT `pk_sys_message_template_id` PRIMARY KEY (`id`),
    INDEX `ix_sys_message_template__type_id` (`type_id`),
    INDEX `ix_sys_message_template__template_type_id` (`template_type_id`)
);
ALTER TABLE `sys_message_template`
    COMMENT '消息模板表';

--
-- 消息表
--

DROP TABLE IF EXISTS `sys_message`;

CREATE TABLE `sys_message`
(
    `id`                   BIGINT UNSIGNED  NOT NULL COMMENT 'ID',
    `type_id`              BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '消息类型ID',
    `target_id`            BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '目标ID',
    `target_type`          VARCHAR(100) COMMENT '目标类型',
    `subject`              VARCHAR(255) COMMENT '标题',
    `content`              TEXT COMMENT '内容',
    `data`                 TEXT COMMENT '数据，一般保存JSON格式的参数',
    `target_sent_datetime` DATETIME COMMENT '目标发送时间',
    `sent_datetime`        DATETIME COMMENT '发送时间',
    `attempt`              TINYINT COMMENT '尝试发送次数',
    `status`               TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '发布状态',
    `active`               TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`           BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`           DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `last_modified_by`     BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at`     DATETIME         NOT NULL DEFAULT NOW() COMMENT '最后修改时间',
    `deleted_by`           BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`           DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT `pk_sys_message` PRIMARY KEY (`id`),
    INDEX `ix_sys_message__type_id` (`type_id`),
    INDEX `ix_sys_message__target` (`target_id`, `target_type`)
);
ALTER TABLE `sys_message`
    COMMENT '消息表';

--
-- 消息用户表
--

DROP TABLE IF EXISTS `sys_message_user`;

CREATE TABLE `sys_message_user`
(
    `id`                  BIGINT UNSIGNED  NOT NULL COMMENT 'ID',
    `message_id`          BIGINT UNSIGNED  NOT NULL COMMENT '消息ID',
    `user_id`             BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '用户ID',
    `type_id`             BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '类型ID',
    `display_name`        VARCHAR(255) COMMENT '姓名',
    `username`            VARCHAR(255) COMMENT '账号',
    `email`               VARCHAR(255) COMMENT '邮箱',
    `mobile_country_code` VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '手机区位码',
    `mobile_number`       VARCHAR(150)     NOT NULL DEFAULT 0 COMMENT '手机号码',
    `active`              TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`          BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`          DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `last_modified_by`    BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at`    DATETIME         NOT NULL DEFAULT NOW() COMMENT '最后修改时间',
    `deleted_by`          BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`          DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT `pk_sys_message_user` PRIMARY KEY (`id`),
    INDEX `ix_sys_message_user__message_id` (`message_id`),
    INDEX `ix_sys_message_user__user` (`user_id`, `type_id`)
);
ALTER TABLE `sys_message_user`
    COMMENT '消息用户表';

--
-- 消息内容表
--

DROP TABLE IF EXISTS `sys_message_content`;

CREATE TABLE `sys_message_content`
(
    `id`               BIGINT UNSIGNED  NOT NULL COMMENT 'ID',
    `message_id`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '消息ID',
    `template_type_id` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '模板类型ID',
    `content`          TEXT COMMENT '消息内容',
    `resp`             TEXT COMMENT '响应内容',
    `exception`        TEXT COMMENT '异常内容',
    `sent_datetime`    DATETIME COMMENT '发送时间',
    `status`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '发布状态',
    `active`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`       DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `last_modified_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at` DATETIME         NOT NULL DEFAULT NOW() COMMENT '最后修改时间',
    `deleted_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`       DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT `pk_sys_message_content` PRIMARY KEY (`id`),
    INDEX `ix_sys_message_content__message_id` (`message_id`),
    INDEX `ix_sys_message_content__template_type_id` (`template_type_id`)
);
ALTER TABLE `sys_message_content`
    COMMENT '消息内容表';

--
-- 消息发送历史记录表
--

DROP TABLE IF EXISTS `sys_message_history`;

CREATE TABLE `sys_message_history`
(
    `id`          BIGINT UNSIGNED  NOT NULL COMMENT 'ID',
    `type_id`     BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '消息类型ID',
    `message_id`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '消息ID',
    `user_id`     BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '用户ID',
    `target_id`   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '目标ID',
    `target_type` VARCHAR(100) COMMENT '目标类型',
    `active`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    CONSTRAINT `pk_sys_message_history` PRIMARY KEY (`id`),
    INDEX `ix_sys_message_history__type_id` (`type_id`),
    INDEX `ix_sys_message_history__message_id` (`message_id`),
    INDEX `ix_sys_message_history__user_id` (`user_id`),
    INDEX `ix_sys_message_history__target` (`target_id`, `target_type`)
);
ALTER TABLE `sys_message_history`
    COMMENT '消息发送历史记录表';

--
-- 系统通知表
--

CREATE TABLE `sys_notice`
(
    `id`               BIGINT UNSIGNED  NOT NULL COMMENT 'ID',
    `subject`          VARCHAR(100)     NOT NULL DEFAULT '' COMMENT '通知标题',
    `content`          VARCHAR(100)     NOT NULL DEFAULT '' COMMENT '通知内容',
    `recipient_id`     BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '收件人ID',
    `sender_id`        BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '发件人ID',
    `read_ind`         TINYINT          NOT NULL DEFAULT 0 COMMENT '是否已读',
    `read_datetime`    DATETIME         NULL COMMENT '阅读时间',
    `active`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`       DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `last_modified_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at` DATETIME         NOT NULL DEFAULT NOW() COMMENT '最后修改时间',
    `deleted_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`       DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT `pk_sys_notice` PRIMARY KEY (`id`),
    INDEX `ix_sys_notice__sender_id` (`sender_id`),
    INDEX `ix_sys_notice__recipient_id` (`recipient_id`)
);
ALTER TABLE `sys_notice`
    COMMENT '系统通知表';

--
-- 宣传栏
--

DROP TABLE IF EXISTS `sys_poster`;

CREATE TABLE `sys_poster`
(
    `id`               BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `title`            VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '标题',
    `description`      VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '备注',
    `sort_order`       INT UNSIGNED     NOT NULL DEFAULT 999 COMMENT '序号',
    `active`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`       DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `last_modified_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at` DATETIME         NOT NULL DEFAULT NOW() COMMENT '最后修改时间',
    `deleted_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`       DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT `pk_sys_poster` PRIMARY KEY (`id`)
) COMMENT '宣传栏';

--
-- 资讯表
--

DROP TABLE IF EXISTS `sys_announcement`;

CREATE TABLE `sys_announcement`
(
    `id`               BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `title`            VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '标题',
    `content`          TEXT             NULL COMMENT '内容',
    `description`      VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '备注',
    `active`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`       DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `last_modified_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at` DATETIME         NOT NULL DEFAULT NOW() COMMENT '最后修改时间',
    `deleted_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`       DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT `pk_sys_announcement` PRIMARY KEY (`id`)
) COMMENT '资讯表';

--
-- 产品表
--

DROP TABLE IF EXISTS `sys_product`;

CREATE TABLE `sys_product`
(
    `id`               BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `title`            VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '标题',
    `content`          TEXT             NULL COMMENT '内容',
    `description`      VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '备注',
    `sort_order`       INT UNSIGNED     NOT NULL DEFAULT 999 COMMENT '序号',
    `active`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`       DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `last_modified_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at` DATETIME         NOT NULL DEFAULT NOW() COMMENT '最后修改时间',
    `deleted_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`       DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT `pk_sys_product` PRIMARY KEY (`id`)
) COMMENT '产品表';

-- =====================================================================================================================
-- OAuth
-- =====================================================================================================================

--
-- 客户端
--

DROP TABLE IF EXISTS `sys_client`;

CREATE TABLE `sys_client`
(
    `id`                            BIGINT UNSIGNED  NOT NULL COMMENT 'ID',
    `client_id`                     VARCHAR(150)     NOT NULL DEFAULT '' COMMENT 'Client ID',
    `client_id_issued_at`           DATETIME                  DEFAULT NULL,
    `client_secret`                 VARCHAR(255)     NOT NULL DEFAULT '' COMMENT 'Client Secret',
    `client_secret_expires_at`      DATETIME                  DEFAULT NULL,
    `client_name`                   VARCHAR(255)     NOT NULL DEFAULT '' COMMENT 'Client Name',
    `client_authentication_methods` VARCHAR(1000)    NOT NULL DEFAULT '' COMMENT 'Client Authentication Methods',
    `authorization_grant_types`     VARCHAR(255)     NOT NULL DEFAULT '' COMMENT 'Authorization Grant Types',
    `redirect_uris`                 VARCHAR(255)     NOT NULL DEFAULT '' COMMENT 'Redirect Uris',
    `post_logout_redirect_uris`     VARCHAR(255)     NOT NULL DEFAULT '' COMMENT 'Redirect Uris',
    `scopes`                        VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT 'Score',
    `client_settings`               VARCHAR(2000)    NOT NULL DEFAULT '',
    `token_settings`                VARCHAR(2000)    NOT NULL DEFAULT '',
    `description`                   VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '备注',
    `active`                        TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_at`                    DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `created_by`                    BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `last_modified_at`              DATETIME         NOT NULL DEFAULT NOW() COMMENT '最后修改时间',
    `last_modified_by`              BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `deleted_at`                    DATETIME         NOT NULL DEFAULT NOW() COMMENT '删除时间',
    `deleted_by`                    BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    CONSTRAINT `pk_sys_client` PRIMARY KEY (`id`),
    INDEX `ix_sys_client__client_id` (`client_id`)
) COMMENT '客户端';

--
-- 认证记录
--

DROP TABLE IF EXISTS `sys_authorization`;

CREATE TABLE `sys_authorization`
(
    `id`                            BIGINT UNSIGNED  NOT NULL COMMENT 'ID',
    `uuid`                          VARCHAR(200)     NOT NULL DEFAULT '',
    `client_id`                     VARCHAR(100)     NOT NULL DEFAULT '',
    `principal_name`                VARCHAR(200)              DEFAULT NULL,
    `authorization_grant_type`      VARCHAR(100)              DEFAULT NULL,
    `attributes`                    TEXT                      DEFAULT NULL,
    `state`                         VARCHAR(2000)             DEFAULT NULL,
    `authorization_code_value`      VARCHAR(2000)             DEFAULT NULL,
    `authorization_code_issued_at`  DATETIME                  DEFAULT NULL,
    `authorization_code_expires_at` DATETIME                  DEFAULT NULL,
    `authorization_code_metadata`   TEXT                      DEFAULT NULL,
    `access_token_value`            VARCHAR(5000)             DEFAULT NULL,
    `access_token_issued_at`        DATETIME                  DEFAULT NULL,
    `access_token_expires_at`       DATETIME                  DEFAULT NULL,
    `access_token_metadata`         TEXT                      DEFAULT NULL,
    `access_token_type`             VARCHAR(100)              DEFAULT NULL,
    `access_token_scopes`           VARCHAR(2000)             DEFAULT NULL,
    `oidc_id_token_value`           VARCHAR(2000)             DEFAULT NULL,
    `oidc_id_token_issued_at`       DATETIME                  DEFAULT NULL,
    `oidc_id_token_expires_at`      DATETIME                  DEFAULT NULL,
    `oidc_id_token_metadata`        TEXT                      DEFAULT NULL,
    `oidc_id_token_claims`          TEXT                      DEFAULT NULL,
    `refresh_token_value`           VARCHAR(2000)             DEFAULT NULL,
    `refresh_token_issued_at`       DATETIME                  DEFAULT NULL,
    `refresh_token_expires_at`      DATETIME                  DEFAULT NULL,
    `refresh_token_metadata`        TEXT                      DEFAULT NULL,
    `active`                        TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`                    BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`                    DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `last_modified_by`              BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at`              DATETIME         NOT NULL DEFAULT NOW() COMMENT '最后修改时间',
    `deleted_by`                    BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`                    DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT `pk_sys_authorization` PRIMARY KEY (`id`),
    INDEX `ix_sys_authorization__client_id` (`client_id`)
) COMMENT '认证记录';

--
-- 认证同意授权记录
--

DROP TABLE IF EXISTS `sys_authorization_consent`;

CREATE TABLE `sys_authorization_consent`
(
    `id`               BIGINT UNSIGNED  NOT NULL COMMENT 'ID',
    `uuid`             VARCHAR(100)     NOT NULL,
    `client_id`        VARCHAR(100)     NOT NULL,
    `principal_name`   VARCHAR(200)     NOT NULL,
    `authorities`      VARCHAR(2000)    NOT NULL,
    `active`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`       DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `last_modified_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at` DATETIME         NOT NULL DEFAULT NOW() COMMENT '最后修改时间',
    `deleted_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`       DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT `pk_sys_authorization_consent` PRIMARY KEY (`id`),
    INDEX `ix_sys_authorization_consent` (`client_id`, `principal_name`)
);
