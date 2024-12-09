import { RouteRecordRaw } from 'vue-router';

export const productRoutes: RouteRecordRaw[] = [
    {
        path: '/product',
        name: 'product-layout-wrapper',
        meta: {
            description: '产品主布局',
        },
        component: () => import('@/layouts/MainLayout.vue'),
        children: [
            {
                path: '',
                name: 'page-product-index',
                meta: {
                    description: '首页',
                },
                component: () => import('@/views/system/product/index.vue'),
            },
            {
                path: '/details',
                name: 'page-product-details',
                meta: {
                    description: '关于我们',
                },
                component: () => import('@/views/system/product/details.vue'),
            },
        ],
    },
];
