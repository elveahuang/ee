import { Key, PageParams, PageResult, R } from '@commons/core/types';
import { DictEntity, DictItemEntity } from '@commons/core/types/dict.ts';
import { get, post } from '@commons/core/utils/http';

//========================================================字典========================================================//
export declare type CheckCodePayload = {
    code: string;
    id: string | number;
};
// ---------------------------------------------------------------------------------------------------------------------
// 检查字典编号是否存在
// ---------------------------------------------------------------------------------------------------------------------
export const checkCodeApi = (params: CheckCodePayload): Promise<R<boolean>> => {
    return get<R<boolean>>('/api/v1/admin/dict/check', params);
};
// ---------------------------------------------------------------------------------------------------------------------
// 检查字典编号是否存在
// ---------------------------------------------------------------------------------------------------------------------
export const checkItemCodeApi = (params: CheckCodePayload): Promise<R<boolean>> => {
    return get<R<boolean>>('/api/v1/admin/dict/item/check', params);
};
// ---------------------------------------------------------------------------------------------------------------------
// 获取字典列表
// ---------------------------------------------------------------------------------------------------------------------
export interface DictEntityListApiResult extends PageResult<DictEntity> {}

export interface DictEntityListApiParams extends PageParams {}

export const dictEntityPageApi = (params: DictEntityListApiParams): Promise<R<DictEntityListApiResult>> => {
    return post<R<DictEntityListApiResult>>('/api/v1/admin/dict/list', params);
};

// ---------------------------------------------------------------------------------------------------------------------
// 获取字典详情
// ---------------------------------------------------------------------------------------------------------------------
export interface DictEntityDetailsApiParams {
    id: number | string;
}

export const dictEntityDetailsApi = (params: DictEntityDetailsApiParams): Promise<R<DictEntity>> => {
    return post<R<DictEntity>>('/api/v1/admin/dict/details', params);
};

// ---------------------------------------------------------------------------------------------------------------------
// 保存字典详情
// ---------------------------------------------------------------------------------------------------------------------
export interface DictEntitySaveApiResult {}

export const dictEntitySaveApi = (params: DictEntity): Promise<R<DictEntitySaveApiResult>> => {
    return post<R<DictEntitySaveApiResult>>('/api/v1/admin/dict/save', params);
};

// ---------------------------------------------------------------------------------------------------------------------
// 删除字典
// ---------------------------------------------------------------------------------------------------------------------
export interface DictEntityDeleteApiParams {
    ids?: Key[];
}

export const dictEntityDeleteApi = (params: DictEntityDeleteApiParams): Promise<R> => {
    return post<R>('/api/v1/admin/dict/delete', params);
};

//========================================================字典项========================================================//
// ---------------------------------------------------------------------------------------------------------------------
// 获取字典项列表
// ---------------------------------------------------------------------------------------------------------------------
export interface DictItemEntityListApiResult extends PageResult<DictItemEntity> {}

export interface DictItemEntityListApiParams extends PageParams {
    code?: string;
    typeId: string | string[] | number;
}

export const dictItemEntityPageApi = (params: DictItemEntityListApiParams): Promise<R<DictItemEntityListApiResult>> => {
    return post<R<DictItemEntityListApiResult>>('/api/v1/admin/dict/item/list', params);
};
// ---------------------------------------------------------------------------------------------------------------------
// 获取字典所有字典项
// ---------------------------------------------------------------------------------------------------------------------
export const dictItemEntitySearchApi = (params: { code: string }): Promise<R<DictEntity>> => {
    return post<R<DictEntity>>('/api/v1/dict/search', params);
};
// ---------------------------------------------------------------------------------------------------------------------
// 获取字典项详情
// ---------------------------------------------------------------------------------------------------------------------
export interface DictItemEntityDetailsApiParams {
    id: number | string;
}

export const dictItemEntityDetailsApi = (params: DictItemEntityDetailsApiParams): Promise<R<DictItemEntity>> => {
    return post<R<DictItemEntity>>('/api/v1/admin/dict/item/details', params);
};

// ---------------------------------------------------------------------------------------------------------------------
// 保存字典项
// ---------------------------------------------------------------------------------------------------------------------
export interface DictItemEntitySaveApiResult {}

export const dictItemEntitySaveApi = (params: DictItemEntity): Promise<R<DictItemEntitySaveApiResult>> => {
    return post<R<DictItemEntitySaveApiResult>>('/api/v1/admin/dict/item/save', params);
};

// ---------------------------------------------------------------------------------------------------------------------
// 删除字典项
// ---------------------------------------------------------------------------------------------------------------------
export interface DictItemEntityDeleteApiParams {
    ids?: Key[];
}

export const dictItemEntityDeleteApi = (params: DictItemEntityDeleteApiParams): Promise<R> => {
    return post<R>('/api/v1/admin/dict/item/delete', params);
};
