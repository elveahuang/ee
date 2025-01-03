<template>
    <a-select
        v-model:value="relation.ids"
        show-search
        :mode="mode[`${props.multipleInd}`]"
        :options="options"
        :filter-option="filterOption"
        @change="onChange"
    />
</template>

<script setup lang="ts">
import { dictItemEntitySearchApi } from '@commons/core/api/admin/dict.ts';
import { R } from '@commons/core/types';
import { DictEntity, DictItemEntity, DictRelationVo } from '@commons/core/types/dict.ts';
import { log } from '@commons/core/utils';
import { onMounted } from 'vue';

const mode = {
    true: 'multiple', // 多选
    false: 'combobox', // 单选
};

const emits = defineEmits(['update:relation']);
const props = defineProps({
    /**
     * 字典code
     */
    code: {
        type: String,
        required: true,
    },
    /**
     * 字典关联vo
     */
    relation: {
        type: Object,
        required: true,
        default: {
            targetId: 0 as number | string,
            targetType: 'UNSPECIFIED' as string,
            dictId: 0 as number | string,
            ids: [] as Array<number | string>,
            items: [] as Array<DictItemEntity>,
        },
    },
    multipleInd: {
        type: Boolean,
        required: false,
        default: false,
    },
});
// 关联vo
const relation = reactive<DictRelationVo>(props.relation);
const dictType = reactive<DictEntity>(new DictEntity());
// 选项
const options = ref<Array<{ label: string; value: string }>>([]);

// 搜索筛选
const filterOption = (input: string, option: any) => {
    return option.label.toLowerCase().indexOf(input.toLowerCase()) >= 0;
};

// 选项变化回调方法
const onChange = async (value: string): Promise<void> => {
    if (!props.multipleInd) relation.ids = [value];
    emits('update:relation', { ...relation });
};

onMounted(async () => {
    log('Component <<DictSelect>> mounted.');

    await dictItemEntitySearchApi({ code: props.code }).then((resp: R<DictEntity>) => {
        relation.dictId = resp.data?.id;
        relation.targetType = resp.data?.code;
        resp.data && Object.assign(dictType, { ...resp.data });
        options.value = resp.data?.children?.map((dictItem: DictItemEntity) => ({ label: dictItem.title, value: String(dictItem.id) }));
        emits('update:relation', { ...relation });
    });
});
</script>
