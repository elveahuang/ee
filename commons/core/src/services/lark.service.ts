import { log } from '@commons/core/utils';

declare const h5sdk: any;
declare const tt: any;

class LarkService {
    /**
     * 是否可用
     */
    async available(): Promise<boolean> {
        return true;
    }

    /**
     * 判断当前是否是运行在飞书客户端
     */
    isLark(): boolean {
        const ua = window.navigator.userAgent.toLowerCase();
        return ua.indexOf('lark') !== -1;
    }

    /**
     * 获取微信签名
     */
    async getSignature(params: any): Promise<any> {
        log(params);
        log(`LarkService getSignature...`);
    }

    async config() {
        if (!this.isLark()) {
            return Promise.resolve();
        }
        log(`LarkService getSignature...`);
        return new Promise<void>((resolve, reject) => {
            this.getSignature(`url`).then((res) => {
                if (res.data) {
                    log(`LarkService config...`);
                    h5sdk.config({
                        appId: res.data.appId,
                        timestamp: +res.data.timestamp,
                        nonceStr: res.data.nonceStr,
                        signature: res.data.signature,
                        jsApiList: ['biz.navigation.close', 'biz.util.share'],
                        onSuccess: (res: any) => {
                            log(`LarkService config onSuccess.`);
                            log(res);
                            resolve();
                        },
                        onFail: (res: any) => {
                            log(`LarkService config onFail.`);
                            log(res);
                            reject();
                        },
                    });
                    h5sdk.error((error: any) => {
                        log(`LarkService config error.`);
                        log(error);
                    });
                    h5sdk.ready(() => {
                        log(`LarkService config ready.`);
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
        log(`LarkService getPhoto...`);
        return this.takePhotos('camera', 1);
    }

    /**
     * 调用微信摄像头从相册选择图片
     */
    async choosePhotos(imageCount = 1): Promise<any> {
        log(`LarkService choosePhotos...`);
        return this.takePhotos('album', imageCount);
    }

    /**
     * 拍照
     */
    async takePhotos(source = '', limit = 1): Promise<void> {
        log(`LarkService takePhotos...`);
        return new Promise((resolve, reject) => {
            if (h5sdk) {
                log(`LarkService h5sdk available.`);
                h5sdk.ready(() => {
                    tt.chooseImage({
                        sourceType: [source],
                        count: limit,
                        sizeType: ['original', 'compressed'],
                        success: (res: any) => {
                            log(`LarkService takePhotos success.`);
                            log(res);
                            resolve(res.tempFilePaths);
                        },
                        fail: (err: any) => {
                            log(`LarkService takePhotos success.`);
                            log(err);
                            reject();
                        },
                    });
                });
            } else {
                log(`LarkService h5sdk not available.`);
                reject();
            }
        });
    }

    /**
     * 扫描二维码
     */
    scanQRCode(): Promise<string> {
        log(`LarkService scanQRCode...`);
        return new Promise<string>((resolve, reject) => {
            if (h5sdk) {
                log(`LarkService h5sdk available.`);
                h5sdk.ready(() => {
                    log(`LarkService h5sdk ready.`);
                    tt.scanCode({
                        scanType: ['barCode', 'qrCode'],
                        barCodeInput: true,
                        success: (res: any) => {
                            log(`LarkService scanQRCode success.`);
                            log(res);
                            resolve(res.result);
                        },
                        fail: (res: any) => {
                            log(`LarkService scanQRCode fail.`);
                            log(res);
                            reject();
                        },
                    });
                });
            } else {
                log(`LarkService h5sdk not available.`);
                reject();
            }
        });
    }
}

export default new LarkService();
