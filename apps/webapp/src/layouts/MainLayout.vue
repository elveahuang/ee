<template>
    <div class="app-layout">
        <div class="app-layout-header">
            <div class="app-layout-header-container">
                <div class="app-layout-logo">
                    <div class="logo">
                        <span>{{ title }}</span>
                    </div>
                </div>
                <div class="app-layout-nav">
                    <app-header-menu />
                </div>
                <div class="app-layout-tools">
                    <el-space>
                        <app-dark-toggle />
                        <app-theme-dropdown />
                        <app-locale-dropdown />
                        <app-avatar-dropdown />
                    </el-space>
                </div>
            </div>
        </div>

        <div class="app-layout-content">
            <div class="app-layout-content-container">
                <router-view />
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { AppAvatarDropdown, AppHeaderMenu } from '@/layouts/components';
import { settings } from '@/settings';
import { menus } from '@/utils/menus';
import { useUserStore } from '@commons/core/store';
import { log } from '@commons/core/utils';
import { getMenuItems, getMenuOpenKeys } from '@commons/core/utils/menu';
import { AppDarkToggle, AppLocaleDropdown, AppThemeDropdown } from '@commons/webapp/components';
import { computed, onMounted, ref } from 'vue';
import { RouterView, useRoute, useRouter } from 'vue-router';

const route = useRoute();
const router = useRouter();
const { isAuthenticated, user, logout } = useUserStore();
const title = computed<string>(() => settings.app.getTitle());
const siderMenuItems = computed(() => getMenuItems(menus));
const openKeys = ref<string[]>(getMenuOpenKeys(menus, route.path));
const selectedKeys = ref<string[]>([route.path]);
const handleMenuChange = (path: string) => {
    router.push(path);
};
const handleLogout = () => {
    logout().then(() => {
        router.push(settings.app.getLogoutSuccessUrl());
    });
};

onMounted(() => {
    log('Component - <<MainLayout>> is mounted.');
});
</script>

<style lang="scss" scoped>
@import './MainLayout';
</style>
