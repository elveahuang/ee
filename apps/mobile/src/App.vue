<template>
    <van-config-provider :theme="theme" :theme-vars="themeVars" theme-vars-scope="global">
        <router-view />
    </van-config-provider>
</template>
<script setup lang="ts">
import { useAppStore } from '@commons/core/store';
import { log } from '@commons/core/utils';
import { Locale } from '@commons/core/utils/locale';
import { Theme } from '@commons/core/utils/theme';
import { getAppDarkMode, getAppTheme, setAppDarkMode, setAppLocale, setAppTheme } from '@commons/mobile/utils';
import { ConfigProviderTheme, ConfigProviderThemeVars } from 'vant';
import { computed, onMounted, watch } from 'vue';

const appStore = useAppStore();
const appLocale = computed<Locale>(() => appStore.locale);
const appTheme = computed<Theme>(() => appStore.theme);
const appDark = computed(() => appStore.dark);
const theme = computed<ConfigProviderTheme>(() => getAppDarkMode(appStore.dark));
const themeVars = computed<ConfigProviderThemeVars>(() => getAppTheme(appStore.theme));

// 监听深色模式
watch(
    appDark,
    (value) => {
        setAppDarkMode(value);
    },
    {
        immediate: true,
    },
);
// 监听主题
watch(
    appTheme,
    (value) => {
        setAppTheme(value);
    },
    {
        immediate: true,
    },
);
// 监听语言
watch(
    appLocale,
    (value) => {
        setAppLocale(value);
    },
    {
        immediate: true,
    },
);

onMounted(async () => {
    log('Component <<App>> mounted.');
});
</script>
