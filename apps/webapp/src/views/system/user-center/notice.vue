<template>
    <el-card shadow="never">
        <template #title>
            <div>{{ $t('common.user_pages_change_password_title') }}</div>
        </template>
        <div class="pb-4">
            <el-space>
                <el-button @click="dataTableSelectionEnabled = !dataTableSelectionEnabled">
                    {{ dataTableSelectionEnabled ? $t('common.label_batch_operation_cancel') : $t('common.label_batch_operation') }}
                </el-button>
            </el-space>
        </div>
        <app-table
            ref="dataTableRef"
            v-model:model="dataTableModel"
            :row-selection-enabled="dataTableSelectionEnabled"
            @get-data-list="getDataList"
        />
    </el-card>
</template>

<script setup lang="ts">
import { noticeListApi } from '@commons/core/api/home/notice';
import { log } from '@commons/core/utils';
import { useDataTable } from '@commons/core/utils/data-table';
import { AppTable } from '@commons/webapp/components';
import { onMounted, reactive, Ref, ref } from 'vue';
import { useI18n } from 'vue-i18n';

const { t } = useI18n();
// 数据列表
const { initialize, handleResult, handleParams } = useDataTable();
const dataTableColumns = [
    {
        title: t('common.field_content'),
        dataIndex: 'content',
        key: 'content',
    },
    {
        title: t('common.field_last_modified_at'),
        dataIndex: 'lastModifiedAt',
        key: 'lastModifiedAt',
        sorter: true,
    },
    {
        title: t('common.label_action'),
        dataIndex: 'operation',
        key: 'operation',
        width: 200,
        fixed: 'right',
    },
];
const dataTableModel = reactive(initialize(dataTableColumns));
const dataTableRef: Ref<typeof AppTable> = ref<typeof AppTable>();
const dataTableSelectionEnabled: Ref<boolean> = ref<boolean>(false);
const getDataList = async () => {
    await noticeListApi(handleParams(dataTableModel)).then((result) => {
        handleResult(dataTableModel, result);
    });
};

onMounted(async () => {
    log('Page <<UserCenterNoticePage>> mounted.');
});
</script>
