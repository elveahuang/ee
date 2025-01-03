import { setupGlobalDirective } from '@commons/core/directives';
import { setupGlobalFilter } from '@commons/core/filters';
import { I18nConfig, setupI18n } from '@commons/core/i18n';
import { RouterConfig, router, setupRouter } from '@commons/core/router';
import { setupStore, useAppStoreExternal } from '@commons/core/store';
import { log } from '@commons/core/utils';
import { setDark } from '@commons/core/utils/dark';
import { setHtmlTitle } from '@commons/core/utils/html.ts';
import { HttpConfig, setupHttp } from '@commons/core/utils/http';
import { Locale } from '@commons/core/utils/locale';
import { setupDotLottie } from '@commons/core/utils/lottie.ts';
import { Theme, ThemeType, getThemeType, setTheme, setThemeCssVar } from '@commons/core/utils/theme';
import { TinyColor } from '@ctrl/tinycolor';
import { default as FcDesigner } from '@form-create/designer';
import { default as FormCreate } from '@form-create/element-ui';
import ElementPlus, { ElMessage as message } from 'element-plus';
import type { Language } from 'element-plus/es/locale';
import enUs from 'element-plus/es/locale/lang/en';
import zhCn from 'element-plus/es/locale/lang/zh-cn';
import zhTw from 'element-plus/es/locale/lang/zh-tw';
import { isFunction } from 'radash';
import { App } from 'vue';

export const toast = async (content: string): Promise<void> => {
    message(content);
};

export const showMessage = async (options: any, callback?: Function): Promise<void> => {
    message(options.message);
};

export const setupApp = async (app: App, routerConfig?: RouterConfig, i18nConfig?: I18nConfig, initializeApp?: Function): Promise<void> => {
    // 设置动效
    await setupDotLottie().then();
    // 基础组件
    app.use(ElementPlus);
    // Form Create
    app.use(FormCreate);
    app.use(FcDesigner);
    // 设置状态
    await setupStore(app).then();
    // 设置国际化
    await setupI18n(app, i18nConfig).then();
    // 设置路由
    await setupRouter(app, routerConfig).then();
    // 设置网络请求
    await setupHttp({
        toast: toast,
        excludes: ['/oauth/token'],
    } as HttpConfig).then();
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
 * 获取应用语言
 */
export const getAppLocale = (locale: Locale): Language => {
    let appLocale: Language;
    switch (locale) {
        case Locale.EN_US:
            appLocale = enUs;
            break;
        case Locale.ZH_TW:
            appLocale = zhTw;
            break;
        default:
            appLocale = zhCn;
    }
    return appLocale;
};

/**
 * 设置应用语言
 */
export const setAppLocale = (locale: Locale): void => {
    log(`setAppLocale - Current locale - ${locale}`);
};

/**
 * 获取框架深色模式
 */
export const setAppDarkMode = (value: boolean): void => {
    setDark(value);
};

/**
 * 设置应用主题
 */
export const setAppTheme = (value: Theme): void => {
    // 设置主题
    setTheme(value);
    // 设置主题变量
    const themeType: ThemeType = getThemeType(value);
    const map: Map<string, string> = new Map();
    map.set('--el-color-primary', themeType.primaryColor);
    map.set('--el-color-primary-light-1', new TinyColor(themeType.primaryColor).tint(10).toString());
    map.set('--el-color-primary-light-2', new TinyColor(themeType.primaryColor).tint(20).toString());
    map.set('--el-color-primary-light-3', new TinyColor(themeType.primaryColor).tint(30).toString());
    map.set('--el-color-primary-light-4', new TinyColor(themeType.primaryColor).tint(40).toString());
    map.set('--el-color-primary-light-5', new TinyColor(themeType.primaryColor).tint(50).toString());
    map.set('--el-color-primary-light-6', new TinyColor(themeType.primaryColor).tint(60).toString());
    map.set('--el-color-primary-light-7', new TinyColor(themeType.primaryColor).tint(70).toString());
    map.set('--el-color-primary-light-8', new TinyColor(themeType.primaryColor).tint(80).toString());
    map.set('--el-color-primary-light-9', new TinyColor(themeType.primaryColor).tint(90).toString());
    setThemeCssVar(map);
};

/**
 * 设置应用标题
 */
export const setAppTitle = async (title: string): Promise<void> => {
    const { setTitle } = useAppStoreExternal();
    setTitle(title);
    setHtmlTitle(title);
};
