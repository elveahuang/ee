<template>
    <el-date-picker v-model="modelValue" :type="pickerType" :format="pickerFormat" @change="onChange" />
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
    parseTime,
    TIME_PATTEN,
} from '@commons/core/utils/date';
import type { IDatePickerType } from 'element-plus/es/components/date-picker/src/date-picker.type';
import { isEmpty } from 'radash';
import { computed, ref, watch } from 'vue';

const emits = defineEmits(['update:from', 'update:to']);
const props = defineProps({
    with: {
        type: Number,
        required: false,
        default: 180,
    },
    type: {
        type: String,
        default: 'datetime',
    },
    from: {
        type: String,
        default: '',
    },
    to: {
        type: String,
        default: '',
    },
});
const getValue = (from: string | Date, to: string | Date): [Date, Date] => {
    if (props.type === 'datetime') {
        return [parseDatetime(from), parseDatetime(to)];
    } else if (props.type === 'date') {
        return [parseDate(from), parseDatetime(to)];
    } else if (props.type === 'time') {
        return [parseTime(from), parseTime(to)];
    } else {
        return [null, null];
    }
};
const formatValue = (obj: string | Date): string => {
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
const pickerType = computed<IDatePickerType>(() => {
    if (props.type === 'datetime') {
        return 'datetimerange';
    } else if (props.type === 'date') {
        return 'daterange';
    } else if (props.type === 'month') {
        return 'monthrange';
    }
    return 'datetimerange';
});
const pickerFormat = computed<string>(() => {
    if (props.type === 'datetime') {
        return DATE_TIME_PATTEN;
    } else if (props.type === 'date') {
        return DATE_PATTEN;
    } else if (props.type === 'time') {
        return TIME_PATTEN;
    }
    return DATE_TIME_PATTEN;
});
const fromValue = computed<string>(() => props.from);
const toValue = computed<string>(() => props.to);
const modelValue = ref<[Date, Date]>(getValue(props.from, props.to));
const onChange = (values: [Date, Date]) => {
    if (isNull(values)) {
        emits('update:from', '');
        emits('update:to', '');
    } else {
        emits('update:from', formatValue(values[0]));
        emits('update:to', formatValue(values[1]));
    }
};

watch([fromValue, toValue], ([newFromValue, newToValue]) => {
    modelValue.value = getValue(newFromValue, newToValue);
});
</script>
