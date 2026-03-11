-- =====================================================================================================================
-- 核心基础表
-- =====================================================================================================================

USE ee_platform;

--
-- 业务类型表
--

DROP TABLE IF EXISTS `sys_biz_type`;

CREATE TABLE `sys_biz_type`
(
    `id`             BIGINT        NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`      BIGINT        NOT NULL DEFAULT 0 COMMENT '租户ID',
    `biz_group_type` VARCHAR(150)  NOT NULL DEFAULT '' COMMENT '业务分组',
    `biz_scope_type` VARCHAR(150)  NOT NULL DEFAULT '' COMMENT '业务范围',
    `biz_type`       VARCHAR(150)  NOT NULL DEFAULT '' COMMENT '业务编号',
    `extra`          VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '附加信息',
    `description`    VARCHAR(255)  NOT NULL DEFAULT '' COMMENT '备注说明',
    `idx`            SMALLINT      NOT NULL DEFAULT 1 COMMENT '序号',
    `status`         SMALLINT      NOT NULL DEFAULT 1 COMMENT '状态',
    `source`         SMALLINT      NOT NULL DEFAULT 1 COMMENT '来源',
    `version`        BIGINT        NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`         SMALLINT      NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`     BIGINT        NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`     DATETIME      NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`     BIGINT        NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`     DATETIME      NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`     BIGINT        NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`     DATETIME      NULL COMMENT '删除时间'
) COMMENT '业务类型表';

CREATE INDEX `ix_sys_biz_type__tenant_id` ON `sys_biz_type` (`tenant_id`);
CREATE INDEX `ix_sys_biz_type__key` ON `sys_biz_type` (`biz_group_type`, `biz_scope_type`);

--
-- 身份主体表
-- 用户体系和账号体系的上层抽象
-- 用户体系用于后台系统
-- 账号体系用于前台系统
--

DROP TABLE IF EXISTS `sys_identity`;

CREATE TABLE `sys_identity`
(
    `id`         BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `uuid`       VARCHAR(150) NOT NULL DEFAULT '' COMMENT '用户标识',
    `status`     SMALLINT     NOT NULL DEFAULT 1 COMMENT '状态',
    `source`     SMALLINT     NOT NULL DEFAULT 1 COMMENT '来源',
    `version`    BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`     SMALLINT     NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by` BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at` DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by` BIGINT       NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at` DATETIME     NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by` BIGINT       NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at` DATETIME     NULL COMMENT '删除时间'
) COMMENT '身份主体表';

CREATE INDEX `ix_sys_identity__active` ON `sys_identity` (`active`);
CREATE INDEX `ix_sys_identity__uuid` ON `sys_identity` (`uuid`);

--
-- 租户表
--

DROP TABLE IF EXISTS `sys_tenant`;

CREATE TABLE `sys_tenant`
(
    `id`                     BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `code`                   VARCHAR(140) NOT NULL DEFAULT '' COMMENT '编号',
    `title`                  VARCHAR(255) NOT NULL DEFAULT '' COMMENT '名称',
    `details`                LONGTEXT     NULL COMMENT '简介',
    `address`                VARCHAR(255) NOT NULL DEFAULT '' COMMENT '地址',
    `domain`                 VARCHAR(255) NOT NULL DEFAULT '' COMMENT '域名',
    `contact_user`           VARCHAR(50)  NOT NULL DEFAULT '' comment '联系人',
    `contact_phone`          VARCHAR(50)  NOT NULL DEFAULT '' comment '联系电话',
    `company_name`           VARCHAR(50)  NOT NULL DEFAULT '' comment '企业名称',
    `company_license_number` VARCHAR(50)  NOT NULL DEFAULT '' comment '统一社会信用代码',
    `registration_date`      DATETIME     NOT NULL DEFAULT NOW() COMMENT '注册时间',
    `expiration_date`        DATETIME     NOT NULL DEFAULT NOW() COMMENT '到期时间',
    `account_count`          INT          NOT NULL DEFAULT 0 COMMENT '租户用户数',
    `root_ind`               TINYINT      NOT NULL DEFAULT 0 COMMENT '是否顶层租户',
    `description`            VARCHAR(255) NOT NULL DEFAULT '' COMMENT '备注说明',
    `status`                 SMALLINT     NOT NULL DEFAULT 1 COMMENT '状态',
    `source`                 SMALLINT     NOT NULL DEFAULT 2 COMMENT '来源',
    `version`                BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`                 SMALLINT     NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`             BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`             DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`             BIGINT       NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`             DATETIME     NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`             BIGINT       NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`             DATETIME     NULL COMMENT '删除时间'
) COMMENT '租户表';

CREATE INDEX `ix_sys_tenant__active` ON `sys_tenant` (`active`);
CREATE INDEX `ix_sys_tenant__code` ON `sys_tenant` (`code`);

--
-- 权限表
-- biz_type - TENANT - 租户套餐功能权限
-- biz_type - MEMBER - 会员套餐功能权限
--

DROP TABLE IF EXISTS `sys_authority`;

CREATE TABLE `sys_authority`
(
    `id`                   BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `parent_id`            BIGINT       NOT NULL DEFAULT 0 COMMENT 'Parent ID',
    `code`                 VARCHAR(100) NOT NULL DEFAULT '' COMMENT '编码',
    `title`                VARCHAR(150) NOT NULL DEFAULT '' COMMENT '标题',
    `label`                VARCHAR(150) NOT NULL DEFAULT '' COMMENT '多语言文本',
    `idx`                  INT          NOT NULL DEFAULT 999 COMMENT '序号',
    `description`          VARCHAR(255) NOT NULL DEFAULT '' COMMENT '备注',
    `authority_type`       VARCHAR(100) NOT NULL DEFAULT 0 COMMENT '权限类型',
    `authority_scope_type` VARCHAR(100) NOT NULL DEFAULT 0 COMMENT '权限范围类型',
    `authority_biz_type`   VARCHAR(100) NOT NULL DEFAULT 0 COMMENT '权限业务类型',
    `status`               SMALLINT     NOT NULL DEFAULT 1 COMMENT '状态',
    `source`               SMALLINT     NOT NULL DEFAULT 1 COMMENT '来源',
    `version`              BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`               SMALLINT     NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`           BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`           DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`           BIGINT       NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`           DATETIME     NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`           BIGINT       NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`           DATETIME     NULL COMMENT '删除时间'
) COMMENT '权限表';

CREATE INDEX `ix_sys_authority__parent_id` ON `sys_authority` (`parent_id`);
CREATE INDEX `ix_sys_authority__authority_type` ON `sys_authority` (`authority_type`);
CREATE INDEX `ix_sys_authority__authority_biz_type` ON `sys_authority` (`authority_biz_type`);
CREATE INDEX `ix_sys_authority__authority_scope_type` ON `sys_authority` (`authority_scope_type`);
CREATE INDEX `ix_sys_authority__code` ON `sys_authority` (`code`);

--
-- 套餐表
--

DROP TABLE IF EXISTS `sys_package`;

CREATE TABLE `sys_package`
(
    `id`          BIGINT        NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `biz_type`    VARCHAR(100)  NOT NULL DEFAULT '' COMMENT '业务类型',
    `code`        VARCHAR(150)  NOT NULL DEFAULT '' COMMENT '编号',
    `title`       VARCHAR(150)  NOT NULL DEFAULT '' COMMENT '标题',
    `label`       VARCHAR(150)  NOT NULL DEFAULT '' COMMENT '文本',
    `privilege`   VARCHAR(1000) NOT NULL DEFAULT '' COMMENT '特权',
    `default_ind` TINYINT       NOT NULL DEFAULT 0 COMMENT '是否默认',
    `trial_ind`   TINYINT       NOT NULL DEFAULT 0 COMMENT '是否允许试用',
    `trial_limit` INT           NOT NULL DEFAULT 60 COMMENT '试用时长，单位是自然天',
    `level`       INT           NOT NULL DEFAULT 1 COMMENT '会员等级，等级越高显示优先级越高',
    `idx`         INT           NOT NULL DEFAULT 999 COMMENT '序号',
    `description` VARCHAR(255)  NOT NULL DEFAULT '' COMMENT '备注',
    `source`      SMALLINT      NOT NULL DEFAULT 1 COMMENT '来源',
    `version`     BIGINT        NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`      SMALLINT      NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`  BIGINT        NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME      NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`  BIGINT        NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`  DATETIME      NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`  BIGINT        NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`  DATETIME      NULL COMMENT '删除时间'
) COMMENT '套餐表';

CREATE INDEX `ix_sys_package__biz_type` ON `sys_package` (`biz_type`);
CREATE INDEX `ix_sys_package__code` ON `sys_package` (`code`);

--
-- 套餐明细表
--

DROP TABLE IF EXISTS `sys_package_item`;

CREATE TABLE `sys_package_item`
(
    `id`                    BIGINT         NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `biz_type`              VARCHAR(100)   NOT NULL DEFAULT '' COMMENT '业务类型',
    `package_id`            BIGINT         NOT NULL DEFAULT 0 COMMENT '套餐ID',
    `code`                  VARCHAR(150)   NOT NULL DEFAULT '' COMMENT '编号',
    `title`                 VARCHAR(150)   NOT NULL DEFAULT '' COMMENT '标题',
    `label`                 VARCHAR(150)   NOT NULL DEFAULT '' COMMENT '文本',
    `automatic_renewal_ind` TINYINT        NOT NULL DEFAULT 0 COMMENT '是否自动续费',
    `list_price`            NUMERIC(10, 6) NOT NULL DEFAULT 0 COMMENT '划线价格',
    `price`                 NUMERIC(10, 6) NOT NULL DEFAULT 0 COMMENT '价格',
    `date_unit`             INT            NOT NULL DEFAULT 0 COMMENT '单位',
    `date_value`            INT            NOT NULL DEFAULT 0 COMMENT '单位',
    `idx`                   INT            NOT NULL DEFAULT 999 COMMENT '序号',
    `description`           VARCHAR(255)   NOT NULL DEFAULT '' COMMENT '备注',
    `status`                SMALLINT       NOT NULL DEFAULT 1 COMMENT '发布状态',
    `version`               BIGINT         NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`                SMALLINT       NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`            BIGINT         NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`            DATETIME       NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`            BIGINT         NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`            DATETIME       NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`            BIGINT         NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`            DATETIME       NULL COMMENT '删除时间'
) COMMENT '套餐明细表';

CREATE INDEX `ix_sys_package_item__biz_type` ON `sys_package_item` (`biz_type`);
CREATE INDEX `ix_sys_package_item__package_id` ON `sys_package_item` (`package_id`);
CREATE INDEX `ix_sys_package_item__code` ON `sys_package_item` (`code`);

--
-- 实体-权限关联表
--

DROP TABLE IF EXISTS `sys_entity_authority`;

CREATE TABLE `sys_entity_authority`
(
    `id`           BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`    BIGINT       NOT NULL DEFAULT 0 COMMENT '租户ID',
    `biz_type`     VARCHAR(100) NOT NULL DEFAULT '' COMMENT '业务类型',
    `entity_id`    BIGINT       NOT NULL DEFAULT 0 COMMENT '实体ID',
    `authority_id` BIGINT       NOT NULL DEFAULT 0 COMMENT '权限ID',
    `version`      BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`       SMALLINT     NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`   BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`   DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间'
) COMMENT '实体-权限关联表';

CREATE INDEX `ix_sys_entity_authority__entity` ON `sys_entity_authority` (`tenant_id`, `biz_type`, `entity_id`);

--
-- 套餐实体关联表
-- entity_type  - TENANT     - 租户会员套餐
-- entity_type  - ACCOUNT    - 账号会员套餐
--

DROP TABLE IF EXISTS `sys_entity_package`;

CREATE TABLE `sys_entity_package`
(
    `id`                BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`         BIGINT       NOT NULL DEFAULT 0 COMMENT '租户ID',
    `biz_type`          VARCHAR(100) NOT NULL DEFAULT '' COMMENT '业务类型',
    `entity_id`         BIGINT       NOT NULL DEFAULT 0 COMMENT '实体ID',
    `package_id`        BIGINT       NOT NULL DEFAULT 0 COMMENT '套餐ID',
    `trial_start_date`  DATETIME COMMENT '会员试用开始时间',
    `trial_end_date`    DATETIME COMMENT '会员试用结束时间',
    `registration_date` DATETIME COMMENT '会员注册时间',
    `expiration_date`   DATETIME COMMENT '会员到期时间',
    `description`       VARCHAR(255) NOT NULL DEFAULT '' COMMENT '备注说明',
    `version`           BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`            SMALLINT     NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`        BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`        DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`        BIGINT       NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`        DATETIME     NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`        BIGINT       NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`        DATETIME     NULL COMMENT '删除时间'
) COMMENT '套餐实体关联表';

CREATE INDEX `ix_sys_package_entity__tenant_id` ON `sys_entity_package` (`tenant_id`);
CREATE INDEX `ix_sys_package_entity__package_id` ON `sys_entity_package` (`package_id`);
CREATE INDEX `ix_sys_package_entity__biz_type` ON `sys_entity_package` (`biz_type`, `entity_id`);

--
-- 实体套餐开通记录表
--

DROP TABLE IF EXISTS `sys_package_log`;

CREATE TABLE `sys_package_log`
(
    `id`          BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`   BIGINT       NOT NULL DEFAULT 0 COMMENT '租户ID',
    `biz_type`    VARCHAR(100) NOT NULL DEFAULT '' COMMENT '业务类型',
    `biz_id`      BIGINT       NOT NULL DEFAULT 0 COMMENT '业务ID',
    `package_id`  BIGINT       NOT NULL DEFAULT 0 COMMENT '套餐ID',
    `order_id`    BIGINT       NOT NULL DEFAULT 0 COMMENT '关联订单ID',
    `quota`       BIGINT       NOT NULL DEFAULT 0 COMMENT '额度',
    `description` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '备注',
    `log_type`    SMALLINT     NOT NULL DEFAULT '0' COMMENT '日志类型',
    `version`     BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`      SMALLINT     NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`  BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`  BIGINT       NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`  DATETIME     NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`  BIGINT       NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`  DATETIME     NULL COMMENT '删除时间'
) COMMENT '实体套餐开通记录表';

CREATE INDEX `ix_sys_package_log__tenant_id` ON `sys_package_log` (`tenant_id`);
CREATE INDEX `ix_sys_package_log__biz_id` ON `sys_package_log` (`biz_type`, `biz_id`);
CREATE INDEX `ix_sys_package_log__package_id` ON `sys_package_log` (`package_id`);

--
-- 订单表
--

DROP TABLE IF EXISTS `sys_order`;

CREATE TABLE `sys_order`
(
    `id`            BIGINT         NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`     BIGINT         NOT NULL DEFAULT 0 COMMENT '租户ID',
    `biz_type`      VARCHAR(100)   NOT NULL DEFAULT '' COMMENT '业务类型',
    `biz_id`        BIGINT         NOT NULL DEFAULT 0 COMMENT '业务ID',
    `order_type_id` BIGINT         NOT NULL DEFAULT 0 COMMENT '订单类型ID',
    `pay_type_id`   BIGINT         NOT NULL DEFAULT 0 COMMENT '支付类型ID',
    `order_sn`      VARCHAR(50)    NOT NULL DEFAULT '' COMMENT '订单编号',
    `total_amount`  NUMERIC(10, 6) NOT NULL DEFAULT 0 COMMENT '订单总金额',
    `pay_amount`    NUMERIC(10, 6) NOT NULL DEFAULT 0 COMMENT '支付金额',
    `pay_type`      VARCHAR(50)    NOT NULL DEFAULT '' COMMENT '支付方式',
    `pay_time`      DATETIME       NULL     DEFAULT NULL COMMENT '支付时间',
    `pay_status`    VARCHAR(50)    NOT NULL DEFAULT '' COMMENT '支付状态',
    `status`        SMALLINT       NOT NULL DEFAULT 1 COMMENT '状态',
    `source`        SMALLINT       NOT NULL DEFAULT 1 COMMENT '订单来源',
    `version`       BIGINT         NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`        SMALLINT       NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`    BIGINT         NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`    DATETIME       NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`    BIGINT         NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`    DATETIME       NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`    BIGINT         NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`    DATETIME       NULL COMMENT '删除时间'
) COMMENT '订单表';

CREATE INDEX `ix_sys_order__biz_id` ON `sys_order` (`biz_type`, `biz_id`);

--
-- 订单明细表
--

DROP TABLE IF EXISTS `sys_order_item`;

CREATE TABLE `sys_order_item`
(
    `id`           BIGINT         NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`    BIGINT         NOT NULL DEFAULT 0 COMMENT '租户ID',
    `biz_type`     VARCHAR(100)   NOT NULL DEFAULT '' COMMENT '业务类型',
    `biz_id`       BIGINT         NOT NULL DEFAULT 0 COMMENT '业务ID',
    `order_id`     BIGINT         NOT NULL DEFAULT 0 COMMENT '订单ID',
    `item_id`      BIGINT         NOT NULL DEFAULT 0 COMMENT '商品ID',
    `quantity`     INT            NOT NULL DEFAULT 0 COMMENT '商品数量',
    `price`        NUMERIC(10, 6) NOT NULL DEFAULT 0 COMMENT '商品单价',
    `total_amount` NUMERIC(10, 6) NOT NULL DEFAULT 0 COMMENT '商品总金额',
    `version`      BIGINT         NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`       SMALLINT       NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`   BIGINT         NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`   DATETIME       NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`   BIGINT         NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`   DATETIME       NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`   BIGINT         NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`   DATETIME       NULL COMMENT '删除时间'
) COMMENT '订单明细表';

CREATE INDEX `ix_sys_order_item__order_id` ON `sys_order_item` (`order_id`);
CREATE INDEX `ix_sys_order_item__biz_id` ON `sys_order_item` (`biz_type`, `biz_id`);
CREATE INDEX `ix_sys_order_item__tenant_id` ON `sys_order_item` (`tenant_id`);

--
-- 订单支付表
--

DROP TABLE IF EXISTS `sys_order_pay`;

CREATE TABLE `sys_order_pay`
(
    `id`               BIGINT         NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`        BIGINT         NOT NULL DEFAULT 0 COMMENT '租户ID',
    `order_id`         BIGINT         NOT NULL DEFAULT 0 COMMENT '订单ID',
    `biz_id`           BIGINT         NOT NULL DEFAULT 0 COMMENT '支付类型ID',
    `biz_type`         VARCHAR(200)   NOT NULL DEFAULT '' COMMENT '支付方式',
    `pay_sn`           VARCHAR(128)   NOT NULL DEFAULT '' COMMENT '支付流水号，加密货币支付时对应交易哈希',
    `pay_address`      VARCHAR(200)   NOT NULL DEFAULT '' COMMENT '钱包地址，仅对加密货币支付有效',
    `pay_amount`       DECIMAL(10, 6) NOT NULL DEFAULT 0.00 COMMENT '应付总额',
    `pay_status`       VARCHAR(100)   NOT NULL DEFAULT '' COMMENT '支付状态',
    `pay_time`         DATETIME       NULL     DEFAULT NULL COMMENT '支付时间',
    `pay_subject`      VARCHAR(200)   NOT NULL DEFAULT '' COMMENT '交易内容',
    `confirm_time`     DATETIME       NULL     DEFAULT NULL COMMENT '确认时间',
    `callback_time`    DATETIME       NULL     DEFAULT NULL COMMENT '回调时间',
    `callback_content` VARCHAR(500)   NOT NULL DEFAULT '' COMMENT '回调内容',
    `version`          BIGINT         NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`           SMALLINT       NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`       BIGINT         NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`       DATETIME       NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`       BIGINT         NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`       DATETIME       NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`       BIGINT         NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`       DATETIME       NULL COMMENT '删除时间'
) COMMENT '订单支付表';

CREATE INDEX `ix_sys_order_pay__order_id` ON `sys_order_pay` (`order_id`);
CREATE INDEX `ix_sys_order_pay__biz_id` ON `sys_order_pay` (`biz_id`);
CREATE INDEX `ix_sys_order_pay__biz_type` ON `sys_order_pay` (`biz_type`);
CREATE INDEX `ix_sys_order_pay__pay_sn` ON `sys_order_pay` (`pay_sn`);

--
-- 订单日志表
--

DROP TABLE IF EXISTS `sys_order_log`;

CREATE TABLE `sys_order_log`
(
    `id`         BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`  BIGINT       NOT NULL DEFAULT 0 COMMENT '租户ID',
    `biz_type`   VARCHAR(100) NOT NULL DEFAULT '' COMMENT '业务类型',
    `biz_id`     BIGINT       NOT NULL DEFAULT 0 COMMENT '业务ID',
    `order_id`   BIGINT       NOT NULL DEFAULT 0 COMMENT '订单ID',
    `details`    TEXT COMMENT '日志详情',
    `version`    BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`     SMALLINT     NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by` BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at` DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by` BIGINT       NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at` DATETIME     NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by` BIGINT       NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at` DATETIME     NULL COMMENT '删除时间'
) COMMENT '订单日志表';

CREATE INDEX `ix_sys_order_log__order_id` ON `sys_order_log` (`order_id`);
CREATE INDEX `ix_sys_order_log__biz_id` ON `sys_order_log` (`biz_type`, `biz_id`);
CREATE INDEX `ix_sys_order_log__tenant_id` ON `sys_order_log` (`tenant_id`);

--
-- 社交账号关联表
--

DROP TABLE IF EXISTS `sys_entity_open_id`;

CREATE TABLE `sys_entity_open_id`
(
    `id`         BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`  BIGINT       NOT NULL DEFAULT 0 COMMENT '租户ID',
    `biz_type`   VARCHAR(100) NOT NULL DEFAULT '' COMMENT '业务类型',
    `biz_id`     BIGINT       NOT NULL DEFAULT 0 COMMENT '业务ID',
    `open_id`    VARCHAR(255) NOT NULL DEFAULT '' COMMENT 'Open ID',
    `union_id`   VARCHAR(255) NOT NULL DEFAULT '' COMMENT 'Union Id',
    `username`   VARCHAR(255) NOT NULL DEFAULT '' COMMENT '用户名',
    `nickname`   VARCHAR(255) NOT NULL DEFAULT '' COMMENT '昵称',
    `email`      VARCHAR(255) NOT NULL DEFAULT '' COMMENT '用户邮箱',
    `avatar`     VARCHAR(500) NOT NULL DEFAULT '' COMMENT '头像地址',
    `status`     SMALLINT     NOT NULL DEFAULT 1 COMMENT '状态',
    `source`     SMALLINT     NOT NULL DEFAULT 1 COMMENT '来源',
    `version`    BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`     SMALLINT     NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by` BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at` DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by` BIGINT       NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at` DATETIME     NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by` BIGINT       NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at` DATETIME     NULL COMMENT '删除时间'
) COMMENT '社交账号关联表';

CREATE INDEX `ix_sys_entity_open__tenant_id` ON `sys_entity_open_id` (`tenant_id`);
CREATE INDEX `ix_sys_entity_open__biz_id` ON `sys_entity_open_id` (`biz_type`, `biz_id`);

--
-- 账号表
--

DROP TABLE IF EXISTS `sys_account`;

CREATE TABLE `sys_account`
(
    `id`                   BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`            BIGINT       NOT NULL DEFAULT 0 COMMENT '租户ID',
    `uuid`                 VARCHAR(150) NOT NULL DEFAULT '' COMMENT 'UUID',
    `username`             VARCHAR(150) NOT NULL DEFAULT '' COMMENT '用户名',
    `name`                 VARCHAR(255) NOT NULL DEFAULT '' COMMENT '姓名',
    `display_name`         VARCHAR(255) NOT NULL DEFAULT '' COMMENT '昵称',
    `avatar_url`           VARCHAR(255) NOT NULL DEFAULT '' COMMENT '头像',
    `email`                VARCHAR(150) NOT NULL DEFAULT '' COMMENT '电子邮箱',
    `mobile_country_code`  VARCHAR(10)  NOT NULL DEFAULT '' COMMENT '手机国家区号',
    `mobile_number`        VARCHAR(50)  NOT NULL DEFAULT '' COMMENT '手机号码',
    `password`             VARCHAR(255) NOT NULL DEFAULT '' COMMENT '密码',
    `id_card_type`         VARCHAR(50)  NOT NULL DEFAULT '' COMMENT '证件类型',
    `id_card_no`           VARCHAR(50)  NOT NULL DEFAULT '' COMMENT '证件号码',
    `sex`                  VARCHAR(10)  NOT NULL DEFAULT '' COMMENT '性别',
    `birthday`             DATE COMMENT '生日',
    `signature`            VARCHAR(255) NOT NULL DEFAULT '' COMMENT '个性签名',
    `description`          VARCHAR(255) NOT NULL DEFAULT '' COMMENT '备注',
    `last_login_status`    VARCHAR(255) NOT NULL DEFAULT '' COMMENT '最后登录状态',
    `last_login_at`        VARCHAR(255) NOT NULL DEFAULT '' COMMENT '最后登录时间',
    `last_login_ip`        VARCHAR(255) NOT NULL DEFAULT '' COMMENT '最后登录IP',
    `password_expire_at`   VARCHAR(255) NOT NULL DEFAULT '' COMMENT '密码过期时间',
    `password_error_at`    VARCHAR(255) NOT NULL DEFAULT '' COMMENT '最后一次输入错误密码的时间',
    `password_error_count` INT          NOT NULL DEFAULT 0 COMMENT '输入错误密码的次数',
    `telegram`             VARCHAR(255) NOT NULL DEFAULT '' COMMENT 'Telegram',
    `registration`         DATETIME     NOT NULL DEFAULT NOW() COMMENT '注册时间',
    `invite_code`          VARCHAR(255) NOT NULL DEFAULT '' COMMENT '邀请码',
    `invite_by`            BIGINT       NOT NULL DEFAULT 0 COMMENT '邀请人',
    `status`               SMALLINT     NOT NULL DEFAULT 1 COMMENT '状态',
    `source`               SMALLINT     NOT NULL DEFAULT 1 COMMENT '来源',
    `version`              BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`               SMALLINT     NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`           BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`           DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`           BIGINT       NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`           DATETIME     NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`           BIGINT       NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`           DATETIME     NULL COMMENT '删除时间'
) COMMENT '账号表';

CREATE INDEX `ix_sys_account__active` ON `sys_account` (`active`);
CREATE INDEX `ix_sys_account__username` ON `sys_account` (`username`);
CREATE INDEX `ix_sys_account__email` ON `sys_account` (`email`);
CREATE INDEX `ix_sys_account__mobile` ON `sys_account` (`mobile_country_code`, `mobile_number`);
CREATE INDEX `ix_sys_account__invite_code` ON `sys_account` (`invite_code`);
CREATE INDEX `ix_sys_account__invite_by` ON `sys_account` (`invite_by`);

--
-- 角色表
--

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role`
(
    `id`              BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`       BIGINT       NOT NULL DEFAULT 0 COMMENT '租户ID',
    `code`            VARCHAR(150) NOT NULL DEFAULT '' COMMENT '编号',
    `title`           VARCHAR(150) NOT NULL DEFAULT '' COMMENT '标题',
    `label`           VARCHAR(150) NOT NULL DEFAULT '' COMMENT '文本',
    `data_score_type` varchar(100) NOT NULL DEFAULT '' COMMENT '数据范围',
    `description`     VARCHAR(255) NOT NULL DEFAULT '' COMMENT '备注',
    `status`          SMALLINT     NOT NULL DEFAULT 1 COMMENT '状态',
    `source`          SMALLINT     NOT NULL DEFAULT 1 COMMENT '来源',
    `version`         BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`          SMALLINT     NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`      BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`      DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`      BIGINT       NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`      DATETIME     NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`      BIGINT       NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`      DATETIME     NULL COMMENT '删除时间'
) COMMENT '角色表';

CREATE INDEX `ix_sys_role__code` ON `sys_role` (`code`);

--
-- 组织表
--

DROP TABLE IF EXISTS `sys_organization`;

CREATE TABLE `sys_organization`
(
    `id`          BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`   BIGINT       NOT NULL DEFAULT 0 COMMENT '租户ID',
    `parent_id`   BIGINT       NOT NULL DEFAULT 0 COMMENT '父级ID',
    `code`        VARCHAR(150) NOT NULL DEFAULT '' COMMENT '编号',
    `label`       VARCHAR(250) NOT NULL DEFAULT '' COMMENT '文本',
    `title`       VARCHAR(150) NOT NULL DEFAULT '' COMMENT '标题',
    `root_ind`    TINYINT      NOT NULL DEFAULT 0 COMMENT '是否顶层',
    `default_ind` TINYINT      NOT NULL DEFAULT 0 COMMENT '是否默认',
    `description` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '备注',
    `status`      SMALLINT     NOT NULL DEFAULT 1 COMMENT '状态',
    `source`      SMALLINT     NOT NULL DEFAULT 1 COMMENT '来源',
    `version`     BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`      SMALLINT     NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`  BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`  BIGINT       NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`  DATETIME     NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`  BIGINT       NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`  DATETIME     NULL COMMENT '删除时间'
) COMMENT '组织表';

CREATE INDEX `ix_sys_organization__tenant_id` ON `sys_organization` (`tenant_id`);
CREATE INDEX `ix_sys_organization__parent_id` ON `sys_organization` (`parent_id`);
CREATE INDEX `ix_sys_organization__code` ON `sys_organization` (`code`);

--
-- 岗位表
--

DROP TABLE IF EXISTS `sys_position`;

CREATE TABLE `sys_position`
(
    `id`          BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`   BIGINT       NOT NULL DEFAULT 0 COMMENT '租户ID',
    `parent_id`   BIGINT       NOT NULL DEFAULT 0 COMMENT '父级ID',
    `code`        VARCHAR(150) NOT NULL DEFAULT '' COMMENT '编号',
    `label`       VARCHAR(250) NOT NULL DEFAULT '' COMMENT '文本',
    `title`       VARCHAR(150) NOT NULL DEFAULT '' COMMENT '标题',
    `description` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '备注',
    `root_ind`    TINYINT      NOT NULL DEFAULT 0 COMMENT '是否顶层',
    `default_ind` TINYINT      NOT NULL DEFAULT 0 COMMENT '是否默认',
    `status`      SMALLINT     NOT NULL DEFAULT 1 COMMENT '状态',
    `source`      SMALLINT     NOT NULL DEFAULT 1 COMMENT '来源',
    `version`     BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`      SMALLINT     NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`  BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`  BIGINT       NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`  DATETIME     NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`  BIGINT       NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`  DATETIME     NULL COMMENT '删除时间'
) COMMENT '岗位表';

CREATE INDEX `ix_sys_position__tenant_id` ON `sys_position` (`tenant_id`);
CREATE INDEX `ix_sys_position__parent_id` ON `sys_position` (`parent_id`);
CREATE INDEX `ix_sys_position__code` ON `sys_position` (`code`);

--
-- 用户表
--

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user`
(
    `id`                   BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`            BIGINT       NOT NULL DEFAULT 0 COMMENT '租户ID',
    `uuid`                 VARCHAR(150) NOT NULL DEFAULT '' COMMENT 'UUID',
    `username`             VARCHAR(150) NOT NULL DEFAULT '' COMMENT '用户名',
    `name`                 VARCHAR(255) NOT NULL DEFAULT '' COMMENT '姓名',
    `display_name`         VARCHAR(255) NOT NULL DEFAULT '' COMMENT '昵称',
    `email`                VARCHAR(150) NOT NULL DEFAULT '' COMMENT '电子邮箱',
    `mobile_country_code`  VARCHAR(10)  NOT NULL DEFAULT '' COMMENT '手机国家区号',
    `mobile_number`        VARCHAR(50)  NOT NULL DEFAULT '' COMMENT '手机号码',
    `password`             VARCHAR(255) NOT NULL DEFAULT '' COMMENT '密码',
    `id_card_type`         VARCHAR(50)  NOT NULL DEFAULT '' COMMENT '证件类型',
    `id_card_no`           VARCHAR(50)  NOT NULL DEFAULT '' COMMENT '证件号码',
    `sex`                  VARCHAR(20)  NOT NULL DEFAULT '' COMMENT '性别',
    `birthday`             DATE COMMENT '生日',
    `signature`            VARCHAR(255) NOT NULL DEFAULT '' COMMENT '个性签名',
    `description`          VARCHAR(255) NOT NULL DEFAULT '' COMMENT '备注',
    `last_login_status`    VARCHAR(255) NOT NULL DEFAULT '' COMMENT '最后登录状态',
    `last_login_at`        VARCHAR(255) NOT NULL DEFAULT '' COMMENT '最后登录时间',
    `last_login_ip`        VARCHAR(255) NOT NULL DEFAULT '' COMMENT '最后登录IP',
    `password_expire_at`   VARCHAR(255) NOT NULL DEFAULT '' COMMENT '密码过期时间',
    `password_error_at`    VARCHAR(255) NOT NULL DEFAULT '' COMMENT '最后一次输入错误密码的时间',
    `password_error_count` INT          NOT NULL DEFAULT 0 COMMENT '输入错误密码的次数',
    `telegram`             VARCHAR(255) NOT NULL DEFAULT '' COMMENT 'Telegram',
    `registration`         DATETIME     NOT NULL DEFAULT NOW() COMMENT '注册时间',
    `invite_code`          VARCHAR(255) NOT NULL DEFAULT '' COMMENT '邀请码',
    `invite_by`            BIGINT       NOT NULL DEFAULT 0 COMMENT '邀请人',
    `status`               SMALLINT     NOT NULL DEFAULT 1 COMMENT '状态',
    `source`               SMALLINT     NOT NULL DEFAULT 1 COMMENT '来源',
    `version`              BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`               SMALLINT     NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`           BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`           DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`           BIGINT       NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`           DATETIME     NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`           BIGINT       NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`           DATETIME     NULL COMMENT '删除时间'
) COMMENT '用户表';

CREATE UNIQUE INDEX `ix_sys_user__username` ON `sys_user` (`username`);
CREATE UNIQUE INDEX `ix_sys_user__email` ON `sys_user` (`email`);
CREATE INDEX `ix_sys_user__active` ON `sys_user` (`active`);
CREATE INDEX `ix_sys_user__mobile` ON `sys_user` (`mobile_country_code`, `mobile_number`);
CREATE INDEX `ix_sys_user__invite_code` ON `sys_user` (`invite_code`);
CREATE INDEX `ix_sys_user__invite_by` ON `sys_user` (`invite_by`);

--
-- 用户-角色关联表
--

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role`
(
    `id`         BIGINT   NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`  BIGINT   NOT NULL DEFAULT 0 COMMENT '租户ID',
    `role_id`    BIGINT   NOT NULL DEFAULT 0 COMMENT '角色ID',
    `user_id`    BIGINT   NOT NULL DEFAULT 0 COMMENT '用户ID',
    `version`    BIGINT   NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`     SMALLINT NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by` BIGINT   NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at` DATETIME NOT NULL DEFAULT NOW() COMMENT '创建时间'
) COMMENT '用户-角色关联表';

CREATE INDEX `ix_sys_user_role__tenant_id` ON `sys_user_role` (`tenant_id`);
CREATE INDEX `ix_sys_user_role__role_id` ON `sys_user_role` (`role_id`);
CREATE INDEX `ix_sys_user_role__user_id` ON `sys_user_role` (`user_id`);

--
-- 实体关联表
--

DROP TABLE IF EXISTS `sys_entity_relation`;

CREATE TABLE `sys_entity_relation`
(
    `id`             BIGINT        NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`      BIGINT        NOT NULL DEFAULT 0 COMMENT '租户ID',
    `biz_type`       VARCHAR(100)  NOT NULL DEFAULT '' COMMENT '业务类型',
    `biz_id`         BIGINT        NOT NULL DEFAULT 0 COMMENT '业务ID',
    `ancestor_id`    BIGINT        NOT NULL DEFAULT 0 COMMENT '祖先ID',
    `entity_id`      BIGINT        NOT NULL DEFAULT 0 COMMENT '实体ID',
    `parent_ind`     TINYINT       NOT NULL DEFAULT 0 COMMENT '是否直接上级',
    `relation_path`  VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '关联路径',
    `relation_index` VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '关联层级',
    `version`        BIGINT        NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`         SMALLINT      NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`     BIGINT        NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`     DATETIME      NOT NULL DEFAULT NOW() COMMENT '创建时间'
) COMMENT '实体关联表';

CREATE INDEX `ix_sys_entity_relation__tenant_id` ON `sys_entity_relation` (`tenant_id`);
CREATE INDEX `ix_sys_entity_relation__ancestor_id` ON `sys_entity_relation` (`ancestor_id`);
CREATE INDEX `ix_sys_entity_relation__entity_id` ON `sys_entity_relation` (`entity_id`);
CREATE INDEX `ix_sys_entity_relation__biz_id` ON `sys_entity_relation` (`biz_type`, `biz_id`);

--
-- 登录会话记录
--

DROP TABLE IF EXISTS `sys_login_session`;

CREATE TABLE `sys_login_session`
(
    `id`                   BIGINT        NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`            BIGINT        NOT NULL DEFAULT 0 COMMENT '租户ID',
    `entity_type`          VARCHAR(100)  NOT NULL DEFAULT '' COMMENT '实体类型',
    `entity_id`            BIGINT        NOT NULL DEFAULT 0 COMMENT '实体ID',
    `session_id`           VARCHAR(50)   NOT NULL DEFAULT '' COMMENT 'Session ID',
    `username`             VARCHAR(255)  NOT NULL DEFAULT '' COMMENT '用户名',
    `host`                 VARCHAR(150)  NOT NULL DEFAULT '' COMMENT '用户登录主机',
    `ua`                   VARCHAR(2000) NOT NULL DEFAULT '' COMMENT 'User Agent',
    `client_id`            VARCHAR(150)  NOT NULL DEFAULT '' COMMENT '客户端编号',
    `client_name`          VARCHAR(150)  NOT NULL DEFAULT '' COMMENT '客户端名称',
    `client_version`       VARCHAR(150)  NOT NULL DEFAULT '' COMMENT '客户端版本',
    `start_datetime`       DATETIME      NOT NULL DEFAULT NOW() COMMENT '会话开始时间',
    `start_year`           INT           NOT NULL DEFAULT 0 COMMENT '会话开始年',
    `start_month`          INT           NOT NULL DEFAULT 0 COMMENT '会话开始月',
    `start_day`            INT           NOT NULL DEFAULT 0 COMMENT '会话开始天',
    `start_hour`           INT           NOT NULL DEFAULT 0 COMMENT '会话开始时',
    `start_minute`         INT           NOT NULL DEFAULT 0 COMMENT '会话开始分',
    `last_access_datetime` DATETIME      NOT NULL DEFAULT NOW() COMMENT '最近访问时间',
    `last_access_year`     INT           NOT NULL DEFAULT 0 COMMENT '最近访问年',
    `last_access_month`    INT           NOT NULL DEFAULT 0 COMMENT '最近访问月',
    `last_access_day`      INT           NOT NULL DEFAULT 0 COMMENT '最近访问天',
    `last_access_hour`     INT           NOT NULL DEFAULT 0 COMMENT '最近访问时',
    `last_access_minute`   INT           NOT NULL DEFAULT 0 COMMENT '最近访问钟',
    `end_datetime`         DATETIME      NULL COMMENT '会话结束时间',
    `success`              TINYINT       NOT NULL DEFAULT 1 COMMENT '是否成功登录',
    `version`              BIGINT        NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`               SMALLINT      NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`           BIGINT        NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`           DATETIME      NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`           BIGINT        NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`           DATETIME      NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`           BIGINT        NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`           DATETIME      NULL COMMENT '删除时间'
) COMMENT '登录会话记录';

CREATE INDEX `ix_sys_login_session__tenant_id` ON `sys_login_session` (`tenant_id`);
CREATE INDEX `ix_sys_login_session__entity_type` ON `sys_login_session` (`entity_type`, `entity_id`);
CREATE INDEX `ix_sys_login_session__session_id` ON `sys_login_session` (`session_id`);
CREATE INDEX `ix_sys_login_session__username` ON `sys_login_session` (`username`);

--
-- 系统设置表
--

DROP TABLE IF EXISTS `sys_config`;

CREATE TABLE `sys_config`
(
    `id`                  BIGINT        NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`           BIGINT        NOT NULL DEFAULT 0 COMMENT '租户ID',
    `title`               VARCHAR(100)  NOT NULL DEFAULT '' COMMENT '标题',
    `label`               VARCHAR(150)  NOT NULL DEFAULT '' COMMENT '文本',
    `config_content_type` VARCHAR(100)  NOT NULL DEFAULT '' COMMENT '配置内容类型',
    `config_group_type`   VARCHAR(100)  NOT NULL DEFAULT '' COMMENT '配置分组类型',
    `config_key`          VARCHAR(100)  NOT NULL DEFAULT '' COMMENT '参数名',
    `config_value`        VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '参数值',
    `default_value`       VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '默认值',
    `extra`               VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '附加信息',
    `help`                VARCHAR(250)  NOT NULL DEFAULT '' COMMENT '帮助信息',
    `description`         VARCHAR(250)  NOT NULL DEFAULT '' COMMENT '备注',
    `source`              SMALLINT      NOT NULL DEFAULT 1 COMMENT '数据来源',
    `version`             BIGINT        NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`              SMALLINT      NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`          BIGINT        NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`          DATETIME      NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`          BIGINT        NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`          DATETIME      NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`          BIGINT        NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`          DATETIME      NULL COMMENT '删除时间'
) COMMENT '系统设置表';

CREATE INDEX `ix_sys_config__tenant_id` ON `sys_config` (`tenant_id`);
CREATE INDEX `ix_sys_config__key` ON `sys_config` (`config_key`);
CREATE INDEX `ix_sys_config__group` ON `sys_config` (`config_group_type`);

--
-- 系统关键字表
-- tenant_id = 0 : 系统全局关键字
-- tenant_id > 0 : 租户专用关键字
--

DROP TABLE IF EXISTS `sys_keyword`;

CREATE TABLE `sys_keyword`
(
    `id`         BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`  BIGINT       NOT NULL DEFAULT 0 COMMENT '租户ID',
    `content`    VARCHAR(100) NOT NULL DEFAULT '' COMMENT '关键字',
    `version`    BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`     SMALLINT     NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by` BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at` DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by` BIGINT       NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at` DATETIME     NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by` BIGINT       NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at` DATETIME     NULL COMMENT '删除时间'
) COMMENT '系统关键字表';

--
-- 语言表
--

DROP TABLE IF EXISTS `sys_lang`;

CREATE TABLE `sys_lang`
(
    `id`          BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `code`        VARCHAR(50)  NOT NULL DEFAULT '' COMMENT '编号',
    `lang`        VARCHAR(50)  NOT NULL DEFAULT '' COMMENT '语言编码',
    `country`     VARCHAR(50)  NOT NULL DEFAULT '' COMMENT '地区编码',
    `label`       VARCHAR(250) NOT NULL DEFAULT '' COMMENT '文本',
    `description` VARCHAR(250) NOT NULL DEFAULT '' COMMENT '备注',
    `default_ind` TINYINT      NOT NULL DEFAULT 0 COMMENT '默认语言',
    `version`     BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`      SMALLINT     NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`  BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间'
) COMMENT '语言表';

CREATE INDEX `ix_sys_lang__code` ON `sys_lang` (`code`);

--
-- 租户-语言关联表
--

DROP TABLE IF EXISTS `sys_tenant_lang`;

CREATE TABLE `sys_tenant_lang`
(
    `id`         BIGINT   NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`  BIGINT   NOT NULL DEFAULT 0 COMMENT '租户ID',
    `lang_id`    BIGINT   NOT NULL DEFAULT 0 COMMENT '语言ID',
    `version`    BIGINT   NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`     SMALLINT NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by` BIGINT   NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at` DATETIME NOT NULL DEFAULT NOW() COMMENT '创建时间'
) COMMENT '角色-权限关联表';

CREATE INDEX `ix_sys_tenant_lang__tenant_id` ON `sys_tenant_lang` (`tenant_id`);
CREATE INDEX `ix_sys_tenant_lang__lang_id` ON `sys_tenant_lang` (`lang_id`);

--
-- 多语言文本表
--

DROP TABLE IF EXISTS `sys_label`;

CREATE TABLE `sys_label`
(
    `id`               BIGINT        NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `label_group_type` VARCHAR(255)  NOT NULL DEFAULT '' COMMENT '多语言分组',
    `code`             VARCHAR(255)  NOT NULL DEFAULT '' COMMENT '多语言标识',
    `zh_cn_label`      VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '简体中文',
    `zh_cn_static_ind` TINYINT       NOT NULL DEFAULT 0 COMMENT '是否静态文本',
    `zh_tw_label`      VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '繁体中文',
    `zh_tw_static_ind` TINYINT       NOT NULL DEFAULT 0 COMMENT '是否静态文本',
    `en_label`         VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '英语',
    `en_static_ind`    TINYINT       NOT NULL DEFAULT 0 COMMENT '是否静态文本',
    `ja_label`         VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '日语',
    `ja_static_ind`    TINYINT       NOT NULL DEFAULT 0 COMMENT '是否静态文本',
    `kr_label`         VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '韩语',
    `kr_static_ind`    TINYINT       NOT NULL DEFAULT 0 COMMENT '是否静态文本',
    `fr_label`         VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '法语',
    `fr_static_ind`    TINYINT       NOT NULL DEFAULT 0 COMMENT '是否静态文本',
    `version`          BIGINT        NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`           SMALLINT      NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`       BIGINT        NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`       DATETIME      NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`       BIGINT        NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`       DATETIME      NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`       BIGINT        NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`       DATETIME      NULL COMMENT '删除时间'
) COMMENT '多语言文本表';

CREATE INDEX `ix_sys_label__group` ON `sys_label` (`label_group_type`);
CREATE INDEX `ix_sys_label__code` ON `sys_label` (`code`);

--
-- 实体多语言文本表
--

DROP TABLE IF EXISTS `sys_entity_label`;

CREATE TABLE `sys_entity_label`
(
    `id`              BIGINT        NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`       BIGINT        NOT NULL DEFAULT 0 COMMENT '租户ID',
    `lang_id`         BIGINT        NOT NULL DEFAULT 0 COMMENT '语言ID',
    `lang_code`       VARCHAR(255)  NOT NULL DEFAULT '' COMMENT '语言编号',
    `entity_class`    VARCHAR(255)  NOT NULL DEFAULT '' COMMENT '实体类名',
    `entity_property` VARCHAR(255)  NOT NULL DEFAULT '' COMMENT '实体属性',
    `entity_id`       BIGINT        NOT NULL DEFAULT 0 COMMENT '实体ID',
    `zh_cn_label`     VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '简体中文',
    `zh_tw_label`     VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '繁体中文',
    `en_label`        VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '英语',
    `ja_label`        VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '日语',
    `kr_label`        VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '韩语',
    `fr_label`        VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '法语',
    `version`         BIGINT        NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`          SMALLINT      NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`      BIGINT        NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`      DATETIME      NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`      BIGINT        NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`      DATETIME      NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`      BIGINT        NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`      DATETIME      NULL COMMENT '删除时间'
) COMMENT '实体多语言文本表';

CREATE INDEX `ix_sys_entity_label` ON `sys_entity_label` (`entity_id`, `entity_class`, `entity_property`);

--
-- 目录表
--

DROP TABLE IF EXISTS `sys_catalog`;

CREATE TABLE `sys_catalog`
(
    `id`          BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`   BIGINT       NOT NULL DEFAULT 0 COMMENT '租户ID',
    `biz_type`    VARCHAR(100) NOT NULL DEFAULT '' COMMENT '业务类型',
    `biz_id`      BIGINT       NOT NULL DEFAULT 0 COMMENT '业务ID',
    `code`        VARCHAR(150) NOT NULL DEFAULT '' COMMENT '编号',
    `title`       VARCHAR(255) NOT NULL DEFAULT '' COMMENT '标题',
    `root_ind`    TINYINT      NOT NULL DEFAULT 0 COMMENT '是否顶层',
    `description` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '简介',
    `source`      SMALLINT     NOT NULL DEFAULT 1 COMMENT '数据来源',
    `version`     BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`      SMALLINT     NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`  BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`  BIGINT       NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`  DATETIME     NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`  BIGINT       NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`  DATETIME     NULL COMMENT '删除时间'
) COMMENT '目录表';

CREATE INDEX `ix_sys_catalog__biz_type` ON `sys_catalog` (`biz_type`, `biz_id`);
CREATE INDEX `ix_sys_catalog__tenant_id` ON `sys_catalog` (`tenant_id`);

--
-- 目录分类关联表
--

DROP TABLE IF EXISTS `sys_catalog_relation`;

CREATE TABLE `sys_catalog_relation`
(
    `id`            BIGINT        NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`     BIGINT        NOT NULL DEFAULT 0 COMMENT '租户ID',
    `biz_type`      VARCHAR(100)  NOT NULL DEFAULT '' COMMENT '关联类型',
    `ancestor_id`   BIGINT        NOT NULL DEFAULT 0 COMMENT '祖先ID',
    `entity_id`     BIGINT        NOT NULL DEFAULT 0 COMMENT '实体ID',
    `parent_ind`    SMALLINT      NOT NULL DEFAULT 0 COMMENT '是否直接上级',
    `relation_path` VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '关联路径',
    `idx`           VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '关联层级',
    `version`       BIGINT        NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`        SMALLINT      NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`    BIGINT        NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`    DATETIME      NOT NULL DEFAULT NOW() COMMENT '创建时间'
) COMMENT '目录分类关联表';

CREATE INDEX `ix_sys_catalog_relation__tenant_id` ON `sys_catalog_relation` (`tenant_id`);
CREATE INDEX `ix_sys_catalog_relation__biz_type` ON `sys_catalog_relation` (`biz_type`);
CREATE INDEX `ix_sys_catalog_relation__ancestor_id` ON `sys_catalog_relation` (`ancestor_id`);
CREATE INDEX `ix_sys_catalog_relation__entity_id` ON `sys_catalog_relation` (`entity_id`);

--
-- 字典明细表
--

DROP TABLE IF EXISTS `sys_dict`;

CREATE TABLE `sys_dict`
(
    `id`           BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`    BIGINT       NOT NULL DEFAULT 0 COMMENT '租户ID',
    `reference_id` BIGINT       NOT NULL DEFAULT 0 COMMENT '参考ID',
    `biz_type`     VARCHAR(100) NOT NULL DEFAULT '' COMMENT '业务类型',
    `biz_id`       BIGINT       NOT NULL DEFAULT 0 COMMENT '业务ID',
    `code`         VARCHAR(100) NOT NULL DEFAULT '' COMMENT '编号',
    `title`        VARCHAR(255) NOT NULL DEFAULT '' COMMENT '文本',
    `content`      TEXT         NULL COMMENT '内容',
    `extra`        TEXT         NULL COMMENT '附加信息',
    `idx`          INT          NOT NULL DEFAULT 999 COMMENT '序号',
    `status`       SMALLINT     NOT NULL DEFAULT 1 COMMENT '发布状态',
    `scope`        SMALLINT     NOT NULL DEFAULT 1 COMMENT '数据范围',
    `source`       SMALLINT     NOT NULL DEFAULT 1 COMMENT '数据来源',
    `version`      BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`       SMALLINT     NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`   BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`   DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`   BIGINT       NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`   DATETIME     NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`   BIGINT       NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`   DATETIME     NULL COMMENT '删除时间'
) COMMENT '字典明细表';

CREATE INDEX `ix_sys_dict__biz_id` ON `sys_dict` (`biz_type`);
CREATE INDEX `ix_sys_dict__tenant_id` ON `sys_dict` (`tenant_id`);

--
-- 字典关联表
--

DROP TABLE IF EXISTS `sys_dict_relation`;

CREATE TABLE `sys_dict_relation`
(
    `id`         BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`  BIGINT      NOT NULL DEFAULT 0 COMMENT '租户ID',
    `biz_type`   VARCHAR(50) NOT NULL DEFAULT '' COMMENT '业务类型',
    `biz_id`     BIGINT      NOT NULL DEFAULT 0 COMMENT '业务ID',
    `dict_id`    BIGINT      NOT NULL DEFAULT 0 COMMENT '字典ID',
    `version`    BIGINT      NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`     SMALLINT    NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by` BIGINT      NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at` DATETIME    NOT NULL DEFAULT NOW() COMMENT '创建时间'
) COMMENT '字典关联表';

CREATE INDEX `ix_sys_dict_relation__tenant_id` ON `sys_dict_relation` (`tenant_id`);
CREATE INDEX `ix_sys_dict_relation__dict_id` ON `sys_dict_relation` (`dict_id`);
CREATE INDEX `ix_sys_dict_relation__biz_type` ON `sys_dict_relation` (`biz_type`, `biz_id`);

--
-- 字典排序表
--

DROP TABLE IF EXISTS `sys_dict_sequence`;

CREATE TABLE `sys_dict_sequence`
(
    `id`         BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`  BIGINT      NOT NULL DEFAULT 0 COMMENT '租户ID',
    `biz_type`   VARCHAR(50) NOT NULL DEFAULT '' COMMENT '业务类型',
    `biz_id`     BIGINT      NOT NULL DEFAULT 0 COMMENT '业务ID',
    `dict_id`    BIGINT      NOT NULL DEFAULT 0 COMMENT '字典ID',
    `idx`        INT         NOT NULL DEFAULT 999 COMMENT '序号',
    `version`    BIGINT      NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`     SMALLINT    NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by` BIGINT      NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at` DATETIME    NOT NULL DEFAULT NOW() COMMENT '创建时间'
) COMMENT '字典排序表';

CREATE INDEX `ix_sys_dict_sequence__tenant_id` ON `sys_dict_sequence` (`tenant_id`);
CREATE INDEX `ix_sys_dict_sequence__dict_id` ON `sys_dict_sequence` (`dict_id`);
CREATE INDEX `ix_sys_dict_sequence__biz_type` ON `sys_dict_sequence` (`biz_type`, `biz_id`);

--
-- 标签表
--

DROP TABLE IF EXISTS `sys_tag`;

CREATE TABLE `sys_tag`
(
    `id`           BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`    BIGINT       NOT NULL DEFAULT 0 COMMENT '租户ID',
    `reference_id` BIGINT       NOT NULL DEFAULT 0 COMMENT '参考ID',
    `biz_type`     VARCHAR(50)  NOT NULL DEFAULT '' COMMENT '业务类型',
    `biz_id`       BIGINT       NOT NULL DEFAULT 0 COMMENT '业务ID',
    `title`        VARCHAR(150) NOT NULL DEFAULT '' COMMENT '标题',
    `content`      TEXT         NULL COMMENT '内容',
    `extra`        TEXT         NULL COMMENT '附加信息',
    `description`  VARCHAR(255) NOT NULL DEFAULT '' COMMENT '备注',
    `idx`          INT          NOT NULL DEFAULT 999 COMMENT '序号',
    `scope`        SMALLINT     NOT NULL DEFAULT 1 COMMENT '数据范围',
    `status`       SMALLINT     NOT NULL DEFAULT 1 COMMENT '发布状态',
    `source`       SMALLINT     NOT NULL DEFAULT 1 COMMENT '数据来源',
    `version`      BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`       SMALLINT     NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`   BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`   DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`   BIGINT       NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`   DATETIME     NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`   BIGINT       NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`   DATETIME     NULL COMMENT '删除时间'
) COMMENT '标签表';

CREATE INDEX `ix_sys_tag__tenant_id` ON `sys_tag` (`tenant_id`);
CREATE INDEX `ix_sys_tag__biz_type` ON `sys_tag` (`biz_type`);

--
-- 标签关联表
--

DROP TABLE IF EXISTS `sys_tag_relation`;

CREATE TABLE `sys_tag_relation`
(
    `id`         BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`  BIGINT      NOT NULL DEFAULT 0 COMMENT '租户ID',
    `biz_type`   VARCHAR(50) NOT NULL DEFAULT '' COMMENT '业务类型',
    `biz_id`     BIGINT      NOT NULL DEFAULT 0 COMMENT '业务ID',
    `tag_id`     BIGINT      NOT NULL DEFAULT 0 COMMENT '标签ID',
    `version`    BIGINT      NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`     SMALLINT    NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by` BIGINT      NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at` DATETIME    NOT NULL DEFAULT NOW() COMMENT '创建时间'
) COMMENT '标签关联表';

CREATE INDEX `ix_sys_tag_relation__tenant_id` ON `sys_tag_relation` (`tenant_id`);
CREATE INDEX `ix_sys_tag_relation__tag_id` ON `sys_tag_relation` (`tag_id`);
CREATE INDEX `ix_sys_tag_relation__biz_type` ON `sys_tag_relation` (`biz_type`, `biz_id`);

--
-- 标签排序表
--

DROP TABLE IF EXISTS `sys_tag_sequence`;

CREATE TABLE `sys_tag_sequence`
(
    `id`         BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`  BIGINT      NOT NULL DEFAULT 0 COMMENT '租户ID',
    `biz_type`   VARCHAR(50) NOT NULL DEFAULT '' COMMENT '业务类型',
    `biz_id`     BIGINT      NOT NULL DEFAULT 0 COMMENT '业务ID',
    `tag_id`     BIGINT      NOT NULL DEFAULT 0 COMMENT '标签ID',
    `idx`        INT         NOT NULL DEFAULT 999 COMMENT '序号',
    `version`    BIGINT      NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`     SMALLINT    NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by` BIGINT      NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at` DATETIME    NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by` BIGINT      NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at` DATETIME    NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by` BIGINT      NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at` DATETIME    NULL COMMENT '删除时间'
) COMMENT '标签排序表';

CREATE INDEX `ix_sys_tag_sequence__tenant_id` ON `sys_tag_sequence` (`tenant_id`);
CREATE INDEX `ix_sys_tag_sequence__tag_id` ON `sys_tag_sequence` (`tag_id`);
CREATE INDEX `ix_sys_tag_sequence__biz_type` ON `sys_tag_sequence` (`biz_type`, `biz_id`);

--
-- 附件文件表
--

DROP TABLE IF EXISTS `sys_attachment`;

CREATE TABLE `sys_attachment`
(
    `id`                BIGINT        NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`         BIGINT        NOT NULL DEFAULT 0 COMMENT '租户ID',
    `biz_type`          VARCHAR(50)   NOT NULL DEFAULT '' COMMENT '业务类型',
    `content_type`      VARCHAR(255)  NOT NULL DEFAULT '' COMMENT '文件类型',
    `storage_type`      VARCHAR(100)  NOT NULL DEFAULT '' COMMENT '文件存储类型',
    `access_type`       VARCHAR(100)  NOT NULL DEFAULT '' COMMENT '文件访问类型',
    `original_filename` VARCHAR(255)  NOT NULL DEFAULT '' COMMENT '原始文件名',
    `filename`          VARCHAR(255)  NOT NULL DEFAULT '' COMMENT '文件名',
    `file_key`          VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '文件标识',
    `size`              BIGINT        NOT NULL DEFAULT 0 COMMENT '文件大小',
    `url`               VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '文件链接',
    `extra`             TEXT          NULL COMMENT '附加信息',
    `version`           BIGINT        NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`            SMALLINT      NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`        BIGINT        NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`        DATETIME      NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`        BIGINT        NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`        DATETIME      NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`        BIGINT        NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`        DATETIME      NULL COMMENT '删除时间'
) COMMENT '附件文件表';

CREATE INDEX `ix_sys_attachment__tenant_id` ON `sys_attachment` (`tenant_id`);
CREATE INDEX `ix_sys_attachment__biz_type` ON `sys_attachment` (`biz_type`);

--
-- 附件关联表
--

DROP TABLE IF EXISTS `sys_attachment_relation`;

CREATE TABLE `sys_attachment_relation`
(
    `id`            BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`     BIGINT      NOT NULL DEFAULT 0 COMMENT '租户ID',
    `attachment_id` BIGINT      NOT NULL DEFAULT 0 COMMENT '附件ID',
    `biz_type`      VARCHAR(50) NOT NULL DEFAULT '' COMMENT '业务类型',
    `biz_id`        BIGINT      NOT NULL DEFAULT 0 COMMENT '业务ID',
    `version`       BIGINT      NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`        SMALLINT    NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`    BIGINT      NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`    DATETIME    NOT NULL DEFAULT NOW() COMMENT '创建时间'
) COMMENT '附件关联表';

CREATE INDEX `ix_sys_attachment_relation__tenant_id` ON `sys_attachment_relation` (`tenant_id`);
CREATE INDEX `ix_sys_attachment_relation__attachment_id` ON `sys_attachment_relation` (`attachment_id`);
CREATE INDEX `ix_sys_attachment_relation__biz_id` ON `sys_attachment_relation` (`biz_type`, `biz_id`);

--
-- 链接日志
--

DROP TABLE IF EXISTS `sys_url_log`;

CREATE TABLE `sys_url_log`
(
    `id`         BIGINT   NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `path`       VARCHAR(250) COMMENT '路径',
    `start_time` DATETIME NULL COMMENT '开始时间',
    `end_time`   DATETIME NULL COMMENT '结束时间',
    `exec_time`  LONG COMMENT '执行时长',
    `version`    BIGINT   NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`     SMALLINT NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by` BIGINT   NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at` DATETIME NOT NULL DEFAULT NOW() COMMENT '创建时间'
) COMMENT '链接日志';

CREATE INDEX `ix_sys_url_log__path` ON `sys_url_log` (`path`);

--
-- 登录日志表
--

DROP TABLE IF EXISTS `sys_login_log`;

CREATE TABLE `sys_login_log`
(
    `id`             BIGINT        NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`      BIGINT        NOT NULL DEFAULT 0 COMMENT '租户ID',
    `biz_type`       VARCHAR(50)   NOT NULL DEFAULT '' COMMENT '业务类型',
    `biz_id`         BIGINT        NOT NULL DEFAULT 0 COMMENT '业务ID',
    `host`           VARCHAR(150)  NOT NULL DEFAULT '' COMMENT '用户登录主机',
    `user_agent`     VARCHAR(2000) NOT NULL DEFAULT '' COMMENT 'User Agent',
    `client_id`      VARCHAR(150)  NOT NULL DEFAULT '' COMMENT '客户端编号',
    `client_name`    VARCHAR(150)  NOT NULL DEFAULT '' COMMENT '客户端名称',
    `client_version` VARCHAR(150)  NOT NULL DEFAULT '' COMMENT '客户端版本',
    `details`        VARCHAR(250) COMMENT '详情',
    `exception`      TEXT COMMENT '异常',
    `success`        TINYINT       NOT NULL DEFAULT 1 COMMENT '登录状态',
    `version`        BIGINT        NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`         SMALLINT      NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`     BIGINT        NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`     DATETIME      NOT NULL DEFAULT NOW() COMMENT '创建时间'
) COMMENT '登录日志表';

CREATE INDEX `ix_sys_login_log__tenant_id` ON `sys_login_log` (`tenant_id`);
CREATE INDEX `ix_sys_login_log__biz_id` ON `sys_login_log` (`biz_type`, `biz_id`);

--
-- 操作日志表
--

DROP TABLE IF EXISTS `sys_operation_log`;

CREATE TABLE `sys_operation_log`
(
    `id`              BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`       BIGINT       NOT NULL DEFAULT 0 COMMENT '租户ID',
    `biz_type`        VARCHAR(50)  NOT NULL DEFAULT '' COMMENT '业务类型',
    `biz_id`          BIGINT       NOT NULL DEFAULT 0 COMMENT '业务ID',
    `class_name`      VARCHAR(250) NOT NULL DEFAULT '' COMMENT '类名',
    `method_name`     VARCHAR(250) NOT NULL DEFAULT '' COMMENT '方法名',
    `request_id`      VARCHAR(250) NOT NULL DEFAULT '' COMMENT '请求ID',
    `request_ip`      VARCHAR(250) NOT NULL DEFAULT '' COMMENT '请求IP',
    `request_method`  VARCHAR(250) NOT NULL DEFAULT '' COMMENT '请求类型',
    `request_ua`      TEXT COMMENT '请求UA',
    `request_uri`     TEXT COMMENT '请求地址',
    `request_params`  TEXT COMMENT '请求参数',
    `request_headers` TEXT COMMENT '请求头',
    `start_time`      DATETIME COMMENT '开始时间',
    `end_time`        DATETIME COMMENT '结束时间',
    `exec_time`       INT          NOT NULL DEFAULT 0 COMMENT '执行时长',
    `details`         VARCHAR(250) COMMENT '详情',
    `exception`       TEXT COMMENT '异常',
    `version`         BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`          SMALLINT     NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`      BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`      DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间'
) COMMENT '操作日志表';

CREATE INDEX `ix_sys_operation_log__tenant_id` ON `sys_operation_log` (`tenant_id`);
CREATE INDEX `ix_sys_operation_log__biz_id` ON `sys_operation_log` (`biz_type`, `biz_id`);

--
-- 系统日志表
--

DROP TABLE IF EXISTS `sys_application_log`;

CREATE TABLE `sys_application_log`
(
    `id`          BIGINT   NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`   BIGINT   NOT NULL DEFAULT 0 COMMENT '租户ID',
    `class_name`  VARCHAR(250) COMMENT '类名',
    `method_name` VARCHAR(250) COMMENT '方法名',
    `request_id`  VARCHAR(250) COMMENT '请求ID',
    `details`     VARCHAR(250) COMMENT '详情',
    `exception`   TEXT COMMENT '异常',
    `version`     BIGINT   NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`      SMALLINT NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`  BIGINT   NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME NOT NULL DEFAULT NOW() COMMENT '创建时间'
) COMMENT '系统日志表';

CREATE INDEX `ix_sys_application_log__tenant_id` ON `sys_application_log` (`tenant_id`);

--
-- 验证码发送日志表
--

DROP TABLE IF EXISTS `sys_captcha_log`;

CREATE TABLE `sys_captcha_log`
(
    `id`                  BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`           BIGINT       NOT NULL DEFAULT 0 COMMENT '租户ID',
    `captcha_type`        VARCHAR(50)  NOT NULL DEFAULT '' COMMENT '验证码类型',
    `captcha_key`         VARCHAR(150) NOT NULL DEFAULT '' COMMENT '验证码标识',
    `captcha_value`       VARCHAR(150) NOT NULL DEFAULT '' COMMENT '验证码',
    `email`               VARCHAR(150) NOT NULL DEFAULT '' COMMENT '电子邮箱',
    `mobile_country_code` VARCHAR(150) NOT NULL DEFAULT '' COMMENT '手机区位码',
    `mobile_number`       VARCHAR(150) NOT NULL DEFAULT 0 COMMENT '手机号码',
    `version`             BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`              SMALLINT     NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`          BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`          DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间'
) COMMENT '验证码发送日志表';

CREATE INDEX `ix_sys_captcha_log__tenant_id` ON `sys_captcha_log` (`tenant_id`);

--
-- 用户搜索日志表
--

DROP TABLE IF EXISTS `sys_search_log`;

CREATE TABLE `sys_search_log`
(
    `id`         BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`  BIGINT       NOT NULL DEFAULT 0 COMMENT '租户ID',
    `user_id`    BIGINT       NOT NULL DEFAULT 0 COMMENT '用户ID',
    `search_key` VARCHAR(150) NOT NULL DEFAULT '' COMMENT '搜索关键字',
    `version`    BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`     SMALLINT     NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by` BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at` DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间'
) COMMENT '用户搜索日志表';

CREATE INDEX `ix_sys_search_log__tenant_id` ON `sys_search_log` (`tenant_id`);
CREATE INDEX `ix_sys_search_log__user_id` ON `sys_search_log` (`user_id`);

--
-- 消息通道表
--

DROP TABLE IF EXISTS `sys_message_channel`;

CREATE TABLE `sys_message_channel`
(
    `id`          BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `code`        VARCHAR(150) NOT NULL DEFAULT '' COMMENT '编号',
    `label`       VARCHAR(150) NOT NULL DEFAULT '' COMMENT '多语言文本',
    `title`       VARCHAR(255) NOT NULL DEFAULT '' COMMENT '标题',
    `description` TEXT         NULL COMMENT '描述备注',
    `status`      SMALLINT     NOT NULL DEFAULT 1 COMMENT '发布状态',
    `version`     BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`      SMALLINT     NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`  BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`  BIGINT       NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`  DATETIME     NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`  BIGINT       NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`  DATETIME     NULL COMMENT '删除时间'
) COMMENT '消息通道表';

--
-- 消息类型表
--

DROP TABLE IF EXISTS `sys_message_type`;

CREATE TABLE `sys_message_type`
(
    `id`          BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `code`        VARCHAR(150) NOT NULL DEFAULT '' COMMENT '编号',
    `label`       VARCHAR(150) NOT NULL DEFAULT '' COMMENT '多语言文本',
    `title`       VARCHAR(255) NOT NULL DEFAULT '' COMMENT '标题',
    `description` TEXT         NULL COMMENT '描述备注',
    `status`      SMALLINT     NOT NULL DEFAULT 1 COMMENT '发布状态',
    `version`     BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`      SMALLINT     NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`  BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`  BIGINT       NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`  DATETIME     NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`  BIGINT       NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`  DATETIME     NULL COMMENT '删除时间'
) COMMENT '消息类型表';

--
-- 消息模板表
--

DROP TABLE IF EXISTS `sys_message_template`;

CREATE TABLE `sys_message_template`
(
    `id`                 BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `message_channel_id` BIGINT       NOT NULL DEFAULT 0 COMMENT '消息通道ID',
    `message_channel`    VARCHAR(100) NOT NULL DEFAULT '' COMMENT '消息通道',
    `message_type_id`    BIGINT       NOT NULL DEFAULT 0 COMMENT '消息类型ID',
    `message_type`       VARCHAR(100) NOT NULL DEFAULT '' COMMENT '消息类型',
    `content`            TEXT         NOT NULL COMMENT '模板',
    `status`             SMALLINT     NOT NULL DEFAULT 1 COMMENT '发布状态',
    `version`            BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`             SMALLINT     NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`         BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`         DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`         BIGINT       NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`         DATETIME     NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`         BIGINT       NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`         DATETIME     NULL COMMENT '删除时间'
) COMMENT '消息模板表';

CREATE INDEX `ix_sys_message_template__message_type_id` ON `sys_message_template` (`message_type_id`);
CREATE INDEX `ix_sys_message_template__message_channel_id` ON `sys_message_template` (message_channel_id);

--
-- 消息表
--

DROP TABLE IF EXISTS `sys_message`;

CREATE TABLE `sys_message`
(
    `id`                   BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`            BIGINT       NOT NULL DEFAULT 0 COMMENT '租户ID',
    `message_type_id`      BIGINT       NOT NULL DEFAULT 0 COMMENT '消息类型ID',
    `message_type`         VARCHAR(100) NOT NULL DEFAULT '' COMMENT '消息类型',
    `subject`              VARCHAR(255) COMMENT '标题',
    `content`              TEXT COMMENT '内容',
    `data`                 TEXT COMMENT '数据，一般保存JSON格式的参数',
    `target_sent_datetime` DATETIME COMMENT '目标发送时间',
    `sent_datetime`        DATETIME COMMENT '发送时间',
    `attempt`              SMALLINT COMMENT '尝试发送次数',
    `status`               SMALLINT     NOT NULL DEFAULT 1 COMMENT '发布状态',
    `version`              BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`               SMALLINT     NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`           BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`           DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`           BIGINT       NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`           DATETIME     NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`           BIGINT       NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`           DATETIME     NULL COMMENT '删除时间'
) COMMENT '消息表';

CREATE INDEX ix_sys_message__tenant_id ON sys_message (tenant_id);
CREATE INDEX ix_sys_message__message_type_id ON sys_message (message_type_id);
CREATE INDEX ix_sys_message__message_type ON sys_message (message_type);

--
-- 消息用户表
--

DROP TABLE IF EXISTS `sys_message_user`;

CREATE TABLE `sys_message_user`
(
    `id`                  BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`           BIGINT       NOT NULL DEFAULT 0 COMMENT '租户ID',
    `biz_type`            VARCHAR(100) NOT NULL COMMENT '业务类型',
    `message_id`          BIGINT       NOT NULL COMMENT '消息ID',
    `user_type`           VARCHAR(100) NOT NULL DEFAULT '' COMMENT '实体类型',
    `user_id`             BIGINT       NOT NULL DEFAULT 0 COMMENT '实体ID',
    `display_name`        VARCHAR(255) COMMENT '姓名',
    `username`            VARCHAR(255) COMMENT '账号',
    `email`               VARCHAR(255) COMMENT '邮箱',
    `mobile_country_code` VARCHAR(150) NOT NULL DEFAULT '' COMMENT '手机区位码',
    `mobile_number`       VARCHAR(150) NOT NULL DEFAULT 0 COMMENT '手机号码',
    `version`             BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`              SMALLINT     NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`          BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`          DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`          BIGINT       NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`          DATETIME     NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`          BIGINT       NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`          DATETIME     NULL COMMENT '删除时间'
) COMMENT '消息用户表';

CREATE INDEX `ix_sys_message_user__tenant_id` ON `sys_message_user` (`tenant_id`);
CREATE INDEX `ix_sys_message_user__message_id` ON `sys_message_user` (`message_id`);
CREATE INDEX `ix_sys_message_user__biz_type` ON `sys_message_user` (`biz_type`);
CREATE INDEX `ix_sys_message_user__entity_type` ON `sys_message_user` (`user_type`, `user_id`);

--
-- 消息内容表
--

DROP TABLE IF EXISTS `sys_message_content`;

CREATE TABLE `sys_message_content`
(
    `id`                 BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`          BIGINT       NOT NULL DEFAULT 0 COMMENT '租户ID',
    `message_channel_id` BIGINT       NOT NULL DEFAULT 0 COMMENT '消息通道ID',
    `message_channel`    VARCHAR(100) NOT NULL DEFAULT '' COMMENT '消息通道',
    `message_type_id`    BIGINT       NOT NULL DEFAULT 0 COMMENT '消息类型ID',
    `message_type`       VARCHAR(100) NOT NULL DEFAULT '' COMMENT '消息类型',
    `message_id`         BIGINT       NOT NULL DEFAULT 0 COMMENT '消息ID',
    `content`            TEXT COMMENT '消息内容',
    `resp`               TEXT COMMENT '响应内容',
    `exception`          TEXT COMMENT '异常内容',
    `sent_datetime`      DATETIME COMMENT '发送时间',
    `status`             SMALLINT     NOT NULL DEFAULT 1 COMMENT '发布状态',
    `version`            BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`             SMALLINT     NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`         BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`         DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`         BIGINT       NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`         DATETIME     NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`         BIGINT       NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`         DATETIME     NULL COMMENT '删除时间'
) COMMENT '消息内容表';

CREATE INDEX ix_sys_message_content__tenant_id ON sys_message_content (tenant_id);
CREATE INDEX ix_sys_message_content__message_channel_id ON sys_message_content (message_channel_id);
CREATE INDEX ix_sys_message_content__message_type_id ON sys_message_content (message_type_id);
CREATE INDEX ix_sys_message_content__message_id ON sys_message_content (message_id);

--
-- 消息发送历史记录表
--

DROP TABLE IF EXISTS `sys_message_history`;

CREATE TABLE `sys_message_history`
(
    `id`          BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`   BIGINT       NOT NULL DEFAULT 0 COMMENT '租户ID',
    `biz_type`    VARCHAR(100) NOT NULL DEFAULT '' COMMENT '业务类型',
    `biz_id`      BIGINT       NOT NULL DEFAULT 0 COMMENT '业务ID',
    `message_id`  BIGINT       NOT NULL DEFAULT 0 COMMENT '消息ID',
    `entity_type` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '实体类型',
    `entity_id`   BIGINT       NOT NULL DEFAULT 0 COMMENT '实体ID',
    `version`     BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`      SMALLINT     NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`  BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间'
) COMMENT '消息发送历史记录表';

CREATE INDEX `ix_sys_message_history__tenant_id` ON `sys_message_history` (`tenant_id`);
CREATE INDEX `ix_sys_message_history__biz_type` ON `sys_message_history` (`biz_type`);
CREATE INDEX `ix_sys_message_history__message_id` ON `sys_message_history` (`message_id`);
CREATE INDEX `ix_sys_message_history__entity_type` ON `sys_message_history` (`entity_type`, `entity_id`);

--
-- 系统通知表
--

DROP TABLE IF EXISTS `sys_notice`;

CREATE TABLE `sys_notice`
(
    `id`             BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`      BIGINT       NOT NULL DEFAULT 0 COMMENT '租户ID',
    `subject`        VARCHAR(100) NOT NULL DEFAULT '' COMMENT '通知标题',
    `content`        VARCHAR(100) NOT NULL DEFAULT '' COMMENT '通知内容',
    `recipient_type` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '实体类型',
    `recipient_id`   BIGINT       NOT NULL DEFAULT 0 COMMENT '收件人ID',
    `sender_type`    VARCHAR(100) NOT NULL DEFAULT '' COMMENT '实体类型',
    `sender_id`      BIGINT       NOT NULL DEFAULT 0 COMMENT '发件人ID',
    `read_ind`       TINYINT      NOT NULL DEFAULT 0 COMMENT '是否已读',
    `read_datetime`  DATETIME COMMENT '阅读时间',
    `version`        BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`         SMALLINT     NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`     BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`     DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`     BIGINT       NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`     DATETIME     NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`     BIGINT       NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`     DATETIME     NULL COMMENT '删除时间'
) COMMENT '系统通知表';

CREATE INDEX `ix_sys_notice__sender` ON `sys_notice` (`recipient_type`, `sender_id`);
CREATE INDEX `ix_sys_notice__recipient` ON `sys_notice` (`sender_type`, `recipient_id`);

--
-- 宣传栏
--

DROP TABLE IF EXISTS `sys_banner`;

CREATE TABLE `sys_banner`
(
    `id`          BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`   BIGINT       NOT NULL DEFAULT 0 COMMENT '租户ID',
    `title`       VARCHAR(150) NOT NULL DEFAULT '' COMMENT '标题',
    `details`     TEXT         NULL COMMENT '详情',
    `description` TEXT         NULL COMMENT '备注',
    `idx`         INT          NOT NULL DEFAULT 999 COMMENT '序号',
    `version`     BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`      SMALLINT     NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`  BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`  BIGINT       NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`  DATETIME     NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`  BIGINT       NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`  DATETIME     NULL COMMENT '删除时间'
) COMMENT '宣传栏';

--
-- 资讯表
--

DROP TABLE IF EXISTS `sys_announcement`;

CREATE TABLE `sys_announcement`
(
    `id`                BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`         BIGINT       NOT NULL DEFAULT 0 COMMENT '租户ID',
    `title`             VARCHAR(150) NOT NULL DEFAULT '' COMMENT '标题',
    `content`           TEXT         NULL COMMENT '内容',
    `status`            SMALLINT     NOT NULL DEFAULT 1 COMMENT '发布状态',
    `start_datetime`    DATETIME     NOT NULL DEFAULT NOW() COMMENT '发布期限',
    `end_datetime`      DATETIME     NOT NULL DEFAULT NOW() COMMENT '发布期限',
    `allow_comment_ind` TINYINT      NOT NULL DEFAULT 1 COMMENT '是否允许评论',
    `description`       VARCHAR(255) NOT NULL DEFAULT '' COMMENT '备注',
    `version`           BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`            SMALLINT     NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`        BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`        DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`        BIGINT       NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`        DATETIME     NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`        BIGINT       NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`        DATETIME     NULL COMMENT '删除时间'
) COMMENT '资讯表';

--
-- 友情链接表
--

DROP TABLE IF EXISTS `sys_link`;

CREATE TABLE `sys_link`
(
    `id`          BIGINT        NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`   BIGINT        NOT NULL DEFAULT 0 COMMENT '租户ID',
    `title`       VARCHAR(150)  NOT NULL DEFAULT '' COMMENT '标题',
    `sub_title`   VARCHAR(150)  NOT NULL DEFAULT '' COMMENT '副标题',
    `link`        VARCHAR(1000) NULL COMMENT '链接',
    `description` VARCHAR(255)  NOT NULL DEFAULT '' COMMENT '备注',
    `summary`     VARCHAR(255)  NOT NULL DEFAULT '' COMMENT '简介',
    `idx`         INT           NOT NULL DEFAULT 999 COMMENT '序号',
    `version`     BIGINT        NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`      SMALLINT      NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`  BIGINT        NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME      NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`  BIGINT        NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`  DATETIME      NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`  BIGINT        NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`  DATETIME      NULL COMMENT '删除时间'
) COMMENT '友情链接表';

--
-- 行政区域表
-- 国内数据来自民政部
-- 中国 - 100000

DROP TABLE IF EXISTS `sys_region`;

CREATE TABLE `sys_region`
(
    `id`                 BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `parent_id`          BIGINT       NOT NULL DEFAULT 0 COMMENT '上级区域ID',
    `type`               VARCHAR(150) NOT NULL DEFAULT '' COMMENT '行政区划类型',
    `code`               VARCHAR(150) NOT NULL DEFAULT '' COMMENT '行政区划代码',
    `title`              VARCHAR(150) NOT NULL DEFAULT '' COMMENT '单位名称',
    `title_first_letter` VARCHAR(5)   NOT NULL DEFAULT '' COMMENT '单位名称拼音首字母大写',
    `version`            BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`             SMALLINT     NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`         BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`         DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`         BIGINT       NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`         DATETIME     NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`         BIGINT       NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`         DATETIME     NULL COMMENT '删除时间'
) COMMENT '行政区域表';

CREATE INDEX `ix_sys_region__parent_id` ON `sys_region` (`parent_id`);
CREATE INDEX `ix_sys_region__code` ON `sys_region` (`code`);


--
-- 地区关联表
--

DROP TABLE IF EXISTS `sys_region_relation`;

CREATE TABLE `sys_region_relation`
(
    `id`         BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`  BIGINT      NOT NULL DEFAULT 0 COMMENT '租户ID',
    `region_id`  BIGINT      NOT NULL DEFAULT 0 COMMENT '地址ID',
    `biz_type`   VARCHAR(50) NOT NULL DEFAULT '' COMMENT '业务类型',
    `biz_id`     BIGINT      NOT NULL DEFAULT 0 COMMENT '业务ID',
    `version`    BIGINT      NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`     SMALLINT    NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by` BIGINT      NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at` DATETIME    NOT NULL DEFAULT NOW() COMMENT '创建时间'
) COMMENT '地址关联表';

CREATE INDEX `ix_sys_region_relation__tenant_id` ON `sys_region_relation` (`tenant_id`);
CREATE INDEX `ix_sys_region_relation__address_id` ON `sys_region_relation` (`region_id`);
CREATE INDEX `ix_sys_region_relation__biz` ON `sys_region_relation` (`biz_type`, `biz_id`);

--
-- 地址表
--

DROP TABLE IF EXISTS `sys_address`;

CREATE TABLE `sys_address`
(
    `id`          BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`   BIGINT       NOT NULL DEFAULT 0 COMMENT '租户ID',
    `biz_type`    VARCHAR(50)  NOT NULL DEFAULT '' COMMENT '业务类型',
    `biz_id`      BIGINT       NOT NULL DEFAULT 0 COMMENT '业务ID',
    `country_id`  BIGINT       NOT NULL DEFAULT 0 COMMENT '国家ID',
    `province_id` BIGINT       NOT NULL DEFAULT 0 COMMENT '身份ID',
    `city_id`     BIGINT       NOT NULL DEFAULT 0 COMMENT '城市ID',
    `county_id`   BIGINT       NOT NULL DEFAULT 0 COMMENT '县区ID',
    `title`       VARCHAR(150) NOT NULL DEFAULT '' COMMENT '工作地点',
    `details`     TEXT         NULL COMMENT '详细地址',
    `extra`       TEXT         NULL COMMENT '附加信息',
    `lng`         VARCHAR(100) NULL COMMENT '经度',
    `lat`         VARCHAR(100) NULL COMMENT '纬度',
    `status`      SMALLINT     NOT NULL DEFAULT 1 COMMENT '状态',
    `version`     BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`      SMALLINT     NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`  BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`  BIGINT       NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`  DATETIME     NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`  BIGINT       NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`  DATETIME     NULL COMMENT '删除时间'
) COMMENT '地址表';

CREATE INDEX `sys_address__tenant_id` ON `sys_address` (`tenant_id`);
CREATE INDEX `sys_address__country_id` ON `sys_address` (`country_id`);
CREATE INDEX `sys_address__province_id` ON `sys_address` (`province_id`);
CREATE INDEX `sys_address__city_id` ON `sys_address` (`city_id`);
CREATE INDEX `sys_address__county_id` ON `sys_address` (`county_id`);

--
-- 地址关联表
--

DROP TABLE IF EXISTS `sys_address_relation`;

CREATE TABLE `sys_address_relation`
(
    `id`         BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`  BIGINT      NOT NULL DEFAULT 0 COMMENT '租户ID',
    `address_id` BIGINT      NOT NULL DEFAULT 0 COMMENT '地址ID',
    `biz_type`   VARCHAR(50) NOT NULL DEFAULT '' COMMENT '业务类型',
    `biz_id`     BIGINT      NOT NULL DEFAULT 0 COMMENT '业务ID',
    `version`    BIGINT      NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`     SMALLINT    NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by` BIGINT      NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at` DATETIME    NOT NULL DEFAULT NOW() COMMENT '创建时间'
) COMMENT '地址关联表';

CREATE INDEX `ix_sys_address_relation__tenant_id` ON `sys_address_relation` (`tenant_id`);
CREATE INDEX `ix_sys_address_relation__address_id` ON `sys_address_relation` (`address_id`);
CREATE INDEX `ix_sys_address_relation__biz` ON `sys_address_relation` (`biz_type`, `biz_id`);

-- =====================================================================================================================
-- Job
-- =====================================================================================================================

--
-- 定时任务表
--

DROP TABLE IF EXISTS `sys_job`;

CREATE TABLE `sys_job`
(
    `id`          BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `code`        VARCHAR(150) NOT NULL DEFAULT '' COMMENT '编号',
    `classname`   VARCHAR(255) NOT NULL DEFAULT '' COMMENT '任务类名',
    `description` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '描述说明',
    `type`        VARCHAR(255) NULL COMMENT '类型',
    `unit`        VARCHAR(255) NULL COMMENT '单位',
    `period`      INT          NULL COMMENT '周期',
    `hour`        INT          NULL COMMENT '小时',
    `minute`      INT          NULL COMMENT '分钟',
    `cron`        VARCHAR(255) COMMENT '表达式',
    `status`      SMALLINT     NOT NULL DEFAULT 1 COMMENT '状态',
    `version`     BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`      SMALLINT     NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`  BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`  BIGINT       NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`  DATETIME     NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`  BIGINT       NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`  DATETIME     NULL COMMENT '删除时间'
) COMMENT '定时任务表';

--
-- 定时任务参数表
--

DROP TABLE IF EXISTS `sys_job_param`;

CREATE TABLE `sys_job_param`
(
    `id`          BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `job_id`      BIGINT       NOT NULL DEFAULT 0 COMMENT '任务ID',
    `param_name`  VARCHAR(150) NOT NULL DEFAULT '' COMMENT '参数名',
    `param_value` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '参数值',
    `version`     BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`      SMALLINT     NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`  BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`  DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`  BIGINT       NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`  DATETIME     NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`  BIGINT       NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`  DATETIME     NULL COMMENT '删除时间'
) COMMENT '定时任务参数表';

CREATE INDEX `ix_sys_job_param__job_id` ON `sys_job_param` (`job_id`);

-- =====================================================================================================================
-- OAuth
-- =====================================================================================================================

--
-- 客户端
--

DROP TABLE IF EXISTS `sys_client`;

CREATE TABLE `sys_client`
(
    `id`                            BIGINT        NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `client_id`                     VARCHAR(150)  NOT NULL DEFAULT '' COMMENT 'Client ID',
    `client_id_issued_at`           DATETIME,
    `client_secret`                 VARCHAR(255)  NOT NULL DEFAULT '' COMMENT 'Client Secret',
    `client_secret_expires_at`      DATETIME,
    `client_name`                   VARCHAR(255)  NOT NULL DEFAULT '' COMMENT 'Client Name',
    `client_authentication_methods` VARCHAR(1000) NOT NULL DEFAULT '' COMMENT 'Client Authentication Methods',
    `authorization_grant_types`     VARCHAR(255)  NOT NULL DEFAULT '' COMMENT 'Authorization Grant Types',
    `redirect_uris`                 VARCHAR(255)  NOT NULL DEFAULT '' COMMENT 'Redirect Uris',
    `post_logout_redirect_uris`     VARCHAR(255)  NOT NULL DEFAULT '' COMMENT 'Redirect Uris',
    `scopes`                        VARCHAR(2000) NOT NULL DEFAULT '' COMMENT 'Score',
    `client_settings`               VARCHAR(2000) NOT NULL DEFAULT '',
    `token_settings`                VARCHAR(2000) NOT NULL DEFAULT '',
    `description`                   VARCHAR(255)  NOT NULL DEFAULT '' COMMENT '备注',
    `status`                        SMALLINT      NOT NULL DEFAULT 0 COMMENT '状态',
    `version`                       BIGINT        NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`                        SMALLINT      NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`                    BIGINT        NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`                    DATETIME      NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`                    BIGINT        NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`                    DATETIME      NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`                    BIGINT        NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`                    DATETIME      NULL COMMENT '删除时间'
) COMMENT '客户端';

CREATE INDEX `ix_sys_client__client_id` ON `sys_client` (`client_id`);

--
-- 认证记录
--

DROP TABLE IF EXISTS `sys_authorization`;

CREATE TABLE `sys_authorization`
(
    `id`                            BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`                     BIGINT       NOT NULL DEFAULT 0 COMMENT '租户ID',
    `uuid`                          VARCHAR(200) NOT NULL DEFAULT '',
    `client_id`                     VARCHAR(100) NOT NULL DEFAULT '',
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
    `version`                       BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`                        SMALLINT     NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`                    BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`                    DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`                    BIGINT       NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`                    DATETIME     NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`                    BIGINT       NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`                    DATETIME     NULL COMMENT '删除时间'
) COMMENT '认证记录';

CREATE INDEX `ix_sys_authorization__client_id` ON `sys_authorization` (`client_id`);

--
-- 认证同意授权记录
--

DROP TABLE IF EXISTS `sys_authorization_consent`;

CREATE TABLE `sys_authorization_consent`
(
    `id`             BIGINT        NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`      BIGINT        NOT NULL DEFAULT 0 COMMENT '租户ID',
    `uuid`           VARCHAR(100)  NOT NULL DEFAULT '',
    `client_id`      VARCHAR(100)  NOT NULL DEFAULT '',
    `principal_name` VARCHAR(200)  NOT NULL DEFAULT '',
    `authorities`    VARCHAR(2000) NOT NULL DEFAULT '',
    `version`        BIGINT        NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`         SMALLINT      NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`     BIGINT        NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`     DATETIME      NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`     BIGINT        NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`     DATETIME      NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`     BIGINT        NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`     DATETIME      NULL COMMENT '删除时间'
) COMMENT '授权记录';

CREATE INDEX `ix_sys_authorization_consent` ON `sys_authorization_consent` (`client_id`, `principal_name`);

-- =====================================================================================================================
-- IM Chat Message
-- =====================================================================================================================

--
-- 聊天室表
--

DROP TABLE IF EXISTS `sys_chat_session`;

CREATE TABLE `sys_chat_session`
(
    `id`                 BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`          BIGINT       NOT NULL DEFAULT 0 COMMENT '租户ID',
    `biz_type`           VARCHAR(50)  NOT NULL DEFAULT '' COMMENT '业务类型',
    `biz_id`             BIGINT       NOT NULL DEFAULT 0 COMMENT '业务ID',
    `entity_type`        VARCHAR(50)  NOT NULL DEFAULT '' COMMENT '实体类型',
    `entity_id`          BIGINT       NOT NULL DEFAULT 0 COMMENT '实体ID',
    `target_entity_type` VARCHAR(50)  NOT NULL DEFAULT '' COMMENT '目标实体类型',
    `target_entity_id`   BIGINT       NOT NULL DEFAULT 0 COMMENT '目标实体ID',
    `session_id`         VARCHAR(100) NOT NULL DEFAULT '' COMMENT '会话标识',
    `status`             SMALLINT     NOT NULL DEFAULT 1 COMMENT '状态',
    `version`            BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`             SMALLINT     NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`         BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`         DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`         BIGINT       NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`         DATETIME     NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`         BIGINT       NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`         DATETIME     NULL COMMENT '删除时间'
) COMMENT '互动会话表';

CREATE INDEX ix_sys_chat_session__tenant_id ON sys_chat_session (tenant_id);
CREATE INDEX ix_sys_chat_session__biz_type ON sys_chat_session (biz_type, biz_id);
CREATE INDEX ix_sys_chat_session__owner ON sys_chat_session (entity_type, entity_id);
CREATE INDEX ix_sys_chat_session__target ON sys_chat_session (target_entity_type, target_entity_id);
CREATE INDEX ix_sys_chat_session__session_id ON sys_chat_session (session_id);

--
-- 互动会话实体表
-- 用于保存互动会话跟实体的状态信息
--

DROP TABLE IF EXISTS `sys_chat_entity_session`;

CREATE TABLE `sys_chat_entity_session`
(
    `id`                   BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`            BIGINT      NOT NULL DEFAULT 0 COMMENT '租户ID',
    `chat_session_id`      BIGINT      NOT NULL DEFAULT 0 COMMENT '互动会话ID',
    `entity_type`          VARCHAR(50) NOT NULL DEFAULT '' COMMENT '实体类型',
    `entity_id`            BIGINT      NOT NULL DEFAULT 0 COMMENT '实体ID',
    `last_read_message_id` BIGINT      NOT NULL DEFAULT 0 COMMENT '最后读取消息ID',
    `last_read_at`         TIMESTAMP   NULL COMMENT '最后读取消息事件',
    `top_ind`              SMALLINT    NOT NULL DEFAULT 1 COMMENT '是否置顶',
    `top_at`               DATETIME    NULL COMMENT '置顶时间',
    `collect_ind`          SMALLINT    NOT NULL DEFAULT 1 COMMENT '是否收藏',
    `collect_at`           DATETIME    NULL COMMENT '收藏时间',
    `clear_ind`            SMALLINT    NOT NULL DEFAULT 1 COMMENT '是否清除',
    `clear_at`             DATETIME    NULL COMMENT '清除时间',
    `status`               SMALLINT    NOT NULL DEFAULT 1 COMMENT '状态',
    `version`              BIGINT      NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`               SMALLINT    NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`           BIGINT      NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`           DATETIME    NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`           BIGINT      NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`           DATETIME    NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`           BIGINT      NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`           DATETIME    NULL COMMENT '删除时间'
) COMMENT '互动会话实体表';

CREATE INDEX ix_sys_chat_entity_session__tenant_id ON sys_chat_entity_session (tenant_id);
CREATE INDEX ix_sys_chat_entity_session__chat_session_id ON sys_chat_entity_session (chat_session_id);
CREATE INDEX ix_sys_chat_entity_session__entity ON sys_chat_entity_session (entity_type, entity_id);
CREATE INDEX ix_sys_chat_entity_session__last_read_message_id ON sys_chat_entity_session (last_read_message_id);

--
-- 互动消息表
--

DROP TABLE IF EXISTS `sys_chat_message`;

CREATE TABLE `sys_chat_message`
(
    `id`                   BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`            BIGINT      NOT NULL DEFAULT 0 COMMENT '租户ID',
    `chat_session_id`      BIGINT      NOT NULL DEFAULT 0 COMMENT '互动会话ID',
    `sender_entity_type`   VARCHAR(50) NOT NULL DEFAULT '' COMMENT '发送人实体类型',
    `sender_entity_id`     BIGINT      NOT NULL DEFAULT 0 COMMENT '发送人ID',
    `receiver_entity_type` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '接收人实体类型',
    `receiver_entity_id`   BIGINT      NOT NULL DEFAULT 0 COMMENT '接收人ID',
    `message_content_type` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '消息内容类型',
    `sequence`             SMALLINT    NOT NULL DEFAULT 1 COMMENT '消息序号',
    `status`               SMALLINT    NOT NULL DEFAULT 1 COMMENT '状态',
    `version`              BIGINT      NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`               SMALLINT    NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`           BIGINT      NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`           DATETIME    NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`           BIGINT      NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`           DATETIME    NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`           BIGINT      NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`           DATETIME    NULL COMMENT '删除时间'
) COMMENT '互动消息表';

CREATE INDEX ix_sys_chat_message__tenant_id ON sys_chat_message (tenant_id);
CREATE INDEX ix_sys_chat_message__chat_session_id ON sys_chat_message (chat_session_id);
CREATE INDEX ix_sys_chat_message__sender ON sys_chat_message (sender_entity_type, sender_entity_id);
CREATE INDEX ix_sys_chat_message__recipient ON sys_chat_message (receiver_entity_type, receiver_entity_id);

--
-- 沟通内容表
--

DROP TABLE IF EXISTS sys_chat_message_content;

CREATE TABLE sys_chat_message_content
(
    `id`              BIGINT   NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`       BIGINT   NOT NULL DEFAULT 0 COMMENT '租户ID',
    `chat_session_id` BIGINT   NOT NULL DEFAULT 0 COMMENT '互动会话ID',
    `chat_message_id` BIGINT   NOT NULL DEFAULT 0 COMMENT '互动消息ID',
    `content`         TEXT     NULL COMMENT '内容',
    `extra`           TEXT     NULL COMMENT '附加信息',
    `status`          SMALLINT NOT NULL DEFAULT 1 COMMENT '状态',
    `version`         BIGINT   NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`          SMALLINT NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`      BIGINT   NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`      DATETIME NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`      BIGINT   NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`      DATETIME NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`      BIGINT   NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`      DATETIME NULL COMMENT '删除时间'
) COMMENT '沟通内容表';

CREATE INDEX ix_sys_chat_message_content__tenant_id ON sys_chat_message_content (tenant_id);
CREATE INDEX ix_sys_chat_message_content__chat_session_id ON sys_chat_message_content (chat_session_id);
CREATE INDEX ix_sys_chat_message_content__chat_message_id ON sys_chat_message_content (chat_message_id);


--
-- 互动消息实体表
-- 用于保存互动消息跟实体的状态信息
--

DROP TABLE IF EXISTS sys_chat_entity_message;

CREATE TABLE sys_chat_entity_message
(
    `id`              BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`       BIGINT      NOT NULL DEFAULT 0 COMMENT '租户ID',
    `chat_session_id` BIGINT      NOT NULL DEFAULT 0 COMMENT '互动会话ID',
    `chat_message_id` BIGINT      NOT NULL DEFAULT 0 COMMENT '互动消息ID',
    `entity_type`     VARCHAR(50) NOT NULL DEFAULT '' COMMENT '实体类型',
    `entity_id`       BIGINT      NOT NULL DEFAULT 0 COMMENT '实体ID',
    `read_ind`        SMALLINT    NOT NULL DEFAULT 1 COMMENT '是否已读',
    `delete_ind`      SMALLINT    NOT NULL DEFAULT 1 COMMENT '是否已删',
    `status`          SMALLINT    NOT NULL DEFAULT 1 COMMENT '状态',
    `version`         BIGINT      NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`          SMALLINT    NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`      BIGINT      NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`      DATETIME    NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`      BIGINT      NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`      DATETIME    NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`      BIGINT      NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`      DATETIME    NULL COMMENT '删除时间'
) COMMENT '互动消息实体表';

CREATE INDEX ix_sys_chat_entity_message__tenant_id ON sys_chat_entity_message (tenant_id);
CREATE INDEX ix_sys_chat_entity_message__chat_session_id ON sys_chat_entity_message (chat_session_id);
CREATE INDEX ix_sys_chat_entity_message__chat_message_id ON sys_chat_entity_message (chat_message_id);
CREATE INDEX ix_sys_chat_entity_message__entity ON sys_chat_entity_message (entity_type, entity_id);

-- =====================================================================================================================
-- AI
-- =====================================================================================================================

--
-- 智能体
--

DROP TABLE IF EXISTS sys_ai_agent;

CREATE TABLE sys_ai_agent
(
    `id`         BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`  BIGINT       NOT NULL DEFAULT 0 COMMENT '租户ID',
    `code`       VARCHAR(150) NOT NULL DEFAULT '' COMMENT '业务类型',
    `title`      VARCHAR(150) NOT NULL DEFAULT '' COMMENT '业务类型',
    `prompt`     TEXT         NULL COMMENT '提示词',
    `version`    BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`     SMALLINT     NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by` BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at` DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by` BIGINT       NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at` DATETIME     NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by` BIGINT       NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at` DATETIME     NULL COMMENT '删除时间'
);

CREATE INDEX `ix_sys_ai_agent__tenant_id` ON `sys_ai_agent` (`tenant_id`);
CREATE INDEX `ix_sys_ai_agent__biz_type` ON `sys_ai_agent` (`code`);

--
-- 对话记录
--

DROP TABLE IF EXISTS sys_ai_chat_memory;

CREATE TABLE sys_ai_chat_memory
(
    `id`              BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `tenant_id`       BIGINT       NOT NULL DEFAULT 0 COMMENT '租户ID',
    `entity_type`     VARCHAR(100) NOT NULL DEFAULT '' COMMENT '业务类型',
    `entity_id`       BIGINT       NOT NULL DEFAULT 0 COMMENT '业务ID',
    `conversation_id` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '会话标识',
    `content`         TEXT         NULL COMMENT '对话内容',
    `type`            VARCHAR(100) NOT NULL default '' COMMENT '对话类型',
    `version`         BIGINT       NOT NULL DEFAULT 0 COMMENT '版本号',
    `active`          SMALLINT     NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`      BIGINT       NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`      DATETIME     NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_by`      BIGINT       NOT NULL DEFAULT 0 COMMENT '修改人',
    `updated_at`      DATETIME     NOT NULL DEFAULT NOW() COMMENT '修改时间',
    `deleted_by`      BIGINT       NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`      DATETIME     NULL COMMENT '删除时间'
);

CREATE INDEX ix_sys_ai_chat_memory__tenant_id ON sys_ai_chat_memory (tenant_id);
CREATE INDEX ix_sys_ai_chat_memory__conversation_id ON sys_ai_chat_memory (conversation_id);
CREATE INDEX ix_sys_ai_chat_memory__entity ON sys_ai_chat_memory (entity_type, entity_id);
