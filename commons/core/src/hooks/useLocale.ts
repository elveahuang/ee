import { i18n, loadMessages } from '@commons/core/i18n';
import { useAppStoreExternal } from '@commons/core/store';
import { setHtmlLang } from '@commons/core/utils/html';
import { Locale } from '@commons/core/utils/locale';

export const setI18NLocale = async (locale: Locale): Promise<void> => {
    if (i18n.mode === 'legacy') {
        i18n.global.locale = locale;
    } else {
        (i18n.global.locale as any).value = locale;
    }
};

export const setI18NLocaleMessages = async (locale: Locale): Promise<void> => {
    i18n.global.setLocaleMessage(locale, await loadMessages(locale));
};

export const useLocale = (): { setLocale: (locale: Locale) => Promise<void> } => {
    return {
        setLocale: async (locale: Locale): Promise<void> => {
            const { setLocale } = useAppStoreExternal();
            await setI18NLocaleMessages(locale);
            await setI18NLocale(locale);
            setLocale(locale);
            setHtmlLang(locale);
        },
    };
};
