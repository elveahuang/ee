import { R } from '@commons/core/types';
import { post } from '@commons/core/utils/http.ts';

/**
 * 附件接口
 */
export class AttachmentApi {
    /**
     * 附件上传
     */
    static UPLOAD_FILE_URL: string = '/api/v1/attachment/upload';

    /**
     * upload
     */
    static upload(formData: FormData, headers: any = {}): Promise<R> {
        return post<R>(this.UPLOAD_FILE_URL, formData, { headers: headers });
    }
}
