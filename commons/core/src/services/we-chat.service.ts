import { WeChatSignatureApiResult, getWeChatSignatureApi } from '@commons/core/api/wechat';
import env from '@commons/core/env';
import BrowserService from '@commons/core/services/browser.service';
import PlatformService from '@commons/core/services/platform.serviice';
import { R } from '@commons/core/types';
import { log, removeQueryParams, replaceString } from '@commons/core/utils';
import { isEmpty } from 'radash';

declare let wx: any;

class WeChatService {
    /**
     * 是否可用
     */
    async available(): Promise<boolean> {
        return true;
    }

    /**
     * 判断当前是否是运行在微信客户端
     */
    isWeChat(): boolean {
        const ua = window.navigator.userAgent.toLowerCase();
        return ua.indexOf('micromessenger') != -1 && ua.indexOf('wxwork') != -1;
    }

    /**
     * 微信登录认证
     * @param url
     * @param customRedirectUrl
     */
    async auth(url: string, customRedirectUrl = false): Promise<any> {
        log(`WeChatService auth...`);
        // 重定向地址
        let redirectUrl;
        if (PlatformService.isWebPlatform() && !isEmpty(env.wechat.mp.webappRedirectUrl)) {
            redirectUrl = customRedirectUrl ? url : replaceString(env.wechat.mp.webappRedirectUrl, removeQueryParams(url, 'code'));
        } else if (PlatformService.isWapPlatform() && !isEmpty(env.wechat.mp.mobileRedirectUrl)) {
            redirectUrl = customRedirectUrl ? url : replaceString(env.wechat.mp.mobileRedirectUrl, removeQueryParams(url, 'code'));
        } else {
            redirectUrl = customRedirectUrl ? url : replaceString(env.wechat.mp.redirectUrl, removeQueryParams(url, 'code'));
        }
        // 认证链接
        const oauthUrl = replaceString(env.wechat.mp.appId, encodeURIComponent(redirectUrl));
        //
        log(`WeChatService redirect to ${oauthUrl}.`);
        await BrowserService.open(
            oauthUrl,
            () => {
                //
            },
            () => {
                //
            },
        );
    }

    /**
     * 获取微信签名
     */
    async getSignature(url: string): Promise<R<WeChatSignatureApiResult>> {
        log(`WeChatService getSignature...`);
        return await getWeChatSignatureApi({
            url: url,
        });
    }

    /**
     * 获取微信配置
     */
    async config(): Promise<void> {
        log(`WeChatService config...`);
        const url = location.href.split('#')[0];
        return new Promise<void>((resolve, reject) => {
            this.getSignature(url)
                .then((res) => {
                    if (res.data) {
                        log(`WeChatService config...`);
                        wx.config({
                            debug: false,
                            appId: res.data.appId,
                            timestamp: res.data.timestamp,
                            nonceStr: res.data.nonceStr,
                            signature: res.data.signature,
                            jsApiList: [
                                'chooseImage',
                                'getLocalImgData',
                                'previewImage',
                                'scanQRCode',
                                'getLocation',
                                'uploadImage',
                                'downloadImage',
                                'onMenuShareTimeline',
                                'onMenuShareAppMessage',
                                'updateAppMessageShareData',
                                'updateTimelineShareData',
                                'onMenuShareWeibo',
                                'invoke',
                            ],
                        });
                        wx.ready(() => {
                            log(`WeChatService config ready.`);
                            resolve();
                        });
                        wx.error((res: any) => {
                            log(`WeChatService config error.`);
                            console.log(res);
                        });
                    } else {
                        reject();
                    }
                })
                .catch((result: any) => {
                    log(`WeChatService config ready.`);
                    log(result);
                    reject();
                });
        });
    }

    /**
     * 调用微信摄像头拍照
     */
    async getPhoto(): Promise<any> {
        log(`WeChatService getPhoto...`);
        return this.takePhotos('camera', 1);
    }

    /**
     * 调用微信摄像头从相册选择图片
     */
    async choosePhotos(imageCount = 1): Promise<any> {
        log(`WeChatService choosePhotos...`);
        return this.takePhotos('album', imageCount);
    }

    /**
     * 拍照
     */
    async takePhotos(source: any = '', limit = 1): Promise<any> {
        log(`WeChatService takePhotos...`);
        return new Promise((resolve, reject) => {
            if (wx) {
                log(`WeChatService available.`);
                wx.ready(() => {
                    wx.chooseImage({
                        sourceType: [source],
                        count: limit,
                        sizeType: ['original', 'compressed'],
                        success: (res: any) => {
                            log(`WeChatService takePhotos success.`);
                            log(res);
                            resolve(res.localIds);
                        },
                        fail: () => {
                            log(`WeChatService takePhotos fail.`);
                            reject();
                        },
                    });
                });
            } else {
                log(`WeChatService not available.`);
                reject();
            }
        });
    }

    /**
     * 扫描二维码
     */
    scanQRCode(): Promise<any> {
        log(`WeChatService scanQRCode...`);
        return new Promise((resolve, reject) => {
            console.log(wx);
            if (wx) {
                log(`WeChatService wx available.`);
                wx.ready(() => {
                    log(`WeChatService wx ready.`);
                    wx.scanQRCode({
                        needResult: 1,
                        scanType: ['qrCode', 'barCode'],
                        success: (res: any) => {
                            log(`WeChatService scanQRCode success.`);
                            log(res);
                            resolve(res.resultStr);
                        },
                        cancel: (res: any) => {
                            log(`WeChatService scanQRCode cancel.`);
                            log(res);
                            reject(res);
                        },
                        error: (res: any) => {
                            log(`WeChatService scanQRCode error.`);
                            log(res);
                            if (res.errMsg.indexOf('function_not_exist') > 0) {
                                console.log('版本过低请升级');
                            }
                            reject(res);
                        },
                    });
                });
            } else {
                log(`WeChatService wx not available.`);
                reject();
            }
        });
    }
}

export default new WeChatService();
