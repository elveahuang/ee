import { RouteRecordRaw } from 'vue-router';

export const noticeRoutes: RouteRecordRaw[] = [
    {
        path: '/notifications',
        children: [
            {
                path: 'index',
                name: 'page-notice-index',
                meta: {
                    description: '消息列表页面',
                    requiresAuth: true,
                },
                component: () => import('@/views/system/massage/index.vue'),
            },
            {
                path: 'details',
                name: 'page-notice-details',
                meta: {
                    description: '消息详情页面',
                    requiresAuth: true,
                },
                component: () => import('@/views/system/massage/details.vue'),
            },
        ],
    },
];
