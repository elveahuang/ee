<template>
    <el-dropdown placement="bottom-end">
        <el-button text circle size="large">
            <template #icon>
                <app-icon :size="24" icon="mdi:theme" />
            </template>
        </el-button>
        <template #dropdown>
            <el-dropdown-menu>
                <el-dropdown-item v-for="i in items" :key="i.theme" @click="onChange(i.theme)">
                    <span class="item" :style="{ backgroundColor: i.primaryColor }" />
                </el-dropdown-item>
            </el-dropdown-menu>
        </template>
    </el-dropdown>
</template>

<script setup lang="ts">
import { useTheme } from '@commons/core/hooks/useTheme';
import { Theme, themes } from '@commons/core/utils/theme';
import { ref } from 'vue';
import { default as AppIcon } from '../icon/Icon.vue';

const { setTheme } = useTheme();
const items = ref(themes);
const onChange = async (value: Theme): Promise<void> => {
    await setTheme(value).then();
};
</script>

<style lang="postcss" scoped>
.item {
    @apply m-1 inline-block;

    width: 120px;
    height: 16px;
}
</style>
