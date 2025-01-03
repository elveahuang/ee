<template>
    <entry-layout>
        <div class="mb-36 content-center sm:w-full md:w-9/12 lg:w-6/12 xl:w-4/12">
            <el-card shadow="never" :header="$t('common.button_login')">
                <el-form ref="form" :model="model" :rules="rules" @finish="onSubmit" @finish-failed="onFailed">
                    <el-form-item prop="username">
                        <el-input size="large" v-model="model.username" :placeholder="t('common.user_field_username_placeholder_login')" />
                    </el-form-item>
                    <el-form-item prop="password">
                        <el-input size="large" v-model="model.password" :placeholder="t('common.user_field_password_placeholder')" />
                    </el-form-item>
                    <el-form-item v-if="isLoginCaptchaEnabled" name="captchaValue">
                        <el-row :wrap="false">
                            <el-col flex="auto">
                                <el-input size="large" v-model="model.captchaValue" :placeholder="t('common.field_captcha_placeholder')" />
                            </el-col>
                            <el-col flex="none" class="mx-4">
                                <app-code-captcha ref="captcha" @change="onCaptchaChange" />
                            </el-col>
                        </el-row>
                    </el-form-item>
                    <el-form-item>
                        <el-button size="large" html-type="submit" type="primary" :disabled="loginButtonDisabled" class="w-full" @click="onSubmit">
                            {{ t('common.button_login') }}
                        </el-button>
                    </el-form-item>
                </el-form>
            </el-card>
        </div>
    </entry-layout>
</template>

<script setup lang="ts">
import { EntryLayout } from '@/layouts';
import { useAppStore, useUserStore } from '@commons/core/store';
import { Credentials } from '@commons/core/types';
import { credentials, log } from '@commons/core/utils';
import { AppCodeCaptcha } from '@commons/webapp/components';
import { FormInstance, FormRules } from 'element-plus';
import { onMounted, reactive, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRouter } from 'vue-router';

const { t } = useI18n();
const router = useRouter();
const { login, getUserInfo } = useUserStore();
const { isLoginCaptchaEnabled } = useAppStore();
const form: Ref<FormInstance> = ref<FormInstance>();
const captcha: Ref<typeof AppCodeCaptcha> = ref<typeof AppCodeCaptcha>();
const loginButtonDisabled = ref(false);
const model = reactive<Credentials>(credentials);
const rules = reactive<FormRules<Credentials>>({
    username: [{ required: true, message: t('common.user_field_username_validation_login') }],
    password: [{ required: true, message: t('common.user_field_password_validation') }],
    captchaValue: [{ required: true, message: t('common.field_captcha_validation') }],
});
const onFailed = async (errors: any) => {
    log(errors);
};
const onSubmit = async () => {
    await form.value.validate().then(async () => {
        await login(model)
            .then(async () => {
                await getUserInfo().then();
                await router.push('/').then();
            })
            .finally(() => {
                model.captchaKey = '';
                model.captchaValue = '';
                loginButtonDisabled.value = false;
                // 刷新验证码
                if (isLoginCaptchaEnabled) {
                    captcha.value.refresh();
                }
            });
    });
};
const onCaptchaChange = async (captchaKey: string) => {
    model.captchaKey = captchaKey;
};

onMounted(async () => {
    log('Page <<Login>> mounted.');
});
</script>
