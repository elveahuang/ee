<template>
    <el-card shadow="never">
        <template #title>
            <div>{{ $t('common.user_pages_change_password_title') }}</div>
        </template>

        <el-form ref="form" :model="model" :rules="rules" @finish="onSubmit" @finish-failed="onFailed">
            <el-form-item name="displayName" :label="$t('common.user_field_display_name')">
                <el-input v-model:value="model.displayName" :placeholder="t('common.user_field_display_name_placeholder')" />
            </el-form-item>
            <el-form-item>
                <el-button html-type="submit" type="primary" @click="onSubmit">
                    {{ $t('common.button_submit') }}
                </el-button>
            </el-form-item>
        </el-form>
    </el-card>
</template>

<script setup lang="ts">
import { updateAccountApi } from '@commons/core/api/user';
import { useUserStore } from '@commons/core/store';
import { log } from '@commons/core/utils';
import { FormInstance, FormRules } from 'element-plus';
import { onMounted, reactive, Ref, ref } from 'vue';
import { useI18n } from 'vue-i18n';

const { t } = useI18n();
const { getUserInfo } = useUserStore();
const form: Ref<FormInstance> = ref<FormInstance>();
const rules = reactive<FormRules>({
    displayName: [{ required: true, message: t('common.user_field_display_name_validation') }],
});
const model = reactive({
    displayName: '',
});
const onFailed = async (errors: any) => {
    log(errors);
};
const onSubmit = async () => {
    await form.value
        .validate()
        .then(async () => {
            const data = {
                displayName: model.displayName,
            };
            updateAccountApi(data)
                .then((result) => {
                    if (result.code == 200) {
                        form.value.resetFields();
                        getUserInfo().then();
                    }
                })
                .catch((e) => {
                    log(e);
                });
        })
        .catch((e) => {
            log(e);
        });
};

onMounted(async () => {
    log('Page <<UserCenterAccountPage>> mounted.');
});
</script>
