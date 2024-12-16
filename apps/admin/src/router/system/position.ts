import { RouteRecordRaw } from 'vue-router';

export const positionRoutes: RouteRecordRaw[] = [
    {
        path: 'position/index',
        name: 'page-position-index',
        meta: {
            requiresAuth: true,
            authorities: ['workbench:workbench'],
            description: '仪表盘',
        },
        component: () => import('@/views/system/position/index.vue'),
    },
];
