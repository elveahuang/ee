<template>
    <van-sticky>
        <van-nav-bar :title="$t('common.user_pages_change_password_title')" left-arrow @click-left="goBack()" />
    </van-sticky>

    <div class="app-divider" />

    <van-form ref="form" @submit="onSubmit">
        <van-cell-group inset>
            <van-field
                v-model="model.originalPassword"
                type="password"
                :placeholder="t('common.user_field_original_password_placeholder')"
                :rules="rules.originalPassword"
            />
            <van-field
                v-model="model.newPassword"
                type="password"
                :placeholder="t('common.user_field_new_password_placeholder')"
                :rules="rules.newPassword"
            >
                <template #right-icon>
                    <app-icon :icon="showPassword ? 'mdi:eye-off-outline' : 'mdi:eye-outline'" @click="toggle(!showPassword)" />
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
import { settings } from '@/settings';
import { changePasswordApi } from '@commons/core/api/user';
import { router } from '@commons/core/router';
import { useUserStore } from '@commons/core/store';
import { goBack, log } from '@commons/core/utils';
import { AppIcon } from '@commons/mobile/components';
import { showMessage } from '@commons/mobile/utils';
import { useToggle } from '@vueuse/core';
import { FieldRule, FormInstance } from 'vant';
import { onMounted, reactive, Ref, ref } from 'vue';
import { useI18n } from 'vue-i18n';

const { t } = useI18n();
const { logout } = useUserStore();
const [showPassword, toggle] = useToggle(false);
const form: Ref<FormInstance> = ref<FormInstance>();
const rules = reactive<Record<string, FieldRule[]>>({
    originalPassword: [{ required: true, message: t('common.user_field_original_password_validation') }],
    newPassword: [{ required: true, message: t('common.user_field_new_password_validation') }],
    confirmPassword: [{ required: true, message: t('common.user_field_confirm_password_validation') }],
});
const model = reactive({
    originalPassword: '',
    newPassword: '',
});
const onSubmit = () => {
    const data = {
        originalPassword: model.originalPassword,
        newPassword: model.newPassword,
    };
    changePasswordApi(data).then((result) => {
        if (result.code == 200) {
            showMessage({ message: t('common.user_pages_change_password_success') }, () => {
                logout().then(() => {
                    router.push(settings.app.getLogoutSuccessUrl());
                });
            });
        } else {
            showMessage({ message: t('common.user_pages_change_password_fail') });
        }
    });
};

onMounted(async () => {
    log('Page <<ChangePasswordPage>> mounted.');
});
</script>
