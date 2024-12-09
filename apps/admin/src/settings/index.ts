import { config } from '@/settings/config.ts';
import { extend } from '@/settings/extend.ts';
import { Settings } from '@commons/core/settings/admin';
import { getAppCopyright, getAppLogo, getAppTitle, getAppVersion } from '@commons/core/utils';

export const settings: Settings = {
    app: {
        getTitle: (): string => {
            return extend?.app?.title ?? config.app.title ?? getAppTitle();
        },
        getVersion: (): string => {
            return extend?.app?.version ?? config.app.version ?? getAppVersion();
        },
        getLogo: (): string => {
            return extend?.app?.logo ?? config.app.logo ?? getAppLogo();
        },
        getCopyright: (): string[] => {
            return extend?.app?.copyright ?? config.app.copyright ?? getAppCopyright();
        },
        getLogoutSuccessUrl: () => {
            return extend?.app?.logout?.success ?? config?.app?.logout?.success;
        },
    },
};
