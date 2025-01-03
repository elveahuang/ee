import { R } from '@commons/core/types';
import { post } from '@commons/core/utils/http';

/**
 * 首页数据接口
 */
export class HomeApiResult {}

export const homeApi = (): Promise<R<HomeApiResult>> => {
    return post<R<HomeApiResult>>('/api/v1/home');
};
