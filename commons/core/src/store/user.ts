import {
    loginApi,
    LoginApiParams,
    LoginApiResult,
    logoutApi,
    LogoutApiResult,
    refreshApi,
    RefreshApiResult,
    userInfoApi,
} from '@commons/core/api/auth';
import { store } from '@commons/core/store';
import { R } from '@commons/core/types';
import { User } from '@commons/core/types/user';
import storage from '@commons/core/utils/storage';
import { defineStore } from 'pinia';
import { isEmpty } from 'radash';

export interface UserState {
    accessToken: string;
    refreshToken: string;
    user: User;
}

export const useUserStore = defineStore('user', {
    persist: true,
    state: (): UserState => ({
        accessToken: null,
        refreshToken: null,
        user: null,
    }),
    getters: {
        /**
         * 是否匿名用户
         */
        isAnonymous: (state: UserState) => isEmpty(state.accessToken),
        /**
         * 是否已登录用户
         */
        isAuthenticated: (state: UserState) => !isEmpty(state.accessToken),
        /**
         * 获取用户名
         */
        username: (state: UserState) => state.user?.username,
        /**
         * 获取用户ID
         */
        id: (state: UserState) => state.user?.id,
    },
    actions: {
        /**
         * 设置访问凭证
         */
        setAccessToken(accessToken: string): void {
            this.accessToken = accessToken;
            storage.setAccessToken(accessToken);
        },
        /**
         * 设置刷新凭证
         */
        setRefreshToken(refreshToken: string): void {
            this.refreshToken = refreshToken;
            storage.setRefreshToken(refreshToken);
        },
        /**
         * 设置用户信息
         */
        setUser(user: User): void {
            this.user = user;
        },
        /**
         * 用户登录
         */
        async login(params: LoginApiParams): Promise<LoginApiResult> {
            return loginApi(params).then((result: LoginApiResult): LoginApiResult => {
                if (result.access_token && result.refresh_token) {
                    this.setAccessToken(result.access_token);
                    this.setRefreshToken(result.refresh_token);
                }
                return result;
            });
        },
        /**
         * 刷新凭证
         */
        async refresh(): Promise<RefreshApiResult> {
            return refreshApi().then((result: RefreshApiResult): RefreshApiResult => {
                if (result.access_token && result.refresh_token) {
                    this.setAccessToken(result.access_token);
                    this.setRefreshToken(result.refresh_token);
                }
                return result;
            });
        },
        /**
         * 退出登录
         */
        async logout(): Promise<LogoutApiResult> {
            return logoutApi().then(async (result: R<LogoutApiResult>): Promise<LogoutApiResult> => {
                await this.clearUser();
                return result;
            });
        },
        /**
         * 根据凭证获取用户信息
         */
        async getUserInfo(): Promise<R<User>> {
            return userInfoApi().then((result: R<User>): R<User> => {
                if (result.code == '200') {
                    this.setUser(result.data);
                }
                return result;
            });
        },
        /**
         * 清除所有用户状态信息
         */
        async clearUser(): Promise<void> {
            try {
                this.setAccessToken(null);
                this.setRefreshToken(null);
                this.setUser(null);
                storage.clear();
                return Promise.resolve();
            } catch (e) {
                return Promise.reject(e);
            }
        },
    },
});

export const useUserStoreExternal = () => {
    return useUserStore(store);
};
