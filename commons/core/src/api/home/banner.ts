import { PageParams, PageResult, R } from '@commons/core/types';
import { Banner } from '@commons/core/types/banner.ts';
import { post } from '@commons/core/utils/http';

// ---------------------------------------------------------------------------------------------------------------------
// 获取市场预警资讯列表
// ---------------------------------------------------------------------------------------------------------------------
export interface BannerListApiResult extends PageResult<Banner> {}

export interface BannerListApiParams extends PageParams {
    code: string;
    itemCodes: Array<string>;
}

export const bannerListApi = (params: BannerListApiParams): Promise<R<BannerListApiResult>> => {
    return post<R<BannerListApiResult>>('/api/v1/banner/list', params);
};
