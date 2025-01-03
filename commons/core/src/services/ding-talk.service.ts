import { log } from '@commons/core/utils';

declare const dd: any;

class DingTalkService {
    /**
     * 是否可用
     */
    async available(): Promise<boolean> {
        return true;
    }

    /**
     * 判断当前是否是运行在钉钉客户端
     */
    isDingTalk(): boolean {
        const ua = window.navigator.userAgent.toLowerCase();
        return ua.indexOf('lark') !== -1;
    }

    /**
     * 获取微信签名
     */
    async getSignature(params: any): Promise<any> {
        log(params);
        log(`DingTalkService getSignature...`);
    }

    async config() {
        log(`DingTalkService config...`);
        return new Promise<void>((resolve, reject) => {
            this.getSignature(`url`).then((res) => {
                if (res.data) {
                    log(`DingTalkService config...`);
                    dd.config({
                        agentId: res.data.agentId,
                        corpId: res.data.corpId,
                        timeStamp: res.data.timestamp,
                        nonceStr: res.data.nonceStr,
                        signature: res.data.signature,
                        jsApiList: [
                            'runtime.info',
                            'biz.contact.choose',
                            'device.notification.confirm',
                            'device.notification.alert',
                            'device.notification.prompt',
                            'biz.ding.post',
                            'biz.util.openLink',
                            'biz.navigation.close',
                            'biz.util.share',
                            'biz.util.chooseImage',
                            'biz.util.scan',
                        ],
                        onSuccess: (res: any) => {
                            log(`DingTalkService config onSuccess.`);
                            log(res);
                            resolve();
                        },
                        onFail: (res: any) => {
                            log(`DingTalkService config onFail.`);
                            log(res);
                            reject();
                        },
                    });
                    dd.error((error: any) => {
                        log(`DingTalkService config error.`);
                        log(error);
                    });
                    dd.ready(() => {
                        log(`DingTalkService config ready.`);
                    });
                } else {
                    reject();
                }
            });
        });
    }

    /**
     * 调用微信摄像头拍照
     */
    async getPhoto(): Promise<any> {
        log(`DingTalkService getPhoto...`);
        return this.takePhotos('camera', 1);
    }

    /**
     * 调用微信摄像头从相册选择图片
     */
    async choosePhotos(imageCount = 1): Promise<any> {
        log(`DingTalkService choosePhotos...`);
        return this.takePhotos('album', imageCount);
    }

    /**
     * 拍照
     */
    async takePhotos(source = '', limit = 1): Promise<void> {
        log(`DingTalkService takePhotos...`);
        return new Promise((resolve, reject) => {
            if (dd) {
                log(`DingTalkService dd available.`);
                dd.ready(() => {
                    dd.chooseImage({
                        sourceType: [source],
                        count: limit,
                        position: ['back'],
                        onSuccess: (res: any) => {
                            log(`DingTalkService takePhotos success.`);
                            console.log(res.tempFilePaths, res.tempFiles);
                            resolve();
                        },
                        onFail: (res: any) => {
                            log(`DingTalkService takePhotos fail.`);
                            log(res);
                            reject();
                        },
                    });
                });
            } else {
                log(`DingTalkService dd not available.`);
                reject();
            }
        });
    }

    /**
     * 扫描二维码
     */
    scanQRCode(): Promise<string> {
        log(`DingTalkService scanQRCode...`);
        return new Promise<string>((resolve, reject) => {
            if (dd) {
                log(`DingTalkService dd available.`);
                dd.ready(() => {
                    log(`DingTalkService dd ready.`);
                    dd.biz.util.scan({
                        type: 'all',
                        onSuccess: (res: any) => {
                            log(`DingTalkService scanQRCode onSuccess.`);
                            log(res);
                            resolve(res.text);
                        },
                        onFail: (res: any) => {
                            log(`DingTalkService scanQRCode onFail.`);
                            log(res);
                            reject();
                        },
                    });
                });
            } else {
                log(`DingTalkService dd not available.`);
                reject();
            }
        });
    }
}

export default new DingTalkService();
