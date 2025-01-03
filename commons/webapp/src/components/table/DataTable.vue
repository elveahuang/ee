<template>
    <div class="data-table">
        <div v-show="toolbarEnabled" class="data-table-toolbar">
            <slot name="toolbar" />
        </div>
        <div class="data-table-body" style="height: 360px">
            <el-auto-resizer>
                <template #default="{ width, height }">
                    <el-table-v2
                        ref="tableRef"
                        :fixed="fixed"
                        :width="width"
                        :height="height"
                        :columns="table.columns"
                        :data="table.items"
                        :row-key="rowKey"
                    >
                        <template #cell="{ cellData, column, columns, columnIndex, rowData, rowIndex }">
                            <slot
                                name="cell"
                                :cell-data="cellData"
                                :column="column"
                                :columns="columns"
                                :column-index="columnIndex"
                                :record="rowData"
                                :record-index="rowIndex"
                            />
                        </template>
                        <template #header-cell="{ column, columns, columnIndex, headerIndex }">
                            <slot name="headerCell" :column="column" :columns="columns" :column-index="columnIndex" :header-index="headerIndex" />
                        </template>
                    </el-table-v2>
                </template>
            </el-auto-resizer>
        </div>
        <div class="data-table-footer">
            <el-pagination v-model="tablePagination.current" :total="tablePagination.total" :page-size="tablePagination.pageSize" />
        </div>
    </div>
</template>

<script setup lang="ts">
import { Key } from '@commons/core/types';
import { log } from '@commons/core/utils';
import { DataTable, DataTableState } from '@commons/core/utils/data-table';
import { snake } from 'radash';
import { computed, onMounted } from 'vue';

const emits = defineEmits(['getDataList']);
const props = defineProps({
    model: {
        type: DataTable,
        require: false,
        default: () => {},
    },
    toolbarEnabled: {
        type: Boolean,
        require: false,
        default: false,
    },
    rowSelectionEnabled: {
        type: Boolean,
        require: false,
        default: false,
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
const table = reactive<DataTable>(props.model);
const tableState = reactive<DataTableState>({
    tableSelectedRowKeys: [],
    tableSelectedRows: [],
});
const tableRef = ref<any>();
const tablePagination = computed(() => {
    return {
        pageSize: table.pagination.size,
        current: table.pagination.page,
        total: table.pagination.total as number,
    };
});
const tableRowSelection = computed(() => {
    if (props.rowSelectionEnabled) {
        if (props.rowSelection) {
            return props.rowSelection;
        } else {
            return {
                type: props.rowSelectionMultipleEnabled ? 'checkbox' : 'radio',
                onChange: (selectedRowKeys: Key[], selectedRows: []) => {
                    tableState.tableSelectedRowKeys = selectedRowKeys;
                    tableState.tableSelectedRows = selectedRows;
                },
                getCheckboxProps: (record: any) => ({
                    id: record[props.rowKey],
                }),
            };
        }
    } else {
        return null;
    }
});
const handleResizeColumn = (w: any, col: any) => {
    col.width = w;
};
const onTableChange = (page: { pageSize: number; current: number }, filters: any, sorter: any) => {
    table.pagination.size = page.pageSize;
    table.pagination.page = page.current;
    if (sorter) {
        table.sort.property = snake(sorter.field);
        table.sort.order = sorter.order === 'ascend' ? 'asc' : 'desc';
    }
    emits('getDataList');
};
const refresh = async (): Promise<void> => {
    table.pagination.page = 1;
    emits('getDataList');
};
const selectedRowKeys = () => {
    return Array.from(tableState.tableSelectedRowKeys);
};
const selectedRows = () => {
    return Array.from(tableState.tableSelectedRows);
};

defineExpose({
    refresh,
    selectedRowKeys,
    selectedRows,
});

onMounted(async () => {
    log('Component <<DataTable>> mounted.');
    emits('getDataList');
});
</script>

<style lang="scss">
.data-table {
    @apply w-full;
}

.data-table .data-table-toolbar {
    @apply mb-4 w-full;
}

.data-table .data-table-body {
    @apply w-full;
}

.data-table .data-table-footer {
    @apply mt-4 w-full text-center;
}
</style>
