export class AttachmentEntity {
    /**
     * 目标实体
     */
    targetId?: number | string = 0;
    /**
     * 目标类型
     */
    targetType?: string = '';
    /**
     * 附件类型
     */
    attachmentType?: AttachmentType = new AttachmentType();
    /**
     * 附件文件列表
     */
    fileList?: AttachmentFileEntity[] = [];
}

export class AttachmentFileEntity {
    /**
     * ID
     */
    id?: string | number = 0;
    /**
     * 当前文件名
     */
    filename?: string = '';
    /**
     * 原始文件名
     */
    original_filename?: string = '';
    /**
     * 文件链接
     */
    url?: string = '';
    /**
     * 附加信息
     */
    extra?: string = '';
}

export class AttachmentType {
    /**
     * 附件类型
     */
    code?: string = '';
    /**
     * 是否支持多文件
     */
    multipleInd?: boolean = false;
    /**
     * 支持扩展名
     */
    ext?: string[] = [];
    /**
     * 文件类型
     * image/jpg、image/png、video/mp4 ...
     */
    fileTypes?: string[] = [];
}

/**
 * 获取附件url
 */
export const getFileUrl = (attachment: AttachmentEntity): Array<string> => {
    const defaultUrl = new URL(`../assets/images/cover.png`, import.meta.url).href;
    return attachment.fileList.map((file: AttachmentFileEntity) => file.url || defaultUrl) || [defaultUrl];
};
