<template>
    <div class="m-6">
        <van-slider v-model="cur" :min="min" :max="max" />
    </div>
</template>

<script setup lang="ts">
import { log } from '@commons/core/utils';
import { onMounted, Ref, ref } from 'vue';

const cur: Ref<number> = ref(-10);
const min: Ref<number> = ref(-100);
const max: Ref<number> = ref(-10);

onMounted(async () => {
    log('Page <<ShowCaseSliderPage>> mounted.');
});
</script>
