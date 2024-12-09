import { logo } from '@commons/core/constants/images';
import { Config } from '@commons/core/settings/mobile';

export const config: Config = {
    app: {
        logo: logo,
        login: {
            success: '/',
        },
        logout: {
            success: '/',
        },
        tabs: [
            { id: 1, icon: 'mdi:home', label: 'common.label_home', link: '/tabs/home' },
            { id: 2, icon: 'mdi:apps', label: 'common.label_discover', link: '/tabs/discover' },
            { id: 3, icon: 'mdi:cog', label: 'common.label_me', link: '/tabs/me' },
        ],
    },
    me: {
        apps: [
            { id: 1, icon: 'mdi:information-variant', label: 'common.label_about', link: '/about' },
            { id: 2, icon: 'mdi:information-variant', label: 'common.label_about', link: '/about' },
            { id: 3, icon: 'mdi:information-variant', label: 'common.label_about', link: '/about' },
            { id: 4, icon: 'mdi:information-variant', label: 'common.label_about', link: '/about' },
            { id: 5, icon: 'mdi:information-variant', label: 'common.label_about', link: '/about' },
            { id: 6, icon: 'mdi:information-variant', label: 'common.label_about', link: '/about' },
            { id: 7, icon: 'mdi:information-variant', label: 'common.label_about', link: '/about' },
            { id: 8, icon: 'mdi:information-variant', label: 'common.label_about', link: '/about' },
        ],
    },
};
