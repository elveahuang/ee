<template>
    <el-dropdown placement="bottom-end">
        <el-button text circle size="large">
            <template #icon>
                <app-icon :size="24" icon="mdi:translate" />
            </template>
        </el-button>
        <template #dropdown>
            <el-dropdown-menu>
                <el-dropdown-item v-for="i in items" :key="i.locale" @click="onChange(i.locale)">
                    <span class="item">{{ i.title }}</span>
                </el-dropdown-item>
            </el-dropdown-menu>
        </template>
    </el-dropdown>
</template>

<script setup lang="ts">
import { useLocale } from '@commons/core/hooks/useLocale';
import { Locale, locales } from '@commons/core/utils/locale';
import { ref } from 'vue';
import { default as AppIcon } from '../icon/Icon.vue';

const { setLocale } = useLocale();
const items = ref(locales);
const onChange = async (value: Locale): Promise<void> => {
    await setLocale(value).then();
};
</script>

<style lang="postcss" scoped>
el-button {
    cursor: pointer;
    display: flex;
    align-items: center;
}

.item {
    @apply m-1 inline-block;
}
</style>
