import 'bootstrap';
//
import '@/main.scss';
//
import Utils, { domReady, log, windowReady } from '@/utils';
import App from '@/modules/app';
import Home from '@/modules/home';
import { setupI18n } from '@/utils/i18n';
import { setupHttp } from '@/utils/http';

const startApp = async () => {
    await setupHttp();
    await setupI18n();
};

window.Utils = Utils;
window.App = App;
window.Home = Home;

domReady(async () => {
    log(`domReady.`);
});

windowReady(async () => {
    log(`windowReady.`);
    await startApp().then(() => {
        log(`Application initialized.`);
    });
});
