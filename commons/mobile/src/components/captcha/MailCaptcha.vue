<template>
    <van-button style="min-width: 60px" size="small" type="primary" :disabled="disabled" @click="getCaptcha">
        {{ text }}
    </van-button>
</template>

<script setup lang="ts">
import { getEmailCaptchaApi } from '@commons/core/api/captcha.ts';
import { log } from '@commons/core/utils';
import { isEmpty } from 'radash';
import { showDialog } from 'vant';
import { onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';

const { t } = useI18n();
const emits = defineEmits(['change']);
const props = defineProps({
    email: {
        type: String,
        default: '',
    },
});
const disabled = ref(false);
const text = ref(t('common.field_captcha_button'));

let time = 0;
let timer: any = null;
const getCaptcha = async (): Promise<void> => {
    if (isEmpty(props.email)) {
        showDialog({ message: t('common.field_captcha_validation_email') }).then();
        return;
    }
    await getEmailCaptchaApi({ email: props.email }).then((resp) => {
        emits('change', resp.data.key);

        disabled.value = true;
        time = 60;
        timer = setInterval(() => {
            time--;
            if (time <= 0) {
                text.value = t('common.field_captcha_button');
                disabled.value = false;
                clearInterval(timer);
            } else {
                // text.value = t(`common.field_captcha_tip`, { time: time });
                text.value = t(`${time}`);
            }
        }, 1000);
    });
};

onMounted(async () => {
    log('Component <<MailCaptcha>> mounted.');
});
</script>
