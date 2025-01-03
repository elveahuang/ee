import { R } from '@commons/core/types';
import { PayType, VipType } from '@commons/core/types/mall.ts';
import { post } from '@commons/core/utils/http';

/**
 * 获取当前可用支付方式
 */
export interface PayTypeListParams {}

export const payTypeListApi = (params: PayTypeListParams = {}): Promise<R<PayType[]>> => {
    return post<R<PayType[]>>('/api/v1/pay/type', params);
};

/**
 * 获取当前可用会员类型
 */
export interface VipTypeListParams {}

export const vipTypeListApi = (params: VipTypeListParams = {}): Promise<R<VipType[]>> => {
    return post<R<VipType[]>>('/api/v1/vip/type', params);
};
