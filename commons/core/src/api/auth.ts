import { env } from '@commons/core/env';
import { R, User } from '@commons/core/types';
import { get, post } from '@commons/core/utils/http';
import storage from '@commons/core/utils/storage';

/**
 * 刷新凭证
 */
export type RefreshApiResult = R & {
    token_type: string;
    access_token: string;
    refresh_token: string;
    expires_in: number;
};

export type RefreshApiParams = {
    grant_type?: string;
    type?: string;
    client_id?: string;
    client_secret?: string;
    refresh_token?: string;
};

export const refreshApi = (): Promise<RefreshApiResult> => {
    const params: RefreshApiParams = {
        refresh_token: storage.getRefreshToken(),
    };
    if (env.auth.oauth.enabled) {
        params.grant_type = 'refresh_token';
        params.type = env.auth.user.type;
        params.client_id = env.auth.oauth.clientId;
        params.client_secret = env.auth.oauth.clientSecret;
    }
    return post<RefreshApiResult>('/oauth/token', params);
};

/**
 * 用户登录
 */
export type LoginApiResult = R & {
    token_type: string;
    access_token: string;
    refresh_token: string;
    expires_in: number;
};

export type LoginApiParams = {
    grant_type?: string;
    type?: string;
    client_id?: string;
    client_secret?: string;
    username?: string;
    password?: string;
    scope?: string | string[];
};

export const loginApi = async (params: LoginApiParams): Promise<LoginApiResult> => {
    if (env.auth.oauth.enabled) {
        params.grant_type = 'password';
        params.scope = 'openid';
        params.type = env.auth.user.type;
        params.client_id = env.auth.oauth.clientId;
        params.client_secret = env.auth.oauth.clientSecret;
    }
    return post<LoginApiResult>('/oauth/token', params);
};

/**
 * 用户退出登录
 */
export const LogoutApiPath: string = env.auth.user.type === 'account' ? '/api/v1/account/logout' : '/api/v1/user/logout';

export type LogoutApiResult = {};

export const logoutApi = (): Promise<R<LogoutApiResult>> => {
    return post<R<LogoutApiResult>>(LogoutApiPath);
};

/**
 * 获取用户信息
 */
export const UserInfoApiPath: string = env.auth.user.type === 'account' ? '/api/v1/account' : '/api/v1/user';

export const userInfoApi = (): Promise<R<User>> => {
    return get<R<User>>(UserInfoApiPath);
};
