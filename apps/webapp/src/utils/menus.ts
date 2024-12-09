import { Menu } from '@commons/core/utils/menu';

export const menus: Menu[] = [
    {
        key: 'home',
        label: 'home',
        path: '/',
    },
    {
        key: 'product',
        label: 'product',
        path: '/product',
    },
    {
        key: 'announcement',
        label: 'announcement',
        path: '/announcement',
    },
    {
        key: 'about',
        label: 'about',
        path: '/about',
    },
];

export const userCenterMenus: Menu[] = [
    {
        key: 'user-center',
        label: 'common.user_pages_user_center_title',
        icon: 'mdi:shield-account-outline',
        path: '/user-center',
    },
    {
        key: 'user-notice',
        label: 'common.user_label_my_notice',
        icon: 'mdi:message-outline',

        path: '/user-center/notice',
    },
    {
        key: 'user-profile',
        label: 'common.user_label_my_profile',
        icon: 'mdi:information-outline',
        path: '/user-center/account',
    },
    {
        key: 'user-password',
        label: 'common.user_pages_change_password_title',
        icon: 'mdi:lock-outline',
        path: '/user-center/password',
    },
];
