<template>
    <el-card shadow="never">
        <template #title>
            <div>{{ $t('common.user_pages_change_password_title') }}</div>
        </template>
        <template #default>
            <el-form ref="form" :model="model" :rules="rules" label-width="200px" style="max-width: 500px">
                <el-form-item name="originalPassword" :label="$t('common.user_field_original_password')">
                    <el-input v-model="model.originalPassword" :placeholder="t('common.user_field_original_password_placeholder')" />
                </el-form-item>
                <el-form-item name="newPassword" :label="$t('common.user_field_new_password')">
                    <el-input v-model="model.newPassword" :placeholder="t('common.user_field_new_password')" />
                </el-form-item>
                <el-form-item name="confirmPassword" :label="$t('common.user_field_confirm_password')">
                    <el-input v-model="model.confirmPassword" :placeholder="t('common.user_field_new_password_placeholder')" />
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="onSubmit">
                        {{ $t('common.button_submit') }}
                    </el-button>
                </el-form-item>
            </el-form>
        </template>
    </el-card>
</template>

<script setup lang="ts">
import { changePasswordApi } from '@commons/core/api/user';
import { log } from '@commons/core/utils';
import { FormInstance, FormRules } from 'element-plus';
import { onMounted, reactive, Ref, ref } from 'vue';
import { useI18n } from 'vue-i18n';

const { t } = useI18n();
const form: Ref<FormInstance> = ref<FormInstance>();
const rules = reactive<FormRules>({
    originalPassword: [{ required: true, message: t('common.user_field_original_password_validation') }],
    newPassword: [{ required: true, message: t('common.user_field_new_password_validation') }],
    confirmPassword: [{ required: true, message: t('common.user_field_confirm_password_validation') }],
});
const model = reactive({
    originalPassword: '',
    newPassword: '',
    confirmPassword: '',
});
const onSubmit = async () => {
    await form.value.validate((valid: boolean) => {
        if (valid) {
            const data = {
                originalPassword: model.originalPassword,
                newPassword: model.newPassword,
            };
            changePasswordApi(data)
                .then((result) => {
                    if (result.code == 200) {
                        form.value.resetFields();
                    }
                })
                .catch((e) => {
                    log(e);
                });
        }
    });
};

onMounted(async () => {
    log('Page <<UserCenterPasswordPage>> mounted.');
});
</script>
