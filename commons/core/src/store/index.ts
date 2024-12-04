import { useAppStore, useAppStoreExternal } from '@commons/core/store/app';
import { useSocketStore, useSocketStoreExternal } from '@commons/core/store/socket';
import { useUserStore, useUserStoreExternal } from '@commons/core/store/user';
import { log } from '@commons/core/utils';
import { createPinia } from 'pinia';
import Logger from 'pinia-logger';
import PersistedState from 'pinia-plugin-persistedstate';
import type { App } from 'vue';

export let store: ReturnType<typeof createPinia>;

export const setupStore = async (app: App<Element>): Promise<void> => {
    log(`Store initialize...`);
    store = createPinia();
    store.use(PersistedState);
    store.use(
        Logger({
            expanded: false,
            disabled: true,
        }),
    );
    app.use(store);
};

export { useAppStore, useAppStoreExternal, useSocketStore, useSocketStoreExternal, useUserStore, useUserStoreExternal };
