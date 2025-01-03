import { accountRoutes } from '@/router/system/account';
import { announcementRoutes } from '@/router/system/announcement';
import { areaRoutes } from '@/router/system/area';
import { attachmentRoutes } from '@/router/system/attachment';
import { authorityRoutes } from '@/router/system/authority';
import { posterRoutes } from '@/router/system/banner.ts';
import { coreRoutes } from '@/router/system/core.ts';
import { dictRoutes } from '@/router/system/dict.ts';
import { groupRoutes } from '@/router/system/group';
import { messageRoutes } from '@/router/system/message';
import { organizationRoutes } from '@/router/system/organization';
import { positionRoutes } from '@/router/system/position';
import { productRoutes } from '@/router/system/product';
import { roleRoutes } from '@/router/system/role';
import { tagRoutes } from '@/router/system/tag';
import { userRoutes } from '@/router/system/user';
import { userCenterRoutes } from '@/router/system/user-center';
import { userSessionRoutes } from '@/router/system/user-session';
import { RouteRecordRaw } from 'vue-router';

export const systemRoutes: RouteRecordRaw[] = [
    {
        path: '/',
        meta: {
            requiresAuth: false,
            description: '主体布局',
        },
        component: () => import('@/layouts/MainLayout.vue'),
        children: [
            ...userRoutes,
            ...roleRoutes,
            ...authorityRoutes,
            ...organizationRoutes,
            ...positionRoutes,
            ...groupRoutes,
            ...attachmentRoutes,
            ...dictRoutes,
            ...tagRoutes,
            ...areaRoutes,
            ...announcementRoutes,
            ...posterRoutes,
            ...productRoutes,
            ...messageRoutes,
            ...userSessionRoutes,
            ...accountRoutes,
            ...coreRoutes,
        ],
    },
    {
        path: '/user-center',
        meta: {
            requiresAuth: true,
            description: '用户中心布局',
        },
        component: () => import('@/layouts/UserLayout.vue'),
        children: [...userCenterRoutes],
    },
    {
        path: '/login',
        name: 'page-login',
        meta: {
            description: '登录页',
        },
        component: () => import('@/views/system/core/login.vue'),
    },
    {
        path: '/redirect/:path(.*)',
        meta: { hidden: true },
        component: () => import('@/views/system/core/redirect.vue'),
    },
    {
        path: '/401',
        name: 'page-401',
        component: () => import('@/views/system/core/401.vue'),
        meta: { hidden: true },
    },
    {
        path: '/404',
        name: 'page-404',
        component: () => import('@/views/system/core/404.vue'),
        meta: { hidden: true },
    },
    {
        path: '/:pathMatch(.*)',
        component: () => import('@/views/system/core/404.vue'),
        meta: { hidden: true },
    },
];
