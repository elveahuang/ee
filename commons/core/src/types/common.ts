/**
 * 接口响应数据
 */
export class R<T = any> {
    code?: number | string;
    message?: string;
    data?: T;
    isSuccess(): boolean {
        return this.code === 200;
    }
}

/**
 * 分页请求接口响应数据
 */
export class PageResponse<T = any> {
    content?: T[];
    totalElements?: number | string;
    pageable?: {
        pageNumber?: number;
        pageSize?: number;
    };
    last?: boolean;
}

export class PageResult<T = any> extends PageResponse<T> {}

/**
 * 分页请求参数
 */
export class PageRequest {
    page?: number;
    size?: number;
    sort?: string;
    order?: string;
    q?: string;
}

export class PageParams extends PageRequest {}

/**
 * 实体主键
 */
export type Key = string | number;

/**
 * 主键实体基类
 */
export abstract class AbstractEntity {
    id?: number | string = 0;
}

/**
 * 实体基类
 */
export abstract class BaseEntity extends AbstractEntity {
    active?: boolean = false;
    createdAt?: Date = null;
    createdBy?: number | string = 0;
    lastModifiedAt?: Date | null = null;
    lastModifiedBy?: number | string = 0;
    deletedAt?: Date | null = null;
    deletedBy?: number | string = 0;
}
