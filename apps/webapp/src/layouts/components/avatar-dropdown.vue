<template>
    <el-dropdown>
        <el-button shape="circle">
            <template #icon>
                <app-icon icon="mdi:account-outline" />
            </template>
        </el-button>
        <template #overlay>
            <el-menu style="width: 250px">
                <el-menu-item-group v-if="isAuthenticated">
                    <span class="!my-2 inline-block">{{ user.displayName }}</span>
                </el-menu-item-group>
                <el-menu-item class="!p-2" @click="router.push('/user-center')">
                    <app-icon icon="mdi:account-circle-outline" />
                    <span class="ml-2">{{ $t('common.label_user_center') }}</span>
                </el-menu-item>
                <el-menu-item class="!p-2">
                    <app-icon icon="mdi:account-cog-outline" />
                    <span class="ml-2">{{ $t('common.label_account') }}</span>
                </el-menu-item>
                <el-menu-item class="!p-2">
                    <app-icon icon="mdi:lock-minus-outline" />
                    <span class="ml-2">{{ $t('common.label_change_password') }}</span>
                </el-menu-item>
                <el-menu-item class="!p-2" @click="handleLogout">
                    <app-icon icon="mdi:logout" />
                    <span class="ml-2">{{ $t('common.label_logout') }}</span>
                </el-menu-item>
            </el-menu>
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
const { isAuthenticated, user, logout } = useUserStore();
const handleLogout = () => {
    logout().then(() => {
        router.push(settings.app.getLogoutSuccessUrl());
    });
};

onMounted(() => {
    log('Component - <<AvatarDropdown>> is mounted.');
});
</script>
