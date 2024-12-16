<template>
    <el-card shadow="never">
        <template #title>
            <div>{{ $t('common.user_pages_change_email_title') }}</div>
        </template>
        <template #default>
            <el-form ref="form" :model="model" :rules="rules" label-width="200px" style="max-width: 500px">
                <el-form-item name="email" :label-col="{ span: 4 }" :label="$t('common.user_field_current_email')">
                    <el-input v-model:value="model.email" :disabled="true" :placeholder="model.email" />
                </el-form-item>
                <el-form-item name="password" :required="true" :label-col="{ span: 4 }" :label="$t('common.user_field_password')">
                    <el-input v-model:value="model.password" type="password" :placeholder="t('common.user_field_password')" />
                </el-form-item>
                <el-form-item name="newEmail" :required="true" :label-col="{ span: 4 }" :label="$t('common.user_field_new_email_placeholder')">
                    <!--                    <el-input-group compact>-->
                    <!--                        <el-input-->
                    <!--                            v-model:value="model.newEmail"-->
                    <!--                            :placeholder="t('common.user_field_email_validation')"-->
                    <!--                            style="width: calc(100% - 100px)"-->
                    <!--                        />-->
                    <!--                        <mail-captcha :email="model.newEmail" @change="onCaptchaChange" />-->
                    <!--                    </el-input-group>-->
                </el-form-item>
                <el-form-item name="captchaValue" :required="true" :label-col="{ span: 4 }" :label="$t('common.field_captcha')">
                    <el-input v-model:value="model.captchaValue" :placeholder="t('common.field_captcha_placeholder')" />
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
import { userInfoApi } from '@commons/core/api/auth.ts';
import { changeEmailApi } from '@commons/core/api/user';
import { log } from '@commons/core/utils';
import { FormInstance, FormRules } from 'element-plus';
import { onMounted, reactive, Ref, ref } from 'vue';
import { useI18n } from 'vue-i18n';

const { t } = useI18n();
const form: Ref<FormInstance> = ref<FormInstance>();
const rules = reactive<FormRules>({
    password: [{ required: true, message: t('common.user_field_password_placeholder') }],
    newEmail: [{ required: true, message: t('common.user_field_new_email_validation') }],
    captchaValue: [{ required: true, message: t('common.field_captcha_placeholder') }],
});
const model = reactive({
    email: '',
    password: '',
    newEmail: '',
    captchaKey: '',
    captchaValue: '',
});

const onCaptchaChange = async (captchaKey: string) => {
    model.captchaKey = captchaKey;
};

const onSubmit = async () => {
    changeEmailApi({
        email: model.email,
        password: model.password,
        newEmail: model.newEmail,
        captchaKey: model.captchaKey,
        captchaValue: model.captchaValue,
    }).then((result) => {
        // if (result.code == 200) {
        //     message.success(t('common.modify_success'));
        // } else {
        //     message.error(t('common.modify_fail'));
        // }
    });
};

onMounted(async () => {
    log('Page <<UserCenterPasswordPage>> mounted.');
    userInfoApi()
        .then((result) => {
            log('用户信息结果：');
            log(result.data);
            Object.assign(model, result.data);
        })
        .catch((e) => {});
});
</script>
