<template>
    <div></div>
</template>

<script setup lang="ts">
import { announcementListApi } from '@commons/core/api/home/announcement.ts';
import { useDataTable } from '@commons/core/utils/data-table.ts';
import { reactive } from 'vue';

const { initialize, handleResult, handleParams } = useDataTable();
const model = reactive(initialize({}));
const getDataList = async () => {
    const result = await announcementListApi(handleParams(model));
    if (result) {
        handleResult(model, result);
    }
};
</script>
