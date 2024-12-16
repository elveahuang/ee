import { FilePayload } from '@commons/core/types/file-payload.model.ts';

export interface FileData {
    /**
     * 附件类型
     */
    targetType: string;
    /**
     * 待上传文件
     */
    fileList: FilePayload[];
}
