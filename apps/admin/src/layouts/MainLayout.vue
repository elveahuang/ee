<template>
    <el-container class="app-layout app-layout-main">
        <el-header class="app-layout-header">
            <div class="app-layout-header-container">
                <div class="app-layout-trigger">
                    <app-icon
                        :size="24"
                        :icon="sidebar.collapsed ? 'ant-design:menu-unfold-outlined' : 'ant-design:menu-fold-outlined'"
                        class="trigger"
                        @click="toggleSidebar"
                    />
                </div>
                <div class="app-layout-logo">
                    <div class="logo">
                        <span>{{ title }}</span>
                    </div>
                </div>
                <div class="app-layout-nav" />
                <div class="app-layout-tools">
                    <app-dark-toggle />
                    <app-theme-dropdown />
                    <app-locale-dropdown />
                    <app-avatar-dropdown />
                </div>
            </div>
        </el-header>

        <el-container class="app-layout-container">
            <el-aside
                v-model:collapsed="sidebar.collapsed"
                :trigger="null"
                :class="sidebar.collapsed ? 'app-layout-sider collapsed' : 'app-layout-sider'"
            >
                <app-sider-menu class="app-layout-menu" />
            </el-aside>
            <el-main :class="sidebar.collapsed ? 'app-layout-content collapsed' : 'app-layout-content'">
                <div class="app-layout-content-container">
                    <router-view />
                </div>
            </el-main>
        </el-container>
    </el-container>
</template>

<script setup lang="ts">
import { AppAvatarDropdown, AppSiderMenu } from '@/layouts/components';
import { settings } from '@/settings';
import { useAppStore } from '@commons/core/store';
import { log } from '@commons/core/utils';
import { AppDarkToggle, AppIcon, AppLocaleDropdown, AppThemeDropdown } from '@commons/webapp/components';
import { computed, onMounted } from 'vue';
import { RouterView } from 'vue-router';

const { sidebar, toggleSidebar } = useAppStore();
const title = computed<string>(() => settings.app.getTitle());

onMounted(() => {
    log('Component - <<MainLayout>> is mounted.');
});
</script>

<style lang="scss" scoped>
@import './MainLayout';
</style>
