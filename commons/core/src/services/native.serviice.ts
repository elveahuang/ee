import { CapacitorBarcodeScanner, CapacitorBarcodeScannerOptions, CapacitorBarcodeScannerScanResult } from '@capacitor/barcode-scanner';
import { Camera, CameraResultType } from '@capacitor/camera';
import { Capacitor } from '@capacitor/core';
import { log } from '@commons/core/utils';

class NativeService {
    /**
     * 是否可用
     */
    async available(): Promise<boolean> {
        return true;
    }

    /**
     * 判断当前是否是运行在原生环境
     */
    isNative(): boolean {
        return Capacitor.isNativePlatform();
    }

    /**
     * 判断当前是否是运行在原生苹果设备
     */
    isIos(): boolean {
        return 'ios' === Capacitor.getPlatform();
    }

    /**
     * 判断当前是否是运行在原生安卓设备
     */
    isAndroid(): boolean {
        return 'android' === Capacitor.getPlatform();
    }

    /**
     * 判断当前是否是运行在浏览器
     */
    isWeb(): boolean {
        return 'web' === Capacitor.getPlatform();
    }

    async config() {
        log(`NativeService config...`);
    }

    /**
     * 拍照
     */
    async takePhotos(source = '', limit = 1): Promise<any> {
        log(`NativeService takePhotos...`);
        log(source);
        log(limit);

        return new Promise<string>(async (resolve, reject) => {
            const available = Capacitor.isPluginAvailable('Camera');
            if (available) {
                log(`NativeService Camera available.`);
                await Camera.getPhoto({
                    quality: 90,
                    allowEditing: true,
                    resultType: CameraResultType.Uri,
                });
                resolve('');
            } else {
                log(`NativeService Camera not available.`);
                reject();
            }
        });
    }

    /**
     * 扫描二维码
     */
    scanQRCode(): Promise<string> {
        log(`NativeService scanQRCode...`);

        return new Promise<string>(async (resolve, reject) => {
            const available = Capacitor.isPluginAvailable('BarcodeScanner');
            if (available) {
                log(`NativeService BarcodeScanner available.`);
                const options: CapacitorBarcodeScannerOptions = {} as CapacitorBarcodeScannerOptions;
                const result: CapacitorBarcodeScannerScanResult = await CapacitorBarcodeScanner.scanBarcode(options);
                if (result.ScanResult) {
                    log(`LarkService scanQRCode success.`);
                    log(result);
                    resolve(result.ScanResult);
                } else {
                    log(`LarkService scanQRCode fail.`);
                    log(result);
                    reject();
                }
            } else {
                log(`NativeService BarcodeScanner not available.`);
                reject();
            }
        });
    }
}

export default new NativeService();
