<template>
    <van-button plain hairline type="primary" size="small" @click="show = true">
        <template #icon>
            <van-icon>
                <v-icon icon="mdi:translate" />
            </van-icon>
        </template>
    </van-button>
    <van-popup v-model:show="show" round position="bottom">
        <van-picker :columns="items" @confirm="onConfirm" @cancel="show = false" />
    </van-popup>
</template>

<script setup lang="ts">
import { useLocale } from '@commons/core/hooks/useLocale';
import { VIcon } from '@commons/core/utils';
import { getLocale, locales } from '@commons/core/utils/locale';
import { isArray } from 'radash';
import { PickerConfirmEventParams } from 'vant';
import { ref } from 'vue';

const { setLocale } = useLocale();
const show = ref<boolean>(false);
const items = ref(
    locales.map((l) => {
        return { text: l.title, value: l.locale };
    }),
);
const onConfirm = async (params: PickerConfirmEventParams): Promise<void> => {
    show.value = false;
    if (isArray(params.selectedValues)) {
        await setLocale(getLocale(params.selectedValues[0] as string)).then();
    }
};
</script>
