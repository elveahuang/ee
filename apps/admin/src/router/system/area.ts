import { RouteRecordRaw } from 'vue-router';

export const areaRoutes: RouteRecordRaw[] = [
    {
        path: 'area/index',
        name: 'page-area-index',
        meta: {
            requiresAuth: true,
            description: '仪表盘',
        },
        component: () => import('@/views/system/area/index.vue'),
    },
];
