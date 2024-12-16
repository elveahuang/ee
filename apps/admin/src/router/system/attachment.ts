import { RouteRecordRaw } from 'vue-router';

export const attachmentRoutes: RouteRecordRaw[] = [
    {
        path: 'attachment/index',
        name: 'page-attachment-index',
        meta: {
            requiresAuth: true,
            authorities: ['workbench:workbench'],
            description: '仪表盘',
        },
        component: () => import('@/views/system/attachment/index.vue'),
    },
];
