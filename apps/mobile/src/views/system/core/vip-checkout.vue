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
                        <div class="app-text-primary">{{ user.username }} - {{ vipType.code }}</div>
                    </div>
                </div>
            </van-cell-group>
        </van-swipe-item>
    </van-swipe>
    <!-- 会员套餐 -->
    <template v-if="vipTypeCurrent?.items?.length > 0">
        <app-divider />
        <div class="vip-item-container">
            <div v-for="(item, index) in vipTypeCurrent.items" :key="item.code" :class="vipItemClass(index)" @click="vipItemChange(index)">
                <van-cell-group :border="true" inset class="app-no-margin p-2">
                    <div>{{ item.code }}</div>
                    <div>{{ item.title }}</div>
                    <div>{{ item.listPrice }}</div>
                    <div>{{ item.price }}</div>
                </van-cell-group>
            </div>
        </div>
    </template>
    <!-- 确认支付订单 -->
    <van-submit-bar :price="totalPrice" :button-text="$t('common.button_renew')" @submit="confirmVipOrder">
        <template #tip> 开通会员代表接受会员服务协议 </template>
    </van-submit-bar>
    <!-- 支付方式 -->
    <van-action-sheet
        v-model:show="actionSheetShow"
        :cancel-text="$t('common.button_cancel')"
        close-on-click-action
        closeable
        title="请选择支付方式"
        @cancel="actionSheetOnCancel"
        @select="actionSheetOnSelect"
    >
        <van-cell-group inset class="!my-4">
            <van-cell v-for="item in payTypeList" :key="item.code" is-link>
                <template #icon>
                    <app-icon :icon="item.icon" />
                </template>
                <template #title>
                    <div class="app-text ml-4" @click="submitVipOrder(item.code)">
                        {{ item.title }}
                    </div>
                </template>
            </van-cell>
        </van-cell-group>
    </van-action-sheet>
</template>

<script setup lang="ts">
import { payTypeListApi, vipTypeListApi } from '@commons/core/api/home/mall.ts';
import { avatar } from '@commons/core/constants/images';
import { CoreService } from '@commons/core/services';
import { useUserStore } from '@commons/core/store';
import { PayType, VipType } from '@commons/core/types/mall.ts';
import { goBack, log } from '@commons/core/utils';
import { AppDivider, AppIcon } from '@commons/mobile/components';
import type { SwipeInstance, TabsInstance } from 'vant';
import { computed, onMounted, ref } from 'vue';

const { user, isAuthenticated } = useUserStore();
const vipTypeList = ref<VipType[]>([]);
const vipTypeTabsShow = computed(() => vipTypeList.value.length && vipTypeList.value.length > 0);
const vipTypeTabsRef = ref<TabsInstance>();
const vipTypeTabsActive = ref<number>(0);
const vipTypeTabsChange = (index: number) => {
    vipTypeSwipeRef.value.swipeTo(index);
    vipItemCurrentIndex.value = 0;
};
const vipTypeSwipeRef = ref<SwipeInstance>();
const vipTypeSwipeChange = (index: number) => {
    vipTypeTabsActive.value = index;
};
const vipTypeCurrent = computed(() => vipTypeList.value.at(vipTypeTabsActive.value));
const vipItemCurrentIndex = ref(0);
const vipItemClass = (index: number = 0) => {
    return vipItemCurrentIndex.value === index ? 'vip-item vip-item-active' : 'vip-item';
};
const vipItemChange = (index: number = 0) => {
    vipItemCurrentIndex.value = index;
};
const actionSheetShow = ref(false);
const actionSheetOnCancel = () => {
    actionSheetShow.value = false;
};
const actionSheetOnSelect = () => {
    actionSheetShow.value = false;
};
const payTypeList = ref<PayType[]>([]);
const totalPrice = computed(() => vipTypeCurrent?.value?.items?.at(vipItemCurrentIndex.value).price * 100);
const confirmVipOrder = () => {
    actionSheetShow.value = true;
};
const submitVipOrder = (payType: string = '') => {
    alert(payType);
};

onMounted(async () => {
    log('Page <<VipOrderCheckout>> mounted.');
    await CoreService.initialize().then(async () => {
        await vipTypeListApi().then((result) => {
            vipTypeList.value = result.data;
        });
        await payTypeListApi().then((result) => {
            payTypeList.value = result.data;
        });
    });
});
</script>

<style scoped lang="scss">
.vip-item-container {
    @apply flex flex-nowrap gap-4 overflow-x-scroll px-4;
}

.vip-item {
    @apply flex-none;

    width: 30%;
}

.vip-item-active {
    @apply bg-sky-400;
}

div::-webkit-scrollbar {
    display: none;
}
</style>
