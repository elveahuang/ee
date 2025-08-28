-- =====================================================================================================================
-- 实体数据
-- 1000000 - 租户 Tenant
-- 1001000 - 账号 Account
-- 1002000 - 用户 User
-- 1003000 - 账号 Member
-- 1004000 - 角色 Role
-- 1005000 - 部门 Organization
-- 1006000 - 岗位 Position
-- 1007000 - 群组 Group
-- =====================================================================================================================

--
-- 内置租户
--

truncate sys_tenant;

insert into sys_tenant (`id`, `code`, `title`, `account_count`, `description`)
values (1000001, 'ROOT', 'Root', 0, '顶层租户'),
       (1000002, 'DEMO', 'Demo', 0, '演示租户'),
       (1000003, 'TEST', 'Test', 0, '测试租户');

--
-- 内置账号
--

truncate sys_identity;

insert into sys_identity (`id`, `uuid`, `source`, `active`, `created_at`, `updated_at`)
values (1001001, '1001001', 1, 1, now(), now());

--
-- 内置用户
--

truncate sys_user;

insert into sys_user (`id`, `tenant_id`, `entity_type`, `entity_id`, `username`, `email`, `mobile_country_code`, `mobile_number`,
                      `display_name`, `birthday`, `active`, `created_at`, `password`)
values (1002001, 1000001, 1, 1001001, 'admin', 'ee@elvea.cn', '0086', '13500000000', 'Administrator', now(), 1, now(),
        '$2a$10$MLkjYEPJkO6KNrfUUBld6eWVr1G09nugg5UpIQVUtsQ.3Z9U2lOSK');

--
-- 角色
--

truncate sys_role;

insert into sys_role (`id`, `tenant_id`, `code`, `title`, `label`, `source`, `active`, `created_at`, `created_by`)
values (1004001, 1000001, 'SYSTEM_ADMINISTRATOR', 'System Administrator', 'label_role_system_administrator', 1, 1, now(), 1),
       (1004002, 1000001, 'ADMINISTRATOR', 'Administrator', 'label_role_administrator', 1, 1, now(), 1),
       (1004003, 1000001, 'USER', 'User', 'label_role_user', 1, 1, now(), 1);

--
-- 顶层组织架构
--

truncate sys_organization;

insert into sys_organization (`id`, `tenant_id`, `parent_id`, `code`, `label`, `title`, `root_ind`, `default_ind`)
values (1005001, 1000001, 0, 'ROOT_ORG', 'label_top_organization', 'All Organization', 1, 1);

--
-- 顶层岗位
--

truncate sys_position;

insert into sys_position (`id`, `tenant_id`, `parent_id`, `code`, `label`, `title`, `root_ind`, `default_ind`)
values (1006001, 1000001, 0, 'ROOT_PST', 'label_top_position', 'All Position', 1, 1);

--
-- 内置用户关联数据
--

truncate sys_user_role;

insert into sys_user_role (`id`, `tenant_id`, `user_id`, `role_id`, `created_at`)
values (1002001001, 1000001, 1002001, 1004001, now()),
       (1002001002, 1000001, 1002001, 1004002, now()),
       (1002001003, 1000001, 1002001, 1004003, now());

truncate sys_entity_relation;

insert into sys_entity_relation (`id`, `tenant_id`, `ancestor_id`, `entity_id`, `parent_ind`, `relation_type`, `relation_path`, `relation_index`)
values (1002001001, 1000001, 1005001, 1005001, 1, 'USR_CURRENT_ORG', '1', 1),
       (1002001002, 1000001, 1006001, 1006001, 1, 'USR_CURRENT_PST', '1', 1);

-- =====================================================================================================================
-- 权限数据
-- =====================================================================================================================

--
-- 权限
-- 1000000 - SECTION    - 版块（标准）
-- 1001000 - GROUP      - 分组（标准）
-- 2000000 - MODULE     - 模块（标准） - 基础模块
-- 2001000 - MODULE     - 模块（标准） - 平台管理
-- 2002000 - MODULE     - 模块（标准） - 开放平台
-- 2003000 - MODULE     - 模块（标准） - 数据统计
-- 2004000 - MODULE     - 模块（标准） - 开发管理
-- 2005000 - MODULE     - 模块（标准） - 商城管理
-- 9000000 - SECTION    - 版块（标准）
-- 9001000 - GROUP      - 分组（标准）
-- 9002000 - MODULE     - 模块（标准）
--

truncate sys_authority;

insert into sys_authority (`id`, `parent_id`, `code`, `title`, `authority_type`, `authority_group_type`, `sort_order`, `active`)
values
    /* ================ 版块 ================ */
    (1000001, 0, 'module:home', '首页', 1, 2, 555, 1),
    (1000002, 0, 'module:system', '系统管理', 1, 2, 777, 1),
    (1000003, 0, 'module:platform', '平台管理', 1, 1, 888, 1),
    (1000004, 0, 'module:open', '开放平台', 1, 2, 666, 1),
    (1000005, 0, 'module:development', '开发管理', 1, 1, 999, 1),
    /* ================ 分组 ================ */
    (1001001, 1000002, 'group:system:workbench', '工作台', 2, 2, 33, 1),
    (1001002, 1000002, 'group:system:site', '站点管理', 2, 2, 33, 1),
    (1001003, 1000002, 'group:system:organization', '组织架构', 4, 2, 88, 1),
    (1001004, 1000002, 'group:system:setting', '系统设置', 5, 2, 99, 1),
    (1001005, 1000002, 'group:system:message', '消息管理', 5, 2, 99, 1),
    (1001006, 1000002, 'group:system:monitor', '系统监控', 5, 2, 99, 1),
    (1001007, 1000002, 'group:system:open', '开放平台', 5, 2, 99, 0),
    (1001008, 1000002, 'group:platform:workbench', '工作台', 2, 2, 33, 1),
    (1001009, 1000002, 'group:platform:setting', '平台设置', 2, 2, 33, 1),
    (1001010, 1000002, 'group:platform:tenant', '租户管理', 2, 2, 33, 1),
    (1001011, 1000002, 'group:platform:member', '会员管理', 2, 2, 33, 1),
    (1001012, 1000002, 'group:platform:monitor', '平台监控', 2, 2, 33, 1),
    (1001013, 1000001, 'group:data:analysis', '数据分析', 2, 2, 11, 0),
    (1001014, 1000001, 'group:data:report', '数据报表', 2, 2, 11, 0),
    (1001015, 1000001, 'group:open:wechat:mp', '微信公众号管理', 3, 2, 33, 1),
    (1001016, 1000001, 'group:open:wechat:ma', '微信小程序管理', 3, 2, 33, 1),
    (1001017, 1000001, 'group:open:alipay:ma', '支付宝小程序管理', 3, 2, 33, 0),
    (1001018, 1000001, 'group:open:douyin:ma', '抖音小程序管理', 3, 2, 33, 0),
    (1001019, 1000001, 'group:open:kuaishou:ma', '快手小程序管理', 3, 2, 33, 0),
    (1001020, 1000001, 'group:open:xiaohongshu:ma', '小红书小程序管理', 3, 2, 33, 0),
    (1001021, 1000001, 'group:development:tools', '开发工具', 3, 2, 33, 0),
    (1001022, 1000001, 'group:development:gateway', '网关管理', 3, 2, 33, 0),
    (1001023, 1000001, 'group:development:monitor', '平台监控', 3, 2, 33, 0),
    /* ================ 模块 ================ */
    /* -------- 基础模块 -------- */
    (2000001, 1001001, 'workbench', '工作台', 1, 2, 11, 1),
    (2000002, 1001001, 'dashboard', '仪表盘', 1, 2, 11, 1),
    (2000003, 1001002, 'announcement', '公告管理', 2, 2, 11, 1),
    (2000004, 1001002, 'banner', '公告管理', 2, 2, 22, 1),
    (2000005, 1001003, 'organization', '组织架构', 2, 2, 1, 1),
    (2000006, 1001003, 'user', '用户管理', 2, 2, 3, 1),
    (2000007, 1001003, 'group', '群组管理', 2, 2, 4, 1),
    (2000008, 1001003, 'position', '岗位管理', 2, 2, 2, 1),
    (2000009, 1001003, 'grade', '职级管理', 2, 2, 4, 1),
    (2000010, 1001004, 'settings', '系统设置', 2, 2, 2, 1),
    (2000011, 1001004, 'config', '参数管理', 2, 2, 2, 1),
    (2000012, 1001004, 'role', '角色管理', 2, 2, 2, 1),
    (2000013, 1001004, 'authority', '权限管理', 2, 2, 1, 1),
    (2000014, 1001004, 'agreement', '协议管理', 2, 2, 1, 1),
    (2000015, 1001004, 'dict', '字典管理', 2, 2, 1, 1),
    (2000016, 1001004, 'tag', '标签管理', 2, 2, 2, 1),
    (2000017, 1001004, 'catalog', '分类管理', 2, 2, 2, 1),
    (2000018, 1001004, 'area', '地区管理', 2, 2, 3, 1),
    (2000019, 1001004, 'keyword', '关键字管理', 2, 2, 3, 1),
    (2000020, 1001006, 'session', '在线用户', 2, 2, 1, 1),
    (2000021, 1001006, 'log:operation', '操作日志', 2, 2, 5, 1),
    (2000022, 1001006, 'log:login', '登录日志', 2, 2, 5, 1),
    (2000023, 1001005, 'message:setting', '基本设置', 2, 2, 5, 1),
    (2000024, 1001005, 'message:template', '消息模版', 2, 2, 5, 1),
    (2000025, 1001005, 'message:log', '消息日志', 2, 2, 5, 1),
    /* -------- 平台设置 -------- */
    (2001001, 1001009, 'platform:settings', '基本设置', 2, 1, 3, 1),
    (2001002, 1001009, 'platform:config', '参数管理', 2, 1, 3, 1),
    (2001003, 1001009, 'platform:role', '角色管理', 2, 1, 2, 1),
    (2001004, 1001009, 'platform:authority', '权限管理', 2, 1, 1, 1),
    (2001005, 1001009, 'platform:user', '用户', 2, 1, 1, 1),
    (2001006, 1001009, 'platform:agreement', '协议管理', 2, 1, 2, 1),
    (2001007, 1001009, 'platform:dict', '字典管理', 2, 1, 1, 1),
    (2001008, 1001009, 'platform:tag', '标签管理', 2, 1, 2, 1),
    (2001009, 1001009, 'platform:catalog', '分类管理', 2, 1, 2, 1),
    (2001010, 1001009, 'platform:area', '地区管理', 2, 1, 3, 1),
    (2001011, 1001009, 'platform:keyword', '关键字管理', 2, 1, 3, 1),
    (2001012, 1001009, 'platform:attachment', '附件管理', 2, 1, 3, 1),
    (2001013, 1001009, 'platform:application', '应用管理', 2, 1, 3, 1),
    (2001014, 1001010, 'platform:tenant', '租户管理', 2, 1, 4, 1),
    (2001015, 1001010, 'platform:tenant:settings', '租户设置', 2, 1, 4, 1),
    (2001016, 1001010, 'platform:tenant:order', '租户订单', 2, 1, 4, 1),
    (2001017, 1001010, 'platform:tenant:log', '租户日志', 2, 1, 4, 1),
    (2001018, 1001011, 'platform:member', '会员管理', 2, 1, 11, 1),
    (2001019, 1001011, 'platform:member:setting', '会员设置', 2, 1, 11, 1),
    (2001020, 1001011, 'platform:member:order', '会员订单', 2, 1, 11, 1),
    (2001021, 1001011, 'platform:member:log', '会员日志', 2, 1, 4, 1),
    (2001022, 1001012, 'platform:session', '在线用户', 2, 1, 1, 1),
    (2001023, 1001012, 'platform:log:operation', '操作日志', 2, 1, 5, 1),
    (2001024, 1001012, 'platform:log:login', '登录日志', 2, 1, 5, 1),
    /* -------- 开放平台 -------- */
    (2002001, 1001015, 'open:wechat:mp', '微信公众号列表', 2, 2, 11, 1),
    (2002002, 1001016, 'open:wechat:ma', '微信小程序列表', 2, 2, 11, 1),
    /* -------- 数据统计和数据分析 -------- */
    (2003001, 1001013, 'data:dashboard', '驾驶舱', 2, 2, 1, 0),
    (2003002, 1001013, 'data:statistics', '数据统计', 2, 2, 1, 0),
    (2003004, 1001013, 'data:analysis', '数据分析', 2, 2, 1, 0),
    (2003003, 1001014, 'data:report', '数据报表', 2, 2, 1, 0),
    /* -------- 开发工具 -------- */
    (2004001, 1001021, 'development:tools:i18n', '多语言文本', 2, 1, 5, 1),
    (2004002, 1001021, 'development:tools:generator', '代码生成器', 2, 1, 5, 1),
    (2004003, 1001021, 'development:tools:docs', '接口文档', 2, 1, 5, 1),
    (2004004, 1001021, 'development:tools:test', '测试工具', 2, 1, 5, 1),
    (2004005, 1001022, 'development:gateway:rule', '限流规则', 2, 1, 5, 1),
    (2004006, 1001022, 'development:gateway:limit', '限流名单', 2, 1, 5, 1),
    (2004007, 1001022, 'development:gateway:router', '网关路由', 2, 1, 5, 1),
    (2004008, 1001023, 'development:monitor:system', '系统监控', 2, 1, 4, 1),
    (2004009, 1001023, 'development:monitor:job', '定时任务', 2, 1, 5, 1),
    (2004010, 1001023, 'development:monitor:cache', '缓存监控', 2, 1, 5, 1),
    (2004011, 1001023, 'development:monitor:log', '日志中心', 2, 1, 5, 1),
    (2004012, 1001023, 'development:monitor:config', '配置中心', 2, 1, 5, 1),
    (2004013, 1001023, 'development:monitor:datasource', '数据源', 2, 1, 5, 1),
    (2004014, 1001023, 'development:monitor:transaction', '分布式事务', 2, 1, 5, 1);

--
-- 角色权限关联
--

truncate sys_role_authority;

insert into sys_role_authority (`role_id`, `authority_id`, `created_at`)
select sr.id, sa.id, now()
from sys_role sr,
     sys_authority sa
where sr.id = 1;

--
-- 系统设置项
--

truncate sys_config;

insert into sys_config (`id`, `tenant_id`, `config_key`, `config_value`, `label`, `description`, `active`)
values (1000001, 0, 'APP_TITLE', 'Application', 'label_config_site_title', '站点标题', 1),
       (1000002, 0, 'APP_COPYRIGHT', 'Copyright@2023', 'label_config_site_copyright', '站点版权信息', 1),
       (1000003, 0, 'LOGIN_CAPTCHA_ENABLED', 'false', 'label_config_login_captcha_enabled', '是否启用登录验证码', 1);

--
-- 附件类型
--

truncate `sys_attachment_type`;

INSERT INTO `sys_attachment_type` (`id`, `code`, `label`, `title`, `multiple_ind`, `source`, `ext`, `file_types`, `active`)
VALUES (1000001, 'USER_AVATAR', '', '用户头像', 0, 1, 'png~|~jpeg~|~jpg~|~git', 'image/png~|~image/jpeg~|~image/jpg~|~image/git', 1),
       (1000002, 'BANNER_COVER', '', '宣传栏封面', 0, 1, 'png~|~jpeg~|~jpg~|~git', 'image/png~|~image/jpeg~|~image/jpg~|~image/git', 1),
       (1000003, 'BANNER_MOBILE_COVER', '', '宣传栏移动端封面', 0, 1, 'png~|~jpeg~|~jpg~|~git', 'image/png~|~image/jpeg~|~image/jpg~|~image/git', 1);

--
-- 字典类型
--

TRUNCATE `sys_dict_type`;

INSERT INTO `sys_dict_type` (`id`, `code`, `title`, `label`, `description`, `source`, `active`)
VALUES (1000001, 'BANNER', '宣传栏类型', 'BANNER', '宣传栏类型', 1, 1),
       (1000002, 'ANNOUNCEMENT', '资讯类型', 'ANNOUNCEMENT', '资讯类型', 1, 1),
       (1000003, 'NOTICE', '通知类型', 'NOTICE', '通知类型', 1, 1),
       (1000004, 'ACCOUNT', '账号标识', 'ACCOUNT', '账号标识', 1, 1),
       (1000005, 'USER', '用户标识', 'USER', '用户标识', 1, 1),
       (1000006, 'DEPARTMENT', '部门标识', 'DEPARTMENT', '部门标识', 1, 1),
       (1000007, 'POSITION', '岗位标识', 'POSITION', '岗位标识', 1, 1),
       (1000008, 'LINK', '友情链接', 'LINK', '友情链接', 1, 1),
       (1000009, 'LINK_CATALOG', '友情链接分类', 'LINK_CATALOG', '友情链接分类', 1, 1);

TRUNCATE `sys_dict_item`;

INSERT INTO `sys_dict_item` (`id`, `code`, `type_id`, `title`, `idx`, `source`, `active`)
VALUES (1000001001, 'HOME', 1000001, '首页宣传栏', 1, 1, 1),
       (1000001002, 'WELCOME', 1000001, '欢迎页宣传栏', 2, 1, 1),
       (1000002001, 'SYSTEM', 1000002, '系统公告', 2, 1, 1),
       (1000003001, 'SYSTEM', 1000002, '系统通知', 2, 1, 1),
       (1000006001, 'GROUP', 1000006, '总部', 2, 1, 1),
       (1000006002, 'BRANCH', 1000006, '分公司', 2, 1, 1),
       (1000006003, 'DEPARTMENT', 1000006, '部门', 2, 1, 1),
       (1000008001, 'LINK', 1000008, '友情链接', 2, 1, 1);

--
-- 标签类型
--

truncate `sys_tag_type`;

INSERT INTO `sys_tag_type` (`id`, `code`, `title`, `label`, `description`, `source`, `active`)
VALUES (1000001, 'SYSTEM', '系统标签', 'SYSTEM', '系统标签', 1, 0),
       (1000002, 'USER', '用户标签', 'USER', '用户标签', 1, 1);

truncate `sys_tag`;

INSERT INTO `sys_tag` (`id`, `type_id`, `title`, `description`, `source`, `active`)
VALUES (1000002001, 1000002, '普通用户', '普通用户', 1, 1);

-- =====================================================================================================================
-- 多语言
-- =====================================================================================================================

--
-- 语言类型
--

truncate sys_lang;

insert into sys_lang (`id`, `code`, `lang`, `country`, `label`, `description`, `default_ind`, `active`)
values (1000001, 'zh_cn', 'zh', 'cn', 'label_lang_zh_cn', '简体中文', 1, 1),
       (1000002, 'zh_tw', 'zh', 'tw', 'label_lang_zh_tw', '繁体中文', 0, 1),
       (1000003, 'en_us', 'en', 'us', 'label_lang_en_us', '美式英语', 0, 1);

--
-- 多语言文本
--

truncate sys_label;

insert into sys_label (`id`, `code`, `zh_cn_label`, `en_label`)
values (10010000101, 'label_lang_type__zh_cn', '简体中文', 'Simplified Chinese'),
       (10010000102, 'label_lang_type__zh_tw', '繁体中文', 'Traditional Chinese'),
       (10010000103, 'label_lang_type__en_us', '英文', 'English'),
       (10010000201, 'label_mobile_country_code__0085', '中国', 'China'),
       (10010000202, 'label_mobile_country_code__00852', '中国香港', 'Hong Kong'),
       (10010000203, 'label_mobile_country_code__00886', '中国台湾', 'Taiwan'),
       (10010000204, 'label_mobile_country_code__00853', '中国澳门', 'Macao'),
       (10020000001, 'label__ok', 'OK', 'OK'),
       (10020000002, 'label__delete', '删除', 'Delete'),
       (10020000003, 'label__save', '保存', 'Save'),
       (10020000004, 'label__reset', '重置', 'Reset'),
       (10020000005, 'label__submit', '提交', 'Submit'),
       (10020000006, 'label__add', '添加', 'Add'),
       (10020000007, 'label__edit', '编辑', 'Edit'),
       (10020000008, 'label__remove', '移除', 'Remove'),
       (10020000009, 'label__id', 'ID', 'ID'),
       (10020000010, 'label__code', '编号', 'Code'),
       (10020000011, 'label__title', '标题', 'Title'),
       (10020000012, 'label__name', '名称', 'Name'),
       (10020000013, 'label__description', '描述说明', 'Description'),
       (10020000014, 'label__x', '占位', '占位');

-- ==============================¬=======================================================================================
-- Message
-- =====================================================================================================================

truncate table sys_message_template_type;

insert into sys_message_template_type (`id`, `code`, `label`, `title`, `status`, `active`)
values (1000001, 'NOTICE', 'label_message_template_type__NOTICE', '通知', 1, 1),
       (1000002, 'MAIL', 'label_message_template_type__MAIL', '邮件', 1, 1),
       (1000003, 'SMS', 'label_message_template_type__SMS', '短息', 1, 1),
       (1000004, 'WECHAT', 'label_message_template_type__WECHAT', '微信', 1, 1),
       (1000005, 'WEWORK', 'label_message_template_type__WEWORK', '企微', 1, 1),
       (1000006, 'LARK', 'label_message_template_type__LARK', '飞书', 1, 1),
       (1000007, 'DINGTALK', 'label_message_template_type__DINGTALK', '钉钉', 1, 1);

truncate table sys_message_type;

insert into sys_message_type (`id`, `code`, `label`, `title`, `status`, `active`)
values (1000001, 'TEST_MESSAGE', 'label_message_type__TEST_MESSAGE', '测试专用消息', 1, 1),
       (1000002, 'CAPTCHA_MESSAGE', 'label_message_type__CAPTCHA_MESSAGE', '验证码消息', 1, 1),
       (1000003, 'REGISTER_SUCCESS_MESSAGE', 'label_message_type__REGISTER_SUCCESS_MESSAGE', '注册成功消息', 1, 1);

truncate table sys_message_template;

insert into sys_message_template (`id`, `type_id`, `template_type_id`, `content`, `status`, `active`)
values (1000001001, 1000001, 1000001, '', 1, 1),
       (1000001002, 1000001, 1000002, '', 1, 1),
       (1000001003, 1000001, 1000003, '', 1, 1),
       (1000001004, 1000001, 1000004, '', 1, 1),
       (1000001005, 1000001, 1000005, '', 1, 1),
       (1000001006, 1000001, 1000006, '', 1, 1),
       (1000001007, 1000001, 1000007, '', 1, 1),
       (1000002001, 1000002, 1000001, '', 1, 1),
       (1000002002, 1000002, 1000002, '', 1, 1),
       (1000002003, 1000002, 1000003, '', 1, 1),
       (1000002004, 1000002, 1000004, '', 1, 1),
       (1000002005, 1000002, 1000005, '', 1, 1),
       (1000002006, 1000002, 1000006, '', 1, 1),
       (1000002007, 1000002, 1000007, '', 1, 1),
       (1000003001, 1000003, 1000001, '', 1, 1),
       (1000003002, 1000003, 1000002, '', 1, 1),
       (1000003003, 1000003, 1000003, '', 1, 1),
       (1000003004, 1000003, 1000004, '', 1, 1),
       (1000003005, 1000003, 1000005, '', 1, 1),
       (1000003006, 1000003, 1000006, '', 1, 1),
       (1000003008, 1000003, 1000007, '', 1, 1);

-- ==============================¬=======================================================================================
-- 前台系统基础表
-- =====================================================================================================================

--
-- 会员类型
--

truncate `sys_vip_type`;

insert into `sys_vip_type` (`id`, `code`, `title`, `label`, `source`, `active`)
values (1000001, 'VIP', 'VIP', 'label_vip_type__vip', 1, 1),
       (1000002, 'SVIP', 'SVIP', 'label_vip_type__svip', 1, 1);

--
-- 会员套餐
--

truncate `sys_vip_item`;

insert into `sys_vip_item` (`id`, `vip_type_id`, `code`, `title`, `label`, `list_price`, `price`, `active`)
values (1000001001, 1000001, 'M1', '1个月', 'label_vip_item__month', 39, 19, 1),
       (1000001002, 1000001, 'M3', '3个月', 'label_vip_item__quarter', 99, 69, 1),
       (1000001003, 1000001, 'M6', '6个月', 'label_vip_item__year', 139, 99, 1),
       (1000001004, 1000001, 'M12', '12个月', 'label_vip_item__year', 199, 139, 1),
       (1000002001, 1000002, 'M1', '1个月', 'label_vip_item__month', 69, 29, 1),
       (1000002002, 1000002, 'M3', '3个月', 'label_vip_item__quarter', 159, 69, 1),
       (1000002003, 1000002, 'M6', '6个月', 'label_vip_item__year', 299, 139, 1),
       (1000002004, 1000002, 'M12', '12个月', 'label_vip_item__year', 399, 199, 1);

--
-- 支付类型
--

truncate `sys_pay_type`;

insert into `sys_pay_type` (`id`, `code`, `title`, `icon_name`, `status`)
values (1000001, 'ALIPAY', '支付宝', 'ant-design:alipay-outlined', 0),
       (1000002, 'WECHAT', '微信支付', 'mdi:wechat', 0),
       (1000003, 'USDT', 'USDT', 'cryptocurrency-color:usdt', 1);

--
-- 订单类型
--

truncate `sys_order_type`;

insert into `sys_order_type` (`id`, `code`, `title`)
values (1000001, 'VIP', 'VIP');

-- ==============================¬======================================================================================
-- Job
-- =====================================================================================================================

--
-- 订单类型
--

truncate `sys_job`;

insert into `sys_job` (`id`, `code`, `classname`, `status`, `type`, `unit`, `period`)
values (1, 'EXAMPLE', 'cc.wdev.platform.system.core.jobs.ExampleJob', 1, 'period', 'm', 3);

-- ==============================¬=======================================================================================
-- OAuth
-- =====================================================================================================================

--
-- 客户端
--

truncate table sys_client;

insert into sys_client (`id`, `client_id`, `client_name`, `client_secret`, `authorization_grant_types`,
                        `client_authentication_methods`, `redirect_uris`, `scopes`, `active`)
values (1000001, 'webapp', 'webapp', '$2a$10$kFl5GA5II.hPTPYcTkeVoe2J7HaUpP.d3ttZtdWmQj1N5Sul94a7a',
        'authorization_code,refresh_token,client_credentials,password',
        'client_secret_basic,client_secret_post,client_secret_jwt,private_key_jwt',
        'http://127.0.0.1:8080,http://127.0.0.1:9292,http://127.0.0.1:9292/login/oauth/code/webapp',
        'openid,profile', 1),
       (1000002, 'admin', 'admin', '$2a$10$kFl5GA5II.hPTPYcTkeVoe2J7HaUpP.d3ttZtdWmQj1N5Sul94a7a',
        'authorization_code,refresh_token,client_credentials,password',
        'client_secret_basic,client_secret_post,client_secret_jwt,private_key_jwt',
        'http://127.0.0.1:8080,http://127.0.0.1:9292,http://127.0.0.1:9292/login/oauth/code/webapp',
        'openid,profile', 1),
       (1000003, 'mobile', 'mobile', '$2a$10$U8AYqn8pV8OSH.5y.teuguFLkIVx98qwIobe3jSP1hhS4K1Oe9Jyu',
        'authorization_code,refresh_token,client_credentials,password',
        'client_secret_basic,client_secret_post,client_secret_jwt,private_key_jwt',
        'http://127.0.0.1:8080,http://127.0.0.1:9292,http://127.0.0.1:9191/login/oauth/code/demo',
        'openid,profile', 0);
