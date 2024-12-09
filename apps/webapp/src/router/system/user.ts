import { RouteRecordRaw } from 'vue-router';

export const userRoutes: RouteRecordRaw[] = [
    {
        path: '/user-center',
        redirect: '/user-center/index',
    },
    {
        path: '/user-center',
        name: 'user-center-layout',
        component: () => import('@/layouts/UserLayout.vue'),
        meta: {
            description: '用户中心布局',
        },
        children: [
            {
                path: 'index',
                name: 'user-center-index-page',
                meta: {
                    requiresAuth: true,
                    description: '用户中心-首页',
                },
                component: () => import('@/views/system/user-center/index.vue'),
            },
            {
                path: 'notice',
                name: 'user-center-notice-page',
                meta: {
                    requiresAuth: true,
                    description: '用户中心-系统通知页面',
                },
                component: () => import('@/views/system/user-center/notice.vue'),
            },
            {
                path: 'account',
                name: 'page-user-account-account',
                meta: {
                    requiresAuth: true,
                    description: '用户中心-个人资料页面',
                },
                component: () => import('@/views/system/user-center/account.vue'),
            },
            {
                path: 'password',
                name: 'user-center-password-page',
                meta: {
                    requiresAuth: true,
                    description: '用户中心-修改密码页面',
                },
                component: () => import('@/views/system/user-center/password.vue'),
            },
        ],
    },
];
