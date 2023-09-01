import i18next from 'i18next';
import ChainedBackend from 'i18next-chained-backend';
import { log } from '@/utils';

export const setupI18n = async () => {
    log('i18next init...');
    if (!i18next.isInitialized) {
        await i18next.use(ChainedBackend).init({
            fallbackLng: 'en_us',
            lng: 'en_us',
            ns: 'label',
            backend: {
                backends: [],
                backendOptions: [
                    {
                        expirationTime: 7 * 24 * 60 * 60 * 1000,
                    },
                ],
            },
        });
    }
    window.i18n = i18next;
    log('i18next initialized.');
};
