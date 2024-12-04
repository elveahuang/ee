import { AttachmentEntity } from '@commons/core/types/attachment.type.ts';

export class AccountEntity {
    /**
     * ID
     */
    id?: number = 0;
    /**
     * 用户名
     */
    username?: string = '';
    /**
     * 昵称
     */
    displayName?: string = '';
    /**
     * 全名
     */
    name?: string = '';
    /**
     * 电子邮箱
     */
    email?: string = '';
    /**
     * 手机号码
     */
    mobile?: string = '';
    /**
     * 手机号码
     */
    birthday?: string = '';
    /**
     * 手机号码
     */
    sex?: string = 'U';
    /**
     * 描述
     */
    description?: string = '';
    /**
     * 密码
     */
    password?: string = '';
    /**
     * 确认密码
     */
    confirmPassword?: string = '';
    /**
     * 用户状态
     */
    status?: boolean = false;
    /**
     * 启用状态
     */
    active?: string = '';
    /**
     * 用户头像
     */
    avatar?: AttachmentEntity = new AttachmentEntity();
    /**
     * 创建时间
     */
    created_at?: Date | null = null;
    /**
     * 创建人
     */
    created_by?: number | null = null;
    /**
     * 更新时间
     */
    updated_at?: Date | null = null;
    /**
     * 更新人
     */
    updated_by?: number | null = null;
    /**
     * 删除时间
     */
    deleted_at?: Date | null = null;
    /**
     * 删除人
     */
    deleted_by?: number | null = null;
}
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
export class Account {
    id?: number;
    username?: string;
    displayName?: string;
    roles?: string[];
    authorities?: string[];
}
