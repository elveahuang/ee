<template>
    <van-icon :size="props.size">
        <v-icon :icon="props.icon" />
    </van-icon>
</template>

<script setup lang="ts">
import { VIcon } from '@commons/core/utils';

const props = defineProps({
    icon: {
        type: String,
        require: true,
        default: '',
    },
    size: {
        type: String,
        require: false,
        default: '18',
    },
});
</script>
