import { Key, PageParams, PageResult, R } from '@commons/core/types';
import { Announcement } from '@commons/core/types/announcement';
import { post } from '@commons/core/utils/http'; // ---------------------------------------------------------------------------------------------------------------------

// ---------------------------------------------------------------------------------------------------------------------
// 获取公告资讯列表
// ---------------------------------------------------------------------------------------------------------------------

export class AnnouncementListApiResult extends PageResult<Announcement> {}

export class AnnouncementListApiParams extends PageParams {}

export const announcementListApi = (params: AnnouncementListApiParams): Promise<R<AnnouncementListApiResult>> => {
    return post<R<AnnouncementListApiResult>>('/api/v1/admin/announcement/list', params);
};

// ---------------------------------------------------------------------------------------------------------------------
// 获取公告资讯详情
// ---------------------------------------------------------------------------------------------------------------------

export interface AnnouncementDetailsApiParams {
    id: number;
}

export const announcementDetailsApi = (params: AnnouncementDetailsApiParams): Promise<R<Announcement>> => {
    return post<R<Announcement>>('/api/v1/admin/announcement/details', params);
};

// ---------------------------------------------------------------------------------------------------------------------
// 保存公告资讯
// ---------------------------------------------------------------------------------------------------------------------

export interface AnnouncementSaveApiResult {}

export const announcementSaveApi = (params: Announcement): Promise<R<AnnouncementSaveApiResult>> => {
    return post<R<AnnouncementSaveApiResult>>('/api/v1/admin/announcement/save', params);
};

// ---------------------------------------------------------------------------------------------------------------------
// 删除公告资讯
// ---------------------------------------------------------------------------------------------------------------------

export interface AnnouncementDeleteApiResult {}

export interface AnnouncementDeleteApiParams {
    ids?: Key[];
}

export const announcementDeleteApi = (params: AnnouncementDeleteApiParams): Promise<R<AnnouncementDeleteApiResult>> => {
    return post<R<AnnouncementDeleteApiResult>>('/api/v1/admin/announcement/delete', params);
};
