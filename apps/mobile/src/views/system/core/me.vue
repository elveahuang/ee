<template>
    <div class="app-page">
        <div class="app-page-content">
            <van-sticky @scroll="onScroll">
                <van-nav-bar :border="false" :class="appHeaderClass" left-arrow @click-left="goBack()">
                    <template #right>
                        <van-space>
                            <van-icon size="24" @click="router.push({ path: '/notifications/index' })">
                                <v-icon icon="mdi:bell-outline" />
                            </van-icon>
                            <van-icon size="24" @click="router.push({ path: '/setting' })">
                                <v-icon icon="mdi:cog-outline" />
                            </van-icon>
                        </van-space>
                    </template>
                </van-nav-bar>
            </van-sticky>

            <app-divider />
            <template v-if="isAnonymous">
                <div class="mx-4 text-center">
                    <van-space>
                        <van-button plain hairline type="primary" size="small" @click="router.push({ path: '/login' })">
                            {{ $t('common.button_login') }}
                        </van-button>
                        <van-button plain hairline type="primary" size="small" @click="router.push({ path: '/register' })">
                            {{ $t('common.button_register') }}
                        </van-button>
                    </van-space>
                </div>
            </template>
            <template v-else>
                <van-cell-group v-if="isAuthenticated" inset>
                    <div class="flex">
                        <div class="flex-none p-4 text-center">
                            <van-image round class="!align-middle" width="3.2rem" height="3.2rem" :src="avatar" />
                        </div>
                        <div class="flex-1 py-4">
                            <div class="app-text-primary">
                                {{ user.username }}
                            </div>
                        </div>
                    </div>
                    <div class="mx-4">
                        <app-line />
                        <van-notice-bar class="app-bg-transparent !m-0 !p-0" mode="link" @click="router.push({ path: '/vip' })">
                            <template #left-icon>
                                <app-icon icon="mdi:crown" />
                            </template>
                            <span class="ml-2">{{ $t('common.label_vip_center') }}</span>
                        </van-notice-bar>
                    </div>
                </van-cell-group>
            </template>
            <app-divider />

            <van-cell-group v-if="isAuthenticated" inset>
                <van-grid square column-num="4">
                    <van-grid-item v-for="application in apps" :key="application.id" :text="application.title">
                        <template #icon>
                            <van-icon class="app-icon mb-2" size="28">
                                <v-icon :icon="application.icon" />
                            </van-icon>
                        </template>
                    </van-grid-item>
                </van-grid>
            </van-cell-group>
            <app-divider />

            <van-cell-group inset>
                <van-cell v-if="isAuthenticated" :title="$t('app.changePassword')" to="/change-password" is-link>
                    <template #icon>
                        <van-icon class="app-icon mr-4" size="24">
                            <v-icon icon="mdi:lock-outline" />
                        </van-icon>
                    </template>
                </van-cell>
                <van-cell v-if="isAuthenticated" :title="$t('common.label_change_email')" to="/change-email" is-link>
                    <template #icon>
                        <van-icon class="app-icon mr-4" size="24">
                            <v-icon icon="ant-design:mail-twotone" />
                        </van-icon>
                    </template>
                </van-cell>
                <van-cell :title="$t('common.label_settings')" to="/setting" is-link>
                    <template #icon>
                        <van-icon class="app-icon mr-4" size="24">
                            <v-icon icon="mdi:cog-outline" />
                        </van-icon>
                    </template>
                </van-cell>
                <van-cell :title="$t('common.label_about')" to="/about" is-link>
                    <template #icon>
                        <van-icon class="app-icon mr-4" size="24">
                            <v-icon icon="mdi:information-outline" />
                        </van-icon>
                    </template>
                </van-cell>
                <van-cell v-if="isAuthenticated" :title="$t('common.label_logout')" is-link @click="handleLogout()">
                    <template #icon>
                        <van-icon class="app-icon mr-4" size="24">
                            <v-icon icon="mdi:logout" />
                        </van-icon>
                    </template>
                </van-cell>
            </van-cell-group>
            <app-divider />
        </div>
    </div>
</template>

<script setup lang="ts">
import { settings } from '@/settings';
import { avatar } from '@commons/core/constants/images';
import { router } from '@commons/core/router';
import { CoreService } from '@commons/core/services';
import { Application } from '@commons/core/settings/mobile';
import { useUserStore } from '@commons/core/store';
import { goBack, log, VIcon } from '@commons/core/utils';
import { AppDivider, AppIcon, AppLine } from '@commons/mobile/components';
import { onMounted, ref } from 'vue';

const { user, logout, isAnonymous, isAuthenticated } = useUserStore();
const appHeaderClass = ref('app-header app-header-transparent');
const onScroll = (args: { scrollTop: number; isFixed: boolean }) => {
    if (args.isFixed && args.scrollTop && args.scrollTop > 60) {
        appHeaderClass.value = 'app-header';
    } else {
        appHeaderClass.value = 'app-header app-header-transparent';
    }
};
const apps: Application[] = settings.me.getApps();
const handleLogout = () => {
    logout().then(() => {
        router.push(settings.app.getLogoutSuccessUrl());
    });
};

onMounted(async () => {
    log('Page <<MePage>> mounted.');
    await CoreService.initialize().then();
});
</script>

<style scoped lang="scss">
:deep(.van-tabbar) {
    color: var(--app-primary-color);
}
</style>
