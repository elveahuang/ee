import { CapacitorConfig } from '@capacitor/cli';

const config: CapacitorConfig = {
    appId: 'cn.elvea.app',
    appName: 'mobile',
    webDir: 'dist',
    server: {
        url: 'http://192.168.0.5:8082',
        cleartext: true,
    },
};

export default config;
