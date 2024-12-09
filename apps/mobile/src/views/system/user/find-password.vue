<template>
    <van-nav-bar :title="t('common.user_pages_find_password_title')" left-arrow @click-left="goBack()" />

    <div class="app-divider" />

    <van-form v-if="formFindEmailShow" ref="formFindEmail" @submit="onSubmitFindEmail">
        <van-cell-group inset>
            <van-field v-model="modelFindEmail.email" :placeholder="t('common.user_field_email_placeholder')" :rules="rulesFindEmail.email" />
            <van-field
                v-model="modelFindEmail.captchaValue"
                :placeholder="t('common.field_captcha_placeholder')"
                :rules="rulesFindEmail.captchaValue"
            >
                <template #button>
                    <app-mail-captcha :email="modelFindEmail.email" @change="handleCaptchaChange" />
                </template>
            </van-field>
        </van-cell-group>
        <div class="m-4">
            <van-button block type="primary" native-type="submit">
                {{ $t('common.button_next') }}
            </van-button>
        </div>
    </van-form>

    <van-form v-if="formResetPasswordShow" ref="formResetPassword" @submit="onSubmitResetPassword">
        <van-cell-group inset>
            <van-field
                v-model="modelResetPassword.password"
                :type="formResetPasswordShowPassword ? 'text' : 'password'"
                :placeholder="t('common.user_field_password_placeholder')"
                :rules="rulesResetPassword.passwordConfirm"
            >
                <template #right-icon>
                    <app-icon
                        :icon="formResetPasswordShowPassword ? 'mdi:eye-off-outline' : 'mdi:eye-outline'"
                        @click="toggle(!formResetPasswordShowPassword)"
                    />
                </template>
            </van-field>
        </van-cell-group>
        <div class="m-4">
            <van-button block type="primary" native-type="submit">
                {{ t('common.button_submit') }}
            </van-button>
        </div>
    </van-form>

    <van-row class="px-4 pt-4">
        <van-col span="24" class="text-right">
            <van-button plain hairline type="primary" size="small" @click="router.push({ path: '/login' })">
                {{ t('common.button_login') }}
            </van-button>
        </van-col>
    </van-row>
</template>

<script setup lang="ts">
import { forgotPasswordApi, resetPasswordApi } from '@commons/core/api/user';
import { router } from '@commons/core/router';
import { goBack, log } from '@commons/core/utils';
import { AppIcon } from '@commons/mobile/components';
import { showMessage } from '@commons/mobile/utils';
import { useToggle } from '@vueuse/core';
import { FormInstance } from 'vant';
import { onMounted, reactive, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';

const { t } = useI18n();
// ------------------------------------------------------------------------------------------------------------------------
// 找回邮箱
// ------------------------------------------------------------------------------------------------------------------------
const formFindEmailShow = ref<boolean>(true);
const formFindEmail: Ref<FormInstance> = ref<FormInstance>();
const rulesFindEmail = reactive({
    email: [{ required: true, message: t('common.user_field_email_validation') }],
    captchaValue: [{ required: true, message: t('common.field_captcha_validation') }],
});
const modelFindEmail = reactive({
    email: '',
    captchaKey: '',
    captchaValue: '',
});
const onSubmitFindEmail = () => {
    const data = {
        email: modelFindEmail.email,
        captchaKey: modelFindEmail.captchaKey,
        captchaValue: modelFindEmail.captchaValue,
    };
    forgotPasswordApi(data).then(async (resp) => {
        if (resp.code == 200) {
            formFindEmailShow.value = false;
            formResetPasswordShow.value = true;
            modelResetPassword.username = resp.data.username;
            modelResetPassword.email = resp.data.email;
            modelResetPassword.captchaKey = modelFindEmail.captchaKey;
            modelResetPassword.captchaValue = modelFindEmail.captchaValue;
        } else {
            showMessage({ message: t('common.field_captcha_validation_email_check') });
            modelFindEmail.captchaKey = '';
            modelFindEmail.captchaValue = '';
        }
    });
};
const handleCaptchaChange = async (captchaKey: string) => {
    modelFindEmail.captchaKey = captchaKey;
};
// ------------------------------------------------------------------------------------------------------------------------
// 重置密码
// ------------------------------------------------------------------------------------------------------------------------
const [formResetPasswordShowPassword, toggle] = useToggle(false);
const formResetPasswordShow = ref<boolean>(false);
const formResetPassword = ref<FormInstance>();
const rulesResetPassword = reactive({
    password: [{ required: true, message: t('common.user_field_password_validation') }],
    passwordConfirm: [{ required: true, message: t('common.user_field_password_validation') }],
});
const modelResetPassword = reactive({
    username: '',
    email: '',
    password: '',
    passwordConfirm: '',
    captchaKey: '',
    captchaValue: '',
});
const onSubmitResetPassword = () => {
    const data = {
        email: modelResetPassword.email,
        username: modelResetPassword.username,
        password: modelResetPassword.password,
        passwordConfirm: modelResetPassword.passwordConfirm,
        captchaKey: modelResetPassword.captchaKey,
        captchaValue: modelResetPassword.captchaValue,
    };
    resetPasswordApi(data).then((resp) => {
        if (resp.code == 200) {
            showMessage({ message: t('common.user_pages_find_password_success') }, () => {
                router.push({ path: '/login' });
            });
        } else {
            showMessage({ message: t('common.user_pages_find_password_fail') }, () => {});
        }
    });
};

onMounted(async () => {
    log('Page <<FindPassword>> mounted.');
});
</script>
