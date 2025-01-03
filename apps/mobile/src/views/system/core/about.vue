<template>
    <van-sticky>
        <van-nav-bar :title="$t('common.label_about')" left-arrow @click-left="goBack()" />
    </van-sticky>

    <div class="logo">
        <van-image width="9.375rem" :src="logo" />
    </div>

    <div class="slogan">
        <p class="app-text">
            {{ title }}
        </p>
        <p class="app-text">
            {{ version }}
        </p>
    </div>

    <div class="copyright">
        <p v-for="(text, index) in copyright" :key="index" class="app-text" v-html="text" />
    </div>
</template>

<script setup lang="ts">
import { settings } from '@/settings';
import { goBack, log } from '@commons/core/utils';
import { computed, onMounted } from 'vue';

const title = computed(() => settings.app.getTitle());
const version = computed(() => settings.app.getVersion());
const logo = computed(() => settings.app.getLogo());
const copyright = computed<string[]>(() => settings.app.getCopyright());

onMounted(async () => {
    log('Page <<About>> mounted.');
});
</script>

<style lang="scss" scoped>
.logo {
    @apply mt-16 text-center;
}

.slogan {
    @apply mt-3 text-center;

    p {
        @apply clear-both pt-0;
    }

    p:first-child {
        @apply clear-both pt-0;
    }
}

.copyright {
    @apply absolute bottom-0 my-3 w-full text-center;

    p {
        @apply clear-both pt-0;
    }
}
</style>
