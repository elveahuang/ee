<template>
    <router-view />
    <div class="van-clearfix">
        <van-tabbar v-model="active" route placeholder safe-area-inset-bottom>
            <van-tabbar-item v-for="tab in tabs" :key="tab.id" replace :to="tab.link">
                <span>{{ tab.title }}</span>
                <template #icon>
                    <van-icon>
                        <v-icon :icon="tab.icon" />
                    </van-icon>
                </template>
            </van-tabbar-item>
        </van-tabbar>
    </div>
</template>

<script setup lang="ts">
import { settings } from '@/settings';
import { Tab } from '@commons/core/settings/mobile';
import { log, VIcon } from '@commons/core/utils';
import { onMounted, Ref, ref } from 'vue';
import { RouterView } from 'vue-router';

const active: Ref<number> = ref<number>(4);
const tabs: Tab[] = settings.app.getTabs();

onMounted(async () => {
    log('Component <<Tabs>> mounted.');
});
</script>

<style scoped lang="scss">
:deep(.van-tabbar) {
    height: var(--app-header-height);
}
</style>
