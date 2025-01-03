<template>
    <van-cell is-link arrow-direction="down" :title="title" :value="text" @click="show = true" />
    <van-popup v-model:show="show" round position="bottom">
        <van-picker :columns="items" @confirm="onConfirm" @cancel="show = false" />
    </van-popup>
</template>
<script setup lang="ts">
import { useDark } from '@commons/core/hooks/useDark';
import { useAppStore } from '@commons/core/store';
import { DarkMode, darkModes, DarkModeType, getDarkMode, getDarkModeLabel } from '@commons/core/utils/dark';
import { isArray } from 'radash';
import { PickerConfirmEventParams } from 'vant';
import { computed, Ref, ref, watch } from 'vue';
import { useI18n } from 'vue-i18n';

const { t } = useI18n();
const appStore = useAppStore();
const { setDarkMode } = useDark();
const show: Ref<boolean> = ref(false);
const title = ref(t('common.label_dark_mode'));
const text = ref(t(getDarkModeLabel(appStore.darkMode)));
const darkMode = computed(() => appStore.darkMode);
const items = ref(
    darkModes.map((s: DarkModeType) => {
        return { text: t(s.label), value: s.mode };
    }),
);
const onConfirm = async (params: PickerConfirmEventParams): Promise<void> => {
    show.value = false;
    if (isArray(params.selectedValues)) {
        const mode: DarkMode = getDarkMode(params.selectedValues[0] as string);
        await setDarkMode(mode).then();
    }
};

watch(darkMode, (value) => {
    text.value = t(getDarkModeLabel(value));
});
</script>
