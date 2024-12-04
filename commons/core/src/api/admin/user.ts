import { Key, PageParams, PageResult, R, User } from '@commons/core/types';
import { UserEntity } from '@commons/core/types/user.type.ts';
import { post } from '@commons/core/utils/http';

// ---------------------------------------------------------------------------------------------------------------------
// 获取用户列表
// ---------------------------------------------------------------------------------------------------------------------

export interface UserListApiResult extends PageResult<UserEntity> {}

export interface UserListApiParams extends PageParams {}

export const userListApi = (params: UserListApiParams): Promise<R<UserListApiResult>> => {
    return post<R<UserListApiResult>>('/api/v1/admin/user/list', params);
};

// ---------------------------------------------------------------------------------------------------------------------
// 获取用户详情
// ---------------------------------------------------------------------------------------------------------------------

export interface UserDetailsApiParams {
    id: number | string;
}

export const userDetailsApi = (params: UserDetailsApiParams): Promise<R<User>> => {
    return post<R<User>>('/api/v1/admin/user/details', params);
};

// 修改密码
export interface ResetPasswordApiParams {
    id: number | string;
    password: string;
}

export const resetPasswordApi = (params: UserEntity): Promise<R<User>> => {
    return post<R<User>>('/api/v1/admin/user/reset/password', params);
};

// ---------------------------------------------------------------------------------------------------------------------
// 保存用户
// ---------------------------------------------------------------------------------------------------------------------

export interface UserSaveApiResult {}

export const userSaveApi = (params: UserEntity): Promise<R<UserSaveApiResult>> => {
    return post<R<UserSaveApiResult>>('/api/v1/admin/user/save', params);
};

// ---------------------------------------------------------------------------------------------------------------------
// 删除用户
// ---------------------------------------------------------------------------------------------------------------------

export interface UserDeleteApiResult {}

export interface UserDeleteApiParams {
    ids?: Key[];
}

export const userDeleteApi = (params: UserDeleteApiParams): Promise<R<UserDeleteApiResult>> => {
    return post<R<UserDeleteApiResult>>('/api/v1/admin/user/delete', params);
};
