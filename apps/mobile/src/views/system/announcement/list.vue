<template>
    <van-sticky>
        <van-nav-bar :title="$t('common.announcement_pages_list_title')" left-arrow @click-left="goBack()" />
    </van-sticky>

    <data-list v-model:model="model" @get-data-list="getDataList">
        <announcement-list :items="model.items" />
    </data-list>
</template>

<script setup lang="ts">
import { AnnouncementList } from '@/views/system/announcement/components';
import { announcementListApi } from '@commons/core/api/home/announcement';
import { goBack, log } from '@commons/core/utils';
import { useDataList } from '@commons/core/utils/data-list';
import { DataList } from '@commons/mobile/components';
import { onMounted, reactive } from 'vue';

const { initialize, handleResult, handleParams } = useDataList();
const model = reactive(initialize());
const getDataList = async () => {
    await announcementListApi(handleParams(model)).then((result) => {
        handleResult(model, result);
    });
};

onMounted(async () => {
    log('Page <<AnnouncementListPage>> mounted.');
});
</script>
