import { RouteRecordRaw } from 'vue-router';

export const announcementRoutes: RouteRecordRaw[] = [
    {
        path: '/announcement',
        name: 'announcement-wrapper',
        meta: {
            description: '资讯主布局',
        },
        component: () => import('@/layouts/MainLayout.vue'),
        children: [
            {
                path: '',
                name: 'announcement-page-index',
                meta: {
                    description: '资讯首页',
                },
                component: () => import('@/views/system/announcement/index.vue'),
            },
            {
                path: '/details',
                name: 'announcement-page-details',
                meta: {
                    description: '资讯详情页',
                },
                component: () => import('@/views/system/announcement/details.vue'),
            },
        ],
    },
];
