<template>
    <van-pull-refresh v-model:model-value="list.refreshing" :disabled="refreshDisabled" @refresh="onRefresh">
        <!-- 加载提示 -->
        <template #loading>
            <van-icon>
                <v-icon icon="ep:loading" />
            </van-icon>
        </template>
        <!-- 数据列表 -->
        <van-list v-model:loading="list.loading" :finished="list.finished" :offset="offset" @load="onLoad">
            <slot />
        </van-list>
    </van-pull-refresh>
</template>

<script setup lang="ts">
import { log, VIcon } from '@commons/core/utils';
import { DataList, initialize } from '@commons/core/utils/data-list';
import { onMounted } from 'vue';

const emits = defineEmits(['getDataList', 'resetData', 'cleanDataList']);
const props = defineProps({
    model: {
        type: DataList,
        require: true,
        default: () => initialize(),
    },
    offset: {
        type: Number,
        default: 100,
    },
    refreshDisabled: {
        type: Boolean,
        default: false,
    },
    loadDisabled: {
        type: Boolean,
        default: false,
    },
});
const list = reactive<DataList>(props.model);
const onLoad = async (): Promise<void> => {
    log('onLoad...');
    if (list.refreshing) {
        log('refreshing');
        list.pagination.page = 0;
        list.finished = false;
        list.loading = false;
        emits('getDataList');
    } else {
        emits('getDataList');
    }
};
const onRefresh = () => {
    log('onRefresh...');
    onLoad();
};

const dataRefresh = async (): Promise<void> => {
    emits('resetData');
    emits('getDataList');
};

defineExpose({
    dataRefresh,
});

onMounted(async () => {
    log('Component <<DataList>> mounted.');
});
</script>

<style scoped lang="scss">
:deep(.van-list) {
    min-height: 85vh;
}
</style>
