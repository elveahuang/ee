/**
 * 登录凭证
 */
export interface Credentials {
    username?: string;
    password?: string;
    captchaKey?: string;
    captchaValue?: string;
}

/**
 * 登录凭证
 */
export interface Register {
    username?: string;
    email?: string;
    password?: string;
    passwordConfirm?: string;
    captchaKey?: string;
    captchaValue?: string;
}

/**
 * 用户
 */
export class User {
    id?: number;
    username?: string;
    displayName?: string;
    email?: string;
    roles?: string[];
    authorities?: string[];
}
