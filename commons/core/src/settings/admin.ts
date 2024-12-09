export interface Config {
    app?: {
        title?: string;
        version?: string;
        logo?: string;
        copyright?: string[];
        logout?: {
            success: string;
        };
    };
}

export interface Settings {
    app: {
        getVersion: () => string;
        getTitle: () => string;
        getLogo: () => string;
        getCopyright: () => string[];
        getLogoutSuccessUrl: () => string;
    };
}
