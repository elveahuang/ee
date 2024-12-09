import { Key, PageParams, PageResult, R } from '@commons/core/types';
import { Banner } from '@commons/core/types/banner.ts';
import { post, postJson } from '@commons/core/utils/http.ts';

export interface BannerListApiResult extends PageResult<Banner> {}

export interface BannerListApiParams extends PageParams {}

export const bannerListApi = (params: BannerListApiParams): Promise<R<BannerListApiResult>> => {
    return post<R<BannerListApiResult>>('/api/v1/admin/banner/list', params);
};

export interface BannerSaveApiResult {}

export const bannerSaveApi = (params: Banner): Promise<R<BannerSaveApiResult>> => {
    return postJson<R<BannerSaveApiResult>>('/api/v1/admin/banner/save', params);
};

export interface BannerDetailsApiParams {
    id: number;
}
export const bannerDetailsApi = (params: BannerDetailsApiParams): Promise<R<Banner>> => {
    return post<R<Banner>>('/api/v1/admin/banner/details', params);
};

export interface BannerDeleteApiResult {}

export interface BannerDeleteApiParams {
    ids?: Key[];
}
export const bannerDeleteApi = (params: BannerDeleteApiParams): Promise<R<BannerDeleteApiResult>> => {
    return post<R<BannerDeleteApiResult>>('/api/v1/admin/banner/delete', params);
};
