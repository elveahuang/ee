import { R } from '@commons/core/types';
import { post } from '@commons/core/utils/http';

/**
 * 获取图形验证码
 */
export class CodeCaptchaApiResult {
    key: string;
    image: string;
}

export class CodeCaptchaApiParams {
    //
}

export const getCodeCaptchaApi = (params: CodeCaptchaApiParams): Promise<R<CodeCaptchaApiResult>> => {
    return post<R<CodeCaptchaApiResult>>('/api/v1/captcha/code', params);
};

/**
 * 获取邮箱验证码
 */
export class EmailCaptchaApiResult {
    key: string;
}

export class EmailCaptchaApiParams {
    email?: string;
}

export const getEmailCaptchaApi = (params: EmailCaptchaApiParams) => {
    return post<R<EmailCaptchaApiResult>>('/api/v1/captcha/mail', params);
};

/**
 * 校验验证码
 */

export class CheckCaptchaApiResult {
    key: string;
}

export class CheckCaptchaApiParams {
    email?: string;
    codeKey?: string;
    code?: string;
}

export const checkCaptchaApi = (params: CheckCaptchaApiParams) => {
    return post<R<EmailCaptchaApiResult>>('/api/v1/check/captcha/mail', params);
};
