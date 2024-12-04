<template>
    <van-sticky>
        <van-nav-bar :border="false" left-arrow @click-left="goBack()">
            <template #title>
                <van-tabs v-if="vipTypeTabsShow" ref="vipTypeTabsRef" v-model:active="vipTypeTabsActive" :line-width="20" @change="vipTypeTabsChange">
                    <van-tab v-for="vipType in vipTypeList" :key="vipType.code">
                        <template #title>
                            <div class="mx-4">
                                {{ vipType.title }}
                            </div>
                        </template>
                    </van-tab>
                </van-tabs>
            </template>
        </van-nav-bar>
    </van-sticky>
    <!-- 会员类型 -->
    <app-divider />
    <van-swipe ref="vipTypeSwipeRef" indicator-color="white" @change="vipTypeSwipeChange">
        <van-swipe-item v-for="vipType in vipTypeList" :key="vipType.code">
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
                <div class="m-4 text-center">
                    <van-button size="small" @click="router.push({ path: '/vip-checkout' })">
                        {{ $t('common.button_renew') }}
                    </van-button>
                </div>
            </van-cell-group>
        </van-swipe-item>
    </van-swipe>
    <app-divider />
</template>

<script setup lang="ts">
import { vipTypeListApi } from '@commons/core/api/home/mall.ts';
import { avatar } from '@commons/core/constants/images';
import { CoreService } from '@commons/core/services';
import { useUserStore } from '@commons/core/store';
import { VipType } from '@commons/core/types/mall.ts';
import { goBack, log } from '@commons/core/utils';
import { AppDivider } from '@commons/mobile/components';
import type { SwipeInstance, TabsInstance } from 'vant';
import { computed, onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';

const { user, isAuthenticated } = useUserStore();
const router = useRouter();
const vipTypeList = ref<VipType[]>([]);
const vipTypeTabsShow = computed(() => vipTypeList.value.length && vipTypeList.value.length > 0);
const vipTypeTabsRef = ref<TabsInstance>();
const vipTypeTabsActive = ref<number>(0);
const vipTypeTabsChange = (index: number) => {
    vipTypeSwipeRef.value.swipeTo(index);
};
const vipTypeSwipeRef = ref<SwipeInstance>();
const vipTypeSwipeChange = (index: number) => {
    vipTypeTabsActive.value = index;
};

onMounted(async () => {
    log('Page <<Vip>> mounted.');
    await CoreService.initialize().then(async () => {
        await vipTypeListApi().then((result) => {
            vipTypeList.value = result.data;
        });
    });
});
</script>
