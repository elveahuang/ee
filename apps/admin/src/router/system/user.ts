import { RouteRecordRaw } from 'vue-router';

export const userRoutes: RouteRecordRaw[] = [
    {
        path: 'index',
        name: 'page-user-index',
        meta: {
            requiresAuth: true,
            description: '用户管理',
        },
        component: () => import('@/views/system/user/index.vue'),
    },
];
