import { RouteRecordRaw } from 'vue-router';

export const posterRoutes: RouteRecordRaw[] = [
    {
        path: 'banner/index',
        name: 'page-poster-index',
        meta: {
            requiresAuth: false,
            authorities: ['site:poster'],
            description: '宣传栏管理列表页',
        },
        component: () => import('@/views/system/banner/index.vue'),
    },
];
