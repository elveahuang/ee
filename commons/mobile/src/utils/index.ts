import { setupGlobalDirective } from '@commons/core/directives';
import { setupGlobalFilter } from '@commons/core/filters';
import { I18nConfig, setupI18n } from '@commons/core/i18n';
import { router, RouterConfig, setupRouter } from '@commons/core/router';
import { setupStore, useAppStoreExternal } from '@commons/core/store';
import { log } from '@commons/core/utils';
import { setDark } from '@commons/core/utils/dark.ts';
import { setHtmlTitle } from '@commons/core/utils/html.ts';
import { HttpConfig, setupHttp } from '@commons/core/utils/http.ts';
import { Locale } from '@commons/core/utils/locale';
import { setupDotLottie } from '@commons/core/utils/lottie.ts';
import { defaultTheme, getThemeType, setTheme, Theme, ThemeType } from '@commons/core/utils/theme.ts';
import { isFunction } from 'radash';
import {
    ConfigProvider,
    ConfigProviderTheme,
    ConfigProviderThemeVars,
    DialogOptions,
    showConfirmDialog,
    showDialog,
    Toast,
    Locale as VLocale,
} from 'vant';
import enUS from 'vant/es/locale/lang/en-US';
import zhCN from 'vant/es/locale/lang/zh-CN';
import zhTW from 'vant/es/locale/lang/zh-TW';
import { App } from 'vue';

export const showMessage = (options: DialogOptions, callback?: Function): void => {
    options = { ...options, ...{ theme: 'round-button', allowHtml: true } };
    showDialog(options).then(async (): Promise<void> => {
        if (isFunction(callback)) {
            callback();
        }
    });
};

export const showConfirm = (options: DialogOptions, confirmCallback?: Function, cancelCallback?: Function): void => {
    options = { ...options, ...{ theme: 'round-button', allowHtml: true } };
    showConfirmDialog(options)
        .then(async (): Promise<void> => {
            if (isFunction(confirmCallback)) {
                confirmCallback();
            }
        })
        .catch((): void => {
            if (isFunction(cancelCallback)) {
                cancelCallback();
            }
        });
};

export const toast = async (message: any): Promise<void> => {
    showMessage({
        width: '80%',
        message: message,
        theme: 'round-button',
    });
};

export const setupApp = async (app: App, routerConfig?: RouterConfig, i18nConfig?: I18nConfig, initializeApp?: Function): Promise<void> => {
    // 设置动效
    await setupDotLottie().then();
    // 设置应用专有配置和组件
    app.use(ConfigProvider);
    app.use(Toast);
    // 设置状态
    await setupStore(app);
    // 设置国际化
    await setupI18n(app, i18nConfig);
    // 设置路由
    await setupRouter(app, routerConfig);
    // 设置网络请求
    await setupHttp({
        toast: toast,
        excludes: ['/oauth/token'],
    } as HttpConfig);
    // 设置全局指令
    await setupGlobalDirective(app);
    // 设置全局过滤器
    await setupGlobalFilter(app);
    // 执行自定义初始化函数
    if (isFunction(initializeApp)) {
        await initializeApp(app);
    }
    // 挂载应用
    router.isReady().then((): void => {
        app.mount('#app');
    });
};

/**
 * 设置应用语言
 */
export const setAppLocale = (locale: Locale): void => {
    log(`setAppLocale - Current locale - ${locale}`);
    switch (locale) {
        case Locale.EN_US:
            VLocale.use('en-US', enUS);
            break;
        case Locale.ZH_CN:
            VLocale.use('zh-CN', zhCN);
            break;
        case Locale.ZH_TW:
            VLocale.use('zh-TW', zhTW);
            break;
    }
};

/**
 * 获取应用深色主题
 */
export const getAppDarkMode = (value: boolean): ConfigProviderTheme => {
    return value ? 'dark' : 'light';
};

/**
 * 设置应用深色主题
 */
export const setAppDarkMode = (value: boolean): void => {
    setDark(value);
};

/**
 * 获取应用主题
 */
export const getAppTheme = (theme: Theme = defaultTheme): ConfigProviderThemeVars => {
    const t: ThemeType = getThemeType(theme);
    return {
        primaryColor: t.primaryColor,
    };
};

/**
 * 设置应用主题
 */
export const setAppTheme = (value: Theme): void => {
    setTheme(value);
};

/**
 * 设置应用标题
 */
export const setAppTitle = async (title: string): Promise<void> => {
    const { setTitle } = useAppStoreExternal();
    setTitle(title);
    setHtmlTitle(title);
};
