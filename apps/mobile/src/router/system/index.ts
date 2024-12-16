import { announcementRoutes } from '@/router/system/announcement';
import { coreRoutes } from '@/router/system/core';
import { noticeRoutes } from '@/router/system/notice';
import { tabsRoutes } from '@/router/system/tabs';
import { RouteRecordRaw } from 'vue-router';

export const systemRoutes: RouteRecordRaw[] = [...coreRoutes, ...tabsRoutes, ...announcementRoutes, ...noticeRoutes];
