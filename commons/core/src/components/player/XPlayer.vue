<template>
    <div ref="playerRef" class="app-video-player" />
</template>

<script setup lang="ts">
import { log } from '@commons/core/utils';
import { getXPlayerOptions, XPlayerOptions, XPlayerPreset } from '@commons/core/utils/player';
import { onBeforeUnmount, onMounted, ref } from 'vue';
import Player, { Events, SimplePlayer } from 'xgplayer';
import 'xgplayer/dist/index.min.css';

const props = defineProps({
    options: {
        type: Object,
        default() {
            return {};
        },
    },
});

let player: Player = null;
let playerRef = ref<HTMLVideoElement>();
/**
 * 初始化播放器
 */
const setupPlayer = async (): Promise<void> => {
    log('XPlayer setup...');
    SimplePlayer.defaultPreset = XPlayerPreset;
};
/**
 * 初始化播放器
 */
const initPlayer = async (options: XPlayerOptions): Promise<void> => {
    const debug = (msg: any) => {
        console.log(msg);
    };

    // 初始播放器实例
    options = {
        ...({
            el: playerRef.value,
            url: options.src,
        } as XPlayerOptions),
        ...options,
    } as XPlayerOptions;
    player = new Player(getXPlayerOptions(options));
    // 事件绑定
    player.on(Events.SEEKING, () => {
        debug(`XPlayer seeking. ${player.currentTime}`);
    });
    player.on(Events.SEEKED, () => {
        debug(`XPlayer seeked. ${player.currentTime}`);
    });
    player.on(Events.ENDED, () => {
        debug(`XPlayer ended. ${player.currentTime}`);
    });
    player.on(Events.TIME_UPDATE, () => {
        debug(`XPlayer timeupdate. ${player.currentTime}`);
    });
};

onMounted(async () => {
    log('Component <<XPlayer>> mounted.');
    await setupPlayer();
    await initPlayer(props.options).then();
});

onBeforeUnmount(async () => {
    log('Component <<XPlayer>> onBeforeUnmount.');
    if (player) {
        player.destroy();
    }
});
</script>
