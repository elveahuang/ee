/**
 * 深色模式
 */
export enum DarkMode {
    AUTO = 'auto',
    DARK = 'dark',
    LIGHT = 'light',
}

/**
 * 深色模式 - 类型定义
 */
export type DarkModeType = {
    mode: DarkMode;
    title: string;
    label: string;
    description: string;
};

/**
 * 默认深色模式
 */
export const defaultDarkMode: DarkMode = DarkMode.LIGHT;

/**
 * 内置深色模式
 */
export const darkModes: DarkModeType[] = [
    {
        mode: DarkMode.AUTO,
        title: '跟随系统',
        label: 'common.label_dark_mode_auto',
        description: '跟随系统',
    },
    {
        mode: DarkMode.DARK,
        title: '深色模式',
        label: 'common.label_dark_mode_dark',
        description: '深色模式',
    },
    {
        mode: DarkMode.LIGHT,
        title: '浅色模式',
        label: 'common.label_dark_mode_light',
        description: '浅色模式',
    },
];

/**
 * 获取深色主题策略
 */
export const getDarkModeLabel = (dark: DarkMode | string): string => {
    const t: DarkModeType = darkModes.find((t: DarkModeType): boolean => t.mode === dark);
    if (t) {
        return t.label;
    }
    return getDarkModeLabel(defaultDarkMode);
};

/**
 * 获取深色主题策略
 */
export const getDarkMode = (mode: DarkMode | string): DarkMode => {
    const t: DarkModeType = darkModes.find((t: DarkModeType): boolean => t.mode === mode);
    if (t) {
        return t.mode;
    }
    return defaultDarkMode;
};

/**
 * 根据策略获取黑色主题的值
 * 1. 浅色 - 直接返回false
 * 2. 黑色 - 直接返回true
 * 3. 自动 - 根据当前浏览器设置
 */
export const getDarkValue = (mode: DarkMode | string): boolean => {
    const darkMode: DarkMode = getDarkMode(mode);
    if (DarkMode.LIGHT === darkMode) {
        return false;
    } else if (DarkMode.DARK === darkMode) {
        return true;
    } else if (DarkMode.AUTO === darkMode) {
        const mql: MediaQueryList = window.matchMedia('(prefers-color-scheme: dark)');
        return mql.matches;
    }
};

/**
 * 设置黑色主题
 */
export const setDark = (dark: boolean): void => {
    if (dark) {
        document.documentElement.classList.add('dark');
        document.body.classList.add('dark');
    } else {
        document.documentElement.classList.remove('dark');
        document.body.classList.remove('dark');
    }
};
