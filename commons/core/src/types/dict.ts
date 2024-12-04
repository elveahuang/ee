import { BaseEntity } from '@commons/core/types/common.ts';

/**
 * 字典项
 */
export class Dict {
    id?: number | string = 0;
    code?: string = '';
    title?: string = '';
    items?: DictItem<any>[] = [];
}

export class DictItem<T> {
    id?: number | string = 0;
    code?: string = '';
    title?: string = '';
    items?: T[] = [];
}

/* 字典项 */
export class DictEntity extends BaseEntity {
    code?: string = '';
    title?: string = '';
    label?: string = '';
    description?: string = '';
    source?: number = 2;
    children?: Array<DictItemEntity> = [];
}

/* 字典子项 */
export class DictItemEntity extends BaseEntity {
    code?: string = '';
    typeId?: number | string = 0;
    idx?: number = 999;
    title?: string = '';
    source?: number = 2;
    type?: string;
    /**
     * 字典关联实体
     */
    data?: any = [];
}

/* 字典项关联Vo */
export class DictRelationVo {
    targetId?: number | string = 0;
    targetType?: string = '';
    dictId?: number | string = 0;
    ids?: Array<number | string> = [];
    items?: Array<DictItemEntity> = [];

    constructor() {
        this.targetId = 0;
        this.targetType = 'UNSPECIFIED';
        this.dictId = 0;
        this.ids = [];
        this.items = [];
    }
}
