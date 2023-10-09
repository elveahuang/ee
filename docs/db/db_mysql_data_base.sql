-- =====================================================================================================================
-- 基础数据
-- =====================================================================================================================

--
-- 角色
--

insert into sys_role (`id`, `code`, `title`, `label`, `source`, `active`, `created_at`, `created_by`)
values (1, 'SYSTEM_ADMINISTRATOR', 'System Administrator', 'label_role_system_administrator', 1, 1, now(), 1),
       (2, 'ADMINISTRATOR', 'Administrator', 'label_role_administrator', 1, 1, now(), 1),
       (3, 'USER', 'User', 'label_role_user', 1, 1, now(), 1);

--
-- 内置用户
--

insert into sys_user (`id`, `username`, `email`, `mobile_country_code`, `mobile_number`,
                      `display_name`, `active`, `created_at`, `password`)
values (1, 'admin', 'me@elvea.cn', '0086', '13500000000', 'Administrator', 1, now(),
        '$2a$10$MLkjYEPJkO6KNrfUUBld6eWVr1G09nugg5UpIQVUtsQ.3Z9U2lOSK');

--
-- 顶层组织架构
--

insert into sys_organization (`id`, `code`, `label`, `title`, `root_ind`, `default_ind`)
values (1, 'ROOT_ORG', 'label_top_organization', 'All Organization', 1, 1);

--
-- 顶层岗位
--

insert into sys_position (`id`, `code`, `label`, `title`, `root_ind`, `default_ind`)
values (1, 'ROOT_PST', 'label_top_position', 'All Position', 1, 1);

--
-- 内置用户关联数据
--

insert into sys_user_role (`id`, `user_id`, `role_id`, `created_at`)
values (1, 1, 1, now()),
       (2, 1, 2, now()),
       (3, 1, 3, now());

insert into sys_entity_relation (`id`, `ancestor_id`, `entity_id`, `parent_ind`,
                                 `relation_type`, `relation_path`, `relation_index`)
values (1, 1, 1, 1, 'USR_CURRENT_ORG', '1', 1),
       (2, 1, 1, 1, 'USR_CURRENT_PST', '1', 1);

--
-- 语言类型
--

insert into sys_lang (`id`, `code`, `lang`, `country`, `label`, `description`, `default_ind`, `active`)
values (1, 'zh_cn', 'zh', 'cn', 'label_lang_zh_cn', '简体中文', 1, 1),
       (2, 'zh_tw', 'zh', 'tw', 'label_lang_zh_tw', '繁体中文', 0, 1),
       (3, 'en_us', 'en', 'us', 'label_lang_en_us', '美式英语', 0, 1);

--
-- 权限
--

insert into sys_authority (`id`, `parent_id`, `code`, `title`, `label`, `authority_type`, `sort_order`, `active`)
values
    /* -------------------------------------------------------------------------------------------------------------- */
    /* 组织架构 */
    /* -------------------------------------------------------------------------------------------------------------- */
    (1001, 0, 'organization', '组织架构', 'authority_organization', 'CATALOG', 96, 1),
    /* 组织架构 */
    (1001001, 1001, 'organization:organization', '组织架构', 'authority_organization_organization', 'MENU', 1, 1),
    (1001001001, 1001001, 'organization:organization:view', '查看', 'authority_view', 'RESOURCE', 1, 1),
    (1001001002, 1001001, 'organization:organization:add', '添加', 'authority_add', 'RESOURCE', 1, 1),
    (1001001003, 1001001, 'organization:organization:edit', '编辑', 'authority_edit', 'RESOURCE', 1, 1),
    (1001001004, 1001001, 'organization:organization:delete', '删除', 'authority_delete', 'RESOURCE', 1, 1),
    (1001001005, 1001001, 'organization:organization:import', '导入', 'authority_import', 'RESOURCE', 1, 1),
    (1001001006, 1001001, 'organization:organization:export', '导出', 'authority_export', 'RESOURCE', 1, 1),
    /* 岗位管理 */
    (1001002, 1001, 'organization:position', '岗位管理', 'authority_organization_position', 'MENU', 2, 1),
    (1001002001, 1001002, 'organization:position:view', '查看', 'authority_view', 'RESOURCE', 1, 1),
    (1001002002, 1001002, 'organization:position:add', '添加', 'authority_add', 'RESOURCE', 1, 1),
    (1001002003, 1001002, 'organization:position:edit', '编辑', 'authority_edit', 'RESOURCE', 1, 1),
    (1001002004, 1001002, 'organization:position:delete', '删除', 'authority_delete', 'RESOURCE', 1, 1),
    (1001002005, 1001002, 'organization:position:import', '导入', 'authority_import', 'RESOURCE', 1, 1),
    (1001002006, 1001002, 'organization:position:export', '导出', 'authority_export', 'RESOURCE', 1, 1),
    /* 用户管理 */
    (1001003, 1001, 'organization:user', '用户管理', 'authority_organization_user', 'MENU', 3, 1),
    (1001003001, 1001003, 'organization:user:view', '查看', 'authority_view', 'RESOURCE', 1, 1),
    (1001003002, 1001003, 'organization:user:add', '添加', 'authority_add', 'RESOURCE', 1, 1),
    (1001003003, 1001003, 'organization:user:edit', '编辑', 'authority_edit', 'RESOURCE', 1, 1),
    (1001003004, 1001003, 'organization:user:delete', '删除', 'authority_delete', 'RESOURCE', 1, 1),
    (1001003005, 1001003, 'organization:user:import', '导入', 'authority_import', 'RESOURCE', 1, 1),
    (1001003006, 1001003, 'organization:user:export', '导出', 'authority_export', 'RESOURCE', 1, 1),
    /* 群组管理 */
    (1001004, 1001, 'organization:group', '群组管理', 'authority_organization_group', 'MENU', 4, 1),
    (1001004001, 1001004, 'organization:group:view', '查看', 'authority_view', 'RESOURCE', 1, 1),
    (1001004002, 1001004, 'organization:group:add', '添加', 'authority_add', 'RESOURCE', 1, 1),
    (1001004003, 1001004, 'organization:group:edit', '编辑', 'authority_edit', 'RESOURCE', 1, 1),
    (1001004004, 1001004, 'organization:group:delete', '删除', 'authority_delete', 'RESOURCE', 1, 1),
    /* -------------------------------------------------------------------------------------------------------------- */
    /* 资源管理 */
    /* -------------------------------------------------------------------------------------------------------------- */
    (1002, 0, 'resource', '资源管理', 'authority_resource', 'CATALOG', 97, 1),
    /* 字典管理 */
    (1002001, 1002, 'resource:dictionary', '字典管理', 'authority_resource_dictionary', 'MENU', 1, 1),
    (1002001001, 1002001, 'resource:dictionary:view', '查看', 'authority_view', 'RESOURCE', 1, 1),
    (1002001002, 1002001, 'resource:dictionary:add', '添加', 'authority_add', 'RESOURCE', 1, 1),
    (1002001003, 1002001, 'resource:dictionary:edit', '编辑', 'authority_edit', 'RESOURCE', 1, 1),
    (1002001004, 1002001, 'resource:dictionary:delete', '删除', 'authority_delete', 'RESOURCE', 1, 1),
    /* 标签管理 */
    (1002002, 1002, 'resource:tag', '标签管理', 'authority_resource_tag', 'MENU', 2, 1),
    (1002002001, 1002002, 'resource:tag:view', '查看', 'authority_view', 'RESOURCE', 1, 1),
    (1002002002, 1002002, 'resource:tag:add', '添加', 'authority_add', 'RESOURCE', 1, 1),
    (1002002003, 1002002, 'resource:tag:edit', '编辑', 'authority_edit', 'RESOURCE', 1, 1),
    (1002002004, 1002002, 'resource:tag:delete', '删除', 'authority_delete', 'RESOURCE', 1, 1),
    /* 地区管理 */
    (1002003, 1002, 'resource:area', '地区管理', 'authority_resource_area', 'MENU', 3, 1),
    (1002003001, 1002003, 'resource:area:view', '查看', 'authority_view', 'RESOURCE', 1, 1),
    (1002003002, 1002003, 'resource:area:add', '添加', 'authority_add', 'RESOURCE', 1, 1),
    (1002003003, 1002003, 'resource:area:edit', '编辑', 'authority_edit', 'RESOURCE', 1, 1),
    (1002003004, 1002003, 'resource:area:delete', '删除', 'authority_delete', 'RESOURCE', 1, 1),
    /* 附件管理 */
    (1002004, 1002, 'resource:attachment', '附件管理', 'authority_resource_attachment', 'MENU', 4, 1),
    (1002004001, 1002004, 'resource:attachment:view', '查看', 'authority_view', 'RESOURCE', 1, 1),
    (1002004002, 1002004, 'resource:attachment:add', '添加', 'authority_add', 'RESOURCE', 1, 1),
    (1002004003, 1002004, 'resource:attachment:edit', '编辑', 'authority_edit', 'RESOURCE', 1, 1),
    (1002004004, 1002004, 'resource:attachment:delete', '删除', 'authority_delete', 'RESOURCE', 1, 1),
    /* 多语言文本 */
    (1002005, 1002, 'resource:label', '多语言文本', 'authority_resource_label', 'MENU', 5, 1),
    (1002005001, 1002005, 'resource:label:view', '查看', 'authority_view', 'RESOURCE', 1, 1),
    (1002005002, 1002005, 'resource:label:add', '添加', 'authority_add', 'RESOURCE', 1, 1),
    (1002005003, 1002005, 'resource:label:edit', '编辑', 'authority_edit', 'RESOURCE', 1, 1),
    (1002005004, 1002005, 'resource:label:delete', '删除', 'authority_delete', 'RESOURCE', 1, 1),
    /* 消息管理 */
    (1002006, 1002, 'resource:message', '消息管理', 'authority_resource_message', 'MENU', 6, 1),
    (1002006001, 1002006, 'resource:message:view', '查看', 'authority_view', 'RESOURCE', 1, 1),
    (1002006002, 1002006, 'resource:message:add', '添加', 'authority_add', 'RESOURCE', 1, 1),
    (1002006003, 1002006, 'resource:message:edit', '编辑', 'authority_edit', 'RESOURCE', 1, 1),
    (1002006004, 1002006, 'resource:message:delete', '删除', 'authority_delete', 'RESOURCE', 1, 1),
    /* -------------------------------------------------------------------------------------------------------------- */
    /* 系统设置 */
    /* -------------------------------------------------------------------------------------------------------------- */
    (1003, 0, 'system', '系统', 'authority_system', 'CATALOG', 99, 1),
    /* 权限管理 */
    (1003001, 1003, 'system:authority', '权限管理', 'authority_system_authority', 'MENU', 1, 1),
    (1003001001, 1003001, 'system:authority:view', '查看', 'authority_view', 'RESOURCE', 1, 1),
    (1003001002, 1003001, 'system:authority:edit', '编辑', 'authority_edit', 'RESOURCE', 1, 1),
    /* 角色管理 */
    (1003002, 1003, 'system:role', '角色管理', 'authority_system_role', 'MENU', 2, 1),
    (1003002001, 1003002, 'system:role:view', '查看', 'authority_view', 'RESOURCE', 1, 1),
    (1003002002, 1003002, 'system:role:add', '添加', 'authority_add', 'RESOURCE', 1, 1),
    (1003002003, 1003002, 'system:role:edit', '编辑', 'authority_edit', 'RESOURCE', 1, 1),
    (1003002004, 1003002, 'system:role:delete', '删除', 'authority_delete', 'RESOURCE', 1, 1),
    /* 系统设置 */
    (1003003, 1003, 'system:setting', '系统设置', 'authority_system_setting', 'MENU', 3, 1),
    (1003003001, 1003003, 'system:setting:view', '查看', 'authority_view', 'RESOURCE', 1, 1),
    (1003003002, 1003003, 'system:setting:edit', '编辑', 'authority_edit', 'RESOURCE', 1, 1),
    /* 在线用户 */
    (1003004, 1003, 'system:user', '在线用户', 'authority_system_user', 'MENU', 4, 1),
    (1003004001, 1003004, 'system:user:view', '查看', 'authority_view', 'RESOURCE', 1, 1),
    (1003004002, 1003004, 'system:user:delete', '删除', 'authority_delete', 'RESOURCE', 1, 1),
    /* 系统日志 */
    (1003005, 1003, 'system:logging', '系统日志', 'authority_system_logging', 'MENU', 5, 1),
    (1003005001, 1003005, 'system:logging:view', '查看', 'authority_view', 'RESOURCE', 1, 1),
    (1003005002, 1003005, 'system:logging:delete', '删除', 'authority_delete', 'RESOURCE', 1, 1),
    /* 定时任务 */
    (1003006, 1003, 'system:task', '定时任务', 'authority_system_task', 'MENU', 6, 1),
    (1003006001, 1003006, 'system:task:view', '查看', 'authority_view', 'RESOURCE', 1, 1),
    (1003006002, 1003006, 'system:task:edit', '编辑', 'authority_edit', 'RESOURCE', 1, 1),
    /* 应用管理 */
    (1003007, 1003, 'system:application', '应用管理', 'authority_system_application', 'MENU', 7, 1),
    (1003007001, 1003007, 'system:application:view', '查看', 'authority_view', 'RESOURCE', 1, 1),
    (1003007002, 1003007, 'system:application:delete', '删除', 'authority_delete', 'RESOURCE', 1, 1),
    /* -------------------------------------------------------------------------------------------------------------- */
    /* 租户管理 */
    /* -------------------------------------------------------------------------------------------------------------- */
    (1005, 0, 'tenant', '租户管理', 'authority_tenant', 'CATALOG', 99, 1),
    /* 租户管理 */
    (1005001, 1005, 'tenant:tenant', '租户管理', 'authority_tenant_tenant', 'MENU', 1, 1),
    (1005001001, 1005001, 'tenant:tenant:view', '查看', 'authority_view', 'RESOURCE', 1, 1),
    (1005001002, 1005001, 'tenant:tenant:add', '添加', 'authority_add', 'RESOURCE', 1, 1),
    (1005001003, 1005001, 'tenant:tenant:edit', '编辑', 'authority_edit', 'RESOURCE', 1, 1),
    (1005001004, 1005001, 'tenant:tenant:delete', '删除', 'authority_delete', 'RESOURCE', 1, 1),
    /* -------------------------------------------------------------------------------------------------------------- */
    /* 工作台 */
    /* -------------------------------------------------------------------------------------------------------------- */
    (2001, 0, 'workbench', '工作台', 'authority_workbench', 'CATALOG', 1, 1),
    /* 仪表盘 */
    (2001001, 2001, 'workbench:workbench', '工作台', 'authority_workbench_workbench', 'MENU', 1, 1),
    (2001002, 2001, 'workbench:dashboard', '仪表盘', 'authority_workbench_dashboard', 'MENU', 1, 1),
    /* -------------------------------------------------------------------------------------------------------------- */
    /* 站点管理 */
    /* -------------------------------------------------------------------------------------------------------------- */
    (2002, 0, 'site', '站点', 'authority_site', 'CATALOG', 2, 1),
    /* 产品管理 */
    (2002001, 2002, 'site:product', '产品管理', 'authority_site_product', 'MENU', 1, 1),
    (2002001001, 2002001, 'site:product:view', '查看', 'authority_view', 'RESOURCE', 1, 1),
    (2002001002, 2002001, 'site:product:add', '添加', 'authority_add', 'RESOURCE', 1, 1),
    (2002001003, 2002001, 'site:product:edit', '编辑', 'authority_edit', 'RESOURCE', 1, 1),
    (2002001004, 2002001, 'site:product:delete', '删除', 'authority_delete', 'RESOURCE', 1, 1);

--
-- 角色权限关联
--

insert into sys_role_authority (`id`, `role_id`, `authority_id`, `created_at`)
select concat(rpad(sr.id, 3, 0), rpad(sa.id, 10, 0)), sr.id, sa.id, now()
from sys_role sr,
     sys_authority sa
where sr.id = 1;

--
-- 系统设置项
--
truncate sys_config;

insert into sys_config (`id`, `config_key`, `config_value`, `label`, `description`, `active`)
values (1, 'APP_TITLE', 'Application', 'label_config_site_title', '站点标题', 1),
       (2, 'APP_COPYRIGHT', 'Copyright@2023', 'label_config_site_copyright', '站点版权信息', 1);

-- =====================================================================================================================
-- 多语言
-- =====================================================================================================================
truncate sys_label;

insert into sys_label (`id`, `code`, `zh_label`, `en_label`)
values (10010010001, 'label_lang_type__zh_cn', '简体中文', 'Simplified Chinese'),
       (10010010002, 'label_lang_type__zh_tw', '繁体中文', 'Traditional Chinese'),
       (10010010003, 'label_lang_type__en_us', '英文', 'English'),
       (10010020001, 'label_mobile_country_code__0085', '中国', 'China'),
       (10010020002, 'label_mobile_country_code__00852', '中国香港', 'Hong Kong'),
       (10010020003, 'label_mobile_country_code__00886', '中国台湾', 'Taiwan'),
       (10010020004, 'label_mobile_country_code__00853', '中国澳门', 'Macao'),
       (20010000001, 'label__ok', 'OK', 'OK'),
       (20010000002, 'label__delete', '删除', 'Delete'),
       (20010000003, 'label__save', '保存', 'Save'),
       (20010000004, 'label__reset', '重置', 'Reset'),
       (20010000005, 'label__submit', '提交', 'Submit'),
       (20010000006, 'label__add', '添加', 'Add'),
       (20010000007, 'label__edit', '编辑', 'Edit'),
       (20010000008, 'label__remove', '移除', 'Remove'),
       (20020000001, 'label__id', 'ID', 'ID'),
       (20020000002, 'label__code', '编号', 'Code'),
       (20020000003, 'label__title', '标题', 'Title'),
       (20020000004, 'label__name', '名称', 'Name'),
       (20020000005, 'label__description', '描述说明', 'Description'),
       (30010000001, 'label__x', '占位', '占位');

-- ==============================¬=======================================================================================
-- Message
-- =====================================================================================================================

truncate table sys_message_template_type;

insert into sys_message_template_type (`id`, `code`, `label`, `title`, `status`, `active`)
values (1001, 'NOTICE', 'NOTICE', 'NOTICE', 1, 1),
       (1002, 'MAIL', 'MAIL', 'MAIL', 1, 1),
       (1003, 'SMS', 'SMS', 'SMS', 1, 1),
       (1004, 'WECHAT', 'WECHAT', 'WECHAT', 1, 1),
       (1005, 'WEWORK', 'WEWORK', 'WEWORK', 1, 1),
       (1006, 'LARK', 'LARK', 'LARK', 1, 1),
       (1007, 'DINGTALK', 'DINGTALK', 'DINGTALK', 1, 1);

truncate table sys_message_type;

insert into sys_message_type (`id`, `code`, `label`, `title`, `status`, `active`)
values (1001, 'REGISTER_CAPTCHA_MESSAGE', '', '', 1, 1),
       (1002, 'REGISTER_SUCCESS_MESSAGE', '', '', 1, 1);

truncate table sys_message_template;

-- ==============================¬=======================================================================================
-- OAuth
-- =====================================================================================================================

--
-- 客户端
--

truncate table sys_client;

insert into sys_client (`id`, `client_id`, `client_name`, `client_secret`, `authorization_grant_types`,
                        `client_authentication_methods`, `redirect_uris`, `scopes`, `active`)
values (1, 'webapp', 'webapp', '$2a$10$kFl5GA5II.hPTPYcTkeVoe2J7HaUpP.d3ttZtdWmQj1N5Sul94a7a',
        'authorization_code,refresh_token,client_credentials,password',
        'client_secret_basic,client_secret_post,client_secret_jwt,private_key_jwt',
        'http://127.0.0.1:8080,http://127.0.0.1:9292,http://127.0.0.1:9292/login/oauth/code/webapp',
        'openid,profile', 1),
       (2, 'demo', 'demo', '$2a$10$U8AYqn8pV8OSH.5y.teuguFLkIVx98qwIobe3jSP1hhS4K1Oe9Jyu',
        'authorization_code,refresh_token,client_credentials,password',
        'client_secret_basic,client_secret_post,client_secret_jwt,private_key_jwt',
        'http://127.0.0.1:8080,http://127.0.0.1:9292,http://127.0.0.1:9191/login/oauth/code/demo',
        'openid,profile', 1);
