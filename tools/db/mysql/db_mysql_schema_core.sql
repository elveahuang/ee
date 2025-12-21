-- =====================================================================================================================
-- 核心基础表
-- =====================================================================================================================

USE ee_platform;

--
-- 身份主体表
-- 用户体系和账号体系的上层抽象
-- 用户体系用于后台系统
-- 账号体系用于前台系统
--

DROP TABLE IF EXISTS `sys_identity`;

CREATE TABLE `sys_identity`
(
    `id`         BIGINT           NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `uuid`       VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '用户标识',
    `status`     TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态',
    `source`     TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '来源',
    `version`    BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`     TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at` DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at` DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at` DATETIME         NULL COMMENT '删除时间',
    INDEX `ix_sys_identity__active` (`active`),
    INDEX `ix_sys_identity__uuid` (`uuid`)
) COMMENT '身份主体表';

--
-- 租户表
--

DROP TABLE IF EXISTS `sys_tenant`;

CREATE TABLE `sys_tenant`
(
    `id`                     BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `code`                   VARCHAR(20)      NOT NULL DEFAULT '' COMMENT '租户编号',
    `title`                  VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '名称',
    `details`                LONGTEXT         NULL COMMENT '简介',
    `address`                VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '地址',
    `domain`                 VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '域名',
    `contact_user`           VARCHAR(50)      NOT NULL DEFAULT '' comment '联系人',
    `contact_phone`          VARCHAR(50)      NOT NULL DEFAULT '' comment '联系电话',
    `company_name`           VARCHAR(50)      NOT NULL DEFAULT '' comment '企业名称',
    `company_license_number` VARCHAR(50)      NOT NULL DEFAULT '' comment '统一社会信用代码',
    `registration_date`      DATETIME         NOT NULL DEFAULT NOW() COMMENT '注册时间',
    `expiration_date`        DATETIME         NOT NULL DEFAULT NOW() COMMENT '到期时间',
    `account_count`          INT              NOT NULL DEFAULT 0 COMMENT '租户用户数',
    `root_ind`               TINYINT          NOT NULL DEFAULT 0 COMMENT '是否顶层租户，顶层租户拥有最大的权限',
    `description`            VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '备注说明',
    `source`                 TINYINT UNSIGNED NOT NULL DEFAULT 2 COMMENT '来源',
    `status`                 TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态',
    `version`                BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`                 TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`             BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`             DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`             BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`             DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`             BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`             DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_tenant__active` (`active`),
    INDEX `ix_sys_tenant__code` (`code`)
) COMMENT '租户表';

--
-- 权限表
-- biz_type - TENANT - 租户套餐功能权限
-- biz_type - MEMBER - 会员套餐功能权限
--

DROP TABLE IF EXISTS `sys_authority`;

CREATE TABLE `sys_authority`
(
    `id`                   BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `parent_id`            BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'Parent ID',
    `code`                 VARCHAR(100)     NOT NULL DEFAULT '' COMMENT '编码',
    `title`                VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '标题',
    `label`                VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '多语言文本',
    `idx`                  INT UNSIGNED     NOT NULL DEFAULT 999 COMMENT '序号',
    `description`          VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '备注',
    `authority_type`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '权限类型',
    `authority_group_type` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '权限分组类型',
    `authority_biz_type`   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '权限业务类型',
    `status`               TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态',
    `source`               TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '来源',
    `version`              BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`               TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`           BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`           DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`           BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`           DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`           BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`           DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_authority__parent_id` (`parent_id`),
    INDEX `ix_sys_authority__code` (`code`)
) COMMENT '权限表';

--
-- 支付类型表
--

DROP TABLE IF EXISTS `sys_pay_type`;

CREATE TABLE `sys_pay_type`
(
    `id`          BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `code`        VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '编号',
    `title`       VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '名称',
    `label`       VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '文本',
    `icon_name`   VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '图标',
    `icon_color`  VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '图标颜色',
    `coin_ind`    TINYINT          NOT NULL DEFAULT 0 COMMENT '是否是加密货币支付',
    `coin_wallet` VARCHAR(1000)    NOT NULL DEFAULT '' COMMENT '钱包地址，仅对数字货币支付有效',
    `callback`    VARCHAR(1000)    NOT NULL DEFAULT '' COMMENT '回调服务类',
    `idx`         INT UNSIGNED     NOT NULL DEFAULT 999 COMMENT '序号',
    `description` VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '备注',
    `source`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '来源',
    `status`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '发布状态',
    `version`     BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`  DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_pay_type__code` (`code`)
) COMMENT '支付类型表';

--
-- 订单类型表
--

DROP TABLE IF EXISTS `sys_order_type`;

CREATE TABLE `sys_order_type`
(
    `id`          BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `code`        VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '编号',
    `title`       VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '名称',
    `callback`    VARCHAR(1000)    NOT NULL DEFAULT '' COMMENT '回调服务类',
    `description` VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '备注',
    `source`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '来源',
    `status`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '发布状态',
    `version`     BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`  DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_order_type__code` (`code`)
) COMMENT '订单类型表';

--
-- 套餐表
--

DROP TABLE IF EXISTS `sys_package`;

CREATE TABLE `sys_package`
(
    `id`          BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `biz_type`    BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '业务类型',
    `code`        VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '编号',
    `title`       VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '标题',
    `label`       VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '文本',
    `privilege`   VARCHAR(1000)    NOT NULL DEFAULT '' COMMENT '特权',
    `default_ind` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否默认',
    `trial_ind`   TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否允许试用',
    `trial_limit` INT UNSIGNED     NOT NULL DEFAULT 60 COMMENT '试用时长，单位是自然天',
    `level`       INT UNSIGNED     NOT NULL DEFAULT 1 COMMENT '会员等级，等级越高显示优先级越高',
    `idx`         INT UNSIGNED     NOT NULL DEFAULT 999 COMMENT '序号',
    `description` VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '备注',
    `source`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '来源',
    `status`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态',
    `version`     BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`  DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_package__biz_type` (`biz_type`),
    INDEX `ix_sys_package__code` (`code`)
) COMMENT '套餐表';

--
-- 套餐明细表
--

DROP TABLE IF EXISTS `sys_package_item`;

CREATE TABLE `sys_package_item`
(
    `id`                    BIGINT UNSIGNED         NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `biz_type`              BIGINT UNSIGNED         NOT NULL DEFAULT 0 COMMENT '业务类型',
    `package_id`            BIGINT UNSIGNED         NOT NULL DEFAULT 0 COMMENT '套餐ID',
    `code`                  VARCHAR(150)            NOT NULL DEFAULT '' COMMENT '编号',
    `title`                 VARCHAR(150)            NOT NULL DEFAULT '' COMMENT '标题',
    `label`                 VARCHAR(150)            NOT NULL DEFAULT '' COMMENT '文本',
    `automatic_renewal_ind` TINYINT UNSIGNED        NOT NULL DEFAULT 0 COMMENT '是否自动续费',
    `list_price`            NUMERIC(10, 6) UNSIGNED NOT NULL DEFAULT 0 COMMENT '划线价格',
    `price`                 NUMERIC(10, 6) UNSIGNED NOT NULL DEFAULT 0 COMMENT '价格',
    `date_unit`             INT UNSIGNED            NOT NULL DEFAULT 0 COMMENT '单位',
    `date_value`            INT UNSIGNED            NOT NULL DEFAULT 0 COMMENT '单位',
    `idx`                   INT UNSIGNED            NOT NULL DEFAULT 999 COMMENT '序号',
    `description`           VARCHAR(255)            NOT NULL DEFAULT '' COMMENT '备注',
    `status`                TINYINT UNSIGNED        NOT NULL DEFAULT 1 COMMENT '发布状态',
    `version`               BIGINT                  NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`                TINYINT UNSIGNED        NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`            BIGINT UNSIGNED         NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`            DATETIME                NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`            BIGINT UNSIGNED         NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`            DATETIME                NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`            BIGINT UNSIGNED         NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`            DATETIME                NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_package_item__code` (`code`)
) COMMENT '套餐明细表';

--
-- 套餐-权限关联表
--

DROP TABLE IF EXISTS `sys_package_authority`;

CREATE TABLE `sys_package_authority`
(
    `id`           BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `biz_type`     BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '业务类型',
    `package_id`   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '权限ID',
    `authority_id` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `version`      BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`       TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`   DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_package_authority__authority_id` (`authority_id`),
    INDEX `ix_sys_package_authority__package_id` (`biz_type`, `package_id`)
) COMMENT '套餐-权限关联表';

--
-- 实体-权限关联表
--

DROP TABLE IF EXISTS `sys_entity_authority`;

CREATE TABLE `sys_entity_authority`
(
    `id`           BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`    BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `biz_type`     BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '业务类型',
    `entity_type`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '实体类型',
    `entity_id`    BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '实体ID',
    `authority_id` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '权限ID',
    `version`      BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`       TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`   DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_entity_authority__entity` (`biz_type`, `entity_type`, `entity_id`, `tenant_id`)
) COMMENT '实体-权限关联表';

--
-- 套餐实体关联表
-- entity_type  - TENANT     - 租户会员套餐
-- entity_type  - ACCOUNT    - 账号会员套餐
--

DROP TABLE IF EXISTS `sys_entity_package`;

CREATE TABLE `sys_entity_package`
(
    `id`                BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`         BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `biz_type`          BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '业务类型',
    `entity_type`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '实体类型',
    `entity_id`         BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '实体ID',
    `package_id`        BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '权限ID',
    `trial_start_date`  DATETIME COMMENT '会员试用开始时间',
    `trial_end_date`    DATETIME COMMENT '会员试用结束时间',
    `registration_date` DATETIME COMMENT '会员注册时间',
    `expiration_date`   DATETIME COMMENT '会员到期时间',
    `description`       VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '备注说明',
    `version`           BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`            TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`        BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`        DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`        BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`        DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`        BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`        DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_package_entity__tenant_id` (`tenant_id`),
    INDEX `ix_sys_package_entity__entity_id` (`biz_type`, `entity_type`, `entity_id`),
    INDEX `ix_sys_package_entity__package_id` (`package_id`)
) COMMENT '套餐实体关联表';

--
-- 实体套餐开通记录表
--

DROP TABLE IF EXISTS `sys_package_log`;

CREATE TABLE `sys_package_log`
(
    `id`          BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `biz_type`    BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '业务类型',
    `entity_type` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '实体类型',
    `entity_id`   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '实体ID',
    `package_id`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '套餐ID',
    `order_id`    BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '关联订单ID',
    `quota`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '额度',
    `description` VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '备注',
    `log_type`    TINYINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '日志类型',
    `version`     BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`  DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_package_log__tenant_id` (`tenant_id`),
    INDEX `ix_sys_package_log__entity_id` (`biz_type`, `entity_type`, `entity_id`),
    INDEX `ix_sys_package_log__package_id` (`package_id`)
) COMMENT '实体套餐开通记录表';

--
-- 订单表
--

DROP TABLE IF EXISTS `sys_order`;

CREATE TABLE `sys_order`
(
    `id`            BIGINT UNSIGNED         NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`     BIGINT UNSIGNED         NOT NULL DEFAULT 0 COMMENT '租户ID',
    `entity_type`   BIGINT UNSIGNED         NOT NULL DEFAULT 0 COMMENT '实体类型',
    `entity_id`     BIGINT UNSIGNED         NOT NULL DEFAULT 0 COMMENT '实体ID',
    `order_type_id` BIGINT UNSIGNED         NOT NULL DEFAULT 0 COMMENT '订单类型ID',
    `pay_type_id`   BIGINT UNSIGNED         NOT NULL DEFAULT 0 COMMENT '支付类型ID',
    `order_sn`      VARCHAR(50)             NOT NULL DEFAULT '' COMMENT '订单编号',
    `total_amount`  NUMERIC(10, 6) UNSIGNED NOT NULL DEFAULT 0 COMMENT '订单总金额',
    `pay_amount`    NUMERIC(10, 6) UNSIGNED NOT NULL DEFAULT 0 COMMENT '支付金额',
    `pay_type`      VARCHAR(50)             NOT NULL DEFAULT '' COMMENT '支付方式',
    `pay_time`      DATETIME                         DEFAULT NULL COMMENT '支付时间',
    `pay_status`    VARCHAR(50)             NOT NULL DEFAULT '' COMMENT '支付状态',
    `status`        TINYINT UNSIGNED        NOT NULL DEFAULT 1 COMMENT '状态',
    `source`        TINYINT UNSIGNED        NOT NULL DEFAULT 1 COMMENT '订单来源',
    `version`       BIGINT                  NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`        TINYINT UNSIGNED        NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`    BIGINT UNSIGNED         NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`    DATETIME                NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`    BIGINT UNSIGNED         NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`    DATETIME                NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`    BIGINT UNSIGNED         NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`    DATETIME                NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_order__user_id` (`entity_id`)
) COMMENT '订单表';

--
-- 订单明细表
--

DROP TABLE IF EXISTS `sys_order_item`;

CREATE TABLE `sys_order_item`
(
    `id`           BIGINT UNSIGNED         NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`    BIGINT UNSIGNED         NOT NULL DEFAULT 0 COMMENT '租户ID',
    `entity_type`  BIGINT UNSIGNED         NOT NULL DEFAULT 0 COMMENT '实体类型',
    `entity_id`    BIGINT UNSIGNED         NOT NULL DEFAULT 0 COMMENT '实体ID',
    `order_id`     BIGINT UNSIGNED         NOT NULL DEFAULT 0 COMMENT '订单ID',
    `item_id`      BIGINT UNSIGNED         NOT NULL DEFAULT 0 COMMENT '商品ID',
    `quantity`     INT UNSIGNED            NOT NULL DEFAULT 0 COMMENT '商品数量',
    `price`        NUMERIC(10, 6) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品单价',
    `total_amount` NUMERIC(10, 6) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品总金额',
    `version`      BIGINT                  NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`       TINYINT UNSIGNED        NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`   BIGINT UNSIGNED         NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`   DATETIME                NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`   BIGINT UNSIGNED         NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`   DATETIME                NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`   BIGINT UNSIGNED         NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`   DATETIME                NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_order_item__order_id` (`order_id`),
    INDEX `ix_sys_order_item__entity_id` (`entity_id`),
    INDEX `ix_sys_order_item__tenant_id` (`tenant_id`)
) COMMENT '订单明细表';

--
-- 订单支付表
--

DROP TABLE IF EXISTS `sys_order_pay`;

CREATE TABLE `sys_order_pay`
(
    `id`               BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`        BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `order_id`         BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '订单ID',
    `pay_type_id`      BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '支付类型ID',
    `pay_type`         VARCHAR(200)     NOT NULL DEFAULT '' COMMENT '支付方式',
    `pay_sn`           VARCHAR(128)     NOT NULL DEFAULT '' COMMENT '支付流水号，加密货币支付时对应交易哈希',
    `pay_address`      VARCHAR(200)     NOT NULL DEFAULT '' COMMENT '钱包地址，仅对加密货币支付有效',
    `pay_amount`       DECIMAL(10, 6)   NOT NULL DEFAULT 0.00 COMMENT '应付总额',
    `pay_status`       VARCHAR(100)     NOT NULL DEFAULT '' COMMENT '支付状态',
    `pay_time`         DATETIME         NULL     DEFAULT NULL COMMENT '支付时间',
    `pay_subject`      VARCHAR(200)     NOT NULL DEFAULT '' COMMENT '交易内容',
    `confirm_time`     DATETIME         NULL     DEFAULT NULL COMMENT '确认时间',
    `callback_time`    DATETIME         NULL     DEFAULT NULL COMMENT '回调时间',
    `callback_content` VARCHAR(500)     NOT NULL DEFAULT '' COMMENT '回调内容',
    `version`          BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`       DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`       DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`       DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_order_pay__order_id` (`order_id`),
    INDEX `ix_sys_order_pay__pay_type_id` (`pay_type_id`),
    INDEX `ix_sys_order_pay__pay_type` (`pay_type`),
    INDEX `ix_sys_order_pay__pay_sn` (`pay_sn`)
) COMMENT '订单支付表';

--
-- 订单日志表
--

DROP TABLE IF EXISTS `sys_order_log`;

CREATE TABLE `sys_order_log`
(
    `id`          BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `entity_type` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '实体类型',
    `entity_id`   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '实体ID',
    `order_id`    BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '订单ID',
    `details`     TEXT COMMENT '日志详情',
    `version`     BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`  DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_order_log__order_id` (`order_id`),
    INDEX `ix_sys_order_log__entity_id` (`entity_id`),
    INDEX `ix_sys_order_log__tenant_id` (`tenant_id`)
) COMMENT '订单日志表';

--
-- 社交账号关联表
--

DROP TABLE IF EXISTS `sys_entity_open_id`;

CREATE TABLE `sys_entity_open_id`
(
    `id`          BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `entity_type` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '用户ID',
    `entity_id`   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '用户ID',
    `open_id`     VARCHAR(255)     NOT NULL DEFAULT '' COMMENT 'Open ID',
    `username`    VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '用户名',
    `nickname`    VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '昵称',
    `email`       VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '用户邮箱',
    `avatar`      VARCHAR(500)     NOT NULL DEFAULT '' COMMENT '头像地址',
    `status`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态',
    `source`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '来源',
    `version`     BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`  DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_entity_open__tenant_id` (`tenant_id`),
    INDEX `ix_sys_entity_open__entity` (`entity_type`, `entity_id`)
) COMMENT '社交账号关联表';

--
-- 账号表
--

DROP TABLE IF EXISTS `sys_account`;

CREATE TABLE `sys_account`
(
    `id`                   BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`            BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `entity_type`          BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '实体类型',
    `entity_id`            BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '实体ID',
    `uuid`                 VARCHAR(150)     NOT NULL DEFAULT '' COMMENT 'UUID',
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
    `birthday`             DATE COMMENT '生日',
    `signature`            VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '个性签名',
    `description`          VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '备注',
    `last_login_status`    VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '最后登录状态',
    `last_login_at`        VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '最后登录时间',
    `last_login_ip`        VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '最后登录IP',
    `password_expire_at`   VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '密码过期时间',
    `password_error_at`    VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '最后一次输入错误密码的时间',
    `password_error_count` INT UNSIGNED     NOT NULL DEFAULT 0 COMMENT '输入错误密码的次数',
    `telegram`             VARCHAR(255)     NOT NULL DEFAULT '' COMMENT 'Telegram',
    `registration`         DATETIME         NOT NULL DEFAULT NOW() COMMENT '注册时间',
    `invite_code`          VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '邀请码',
    `invite_by`            BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '邀请人',
    `status`               TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态',
    `source`               TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '来源',
    `version`              BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`               TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`           BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`           DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`           BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`           DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`           BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`           DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_account__active` (`active`),
    INDEX `ix_sys_account__username` (`username`),
    INDEX `ix_sys_account__email` (`email`),
    INDEX `ix_sys_account__mobile` (`mobile_country_code`, `mobile_number`),
    INDEX `ix_sys_account__subject` (`entity_type`, `entity_id`),
    INDEX `ix_sys_account__invite_code` (`invite_code`),
    INDEX `ix_sys_account__invite_by` (`invite_by`)
) COMMENT '账号表';

--
-- 角色表
--

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role`
(
    `id`              BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `code`            VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '编号',
    `title`           VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '标题',
    `label`           VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '文本',
    `data_score_type` varchar(100)     NOT NULL DEFAULT '' COMMENT '数据范围',
    `description`     VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '备注',
    `status`          TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态',
    `source`          TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '来源',
    `version`         BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`          TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`      BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`      DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`      BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`      DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`      BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`      DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_role__code` (`code`)
) COMMENT '角色表';

--
-- 组织表
--

DROP TABLE IF EXISTS `sys_organization`;

CREATE TABLE `sys_organization`
(
    `id`          BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `parent_id`   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '父级ID',
    `code`        VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '编号',
    `label`       VARCHAR(250)     NOT NULL DEFAULT '' COMMENT '文本',
    `title`       VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '标题',
    `root_ind`    TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否顶层',
    `default_ind` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否默认',
    `description` VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '备注',
    `status`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态',
    `source`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '来源',
    `version`     BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`  DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_organization__tenant_id` (`tenant_id`),
    INDEX `ix_sys_organization__parent_id` (`parent_id`),
    INDEX `ix_sys_organization__code` (`code`)
) COMMENT '组织表';

--
-- 岗位表
--

DROP TABLE IF EXISTS `sys_position`;

CREATE TABLE `sys_position`
(
    `id`          BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `parent_id`   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '父级ID',
    `code`        VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '编号',
    `label`       VARCHAR(250)     NOT NULL DEFAULT '' COMMENT '文本',
    `title`       VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '标题',
    `description` VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '备注',
    `root_ind`    TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否顶层',
    `default_ind` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否默认',
    `status`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态',
    `source`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '来源',
    `version`     BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`  DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_position__tenant_id` (`tenant_id`),
    INDEX `ix_sys_position__parent_id` (`parent_id`),
    INDEX `ix_sys_position__code` (`code`)
) COMMENT '岗位表';

--
-- 用户表
--

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user`
(
    `id`                   BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`            BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `entity_type`          BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '实体类型',
    `entity_id`            BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '实体ID',
    `uuid`                 VARCHAR(150)     NOT NULL DEFAULT '' COMMENT 'UUID',
    `username`             VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '用户名',
    `name`                 VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '姓名',
    `display_name`         VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '昵称',
    `email`                VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '电子邮箱',
    `mobile_country_code`  VARCHAR(10)      NOT NULL DEFAULT '' COMMENT '手机国家区号',
    `mobile_number`        VARCHAR(50)      NOT NULL DEFAULT '' COMMENT '手机号码',
    `password`             VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '密码',
    `id_card_type`         VARCHAR(50)      NOT NULL DEFAULT '' COMMENT '证件类型',
    `id_card_no`           VARCHAR(50)      NOT NULL DEFAULT '' COMMENT '证件号码',
    `sex`                  VARCHAR(20)      NOT NULL DEFAULT '' COMMENT '性别',
    `birthday`             DATE COMMENT '生日',
    `signature`            VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '个性签名',
    `description`          VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '备注',
    `last_login_status`    VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '最后登录状态',
    `last_login_at`        VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '最后登录时间',
    `last_login_ip`        VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '最后登录IP',
    `password_expire_at`   VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '密码过期时间',
    `password_error_at`    VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '最后一次输入错误密码的时间',
    `password_error_count` INT UNSIGNED     NOT NULL DEFAULT 0 COMMENT '输入错误密码的次数',
    `telegram`             VARCHAR(255)     NOT NULL DEFAULT '' COMMENT 'Telegram',
    `registration`         DATETIME         NOT NULL DEFAULT NOW() COMMENT '注册时间',
    `invite_code`          VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '邀请码',
    `invite_by`            BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '邀请人',
    `status`               TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态',
    `source`               TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '来源',
    `version`              BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`               TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`           BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`           DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`           BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`           DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`           BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`           DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
    CONSTRAINT UNIQUE (`username`),
    CONSTRAINT UNIQUE (`email`),
    INDEX `ix_sys_user__active` (`active`),
    INDEX `ix_sys_user__mobile` (`mobile_country_code`, `mobile_number`),
    INDEX `ix_sys_user__invite_code` (`invite_code`),
    INDEX `ix_sys_user__invite_by` (`invite_by`)
) COMMENT '用户表';

--
-- 角色-权限关联表
--

DROP TABLE IF EXISTS `sys_role_authority`;

CREATE TABLE `sys_role_authority`
(
    `id`           BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`    BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `role_id`      BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '角色ID',
    `authority_id` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '权限ID',
    `version`      BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`       TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`   DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_role_authority__tenant_id` (`tenant_id`),
    INDEX `ix_sys_role_authority__role_id` (`role_id`),
    INDEX `ix_sys_role_authority__authority_id` (`authority_id`)
) COMMENT '角色-权限关联表';

--
-- 用户-角色关联表
--

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role`
(
    `id`         BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `role_id`    BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '角色ID',
    `user_id`    BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '用户ID',
    `version`    BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`     TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at` DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_user_role__tenant_id` (`tenant_id`),
    INDEX `ix_sys_user_role__role_id` (`role_id`),
    INDEX `ix_sys_user_role__user_id` (`user_id`)
) COMMENT '用户-角色关联表';

--
-- 实体关联表
--

DROP TABLE IF EXISTS `sys_entity_relation`;

CREATE TABLE `sys_entity_relation`
(
    `id`             BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`      BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `ancestor_id`    BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '祖先ID',
    `entity_id`      BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '实体ID',
    `parent_ind`     TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否直接上级',
    `relation_type`  VARCHAR(50)      NOT NULL DEFAULT '' COMMENT '关联类型',
    `relation_path`  VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '关联路径',
    `relation_index` VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '关联层级',
    `version`        BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`         TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`     BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`     DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_entity_relation__tenant_id` (`tenant_id`),
    INDEX `ix_sys_entity_relation__ancestor_id` (`ancestor_id`),
    INDEX `ix_sys_entity_relation__entity_id` (`entity_id`),
    INDEX `ix_sys_entity_relation__relation_type` (`relation_type`)
) COMMENT '实体关联表';

--
-- 登录会话记录
--

DROP TABLE IF EXISTS `sys_login_session`;

CREATE TABLE `sys_login_session`
(
    `id`                   BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`            BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `entity_type`          BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '实体类型',
    `entity_id`            BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '实体ID',
    `session_id`           VARCHAR(50)      NOT NULL DEFAULT '' COMMENT 'Session ID',
    `username`             VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '用户名',
    `host`                 VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '用户登录主机',
    `ua`                   VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT 'User Agent',
    `client_id`            VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '客户端编号',
    `client_name`          VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '客户端名称',
    `client_version`       VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '客户端版本',
    `start_datetime`       DATETIME         NOT NULL DEFAULT NOW() COMMENT '会话开始时间',
    `start_year`           INT UNSIGNED     NOT NULL DEFAULT 0 COMMENT '会话开始年',
    `start_month`          INT UNSIGNED     NOT NULL DEFAULT 0 COMMENT '会话开始月',
    `start_day`            INT UNSIGNED     NOT NULL DEFAULT 0 COMMENT '会话开始天',
    `start_hour`           INT UNSIGNED     NOT NULL DEFAULT 0 COMMENT '会话开始时',
    `start_minute`         INT UNSIGNED     NOT NULL DEFAULT 0 COMMENT '会话开始分',
    `last_access_datetime` DATETIME         NOT NULL DEFAULT NOW() COMMENT '最近访问时间',
    `last_access_year`     INT UNSIGNED     NOT NULL DEFAULT 0 COMMENT '最近访问年',
    `last_access_month`    INT UNSIGNED     NOT NULL DEFAULT 0 COMMENT '最近访问月',
    `last_access_day`      INT UNSIGNED     NOT NULL DEFAULT 0 COMMENT '最近访问天',
    `last_access_hour`     INT UNSIGNED     NOT NULL DEFAULT 0 COMMENT '最近访问时',
    `last_access_minute`   INT UNSIGNED     NOT NULL DEFAULT 0 COMMENT '最近访问钟',
    `end_datetime`         DATETIME         NULL COMMENT '会话结束时间',
    `success`              TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否成功登录',
    `version`              BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`               TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`           BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`           DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`           BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`           DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`           BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`           DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_login_session__tenant_id` (`tenant_id`),
    INDEX `ix_sys_login_session__entity_id` (`entity_id`),
    INDEX `ix_sys_login_session__session_id` (`session_id`),
    INDEX `ix_sys_login_session__username` (`username`)
) COMMENT '登录会话记录';

--
-- 系统设置表
-- tenant_id = 0 : 系统全局设置项
-- tenant_id > 0 : 租户专用设置项
--

DROP TABLE IF EXISTS `sys_config`;

CREATE TABLE `sys_config`
(
    `id`             BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `biz_scope_type` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '业务范围类型',
    `tenant_id`      BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `title`          VARCHAR(100)     NOT NULL DEFAULT '' COMMENT '标题',
    `label`          VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '文本',
    `config_type`    INT              NOT NULL DEFAULT 1 COMMENT '类型',
    `config_group`   VARCHAR(100)     NOT NULL DEFAULT '' COMMENT '分组',
    `config_key`     VARCHAR(100)     NOT NULL DEFAULT '' COMMENT '参数名',
    `config_value`   VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '参数值',
    `default_value`  VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '默认值',
    `extra`          VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '附加信息',
    `help`           VARCHAR(250)     NOT NULL DEFAULT '' COMMENT '帮助信息',
    `description`    VARCHAR(250)     NOT NULL DEFAULT '' COMMENT '备注',
    `source`         TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '数据来源',
    `version`        BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`         TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`     BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`     DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`     BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`     DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`     BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`     DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_config__tenant_id` (`tenant_id`),
    INDEX `ix_sys_config__key` (`config_key`),
    INDEX `ix_sys_config__group` (`config_group`)
) COMMENT '系统设置表';

--
-- 系统关键字表
-- tenant_id = 0 : 系统全局关键字
-- tenant_id > 0 : 租户专用关键字
--

DROP TABLE IF EXISTS `sys_keyword`;

CREATE TABLE `sys_keyword`
(
    `id`         BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `content`    VARCHAR(100)     NOT NULL DEFAULT '' COMMENT '关键字',
    `version`    BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`     TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at` DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at` DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at` DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`)
) COMMENT '系统关键字表';

--
-- 语言表
--

DROP TABLE IF EXISTS `sys_lang`;

CREATE TABLE `sys_lang`
(
    `id`          BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `code`        VARCHAR(50)      NOT NULL DEFAULT '' COMMENT '编号',
    `lang`        VARCHAR(50)      NOT NULL DEFAULT '' COMMENT '语言编码',
    `country`     VARCHAR(50)      NOT NULL DEFAULT '' COMMENT '地区编码',
    `label`       VARCHAR(250)     NOT NULL DEFAULT '' COMMENT '文本',
    `description` VARCHAR(250)     NOT NULL DEFAULT '' COMMENT '备注',
    `default_ind` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '默认语言',
    `version`     BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_lang__code` (`code`)
) COMMENT '语言表';

--
-- 租户-语言关联表
--

DROP TABLE IF EXISTS `sys_tenant_lang`;

CREATE TABLE `sys_tenant_lang`
(
    `id`         BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `lang_id`    BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '语言ID',
    `version`    BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`     TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at` DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_role_authority__tenant_id` (`tenant_id`),
    INDEX `ix_sys_role_authority__lang_id` (`lang_id`)
) COMMENT '角色-权限关联表';

--
-- 多语言文本表
--

DROP TABLE IF EXISTS `sys_label`;

CREATE TABLE `sys_label`
(
    `id`               BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `group`            VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '分组',
    `code`             VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '多语言标识',
    `zh_cn_label`      VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '简体中文',
    `zh_cn_static_ind` TINYINT          NOT NULL DEFAULT 0 COMMENT '是否静态文本',
    `zh_tw_label`      VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '繁体中文',
    `zh_tw_static_ind` TINYINT          NOT NULL DEFAULT 0 COMMENT '是否静态文本',
    `en_label`         VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '英语',
    `en_static_ind`    TINYINT          NOT NULL DEFAULT 0 COMMENT '是否静态文本',
    `ja_label`         VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '日语',
    `ja_static_ind`    TINYINT          NOT NULL DEFAULT 0 COMMENT '是否静态文本',
    `kr_label`         VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '韩语',
    `kr_static_ind`    TINYINT          NOT NULL DEFAULT 0 COMMENT '是否静态文本',
    `fr_label`         VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '法语',
    `fr_static_ind`    TINYINT          NOT NULL DEFAULT 0 COMMENT '是否静态文本',
    `version`          BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`       DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`       DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`       DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_label__group` (`group`),
    INDEX `ix_sys_label__code` (`code`)
) COMMENT '多语言文本表';

--
-- 实体多语言文本表
--

DROP TABLE IF EXISTS `sys_entity_label`;

CREATE TABLE `sys_entity_label`
(
    `id`              BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `lang_id`         BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '语言ID',
    `lang_code`       VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '语言编号',
    `entity_class`    VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '实体类名',
    `entity_property` VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '实体属性',
    `entity_id`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '实体ID',
    `zh_cn_label`     VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '简体中文',
    `zh_tw_label`     VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '繁体中文',
    `en_label`        VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '英语',
    `ja_label`        VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '日语',
    `kr_label`        VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '韩语',
    `fr_label`        VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '法语',
    `version`         BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`          TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`      BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`      DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`      BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`      DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`      BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`      DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_entity_label` (`entity_id`, `entity_class`, `entity_property`)
) COMMENT '实体多语言文本表';

--
-- 目录类型表
--

DROP TABLE IF EXISTS `sys_catalog_type`;

CREATE TABLE `sys_catalog_type`
(
    `id`          BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `code`        VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '编号',
    `title`       VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '名称',
    `extra`       VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '附加信息',
    `description` VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '备注',
    `status`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '发布状态',
    `version`     BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`  DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_catalog_type__code` (`code`)
) COMMENT '目录类型表';

--
-- 目录表
--

DROP TABLE IF EXISTS `sys_catalog`;

CREATE TABLE `sys_catalog`
(
    `id`          BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `type_id`     BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '分类类型ID',
    `code`        VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '编号',
    `title`       VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '标题',
    `root_ind`    TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否顶层',
    `description` VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '简介',
    `source`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '数据来源',
    `version`     BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`  DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_catalog__type_id` (`type_id`),
    INDEX `ix_sys_catalog__code` (`code`)
) COMMENT '目录表';

--
-- 目录分类关联表
--

DROP TABLE IF EXISTS `sys_catalog_relation`;

CREATE TABLE `sys_catalog_relation`
(
    `id`             BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`      BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `ancestor_id`    BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '祖先ID',
    `entity_id`      BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '实体ID',
    `parent_ind`     TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否直接上级',
    `relation_type`  VARCHAR(50)      NOT NULL DEFAULT '' COMMENT '关联类型',
    `relation_path`  VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '关联路径',
    `relation_index` VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '关联层级',
    `version`        BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`         TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`     BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`     DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_catalog_relation__relation_type` (`relation_type`),
    INDEX `ix_sys_catalog_relation__ancestor_id` (`ancestor_id`),
    INDEX `ix_sys_catalog_relation__entity_id` (`entity_id`)
) COMMENT '目录分类关联表';

--
-- 字典类型表
--

DROP TABLE IF EXISTS `sys_dict_type`;

CREATE TABLE `sys_dict_type`
(
    `id`          BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `code`        VARCHAR(100)     NOT NULL DEFAULT '' COMMENT '编号',
    `title`       VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '标题',
    `label`       VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '文本',
    `description` VARCHAR(250)     NOT NULL DEFAULT '' COMMENT '备注',
    `source`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '数据来源',
    `version`     BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`  DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_dict_type__code` (`code`)
) COMMENT '字典类型表';

--
-- 字典明细表
--

DROP TABLE IF EXISTS `sys_dict_item`;

CREATE TABLE `sys_dict_item`
(
    `id`         BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `type_id`    BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '字典类型ID',
    `code`       VARCHAR(100)     NOT NULL DEFAULT '' COMMENT '编号',
    `title`      VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '文本',
    `idx`        INT UNSIGNED     NOT NULL DEFAULT 999 COMMENT '序号',
    `source`     TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '数据来源',
    `version`    BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`     TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at` DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at` DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at` DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_dict_item__type_id` (`type_id`)
) COMMENT '字典明细表';

--
-- 字典关联表
--

DROP TABLE IF EXISTS `sys_dict_relation`;

CREATE TABLE `sys_dict_relation`
(
    `id`          BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `target_type` VARCHAR(50)      NOT NULL DEFAULT '' COMMENT '目标类型',
    `target_id`   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '目标实体ID',
    `type_id`     BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '字典类型ID',
    `item_id`     BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '字典项ID',
    `version`     BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_dict_relation__type_id` (`type_id`),
    INDEX `ix_sys_dict_relation__item_id` (`item_id`),
    INDEX `ix_sys_dict_relation__target_type` (`target_type`),
    INDEX `ix_sys_dict_relation__target_id` (`target_id`)
) COMMENT '字典关联表';

--
-- 标签类型表
--

DROP TABLE IF EXISTS `sys_tag_type`;

CREATE TABLE `sys_tag_type`
(
    `id`          BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `code`        VARCHAR(100)     NOT NULL DEFAULT '' COMMENT '编号',
    `title`       VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '标题',
    `label`       VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '文本',
    `extra`       VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '附加信息',
    `description` VARCHAR(250)     NOT NULL DEFAULT '' COMMENT '备注',
    `source`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '数据来源',
    `version`     BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`  DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_tag_type__code` (`code`)
) COMMENT '标签类型表';

--
-- 标签表
--

DROP TABLE IF EXISTS `sys_tag`;

CREATE TABLE `sys_tag`
(
    `id`          BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `type_id`     BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '标签类型ID',
    `title`       VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '标题',
    `description` VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '备注',
    `idx`         INT UNSIGNED     NOT NULL DEFAULT 999 COMMENT '序号',
    `source`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '数据来源',
    `active`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `version`     BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `created_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`  DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_tag__type_id` (`type_id`)
) COMMENT '标签表';

--
-- 标签关联表
--

DROP TABLE IF EXISTS `sys_tag_relation`;

CREATE TABLE `sys_tag_relation`
(
    `id`          BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `tag_type_id` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '标签类型ID',
    `tag_id`      BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '标签ID',
    `biz_type`    VARCHAR(50)      NOT NULL DEFAULT '' COMMENT '业务类型',
    `entity_type` VARCHAR(50)      NOT NULL DEFAULT '' COMMENT '实体类型',
    `entity_id`   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '实体ID',
    `version`     BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_tag_relation__tenant_id` (`tenant_id`),
    INDEX `ix_sys_tag_relation__tag_type_id` (`tag_type_id`),
    INDEX `ix_sys_tag_relation__tag_id` (`tag_id`),
    INDEX `ix_sys_tag_relation__entity_id` (`entity_type`, `entity_id`)
) COMMENT '标签关联表';

--
-- 附件类型表
--

DROP TABLE IF EXISTS `sys_attachment_type`;

CREATE TABLE `sys_attachment_type`
(
    `id`           BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `code`         VARCHAR(100)     NOT NULL DEFAULT '' COMMENT '编号',
    `title`        VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '标题',
    `label`        VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '文本',
    `ext`          VARCHAR(255)     NULL COMMENT '文件后缀',
    `file_types`   VARCHAR(255)     NULL COMMENT '类型关联',
    `multiple_ind` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否多选',
    `extra`        VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '附加信息',
    `description`  VARCHAR(250)     NOT NULL DEFAULT '' COMMENT '备注',
    `source`       TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '数据来源',
    `version`      BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`       TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`   DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`   DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`   DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_attachment_type__code` (`code`)
) COMMENT '附件类型表';

--
-- 附件文件表
--

DROP TABLE IF EXISTS `sys_attachment_file`;

CREATE TABLE `sys_attachment_file`
(
    `id`                 BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`          BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `attachment_type_id` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '附件类型ID',
    `content_type`       VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '文件大小',
    `storage_type`       VARCHAR(100)     NOT NULL DEFAULT '' COMMENT '文件大小',
    `access_type`        VARCHAR(100)     NOT NULL DEFAULT '' COMMENT '文件访问类型',
    `original_filename`  VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '原始文件名',
    `filename`           VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '文件名',
    `file_key`           VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '文件标识',
    `size`               BIGINT           NOT NULL DEFAULT 0 COMMENT '文件大小',
    `url`                VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT '文件链接',
    `extra`              TEXT             NULL COMMENT '附加信息',
    `version`            BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`             TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`         BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`         DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`         BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`         DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`         BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`         DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_attachment_file__tenant_id` (`tenant_id`),
    INDEX `ix_sys_attachment_file__attachment_type_id` (`attachment_type_id`)
) COMMENT '附件文件表';

--
-- 附件关联表
--

DROP TABLE IF EXISTS `sys_attachment_relation`;

CREATE TABLE `sys_attachment_relation`
(
    `id`            BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`     BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `attachment_id` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '附件ID',
    `entity_type`   VARCHAR(50)      NOT NULL DEFAULT '' COMMENT '目标类型',
    `entity_id`     BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '目标实体',
    `version`       BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`        TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`    BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`    DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_attachment_relation__tenant_id` (`tenant_id`),
    INDEX `ix_sys_attachment_relation__attachment_id` (`attachment_id`),
    INDEX `ix_sys_attachment_relation__entity_id` (`entity_type`, `entity_id`)
) COMMENT '附件关联表';

--
-- 链接日志
--

DROP TABLE IF EXISTS `sys_url_log`;

CREATE TABLE `sys_url_log`
(
    `id`         BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `path`       VARCHAR(250) COMMENT '路径',
    `start_time` DATETIME         NULL COMMENT '开始时间',
    `end_time`   DATETIME         NULL COMMENT '结束时间',
    `exec_time`  LONG COMMENT '执行时长',
    `version`    BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`     TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at` DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_url_log__path` (`path`)
) COMMENT '链接日志';

--
-- 登录日志表
--

DROP TABLE IF EXISTS `sys_login_log`;

CREATE TABLE `sys_login_log`
(
    `id`             BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`      BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `entity_type`    VARCHAR(50)      NOT NULL DEFAULT '' COMMENT '目标类型',
    `entity_id`      BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '目标实体',
    `host`           VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '用户登录主机',
    `user_agent`     VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT 'User Agent',
    `client_id`      VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '客户端编号',
    `client_name`    VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '客户端名称',
    `client_version` VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '客户端版本',
    `details`        VARCHAR(250) COMMENT '详情',
    `exception`      TEXT COMMENT '异常',
    `success`        TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '登录状态',
    `version`        BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`         TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`     BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`     DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_login_log__tenant_id` (`tenant_id`),
    INDEX `ix_sys_login_log__entity_id` (`entity_type`, `entity_id`)
) COMMENT '登录日志表';

--
-- 操作日志表
--

DROP TABLE IF EXISTS `sys_operation_log`;

CREATE TABLE `sys_operation_log`
(
    `id`              BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `entity_type`     VARCHAR(50)      NOT NULL DEFAULT '' COMMENT '实体类型',
    `entity_id`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '实体ID',
    `class_name`      VARCHAR(250)     NOT NULL DEFAULT '' COMMENT '类名',
    `method_name`     VARCHAR(250)     NOT NULL DEFAULT '' COMMENT '方法名',
    `request_id`      VARCHAR(250)     NOT NULL DEFAULT '' COMMENT '请求ID',
    `request_ip`      VARCHAR(250)     NOT NULL DEFAULT '' COMMENT '请求IP',
    `request_method`  VARCHAR(250)     NOT NULL DEFAULT '' COMMENT '请求类型',
    `request_ua`      TEXT COMMENT '请求UA',
    `request_uri`     TEXT COMMENT '请求地址',
    `request_params`  TEXT COMMENT '请求参数',
    `request_headers` TEXT COMMENT '请求头',
    `start_time`      DATETIME COMMENT '开始时间',
    `end_time`        DATETIME COMMENT '结束时间',
    `exec_time`       INT UNSIGNED     NOT NULL DEFAULT 0 COMMENT '执行时长',
    `details`         VARCHAR(250) COMMENT '详情',
    `exception`       TEXT COMMENT '异常',
    `version`         BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`          TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`      BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`      DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_operation_log__tenant_id` (`tenant_id`),
    INDEX `ix_sys_operation_log__entity_id` (`entity_type`, `entity_id`)
) COMMENT '操作日志表';

--
-- 系统日志表
--

DROP TABLE IF EXISTS `sys_application_log`;

CREATE TABLE `sys_application_log`
(
    `id`          BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `class_name`  VARCHAR(250) COMMENT '类名',
    `method_name` VARCHAR(250) COMMENT '方法名',
    `request_id`  VARCHAR(250) COMMENT '请求ID',
    `details`     VARCHAR(250) COMMENT '详情',
    `exception`   TEXT COMMENT '异常',
    `version`     BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_application_log__tenant_id` (`tenant_id`)
) COMMENT '系统日志表';

--
-- 验证码发送日志表
--

DROP TABLE IF EXISTS `sys_captcha_log`;

CREATE TABLE `sys_captcha_log`
(
    `id`                  BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`           BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `captcha_type`        VARCHAR(50)      NOT NULL DEFAULT '' COMMENT '验证码类型',
    `captcha_key`         VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '验证码标识',
    `captcha_value`       VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '验证码',
    `email`               VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '电子邮箱',
    `mobile_country_code` VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '手机区位码',
    `mobile_number`       VARCHAR(150)     NOT NULL DEFAULT 0 COMMENT '手机号码',
    `version`             BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`              TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`          BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`          DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_captcha_log__tenant_id` (`tenant_id`)
) COMMENT '验证码发送日志表';

--
-- 用户搜索日志表
--

DROP TABLE IF EXISTS `sys_search_log`;

CREATE TABLE `sys_search_log`
(
    `id`         BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `user_id`    BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '用户ID',
    `search_key` VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '搜索关键字',
    `version`    BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`     TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at` DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_search_log__tenant_id` (`tenant_id`),
    INDEX `ix_sys_search_log__user_id` (`user_id`)
) COMMENT '用户搜索日志表';

--
-- 消息类型表
--

DROP TABLE IF EXISTS `sys_message_type`;

CREATE TABLE `sys_message_type`
(
    `id`          BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `code`        VARCHAR(150)     NOT NULL COMMENT '编号',
    `label`       VARCHAR(150)     NOT NULL COMMENT '多语言文本',
    `title`       VARCHAR(255)     NOT NULL COMMENT '标题',
    `description` TEXT COMMENT '描述备注',
    `status`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '发布状态',
    `version`     BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`  DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`)
);
ALTER TABLE `sys_message_type`
    COMMENT '消息类型表';

--
-- 消息模版类型表
--

DROP TABLE IF EXISTS `sys_message_template_type`;

CREATE TABLE `sys_message_template_type`
(
    `id`          BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `code`        VARCHAR(150)     NOT NULL COMMENT '编号',
    `label`       VARCHAR(150)     NOT NULL COMMENT '多语言文本',
    `title`       VARCHAR(255)     NOT NULL COMMENT '标题',
    `description` TEXT             NULL COMMENT '描述备注',
    `status`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '发布状态',
    `version`     BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`  DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`)
);
ALTER TABLE `sys_message_type`
    COMMENT '消息模版类型表';

--
-- 消息模板表
--

DROP TABLE IF EXISTS `sys_message_template`;

CREATE TABLE `sys_message_template`
(
    `id`               BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`        BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `type_id`          BIGINT UNSIGNED  NOT NULL COMMENT '消息类型ID',
    `template_type_id` BIGINT UNSIGNED  NOT NULL COMMENT '模板类型ID',
    `content`          TEXT             NOT NULL COMMENT '模板',
    `status`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '发布状态',
    `version`          BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`       DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`       DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`       DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
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
    `id`                   BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`            BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
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
    `version`              BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`               TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`           BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`           DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`           BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`           DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`           BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`           DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
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
    `id`                  BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`           BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `message_id`          BIGINT UNSIGNED  NOT NULL COMMENT '消息ID',
    `user_id`             BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '用户ID',
    `type_id`             BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '类型ID',
    `display_name`        VARCHAR(255) COMMENT '姓名',
    `username`            VARCHAR(255) COMMENT '账号',
    `email`               VARCHAR(255) COMMENT '邮箱',
    `mobile_country_code` VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '手机区位码',
    `mobile_number`       VARCHAR(150)     NOT NULL DEFAULT 0 COMMENT '手机号码',
    `version`             BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`              TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`          BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`          DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`          BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`          DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`          BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`          DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
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
    `id`               BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`        BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `message_id`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '消息ID',
    `template_type_id` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '模板类型ID',
    `content`          TEXT COMMENT '消息内容',
    `resp`             TEXT COMMENT '响应内容',
    `exception`        TEXT COMMENT '异常内容',
    `sent_datetime`    DATETIME COMMENT '发送时间',
    `status`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '发布状态',
    `version`          BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`       DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`       DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`       DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
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
    `id`          BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `type_id`     BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '消息类型ID',
    `message_id`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '消息ID',
    `user_id`     BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '用户ID',
    `target_id`   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '目标ID',
    `target_type` VARCHAR(100) COMMENT '目标类型',
    `version`     BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    CONSTRAINT PRIMARY KEY (`id`),
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

DROP TABLE IF EXISTS `sys_notice`;

CREATE TABLE `sys_notice`
(
    `id`            BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`     BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `subject`       VARCHAR(100)     NOT NULL DEFAULT '' COMMENT '通知标题',
    `content`       VARCHAR(100)     NOT NULL DEFAULT '' COMMENT '通知内容',
    `recipient_id`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '收件人ID',
    `sender_id`     BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '发件人ID',
    `read_ind`      TINYINT          NOT NULL DEFAULT 0 COMMENT '是否已读',
    `read_datetime` DATETIME COMMENT '阅读时间',
    `version`       BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`        TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`    BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`    DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`    BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`    DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`    BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`    DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_notice__sender_id` (`sender_id`),
    INDEX `ix_sys_notice__recipient_id` (`recipient_id`)
);
ALTER TABLE `sys_notice`
    COMMENT '系统通知表';

--
-- 宣传栏
--

DROP TABLE IF EXISTS `sys_banner`;

CREATE TABLE `sys_banner`
(
    `id`          BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `title`       VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '标题',
    `details`     TEXT             NULL COMMENT '详情',
    `description` VARCHAR(250)     NOT NULL DEFAULT '' COMMENT '备注',
    `idx`         INT UNSIGNED     NOT NULL DEFAULT 999 COMMENT '序号',
    `version`     BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`  DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`)
) COMMENT '宣传栏';

--
-- 资讯表
--

DROP TABLE IF EXISTS `sys_announcement`;

CREATE TABLE `sys_announcement`
(
    `id`                BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`         BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `title`             VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '标题',
    `content`           TEXT             NULL COMMENT '内容',
    `status`            TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '发布状态',
    `start_datetime`    DATETIME         NOT NULL DEFAULT NOW() COMMENT '发布期限',
    `end_datetime`      DATETIME         NOT NULL DEFAULT NOW() COMMENT '发布期限',
    `allow_comment_ind` TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否允许评论',
    `description`       VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '备注',
    `version`           BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`            TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`        BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`        DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`        BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`        DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`        BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`        DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`)
) COMMENT '资讯表';

--
-- 友情链接表
--

DROP TABLE IF EXISTS `sys_link`;

CREATE TABLE `sys_link`
(
    `id`          BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `title`       VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '标题',
    `sub_title`   VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '副标题',
    `link`        VARCHAR(1000)    NULL COMMENT '链接',
    `description` VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '备注',
    `summary`     VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '简介',
    `idx`         INT UNSIGNED     NOT NULL DEFAULT 999 COMMENT '序号',
    `version`     BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`  DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`)
) COMMENT '友情链接表';

--
-- 定时任务表
--

DROP TABLE IF EXISTS `sys_job`;

CREATE TABLE `sys_job`
(
    `id`          BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `code`        VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '编号',
    `classname`   VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '任务类名',
    `description` VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '描述说明',
    `type`        VARCHAR(255) COMMENT '类型',
    `unit`        VARCHAR(255) COMMENT '单位',
    `period`      INT COMMENT '周期',
    `hour`        INT COMMENT '小时',
    `minute`      INT COMMENT '分钟',
    `cron`        VARCHAR(255) COMMENT '表达式',
    `status`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态',
    `version`     BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`  DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`)
) COMMENT '定时任务表';

--
-- 定时任务参数表
--

DROP TABLE IF EXISTS `sys_job_param`;

CREATE TABLE `sys_job_param`
(
    `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `job_id`      BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '任务ID',
    `param_name`  VARCHAR(150)    NOT NULL DEFAULT '' COMMENT '参数名',
    `param_value` VARCHAR(255)    NOT NULL DEFAULT '' COMMENT '参数值',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_job_param__job_id` (`job_id`)
) COMMENT '定时任务参数表';

-- =====================================================================================================================
-- View
-- =====================================================================================================================

CREATE OR REPLACE VIEW sys_v_operator (`type`, `id`, `username`) AS
SELECT sm.id as `id`, 'account' as `type`, sm.username as 'username'
FROM sys_account sm
UNION
SELECT su.id as `id`, 'user' as `type`, su.username as 'username'
FROM sys_user su;

-- =====================================================================================================================
-- OAuth
-- =====================================================================================================================

--
-- 客户端
--

DROP TABLE IF EXISTS `sys_client`;

CREATE TABLE `sys_client`
(
    `id`                            BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `client_id`                     VARCHAR(150)     NOT NULL DEFAULT '' COMMENT 'Client ID',
    `client_id_issued_at`           DATETIME,
    `client_secret`                 VARCHAR(255)     NOT NULL DEFAULT '' COMMENT 'Client Secret',
    `client_secret_expires_at`      DATETIME,
    `client_name`                   VARCHAR(255)     NOT NULL DEFAULT '' COMMENT 'Client Name',
    `client_authentication_methods` VARCHAR(1000)    NOT NULL DEFAULT '' COMMENT 'Client Authentication Methods',
    `authorization_grant_types`     VARCHAR(255)     NOT NULL DEFAULT '' COMMENT 'Authorization Grant Types',
    `redirect_uris`                 VARCHAR(255)     NOT NULL DEFAULT '' COMMENT 'Redirect Uris',
    `post_logout_redirect_uris`     VARCHAR(255)     NOT NULL DEFAULT '' COMMENT 'Redirect Uris',
    `scopes`                        VARCHAR(2000)    NOT NULL DEFAULT '' COMMENT 'Score',
    `client_settings`               VARCHAR(2000)    NOT NULL DEFAULT '',
    `token_settings`                VARCHAR(2000)    NOT NULL DEFAULT '',
    `description`                   VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '备注',
    `status`                        TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '发布状态',
    `version`                       BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`                        TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`                    BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`                    DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`                    BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`                    DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`                    BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`                    DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_client__client_id` (`client_id`)
) COMMENT '客户端';

--
-- 认证记录
--

DROP TABLE IF EXISTS `sys_authorization`;

CREATE TABLE `sys_authorization`
(
    `id`                            BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`                     BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `uuid`                          VARCHAR(200)     NOT NULL DEFAULT '',
    `client_id`                     VARCHAR(100)     NOT NULL DEFAULT '',
    `principal_name`                VARCHAR(200),
    `authorization_grant_type`      VARCHAR(100),
    `attributes`                    TEXT,
    `state`                         VARCHAR(2000),
    `authorization_code_value`      VARCHAR(2000),
    `authorization_code_issued_at`  DATETIME,
    `authorization_code_expires_at` DATETIME,
    `authorization_code_metadata`   TEXT,
    `access_token_value`            TEXT,
    `access_token_issued_at`        DATETIME,
    `access_token_expires_at`       DATETIME,
    `access_token_metadata`         TEXT,
    `access_token_type`             VARCHAR(100),
    `access_token_scopes`           VARCHAR(2000),
    `oidc_id_token_value`           TEXT,
    `oidc_id_token_issued_at`       DATETIME,
    `oidc_id_token_expires_at`      DATETIME,
    `oidc_id_token_metadata`        TEXT,
    `oidc_id_token_claims`          TEXT,
    `refresh_token_value`           TEXT,
    `refresh_token_issued_at`       DATETIME,
    `refresh_token_expires_at`      DATETIME,
    `refresh_token_metadata`        TEXT,
    `active`                        TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `version`                       BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `created_by`                    BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`                    DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`                    BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`                    DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`                    BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`                    DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_authorization__client_id` (`client_id`)
) COMMENT '认证记录';

--
-- 认证同意授权记录
--

DROP TABLE IF EXISTS `sys_authorization_consent`;

CREATE TABLE `sys_authorization_consent`
(
    `id`             BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`      BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `uuid`           VARCHAR(100)     NOT NULL,
    `client_id`      VARCHAR(100)     NOT NULL,
    `principal_name` VARCHAR(200)     NOT NULL,
    `authorities`    VARCHAR(2000)    NOT NULL,
    `version`        BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`         TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`     BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`     DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`     BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`     DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`     BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`     DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_authorization_consent` (`client_id`, `principal_name`)
) COMMENT '授权记录';

-- =====================================================================================================================
-- AI Chat Memory
-- =====================================================================================================================

--
-- 对话类型
--

DROP TABLE IF EXISTS `sys_ai_chat_type`;

CREATE TABLE `sys_ai_chat_type`
(
    `id`          BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `code`        VARCHAR(100)     NOT NULL DEFAULT '' COMMENT '编号',
    `title`       VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '标题',
    `label`       VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '文本',
    `description` VARCHAR(250)     NOT NULL DEFAULT '' COMMENT '备注',
    `source`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '数据来源',
    `version`     BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`      TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`  DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`  DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_ai_chat_type__code` (`code`)
);

--
-- 对话记录
--

DROP TABLE IF EXISTS `sys_ai_chat_memory`;

CREATE TABLE `sys_ai_chat_memory`
(
    `id`              BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '租户ID',
    `entity_type`     BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '实体类型',
    `entity_id`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '实体ID',
    `conversation_id` VARCHAR(100)     NOT NULL DEFAULT '' COMMENT '会话标识，一般格式为UUID',
    `content`         LONGTEXT         NULL COMMENT '对话内容',
    `type`            VARCHAR(100)     NOT NULL default '' COMMENT '对话类型',
    `version`         BIGINT           NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`          TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`      BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`      DATETIME         NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`      BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`      DATETIME         NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`      BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`      DATETIME         NULL COMMENT '删除时间',
    CONSTRAINT PRIMARY KEY (`id`),
    INDEX `ix_sys_ai_chat_memory__tenant_id` (`tenant_id`),
    INDEX `ix_sys_ai_chat_memory__conversation_id` (`conversation_id`),
    INDEX `ix_sys_ai_chat_memory__entity` (`entity_type`, `entity_id`)
);
