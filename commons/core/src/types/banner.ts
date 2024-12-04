import { AttachmentEntity } from '@commons/core/types/attachment.type.ts';
import { BaseEntity } from '@commons/core/types/common.ts';
import { DictRelationVo } from '@commons/core/types/dict.ts';

export class Banner extends BaseEntity {
    title?: string = '';
    type?: DictRelationVo = new DictRelationVo();
    cover?: AttachmentEntity = new AttachmentEntity();
    mobileCover?: AttachmentEntity = new AttachmentEntity();
    sortOrder?: string | number = 999;
    description?: string = '';
    startDatetime?: string = '';
    endDatetime?: string = '';
}
