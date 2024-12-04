<template>
    <van-button plain hairline type="primary" size="small" @click="show = true">
        <template #icon>
            <van-icon>
                <v-icon icon="ant-design:skin-outlined" />
            </van-icon>
        </template>
    </van-button>
    <van-popup v-model:show="show" round position="bottom">
        <van-picker :columns="items" @confirm="onConfirm" @cancel="show = false" />
    </van-popup>
</template>

<script setup lang="ts">
import { useTheme } from '@commons/core/hooks/useTheme';
import { VIcon } from '@commons/core/utils';
import { getTheme, Theme, themes, ThemeType } from '@commons/core/utils/theme';
import { isArray } from 'radash';
import { PickerConfirmEventParams } from 'vant';
import { Ref, ref } from 'vue';

const { setTheme } = useTheme();
const show: Ref<boolean> = ref<boolean>(false);
const items = ref(
    themes.map((t: ThemeType): { text: string; value: Theme } => {
        return { text: t.title, value: t.theme };
    }),
);
const onConfirm = async (params: PickerConfirmEventParams): Promise<void> => {
    show.value = false;
    if (isArray(params.selectedValues)) {
        await setTheme(getTheme(params.selectedValues[0] as string)).then();
    }
};
</script>
