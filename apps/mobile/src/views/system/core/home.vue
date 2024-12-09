<template>
    <van-sticky>
        <van-nav-bar :fixed="true" :placeholder="true" :title="title">
            <template #right>
                <van-icon size="24" @click="router.push({ path: '/notifications/index' })">
                    <v-icon icon="mdi:bell-outline" />
                </van-icon>
            </template>
        </van-nav-bar>
    </van-sticky>

    <!-- 公告资讯 -->
    <announcement-home />

    <!-- 宣传栏 -->
    <app-divider />
    <van-swipe class="mx-4" :autoplay="3000" lazy-render :show-indicators="false">
        <van-swipe-item v-for="item in images" :key="item.id" class="aspect-[16/9] w-full">
            <van-image fit="fill" position="top" class="aspect-[16/9] w-full" :radius="5" alt="IMG" :src="item.img" />
        </van-swipe-item>
    </van-swipe>

    <!-- 宣传栏 -->
    <app-divider />
    <div class="m-8 text-center">
        <van-button type="primary" to="/login"> Login </van-button>
        <van-button type="primary" to="/announcement/index"> Announcement </van-button>
    </div>
</template>

<script setup lang="ts">
import { settings } from '@/settings';
import { AnnouncementHome } from '@/views/system/announcement/components';
import { homeApi } from '@commons/core/api/home';
import { poster_1, poster_2, poster_3, poster_4 } from '@commons/core/constants/images';
import { router } from '@commons/core/router';
import { log, VIcon } from '@commons/core/utils';
import { AppDivider } from '@commons/mobile/components';
import { computed, onMounted } from 'vue';

const title = computed(() => settings.app.getTitle());
const images = [
    {
        id: 1,
        img: poster_1,
    },
    {
        id: 2,
        img: poster_2,
    },
    {
        id: 3,
        img: poster_3,
    },
    {
        id: 4,
        img: poster_4,
    },
];

const handleClick = (v: number): void => {
    alert(v);
};

onMounted(async (): Promise<void> => {
    log('Page <<HomePage>> mounted.');
    homeApi().then();
});
</script>
