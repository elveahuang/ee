import { applicationVersion } from '@commons/core/constants';
import { logo } from '@commons/core/constants/images';
import env from '@commons/core/env';
import { useI18nExternal } from '@commons/core/i18n';
import { router } from '@commons/core/router';
import { Credentials } from '@commons/core/types';
import { getYear } from '@commons/core/utils/date.ts';
import { isEmpty } from 'radash';

export { Icon as VIcon } from '@iconify/vue';

/**
 * 获取应用标题
 */
export const getAppTitle = (): string => {
    const { t } = useI18nExternal();
    return env.custom.enabled ? t('app.title') : t('common.title');
};

/**
 * 获取应用版本
 */
export const getAppVersion = (): string => {
    return applicationVersion;
};

/**
 * 获取应用版本
 */
export const getAppLogo = (): string => {
    return logo;
};

/**
 * 获取应用版权信息
 */
export const getAppCopyright = (): string[] => {
    const { t } = useI18nExternal();
    const curYear: number = getYear();
    return env.custom.enabled
        ? [t('app.copyright_1', { year: curYear }), t('app.copyright_2'), t('app.copyright_3')]
        : [t('common.copyright_1', { year: curYear }), t('common.copyright_2'), t('common.copyright_3')];
};

/**
 * 输出日志
 */
export function pop(text: any): void {
    if (env.debug.enabled) {
        alert(text);
    }
}

/**
 * 输出日志
 */
export function log(log: any): void {
    if (env.debug.enabled) {
        console.log(log);
    }
}

/**
 * 从文件对象获取本地图片链接
 */
export function getObjectURL(file: File): string {
    let url: string = null;
    if (URL !== undefined) {
        url = URL.createObjectURL(file);
    }
    return url;
}

/**
 *
 */
export function formatUrl(url: string): string {
    return url;
}

/**
 * 替换字符串
 */
export const replaceString = (str: string, ...val: string[]) => {
    for (let index: number = 0; index < val.length; index++) {
        str = str.replace(`{${index}}`, val[index]);
    }
    return str;
};

/**
 * 替换字符串
 */
export const removeQueryParams = (url: string, ...queryParams: string[]): string => {
    if (!isEmpty(url)) {
        const splitUrl: string[] = url.split('?');
        const path: string = splitUrl[0];
        if (splitUrl[1] && URLSearchParams && queryParams.length) {
            const urlSearchParams: URLSearchParams = new URLSearchParams(splitUrl[1]);
            queryParams.forEach((queryParam: string): void => {
                urlSearchParams.delete(queryParam);
            });
            const parsedUrl: string = urlSearchParams.toString();
            return parsedUrl ? path + '?' + parsedUrl : path;
        }
    }
    return url;
};

/**
 * 替换字符串
 */
export const goBack = (url: string = ''): void => {
    if (isEmpty(url)) {
        router.back();
    } else {
        router.push(url).then();
    }
};

/**
 * 默认用户登录凭证，方便开发调试用
 */
export const credentials: Credentials = {
    username: 'admin',
    password: 'admin',
};
