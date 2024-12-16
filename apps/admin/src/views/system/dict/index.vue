<template>
    <app-page-header :title="$t('common.announcement_management')" />

    <el-card shadow="never">
        <app-table
            ref="dataTableRef"
            v-model="dataTableModel"
            :row-selection-enabled="dataTableRowSelectionEnabled"
            :toolbar-enabled="true"
            @get-data-list="getDataList"
        >
            <template #toolbar>
                <el-button type="primary" @click="handleFormShow(0)">
                    {{ $t('common.button_add') }}
                </el-button>
                <el-button @click="dataTableRowSelectionEnabled = !dataTableRowSelectionEnabled">
                    {{ dataTableRowSelectionEnabled ? $t('common.label_batch_operation_cancel') : $t('common.label_batch_operation') }}
                </el-button>
                <el-button danger @click="handleDelete(0)">
                    {{ $t('common.button_delete') }}
                </el-button>
            </template>
            <template #body>
                <el-table-column prop="title" :label="$t('common.field_title')" fixed="left" />
                <el-table-column :label="$t('common.field_publish_period')" :width="300">
                    <template #default="scope">
                        <div v-html="formatDatetimePeriod(scope.row.startDatetime, scope.row.endDatetime)" />
                    </template>
                </el-table-column>
                <el-table-column :label="$t('common.field_publish_status')" :width="800">
                    <template #default="scope">
                        <div v-if="scope.row.status">
                            {{ $t('common.field_publish_status_on') }}
                        </div>
                        <div v-else>
                            {{ $t('common.field_publish_status_off') }}
                        </div>
                    </template>
                </el-table-column>
                <el-table-column :label="t('common.label_action')" :width="200" fixed="right">
                    <template #default="scope">
                        <el-space>
                            <el-button type="primary" size="small" @click="showPreviewModal(scope.row)">
                                <template #icon>
                                    <app-icon icon="mdi:eye-outline" />
                                </template>
                            </el-button>
                            <el-button type="primary" size="small" @click="handleFormShow(scope.row.id)">
                                <template #icon>
                                    <app-icon icon="ant-design:edit-outlined" />
                                </template>
                            </el-button>
                            <el-popconfirm :title="$t('common.label_confirm_delete_record')" @confirm="handleDelete(scope.row.id)">
                                <template #reference>
                                    <el-button danger type="danger" size="small" @confirm="handleDelete(scope.row.id)">
                                        <template #icon>
                                            <app-icon icon="ant-design:close-outlined" />
                                        </template>
                                    </el-button>
                                </template>
                            </el-popconfirm>
                        </el-space>
                    </template>
                </el-table-column>
            </template>
        </app-table>
    </el-card>

    <el-dialog v-model="previewModalOpen" class="app-modal" :title="$t('common.label_preview')" destroy-on-close>
        <div v-html="previewModalContent" />
    </el-dialog>

    <el-dialog v-model="entityFormModalOpen" class="app-modal" :title="entityFormModalTitle" destroy-on-close>
        <template #default>
            <div class="app-modal-body">
                <el-form ref="entityFormRef" label-width="auto" :model="entityFormModel" :rules="entityFormRules" @finish="handleFormSubmit">
                    <el-form-item prop="title" :label="$t('common.field_title')">
                        <el-input v-model="entityFormModel.title" :placeholder="t('common.field_title_placeholder')" />
                    </el-form-item>
                    <el-form-item prop="content" :label="$t('common.field_content')">
                        <app-editor v-model:value="entityFormModel.content" />
                    </el-form-item>
                    <el-form-item prop="status" :label="$t('common.field_publish_status')">
                        <el-radio-group v-model="entityFormModel.status">
                            <el-radio :value="1">
                                {{ t('common.field_publish_status_finish') }}
                            </el-radio>
                            <el-radio :value="0">
                                {{ t('common.field_publish_status_unfinished') }}
                            </el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <el-form-item :label="$t('common.field_publish_period')">
                        <app-date-range-picker
                            v-model:from="entityFormModel.startDatetime"
                            v-model:to="entityFormModel.endDatetime"
                            type="datetime"
                        />
                    </el-form-item>
                    <el-form-item prop="description" :label="$t('common.field_description')">
                        <el-input v-model="entityFormModel.description" :placeholder="t('common.field_description_placeholder')" allow-clear />
                    </el-form-item>
                </el-form>
            </div>
        </template>
        <template #footer>
            <div class="app-modal-footer">
                <el-space>
                    <el-button native-type="submit" type="primary" @click="handleFormSubmit">{{ $t('common.button_submit') }}</el-button>
                    <el-button native-type="reset" @click="handleFormCancel">{{ $t('common.button_cancel') }}</el-button>
                </el-space>
            </div>
        </template>
    </el-dialog>
</template>

<script setup lang="ts">
import { announcementDeleteApi, announcementDetailsApi, announcementListApi, announcementSaveApi } from '@commons/core/api/admin/announcement';
import { AppEditor } from '@commons/core/components';
import { Key } from '@commons/core/types';
import { Announcement, defaultAnnouncement } from '@commons/core/types/announcement';
import { log } from '@commons/core/utils';
import { useDataTable } from '@commons/core/utils/data-table.ts';
import { formatDatetimePeriod } from '@commons/core/utils/date.ts';
import { AppDateRangePicker, AppIcon, AppPageHeader, AppTable } from '@commons/webapp/components';
import { showMessage } from '@commons/webapp/utils';
import { editorNotEmpty } from '@commons/webapp/utils/validation.ts';
import type { FormInstance, FormRules } from 'element-plus';
import { isEmpty } from 'radash';
import { onMounted, reactive, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';

const { t } = useI18n();
// 数据列表
const { initialize, handleResult, handleParams } = useDataTable();
const dataTableColumns = [
    {
        title: t('common.field_title'),
        dataKey: 'title',
        key: 'title',
        width: 360,
    },
    {
        title: t('common.field_publish_status'),
        dataKey: 'status',
        key: 'status',
        width: 150,
    },
    {
        title: t('common.field_publish_period'),
        dataKey: 'publishPeriod',
        key: 'publishPeriod',
        width: 500,
    },
    {
        title: t('common.field_last_modified_at'),
        dataKey: 'lastModifiedAt',
        key: 'lastModifiedAt',
        width: 200,
    },
    {
        title: t('common.label_action'),
        dataKey: 'operation',
        key: 'operation',
        width: 160,
    },
];
const dataTableOptions = {
    columns: dataTableColumns,
};
const dataTableModel = reactive(initialize(dataTableOptions));
const dataTableRef = ref<typeof AppTable>();
const dataTableRowSelectionEnabled = ref<boolean>(false);
const getDataList = async () => {
    await announcementListApi(handleParams(dataTableModel)).then((result) => {
        handleResult(dataTableModel, result);
    });
};
// 添加编辑
const entityFormModalTitle = ref<string>('');
const entityFormModalOpen = ref<boolean>(false);
const entityFormRef: Ref<FormInstance> = ref<FormInstance>();
const entityFormModel = reactive<Announcement>({});
const entityFormRules = reactive<FormRules<Announcement>>({
    title: [{ required: true, message: t('common.field_title_validation') }],
    content: [{ required: true, message: t('common.field_description_validation'), validator: editorNotEmpty }],
});
const handleFormShow = async (id?: number) => {
    entityFormRef.value?.clearValidate();
    if (id && id > 0) {
        entityFormModalTitle.value = t('common.announcement_pages_edit_title');
        await announcementDetailsApi({ id: id }).then((result) => {
            Object.assign(entityFormModel, { ...result.data });
        });
    } else {
        entityFormModalTitle.value = t('common.announcement_pages_add_title');
        Object.assign(entityFormModel, { ...defaultAnnouncement });
    }
    entityFormModalOpen.value = true;
};
const handleFormCancel = () => {
    entityFormModalOpen.value = false;
};
const handleFormSubmit = async (): Promise<void> => {
    await entityFormRef.value.validate(async (valid: boolean) => {
        if (valid) {
            announcementSaveApi(entityFormModel).then((result) => {
                if (result.code == '200') {
                    entityFormModalOpen.value = false;
                    dataTableRef.value.refresh();
                }
            });
        }
    });
};
// 删除
const handleDelete = async (id: number = 0): Promise<void> => {
    const ids: Key[] = id > 0 ? [id] : dataTableRef.value.selectedRowKeys();
    if (isEmpty(ids)) {
        showMessage({ message: t('common.label_please_select_one_record') }).then();
        return;
    }
    announcementDeleteApi({ ids: ids }).then((result) => {
        if (result.code == '200') {
            entityFormModalOpen.value = false;
            dataTableRef.value.refresh();
        }
    });
};
// 预览
const previewModalOpen = ref<boolean>(false);
const previewModalContent = ref<string>('');
const showPreviewModal = async (entity: Announcement) => {
    previewModalContent.value = entity.content;
    previewModalOpen.value = true;
};

onMounted(async (): Promise<void> => {
    log('Page <<DictIndexPage>> mounted.');
});
</script>
