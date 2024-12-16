import AttachmentService from '@commons/core/services/attachment.service.ts';
import { NativeService } from '@commons/core/services/index.ts';
import { R } from '@commons/core/types';
import { AttachmentFileEntity } from '@commons/core/types/attachment.type.ts';
import { FileData } from '@commons/core/types/file-data.model.ts';
import { FilePayload } from '@commons/core/types/file-payload.model.ts';
import { isEmpty } from 'radash';

/**
 * 本地文件服务
 */
class FileNativeService {
    /**
     * 是否可用
     */
    available(): Promise<boolean> {
        return Promise.resolve(NativeService.isNative());
    }

    /**
     * 图片预览
     */
    previewPhoto(file: any): Promise<any> {
        return Promise.resolve(file);
    }

    /**
     * 上传文件
     */
    async postData(data: FileData): Promise<any> {
        return this.resolveFiles(data && data.fileList).then((formData: FormData) => {
            formData.append('targetType', data.targetType);
            return AttachmentService.uploadAttachment(formData);
        });
    }

    /**
     * 上传文件并返回文件ID
     */
    async postDataAndReturnIdList(data: FileData): Promise<Array<string | number>> {
        return this.postData(data).then((response: R) => {
            const fileList: AttachmentFileEntity[] = response.data as AttachmentFileEntity[];
            let fileIdList: Array<string | number> = [];
            if (!isEmpty(fileList)) {
                fileIdList = fileList.map((attachmentEntity: AttachmentFileEntity) => {
                    return attachmentEntity.id;
                });
            }
            return fileIdList;
        });
    }

    /**
     * 解析文件封装成表单对象
     */
    async resolveFiles(fileList: FilePayload[]): Promise<FormData> {
        const formData = new FormData();
        const promiseList: any = [];
        if (!isEmpty(fileList)) {
            fileList.forEach((filePayload: FilePayload, index: number) => {
                promiseList.push(
                    this.nativeResolveLocalFilesystemUrl(filePayload.path).then(({ blob, entryName }) => {
                        formData.append('file-' + index, blob, entryName);
                    }),
                );
            });
        }
        return Promise.all(promiseList).then(() => {
            return formData;
        });
    }

    async nativeResolveLocalFilesystemUrl(filePath: string): Promise<{ blob: Blob; entryName: string }> {
        if (NativeService.isAndroid()) {
            // filePath = await filePath.resolveNativePath(filePath);
        }
        if (NativeService.isIos() && filePath.indexOf('file://') != 0) {
            filePath = 'file://' + filePath;
        }
        // 临时改造
        return Promise.resolve(null);
        // return this.file.resolveLocalFilesystemUrl(filePath).then(
        //     (entry: Entry) =>
        //         new Promise((resolve, reject) => {
        //             (<FileEntry>entry).file((file: IFile) => {
        //                 const reader = new FileReader();
        //                 reader.onloadend = () => {
        //                     const blob = new Blob([reader.result], {
        //                         type: file.type,
        //                     });
        //                     resolve({
        //                         blob,
        //                         entryName: entry.name,
        //                     });
        //                 };
        //
        //                 reader.onerror = (reason) => {
        //                     reject(reason);
        //                 };
        //
        //                 reader.readAsArrayBuffer(file);
        //             });
        //         }),
        // );
    }

    async resolveLocalFilesystemUrl(filePath: string): Promise<[string, string]> {
        return this.nativeResolveLocalFilesystemUrl(filePath).then(({ blob, entryName }) => [URL.createObjectURL(blob), entryName]);
    }

    chooseFile(): Promise<FilePayload[]> {
        return new Promise<FilePayload[]>(() => {});
    }
}

export default new FileNativeService();
