import { CapacitorConfig } from '@capacitor/cli';

const config: CapacitorConfig = {
    appId: 'cn.elvea.app',
    appName: 'mobile',
    webDir: 'dist',
    android: {
        buildOptions: {
            keystorePath: '../../../tools/android/app.jks',
            keystorePassword: '123456',
            keystoreAlias: 'app',
            keystoreAliasPassword: '123456',
            releaseType: 'APK',
        },
    },
};

export default config;
