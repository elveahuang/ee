import DingTalkService from '@commons/core/services/ding-talk.service';
import LarkService from '@commons/core/services/lark.service';
import NativeService from '@commons/core/services/native.serviice';
import WeChatService from '@commons/core/services/we-chat.service';
import WeWorkService from '@commons/core/services/we-com.service.ts';
import { log } from '@commons/core/utils';

class CameraService {
    /**
     *  是否可用
     */
    async available(): Promise<boolean> {
        return true;
    }

    /**
     * 调用微信摄像头拍照
     */
    async getPhoto(): Promise<any> {
        log(`CameraService getPhoto...`);
        return this.takePhotos('camera', 1);
    }

    /**
     * 调用微信摄像头从相册选择图片
     */
    async choosePhotos(imageCount = 1): Promise<any> {
        log(`CameraService choosePhotos...`);
        return this.takePhotos('album', imageCount);
    }

    /**
     * 拍照
     */
    async takePhotos(source = '', limit = 1): Promise<void> {
        log(`CameraService choosePhotos...`);
        if (NativeService.isNative()) {
            await NativeService.takePhotos(source, limit);
        } else if (WeWorkService.isWeCom()) {
            await WeWorkService.takePhotos(source, limit);
        } else if (WeChatService.isWeChat()) {
            await WeChatService.takePhotos(source, limit);
        } else if (LarkService.isLark()) {
            await LarkService.takePhotos(source, limit);
        } else if (DingTalkService.isDingTalk()) {
            await DingTalkService.takePhotos(source, limit);
        }
    }

    /**
     * 扫码
     */
    async scanQrCode(): Promise<void> {
        log(`CameraService scanQrCode...`);
        if (NativeService.isNative()) {
            await NativeService.scanQRCode();
        } else if (WeWorkService.isWeCom()) {
            await WeWorkService.scanQRCode();
        } else if (WeChatService.isWeChat()) {
            await WeChatService.scanQRCode();
        } else if (LarkService.isLark()) {
            await LarkService.scanQRCode();
        } else if (DingTalkService.isDingTalk()) {
            await DingTalkService.scanQRCode();
        }
    }
}

export default new CameraService();
