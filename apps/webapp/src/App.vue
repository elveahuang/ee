<template>
    <el-config-provider :locale="locale">
        <router-view />
    </el-config-provider>
</template>

<script setup lang="ts">
import { useAppStore } from '@commons/core/store';
import { log } from '@commons/core/utils';
import { Locale } from '@commons/core/utils/locale';
import { Theme } from '@commons/core/utils/theme';
import { getAppLocale, setAppDarkMode, setAppLocale, setAppTheme } from '@commons/webapp/utils';
import type { Language } from 'element-plus/es/locale';
import { computed, onMounted, watch } from 'vue';

const appStore = useAppStore();
const appLocale = computed<Locale>(() => appStore.locale);
const appTheme = computed<Theme>(() => appStore.theme);
const appDark = computed(() => appStore.dark);
const locale = computed<Language>(() => getAppLocale(appStore.locale));

// 监听深色模式
watch(
    appDark,
    (value: boolean) => {
        setAppDarkMode(value);
    },
    {
        immediate: true,
    },
);
// 监听主题
watch(
    appTheme,
    (value: Theme) => {
        setAppTheme(value);
    },
    {
        immediate: true,
    },
);
// 监听语言
watch(
    appLocale,
    (value: Locale): void => {
        setAppLocale(value);
    },
    {
        immediate: true,
    },
);

onMounted(async (): Promise<void> => {
    log(`Component <<App>> mounted.`);
});
</script>
