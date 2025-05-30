-- =====================================================================================================================
-- LXP
-- =====================================================================================================================

--
-- 资源类型表
--

DROP TABLE IF EXISTS `lxp_resource_type`;

CREATE TABLE `lxp_resource_type`
(
    `id`               BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `code`             VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '类型编号，唯一',
    `title`            VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '名称',
    `description`      VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '备注，仅用于后台管理显示',
    `status`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '发布状态',
    `active`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`       DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '创建时间',
    `last_modified_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at` DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '最后修改时间',
    `deleted_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`       DATETIME(3)      NULL COMMENT '删除时间',
    CONSTRAINT `pk_lxp_resource_type` PRIMARY KEY (`id`),
    INDEX `ix_lxp_resource_type__code` (`code`)
) COMMENT '资源类型表';

--
-- 资源表
--

DROP TABLE IF EXISTS `lxp_resource`;

CREATE TABLE `lxp_resource`
(
    `id`                       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `type_id`                  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '类型ID',
    `code`                     VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '编码',
    `title`                    VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '标题',
    `details`                  VARCHAR(5000)    NOT NULL DEFAULT '' COMMENT '详情',
    `description`              VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '备注',
    `start_datetime`           DATETIME         NOT NULL DEFAULT '2000-01-01 00:00:00' COMMENT '发布开始时间',
    `end_datetime`             DATETIME         NOT NULL DEFAULT '9999-12-31 23:59:59' COMMENT '发布结束时间',
    `content_start_datetime`   DATETIME         NOT NULL DEFAULT '2000-01-01 00:00:00' COMMENT '学习开始时间',
    `content_end_datetime`     DATETIME         NOT NULL DEFAULT '9999-12-31 23:59:59' COMMENT '学习结束时间',
    `attempt_limit`            INT UNSIGNED     NOT NULL DEFAULT 0 COMMENT '最多允许尝试次数',
    `attempt_time_limit`       INT UNSIGNED     NOT NULL DEFAULT 0 COMMENT '最多允许尝试时长',
    `attempt_policy`           VARCHAR(50)      NOT NULL DEFAULT '' COMMENT '尝试次数计算策略',
    `complete_rule`            VARCHAR(100)     NOT NULL DEFAULT '' COMMENT '完成规则',
    `complete_score`           INT UNSIGNED     NOT NULL DEFAULT 0 COMMENT '及格分数',
    `complete_strict_mode_ind` TINYINT          NOT NULL DEFAULT 1 COMMENT '是否启用严格模式',
    `complete_total_time`      INT UNSIGNED     NOT NULL DEFAULT 0 COMMENT '需学习时长',
    `status`                   TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '发布状态',
    `active`                   TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`               BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`               DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '创建时间',
    `last_modified_by`         BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at`         DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '最后修改时间',
    `deleted_by`               BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`               DATETIME(3)      NULL COMMENT '删除时间',
    CONSTRAINT `pk_lxp_resource` PRIMARY KEY (`id`),
    INDEX `ix_lxp_resource__code` (`code`)
) COMMENT '资源表';

--
-- 项目类型表
--

DROP TABLE IF EXISTS `lxp_project_type`;

CREATE TABLE `lxp_project_type`
(
    `id`               BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `code`             VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '类型编号，唯一',
    `title`            VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '名称',
    `description`      VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '备注',
    `status`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '发布状态',
    `active`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`       DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '创建时间',
    `last_modified_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at` DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '最后修改时间',
    `deleted_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`       DATETIME(3)      NULL COMMENT '删除时间',
    CONSTRAINT `pk_lxp_project_type` PRIMARY KEY (`id`),
    INDEX `ix_lxp_project_type__code` (`code`)
) COMMENT '项目类型表';

--
-- 项目表
--

DROP TABLE IF EXISTS `lxp_project`;

CREATE TABLE `lxp_project`
(
    `id`                     BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `code`                   VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '编码',
    `title`                  VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '标题',
    `details`                VARCHAR(5000)    NOT NULL DEFAULT '' COMMENT '详情',
    `description`            VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '备注',
    `start_datetime`         DATETIME         NOT NULL DEFAULT '2000-01-01 00:00:00' COMMENT '发布期限开始时间',
    `end_datetime`           DATETIME         NOT NULL DEFAULT '9999-12-31 23:59:59' COMMENT '发布期限结束时间',
    `enroll_start_datetime`  DATETIME         NOT NULL DEFAULT '2000-01-01 00:00:00' COMMENT '报名期限开始时间',
    `enroll_end_datetime`    DATETIME         NOT NULL DEFAULT '9999-12-31 23:59:59' COMMENT '报名期限结束时间',
    `content_start_datetime` DATETIME         NOT NULL DEFAULT '2000-01-01 00:00:00' COMMENT '学习期限开始时间',
    `content_end_datetime`   DATETIME         NOT NULL DEFAULT '9999-12-31 23:59:59' COMMENT '学习期限结束时间',
    `pay_ind`                TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否收费',
    `original_price`         NUMERIC(8, 2)    NOT NULL DEFAULT 0 COMMENT '价格',
    `discount_price`         NUMERIC(8, 2)    NOT NULL DEFAULT 0 COMMENT '促销价格',
    `vip_price`              NUMERIC(8, 2)    NOT NULL DEFAULT 0 COMMENT '会员价格',
    `status`                 TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '发布状态',
    `active`                 TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`             BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`             DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '创建时间',
    `last_modified_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at`       DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '最后修改时间',
    `deleted_by`             BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`             DATETIME(3)      NULL COMMENT '删除时间',
    CONSTRAINT `pk_lxp_project` PRIMARY KEY (`id`),
    INDEX `ix_lxp_project__code` (`code`)
) COMMENT '活动表';

--
-- 项目章节表
--

DROP TABLE IF EXISTS `lxp_project_section`;

CREATE TABLE `lxp_project_section`
(
    `id`               BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `project_id`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '项目ID',
    `code`             VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '编号',
    `title`            VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '名称',
    `details`          VARCHAR(5000)    NOT NULL DEFAULT '' COMMENT '详情',
    `description`      VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '备注',
    `status`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '发布状态',
    `active`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`       DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '创建时间',
    `last_modified_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at` DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '最后修改时间',
    `deleted_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`       DATETIME(3)      NULL COMMENT '删除时间',
    CONSTRAINT `pk_lxp_project_section` PRIMARY KEY (`id`)
) COMMENT '项目章节表';

--
-- 项目章节关联表
--

DROP TABLE IF EXISTS `lxp_project_section_item`;

CREATE TABLE `lxp_project_section_item`
(
    `id`                 BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT 'ID',
    `project_id`         BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '项目ID',
    `project_section_id` BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '项目章节ID',
    `resource_id`        BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '资源ID',
    CONSTRAINT `pk_lxp_project_section_item` PRIMARY KEY (`id`)
) COMMENT '项目章节关联表';

--
-- 项目报名成绩表
--

DROP TABLE IF EXISTS `lxp_project_attendance`;

CREATE TABLE `lxp_project_attendance`
(
    `id`                    BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `project_id`            BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '项目ID',
    `user_id`               BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '用户ID',
    `enrollment_status`     VARCHAR(50)      NOT NULL DEFAULT '' COMMENT '报名状态',
    `enrollment_datetime`   DATETIME         NOT NULL DEFAULT NOW() COMMENT '报名时间',
    `enrollment_approve_by` BIGINT           NOT NULL DEFAULT 0 COMMENT '审批人ID',
    `enrollment_approve_at` DATETIME         NULL COMMENT '审批日期',
    `attendance_status`     VARCHAR(50)      NOT NULL DEFAULT '' COMMENT '完成状态',
    `commence_datetime`     DATETIME         NULL COMMENT '首次访问时间',
    `expire_datetime`       DATETIME         NULL COMMENT '失效时间',
    `last_access_datetime`  DATETIME         NULL COMMENT '最近访问时间',
    `complete_datetime`     DATETIME         NULL COMMENT '完成时间',
    `total_attempt`         INT UNSIGNED     NOT NULL DEFAULT 0 COMMENT '尝试总次数',
    `total_time`            INT UNSIGNED     NOT NULL DEFAULT 0 COMMENT '学习总时长',
    `total_valid_time`      INT UNSIGNED     NOT NULL DEFAULT 0 COMMENT '学习总有效时长',
    `score`                 NUMERIC(10, 2)   NOT NULL DEFAULT -1 COMMENT '分数',
    `final_score`           NUMERIC(10, 2)   NOT NULL DEFAULT -1 COMMENT '最终分数',
    `final_ind`             TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否是最终结果',
    `latest_ind`            TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否最新的报名',
    `source`                VARCHAR(50)      NOT NULL DEFAULT '' COMMENT '报名来源',
    `active`                TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`            BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`            DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '创建时间',
    `last_modified_by`      BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at`      DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '最后修改时间',
    `deleted_by`            BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`            DATETIME(3)      NULL COMMENT '删除时间',
    CONSTRAINT `pk_lxp_project_attendance` PRIMARY KEY (`id`),
    INDEX `ix_lxp_project_attendance__project_id` (`project_id`),
    INDEX `ix_lxp_project_attendance__user_id` (`user_id`)
) COMMENT '项目报名成绩表';

--
-- 项目章节成绩表
--

DROP TABLE IF EXISTS `lxp_project_section_attendance`;

CREATE TABLE `lxp_project_section_attendance`
(
    `id`                   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `course_id`            BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '课程ID',
    `user_id`              BIGINT UNSIGNED  NOT NULL DEFAULT 0 DEFAULT 0 COMMENT '用户ID',
    `status`               VARCHAR(50)      NOT NULL DEFAULT '' COMMENT '完成状态',
    `commence_datetime`    DATETIME         NULL COMMENT '首次访问时间',
    `last_access_datetime` DATETIME         NULL COMMENT '最近访问时间',
    `complete_datetime`    DATETIME         NULL COMMENT '完成时间',
    `total_attempt`        INT UNSIGNED     NOT NULL DEFAULT 0 COMMENT '尝试总次数',
    `total_time`           INT UNSIGNED     NOT NULL DEFAULT 0 COMMENT '学习总时长',
    `total_valid_time`     INT UNSIGNED     NOT NULL DEFAULT 0 COMMENT '学习总有效时长',
    `score`                NUMERIC(10, 2)   NOT NULL DEFAULT 0 COMMENT '分数',
    `first_score`          NUMERIC(10, 2)   NOT NULL DEFAULT 0 COMMENT '第一次分数',
    `last_score`           NUMERIC(10, 2)   NOT NULL DEFAULT 0 COMMENT '最后一次分数',
    `max_score`            NUMERIC(10, 2)   NOT NULL DEFAULT 0 COMMENT '最高分数',
    `min_score`            NUMERIC(10, 2)   NOT NULL DEFAULT 0 COMMENT '最低分数',
    `avg_score`            NUMERIC(10, 2)   NOT NULL DEFAULT 0 COMMENT '平均分数',
    `final_score`          NUMERIC(10, 2)   NOT NULL DEFAULT 0 COMMENT '最终分数',
    `source`               VARCHAR(50)      NOT NULL DEFAULT '' COMMENT '报名来源',
    `active`               TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`           BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`           DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '创建时间',
    `last_modified_by`     BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at`     DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '最后修改时间',
    `deleted_by`           BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`           DATETIME(3)      NULL COMMENT '删除时间',
    CONSTRAINT `pk_lxp_project_section_attendance` PRIMARY KEY (`id`)
) COMMENT '项目章节成绩表';

--
-- 项目资源成绩表
--

DROP TABLE IF EXISTS `lxp_project_resource_attendance`;

CREATE TABLE `lxp_project_resource_attendance`
(
    `id`                   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `course_id`            BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '课程ID',
    `user_id`              BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '用户ID',
    `status`               VARCHAR(50)      NOT NULL DEFAULT '' COMMENT '完成状态',
    `commence_datetime`    DATETIME         NULL COMMENT '首次访问时间',
    `last_access_datetime` DATETIME         NULL COMMENT '最近访问时间',
    `complete_datetime`    DATETIME         NULL COMMENT '完成时间',
    `total_attempt`        INT UNSIGNED     NOT NULL DEFAULT 0 COMMENT '尝试总次数',
    `total_time`           INT UNSIGNED     NOT NULL DEFAULT 0 COMMENT '学习总时长',
    `total_valid_time`     INT UNSIGNED     NOT NULL DEFAULT 0 COMMENT '学习总有效时长',
    `score`                NUMERIC(10, 2)   NOT NULL DEFAULT 0 COMMENT '分数',
    `first_score`          NUMERIC(10, 2)   NOT NULL DEFAULT 0 COMMENT '第一次分数',
    `last_score`           NUMERIC(10, 2)   NOT NULL DEFAULT 0 COMMENT '最后一次分数',
    `max_score`            NUMERIC(10, 2)   NOT NULL DEFAULT 0 COMMENT '最高分数',
    `min_score`            NUMERIC(10, 2)   NOT NULL DEFAULT 0 COMMENT '最低分数',
    `avg_score`            NUMERIC(10, 2)   NOT NULL DEFAULT 0 COMMENT '平均分数',
    `final_score`          NUMERIC(10, 2)   NOT NULL DEFAULT 0 COMMENT '最终分数',
    `source`               VARCHAR(50)      NOT NULL DEFAULT '' COMMENT '报名来源',
    `active`               TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`           BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`           DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '创建时间',
    `last_modified_by`     BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at`     DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '最后修改时间',
    `deleted_by`           BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`           DATETIME(3)      NULL COMMENT '删除时间',
    CONSTRAINT `pk_lxp_project_resource_attendance` PRIMARY KEY (`id`)
) COMMENT '活动资源成绩表';

-- ---------------------------------------------------------------------------------------------------------------------
-- 题库
-- ---------------------------------------------------------------------------------------------------------------------

--
-- 题目表
--

DROP TABLE IF EXISTS `lxp_question`;

CREATE TABLE `lxp_question`
(
    `id`               BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `reference_id`     BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '原始题目ID',
    `type`             VARCHAR(50)      NOT NULL DEFAULT '' COMMENT '类型',
    `title`            VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '标题',
    `content`          VARCHAR(5000)    NOT NULL DEFAULT '' COMMENT '题干',
    `explanation`      VARCHAR(1000)    NOT NULL DEFAULT '' COMMENT '参考答案',
    `answer`           VARCHAR(1000)    NOT NULL DEFAULT '' COMMENT '解析',
    `description`      VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '备注说明',
    `difficulty`       INT UNSIGNED     NOT NULL DEFAULT 0 COMMENT '难度系数',
    `score`            NUMERIC(10, 2)   NOT NULL DEFAULT 0 COMMENT '题目分值',
    `native_ind`       TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否是试题库的题目',
    `status`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态',
    `source`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '数据来源',
    `active`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`       DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '创建时间',
    `last_modified_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at` DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '最后修改时间',
    `deleted_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`       DATETIME(3)      NULL COMMENT '删除时间',
    CONSTRAINT `pk_sys_question_id` PRIMARY KEY (`id`)
) COMMENT '试题表';

--
-- 题目选项表
--

DROP TABLE IF EXISTS `lxp_question_option`;

CREATE TABLE `lxp_question_option`
(
    `id`               BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `question_id`      BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '题目ID',
    `reference_id`     BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '原始选项ID，只针对复制题目有效',
    `content`          VARCHAR(5000)    NOT NULL DEFAULT '' COMMENT '题干',
    `explanation`      VARCHAR(1000)    NOT NULL DEFAULT '' COMMENT '选项解释',
    `idx`              INT(1) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '序号',
    `score`            NUMERIC(10, 2)   NOT NULL DEFAULT 0 COMMENT '选项分值',
    `correct_ind`      TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否正确答案',
    `case_ind`         TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否不区分大小写',
    `active`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`       DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '创建时间',
    `last_modified_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at` DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '最后修改时间',
    `deleted_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`       DATETIME(3)      NULL COMMENT '删除时间',
    CONSTRAINT `pk_lxp_question_option` PRIMARY KEY (`id`),
    INDEX `ix_lxp_question_option` (`question_id`)
) COMMENT '题目选项表';

--
-- 试卷章节表
--

DROP TABLE IF EXISTS `lxp_paper_section`;

CREATE TABLE `lxp_paper_section`
(
    `id`          BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT 'ID',
    `title`       VARCHAR(255)    NOT NULL DEFAULT '' COMMENT '标题',
    `content`     VARCHAR(5000)   NOT NULL DEFAULT '' COMMENT '内容',
    `explanation` VARCHAR(1000)   NOT NULL DEFAULT '' COMMENT '解析',
    `description` VARCHAR(255)    NOT NULL DEFAULT '' COMMENT '备注说明',
    CONSTRAINT `pk_lxp_paper_section` PRIMARY KEY (`id`)
) COMMENT '试卷章节表';

--
-- 试卷试题表
--

DROP TABLE IF EXISTS `lxp_paper_question`;

CREATE TABLE `lxp_paper_question`
(
    `id`               BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT 'ID',
    `paper_section_id` BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '试卷章节ID',
    `question_id`      BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '试卷题目ID',
    `created_at`       DATETIME        NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `created_by`       BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建人',
    CONSTRAINT `pk_lxp_paper_question` PRIMARY KEY (`id`),
    INDEX `ix_lxp_paper_question` (`paper_section_id`, `question_id`)
) COMMENT '试卷试题表';

--
-- 试卷答题记录
--

DROP TABLE IF EXISTS `lxp_paper_attendance`;

CREATE TABLE `lxp_paper_attendance`
(
    `id`                  BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `paper_section_id`    BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '试卷章节ID',
    `user_id`             BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '试卷章节ID',
    `learning_session_id` VARCHAR(150)     NOT NULL DEFAULT '' COMMENT '学习会话ID',
    `score`               NUMERIC(10, 2)   NOT NULL DEFAULT 0 COMMENT '分数',
    `active`              TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`          BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`          DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '创建时间',
    `last_modified_by`    BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at`    DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '最后修改时间',
    `deleted_by`          BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`          DATETIME(3)      NULL COMMENT '删除时间',
    CONSTRAINT `pk_lxp_paper_attendance` PRIMARY KEY (`id`),
    INDEX `ix_lxp_paper_attendance` (`paper_section_id`, `user_id`)
) COMMENT '试卷答题记录表';

--
-- 题目答题记录
--

DROP TABLE IF EXISTS `lxp_paper_question_attendance`;

CREATE TABLE `lxp_paper_question_attendance`
(
    `id`               BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `paper_section_id` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '试卷章节ID',
    `user_id`          BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '试卷章节ID',
    `answer`           VARCHAR(5000)    NOT NULL DEFAULT '' COMMENT '答案',
    `score`            NUMERIC(10, 2)   NOT NULL DEFAULT 0 COMMENT '分数',
    `active`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`       DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '创建时间',
    `last_modified_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at` DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '最后修改时间',
    `deleted_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`       DATETIME(3)      NULL COMMENT '删除时间',
    CONSTRAINT `pk_lxp_paper_question_attendance` PRIMARY KEY (`id`)
) COMMENT '题目答题记录';

--
-- 选项答题记录
--

DROP TABLE IF EXISTS `lxp_paper_question_option_attendance`;

CREATE TABLE `lxp_paper_question_option_attendance`
(
    `id`               BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'ID',
    `paper_section_id` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '试卷章节ID',
    `option_id`        BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '试卷章节ID',
    `user_id`          BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '试卷章节ID',
    `active`           TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '创建人',
    `created_at`       DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '创建时间',
    `last_modified_by` BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后修改人',
    `last_modified_at` DATETIME(3)      NOT NULL DEFAULT NOW(3) COMMENT '最后修改时间',
    `deleted_by`       BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除人',
    `deleted_at`       DATETIME(3)      NULL COMMENT '删除时间',
    CONSTRAINT `pk_lxp_paper_question_option_attendance` PRIMARY KEY (`id`)
) COMMENT '选项答题记录';
