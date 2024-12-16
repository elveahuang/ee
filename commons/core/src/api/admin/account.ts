import { Key, PageParams, PageResult, R } from '@commons/core/types';
import { AccountEntity } from '@commons/core/types/account.ts';
import { get, post } from '@commons/core/utils/http.ts';

export interface AccountEntityListApiResult extends PageResult<AccountEntity> {}

export interface AccountApiParams extends PageParams {}

export const accountListApi = (params: AccountApiParams): Promise<R<AccountEntityListApiResult>> => {
    return post<R<AccountEntityListApiResult>>('/api/v1/admin/account/list', params);
};

export interface AccountDetailsApiParams {
    id: number | string;
}

export const accountDetails = (params: AccountDetailsApiParams): Promise<R<AccountEntity>> => {
    return post<R<AccountEntity>>('/api/v1/admin/account/details', params);
};
export interface AccountSaveApiResult {}

export const accountSaveApi = (params: AccountEntity): Promise<R<AccountSaveApiResult>> => {
    return post<R<AccountSaveApiResult>>('/api/v1/admin/account/save', params);
};
export interface AccountDeleteApiParams {
    ids?: Key[];
}

export const accountDeleteApi = (params: AccountDeleteApiParams): Promise<R<AccountSaveApiResult>> => {
    return post<R<AccountSaveApiResult>>('/api/v1/admin/account/delete', params);
};

export const resetPasswordApi = (params: AccountEntity): Promise<R<AccountEntity>> => {
    return post<R<AccountEntity>>('/api/v1/admin/account/reset/password', params);
};

export declare type CheckAccountPayload = {
    id: number;
    username: string;
};
export const accountCheckApi = (params: CheckAccountPayload): Promise<R<boolean>> => {
    return get<R<boolean>>('api/v1/account/check-username', params);
};
