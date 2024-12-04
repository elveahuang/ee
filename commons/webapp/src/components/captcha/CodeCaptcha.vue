<template>
    <div class="flex items-center">
        <el-image :preview="false" :alt="$t('common.field_captcha')" :width="props.width" :height="props.height" :src="image" @click="getCaptcha" />
    </div>
</template>

<script setup lang="ts">
import { getCodeCaptchaApi } from '@commons/core/api/captcha.ts';
import { log } from '@commons/core/utils';
import { onMounted, Ref, ref } from 'vue';

const emits = defineEmits(['change']);
const props = defineProps({
    width: {
        type: Number,
        default: 120,
    },
    height: {
        type: Number,
        default: 32,
    },
});
const image: Ref<string> = ref<string>('');
const getCaptcha = async (): Promise<void> => {
    await getCodeCaptchaApi({}).then((resp) => {
        image.value = resp.data.image;
        emits('change', resp.data.key);
    });
};
const refresh = async (): Promise<void> => {
    getCaptcha().then();
};

defineExpose({
    refresh,
});

onMounted(async () => {
    log('Component <<Captcha>> mounted.');
    getCaptcha().then();
});
</script>
