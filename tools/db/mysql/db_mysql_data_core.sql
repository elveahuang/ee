-- =====================================================================================================================
-- 核心基础数据
-- =====================================================================================================================

USE ee_platform;

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
values (1001001, '1001001', 1, 1, now(), now()),
       (1001002, '1001002', 1, 1, now(), now());

truncate sys_user;

insert into sys_user (`id`, `tenant_id`, `uuid`, `username`, `email`, `mobile_country_code`, `mobile_number`,
                      `display_name`, `birthday`, `active`, `created_at`, `password`)
values (1002001, 1000001, 1001001, 'admin', 'ee@elvea.cn', '86', '13500000000', 'Administrator', now(), 1, now(),
        '$2a$10$Ef.pnezRUx3LPk1SCpy2PeHI/o..a4EGqOyZ69B0Qs/HvGA5Vq/uK');

truncate sys_account;

insert into sys_account (`id`, `tenant_id`, `uuid`, `username`, `email`, `mobile_country_code`, `mobile_number`,
                         `display_name`, `birthday`, `active`, `created_at`, `password`)
values (1003001, 1000001, 1001002, 'test', 'me@elvea.cn', '86', '13500000000', 'Administrator', now(), 1, now(),
        '$2a$10$3HKECKLohGTOQ1bmvKfS5.Erln4wQ1xY/rRunUVtRVl.tz9DlVOVm');

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

insert into sys_entity_relation (`id`, `tenant_id`, `ancestor_id`, `entity_id`, `parent_ind`, `biz_type`, `relation_path`, `relation_index`)
values (1002001001, 1000001, 1005001, 1005001, 1, 'USR_CURRENT_ORG', '1', 1),
       (1002001002, 1000001, 1006001, 1006001, 1, 'USR_CURRENT_PST', '1', 1);

-- =====================================================================================================================
-- 权限数据
-- =====================================================================================================================

--
-- 权限
--

truncate sys_authority;

insert into sys_authority (`id`, `parent_id`, `code`, `title`, `authority_type`, `authority_scope_type`, `authority_biz_type`, `idx`, `active`)
values
    /* ================================================ 模块 ================================================ */
    (1000001, 0, 'module:home', '首页', 'MODULE', 'TENANT', 'TENANT', 111, 0),
    (1000002, 0, 'module:system', '系统管理', 'MODULE', 'TENANT', 'TENANT', 222, 1),
    (1000003, 0, 'module:platform', '平台管理', 'MODULE', 'PLATFORM', 'TENANT', 333, 1),
    (1000004, 0, 'module:data', '数据', 'MODULE', 'TENANT', 'TENANT', 555, 1),
    (1000005, 0, 'module:open', '开放平台', 'MODULE', 'TENANT', 'TENANT', 444, 1),
    (1000006, 0, 'module:dev', '开发管理', 'MODULE', 'PLATFORM', 'TENANT', 999, 1),
    (1000007, 0, 'module:ai', 'AI', 'MODULE', 'PLATFORM', 'TENANT', 666, 1),
    /* ================================================ 分组 ================================================ */
    /* -------- 系统管理 - 分组 -------- */
    (1000002001, 1000002, 'group:system:site', '站点管理', 'GROUP', 'TENANT', 'TENANT', 33, 1),
    (1000002002, 1000002, 'group:system:organization', '组织架构', 'GROUP', 'TENANT', 'TENANT', 88, 1),
    (1000002003, 1000002, 'group:system:setting', '系统设置', 'GROUP', 'TENANT', 'TENANT', 99, 1),
    (1000002004, 1000002, 'group:system:monitor', '系统监控', 'GROUP', 'TENANT', 'TENANT', 99, 1),
    /* -------- 平台管理 - 分组 -------- */
    (1000003001, 1000003, 'group:platform:tenant', '租户管理', 'GROUP', 'PLATFORM', 'TENANT', 33, 1),
    (1000003002, 1000003, 'group:platform:member', '会员管理', 'GROUP', 'PLATFORM', 'TENANT', 33, 1),
    (1000003003, 1000003, 'group:platform:setting', '平台设置', 'GROUP', 'PLATFORM', 'TENANT', 33, 1),
    (1000003004, 1000003, 'group:platform:monitor', '平台监控', 'GROUP', 'PLATFORM', 'TENANT', 33, 1),
    /* -------- 数据 - 分组 -------- */
    (1000004001, 1000004, 'group:data:workbench', '仪表盘', 'GROUP', 'TENANT', 'TENANT', 11, 0),
    (1000004002, 1000004, 'group:data:report', '数据报表', 'GROUP', 'TENANT', 'TENANT', 11, 0),
    /* -------- 开放平台 - 分组 -------- */
    (1000005001, 1000005, 'group:open:wechat:mp', '微信公众号', 'GROUP', 'TENANT', 'TENANT', 33, 1),
    (1000005002, 1000005, 'group:open:wechat:ma', '微信小程序', 'GROUP', 'TENANT', 'TENANT', 33, 1),
    (1000005003, 1000005, 'group:open:alipay:ma', '支付宝小程序', 'GROUP', 'TENANT', 'TENANT', 33, 0),
    (1000005004, 1000005, 'group:open:douyin:ma', '抖音小程序', 'GROUP', 'TENANT', 'TENANT', 33, 0),
    (1000005005, 1000005, 'group:open:ks:ma', '快手小程序', 'GROUP', 'TENANT', 'TENANT', 33, 0),
    (1000005006, 1000005, 'group:open:xhs:ma', '小红书小程序', 'GROUP', 'TENANT', 'TENANT', 33, 0),
    /* -------- 开发管理 - 分组 -------- */
    (1000006001, 1000006, 'group:dev:tools', '开发工具', 'GROUP', 'PLATFORM', 'TENANT', 11, 0),
    (1000006002, 1000006, 'group:dev:gateway', '网关管理', 'GROUP', 'PLATFORM', 'TENANT', 22, 0),
    (1000006003, 1000006, 'group:dev:monitor', '平台监控', 'GROUP', 'PLATFORM', 'TENANT', 33, 0),
    (1000006004, 1000006, 'group:dev:ai', 'AI', 'GROUP', 'PLATFORM', 'TENANT', 44, 0),
    /* ================================================ 模块 ================================================ */
    /* -------- 首页 - 资源 -------- */
    (1000001000001, 1000001, 'system:workbench', '工作台', 'RESOURCE', 'TENANT', 'TENANT', 11, 1),
    /* -------- 系统管理 - 资源 -------- */
    (1000002001001, 1000002001, 'system:announcement', '公告管理', 'RESOURCE', 'TENANT', 'TENANT', 11, 1),
    (1000002001002, 1000002001, 'system:banner', '公告管理', 'RESOURCE', 'TENANT', 'TENANT', 22, 1),
    (1000002002001, 1000002002, 'system:organization', '组织架构', 'RESOURCE', 'TENANT', 'TENANT', 1, 1),
    (1000002002002, 1000002002, 'system:user', '用户管理', 'RESOURCE', 'TENANT', 'TENANT', 3, 1),
    (1000002002003, 1000002002, 'system:group', '群组管理', 'RESOURCE', 'TENANT', 'TENANT', 4, 1),
    (1000002002004, 1000002002, 'system:position', '岗位管理', 'RESOURCE', 'TENANT', 'TENANT', 2, 1),
    (1000002002005, 1000002002, 'system:grade', '职级管理', 'RESOURCE', 'TENANT', 'TENANT', 4, 1),
    (1000002003001, 1000002003, 'system:settings', '基本设置', 'RESOURCE', 'TENANT', 'TENANT', 3, 1),
    (1000002003002, 1000002003, 'system:config', '参数管理', 'RESOURCE', 'TENANT', 'TENANT', 3, 1),
    (1000002003003, 1000002003, 'system:role', '角色管理', 'RESOURCE', 'TENANT', 'TENANT', 2, 1),
    (1000002003004, 1000002003, 'system:authority', '权限管理', 'RESOURCE', 'TENANT', 'TENANT', 1, 1),
    (1000002003005, 1000002003, 'system:agreement', '协议管理', 'RESOURCE', 'TENANT', 'TENANT', 2, 1),
    (1000002003006, 1000002003, 'system:dict', '字典管理', 'RESOURCE', 'TENANT', 'TENANT', 1, 1),
    (1000002003007, 1000002003, 'system:tag', '标签管理', 'RESOURCE', 'TENANT', 'TENANT', 2, 1),
    (1000002003008, 1000002003, 'system:catalog', '分类管理', 'RESOURCE', 'TENANT', 'TENANT', 2, 1),
    (1000002003009, 1000002003, 'system:region', '地区管理', 'RESOURCE', 'TENANT', 'TENANT', 3, 1),
    (1000002003010, 1000002003, 'system:keyword', '关键字管理', 'RESOURCE', 'TENANT', 'TENANT', 3, 1),
    (1000002003011, 1000002003, 'system:attachment', '附件管理', 'RESOURCE', 'TENANT', 'TENANT', 3, 1),
    (1000002003012, 1000002003, 'system:application', '应用管理', 'RESOURCE', 'TENANT', 'TENANT', 3, 1),
    (1000002003013, 1000002003, 'system:message', '消息管理', 'RESOURCE', 'TENANT', 'TENANT', 3, 1),
    (1000002004001, 1000002004, 'system:session', '在线用户', 'RESOURCE', 'TENANT', 'TENANT', 1, 1),
    (1000002004002, 1000002004, 'system:log:operation', '操作日志', 'RESOURCE', 'TENANT', 'TENANT', 5, 1),
    (1000002004003, 1000002004, 'system:log:login', '登录日志', 'RESOURCE', 'TENANT', 'TENANT', 5, 1),
    (1000002004004, 1000002004, 'system:log:message', '消息日志', 'RESOURCE', 'TENANT', 'TENANT', 5, 1),
    /* -------- 平台设置 - 资源 -------- */
    (1000003001001, 1000003001, 'platform:tenant', '租户管理', 'RESOURCE', 'PLATFORM', 'TENANT', 4, 1),
    (1000003001002, 1000003001, 'platform:tenant:package', '租户套餐', 'RESOURCE', 'PLATFORM', 'TENANT', 4, 1),
    (1000003001003, 1000003001, 'platform:tenant:order', '租户订单', 'RESOURCE', 'PLATFORM', 'TENANT', 4, 1),
    (1000003001004, 1000003001, 'platform:tenant:log', '租户日志', 'RESOURCE', 'PLATFORM', 'TENANT', 4, 1),
    (1000000002001, 1000003002, 'platform:member', '会员管理', 'RESOURCE', 'PLATFORM', 'TENANT', 11, 1),
    (1000003002002, 1000003002, 'platform:member:package', '会员套餐', 'RESOURCE', 'PLATFORM', 'TENANT', 11, 1),
    (1000003002003, 1000003002, 'platform:member:order', '会员订单', 'RESOURCE', 'PLATFORM', 'TENANT', 11, 1),
    (1000003002004, 1000003002, 'platform:member:log', '会员日志', 'RESOURCE', 'PLATFORM', 'TENANT', 4, 1),
    (1000003003001, 1000003003, 'platform:settings', '基本设置', 'RESOURCE', 'PLATFORM', 'TENANT', 3, 1),
    (1000003003002, 1000003003, 'platform:config', '参数管理', 'RESOURCE', 'PLATFORM', 'TENANT', 3, 1),
    (1000003003003, 1000003003, 'platform:role', '角色管理', 'RESOURCE', 'PLATFORM', 'TENANT', 2, 1),
    (1000003003004, 1000003003, 'platform:authority', '权限管理', 'RESOURCE', 'PLATFORM', 'TENANT', 1, 1),
    (1000003003005, 1000003003, 'platform:agreement', '协议管理', 'RESOURCE', 'PLATFORM', 'TENANT', 2, 1),
    (1000003003006, 1000003003, 'platform:dict', '字典管理', 'RESOURCE', 'PLATFORM', 'TENANT', 1, 1),
    (1000003003007, 1000003003, 'platform:tag', '标签管理', 'RESOURCE', 'PLATFORM', 'TENANT', 2, 1),
    (1000003003008, 1000003003, 'platform:catalog', '分类管理', 'RESOURCE', 'PLATFORM', 'TENANT', 2, 1),
    (1000003003009, 1000003003, 'platform:region', '地区管理', 'RESOURCE', 'PLATFORM', 'TENANT', 3, 1),
    (1000003003010, 1000003003, 'platform:keyword', '关键字管理', 'RESOURCE', 'PLATFORM', 'TENANT', 3, 1),
    (1000003003011, 1000003003, 'platform:attachment', '附件管理', 'RESOURCE', 'PLATFORM', 'TENANT', 3, 1),
    (1000003003012, 1000003003, 'platform:application', '应用管理', 'RESOURCE', 'PLATFORM', 'TENANT', 3, 1),
    (1000003003013, 1000003003, 'platform:message', '消息管理', 'RESOURCE', 'PLATFORM', 'TENANT', 3, 1),
    (1000003004001, 1000003004, 'platform:session', '在线用户', 'RESOURCE', 'PLATFORM', 'TENANT', 1, 1),
    (1000003004002, 1000003004, 'platform:log:operation', '操作日志', 'RESOURCE', 'PLATFORM', 'TENANT', 5, 1),
    (1000003004003, 1000003004, 'platform:log:login', '登录日志', 'RESOURCE', 'PLATFORM', 'TENANT', 5, 1),
    (1000003004004, 1000003004, 'platform:log:message', '消息日志', 'RESOURCE', 'PLATFORM', 'TENANT', 5, 1),
    /* -------- 数据 - 资源 -------- */
    (1000004001001, 1000004001, 'data:dashboard', '仪表盘', 'RESOURCE', 'TENANT', 'TENANT', 1, 0),
    (1000004002001, 1000004002, 'data:report', '数据报表', 'RESOURCE', 'TENANT', 'TENANT', 1, 0),
    /* -------- 开放平台 - 资源 -------- */
    (1000005001001, 1000005001, 'open:wechat:mp', '微信公众号列表', 'RESOURCE', 'TENANT', 'TENANT', 11, 1),
    (1000005002001, 1000005002, 'open:wechat:ma', '微信小程序列表', 'RESOURCE', 'TENANT', 'TENANT', 11, 1),
    /* -------- 开发工具 - 资源 -------- */
    (1000006001001, 1000006001, 'dev:tools:i18n', '多语言文本', 'RESOURCE', 'PLATFORM', 'TENANT', 5, 1),
    (1000006001002, 1000006001, 'dev:tools:generator', '代码生成器', 'RESOURCE', 'PLATFORM', 'TENANT', 5, 1),
    (1000006001003, 1000006001, 'dev:tools:docs', '接口文档', 'RESOURCE', 'PLATFORM', 'TENANT', 5, 1),
    (1000006001004, 1000006001, 'dev:tools:test', '测试工具', 'RESOURCE', 'PLATFORM', 'TENANT', 5, 1),
    (1000006002001, 1000006002, 'dev:gateway:rule', '限流规则', 'RESOURCE', 'PLATFORM', 'TENANT', 5, 1),
    (1000006002002, 1000006002, 'dev:gateway:limit', '限流名单', 'RESOURCE', 'PLATFORM', 'TENANT', 5, 1),
    (1000006002003, 1000006002, 'dev:gateway:router', '网关路由', 'RESOURCE', 'PLATFORM', 'TENANT', 5, 1),
    (1000006003001, 1000006003, 'dev:monitor:system', '系统监控', 'RESOURCE', 'PLATFORM', 'TENANT', 4, 1),
    (1000006003002, 1000006003, 'dev:monitor:job', '定时任务', 'RESOURCE', 'PLATFORM', 'TENANT', 5, 1),
    (1000006003003, 1000006003, 'dev:monitor:cache', '缓存监控', 'RESOURCE', 'PLATFORM', 'TENANT', 5, 1),
    (1000006003004, 1000006003, 'dev:monitor:application', '应用监控', 'RESOURCE', 'PLATFORM', 'TENANT', 5, 1),
    (1000006003005, 1000006003, 'dev:monitor:log', '日志中心', 'RESOURCE', 'PLATFORM', 'TENANT', 5, 1),
    (1000006003006, 1000006003, 'dev:monitor:config', '配置中心', 'RESOURCE', 'PLATFORM', 'TENANT', 5, 1),
    (1000006004001, 1000006004, 'dev:ai:agent', '智能体', 'RESOURCE', 'TENANT', 'TENANT', 11, 1),
    (1000006004002, 1000006004, 'dev:ai:chat', '机器人', 'RESOURCE', 'TENANT', 'TENANT', 11, 1);

--
-- 系统设置项
--

truncate sys_config;

insert into sys_config (`id`, `tenant_id`, `config_key`, `config_value`, `label`, `description`, `active`)
values (1000001, 0, 'APP_TITLE', 'Application', 'label_config_site_title', '站点标题', 1),
       (1000002, 0, 'APP_COPYRIGHT', 'Copyright@2023', 'label_config_site_copyright', '站点版权信息', 1),
       (1000003, 0, 'LOGIN_CAPTCHA_ENABLED', 'false', 'label_config_login_captcha_enabled', '是否启用登录验证码', 1);

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

--
-- 会员类型
--

truncate `sys_package`;

insert into `sys_package` (`id`, `biz_type`, `code`, `title`, `label`, `source`, `active`)
values (1001001, 1, 'Professional', '专业版', 'label_package_professional', 1, 1),
       (1001002, 1, 'Enterprise', '企业版', 'label_package_enterprise', 1, 1),
       (1002001, 2, 'VIP', '会员', 'label_package_vip', 1, 1),
       (1002002, 2, 'SVIP', '超级会员', 'label_package_svip', 1, 1);

-- ==============================¬======================================================================================
-- Job
-- =====================================================================================================================

--
-- 定时任务
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
