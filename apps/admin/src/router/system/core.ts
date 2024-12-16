import { RouteRecordRaw } from 'vue-router';

export const coreRoutes: RouteRecordRaw[] = [
    {
        path: 'workbench',
        name: 'page-workbench',
        meta: {
            requiresAuth: true,
            description: '工作台',
        },
        component: () => import('@/views/system/core/workbench.vue'),
    },
    {
        path: 'dashboard',
        name: 'page-dashboard',
        meta: {
            requiresAuth: true,
            description: '仪表盘',
        },
        component: () => import('@/views/system/core/dashboard.vue'),
    },
    {
        path: 'dashboard/analysis',
        name: 'page-dashboard-analysis',
        meta: {
            requiresAuth: true,
            description: '数据分析',
        },
        component: () => import('@/views/system/core/dashboard-analysis.vue'),
    },
    {
        path: 'dashboard/monitor',
        name: 'page-dashboard-monitor',
        meta: {
            requiresAuth: true,
            description: '系统监控',
        },
        component: () => import('@/views/system/core/dashboard-monitor.vue'),
    },
    {
        path: '',
        redirect: '/dashboard',
    },
];
