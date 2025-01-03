<template>
    <van-sticky>
        <van-nav-bar title="NoticeDetailsPage" left-arrow @click-left="goBack()" />
    </van-sticky>

    <van-skeleton title :row="5" :loading="loading" />
    <template v-if="!loading">
        <div>
            <div class="text-center">
                {{ model?.subject }}
            </div>
            <div class="text-center">
                {{ model?.content }}
            </div>
        </div>
    </template>
</template>
<script setup lang="ts">
import { noticeDetailsApi } from '@commons/core/api/home/notice';
import { router } from '@commons/core/router';
import { Notice } from '@commons/core/types/notice';
import { goBack, log } from '@commons/core/utils';
import { onMounted, ref } from 'vue';

const loading = ref(true);
const id = ref();
const model = ref<Notice>();

onMounted(async () => {
    log('Page <<NoticeDetailsPage>> mounted.');
    id.value = router.currentRoute.value.query.id;
    if (id.value >= 0) {
        const result = await noticeDetailsApi({ id: id.value }).then();
        model.value = result.data;
        loading.value = false;
    }
});
</script>
