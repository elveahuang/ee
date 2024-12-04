<template>
    <van-sticky>
        <van-nav-bar :title="$t('common.announcement_pages_details_title')" left-arrow @click-left="goBack()" />
    </van-sticky>
    <van-pull-refresh v-model:model-value="refreshing" @refresh="onRefresh">
        <div class="app-page">
            <div class="app-page-content">
                <van-skeleton title :row="5" :loading="loading" />
                <template v-if="!loading">
                    <app-divider />
                    <van-cell-group inset>
                        <van-cell-group inset>
                            <div class="app-text app-text-bold mt-2" v-html="model?.title" />
                            <div class="app-line" />
                            <div class="app-text-muted mb-4" v-html="model?.content" />
                        </van-cell-group>
                    </van-cell-group>
                </template>
            </div>
        </div>
    </van-pull-refresh>
</template>
<script setup lang="ts">
import { announcementDetailsApi } from '@commons/core/api/home/announcement';
import { router } from '@commons/core/router';
import { Announcement } from '@commons/core/types/announcement';
import { goBack, log } from '@commons/core/utils';
import { AppDivider } from '@commons/mobile/components';
import { onMounted, ref } from 'vue';

const loading = ref(true);
const refreshing = ref(false);
const id = ref();
const model = ref<Announcement>();
const onRefresh = async () => {
    await getData().then(() => {
        refreshing.value = false;
    });
};
const getData = async () => {
    id.value = router.currentRoute.value.query.id;
    if (id.value >= 0) {
        const result = await announcementDetailsApi({ id: id.value }).then();
        model.value = result.data;
        loading.value = false;
    }
};

onMounted(async () => {
    log('Page <<AnnouncementDetailsPage>> mounted.');
    await getData().then();
});
</script>
