<template>
    <el-select v-model:value="model" @change="onChange">
        <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value" />
    </el-select>
</template>

<script setup lang="ts">
import { useTheme } from '@commons/core/hooks/useTheme';
import { useAppStore } from '@commons/core/store';
import { Locale } from '@commons/core/utils/locale.ts';
import { Theme, themes, ThemeType } from '@commons/core/utils/theme';
import { computed, ComputedRef, ref } from 'vue';

const appStore = useAppStore();
const { setTheme } = useTheme();
const model: ComputedRef<Locale> = computed<Locale>(() => appStore.locale);
const options = ref(
    themes.map((item: ThemeType) => {
        return { value: item.theme.toString(), label: item.title };
    }),
);
const onChange = async (value: Theme): Promise<void> => {
    await setTheme(value).then();
};
</script>
