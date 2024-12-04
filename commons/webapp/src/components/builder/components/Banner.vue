<template>
    <div class="app-text-bold h-full w-full">
        <div v-html="value" />
    </div>
</template>

<script lang="ts">
import { log } from '@commons/core/utils';
import { defineComponent, toRef } from 'vue';

export default defineComponent({
    name: 'XBanner',
    props: {
        formCreateInject: Object,
        text: { type: String, required: true },
    },
    setup(props) {
        const value = toRef(props, 'text');
        return { value };
    },
    onMounted() {
        log('Builder Component <<Banner>> mounted.');
    },
});
</script>
