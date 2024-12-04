<template>
    <van-cell is-link arrow-direction="down" :title="title" :value="text" @click="show = true" />
    <van-popup v-model:show="show" round position="bottom">
        <van-picker :columns="items" @confirm="onConfirm" @cancel="show = false" />
    </van-popup>
</template>

<script setup lang="ts">
import { useLocale } from '@commons/core/hooks/useLocale';
import { useAppStore } from '@commons/core/store';
import { getLocale, getLocaleLabel, locales } from '@commons/core/utils/locale';
import { isArray } from 'radash';
import { PickerConfirmEventParams } from 'vant';
import { computed, ref, watch } from 'vue';
import { useI18n } from 'vue-i18n';

const { t } = useI18n();
const appStore = useAppStore();
const { setLocale } = useLocale();
const show = ref<boolean>(false);
const title = ref(t('common.label_language'));
const text = ref(t(getLocaleLabel(appStore.locale)));
const appLocale = computed(() => appStore.locale);
const items = ref(
    locales.map((l) => {
        return { text: t(l.label), value: l.locale };
    }),
);
const onConfirm = async (params: PickerConfirmEventParams): Promise<void> => {
    show.value = false;
    if (isArray(params.selectedValues)) {
        await setLocale(getLocale(params.selectedValues[0] as string)).then();
    }
};

watch(appLocale, (value) => {
    text.value = t(getLocaleLabel(value));
});
</script>
