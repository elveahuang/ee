import { R } from '@commons/core/types';
import { post } from '@commons/core/utils/http';

/**
 * 应用初始化接口
 */
export class InitializeApiResult {
    loginCaptchaEnabled: boolean;
}

export const initializeApi = (): Promise<R<InitializeApiResult>> => {
    return post<R<InitializeApiResult>>('/api/v1/initialize');
};
