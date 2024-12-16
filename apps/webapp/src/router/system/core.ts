import { RouteRecordRaw } from 'vue-router';

export const coreRoutes: RouteRecordRaw[] = [
    {
        path: '',
        redirect: '/home',
    },
    {
        path: '/login',
        name: 'page-login',
        component: () => import('@/views/system/core/login.vue'),
        meta: {
            description: '登录页',
        },
    },
    {
        path: '/register',
        name: 'page-register',
        meta: {
            description: '注册页',
        },
        component: () => import('@/views/system/core/register.vue'),
    },
    {
        path: '/theme',
        meta: {
            description: '主题预览',
            locale: 'page.theme',
            requiresAuth: false,
            icon: '',
        },
        component: () => import('@/views/system/core/theme.vue'),
    },
    {
        path: '/',
        name: 'layout-main-wrapper',
        meta: {
            description: '主布局',
        },
        component: () => import('@/layouts/MainLayout.vue'),
        children: [
            {
                path: '/home',
                name: 'page-home',
                meta: {
                    description: '首页',
                    requiresAuth: true,
                },
                component: () => import('@/views/system/core/home.vue'),
            },
            {
                path: '/about',
                name: 'page-about',
                meta: {
                    description: '关于我们',
                    requiresAuth: true,
                },
                component: () => import('@/views/system/core/about.vue'),
            },
            {
                path: '401',
                name: 'page-401',
                component: () => import('@/views/system/core/401.vue'),
                meta: {
                    description: '没权限访问',
                    hidden: true,
                },
            },
        ],
    },
];
