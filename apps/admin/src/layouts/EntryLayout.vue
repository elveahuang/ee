<template>
    <el-container class="app-layout app-layout-entry">
        <el-header class="app-layout-header">
            <div class="app-layout-header-container">
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
                </div>
            </div>
        </el-header>
        <el-main class="app-layout-content">
            <div class="app-layout-content-container">
                <slot />
            </div>
        </el-main>
        <el-footer class="app-layout-footer">
            <div class="app-layout-footer-container">
                <div v-for="(text, index) in copyright" :key="index" class="app-text" v-html="text" />
            </div>
        </el-footer>
    </el-container>
</template>

<script setup lang="ts">
import { settings } from '@/settings';
import { log } from '@commons/core/utils';
import { AppDarkToggle, AppLocaleDropdown, AppThemeDropdown } from '@commons/webapp/components';
import { computed, onMounted } from 'vue';

const title = computed<string>(() => settings.app.getTitle());
const copyright = computed<string[]>(() => settings.app.getCopyright());

onMounted(() => {
    log('Component <<EntryLayout>> is mounted.');
});
</script>

<style lang="scss" scoped>
@import './EntryLayout';
</style>
