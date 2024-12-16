<template>
    <app-divider />
    <van-cell-group :border="false" class="app-bg-transparent !mx-4">
        <v-player :options="vPlayerOptions" />
    </van-cell-group>
    <app-divider />
    <van-cell-group :border="false" class="app-bg-transparent !mx-4">
        <x-player :options="xPlayerOptions" />
    </van-cell-group>
    <app-divider />
    <van-cell-group :border="false" class="app-bg-transparent !mx-4">
        <app-player :options="xPlayerOptions" />
    </van-cell-group>
    <app-divider />
</template>

<script setup lang="ts">
import { AppPlayer, VPlayer, XPlayer } from '@commons/core/components';
import { log } from '@commons/core/utils';
import { VPlayerOptions, XPlayerOptions } from '@commons/core/utils/player.ts';
import { AppDivider } from '@commons/mobile/components';
import { onMounted, ref } from 'vue';

const vPlayerOptions = ref<VPlayerOptions>({
    debug: false,
    src: 'https://sf1-cdn-tos.huoshanstatic.com/obj/media-fe/xgplayer_doc_video/mp4/xgplayer-demo-360p.mp4',
});

const xPlayerOptions = ref<XPlayerOptions>({
    debug: false,
    src: 'https://sf1-cdn-tos.huoshanstatic.com/obj/media-fe/xgplayer_doc_video/mp4/xgplayer-demo-360p.mp4',
});

onMounted(async () => {
    log('Page <<PlayerDemoPage>> mounted.');
});
</script>
