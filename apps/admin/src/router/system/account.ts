import { RouteRecordRaw } from 'vue-router';

export const accountRoutes: RouteRecordRaw[] = [
    {
        path: 'account/index',
        name: 'page-vip-index',
        meta: {
            requiresAuth: false,
            authorities: ['site:workbench'],
            description: 'account',
        },
        component: () => import('@/views/system/account/index.vue'),
    },
];
