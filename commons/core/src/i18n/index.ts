import { useAppStoreExternal } from '@commons/core/store';
import { log } from '@commons/core/utils';
import { setHtmlLang } from '@commons/core/utils/html';
import { locales, LocaleType } from '@commons/core/utils/locale';
import { isFunction } from 'radash';
import { App } from 'vue';
import type { I18nOptions } from 'vue-i18n';
import { createI18n } from 'vue-i18n';

/**
 * 国际化自定义配置定义
 */
export class I18nConfig {
    message?: Function;
}

/**
 * 国际化配置
 */
export let config: I18nConfig = {};

/**
 * 国际化实例
 */
export let i18n: ReturnType<typeof createI18n>;

export const loadCommonMessages = async (locale: string): Promise<any> => {
    const messages = await import(`./locales/${locale.toLowerCase()}/common.json`);
    return messages.default ?? {};
};

export const loadExtraMessages = async (locale: string): Promise<any> => {
    const messages = await import(`./locales/${locale.toLowerCase()}/extra.json`);
    return messages.default ?? {};
};

export const loadMessages = async (locale: string): Promise<any> => {
    if (isFunction(config?.message)) {
        log('Use extend loadMessages.');
        return config.message(locale);
    } else {
        log('Use standard loadMessages.');
        return {
            common: await loadCommonMessages(locale),
            extra: await loadExtraMessages(locale),
        };
    }
};

export const createI18nOptions = async (): Promise<I18nOptions> => {
    const { locale } = useAppStoreExternal();
    await setHtmlLang(locale);
    return {
        sync: true,
        legacy: false,
        allowComposition: true,
        locale: locale,
        fallbackLocale: locale,
        messages: {
            [locale]: await loadMessages(locale),
        },
        availableLocales: locales.map((val: LocaleType) => val.locale),
        silentTranslationWarn: false,
        missingWarn: false,
        silentFallbackWarn: false,
    };
};

export const setupI18n = async (app: App, i18nConfig?: I18nConfig): Promise<void> => {
    log(`I18n initialize...`);
    config = i18nConfig;
    i18n = createI18n(await createI18nOptions());
    app.use(i18n);
};

export const useI18nExternal = (): any => {
    return i18n.global;
};
