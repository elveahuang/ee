import { Announcement } from '@commons/core/types/announcement';
import { PageParams, PageResult, R } from '@commons/core/types/common';
import { post } from '@commons/core/utils/http';

// ---------------------------------------------------------------------------------------------------------------------
// 获取公告资讯列表
// ---------------------------------------------------------------------------------------------------------------------

export interface AnnouncementListApiResult extends PageResult<Announcement> {}

export interface AnnouncementListApiParams extends PageParams {}

export const announcementListApi = (params: AnnouncementListApiParams): Promise<R<AnnouncementListApiResult>> => {
    return post<R<AnnouncementListApiResult>>('/api/v1/announcement/list', params);
};

// ---------------------------------------------------------------------------------------------------------------------
// 获取公告资讯详情
// ---------------------------------------------------------------------------------------------------------------------

export interface AnnouncementDetailsApiParams {
    id: number;
}

export const announcementDetailsApi = (params: AnnouncementDetailsApiParams): Promise<R<Announcement>> => {
    return post<R<Announcement>>('/api/v1/announcement/details', params);
};
