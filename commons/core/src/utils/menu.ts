import { useI18nExternal } from '@commons/core/i18n';

/**
 * 菜单
 */
export class Menu {
    /**
     * 菜单标识
     */
    key: string;
    /**
     * 菜单多语言文本
     */
    label?: string;
    /**
     * 菜单标题
     */
    title?: string;
    /**
     * 菜单图标
     */
    icon?: string;
    /**
     * 菜单链接
     */
    path?: string;
    /**
     * 菜单是否需要检查权限
     */
    authenticated?: boolean;
    /**
     * 菜单是否启用
     */
    enabled?: boolean;
    /**
     * 菜单对应权限
     */
    authorities?: string | string[] | null | undefined;
    /**
     * 子菜单
     */
    items?: Menu[];
}

export const getMenuItems = (menus: Menu[]): Menu[] => {
    return menus
        .filter((e: Menu) => e.enabled === undefined || e.enabled)
        .map((m: Menu) => {
            return getMenuItem(m);
        });
};

export function getMenuItem(menu: Menu): Menu {
    const { t } = useI18nExternal();
    menu.title = menu?.title ?? t(menu.label);
    if (menu.items && menu.items.length && menu.items.length > 0) {
        menu.items = getMenuItems(menu.items);
    }
    return menu;
}

export const getMenuRootKeys = (menus: Menu[]): string[] => {
    if (menus?.length > 0) {
        return menus.map((menu: Menu) => menu.key);
    }
    return [];
};

export const getMenuOpenKeys = (menus: Menu[], path: string): string[] => {
    return menus.filter((menu: Menu) => menu?.items?.find((child: Menu): boolean => child.path === path)).map((n: Menu) => n.key);
};
