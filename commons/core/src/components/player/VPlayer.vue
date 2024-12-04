<template>
    <video
        ref="playerRef"
        class="video-js vjs-16-9 app-video-player"
        webkit-playsinline
        playsinline
        x5-playsinline
        x5-video-player-fullscreen="true"
        x5-video-player-type="h5"
        x5-video-orientation="portrait"
        preload="metadata"
    />
</template>

<script setup lang="ts">
import { log } from '@commons/core/utils';
import { getVPlayerOptions, VPlayerFullScreenBtn, VPlayerOptions } from '@commons/core/utils/player';
import videoJs from 'video.js';
import 'video.js/dist/video-js.min.css';
import 'videojs-youtube';
import { onBeforeUnmount, onMounted, ref } from 'vue';

const props = defineProps({
    options: {
        type: Object,
        default() {
            return {};
        },
    },
});

let player: any = null;
let playerRef = ref<HTMLVideoElement>();
/**
 * 初始化播放器
 */
const setupPlayer = async (): Promise<void> => {
    log('VPlayer setup...');
    videoJs.registerComponent('fullscreenButton', VPlayerFullScreenBtn);
};
/**
 * 初始化播放器
 */
const initPlayer = async (options: VPlayerOptions): Promise<void> => {
    const debug = (msg: any) => {
        if (options.debug) {
            log(msg);
        }
    };

    // 初始播放器实例
    options = getVPlayerOptions(options);
    player = videoJs(playerRef.value, options, () => {
        // 移除默认画中画按钮
        if (options.disablePictureInPicture) {
            player.getChild('ControlBar').removeChild('PictureInPictureToggle');
        }
        // 移除默认全屏按钮并重新添加网页全屏按钮
        // player.getChild('ControlBar').removeChild('FullscreenToggle');
        player.getChild('ControlBar').addChild('fullscreenButton');
        // 事件绑定
        player.on('seeking', () => {
            debug(`VPlayer seeking. ${player.currentTime()}`);
        });
        player.on('seeked', () => {
            debug(`VPlayer seeked. ${player.currentTime()}`);
        });
        player.on('ended', () => {
            debug(`VPlayer ended. ${player.currentTime()}`);
        });
        player.on('timeupdate', () => {
            debug(`VPlayer timeupdate. ${player.currentTime()}`);
        });
    });
};

onMounted(async () => {
    log('Component <<VPlayer>> mounted.');
    await setupPlayer();
    await initPlayer(props.options).then();
});

onBeforeUnmount(async () => {
    log('Component <<VPlayer>> onBeforeUnmount.');
    if (player) {
        player.dispose();
    }
});
</script>
