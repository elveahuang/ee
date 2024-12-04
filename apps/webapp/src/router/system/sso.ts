import { RouteRecordRaw } from 'vue-router';

export const ssoRoutes: RouteRecordRaw[] = [
    {
        path: '/sso',
        name: 'sso-wrapper',
        meta: {
            description: '单点登录',
            requiresAuth: false,
        },
        children: [
            {
                path: 'wechat',
                name: 'sso-wechat',
                meta: {
                    description: '微信公众号单点登录',
                    requiresAuth: false,
                },
                component: () => import('@/views/system/core/wechat.vue'),
            },
            {
                path: 'wework',
                name: 'sso-wework',
                meta: {
                    description: '企微微信单点登录',
                    requiresAuth: false,
                },
                component: () => import('@/views/system/core/wework.vue'),
            },
            {
                path: 'lark',
                name: 'sso-lark',
                meta: {
                    description: '飞书单点登录',
                    requiresAuth: false,
                },
                component: () => import('@/views/system/core/lark.vue'),
            },
        ],
    },
];
