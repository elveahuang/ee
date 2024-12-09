import { isEmpty } from 'radash';
//
import { WeChatSignatureApiResult, getWeComSignatureApi } from '@commons/core/api/wechat';
import env from '@commons/core/env';
import BrowserService from '@commons/core/services/browser.service.ts';
import PlatformService from '@commons/core/services/platform.serviice';
import { R } from '@commons/core/types';
import { log, pop, replaceString } from '@commons/core/utils';

declare let wx: any;

class WeComService {
    /**
     * 是否可用
     */
    async available(): Promise<boolean> {
        return true;
    }

    /**
     * 判断当前是否是运行在企微客户端
     */
    isWeCom(): boolean {
        const ua = window.navigator.userAgent.toLowerCase();
        return ua.indexOf('micromessenger') != -1 && ua.indexOf('wxwork') != -1;
    }

    /**
     * 判断企微当前是否启用
     */
    isWeComEnabled(): boolean {
        return env.wechat.cp.enabled;
    }

    /**
     * 企微登录认证
     * @param url
     * @param customRedirectUrl
     */
    async auth(url = '', customRedirectUrl = false): Promise<any> {
        log(`WeComService auth...`);
        // 重定向地址
        let redirectUrl;
        if (PlatformService.isWebPlatform() && !isEmpty(env.wechat.cp.webappRedirectUrl)) {
            log(`WeComService webapp auth. ${env.wechat.cp.webappRedirectUrl}`);
            redirectUrl = customRedirectUrl ? url : replaceString(env.wechat.cp.webappRedirectUrl, url);
        } else if (PlatformService.isWapPlatform() && !isEmpty(env.wechat.cp.mobileRedirectUrl)) {
            log(`WeComService mobile auth. ${env.wechat.cp.mobileRedirectUrl}`);
            redirectUrl = customRedirectUrl ? url : replaceString(env.wechat.cp.mobileRedirectUrl, url);
        } else {
            log(`WeComService auth. ${env.wechat.cp.redirectUrl}`);
            redirectUrl = customRedirectUrl ? url : replaceString(env.wechat.cp.redirectUrl, url);
        }
        log(`WeComService auth redirectUrl. ${redirectUrl}`);
        // 认证链接
        const oauthUrl = replaceString(
            env.wechat.cp.oauthUrl,
            env.wechat.cp.appId,
            encodeURIComponent(redirectUrl),
            env.wechat.cp.agentId,
            env.wechat.cp.oauthPrivateEnabled ? 'snsapi_privateinfo' : 'snsapi_base',
        );
        log(`WeComService redirect to ${oauthUrl}.`);
        //
        pop(oauthUrl);

        //
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
     * 获取企微签名
     */
    async getSignature(url: string): Promise<R<WeChatSignatureApiResult>> {
        log(`WeComService getSignature...`);
        return await getWeComSignatureApi({
            url: url,
        });
    }

    /**
     * 获取企业微信配置
     */
    async config(): Promise<void> {
        log(`WeComService config...`);
        const url = location.href.split('#')[0];
        return new Promise<void>((resolve, reject) => {
            this.getSignature(url)
                .then((res) => {
                    if (res.data) {
                        log(`WeComService config signature.`);
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
                            log(`WeComService config ready.`);
                            resolve();
                        });
                        wx.error((res: any) => {
                            log(`WeComService config error.`);
                            console.log(res);
                        });
                    } else {
                        reject();
                    }
                })
                .catch((result: any) => {
                    log(`WeComService config ready.`);
                    log(result);
                    reject();
                });
        });
    }

    /**
     * 调用微信摄像头拍照
     */
    async getPhoto(): Promise<any> {
        log(`WeComService getPhoto...`);
        return this.takePhotos('camera', 1);
    }

    /**
     * 调用微信摄像头从相册选择图片
     */
    async choosePhotos(imageCount = 1): Promise<any> {
        log(`WeComService choosePhotos...`);
        return this.takePhotos('album', imageCount);
    }

    /**
     * 拍照
     */
    async takePhotos(source = '', limit = 1): Promise<any> {
        log(`WeComService takePhotos...`);
        return new Promise((resolve, reject) => {
            if (wx) {
                log(`WeComService wx available.`);
                wx.ready(() => {
                    wx.chooseImage({
                        sourceType: [source],
                        count: limit,
                        sizeType: ['original', 'compressed'],
                        success: (res: any) => {
                            log(`WeComService takePhotos success.`);
                            log(res);
                            resolve(res.localIds);
                        },
                        fail: () => {
                            log(`WeComService takePhotos fail.`);
                            reject();
                        },
                    });
                });
            } else {
                log(`WeComService not available.`);
                reject();
            }
        });
    }

    /**
     * 扫描二维码
     */
    scanQRCode(): Promise<any> {
        log(`WeComService scanQRCode...`);
        return new Promise((resolve, reject) => {
            console.log(wx);
            if (wx) {
                log(`WeComService wx available.`);
                wx.ready(() => {
                    log(`WeComService wx ready.`);
                    wx.scanQRCode({
                        needResult: 1,
                        scanType: ['qrCode', 'barCode'],
                        success: (res: any) => {
                            log(`WeComService scanQRCode success.`);
                            log(res);
                            resolve(res.resultStr);
                        },
                        cancel: (res: any) => {
                            log(`WeComService scanQRCode cancel.`);
                            log(res);
                            reject(res);
                        },
                        error: (res: any) => {
                            log(`WeComService scanQRCode error.`);
                            log(res);
                            if (res.errMsg.indexOf('function_not_exist') > 0) {
                                console.log('版本过低请升级');
                            }
                            reject(res);
                        },
                    });
                });
            } else {
                log(`WeComService wx not available.`);
                reject();
            }
        });
    }
}

export default new WeComService();
