<template>
    <van-nav-bar :title="t('common.user_pages_register_title')" left-arrow @click-left="goBack()" />

    <div class="app-divider" />

    <van-form ref="form" @submit="onSubmit">
        <van-cell-group inset>
            <van-field v-model="model.username" :placeholder="t('common.user_field_username_placeholder')" :rules="rules.username" />
            <van-field v-model="model.email" :placeholder="t('common.user_field_email_placeholder')" :rules="rules.email" />
            <van-field v-model="model.captchaValue" :placeholder="t('common.field_captcha_placeholder')" :rules="rules.captchaValue">
                <template #button>
                    <app-mail-captcha :email="model.email" @change="handleCaptchaChange" />
                </template>
            </van-field>
            <van-field
                v-model="model.password"
                :type="showPassword ? 'text' : 'password'"
                :placeholder="t('common.user_field_password_placeholder')"
                :rules="rules.password"
            >
                <template #button>
                    <app-icon :icon="showPassword ? 'mdi:eye-off-outline' : 'mdi:eye-outline'" @click="toggle(!showPassword)" />
                </template>
            </van-field>
        </van-cell-group>
        <div class="m-4">
            <van-button block type="primary" native-type="submit" :disabled="registerButtonDisabled">
                {{ t('common.button_register') }}
            </van-button>
        </div>
    </van-form>

    <van-row class="px-4 pt-4">
        <van-col span="12" class="text-left">
            <van-space>
                <app-locale-picker />
                <app-dark-mode-picker />
                <app-theme-picker />
            </van-space>
        </van-col>
        <van-col span="12" class="text-right">
            <van-button plain hairline type="primary" size="small" @click="router.push({ path: '/login' })">
                {{ t('common.button_login') }}
            </van-button>
        </van-col>
    </van-row>
</template>

<script setup lang="ts">
import { registerApi, RegisterApiResult } from '@commons/core/api/user';
import { router } from '@commons/core/router';
import { R } from '@commons/core/types';
import { Register } from '@commons/core/types/user';
import { goBack, log } from '@commons/core/utils';
import { AppDarkModePicker, AppIcon, AppLocalePicker, AppMailCaptcha, AppThemePicker } from '@commons/mobile/components';
import { showMessage } from '@commons/mobile/utils';
import { EmailValidator, PasswordValidator, UsernameValidator } from '@commons/mobile/utils/validation';
import { useToggle } from '@vueuse/core';
import { FieldRule, FormInstance } from 'vant';
import { onMounted, reactive, Ref, ref } from 'vue';
import { useI18n } from 'vue-i18n';

const { t } = useI18n();
const form: Ref<FormInstance> = ref<FormInstance>();
const rules = reactive<Record<string, FieldRule[]>>({
    username: [
        { required: true, message: t('common.user_field_username_validation') },
        { validator: UsernameValidator, message: t('common.user_field_username_validation_check') },
    ],
    email: [
        { required: true, message: t('common.user_field_email_validation') },
        { validator: EmailValidator, message: t('common.user_field_email_validation_check') },
    ],
    password: [
        { required: true, message: t('common.user_field_password_validation') },
        { validator: PasswordValidator, message: t('common.user_field_password_validation_check') },
    ],
    captchaValue: [{ required: true, message: t('common.field_captcha_validation') }],
});
const model = reactive<Register>({
    username: '',
    email: '',
    password: '',
    captchaKey: '',
    captchaValue: '',
});
const [showPassword, toggle] = useToggle(false);
const registerButtonDisabled: Ref<boolean> = ref(false);
const handleCaptchaChange = async (captchaKey: string) => {
    model.captchaKey = captchaKey;
};
const onSubmit = () => {
    registerApi(model).then((result: R<RegisterApiResult>) => {
        if (result.code == 200) {
            showMessage({ message: t('common.label_register_success') }, () => {
                router.push({ path: '/login' });
            });
        }
    });
};

onMounted(async () => {
    log('Page <<Register>> mounted.');
});
</script>
