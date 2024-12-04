import { R } from '@commons/core/types';
import { ChartVo } from '@commons/core/types/echarts.ts';
import { get } from '@commons/core/utils/http';

/**
 * 登录终端来源折线图
 */
export declare type LoginPlatformLineChartApiRequest = {
    type: number;
    time: string;
    goHeavy: boolean;
};
export const loginPlatformLineChartApi = (params: LoginPlatformLineChartApiRequest): Promise<R<ChartVo>> => {
    return get<R<ChartVo>>('/api/v1/admin/login-platform-line-chart', params);
};
