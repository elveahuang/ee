<template>
    <el-icon :size="props.size">
        <v-icon :width="props.size" :height="props.size" :icon="props.icon" />
    </el-icon>
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
        type: Number,
        require: false,
        default: 18,
    },
    width: {
        type: Number,
        require: false,
        default: null,
    },
    height: {
        type: Number,
        require: false,
        default: null,
    },
});
</script>
