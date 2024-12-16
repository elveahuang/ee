import { useI18nExternal } from '@commons/core/i18n';
import { log } from '@commons/core/utils/index.ts';

// 数值转化为带单位的数值
export const convertToChineseUnit = (value: any, digit = 2) => {
    const { t } = useI18nExternal();
    const units = [t('app.label_ten_thousand'), t('app.label_Billion'), t('app.label_omen'), t('app.label_giga'), t('app.label_tera')];

    // 如果数值为负数，将其转换为正数进行处理
    const absValue = Math.abs(value);

    if (absValue < 10000) {
        let result = Number(absValue.toFixed(digit));
        if (result == 0) {
            return digit == 2 ? '0.00' : '0';
        }
        result = value >= 0 ? result : -result;
        return `${result}`;
    }
    // 遍历单位列表，找到最近单位
    let nearestUnit = units[0];
    let nearestValue = absValue;
    let j = 0;
    for (let i = 0; i < units.length; i++) {
        const unitValue = Math.pow(10, j + 4);
        const quotient = Number((absValue / unitValue).toFixed(digit));
        if (quotient <= 9999) {
            nearestValue = quotient;
            nearestUnit = units[i];
            break;
        }
        j += 4;
    }
    // 将最近单位的数值转换为正确的符号
    const resultValue = value >= 0 ? nearestValue : -nearestValue;
    return `${resultValue}${nearestUnit}`;
};

// 带单位的数值字符串转换为数值
export const convertToNumber = (value: string) => {
    if (value == '0.00') {
        return 0;
    }
    return Number(value.substring(0, value.length - 1));
};

// 数值转化为百分比
export const convertToPercentage = (value: any, digit = 2) => {
    const absValue = Math.abs(value);
    log('绝对值');
    let result = Number(absValue.toFixed(digit));
    if (result == 0) {
        return '0.00%';
    }
    result = value >= 0 ? result : -result;
    return `${result}%`;
};

// 数值扩大百倍后转换为百分比
export const convertToPercentageForHundred = (value: any) => {
    const absValue = Math.abs(value);
    if (absValue > 1) {
        const result = Number(absValue.toFixed(2));
        value = result >= 0 ? result : -result;
        return `${value}%`;
    }
    return value.toLocaleString('en-US', {
        style: 'percent',
        minimumFractionDigits: 2,
        maximumFractionDigits: 2,
    });
};

// 文本省略处理
export const convertStringToCiphertext = (string: string, number: number, start: number, end: number) => {
    const startString = string.substring(0, start);
    const endString = string.substring(string.length - end, string.length);
    let star = '';
    for (let i = 0; i < number; i++) {
        star += '*';
    }
    return startString + star + endString;
};

//动态文本颜色
export const getClassByValue = (value: number, standard: number) => {
    if (value > standard) {
        return '!text-green-500';
    }
    return '!text-red-500';
};
