import { usePageLoading } from '@commons/core/hooks';
import { whiteList } from '@commons/core/router';
import { useUserStore } from '@commons/core/store';
import { log } from '@commons/core/utils';
import { isArray, isEmpty, isString } from 'radash';
import { App } from 'vue';
import { NavigationGuardNext, RouteLocationNormalized, Router } from 'vue-router';

/**
 * 设置权限控制
 */
export const setupAuth = async (app: App, router: Router): Promise<void> => {
    log(`Router Auth initialize...`);

    const { pageLoadDone, pageLoadStart } = usePageLoading();

    router.beforeEach(async (to: RouteLocationNormalized, from: RouteLocationNormalized, next: NavigationGuardNext): Promise<void> => {
        log(`Router beforeEach...`);
        pageLoadStart();

        const { getUserInfo, clearUser, isAuthenticated, user } = useUserStore();

        if (isOpenRoute(to)) {
            next();
            pageLoadDone();
        } else {
            if (isAuthenticated) {
                if (to.path === '/login') {
                    await clearUser().then();
                    next();
                    pageLoadDone();
                } else {
                    if (isAuthenticated && user) {
                        await getUserInfo();
                    }
                    if (canAccessRoute(to)) {
                        next();
                    } else {
                        next(`/401?redirect=${to.path}`);
                        pageLoadDone();
                    }
                }
            } else {
                // 未登录可以访问白名单页面(登录页面)
                if (whiteList.indexOf(to.path) !== -1) {
                    next();
                } else {
                    next(`/login?redirect=${to.path}`);
                    pageLoadDone();
                }
            }
        }
    });

    router.afterEach(async (): Promise<void> => {
        log(`Router afterEach...`);
        pageLoadDone();
    });
};

/**
 * 检查当前路由是否是开放路由
 */
export const isOpenRoute = (route: RouteLocationNormalized) => {
    return !(route.meta && route.meta.requiresAuth);
};

/**
 * 检查当前用户是否有权限访问指定路由
 */
export const canAccessRoute = (route: RouteLocationNormalized): boolean => {
    const { isAuthenticated } = useUserStore();
    if (route.meta && route.meta.requiresAuth) {
        if (isEmpty(route.meta?.roles) && isEmpty(route.meta?.authorities)) {
            return isAuthenticated;
        }

        // 检查是否拥有指定角色
        let hasRole: boolean = false;
        if (route.meta && route.meta.roles && route.meta.roles.length && route.meta.roles.length > 0) {
            hasRole = hasAnyRole(route.meta.roles);
        }

        // 检查是否拥有指定权限
        let hasAuthority: boolean = false;
        if (route.meta && route.meta.authorities && route.meta.authorities.length && route.meta.authorities.length > 0) {
            hasAuthority = hasAnyAuthority(route.meta.authorities);
        }

        return hasRole || hasAuthority;
    }
    return true;
};

/**
 * 检查当前用户是否拥有指定角色
 */
export const hasAnyRole = (roles: string | Array<string>): boolean => {
    const { user, isAuthenticated } = useUserStore();

    let hasAnyRole: boolean = false;
    if (isAuthenticated && isArray(roles) && !isEmpty(roles)) {
        hasAnyRole = user?.roles?.some((role) => {
            return roles.includes(role);
        });
    } else if (isAuthenticated && isString(roles) && !isEmpty(roles)) {
        hasAnyRole = user?.roles?.includes(roles);
    }

    return hasAnyRole;
};

/**
 * 检查当前用户是否拥有指定权限
 */
export const hasAnyAuthority = (authorities: string | Array<string>): boolean => {
    const { user, isAuthenticated } = useUserStore();

    let hasAnyAuthority: boolean = false;
    if (isAuthenticated && isArray(authorities) && !isEmpty(authorities)) {
        hasAnyAuthority = user?.authorities?.some((role) => {
            return authorities.includes(role);
        });
    } else if (isAuthenticated && isString(authorities) && !isEmpty(authorities)) {
        hasAnyAuthority = user?.authorities?.includes(authorities);
    }

    return hasAnyAuthority;
};
