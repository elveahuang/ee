import { RouteRecordRaw } from 'vue-router';

export const announcementRoutes: RouteRecordRaw[] = [
    {
        path: 'announcement/index',
        name: 'page-announcement-index',
        meta: {
            requiresAuth: false,
            authorities: ['site:workbench'],
            description: '公告管理列表页',
        },
        component: () => import('@/views/system/announcement/index.vue'),
    },
    {
        path: 'announcement/details',
        name: 'page-announcement-details',
        meta: {
            requiresAuth: false,
            authorities: ['site:workbench'],
            description: '产品管理详情页',
        },
        component: () => import('@/views/system/announcement/details.vue'),
    },
];
