-- =====================================================================================================================
-- 实体数据
-- 1001000 - 账号
-- 1002000 - 角色
-- 1003000 - 用户
-- 1004000 - 部门
-- 1005000 - 岗位
-- =====================================================================================================================

--
-- 内置账号
--

truncate sys_account;

insert into sys_account (`id`, `username`, `email`, `mobile_country_code`, `mobile_number`,
                         `display_name`, `birthday`, `active`, `created_at`, `password`)
values (1001001, 'test', 'huang@elvea.cn', '0086', '13800138000', 'test', now(), 1, now(),
        '$2a$10$/ax0s7CATLLmYwvedSmZZumjHgaspXxWb4uv2ywzi9fNU6M4AJqpq');

--
-- 角色
--

truncate sys_role;

insert into sys_role (`id`, `code`, `title`, `label`, `source`, `active`, `created_at`, `created_by`)
values (1002001, 'SYSTEM_ADMINISTRATOR', 'System Administrator', 'label_role_system_administrator', 1, 1, now(), 1),
       (1002002, 'ADMINISTRATOR', 'Administrator', 'label_role_administrator', 1, 1, now(), 1),
       (1002003, 'USER', 'User', 'label_role_user', 1, 1, now(), 1);

--
-- 内置用户
--

truncate sys_user;

insert into sys_user (`id`, `username`, `email`, `mobile_country_code`, `mobile_number`,
                      `display_name`, `birthday`, `active`, `created_at`, `password`)
values (1003001, 'admin', 'me@elvea.cn', '0086', '13500000000', 'Administrator', now(), 1, now(),
        '$2a$10$MLkjYEPJkO6KNrfUUBld6eWVr1G09nugg5UpIQVUtsQ.3Z9U2lOSK');

--
-- 顶层组织架构
--

truncate sys_organization;

insert into sys_organization (`id`, `code`, `label`, `title`, `root_ind`, `default_ind`)
values (1004001, 'ROOT_ORG', 'label_top_organization', 'All Organization', 1, 1);

--
-- 顶层岗位
--

truncate sys_position;

insert into sys_position (`id`, `code`, `label`, `title`, `root_ind`, `default_ind`)
values (1005001, 'ROOT_PST', 'label_top_position', 'All Position', 1, 1);

--
-- 内置用户关联数据
--

truncate sys_user_role;

insert into sys_user_role (`id`, `user_id`, `role_id`, `created_at`)
values (1003001001, 1003001, 1002001, now()),
       (1003001002, 1003001, 1002002, now()),
       (1003001003, 1003001, 1002003, now());

truncate sys_entity_relation;

insert into sys_entity_relation (`id`, `ancestor_id`, `entity_id`, `parent_ind`, `relation_type`, `relation_path`, `relation_index`)
values (1003001001, 1004001, 1003001, 1, 'USR_CURRENT_ORG', '1', 1),
       (1003001002, 1005001, 1003001, 1, 'USR_CURRENT_PST', '1', 1);

-- =====================================================================================================================
-- 权限数据
-- =====================================================================================================================

--
-- 权限
--

truncate sys_authority;

insert into sys_authority (`id`, `parent_id`, `code`, `title`, `label`, `authority_type`, `sort_order`, `active`)
values
    /* -------------------------------------------------------------------------------------------------------------- */
    /* 工作台 */
    /* -------------------------------------------------------------------------------------------------------------- */
    (1001001000000, 0, 'workbench', '工作台', '', 'CATALOG', 11, 1),
    /* -------------------------------------------------------------------------------------------------------------- */
    /* 仪表盘 */
    /* -------------------------------------------------------------------------------------------------------------- */
    (1001002000000, 0, 'dashboard', '仪表盘', '', 'CATALOG', 22, 0),
    /* 仪表盘 */
    (1001002001000, 1001002000000, 'dashboard:analysis', '数据分析', '', 'MENU', 1, 0),
    (1001002002000, 1001002000000, 'dashboard:monitor', '系统监控', '', 'MENU', 1, 0),
    /* -------------------------------------------------------------------------------------------------------------- */
    /* 站点管理 */
    /* -------------------------------------------------------------------------------------------------------------- */
    (1001003000000, 0, 'site', '站点', '', 'CATALOG', 33, 1),
    /* 公告管理 */
    (1001003001000, 1001003000000, 'site:announcement', '公告管理', '', 'MENU', 11, 1),
    (1001003001001, 1001003001000, 'site:announcement:view', '查看', '', 'RESOURCE', 1, 1),
    (1001003001002, 1001003001000, 'site:announcement:add', '添加', '', 'RESOURCE', 1, 1),
    (1001003001003, 1001003001000, 'site:announcement:edit', '编辑', '', 'RESOURCE', 1, 1),
    (1001003001004, 1001003001000, 'site:announcement:delete', '删除', '', 'RESOURCE', 1, 1),
    /* 宣传栏管理 */
    (1001003002000, 1001003000000, 'site:poster', '公告管理', '', 'MENU', 22, 1),
    (1001003002001, 1001003002000, 'site:poster:view', '查看', '', 'RESOURCE', 1, 1),
    (1001003002002, 1001003002000, 'site:poster:add', '添加', '', 'RESOURCE', 1, 1),
    (1001003002003, 1001003002000, 'site:poster:edit', '编辑', '', 'RESOURCE', 1, 1),
    (1001003002004, 1001003002000, 'site:poster:delete', '删除', '', 'RESOURCE', 1, 1),
    /* 产品管理 */
    (1001003003000, 1001003000000, 'site:product', '产品管理', '', 'MENU', 33, 0),
    (1001003003001, 1001003003000, 'site:product:view', '查看', '', 'RESOURCE', 1, 1),
    (1001003003002, 1001003003000, 'site:product:add', '添加', '', 'RESOURCE', 1, 1),
    (1001003003003, 1001003003000, 'site:product:edit', '编辑', '', 'RESOURCE', 1, 1),
    (1001003003004, 1001003003000, 'site:product:delete', '删除', '', 'RESOURCE', 1, 1),
    /* -------------------------------------------------------------------------------------------------------------- */
    /* 资源管理 */
    /* -------------------------------------------------------------------------------------------------------------- */
    (1001004000000, 0, 'resource', '资源管理', '', 'CATALOG', 77, 1),
    /* 字典管理 */
    (1001004001000, 1001004000000, 'resource:dictionary', '字典管理', '', 'MENU', 1, 1),
    (1001004001001, 1001002001000, 'resource:dictionary:view', '查看', '', 'RESOURCE', 1, 1),
    (1001004001002, 1001002001000, 'resource:dictionary:add', '添加', '', 'RESOURCE', 1, 1),
    (1001004001003, 1001002001000, 'resource:dictionary:edit', '编辑', '', 'RESOURCE', 1, 1),
    (1001004001004, 1001002001000, 'resource:dictionary:delete', '删除', '', 'RESOURCE', 1, 1),
    /* 标签管理 */
    (1001004002000, 1001004000000, 'resource:tag', '标签管理', '', 'MENU', 2, 1),
    (1001004002001, 1001002002000, 'resource:tag:view', '查看', '', 'RESOURCE', 1, 1),
    (1001004002002, 1001002002000, 'resource:tag:add', '添加', '', 'RESOURCE', 1, 1),
    (1001004002003, 1001002002000, 'resource:tag:edit', '编辑', '', 'RESOURCE', 1, 1),
    (1001004002004, 1001002002000, 'resource:tag:delete', '删除', '', 'RESOURCE', 1, 1),
    /* 地区管理 */
    (1001004003000, 1001004000000, 'resource:area', '地区管理', '', 'MENU', 3, 1),
    (1001004003001, 1001002003000, 'resource:area:view', '查看', '', 'RESOURCE', 1, 1),
    (1001004003002, 1001002003000, 'resource:area:add', '添加', '', 'RESOURCE', 1, 1),
    (1001004003003, 1001002003000, 'resource:area:edit', '编辑', '', 'RESOURCE', 1, 1),
    (1001004003004, 1001002003000, 'resource:area:delete', '删除', '', 'RESOURCE', 1, 1),
    /* 附件管理 */
    (1001004004000, 1001004000000, 'resource:attachment', '附件管理', '', 'MENU', 4, 1),
    (1001004004001, 1001002004000, 'resource:attachment:view', '查看', '', 'RESOURCE', 1, 1),
    (1001004004002, 1001002004000, 'resource:attachment:add', '添加', '', 'RESOURCE', 1, 1),
    (1001004004003, 1001002004000, 'resource:attachment:edit', '编辑', '', 'RESOURCE', 1, 1),
    (1001004004004, 1001002004000, 'resource:attachment:delete', '删除', '', 'RESOURCE', 1, 1),
    /* -------------------------------------------------------------------------------------------------------------- */
    /* 商城管理 */
    /* -------------------------------------------------------------------------------------------------------------- */
    (1001005000000, 0, 'mall', '商城', '', 'CATALOG', 33, 1),
    /* 商品管理 */
    (1001005001000, 1001005000000, 'mall:goods', '商品管理', '', 'MENU', 11, 1),
    (1001005001001, 1001005001000, 'mall:goods:view', '查看', '', 'RESOURCE', 1, 1),
    (1001005001002, 1001005001000, 'mall:goods:add', '添加', '', 'RESOURCE', 1, 1),
    (1001005001003, 1001005001000, 'mall:goods:edit', '编辑', '', 'RESOURCE', 1, 1),
    (1001005001004, 1001005001000, 'mall:goods:delete', '删除', '', 'RESOURCE', 1, 1),
    /* 品牌管理 */
    (1001005002000, 1001005000000, 'mall:brand', '品牌管理', '', 'MENU', 11, 1),
    (1001005002001, 1001005002000, 'mall:brand:view', '查看', '', 'RESOURCE', 1, 1),
    (1001005002002, 1001005002000, 'mall:brand:add', '添加', '', 'RESOURCE', 1, 1),
    (1001005002003, 1001005002000, 'mall:brand:edit', '编辑', '', 'RESOURCE', 1, 1),
    (1001005002004, 1001005002000, 'mall:brand:delete', '删除', '', 'RESOURCE', 1, 1),
    /* 商品订单 */
    (1001005003000, 1001005000000, 'mall:order', '商品订单', '', 'MENU', 11, 1),
    (1001005003001, 1001005003000, 'mall:order:view', '查看', '', 'RESOURCE', 1, 1),
    (1001005003002, 1001005003000, 'mall:order:add', '添加', '', 'RESOURCE', 1, 1),
    (1001005003003, 1001005003000, 'mall:order:edit', '编辑', '', 'RESOURCE', 1, 1),
    (1001005003004, 1001005003000, 'mall:order:delete', '删除', '', 'RESOURCE', 1, 1),
    /* -------------------------------------------------------------------------------------------------------------- */
    /* 会员管理 */
    /* -------------------------------------------------------------------------------------------------------------- */
    (1001006000000, 0, 'vip', '会员', '', 'CATALOG', 33, 1),
    /* 会员设置 */
    (1001006001000, 1001006000000, 'vip:setting', '会员设置', '', 'MENU', 11, 1),
    (1001006001001, 1001006001000, 'vip:setting:view', '查看', '', 'RESOURCE', 1, 1),
    (1001006001002, 1001006001000, 'vip:setting:add', '添加', '', 'RESOURCE', 1, 1),
    (1001006001003, 1001006001000, 'vip:setting:edit', '编辑', '', 'RESOURCE', 1, 1),
    (1001006001004, 1001006001000, 'vip:setting:delete', '删除', '', 'RESOURCE', 1, 1),
    /* 会员列表 */
    (1001006002000, 1001006000000, 'vip:account', '会员列表', '', 'MENU', 11, 1),
    (1001006002001, 1001006002000, 'vip:account:view', '查看', '', 'RESOURCE', 1, 1),
    (1001006002002, 1001006002000, 'vip:account:add', '添加', '', 'RESOURCE', 1, 1),
    (1001006002003, 1001006002000, 'vip:account:edit', '编辑', '', 'RESOURCE', 1, 1),
    (1001006002004, 1001006002000, 'vip:account:delete', '删除', '', 'RESOURCE', 1, 1),
    /* 会员订单管理 */
    (1001006003000, 1001006000000, 'vip:order', '会员订单', '', 'MENU', 11, 1),
    (1001006003001, 1001006003000, 'vip:order:view', '查看', '', 'RESOURCE', 1, 1),
    (1001006003002, 1001006003000, 'vip:order:add', '添加', '', 'RESOURCE', 1, 1),
    (1001006003003, 1001006003000, 'vip:order:edit', '编辑', '', 'RESOURCE', 1, 1),
    (1001006003004, 1001006003000, 'vip:order:delete', '删除', '', 'RESOURCE', 1, 1),
    /* -------------------------------------------------------------------------------------------------------------- */
    /* 组织架构 */
    /* -------------------------------------------------------------------------------------------------------------- */
    (1001007000000, 0, 'organization', '组织架构', '', 'CATALOG', 88, 1),
    /* 组织架构 */
    (1001007001000, 1001007000000, 'organization:organization', '组织架构', '', 'MENU', 1, 1),
    (1001007001001, 1001007001000, 'organization:organization:view', '查看', '', 'RESOURCE', 1, 1),
    (1001007001002, 1001007001000, 'organization:organization:add', '添加', '', 'RESOURCE', 1, 1),
    (1001007001003, 1001007001000, 'organization:organization:edit', '编辑', '', 'RESOURCE', 1, 1),
    (1001007001004, 1001007001000, 'organization:organization:delete', '删除', '', 'RESOURCE', 1, 1),
    (1001007001005, 1001007001000, 'organization:organization:import', '导入', '', 'RESOURCE', 1, 1),
    (1001007001006, 1001007001000, 'organization:organization:export', '导出', '', 'RESOURCE', 1, 1),
    /* 岗位管理 */
    (1001007002000, 1001007000000, 'organization:position', '岗位管理', '', 'MENU', 2, 1),
    (1001007002001, 1001007002000, 'organization:position:view', '查看', '', 'RESOURCE', 1, 1),
    (1001007002002, 1001007002000, 'organization:position:add', '添加', '', 'RESOURCE', 1, 1),
    (1001007002003, 1001007002000, 'organization:position:edit', '编辑', '', 'RESOURCE', 1, 1),
    (1001007002004, 1001007002000, 'organization:position:delete', '删除', '', 'RESOURCE', 1, 1),
    (1001007002005, 1001007002000, 'organization:position:import', '导入', '', 'RESOURCE', 1, 1),
    (1001007002006, 1001007002000, 'organization:position:export', '导出', '', 'RESOURCE', 1, 1),
    /* 用户管理 */
    (1001007003000, 1001007000000, 'organization:user', '用户管理', '', 'MENU', 3, 1),
    (1001007003001, 1001007003000, 'organization:user:view', '查看', '', 'RESOURCE', 1, 1),
    (1001007003002, 1001007003000, 'organization:user:add', '添加', '', 'RESOURCE', 1, 1),
    (1001007003003, 1001007003000, 'organization:user:edit', '编辑', '', 'RESOURCE', 1, 1),
    (1001007003004, 1001007003000, 'organization:user:delete', '删除', '', 'RESOURCE', 1, 1),
    (1001007003005, 1001007003000, 'organization:user:import', '导入', '', 'RESOURCE', 1, 1),
    (1001007003006, 1001007003000, 'organization:user:export', '导出', '', 'RESOURCE', 1, 1),
    /* 群组管理 */
    (1001007004000, 1001007000000, 'organization:group', '群组管理', '', 'MENU', 4, 1),
    (1001007004001, 1001007004000, 'organization:group:view', '查看', '', 'RESOURCE', 1, 1),
    (1001007004002, 1001007004000, 'organization:group:add', '添加', '', 'RESOURCE', 1, 1),
    (1001007004003, 1001007004000, 'organization:group:edit', '编辑', '', 'RESOURCE', 1, 1),
    (1001007004004, 1001007004000, 'organization:group:delete', '删除', '', 'RESOURCE', 1, 1),
    /* -------------------------------------------------------------------------------------------------------------- */
    /* 系统设置 */
    /* -------------------------------------------------------------------------------------------------------------- */
    (1001008000000, 0, 'system', '系统', '', 'CATALOG', 99, 1),
    /* 权限管理 */
    (1001008001000, 1001008000000, 'system:authority', '权限管理', '', 'MENU', 1, 1),
    (1001008001001, 1001008001000, 'system:authority:view', '查看', '', 'RESOURCE', 1, 1),
    (1001008001002, 1001008001000, 'system:authority:edit', '编辑', '', 'RESOURCE', 1, 1),
    /* 角色管理 */
    (1001008002000, 1001008000000, 'system:role', '角色管理', '', 'MENU', 2, 1),
    (1001008002001, 1001008002000, 'system:role:view', '查看', '', 'RESOURCE', 1, 1),
    (1001008002002, 1001008002000, 'system:role:add', '添加', '', 'RESOURCE', 1, 1),
    (1001008002003, 1001008002000, 'system:role:edit', '编辑', '', 'RESOURCE', 1, 1),
    (1001008002004, 1001008002000, 'system:role:delete', '删除', '', 'RESOURCE', 1, 1),
    /* 系统设置 */
    (1001008003000, 1001008000000, 'system:setting', '系统设置', '', 'MENU', 3, 1),
    (1001008003001, 1001008003000, 'system:setting:view', '查看', '', 'RESOURCE', 1, 1),
    (1001008003002, 1001008003000, 'system:setting:edit', '编辑', '', 'RESOURCE', 1, 1),
    /* 在线用户 */
    (1001008004000, 1001008000000, 'system:user', '在线用户', '', 'MENU', 4, 1),
    (1001008004001, 1001008004000, 'system:user:view', '查看', '', 'RESOURCE', 1, 1),
    (1001008004002, 1001008004000, 'system:user:delete', '删除', '', 'RESOURCE', 1, 1),
    /* 系统日志 */
    (1001008005000, 1001008000000, 'system:logging', '系统日志', '', 'MENU', 5, 1),
    (1001008005001, 1001008005000, 'system:logging:view', '查看', '', 'RESOURCE', 1, 1),
    (1001008005002, 1001008005000, 'system:logging:delete', '删除', '', 'RESOURCE', 1, 1),
    /* 定时任务 */
    (1001008006000, 1001008000000, 'system:task', '定时任务', '', 'MENU', 6, 1),
    (1001008006001, 1001008006000, 'system:task:view', '查看', '', 'RESOURCE', 1, 1),
    (1001008006002, 1001008006000, 'system:task:edit', '编辑', '', 'RESOURCE', 1, 1),
    /* 应用管理 */
    (1001008007000, 1001008000000, 'system:application', '应用管理', '', 'MENU', 7, 1),
    (1001008007001, 1001008007000, 'system:application:view', '查看', '', 'RESOURCE', 1, 1),
    (1001008007002, 1001008007000, 'system:application:delete', '删除', '', 'RESOURCE', 1, 1),
    /* 消息管理 */
    (1001008008000, 1001008000000, 'system:message', '消息管理', '', 'MENU', 7, 1),
    (1001008008001, 1001008008000, 'system:message:notice', '查看系统通知', '', 'RESOURCE', 1, 1),
    /* -------------------------------------------------------------------------------------------------------------- */
    /* 开发管理 */
    /* -------------------------------------------------------------------------------------------------------------- */
    (1001009000000, 0, 'development', '开发', '', 'CATALOG', 99, 1),
    /* 多语言文本 */
    (1001009001000, 1001009000000, 'resource:label', '多语言文本管理', '', 'MENU', 5, 1),
    (1001009001001, 1001009001000, 'resource:label:view', '查看', '', 'RESOURCE', 1, 1),
    (1001009001002, 1001009001000, 'resource:label:add', '添加', '', 'RESOURCE', 1, 1),
    (1001009001003, 1001009001000, 'resource:label:edit', '编辑', '', 'RESOURCE', 1, 1),
    (1001009001004, 1001009001000, 'resource:label:delete', '删除', '', 'RESOURCE', 1, 1);

--
-- 角色权限关联
--

truncate sys_role_authority;

insert into sys_role_authority (`id`, `role_id`, `authority_id`, `created_at`)
select concat(rpad(sr.id, 6, 0), rpad(sa.id, 13, 0)), sr.id, sa.id, now()
from sys_role sr,
     sys_authority sa
where sr.id = 1;

--
-- 系统设置项
--

truncate sys_config;

insert into sys_config (`id`, `config_key`, `config_value`, `label`, `description`, `active`)
values (1000001, 'APP_TITLE', 'Application', 'label_config_site_title', '站点标题', 1),
       (1000002, 'APP_COPYRIGHT', 'Copyright@2023', 'label_config_site_copyright', '站点版权信息', 1),
       (1000003, 'LOGIN_CAPTCHA_ENABLED', 'false', 'label_config_login_captcha_enabled', '是否启用登录验证码', 1);

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

insert into sys_label (`id`, `code`, `zh_label`, `en_label`)
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
values (1, 'TEST', 'cc.elvea.platform.system.job.task.TestJob', 1, 'period', 'm', 3);

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
