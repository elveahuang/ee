import { R } from '@commons/core/types/common.ts';

export interface CusFile extends File {
    lastModifiedDate: string;
    originFileObj: any;
    percent: number;
    response: R;
    status: string;
    uid: string;
    xhr: any;
}
