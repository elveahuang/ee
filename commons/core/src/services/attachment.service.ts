import { AttachmentApi } from '@commons/core/api/attachment.ts';
import { R } from '@commons/core/types';
import { AttachmentEntity } from '@commons/core/types/attachment.type.ts';
import { isEmpty } from 'radash';

/**
 * 附件服务
 */
class AttachmentService {
    uploadAttachment(params: any): Promise<R> {
        return AttachmentApi.upload(params);
    }

    getAttachmentFileUrls(attachment: AttachmentEntity) {
        const attachmentFileUrls = [];
        if (!isEmpty(attachment.fileList)) {
            for (const file of attachment.fileList) {
                attachmentFileUrls.push(file.url);
            }
        }
        return attachmentFileUrls;
    }
}

export default new AttachmentService();
