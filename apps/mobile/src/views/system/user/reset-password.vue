<template>
    <van-nav-bar :title="t('common.user_pages_reset_password_title')" left-arrow @click-left="goBack()" />

    <div class="app-divider" />

    <van-form ref="form" @submit="onSubmit">
        <van-cell-group inset>
            <van-field v-model="model.password" :placeholder="t('common.user_field_password_placeholder')" :rules="rules.password" />
            <van-field
                v-model="model.passwordConfirm"
                :placeholder="t('common.user_field_password_confirm_placeholder')"
                :rules="rules.passwordConfirm"
            />
        </van-cell-group>
        <div class="m-4">
            <van-button block type="primary" native-type="submit">
                {{ t('common.button_submit') }}
            </van-button>
        </div>
    </van-form>
</template>

<script setup lang="ts">
import { resetPasswordApi } from '@commons/core/api/user';
import { router } from '@commons/core/router';
import { goBack, log } from '@commons/core/utils';
import storage from '@commons/core/utils/storage';
import { showMessage } from '@commons/mobile/utils';
import { FormInstance } from 'vant';
import { onMounted, reactive, Ref, ref } from 'vue';
import { useI18n } from 'vue-i18n';

const { t } = useI18n();
const form: Ref<FormInstance> = ref<FormInstance>();
const rules = reactive({
    password: [{ required: true, message: t('common.user_field_password_validation') }],
    passwordConfirm: [{ required: true, message: t('common.user_field_password_validation') }],
});
const model = reactive({
    username: '',
    email: '',
    password: '',
    passwordConfirm: '',
    captchaKey: '',
    captchaValue: '',
});
const onSubmit = () => {
    const data = {
        email: model.email,
        username: model.username,
        password: model.password,
        passwordConfirm: model.passwordConfirm,
        captchaKey: model.captchaKey,
        captchaValue: model.captchaValue,
    };
    resetPasswordApi(data).then((resp) => {
        if (resp.code == 200) {
            showMessage({ message: '修改成功，请登录！' }, () => {
                router.push({ path: '/login' });
            });
        } else {
            showMessage({ message: '修改失败，请重试！' });
        }
    });
};

onMounted(async () => {
    log('Page <<ResetPassword>> mounted.');
    model.username = storage.getItem('username');
    model.email = storage.getItem('email');
    model.captchaKey = storage.getItem('captchaKey');
    model.captchaValue = storage.getItem('captchaValue');
});
</script>
