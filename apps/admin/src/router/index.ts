import { extendRoutes } from '@/router/extend';
import { systemRoutes } from '@/router/system';
import { mergeRoutes } from '@commons/core/router';
import { RouteRecordRaw } from 'vue-router';

export const routes: RouteRecordRaw[] = mergeRoutes(systemRoutes, extendRoutes);
