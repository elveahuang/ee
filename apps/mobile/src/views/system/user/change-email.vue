<template>
    <van-sticky>
        <van-nav-bar :title="$t('common.user_pages_change_email_title')" left-arrow @click-left="goBack()" />
    </van-sticky>

    <div class="app-divider" />

    <van-form ref="form" @submit="onSubmit">
        <van-cell-group inset>
            <van-field
                v-model="model.email"
                :label="t('common.user_field_current_email')"
                border
                label-width="4em"
                label-align="right"
                readonly
                :placeholder="model.email"
                :rules="rules.email"
            />
            <van-field
                v-model="model.password"
                :label="t('common.user_field_password')"
                label-width="4em"
                label-align="right"
                type="password"
                :placeholder="t('common.user_field_password_placeholder')"
                :rules="rules.password"
            />
            <van-field
                v-model="model.newEmail"
                :label="t('common.user_field_new_email')"
                label-width="4em"
                label-align="right"
                :placeholder="t('common.user_field_new_email_placeholder')"
                :rules="rules.newEmail"
            />
            <van-field
                v-model="model.captchaValue"
                :label="t('common.field_captcha')"
                label-width="4em"
                label-align="right"
                :placeholder="t('common.field_captcha_placeholder')"
                :rules="rules.captchaValue"
            >
                <template #button>
                    <app-mail-captcha :email="model.newEmail" @change="handleCaptchaChange" />
                </template>
            </van-field>
        </van-cell-group>
        <div class="m-4">
            <van-button block type="primary" native-type="submit">
                {{ t('common.button_submit') }}
            </van-button>
        </div>
    </van-form>
</template>

<script setup lang="ts">
import { userInfoApi } from '@commons/core/api/auth.ts';
import { changeEmailApi } from '@commons/core/api/user';
import { router } from '@commons/core/router';
import { goBack, log } from '@commons/core/utils';
import { showMessage } from '@commons/mobile/utils';
import { FieldRule, FormInstance } from 'vant';
import { onMounted, reactive, Ref, ref } from 'vue';
import { useI18n } from 'vue-i18n';

const { t } = useI18n();
const form: Ref<FormInstance> = ref<FormInstance>();
const rules = reactive<Record<string, FieldRule[]>>({
    originalPassword: [{ required: true, message: t('common.user_field_original_password_validation') }],
    newPassword: [{ required: true, message: t('common.user_field_new_password_validation') }],
    confirmPassword: [{ required: true, message: t('common.user_field_confirm_password_validation') }],
});

const model = reactive({
    email: '',
    password: '',
    newEmail: '',
    captchaKey: '',
    captchaValue: '',
});

const handleCaptchaChange = async (captchaKey: string) => {
    model.captchaKey = captchaKey;
};
const onSubmit = () => {
    const params = {
        email: model.email,
        password: model.password,
        newEmail: model.newEmail,
        captchaKey: model.captchaKey,
        captchaValue: model.captchaValue,
    };
    changeEmailApi(params).then((result) => {
        if (result.code == 200) {
            showMessage({ message: t('common.user_pages_change_email_success') }, () => {
                router.push({ path: '/me' });
            });
        } else {
            showMessage({ message: t('common.user_pages_change_email_fail') });
        }
    });
};

onMounted(async () => {
    log('Page <<ChangePasswordPage>> mounted.');
    userInfoApi().then((result) => {
        Object.assign(model, result.data);
    });
});
</script>
