import 'vue-router';

declare module 'vue-router' {
    interface RouteMeta {
        description?: string;
        requiresAuth?: boolean;
        roles?: string[];
        authorities?: string[];
        icon?: string;
        locale?: string;
    }
}
