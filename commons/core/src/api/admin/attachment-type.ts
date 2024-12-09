import { R } from '@commons/core/types';
import { AttachmentType } from '@commons/core/types/attachment.type.ts';
import { post } from '@commons/core/utils/http.ts';

export const attachmentTypeSearchApi = (params: { code: string }): Promise<R<AttachmentType>> => {
    return post<R<AttachmentType>>('/api/v1/attachment/search', params);
};
