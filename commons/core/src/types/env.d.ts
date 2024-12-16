/// <reference types="vite/client" />
import 'vue-router';

export {};

declare module '*.vue' {
    import { DefineComponent } from 'vue';
    const component: DefineComponent<{}, {}, any>;
    export default component;
}

interface ImportMetaEnv {
    VITE_HOST: string;
    VITE_PORT: number;
    VITE_APP_BASE: string;
    VITE_APP_MODE: string;
    VITE_APP_TITLE: string;
    VITE_APP_LOCALE: string;
    VITE_APP_SERVER: string;
    VITE_APP_ROUTER_MODE: string;
    VITE_APP_WEB_SOCKET_SERVER: string;
    VITE_APP_WEB_SOCKET_ENABLED: string;
    VITE_APP_DEBUG_ENABLED: boolean;
    VITE_APP_PROGRESS_ENABLED: boolean;
    VITE_APP_MOCK_ENABLED: boolean;
    VITE_APP_XDEBUG_ENABLED: boolean;
    VITE_APP_XDEBUG_KEY: string;
    VITE_APP_CONSOLE_ENABLED: boolean;
    VITE_APP_CUSTOM_ENABLED: boolean;
    VITE_APP_OAUTH_USER_TYPE: string;
}

declare global {
    interface ImportMeta {
        readonly env: ImportMetaEnv;
    }
}

declare module '*.module.scss' {
    const classes: ScssModuleClasses;
    export default classes;
}

declare module 'vue-router' {
    interface RouteMeta {
        requiresAuth: boolean;
        authorities: string[] | string;
        description: string;
    }
}
