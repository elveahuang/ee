import { RouteRecordRaw } from 'vue-router';

export const announcementRoutes: RouteRecordRaw[] = [
    {
        path: '/announcement',
        children: [
            {
                path: 'index',
                name: 'page-announcement-index',
                meta: {
                    description: '公告列表页面',
                    requiresAuth: true,
                },
                component: () => import('@/views/system/announcement/list.vue'),
            },
            {
                path: 'details',
                name: 'page-announcement-details',
                meta: {
                    description: '公告详情页面',
                    requiresAuth: true,
                },
                component: () => import('@/views/system/announcement/details.vue'),
            },
        ],
    },
];
