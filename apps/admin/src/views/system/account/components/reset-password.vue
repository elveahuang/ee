<template>
    <el-dialog v-model:open="open" class="app-modal" :title="$t('common.user_pages_reset_password_title')" @ok="handleSubmit" @cancel="handleCancel">
        <el-card>
            <el-form ref="entityForm" :model="model" :rules="rules" :label-col="{ span: 4 }" :wrapper-col="{ span: 12 }" @finish="handleSubmit">
                <el-form-item name="password" has-feedback :label="$t('common.user_field_password')">
                    <el-input
                        v-model:value="model.password"
                        type="password"
                        :placeholder="$t('common.user_field_password_placeholder')"
                        autocomplete="off"
                    />
                </el-form-item>
            </el-form>
        </el-card>
    </el-dialog>
</template>

<script setup lang="ts">
import { resetPasswordApi } from '@commons/core/api/admin/account.ts';
import { AccountEntity } from '@commons/core/types/account.ts';
import { log } from '@commons/core/utils';
import { FormInstance, FormRules } from 'element-plus';
import { computed, reactive, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';

const { t } = useI18n();
const emits = defineEmits(['update:show', 'onClose', 'onCancel']);
const props = defineProps({
    show: {
        type: Boolean,
        required: false,
        default: false,
    },
    value: {
        type: String,
        required: false,
        default: '0',
    },
});
const open = computed<boolean>(() => props.show);
const entityForm: Ref<FormInstance> = ref<FormInstance>();
const model = reactive<AccountEntity>({});
const rules = reactive<FormRules>({
    password: [
        { required: true, message: t('common.user_field_password_validation') },
        // {
        //     validator: checkPassword,
        //     message: t('common.user_field_password_validation_check'),
        // },
    ],
});
const handleSubmit = async () => {
    await entityForm.value
        ?.validate()
        .then(async () => {
            resetPasswordApi(model).then((result) => {
                if (result.code == '200') {
                    entityForm.value?.resetFields();
                }
            });
        })
        .catch((e) => {
            log(e);
        });
    emits('update:show', false);
};
const handleCancel = () => {
    entityForm.value?.resetFields();
    emits('update:show', false);
    emits('onCancel');
};
</script>
