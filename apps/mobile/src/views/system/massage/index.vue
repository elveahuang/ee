<template>
    <van-sticky>
        <van-nav-bar title="Notice" left-arrow @click-left="goBack()" />
    </van-sticky>

    <data-list v-model:model="model" @get-data-list="getDataList">
        <notice-list :items="model.items" />
    </data-list>
</template>

<script setup lang="ts">
import { NoticeList } from '@/views/system/massage/components';
import { noticeListApi } from '@commons/core/api/home/notice';
import { goBack, log } from '@commons/core/utils';
import { useDataList } from '@commons/core/utils/data-list';
import { DataList } from '@commons/mobile/components';
import { onMounted, reactive } from 'vue';

const { initialize, handleResult, handleParams } = useDataList();
const model = reactive(initialize());
const getDataList = async () => {
    await noticeListApi(handleParams(model)).then((result) => {
        handleResult(model, result);
    });
};

onMounted(() => {
    log('Page <<NoticeIndexPage>> mounted.');
});
</script>
