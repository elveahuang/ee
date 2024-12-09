import { useI18nExternal } from '@commons/core/i18n';
import dayjs, { Dayjs } from 'dayjs';
import { isEmpty, isEqual } from 'radash';

export declare type DATE_RANGE_PICKER_TYPE = 'datetime' | 'date' | 'time';
export const DATE_TIME_PATTEN: string = 'YYYY-MM-DD HH:mm:ss';
export const DATE_PATTEN: string = 'YYYY-MM-DD';
export const SIMPLE_DATE_PATTEN: string = 'MM-DD';
export const TIME_PATTEN: string = 'HH:mm:ss';
export const YEAR_PATTEN: string = 'YYYY';
export const MONTH_PATTEN: string = 'MM';
export const SIMPLE_TIME_PATTEN: string = 'HH:mm';
export const MAX_YEAR: number = 9999;
export const MIN_YEAR: number = 1970;

export const DAYS_OF_WEEK = {
    zhCN: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
    zhTW: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
    enUS: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'],
};

/**
 * 获取当前年份
 */
export function getYear(): number {
    return dayjs().year();
}

/**
 * 解析完整日期
 */
export function parseDatetime(obj: Dayjs | Date | number | string): Date {
    if (!isEmpty(obj)) {
        const dt: Dayjs = dayjs(obj, DATE_TIME_PATTEN);
        if (dt.year() == MAX_YEAR || dt.year() == MIN_YEAR) {
            return null;
        }
        return dt.toDate();
    }
    return null;
}

/**
 * 解析日期
 */
export function parseDate(obj: Dayjs | Date | number | string): Date {
    if (!isEmpty(obj)) {
        const dt: Dayjs = dayjs(obj, DATE_PATTEN);
        if (dt.year() == MAX_YEAR || dt.year() == MIN_YEAR) {
            return null;
        }
        return dt.toDate();
    }
    return null;
}

/**
 * 解析时间
 */
export function parseTime(obj: Dayjs | Date | number | string): Date {
    if (!isEmpty(obj)) {
        return dayjs(obj, TIME_PATTEN).toDate();
    }
    return null;
}

/**
 * 格式化日期
 */
export function formatDatetime(obj: Dayjs | Date | number | string, defaultValue: string = '--'): string {
    return format(obj, DATE_TIME_PATTEN, defaultValue);
}

/**
 * 格式化日期
 */
export function formatDate(obj: Dayjs | Date | number | string, defaultValue: string = '--'): string {
    return format(obj, DATE_PATTEN, defaultValue);
}

/**
 * 格式化时间
 */
export function formatTime(obj: Dayjs | Date | number | string, defaultValue: string = '--'): string {
    return format(obj, TIME_PATTEN, defaultValue);
}

/**
 * 格式化年份
 */
export function formatYear(obj: Dayjs | Date | number | string, defaultValue: string = '--'): string | number {
    return format(obj, YEAR_PATTEN, defaultValue);
}

/**
 * 格式化月份
 */
export function formatMonth(obj: Dayjs | Date | number | string, defaultValue: string = '--'): string | number {
    return format(obj, MONTH_PATTEN, defaultValue);
}

/**
 * 格式化日期
 */
export function format(obj: Dayjs | Date | number | string, pattern: string = DATE_TIME_PATTEN, defaultValue: string = '--'): string {
    if (obj !== undefined) {
        const dt: Dayjs = dayjs(obj);
        if (dt.year() == MAX_YEAR || dt.year() == MIN_YEAR) {
            return defaultValue;
        }
        return dt.format(pattern);
    }
    return defaultValue;
}

/**
 * 格式化日期
 */
export function formatDatetimePeriod(from: Dayjs | Date | number | string, to: Dayjs | Date | number | string): string {
    const { t } = useI18nExternal();

    const formText: string = formatDatetime(from);
    const toText: string = formatDatetime(to);
    if (!isEqual(formText, '--') && !isEqual(toText, '--')) {
        return t('common.label_date_period_display_format_1', { start: formText, end: toText });
    } else if (!isEqual(formText, '--') && isEqual(toText, '--')) {
        return t('common.label_date_period_display_format_2', { start: formText });
    } else if (isEqual(formText, '--') && !isEqual(toText, '--')) {
        return t('common.label_date_period_display_format_3', { end: toText });
    } else if (isEqual(formText, '--') && isEqual(toText, '--')) {
        return t('common.label_date_period_display_format_4');
    }
    return '--';
}
