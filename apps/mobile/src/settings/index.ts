import { config } from '@/settings/config';
import { extend } from '@/settings/extend.ts';
import { useI18nExternal } from '@commons/core/i18n';
import { Application, Settings, Tab } from '@commons/core/settings/mobile';
import { getAppCopyright, getAppLogo, getAppTitle, getAppVersion } from '@commons/core/utils';
import { isEmpty } from 'radash';

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
        getLoginSuccessUrl: () => {
            return extend?.app?.login?.success ?? config?.app?.login?.success;
        },
        getLogoutSuccessUrl: () => {
            return extend?.app?.logout?.success ?? config?.app?.logout?.success;
        },
        getTabs: (): Tab[] => {
            const { t } = useI18nExternal();
            const tabs: Tab[] = extend?.app?.tabs ?? config.app.tabs;
            if (isEmpty(tabs)) {
                return [];
            }
            return tabs.map((tab: Tab): Tab => {
                if (isEmpty(tab.title)) {
                    tab.title = t(tab.label);
                }
                return tab;
            });
        },
    },
    me: {
        getApps: (): Application[] => {
            const { t } = useI18nExternal();
            const apps: Application[] = extend?.me?.apps ?? config.me.apps;
            if (isEmpty(apps)) {
                return [];
            }
            return apps.map((app: Application): Application => {
                if (isEmpty(app.title)) {
                    app.title = t(app.label);
                }
                return app;
            });
        },
    },
};
