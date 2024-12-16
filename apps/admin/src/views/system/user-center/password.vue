<template>
    <el-card shadow="never">
        <template #title>
            <div>{{ $t('common.user_pages_change_password_title') }}</div>
        </template>
        <template #default>
            <el-form ref="form" :model="model" :rules="rules" label-width="200px" style="max-width: 500px">
                <el-form-item :required="true" name="originalPassword" :label-col="{ span: 4 }" :label="$t('common.user_field_original_password')">
                    <el-input v-model:value="model.originalPassword" :placeholder="t('common.user_field_original_password_placeholder')" />
                </el-form-item>
                <el-form-item :required="true" name="newPassword" :label-col="{ span: 4 }" :label="$t('common.user_field_new_password')">
                    <el-input v-model:value="model.newPassword" type="password" :placeholder="t('common.user_field_new_password_placeholder')" />
                </el-form-item>
                <el-form-item :required="true" name="confirmPassword" :label-col="{ span: 4 }" :label="$t('common.user_field_confirm_password')">
                    <el-input
                        v-model:value="model.confirmPassword"
                        type="password"
                        :placeholder="t('common.user_field_confirm_password_placeholder')"
                    />
                </el-form-item>
                <el-form-item :colon="false" :label-col="{ span: 16 }">
                    <template #label>
                        <el-button class="!w-36" type="primary" @click="onSubmit">
                            {{ $t('common.button_submit') }}
                        </el-button>
                    </template>
                </el-form-item>
            </el-form>
        </template>
    </el-card>
</template>

<script setup lang="ts">
import { changePasswordApi } from '@commons/core/api/user';
import { log } from '@commons/core/utils';
import { FormInstance, FormRules } from 'element-plus';
import { onMounted, Ref } from 'vue';
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
    const data = {
        originalPassword: model.originalPassword,
        newPassword: model.newPassword,
    };
    changePasswordApi(data).then((result) => {
        // if (result.code == 200) {
        //     message.success(t('common.modify_success'));
        // } else {
        //     message.error(t('common.modify_fail'));
        // }
    });
};

onMounted(async () => {
    log('Page <<UserCenterPasswordPage>> mounted.');
});
</script>
