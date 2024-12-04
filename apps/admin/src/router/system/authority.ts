import { RouteRecordRaw } from 'vue-router';

export const authorityRoutes: RouteRecordRaw[] = [
    {
        path: 'authority/index',
        name: 'page-authority-index',
        meta: {
            requiresAuth: true,
            authorities: ['workbench:workbench'],
            description: '仪表盘',
        },
        component: () => import('@/views/system/authority/index.vue'),
    },
];
