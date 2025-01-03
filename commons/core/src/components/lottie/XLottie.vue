<template>
    <canvas ref="{dotLottieCanvasRef}"></canvas>
</template>

<script setup lang="ts">
import { log } from '@commons/core/utils';
import { QuillOptions } from 'quill/core/quill';
import 'quill/dist/quill.core.css';
import 'quill/dist/quill.snow.css';
import { onBeforeUnmount, onMounted } from 'vue';

const props = defineProps({
    options: {
        type: Object,
        default() {
            return {};
        },
    },
});
const initDotLottie = async (options: QuillOptions): Promise<void> => {};
const destroyDotLottie = async (): Promise<void> => {};

onMounted(async (): Promise<void> => {
    log('Component <<QuillEditor>> mounted.');
    await initDotLottie(props.options as QuillOptions).then();
});

onBeforeUnmount(async (): Promise<void> => {
    log('Component <<QuillEditor>> onBeforeUnmount.');
    await destroyDotLottie().then();
});
</script>
