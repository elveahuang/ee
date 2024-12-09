<template>
    <a-layout class="app-layout">
        <a-layout-header class="app-layout-header">
            <div class="app-layout-header-container">
                <div class="app-layout-logo">
                    <div class="logo">
                        <span>{{ title }}</span>
                    </div>
                </div>
                <div class="app-layout-nav">
                    <el-button shape="circle" @click="router.push('/')">
                        <template #icon>
                            <app-icon icon="mdi:home-outline" />
                        </template>
                    </el-button>
                </div>
                <div class="app-layout-tools">
                    <el-space size="middle">
                        <app-dark-toggle />
                        <app-theme-dropdown />
                        <app-locale-dropdown />
                        <app-avatar-dropdown />
                    </el-space>
                </div>
            </div>
        </a-layout-header>
        <a-layout class="app-layout-container">
            <a-layout-sider class="app-layout-sider">
                <el-card>
                    <div class="flex justify-center">
                        <app-icon :width="120" :height="120" icon="mdi:account-outline" />
                    </div>
                    <a-menu v-model:open-keys="menuOpenKeys" v-model:selected-keys="menuSelectedKeys" class="app-menu" theme="light" mode="inline">
                        <a-menu-item v-for="menu in menuItems" :key="menu.path" @click="handleMenuChange(menu.path)">
                            <app-icon :icon="menu.icon" />
                            {{ menu.title }}
                        </a-menu-item>
                    </a-menu>
                </el-card>
            </a-layout-sider>
            <a-layout-content class="app-layout-content">
                <div class="app-layout-content-container">
                    <router-view />
                </div>
            </a-layout-content>
        </a-layout>
    </a-layout>
</template>

<script setup lang="ts">
import { AppAvatarDropdown } from '@/layouts/components';
import { settings } from '@/settings';
import { userCenterMenus } from '@/utils/menus.ts';
import { log } from '@commons/core/utils';
import { getMenuItems, getMenuOpenKeys } from '@commons/core/utils/menu.ts';
import { AppDarkToggle, AppIcon, AppLocaleDropdown, AppThemeDropdown } from '@commons/webapp/components';
import { computed, onMounted, ref } from 'vue';
import { RouterView, useRoute, useRouter } from 'vue-router';

const route = useRoute();
const router = useRouter();
const title = computed<string>(() => settings.app.getTitle());
const menuItems = computed(() => getMenuItems(userCenterMenus));
const menuOpenKeys = ref<string[]>(getMenuOpenKeys(userCenterMenus, route.path));
const menuSelectedKeys = ref<string[]>([route.path]);
const handleMenuChange = (path: string) => {
    router.push(path);
};

onMounted(() => {
    log('Component - <<UserLayout>> is mounted.');
});
</script>

<style lang="scss" scoped>
@import './UserLayout';
</style>
