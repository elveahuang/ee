export interface Config {
    app?: {
        title?: string;
        version?: string;
        logo?: string;
        copyright?: string[];
        login?: {
            success: string;
        };
        logout?: {
            success: string;
        };
        tabs?: Tab[];
    };
    me?: {
        apps?: Application[];
    };
}

export interface Tab {
    id?: number;
    label?: string;
    title?: string;
    description?: string;
    icon?: string;
    link?: string;
    active?: boolean;
}

export interface Application {
    id?: number;
    label?: string;
    title?: string;
    description?: string;
    icon?: string;
    link?: string;
    active?: boolean;
}

export interface Settings {
    app?: {
        getVersion: () => string;
        getTitle: () => string;
        getLogo: () => string;
        getCopyright: () => string[];
        getLoginSuccessUrl: () => string;
        getLogoutSuccessUrl: () => string;
        getTabs: () => Tab[];
    };
    me: {
        getApps: () => Application[];
    };
}
