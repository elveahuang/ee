import { PageParams, PageResult, R } from '@commons/core/types/common';
import { Notice } from '@commons/core/types/notice.ts';
import { post } from '@commons/core/utils/http';

// ---------------------------------------------------------------------------------------------------------------------
// 获取系统通知列表
// ---------------------------------------------------------------------------------------------------------------------

export interface NoticeListApiResult extends PageResult<Notice> {}

export interface NoticeListApiParams extends PageParams {}

export const noticeListApi = (params: NoticeListApiParams): Promise<R<NoticeListApiResult>> => {
    return post<R<NoticeListApiResult>>('/api/v1/notice/list', params);
};

// ---------------------------------------------------------------------------------------------------------------------
// 获取系统通知详情
// ---------------------------------------------------------------------------------------------------------------------

export interface NoticeDetailsApiParams {
    id: number;
}

export const noticeDetailsApi = (params: NoticeDetailsApiParams): Promise<R<Notice>> => {
    return post<R<Notice>>('/api/v1/notice/details', params);
};
