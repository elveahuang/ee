<template>
    <el-dropdown placement="bottom-end">
        <el-button text circle size="large">
            <template #icon>
                <app-icon :size="24" icon="mdi:account" />
            </template>
        </el-button>
        <template #dropdown>
            <el-dropdown-menu style="width: 250px">
                <el-dropdown-item class="!p-2">
                    <span>{{ user.displayName }}</span>
                </el-dropdown-item>
                <el-dropdown-item class="!p-2" @click="router.push('/user-center')">
                    <app-icon icon="mdi:account-circle-outline" />
                    <span class="ml-2">{{ $t('common.label_user_center') }}</span>
                </el-dropdown-item>
                <el-dropdown-item class="!p-2" @click="handleLogout">
                    <app-icon icon="mdi:logout" />
                    <span class="ml-2">{{ $t('common.label_logout') }}</span>
                </el-dropdown-item>
            </el-dropdown-menu>
        </template>
    </el-dropdown>
</template>

<script setup lang="ts">
import { settings } from '@/settings';
import { useUserStore } from '@commons/core/store';
import { log } from '@commons/core/utils';
import { AppIcon } from '@commons/webapp/components';
import { onMounted } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const { user, logout } = useUserStore();
const handleLogout = () => {
    logout().then(() => {
        router.push(settings.app.getLogoutSuccessUrl());
    });
};

onMounted(() => {
    log('Component <<AvatarDropdown>> is mounted.');
});
</script>
