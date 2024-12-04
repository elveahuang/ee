import { RouteRecordRaw } from 'vue-router';

export const demoRoutes: RouteRecordRaw[] = [
    {
        path: '/demo',
        name: 'wrapper-demo',
        meta: {
            description: '开发示例专用',
            requiresAuth: false,
        },
        children: [
            {
                path: '',
                name: 'page-demo-index',
                component: () => import('@/views/extend/demo/index.vue'),
            },
            {
                path: 'slider',
                name: 'page-demo-slider',
                component: () => import('@/views/extend/demo/slider.vue'),
            },
            {
                path: 'editor',
                name: 'page-demo-editor',
                component: () => import('@/views/extend/demo/editor.vue'),
            },
            {
                path: 'player',
                name: 'page-demo-player',
                component: () => import('@/views/extend/demo/player.vue'),
            },
        ],
    },
];
