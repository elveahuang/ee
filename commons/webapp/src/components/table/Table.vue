<template>
    <div class="app-data-table">
        <div v-show="toolbarEnabled" class="app-data-table-toolbar">
            <slot name="toolbar" />
        </div>
        <div class="app-data-table-body">
            <el-table
                ref="tableRef"
                class="w-full"
                :data="model.items"
                :row-key="rowKey"
                @selectionChange="handleSelectionChange"
                v-loading="model.loading"
            >
                <template #default>
                    <el-table-column v-if="rowSelectionEnabled" :column-key="rowKey" type="selection" fixed="left" />
                    <slot name="body" />
                </template>
            </el-table>
        </div>
        <div class="app-data-table-footer">
            <el-pagination
                v-model="tablePagination.current"
                :total="tablePagination.total"
                :page-size="tablePagination.pageSize"
                @change="handlePageChange"
            />
        </div>
    </div>
</template>

<script setup lang="ts">
import { log } from '@commons/core/utils';
import { DataTable, DataTableState } from '@commons/core/utils/data-table';
import { ElTable } from 'element-plus';
import { computed, onMounted, reactive, ref } from 'vue';

const emits = defineEmits(['getDataList']);
const model = defineModel<DataTable>({});
const props = defineProps({
    toolbarEnabled: {
        type: Boolean,
        require: false,
        default: false,
    },
    rowSelectionEnabled: {
        type: Boolean,
        require: false,
        default: true,
    },
    rowSelectionMultipleEnabled: {
        type: Boolean,
        require: false,
        default: true,
    },
    rowSelection: {
        type: Object,
        require: false,
        default: null,
    },
    rowKey: {
        type: String,
        require: false,
        default: 'id',
    },
    fixed: {
        type: Boolean,
        require: false,
        default: false,
    },
});
const tableState = reactive<DataTableState>({
    tableSelectedRowKeys: [],
    tableSelectedRows: [],
});
const tableRef = ref<InstanceType<typeof ElTable>>();
const tablePagination = computed(() => {
    return {
        pageSize: model.value.pagination.size,
        current: model.value.pagination.page,
        total: model.value.pagination.total as number,
    };
});
const handleSelectionChange = (selectedRows: []) => {
    console.log(`DataTable.onSelectionChange...`);
    tableState.tableSelectedRows = selectedRows;
    tableState.tableSelectedRowKeys = selectedRows.filter((e: any) => e[props.rowKey]).map((e: any) => e[props.rowKey]);
};
const handlePageChange = (currentPage: number, pageSize: number) => {
    console.log(`DataTable.onTableChange...`);
    model.value.pagination.size = pageSize;
    model.value.pagination.page = currentPage;
    emits('getDataList');
};
const refresh = async (): Promise<void> => {
    model.value.pagination.page = 1;
    emits('getDataList');
};
const selectedRowKeys = () => {
    console.log(`DataTable.selectedRowKeys...`);
    console.log(Array.from(tableState.tableSelectedRowKeys));
    return Array.from(tableState.tableSelectedRowKeys);
};
const selectedRows = () => {
    console.log(`DataTable.selectedRows...`);
    console.log(Array.from(tableState.tableSelectedRows));
    return Array.from(tableState.tableSelectedRows);
};

defineExpose({
    refresh,
    selectedRowKeys,
    selectedRows,
});

onMounted(async () => {
    log('Component <<Table>> mounted.');
    emits('getDataList');
});
</script>

<style lang="scss" scoped>
@import 'Table';
</style>
