<template>
    <el-menu class="app-menu" v-model:open-keys="openKeys" v-model:selected-keys="selectedKeys" :collapse="sidebar.collapsed">
        <template v-for="menu in items" :key="menu.key">
            <el-sub-menu v-if="menu.items && menu.items.length && menu.items.length > 0" :index="menu.key">
                <template #title>
                    <app-icon :icon="menu.icon" />
                    <span>{{ menu.title }}</span>
                </template>
                <el-menu-item v-for="subMenu in menu.items" :key="subMenu.path" @click="handleMenuChange(subMenu.path)">
                    {{ subMenu.title }}
                </el-menu-item>
            </el-sub-menu>
            <el-menu-item v-else :index="menu.key" @click="handleMenuChange(menu.path)">
                <app-icon :icon="menu.icon" />
                <span>{{ menu.title }}</span>
            </el-menu-item>
        </template>
    </el-menu>
</template>

<script setup lang="ts">
import { menus } from '@/utils/menus';
import { useAppStore } from '@commons/core/store';
import { log } from '@commons/core/utils';
import { getMenuItems, getMenuOpenKeys } from '@commons/core/utils/menu';
import { AppIcon } from '@commons/webapp/components';
import { computed, onMounted, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const route = useRoute();
const router = useRouter();
const { sidebar } = useAppStore();
const items = computed(() => getMenuItems(menus));
const openKeys = ref<string[]>(getMenuOpenKeys(menus, route.path));
const selectedKeys = ref<string[]>([route.path]);
const handleMenuChange = (path: string) => {
    router.push(path);
};

onMounted(() => {
    log('Component <<SiderMenu>> is mounted.');
});
</script>
