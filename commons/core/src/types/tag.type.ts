import { BaseEntity } from '@commons/core/types/common.ts';

export class TagTypeEntity {
    /**
     * ID
     */
    id?: number = 0;
    /**
     * 编号
     */
    code?: string = '';
    /**
     * 标题
     */
    title?: string = '';
    /**
     * 标题
     */
    label?: string = '';
    /**
     * 备注描述
     */
    description?: string = '';
    /**
     * 来源
     */
    source?: number = 0;
    /**
     * 启用状态
     */
    active?: boolean = false;
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

export class TagRelation {
    /**
     * 目标实体
     */
    targetId?: number | string = 0;
    /**
     * 目标类型
     */
    targetType?: string = '';
    /**
     * 标签ID数组
     */
    tagIdList?: number[] = [];
    /**
     * 标签数组
     */
    tagList?: TagTypeEntity[] = [];
    /**
     * 是否正在加载搜索
     */
    loading?: boolean = false;
}
/* 字典子项 */
export class TagEntity extends BaseEntity {
    code?: string = '';
    tagTypeId?: number | string = 0;
    sortOrder?: number = 999;
    title?: string = '';
    description?: string = '';
    source?: number = 2;
    /**
     * 字典关联实体
     */
    data?: any;
}
