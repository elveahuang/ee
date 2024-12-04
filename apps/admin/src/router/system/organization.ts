import { RouteRecordRaw } from 'vue-router';

export const organizationRoutes: RouteRecordRaw[] = [
    {
        path: 'organization/index',
        name: 'page-organization-index',
        meta: {
            requiresAuth: true,
            authorities: ['workbench:workbench'],
            description: '仪表盘',
        },
        component: () => import('@/views/system/organization/index.vue'),
    },
];
