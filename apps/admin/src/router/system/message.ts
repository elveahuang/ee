import { RouteRecordRaw } from 'vue-router';

export const messageRoutes: RouteRecordRaw[] = [
    {
        path: 'message/notice/index',
        name: 'message-center-notice-index-page',
        meta: {
            requiresAuth: true,
            description: '系统通知',
        },
        component: () => import('@/views/system/message/notice.vue'),
    },
];
