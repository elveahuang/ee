import { Key, PageParams, PageResult, R } from '@commons/core/types';
import { post } from '@commons/core/utils/http';

// ---------------------------------------------------------------------------------------------------------------------
// 在线用户
// ---------------------------------------------------------------------------------------------------------------------

export interface OnlineUsersListApiResult extends PageResult<OnlineUsers> {}

export interface OnlineUsersListApiParams extends PageParams {}

export const onlineUsersListApi = (params: OnlineUsersListApiParams): Promise<R<OnlineUsersListApiResult>> => {
    return post<R<OnlineUsersListApiResult>>('/api/v1/admin/user-session/list', params);
};

// ---------------------------------------------------------------------------------------------------------------------
// 获取在线用户详情
// ---------------------------------------------------------------------------------------------------------------------

export interface userSessionDetailsApiParams {
    id: number;
}

export const userSessionDetailsApi = (params: userSessionDetailsApiParams): Promise<R<OnlineUsers>> => {
    return post<R<OnlineUsers>>('/api/v1/admin/user-session/detail', params);
};

// ---------------------------------------------------------------------------------------------------------------------
// 删除公告资讯
// ---------------------------------------------------------------------------------------------------------------------

export interface SystemNoticeDeleteApiResult {}

export interface SystemNoticeDeleteApiParams {
    ids?: Key[];
}

export const systemNoticeDeleteApi = (params: SystemNoticeDeleteApiParams): Promise<R<SystemNoticeDeleteApiResult>> => {
    return post<R<SystemNoticeDeleteApiResult>>('/api/v1/admin/notice/delete', params);
};
