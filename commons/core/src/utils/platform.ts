/**
 * =====================================================================================================================
 * 设备平台
 * =====================================================================================================================
 */

/**
 * 设备平台枚举
 */
export enum Platform {
    PC = 'PC',
    WAP = 'WAP',
    IOS = 'IOS',
    ANDROID = 'ANDROID',
    FEI_SHU = 'FEI_SHU',
    WECHAT = 'WECHAT',
    WORK_WECHAT = 'WORK_WECHAT',
    DING_TALK = 'DING_TALK',
}

/**
 * 设备平台类型
 */
export type PlatformType = {
    direction: Platform;
    title: string;
    description: string;
};

/**
 * 默认设备平台
 */
export const defaultPlatform: Platform = Platform.PC;

/**
 * 内置设备平台
 */
export const platforms: Array<PlatformType> = [
    {
        direction: Platform.PC,
        title: '电脑浏览器',
        description: '电脑浏览器',
    },
    {
        direction: Platform.WAP,
        title: '手机浏览器',
        description: '手机浏览器',
    },
];

export default platforms;
