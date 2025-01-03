import { RouteRecordRaw } from 'vue-router';

export const coreRoutes: RouteRecordRaw[] = [
    {
        path: '/',
        redirect: '/tabs/home',
    },
    {
        path: '/register',
        component: () => import('@/views/system/user/register.vue'),
        meta: {
            description: '注册页面',
            requiresAuth: false,
        },
    },
    {
        path: '/login',
        component: () => import('@/views/system/user/login.vue'),
        meta: {
            description: '登录页面',
            requiresAuth: false,
        },
    },
    {
        path: '/find-password',
        meta: {
            description: '找回密码',
            requiresAuth: false,
        },
        component: () => import('@/views/system/user/find-password.vue'),
    },
    {
        path: '/change-password',
        meta: {
            description: '修改密码',
            requiresAuth: true,
        },
        component: () => import('@/views/system/user/change-password.vue'),
    },
    {
        path: '/change-email',
        meta: {
            description: '修改邮箱',
            requiresAuth: true,
        },
        component: () => import('@/views/system/user/change-email.vue'),
    },
    {
        path: '/me',
        meta: {
            description: '个人中心',
            requiresAuth: false,
        },
        component: () => import('@/views/system/core/me.vue'),
    },
    {
        path: '/vip',
        meta: {
            description: '会员中心',
            requiresAuth: false,
        },
        component: () => import('@/views/system/core/vip.vue'),
    },
    {
        path: '/vip-checkout',
        meta: {
            description: '会员续费',
            requiresAuth: false,
        },
        component: () => import('@/views/system/core/vip-checkout.vue'),
    },
    {
        path: '/me-account',
        meta: {
            description: '个人信息',
            requiresAuth: true,
        },
        component: () => import('@/views/system/core/me-account.vue'),
    },
    {
        path: '/about',
        meta: {
            description: '关于',
            requiresAuth: false,
        },
        component: () => import('@/views/system/core/about.vue'),
    },
    {
        path: '/setting',
        meta: {
            description: '设置',
            requiresAuth: false,
        },
        component: () => import('@/views/system/core/setting.vue'),
    },
    {
        path: '/redirect',
        name: 'redirectWrapper',
        meta: {
            description: '重定向页面',
            requiresAuth: false,
        },
        children: [
            {
                path: '/redirect/:path',
                component: () => import('@/views/system/core/redirect.vue'),
                meta: {
                    description: '重定向页面',
                    requiresAuth: false,
                },
            },
        ],
    },
    {
        path: '/401',
        name: 'page-401',
        component: () => import('@/views/system/core/401.vue'),
        meta: {
            description: '401',
            requiresAuth: false,
        },
    },
    {
        path: '/:pathMatch(.*)*',
        name: 'notFound',
        meta: {
            description: '404',
            requiresAuth: false,
        },
        component: () => import('@/views/system/core/not-found.vue'),
    },
];
