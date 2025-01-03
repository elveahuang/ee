import { Locale } from '@commons/core/utils/locale';

export const setHtmlLang = (locale: Locale | string): void => {
    document.querySelector('html')?.setAttribute('lang', locale);
};

export const setHtmlTitle = (title: string): void => {
    document.title = title;
};
