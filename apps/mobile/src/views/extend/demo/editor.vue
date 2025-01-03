<template>
    <div class="m-6">
        <app-editor />
    </div>
</template>

<script setup lang="ts">
import { AppEditor } from '@commons/core/components';
import { log } from '@commons/core/utils';
import { onMounted } from 'vue';

onMounted(async () => {
    log('Page <<ShowCaseSliderPage>> mounted.');
});
</script>
