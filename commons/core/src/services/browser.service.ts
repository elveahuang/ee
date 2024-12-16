import { Browser } from '@capacitor/browser';
import { Capacitor } from '@capacitor/core';

class BrowserService {
    /**
     *  是否可用
     */
    async available(): Promise<boolean> {
        return true;
    }

    /**
     * 打开链接
     */
    async open(url: string, onFinished: () => void, onLoaded: () => void): Promise<void> {
        if (Capacitor.isNativePlatform()) {
            await Browser.open({
                url: url,
            });
            await Browser.addListener('browserFinished', onFinished);
            await Browser.addListener('browserPageLoaded', onLoaded);
        } else {
            return new Promise<void>((resolve) => {
                window.open(url, '_self');
                resolve();
            });
        }
    }

    /**
     * 关闭链接
     */
    async close(): Promise<void> {
        await Browser.removeAllListeners();
    }
}

export default new BrowserService();
