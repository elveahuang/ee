<template>
    <el-divider />
</template>

<script lang="ts">
import { log } from '@commons/core/utils';
import { defineComponent } from 'vue';

export default defineComponent({
    name: 'XDivider',
    props: {
        height: { type: Number, required: false, default: 20 },
    },
    onMounted() {
        log('Builder Component <<Divider>> mounted.');
    },
});
</script>
