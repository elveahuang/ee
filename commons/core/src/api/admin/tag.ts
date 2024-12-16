import { Key, PageParams, PageResult, R } from '@commons/core/types';
import { DictItemEntity } from '@commons/core/types/dict.ts';
import { TagEntity, TagTypeEntity } from '@commons/core/types/tag.type.ts';
import { get, post } from '@commons/core/utils/http.ts';

export interface TagTypeEntityListApiResult extends PageResult<TagTypeEntity> {}

export interface TagTypeEntityListApiParams extends PageParams {}

export const tagTypeEntityPageApi = (params: TagTypeEntityListApiParams): Promise<R<TagTypeEntityListApiResult>> => {
    return post<R<TagTypeEntityListApiResult>>('/api/v1/admin/tag/type/list', params);
};

export interface TagTypeEntityDeleteApiParams {
    ids?: Key[];
}

export const tagTypeEntityDeleteApi = (params: TagTypeEntityDeleteApiParams): Promise<R> => {
    return post<R>('/api/v1/admin/tag/type/delete', params);
};

export interface TagTypeEntityDetailsApiParams {
    id: number | string;
}

export const tagTypeEntityDetailsApi = (params: TagTypeEntityDetailsApiParams): Promise<R<TagTypeEntity>> => {
    return post<R<TagTypeEntity>>('/api/v1/admin/tag/type/details', params);
};

export interface TagTypeEntitySaveApiResult {}

export const tagTypeEntitySaveApi = (params: TagTypeEntity): Promise<R<TagTypeEntitySaveApiResult>> => {
    return post<R<TagTypeEntitySaveApiResult>>('/api/v1/admin/tag/type/save', params);
};

export interface TagEntityListApiResult extends PageResult<TagEntity> {}

export interface TagEntityListApiParams extends PageParams {
    typeId: string | string[] | number;
}

export const tagEntityPageApi = (params: TagEntityListApiParams): Promise<R<TagEntityListApiResult>> => {
    return post<R<TagEntityListApiResult>>('/api/v1/admin/tag/list', params);
};

export interface TagEntityDetailsApiParams {
    id: number | string;
}

export const tagEntityDetailsApi = (params: TagEntityDetailsApiParams): Promise<R<TagEntity>> => {
    return post<R<TagEntity>>('/api/v1/admin/tag/details', params);
};

export interface TagEntitySaveApiResult {}

export const tagEntitySaveApi = (params: DictItemEntity): Promise<R<TagEntitySaveApiResult>> => {
    return post<R<TagEntitySaveApiResult>>('/api/v1/admin/tag/save', params);
};

export declare type CheckCodePayload = {
    code: string;
    id: string | number;
};
export const checkCodeApi = (params: CheckCodePayload): Promise<R<boolean>> => {
    return get<R<boolean>>('/api/v1/admin/tag/type/check', params);
};

export interface TagEntityDeleteApiParams {
    ids?: Key[];
}
export const tagEntityDeleteApi = (params: TagEntityDeleteApiParams): Promise<R> => {
    return post<R>('/api/v1/admin/tag/delete', params);
};
