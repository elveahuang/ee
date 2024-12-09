import { RouteRecordRaw } from 'vue-router';

export const demoRoutes: RouteRecordRaw[] = [
    {
        path: '/demo',
        name: 'page-demo-index',
        meta: {
            requiresAuth: false,
            description: '示例首页',
        },
        component: () => import('@/views/extend/demo/index.vue'),
    },
    {
        path: '/demo/editor',
        name: 'page-demo-editor',
        meta: {
            requiresAuth: false,
            description: '富文本编辑器示例',
        },
        component: () => import('@/views/extend/demo/editor.vue'),
    },
    {
        path: '/demo/player',
        name: 'page-demo-player',
        meta: {
            requiresAuth: false,
            description: '媒体播放器示例',
        },
        component: () => import('@/views/extend/demo/player.vue'),
    },
    {
        path: '/demo/builder',
        name: 'page-demo-builder',
        meta: {
            requiresAuth: false,
            description: '可视化编辑器示例',
        },
        component: () => import('@/views/extend/demo/builder.vue'),
    },
    {
        path: '/demo/banner',
        name: 'page-demo-banner',
        meta: {
            requiresAuth: false,
            description: '宣传栏',
        },
        component: () => import('@/views/extend/demo/banner.vue'),
    },
];
