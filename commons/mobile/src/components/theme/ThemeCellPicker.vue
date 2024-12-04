<template>
    <van-cell is-link arrow-direction="down" :title="title" :value="text" @click="show = true" />
    <van-popup v-model:show="show" round position="bottom">
        <van-picker :columns="items" @confirm="onConfirm" @cancel="show = false" />
    </van-popup>
</template>

<script setup lang="ts">
import { useTheme } from '@commons/core/hooks/useTheme';
import { useAppStore } from '@commons/core/store';
import { getTheme, getThemeLabel, Theme, themes, ThemeType } from '@commons/core/utils/theme';
import { isArray } from 'radash';
import { PickerConfirmEventParams } from 'vant';
import { computed, Ref, ref, watch } from 'vue';
import { useI18n } from 'vue-i18n';

const { t } = useI18n();
const appStore = useAppStore();
const { setTheme } = useTheme();
const show: Ref<boolean> = ref<boolean>(false);
const title = ref(t('common.label_theme'));
const text = ref(t(getThemeLabel(appStore.theme)));
const appTheme = computed(() => appStore.theme);
const items = themes.map((theme: ThemeType): { text: string; value: Theme } => {
    return { text: t(theme.label), value: theme.theme };
});
const onConfirm = async (params: PickerConfirmEventParams): Promise<void> => {
    show.value = false;
    if (isArray(params.selectedValues)) {
        await setTheme(getTheme(params.selectedValues[0] as string)).then();
    }
};

watch(appTheme, (value) => {
    text.value = t(getThemeLabel(value));
});
</script>
