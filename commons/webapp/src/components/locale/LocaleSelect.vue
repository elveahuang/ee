<template>
    <el-select v-model:value="model" @change="onChange">
        <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value" />
    </el-select>
</template>

<script setup lang="ts">
import { useLocale } from '@commons/core/hooks/useLocale';
import { useAppStore } from '@commons/core/store';
import { Locale, locales, LocaleType } from '@commons/core/utils/locale';
import { computed, ComputedRef, ref } from 'vue';

const appStore = useAppStore();
const { setLocale } = useLocale();
const model: ComputedRef<Locale> = computed<Locale>(() => appStore.locale);
const options = ref(
    locales.map((item: LocaleType) => {
        return { value: item.locale, label: item.title };
    }),
);
const onChange = async (value: Locale): Promise<void> => {
    await setLocale(value).then();
};
</script>
