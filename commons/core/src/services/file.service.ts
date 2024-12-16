import FileNativeService from '@commons/core/services/file.native.service.ts';
import { NativeService, WeChatService, WeWorkService } from '@commons/core/services/index.ts';
import { FileData } from '@commons/core/types/file-data.model.ts';

class FileService {
    /**
     *  是否可用
     */
    async available(): Promise<boolean> {
        return true;
    }

    /**
     * 上传文件
     * @param data
     */
    async postData(data: FileData): Promise<any> {
        if (WeChatService.isWeChat() || WeWorkService.isWeCom() || NativeService.isWeb()) {
            // return FileWebService.postData(data);
        }
        return FileNativeService.postData(data);
    }
}

export default new FileService();
