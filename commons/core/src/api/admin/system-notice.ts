import { Key, PageParams, PageResult, R } from '@commons/core/types';
import { SystemNotice } from '@commons/core/types/system-notice';
import { post } from '@commons/core/utils/http';

// ---------------------------------------------------------------------------------------------------------------------
// 获取公告资讯列表
// ---------------------------------------------------------------------------------------------------------------------

export interface SystemNoticeListApiResult extends PageResult<SystemNotice> {}

export interface SystemNoticeListApiParams extends PageParams {}

export const systemNoticeListApi = (params: SystemNoticeListApiParams): Promise<R<SystemNoticeListApiResult>> => {
    return post<R<SystemNoticeListApiResult>>('/api/v1/admin/notice/list', params);
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
