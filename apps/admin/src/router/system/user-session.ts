import { RouteRecordRaw } from 'vue-router';

export const userSessionRoutes: RouteRecordRaw[] = [
    {
        path: 'user-session/index',
        name: 'online-users-center-index-page',
        meta: {
            requiresAuth: true,
            description: '在线用户',
        },
        component: () => import('@/views/system/user-session/index.vue'),
    },
];
