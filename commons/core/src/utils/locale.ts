import { isEmpty } from 'radash';

/**
 * 语言枚举
 */
export enum Locale {
    ZH_CN = 'zh_CN',
    ZH_TW = 'zh_TW',
    EN_US = 'en_US',
}

/**
 * 语言类型
 */
export type LocaleType = {
    locale: Locale;
    availableValues: Array<string>;
    title: string;
    label: string;
    description: string;
};

/**
 * 默认语言
 */
export const defaultLocale: Locale = Locale.ZH_CN;

/**
 * 内置语言
 */
export const locales: Array<LocaleType> = [
    {
        locale: Locale.ZH_CN,
        availableValues: ['zh_cn', 'zh-cn', 'zh'],
        title: '简体中文',
        label: 'common.label_language_zh_cn',
        description: '简体中文',
    },
    {
        locale: Locale.ZH_TW,
        availableValues: ['zh_tw', 'zh-tw'],
        title: '繁体中文',
        label: 'common.label_language_zh_tw',
        description: '繁体中文',
    },
    {
        locale: Locale.EN_US,
        availableValues: ['en_us', 'en-us', 'en'],
        title: '美式英语',
        label: 'common.label_language_en_us',
        description: '美式英语',
    },
];

/**
 * 根据语言标识获取语言枚举类型
 * @param language 语言标识
 */
export const getLocaleLabel = (language: Locale): string => {
    const locale: LocaleType = locales.find((l: LocaleType) => l.availableValues.includes(language.toLowerCase()));
    if (locale) {
        return locale.label;
    }
    return getLocaleLabel(defaultLocale);
};

/**
 * 根据语言标识获取语言枚举类型
 */
export const getLocale = (language: string): Locale => {
    const locale: LocaleType = locales.find((l: LocaleType) => l.availableValues.includes(language.toLowerCase()));
    if (locale) {
        return locale.locale;
    }
    return defaultLocale;
};

/**
 * 获取浏览器本地语言枚举类型
 */
export const getBrowserLocale = (): Locale => {
    const language: string = navigator.language;
    if (isEmpty(language)) {
        return getLocale(language);
    }
    return defaultLocale;
};

export default locales;
