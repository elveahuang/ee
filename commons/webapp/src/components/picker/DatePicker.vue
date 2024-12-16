<template>
    <el-date-picker
        v-model:value="value"
        :allow-clear="allowClear"
        :allow-empty="allowEmpty"
        :show-time="showTime"
        :format="format"
        @change="onChange"
    />
</template>
<script setup lang="ts">
import {
    DATE_PATTEN,
    DATE_TIME_PATTEN,
    formatDate,
    formatDatetime,
    formatTime,
    parseDate,
    parseDatetime,
    TIME_PATTEN,
} from '@commons/core/utils/date';
import dayjs, { Dayjs } from 'dayjs';
import { isEmpty } from 'radash';
import { ref, watch } from 'vue';

const emits = defineEmits(['update:modelValue', 'update:to']);
const props = defineProps({
    with: {
        type: Number,
        required: false,
        default: 360,
    },
    type: {
        type: String,
        default: 'datetime',
    },
    modelValue: {
        type: String,
        default: '',
    },
});
const getShowTimeValue = () => {
    if (props.type === 'datetime') {
        return {
            hideDisabledOptions: true,
            defaultValue: [dayjs('00:00:00', 'HH:mm:ss'), dayjs('23:59:59', 'HH:mm:ss')],
        };
    }
    return false;
};
const getDateFormat = (type: string): string => {
    if (type === 'datetime') {
        return DATE_TIME_PATTEN;
    } else if (type === 'date') {
        return DATE_PATTEN;
    } else if (type === 'time') {
        return TIME_PATTEN;
    } else {
        return DATE_TIME_PATTEN;
    }
};
const getValue = (value: string | Date | Dayjs): Dayjs => {
    if (props.type === 'datetime') {
        return parseDatetime(value);
    } else if (props.type === 'date') {
        return parseDate(value);
    } else if (props.type === 'time') {
        return parseDate(value);
    } else {
        return null;
    }
};
const formatValue = (obj: string | Date | Dayjs): string => {
    if (!isEmpty(obj)) {
        if (props.type === 'datetime') {
            return formatDatetime(obj, '');
        } else if (props.type === 'date') {
            return formatDate(obj, '');
        } else if (props.type === 'time') {
            return formatTime(obj, '');
        }
    }
    return '';
};
const value = ref<Dayjs>(getValue(props.modelValue));
const format = ref<string>(getDateFormat(props.type));
const allowEmpty = ref<[boolean, boolean]>([true, true]);
const allowClear = ref<boolean>(true);
const showTime = ref(getShowTimeValue());
const onChange = (value: Dayjs) => {
    if (isEmpty(value)) {
        emits('update:modelValue', '');
    } else {
        emits('update:modelValue', formatValue(value));
    }
};

watch(value, (newValue) => {
    value.value = getValue(newValue);
});
</script>
