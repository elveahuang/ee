<template>
    <a-switch v-model:checked="model" class="app-switch" @change="onChange">
        <template #checkedChildren>
            <app-icon icon="mdi:weather-night" />
        </template>
        <template #unCheckedChildren>
            <app-icon icon="mdi:white-balance-sunny" />
        </template>
    </a-switch>
</template>

<script setup lang="ts">
import { useDark } from '@commons/core/hooks/useDark';
import { useAppStore } from '@commons/core/store';
import { AppIcon } from '@commons/webapp/components';
import { computed, ComputedRef } from 'vue';

const appStore = useAppStore();
const { setDark } = useDark();
const model: ComputedRef<boolean> = computed<boolean>(() => appStore.dark);
const onChange = async (value: boolean): Promise<void> => {
    await setDark(value).then();
};
</script>

<style lang="scss" scoped>
.ant-switch-handle {
    display: none;
    visibility: hidden;
}
</style>
