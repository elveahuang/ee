<template>
    <div class="app-scrolling-container">
        <div ref="contentRef" class="app-scrolling-content" @scroll="handleScroll">
            <!-- 列表内容 -->
            <div ref="scrollRef" class="flex flex-nowrap gap-4" @touchstart="touchStart" @touchmove="touchMove" @touchend="touchEnd">
                <slot name="items" />
            </div>
            <!-- 查看更多 -->
            <div v-if="more" ref="moreRef" class="flex-none">
                <div class="more inline-block w-8" :class="[progress ? 'h-20' : 'mb-8 h-10']">
                    <div ref="more" class="more-icon">
                        <app-icon icon="mdi:more-horiz" />
                    </div>
                </div>
            </div>
        </div>
        <!-- 进度条 -->
        <div v-if="progress" class="app-scrolling-progress">
            <div ref="progressRef">
                <van-progress :percentage="100" :show-pivot="false" stroke-width="5" class="w-6" />
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { AppIcon } from '@commons/mobile/components';
import { defineProps, ref } from 'vue';

defineProps({
    more: {
        type: Boolean,
        required: false,
        default: false,
    },
    progress: {
        type: Boolean,
        required: false,
        default: false,
    },
});
const progressRef = ref<HTMLDivElement>();
const contentRef = ref<HTMLDivElement>();
const scrollRef = ref<HTMLDivElement>();
const moreRef = ref<HTMLDivElement>();
const handleScroll = () => {};
const touchStart = () => {};
const touchMove = () => {};
const touchEnd = () => {};
</script>

<style scoped lang="scss">
.app-scrolling-container {
    @apply rounded-2xl;
}

.app-scrolling-content {
    @apply flex flex-nowrap gap-4 overflow-x-scroll px-4;
}

.app-scrolling-item {
}

.app-scrolling-progress {
    @apply flex w-8;
}

div::-webkit-scrollbar {
    display: none;
}
</style>
