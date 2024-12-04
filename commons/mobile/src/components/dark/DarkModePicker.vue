<template>
    <van-button plain hairline type="primary" size="small" @click="show = true">
        <template #icon>
            <van-icon>
                <v-icon icon="line-md:light-dark-loop" />
            </van-icon>
        </template>
    </van-button>
    <van-popup v-model:show="show" round position="bottom">
        <van-picker :columns="items" @confirm="onConfirm" @cancel="show = false" />
    </van-popup>
</template>
<script setup lang="ts">
import { useDark } from '@commons/core/hooks/useDark';
import { VIcon } from '@commons/core/utils';
import { DarkMode, darkModes, DarkModeType, getDarkMode } from '@commons/core/utils/dark';
import { isArray } from 'radash';
import { PickerConfirmEventParams } from 'vant';
import { Ref, ref } from 'vue';

const { setDarkMode } = useDark();
const show: Ref<boolean> = ref<boolean>(false);
const items = ref(
    darkModes.map((s: DarkModeType) => {
        return { text: s.title, value: s.mode };
    }),
);
const onConfirm = async (params: PickerConfirmEventParams): Promise<void> => {
    show.value = false;
    if (isArray(params.selectedValues)) {
        const mode: DarkMode = getDarkMode(params.selectedValues[0] as string);
        await setDarkMode(mode).then();
    }
};
</script>
