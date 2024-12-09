import { AttachmentEntity } from '@commons/core/types/attachment.type.ts';

export class ProductCatalogEntity {
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
     * 内容简介
     */
    content?: string = '';
    /**
     * 描述备注
     */
    description?: string = '';
    /**
     * 目录封面
     */
    cover?: AttachmentEntity = new AttachmentEntity();
    /**
     * 目录文件
     */
    files?: AttachmentEntity = new AttachmentEntity();
    /**
     * 发布状态
     */
    status?: number = 0;
    /**
     * 启用状态
     */
    active?: number = 1;
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
