import { env } from '@commons/core/env';
import { R, User } from '@commons/core/types';
import { get, post } from '@commons/core/utils/http';

/**
 * 用户注册
 */
export const RegisterApiPath: string = env.auth.user.type === 'account' ? '/api/v1/account/register' : '/api/v1/user/register';

export class RegisterApiResult {
    user: User;
    now: Date | string;
}

export class RegisterApiParams {
    username?: string;
    password?: string;
    email?: string;
    emailCode?: string;
    codeKey?: string;
    address?: string;
    telegram?: string;
}

export const registerApi = (params: RegisterApiParams) => {
    return post<R<RegisterApiResult>>(RegisterApiPath, params);
};

/**
 * 编辑个人资料
 */
export const UpdateAccountURL: string = env.auth.user.type === 'account' ? '/api/v1/account/save' : '/api/v1/user/save';

export class UpdateAccountResult {}

export class UpdateAccountParams {
    displayName?: string;
    sex?: string;
    birthday?: any;
}

export const updateAccountApi = (params: UpdateAccountParams): Promise<R<UpdateAccountResult>> => {
    return post<R<UpdateAccountResult>>(UpdateAccountURL, params);
};

/**
 * 修改密码
 */
export const ChangePasswordURL: string = env.auth.user.type === 'account' ? '/api/v1/account/change-password' : '/api/v1/user/change-password';

export class ChangePasswordResult {}

export class ChangePasswordParams {
    originalPassword?: string;
    newPassword?: string;
}

export const changePasswordApi = (params: ChangePasswordParams): Promise<R<ChangePasswordResult>> => {
    return post<R<ChangePasswordResult>>(ChangePasswordURL, params);
};

/**
 * 修改邮箱
 */
export const ChangeEmailURL: string = env.auth.user.type === 'account' ? '/api/v1/account/change-email' : '/api/v1/user/change-email';

export class ChangeEmailParams {
    email?: string;
    password?: string;
    newEmail?: string;
    captchaKey?: string;
    captchaValue?: string;
}

export const changeEmailApi = (params: ChangeEmailParams): Promise<R> => {
    return post<R>(ChangeEmailURL, params);
};

/**
 * 找回密码
 */
export const ForgotPasswordURL: string = env.auth.user.type === 'account' ? '/api/v1/account/forgot-password' : '/api/v1/user/forgot-password';

export class ForgotPasswordResult {
    username: string;
    email: string;
}

export class ForgotPasswordParams {
    email?: string;
    captchaKey?: string;
    captchaValue?: string;
}

export const forgotPasswordApi = (params: ForgotPasswordParams): Promise<R<ForgotPasswordResult>> => {
    return post<R<ForgotPasswordResult>>(ForgotPasswordURL, params);
};

/**
 * 重置密码
 */
export const ResetPasswordURL: string = env.auth.user.type === 'account' ? '/api/v1/account/reset-password' : '/api/v1/user/reset-password';

export class ResetPasswordResult {}

export class ResetPasswordParams {
    email?: string;
    username?: string;
    password?: string;
    captchaKey?: string;
    captchaValue?: string;
}

export const resetPasswordApi = (params: ResetPasswordParams): Promise<R<ResetPasswordResult>> => {
    return post<R<ResetPasswordResult>>(ResetPasswordURL, params);
};

/**
 * 检查用户名是否可用
 */
export const CheckUsernameURL: string = env.auth.user.type === 'account' ? '/api/v1/account/check-username' : '/api/v1/user/check-username';

export class CheckUsernameParams {
    id?: number;
    username?: string;
}

export const checkUsernameApi = (params: CheckUsernameParams): Promise<R<boolean>> => {
    return get<R<boolean>>(CheckUsernameURL, params);
};

/**
 * 检查邮箱是否可用
 */
export const CheckEmailURL: string = env.auth.user.type === 'account' ? '/api/v1/account/check-email' : '/api/v1/user/check-email';

export class CheckEmailParams {
    id?: number;
    email?: string;
}

export const checkEmailApi = (params: CheckEmailParams): Promise<R<boolean>> => {
    return get<R<boolean>>(CheckEmailURL, params);
};
