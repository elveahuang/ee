export class FilePayload {
    /**
     * 附件id
     */
    id: string;
    /**
     * 文件链接
     */
    url: any;
    /**
     * base64
     */
    content: '';
    /**
     * 文件路径
     */
    path: string;
    /**
     * 文件类型
     */
    type: string;
    /**
     * 文件名
     */
    name: string;
    /**
     * 文件对象
     */
    obj: any;
    /**
     * 文件对象
     */
    file: File;
    /**
     * 文件上传状态
     */
    status: string;
    /**
     * 文件上传状态提示
     */
    message: string;
    /**
     * 文件数组长度
     */
    length: number;
    /**
     * blob文件
     */
    blob: Blob;
}
