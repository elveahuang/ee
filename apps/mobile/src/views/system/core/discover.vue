<template>
    <van-nav-bar :title="$t('common.label_discover')" :on-click-left="goBack" />
    <div>
        <div class="m-8 text-center">Discover</div>
    </div>
</template>

<script setup lang="ts">
import { goBack, log } from '@commons/core/utils';
import { onMounted } from 'vue';

onMounted(async () => {
    log('Page <<DiscoverPage>> mounted.');
});
</script>
