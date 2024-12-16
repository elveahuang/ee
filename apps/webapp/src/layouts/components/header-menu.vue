<template>
    <el-menu v-model:open-keys="openKeys" v-model:selected-keys="selectedKeys" class="app-menu" mode="horizontal">
        <template v-for="menu in items" :key="menu.key">
            <el-sub-menu v-if="menu.items && menu.items.length && menu.items.length > 0" :key="menu.key">
                <template #title>
                    <span>{{ menu.title }}</span>
                </template>
                <el-menu-item v-for="subMenu in menu.items" :key="subMenu.path" @click="handleMenuChange(subMenu.path)">
                    {{ subMenu.title }}
                </el-menu-item>
            </el-sub-menu>
            <el-menu-item v-else :key="menu.path" @click="handleMenuChange(menu.path)">
                <span>{{ menu.title }}</span>
            </el-menu-item>
        </template>
    </el-menu>
</template>

<script setup lang="ts">
import { menus } from '@/utils/menus';
import { log } from '@commons/core/utils';
import { getMenuItems, getMenuOpenKeys } from '@commons/core/utils/menu';
import { computed, onMounted, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const route = useRoute();
const router = useRouter();
const items = computed(() => getMenuItems(menus));
const openKeys = ref<string[]>(getMenuOpenKeys(menus, route.path));
const selectedKeys = ref<string[]>([route.path]);
const handleMenuChange = (path: string) => {
    router.push(path);
};

onMounted(() => {
    log('Component - <<AvatarDropdown>> is mounted.');
});
</script>
