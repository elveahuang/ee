import { announcementRoutes } from '@/router/system/announcement.ts';
import { coreRoutes } from '@/router/system/core.ts';
import { productRoutes } from '@/router/system/product.ts';
import { ssoRoutes } from '@/router/system/sso.ts';
import { userRoutes } from '@/router/system/user.ts';
import { RouteRecordRaw } from 'vue-router';

export const systemRoutes: RouteRecordRaw[] = [...coreRoutes, ...userRoutes, ...ssoRoutes, ...announcementRoutes, ...productRoutes];
