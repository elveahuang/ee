import { RouteRecordRaw } from 'vue-router';

export const tabsRoutes: RouteRecordRaw[] = [
    {
        path: '/tabs/',
        component: () => import('@/views/system/core/tabs.vue'),
        children: [
            {
                path: 'home',
                component: () => import('@/views/system/core/home.vue'),
                meta: {
                    description: '首页',
                    locale: 'page.login',
                    requiresAuth: false,
                },
            },
            {
                path: 'discover',
                component: () => import('@/views/system/core/discover.vue'),
                meta: {
                    description: '首页',
                    locale: 'page.login',
                    requiresAuth: false,
                },
            },
            {
                path: 'me',
                component: () => import('@/views/system/core/me.vue'),
                meta: {
                    description: '首页',
                    locale: 'page.login',
                    requiresAuth: false,
                },
            },
        ],
    },
];
