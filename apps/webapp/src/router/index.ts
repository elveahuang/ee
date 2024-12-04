import { systemRoutes } from '@/router/system';
import { mergeRoutes } from '@commons/core/router';
import { extendRoutes } from 'src/router/extend';
import { RouteRecordRaw } from 'vue-router';

export const routes: RouteRecordRaw[] = mergeRoutes(systemRoutes, extendRoutes);
