import { routes } from '@/router';
import { settings } from '@/settings';
import { env } from '@commons/core/env';
import { useAppStoreExternal } from '@commons/core/store';
import { useSocketStoreExternal } from '@commons/core/store/socket';
import { log } from '@commons/core/utils';
import { setAppTitle, setupApp } from '@commons/mobile/utils';
import '@vant/touch-emulator';
import { App } from 'vue';

export const setup = async (app: App): Promise<void> => {
    log(`App for mobile initialize...`);
    await setupApp(app, { routes: routes, base: env.router.base, mode: env.router.mode }, {}, initializeApp);
};

export const initializeApp = async (): Promise<any> => {
    // 初始化应用并设置应用标题
    const { initialize } = useAppStoreExternal();
    await initialize().catch((): void => {
        log(`App for admin initialize failed.`);
    });
    await setAppTitle(settings.app.getTitle()).then();
    // 初始化WebSocket
    const { initializeWebSocket } = useSocketStoreExternal();
    if (env.socket.enabled) {
        await initializeWebSocket().then();
    }
};
