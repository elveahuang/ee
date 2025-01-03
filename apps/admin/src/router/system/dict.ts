import { RouteRecordRaw } from 'vue-router';

export const dictRoutes: RouteRecordRaw[] = [
    {
        path: 'dict/index',
        name: 'page-dict-index',
        meta: {
            requiresAuth: false,
            authorities: ['resource:dictionary'],
            description: '字典管理列表页',
        },
        component: () => import('@/views/system/dict/index.vue'),
    },
    {
        path: 'dict/items',
        name: 'page-dict-items',
        meta: {
            requiresAuth: false,
            authorities: ['resource:dictionary'],
            description: '字典管理详情页',
        },
        component: () => import('@/views/system/dict/items.vue'),
    },
];
