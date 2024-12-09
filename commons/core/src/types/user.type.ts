import { AttachmentEntity } from '@commons/core/types/attachment.type';
export class UserEntity {
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
 * 性别类型定义
 */
export const SEX_TYPES: { [key: string]: any } = {
    MALE: {
        type: 'M',
        label: 'common.user_field_sex_male',
    },
    FEMALE: {
        type: 'F',
        label: 'common.user_field_sex_female',
    },
    UNSPECIFIED: {
        type: 'UNSPECIFIED',
        label: 'common.user_field_sex_unspecified',
    },
};
