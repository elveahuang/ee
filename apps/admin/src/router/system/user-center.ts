import { RouteRecordRaw } from 'vue-router';

export const userCenterRoutes: RouteRecordRaw[] = [
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
    {
        path: 'email',
        name: 'user-center-email-page',
        meta: {
            requiresAuth: true,
            description: '用户中心-修改邮箱页面',
        },
        component: () => import('@/views/system/user-center/email.vue'),
    },
];
