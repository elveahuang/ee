<template>
    <van-nav-bar :title="t('common.user_pages_login_title')" left-arrow @click-left="goBack('/tabs/welcome')" />

    <div class="app-divider" />

    <van-form ref="form" validate-trigger="onSubmit" @submit="onSubmit" @failed="onFailed">
        <van-cell-group inset>
            <van-field v-model="model.username" :placeholder="t('common.user_field_username')" :rules="rules.username" />
            <van-field
                v-model="model.password"
                :placeholder="t('common.user_field_password')"
                :rules="rules.password"
                :type="showPassword ? 'text' : 'password'"
            >
                <template #right-icon>
                    <app-icon :icon="showPassword ? 'mdi:eye-off-outline' : 'mdi:eye-outline'" @click="toggle(!showPassword)" />
                </template>
            </van-field>
            <van-field
                v-if="isLoginCaptchaEnabled"
                v-model="model.captchaValue"
                :placeholder="t('common.field_captcha_placeholder')"
                :rules="rules.captchaValue"
            >
                <template #button>
                    <app-code-captcha ref="captcha" @change="onCaptchaChange" />
                </template>
            </van-field>
        </van-cell-group>

        <div class="m-4">
            <van-button block native-type="submit" type="primary" :disabled="loginButtonDisabled">
                {{ t('common.button_login') }}
            </van-button>
        </div>

        <van-row class="px-4 pt-4">
            <van-col span="12" class="text-left">
                <van-space>
                    <app-locale-picker />
                    <app-dark-mode-picker />
                    <app-theme-picker />
                </van-space>
            </van-col>
            <van-col span="12" class="text-right">
                <van-space>
                    <van-button plain hairline type="primary" size="small" @click="router.push({ path: 'find-password' })">
                        {{ t('common.user_pages_find_password_title') }}
                    </van-button>
                    <van-button plain hairline type="primary" size="small" @click="router.push({ path: 'register' })">
                        {{ t('common.user_pages_register_title') }}
                    </van-button>
                </van-space>
            </van-col>
        </van-row>
    </van-form>
</template>

<script setup lang="ts">
import { settings } from '@/settings';
import { LoginApiResult } from '@commons/core/api/auth.ts';
import { useAppStore, useUserStore } from '@commons/core/store';
import { Credentials } from '@commons/core/types/user';
import { credentials, goBack, log } from '@commons/core/utils';
import { AppCodeCaptcha, AppDarkModePicker, AppIcon, AppLocalePicker, AppThemePicker } from '@commons/mobile/components';
import { showMessage } from '@commons/mobile/utils';
import { useToggle } from '@vueuse/core';
import { FormInstance } from 'vant';
import { onMounted, reactive, Ref, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRouter } from 'vue-router';

const { t } = useI18n();
const { isLoginCaptchaEnabled } = useAppStore();
const { login, getUserInfo } = useUserStore();
const router = useRouter();
const [showPassword, toggle] = useToggle(false);
const form: Ref<FormInstance> = ref<FormInstance>();
const captcha: Ref<typeof AppCodeCaptcha> = ref<typeof AppCodeCaptcha>();
const loginButtonDisabled = ref(false);
const model = reactive<Credentials>(credentials);
const rules = reactive({
    username: [{ required: true, message: t('common.user_field_username_validation_login') }],
    password: [{ required: true, message: t('common.user_field_password_validation') }],
    captchaValue: [{ required: isLoginCaptchaEnabled, message: t('common.field_captcha_validation') }],
});
const onFailed = async (errors: any) => {
    log(errors);
};
const onSubmit = async () => {
    loginButtonDisabled.value = true;
    login(model)
        .then(async (result: LoginApiResult) => {
            if (result.access_token && result.refresh_token) {
                await getUserInfo().then();
                router.push(settings.app.getLoginSuccessUrl()).then();
            } else if (result?.code && result?.code >= 10000000) {
                showMessage({ message: t(`common.error__${result?.code}`) });
            } else {
                showMessage({ message: t('common.system_error') });
            }
        })
        .finally(() => {
            loginButtonDisabled.value = false;
            if (isLoginCaptchaEnabled) {
                model.captchaKey = '';
                model.captchaValue = '';
                captcha.value.refresh();
            }
        });
};
const onCaptchaChange = async (captchaKey: string) => {
    model.captchaKey = captchaKey;
};

onMounted(async () => {
    log('Page <<Login>> mounted.');
});
</script>
