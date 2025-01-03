import { log } from '@commons/core/utils';
import { canAccessRoute, setupAuth } from '@commons/core/utils/auth';
import { isArray, isEmpty, isEqual } from 'radash';
import { App } from 'vue';
import { RouteRecordRaw, createRouter, createWebHashHistory, createWebHistory } from 'vue-router';

/**
 * 路由自定义配置定义
 */
export class RouterConfig {
    mode?: string = 'history';
    base?: string = '';
    routes?: RouteRecordRaw[] = [];
    customWhiteList?: Array<string> = [];
}

/**
 * 路由配置
 */
export let config: RouterConfig = {};

/**
 * 路由实例
 */
export let router: ReturnType<typeof createRouter>;

/**
 * 全局白名单
 */
export let whiteList: Array<string> = ['/login'];

/**
 * 初始化
 */
export const setupRouter = async (app: App, routerConfig?: RouterConfig): Promise<void> => {
    log(`Router initialize...`);
    config = routerConfig;
    // 初始化路由实例
    router = createRouter({
        routes: config.routes,
        history: isEqual(config.mode, 'hash') ? createWebHashHistory(config.base) : createWebHistory(config.base),
        scrollBehavior: () => ({ left: 0, top: 0 }),
    });
    // 设置白名单
    if (!isEmpty(config.customWhiteList)) {
        whiteList = config.customWhiteList;
    }
    // 设置路由守卫
    await setupAuth(app, router);
    //
    app.use(router);
};

/**
 * 路由合并去重
 */
export const mergeGroupRoutes = (targetRoutes: RouteRecordRaw[] = [], sourceRoutes: RouteRecordRaw[] = []): RouteRecordRaw[] => {
    const routes: RouteRecordRaw[] = [];
    if (isArray(routes) && isArray(sourceRoutes)) {
        targetRoutes.forEach((targetRoute: RouteRecordRaw): void => {
            const matchRoutes: RouteRecordRaw[] = sourceRoutes.filter((sourceRoute: RouteRecordRaw) => isEqual(sourceRoute.path, targetRoute.path));
            if (isArray(matchRoutes)) {
                matchRoutes.forEach((matchRoute: RouteRecordRaw): void => {
                    targetRoute = mergeRoute(targetRoute, matchRoute);
                });
            }
            routes.push(targetRoute);
        });
        sourceRoutes.forEach((sourceRoute: RouteRecordRaw): void => {
            if (targetRoutes.findIndex((targetRoute: RouteRecordRaw) => isEqual(sourceRoute.path, targetRoute.path)) < 0) {
                routes.push(sourceRoute);
            }
        });
    }
    return routes;
};

/**
 * 路由合并去重
 */
export const mergeRoute = (targetRoute: RouteRecordRaw, sourceRoute: RouteRecordRaw): RouteRecordRaw => {
    const route: RouteRecordRaw = { ...targetRoute, ...sourceRoute } as RouteRecordRaw;
    if (targetRoute.children || sourceRoute.children) {
        route.children = mergeGroupRoutes(targetRoute.children, sourceRoute.children);
    }
    return route;
};

/**
 * 路由合并去重
 */
export const mergeRoutes = (...sourceRoutesArgs: any): RouteRecordRaw[] => {
    let routes: RouteRecordRaw[] = [];
    if (sourceRoutesArgs && sourceRoutesArgs.length && sourceRoutesArgs.length > 0) {
        sourceRoutesArgs.forEach((sourceRoutes: RouteRecordRaw[]): void => {
            routes = mergeGroupRoutes(routes, sourceRoutes);
        });
    }
    return routes;
};

export { canAccessRoute, setupAuth };
