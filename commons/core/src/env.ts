import { dingtalkOAuthUrl, larkOAuthUrl, weChatOAuthUrl, weComOAuthUrl } from '@commons/core/constants';
import { isEmpty, isEqual } from 'radash';

declare type Environment = {
    /**
     * 路径
     */
    base: string;
    /**
     * 环境名称
     */
    mode: string;
    /**
     * 应用名称
     */
    title: string;
    /**
     * 默认语言
     */
    locale: string;
    /**
     * 服务器地址
     */
    server: string;
    /**
     * 服务器地址
     */
    socket: {
        enabled: boolean;
        server: string;
    };
    /**
     * Mock
     */
    mock: {
        enabled: boolean;
    };
    /**
     * Debug
     */
    debug: {
        enabled: boolean;
    };
    /**
     * NProgress
     */
    progress: {
        enabled: boolean;
    };
    /**
     * XDebug
     */
    xdebug: {
        enabled: boolean;
        key: string;
    };
    /**
     * 路由设置
     */
    router: {
        mode: string;
        base: string;
    };
    /**
     * WeChat
     */
    wechat: {
        /**
         * 微信小程序
         */
        ma: {
            enabled: boolean;
        };
        /**
         * 微信公众号
         */
        mp: {
            enabled: boolean;
            appId: string;
            oauthUrl: string;
            webappRedirectUrl: string;
            mobileRedirectUrl: string;
            redirectUrl: string;
        };
        /**
         * 企业微信
         */
        cp: {
            enabled: boolean;
            oauthPrivateEnabled: boolean;
            appId: string;
            agentId: string;
            oauthUrl: string;
            webappRedirectUrl: string;
            mobileRedirectUrl: string;
            redirectUrl: string;
        };
    };
    /**
     * 飞书
     */
    lark: {
        enabled: boolean;
        appId: string;
        oauthUrl: string;
        webappRedirectUrl: string;
        mobileRedirectUrl: string;
        redirectUrl: string;
    };
    /**
     * 钉钉
     */
    dingtalk: {
        enabled: boolean;
        corpId: string;
        appId: string;
        oauthUrl: string;
        webappRedirectUrl: string;
        mobileRedirectUrl: string;
        redirectUrl: string;
    };
    /**
     * VConsole
     */
    console: {
        enabled: boolean;
    };
    /**
     * Auth
     */
    auth: {
        user: {
            type: string;
        };
        oauth: {
            enabled: boolean;
            clientId: string;
            clientSecret: string;
        };
    };
    /**
     * Custom
     */
    custom: {
        enabled: boolean;
    };
};

/**
 * 环境配置
 */
export const env: Environment = {
    base: import.meta.env.VITE_APP_BASE ?? '/',
    mode: import.meta.env.VITE_APP_MODE ?? 'development',
    title: import.meta.env.VITE_APP_TITLE ?? '',
    locale: import.meta.env.VITE_APP_LOCALE ?? '',
    server: import.meta.env.VITE_APP_SERVER ?? '',
    socket: {
        enabled: isEqual(import.meta.env.VITE_APP_WEB_SOCKET_ENABLED, 'true'),
        server: import.meta.env.VITE_APP_WEB_SOCKET_SERVER ?? '',
    },
    debug: {
        enabled: isEqual(import.meta.env.VITE_APP_DEBUG_ENABLED, 'true'),
    },
    progress: {
        enabled: isEqual(import.meta.env.VITE_APP_PROGRESS_ENABLED, 'true'),
    },
    mock: {
        enabled: isEqual(import.meta.env.VITE_APP_MOCK_ENABLED, 'true'),
    },
    xdebug: {
        enabled: isEqual(import.meta.env.VITE_APP_XDEBUG_ENABLED, 'true'),
        key: import.meta.env.VITE_APP_XDEBUG_KEY ?? 'XDebug',
    },
    console: {
        enabled: isEqual(import.meta.env.VITE_APP_CONSOLE_ENABLED, 'true'),
    },
    router: {
        mode: import.meta.env.VITE_APP_ROUTER_MODE ?? 'history',
        base: import.meta.env.VITE_APP_BASE ?? '',
    },
    wechat: {
        ma: {
            enabled: false,
        },
        mp: {
            enabled: false,
            appId: import.meta.env.VITE_APP_WECHAT_MP_APP_ID ?? '',
            oauthUrl: isEmpty(import.meta.env.VITE_APP_WECHAT_MP_OAUTH_URL) ? weChatOAuthUrl : import.meta.env.VITE_APP_WECHAT_CP_OAUTH_URL,
            webappRedirectUrl: import.meta.env.VITE_APP_WECHAT_MP_WEBAPP_REDIRECT_URL ?? '',
            mobileRedirectUrl: import.meta.env.VITE_APP_WECHAT_MP_MOBILE_REDIRECT_URL ?? '',
            redirectUrl: import.meta.env.VITE_APP_WECHAT_MP_REDIRECT_URL ?? '',
        },
        cp: {
            enabled: isEqual(import.meta.env.VITE_APP_WECHAT_CP_ENABLED, 'true'),
            oauthPrivateEnabled: isEqual(import.meta.env.VITE_APP_WECHAT_CP_OAUTH_PRIVATE_ENABLED, 'true'),
            appId: import.meta.env.VITE_APP_WECHAT_CP_APP_ID ?? '',
            agentId: import.meta.env.VITE_APP_WECHAT_CP_AGENT_ID ?? '',
            oauthUrl: isEmpty(import.meta.env.VITE_APP_WECHAT_CP_OAUTH_URL) ? weComOAuthUrl : import.meta.env.VITE_APP_WECHAT_CP_OAUTH_URL,
            webappRedirectUrl: import.meta.env.VITE_APP_WECHAT_CP_WEBAPP_REDIRECT_URL ?? '',
            mobileRedirectUrl: import.meta.env.VITE_APP_WECHAT_CP_MOBILE_REDIRECT_URL ?? '',
            redirectUrl: import.meta.env.VITE_APP_WECHAT_CP_REDIRECT_URL ?? '',
        },
    },
    lark: {
        enabled: isEqual(import.meta.env.VITE_APP_LARK_ENABLED, 'true'),
        appId: import.meta.env.VITE_APP_LAKR_APP_ID ?? '',
        oauthUrl: import.meta.env.VITE_APP_LAKR_OAUTH_URL ?? larkOAuthUrl,
        webappRedirectUrl: import.meta.env.VITE_APP_LARK_WEBAPP_REDIRECT_URL ?? '',
        mobileRedirectUrl: import.meta.env.VITE_APP_LARK_MOBILE_REDIRECT_URL ?? '',
        redirectUrl: import.meta.env.VITE_APP_LARK_REDIRECT_URL ?? '',
    },
    dingtalk: {
        enabled: false,
        corpId: import.meta.env.VITE_APP_DINGTALK_APP_ID ?? '',
        appId: import.meta.env.VITE_APP_DINGTALK_APP_ID ?? '',
        oauthUrl: import.meta.env.VITE_APP_DINGTALK_OAUTH_URL ?? dingtalkOAuthUrl,
        webappRedirectUrl: import.meta.env.VITE_APP_DINGTALK_WEBAPP_REDIRECT_URL ?? '',
        mobileRedirectUrl: import.meta.env.VITE_APP_DINGTALK_MOBILE_REDIRECT_URL ?? '',
        redirectUrl: import.meta.env.VITE_APP_DINGTALK_REDIRECT_URL ?? '',
    },
    auth: {
        user: {
            type: import.meta.env.VITE_APP_OAUTH_USER_TYPE ?? 'user',
        },
        oauth: {
            enabled: isEqual(import.meta.env.VITE_APP_OAUTH_ENABLED, 'true'),
            clientId: import.meta.env.VITE_APP_OAUTH_CLIENT_ID ?? '',
            clientSecret: import.meta.env.VITE_APP_OAUTH_CLIENT_SECRET ?? '',
        },
    },
    custom: {
        enabled: isEqual(import.meta.env.VITE_APP_CUSTOM_ENABLED, 'true'),
    },
};

export default env;
