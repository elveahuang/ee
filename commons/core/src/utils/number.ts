/**
 * 数字格式化选项
 */
export type FormatOptions = {
    locale?: string;
    style?: string;
    currency?: string;
    minimumFractionDigits?: number;
    maximumFractionDigits?: number;
    default?: string | number;
};

/**
 * 默认数字格式化选项
 */
export const deFaultNumberOptions: FormatOptions = {
    locale: 'en-US',
    minimumFractionDigits: 2,
    maximumFractionDigits: 8,
    default: 0,
};

/**
 * 格式化数字输出文本
 */
export const formatNumber = (number: number, options: FormatOptions = deFaultNumberOptions): string => {
    return new Intl.NumberFormat(options.locale, { ...options } as Intl.NumberFormatOptions).format(number);
};

export const formatNumberWithUnit = (number: number, options: FormatOptions = deFaultNumberOptions): string => {
    const units: string[] = ['', '万', '亿', '万亿'];
    const k: number = 10000;
    if (number < k) {
        return formatNumber(number, options);
    }
    const i: number = Math.floor(Math.log(number) / Math.log(k));
    return `${formatNumber(number / Math.pow(k, i), { ...options, ...{ maximumFractionDigits: 2 } })}${units[i]}`;
};

/**
 * 默认百分比格式化选项
 */
export const deFaultPercentOptions: FormatOptions = {
    locale: 'en-US',
    style: 'percent',
    minimumFractionDigits: 2,
    maximumFractionDigits: 4,
};

/**
 * 格式化百分比输出文本
 */
export const formatPercent = (number: number, options: FormatOptions = {}): string => {
    return new Intl.NumberFormat(options.locale, { ...deFaultPercentOptions, ...options } as Intl.NumberFormatOptions).format(number);
};

/**
 * 默认货币格式化选项
 */
export const deFaultFormatCurrencyOptions: FormatOptions = {
    locale: 'en-US',
    style: 'currency',
    currency: 'USD',
    minimumFractionDigits: 2,
    maximumFractionDigits: 8,
};

/**
 * 格式化货币输出文本
 */
export const formatCurrency = (number: number, options: FormatOptions = deFaultFormatCurrencyOptions): string => {
    return new Intl.NumberFormat(options.locale, { ...options } as Intl.NumberFormatOptions).format(number);
};

/**
 * 格式化货币输出文本，附带单位
 */
export const formatCurrencyWithUnit = (number: number, options: FormatOptions = deFaultFormatCurrencyOptions): string => {
    const units: string[] = ['', '万', '亿', '万亿'];
    const k: number = 10000;
    if (number < k) {
        return formatCurrency(number, options);
    }
    const i: number = Math.floor(Math.log(number) / Math.log(k));
    return `${formatCurrency(number / Math.pow(k, i), { ...options, ...{ maximumFractionDigits: 2 } })}${units[i]}`;
};
