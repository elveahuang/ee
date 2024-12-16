import { routes } from '@/router';
import { settings } from '@/settings';
import { env } from '@commons/core/env';
import { useAppStoreExternal } from '@commons/core/store';
import { log } from '@commons/core/utils';
import { XBanner, XDivider, XLayout, XNavbar, XSpace } from '@commons/webapp/components/builder/components';
import { setAppTitle, setupApp } from '@commons/webapp/utils';
import { App } from 'vue';

export const setup = async (app: App): Promise<void> => {
    log(`App for webapp initialize...`);
    await setupApp(app, { routes: routes, base: env.router.base, mode: env.router.mode }, {}, initializeApp);
};

export const initializeApp = async (app: App): Promise<any> => {
    // 初始化自定义组件
    app.component(XBanner.name, XBanner);
    app.component(XNavbar.name, XNavbar);
    app.component(XLayout.name, XLayout);
    app.component(XDivider.name, XDivider);
    app.component(XSpace.name, XSpace);
    // 初始化应用并设置应用标题
    const { initialize } = useAppStoreExternal();
    await initialize().catch((): void => {
        log(`App for webapp initialize failed.`);
    });
    await setAppTitle(settings.app.getTitle()).then();
};
