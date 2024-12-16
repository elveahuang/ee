<template>
    <el-dialog v-model:open="open" class="app-modal" :title="$t('common.account_pages_add')" @ok="handleSubmit" @cancel="handleCancel">
        <el-card>
            <el-form ref="form" :model="model" :rules="rules" :label-col="{ span: 4 }" :wrapper-col="{ span: 12 }">
                <el-form-item name="username" :label="$t('common.user_field_username')">
                    <el-input v-model:value="model.username" :placeholder="$t('common.user_field_username_placeholder')" />
                </el-form-item>
                <el-form-item name="password" :label="$t('common.user_field_password')">
                    <el-input v-model:value="model.password" type="password" :placeholder="$t('common.user_field_password_placeholder')" />
                </el-form-item>
                <el-form-item name="email" :label="$t('common.user_field_email')">
                    <el-input v-model:value="model.email" :placeholder="$t('common.user_field_email_validation')" />
                </el-form-item>
                <el-form-item name="displayName" :label="$t('common.user_field_display_name')">
                    <el-input v-model:value="model.displayName" :placeholder="$t('common.user_field_display_name_placeholder')" />
                </el-form-item>
                <el-form-item name="description" :label="$t('common.field_description')">
                    <el-input v-model:value="model.description" :placeholder="$t('common.field_description_placeholder')" />
                </el-form-item>
            </el-form>
        </el-card>
    </el-dialog>
</template>

<script setup lang="ts">
import { accountSaveApi } from '@commons/core/api/admin/account.ts';
import { AccountEntity } from '@commons/core/types/account.ts';
import { log } from '@commons/core/utils';
import { FormInstance, FormRules } from 'element-plus';
import { computed, reactive, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';

const { t } = useI18n();
const emits = defineEmits(['update:show', 'onSuccess', 'onClose', 'onCancel']);
const props = defineProps({
    show: {
        type: Boolean,
        required: false,
        default: false,
    },
});
const open = computed<boolean>(() => props.show);
const form: Ref<FormInstance> = ref<FormInstance>();
const model = reactive<AccountEntity>({});
const rules = reactive<FormRules>({
    username: [
        { required: true, message: t('common.account_field_username_validation') },
        // {
        //     validator: async (rule: Rule, payload: CheckAccountPayload): Promise<void> => {
        //         const result: R<Boolean> = await accountCheckApi({ ...payload });
        //         if (result.code == '200' && result.data === true) {
        //             return Promise.resolve();
        //         }
        //         return Promise.reject();
        //     },
        //     message: t('common.account_field_username_validation_check'),
        //     transform: (value: string): CheckAccountPayload => {
        //         return { username: value, id: 0 };
        //     },
        // },
    ],
    password: [{ required: true, message: t('common.user_field_password_validation') }],
    email: [{ required: true, message: t('common.user_field_email_validation') }],
    displayName: [{ required: true, message: t('common.user_field_display_name_validation') }],
});
const handleSubmit = async () => {
    await form.value
        .validate()
        .then(async () => {
            accountSaveApi(model).then((result) => {
                if (result.code == '200') {
                    Object.assign(model, { ...new AccountEntity() });
                    emits('update:show', false);
                    emits('onSuccess');
                    emits('onClose');
                }
            });
        })
        .catch((e) => {
            log(e);
        });
};
const handleCancel = () => {
    form.value.clearValidate();
    Object.assign(model, { ...new AccountEntity() });
    emits('update:show', false);
    emits('onCancel');
};
</script>
