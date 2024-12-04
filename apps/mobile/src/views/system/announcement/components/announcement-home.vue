<template>
    <van-notice-bar v-if="show" left-icon="volume-o" :scrollable="false" mode="link" @click="router.push({ path: '/announcement/index' })">
        <van-swipe class="custom-swipe" vertical :autoplay="3000" :touchable="false" :show-indicators="false">
            <van-swipe-item v-for="item in items" :key="item.id">
                {{ item.title }}
            </van-swipe-item>
        </van-swipe>
    </van-notice-bar>
</template>

<script setup lang="ts">
import { announcementListApi } from '@commons/core/api/home/announcement';
import { router } from '@commons/core/router';
import { Announcement } from '@commons/core/types/announcement';
import { log } from '@commons/core/utils';
import { onMounted, ref } from 'vue';

const show = ref<boolean>(false);
const items = ref<Announcement[]>([]);
const getDataList = async () => {
    const result = await announcementListApi({ page: 1, size: 5 });
    if (result.code == '200') {
        items.value = result.data.content;
        show.value = items.value.length && items.value.length > 0;
    }
};

onMounted(async () => {
    log('Component <<AnnouncementNew>> mounted.');
    getDataList().then();
});
</script>

<style lang="scss">
.custom-swipe {
    height: 40px;
    line-height: 40px;
}
</style>
