<template>
    <el-select v-model:value="model" @change="onChange">
        <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value" />
    </el-select>
</template>

<script setup lang="ts">
import { useDark } from '@commons/core/hooks/useDark';
import { useAppStore } from '@commons/core/store';
import { DarkMode, darkModes, DarkModeType } from '@commons/core/utils/dark';
import { computed, ComputedRef, ref } from 'vue';

const appStore = useAppStore();
const { setDarkMode } = useDark();
const model: ComputedRef<DarkMode> = computed<DarkMode>(() => appStore.darkMode);
const options = ref(
    darkModes.map((item: DarkModeType) => {
        return { value: item.mode, label: item.title };
    }),
);
const onChange = async (value: DarkMode): Promise<void> => {
    await setDarkMode(value);
};
</script>
