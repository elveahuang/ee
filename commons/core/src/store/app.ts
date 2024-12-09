import { initializeApi, InitializeApiResult } from '@commons/core/api';
import { store } from '@commons/core/store';
import { R } from '@commons/core/types';
import { DarkMode, defaultDarkMode } from '@commons/core/utils/dark';
import { defaultDirection, Direction } from '@commons/core/utils/direction';
import { defaultLocale, Locale } from '@commons/core/utils/locale';
import { defaultTheme, Theme } from '@commons/core/utils/theme';
import { defineStore } from 'pinia';

export interface AppState {
    /**
     * 名称
     */
    title: string;
    /**
     * 版本号
     */
    version: string;
    /**
     * 页面是否正在加载
     */
    loading: boolean;
    /**
     * 语言
     */
    locale: Locale;
    /**
     * 主题
     */
    theme: Theme;
    /**
     * 对齐方式
     */
    direction: Direction;
    /**
     * 深色模式
     */
    darkMode: DarkMode;
    /**
     * 启用深色模式
     */
    dark: boolean;
    /**
     * 左边导航侧边栏
     */
    sidebar: {
        /**
         * 是否收起
         */
        collapsed: boolean;
        /**
         * 小型化
         */
        mini: boolean;
    };
    /**
     * 右边控制侧边栏
     */
    controlSidebar: {
        /**
         * 是否收起
         */
        collapsed: boolean;
        /**
         * 小型化
         */
        mini: boolean;
    };
    /**
     * 登录相关
     */
    login: {
        /**
         * 是否启用登录验证码
         */
        captcha: boolean;
    };
}

export const useAppStore = defineStore('app', {
    persist: true,
    state: (): AppState => ({
        title: '',
        version: '',
        loading: true,
        locale: defaultLocale,
        theme: defaultTheme,
        direction: defaultDirection,
        darkMode: defaultDarkMode,
        dark: false,
        sidebar: {
            collapsed: false,
            mini: false,
        },
        controlSidebar: {
            collapsed: false,
            mini: false,
        },
        login: {
            captcha: false,
        },
    }),
    getters: {
        isLoginCaptchaEnabled: (state: AppState) => state.login.captcha,
    },
    actions: {
        setTitle(title: string): void {
            this.title = title;
        },
        setVersion(title: string): void {
            this.title = title;
        },
        setLocale(locale: Locale): void {
            this.locale = locale;
        },
        setTheme(theme: Theme): void {
            this.theme = theme;
        },
        setDirection(direction: Direction): void {
            this.direction = direction;
        },
        setDarkMode(darkMode: DarkMode): void {
            this.darkMode = darkMode;
        },
        setDark(value: boolean): void {
            this.dark = value;
        },
        setLoading(value: boolean): void {
            this.loading = value;
        },
        toggleSidebar(): void {
            this.sidebar.collapsed = !this.sidebar.collapsed;
        },
        toggleControlSidebar(): void {
            this.controlSidebar.collapsed = !this.controlSidebar.collapsed;
        },
        /**
         * 应用初始化
         */
        async initialize(): Promise<R<InitializeApiResult>> {
            return initializeApi().then((result: R<InitializeApiResult>): R<InitializeApiResult> => {
                if (result.code == '200') {
                    this.login.captcha = result.data.loginCaptchaEnabled;
                }
                return result;
            });
        },
    },
});

export const useAppStoreExternal = () => {
    return useAppStore(store);
};
