import { Capacitor } from '@capacitor/core';
import DingTalkService from '@commons/core/services/ding-talk.service';
import LarkService from '@commons/core/services/lark.service';
import NativeService from '@commons/core/services/native.serviice';
import WeChatService from '@commons/core/services/we-chat.service';
import WeComService from '@commons/core/services/we-com.service.ts';
import { log } from '@commons/core/utils';

class PlatformService {
    /**
     * 获取当前设备平台
     */
    getPlatform(): string {
        return Capacitor.getPlatform();
    }

    /**
     * 判断是否是移动设备
     *
     * @returns {boolean}
     */
    isWapPlatform(): boolean {
        const ua: string = navigator.userAgent.toLowerCase();
        return /mobile|android|iphone|ipad|phone/i.test(ua);
    }

    /**
     * 判断是否是桌面设备
     *
     * @returns {boolean}
     */
    isWebPlatform(): boolean {
        return !this.isWapPlatform();
    }

    /**
     * 获取当前平台的设置
     */
    async initialize(): Promise<void> {
        log(`PlatformService initialize...`);
        if (NativeService.isNative()) {
            await NativeService.config();
        } else {
            if (WeComService.isWeCom()) {
                await WeComService.config();
            } else if (WeChatService.isWeChat()) {
                await WeChatService.config();
            } else if (LarkService.isLark()) {
                await LarkService.config();
            } else if (DingTalkService.isDingTalk()) {
                await DingTalkService.config();
            }
        }
    }
}

export default new PlatformService();
