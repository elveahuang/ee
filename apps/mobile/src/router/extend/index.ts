import { demoRoutes } from '@/router/extend/demo.ts';
import { RouteRecordRaw } from 'vue-router';

export const extendRoutes: RouteRecordRaw[] = [...demoRoutes];
