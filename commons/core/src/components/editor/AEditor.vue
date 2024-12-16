<template>
    <div class="editor">
        <div ref="editorRef" class="ai-editor" />
    </div>
</template>

<script setup lang="ts">
import { useAppStore } from '@commons/core/store';
import { log } from '@commons/core/utils';
import { getEditorOptions } from '@commons/core/utils/editor';
import { Locale } from '@commons/core/utils/locale';
import { AiEditor, AiEditorOptions } from 'aieditor';
import 'aieditor/dist/style.css';
import { isEqual } from 'radash';
import { computed, onBeforeUnmount, onMounted, ref, shallowRef, watch } from 'vue';

const emits = defineEmits(['update:value']);
const props = defineProps({
    options: {
        type: Object,
        default() {
            return {};
        },
    },
    value: {
        type: String,
        default: '',
    },
});
const { locale, dark } = useAppStore();
const editorRef = ref();
const editor = shallowRef<AiEditor>();
const content = computed<string>(() => props.value);
const theme = computed<string>(() => (dark ? 'dark' : 'light'));
const lang = computed<string>(() => (locale === Locale.ZH_CN ? 'zh' : 'en'));
const initEditor = async (options: AiEditorOptions): Promise<void> => {
    const defaultOptions = {
        element: editorRef.value as Element,
        content: content.value,
        theme: theme.value,
        lang: lang.value,
        onChange: (aiEditor: AiEditor) => {
            emits('update:value', aiEditor.getHtml());
        },
    } as AiEditorOptions;
    editor.value = new AiEditor(getEditorOptions({ ...defaultOptions, ...options }));
};
const destroyEditor = async (): Promise<void> => {
    if (editor.value) {
        editor.value.destroy();
    }
};

watch(content, (value: string): void => {
    if (editor.value && !isEqual(editor.value.getHtml(), value)) {
        editor.value.clear();
        editor.value.focus().insert(value);
    }
});

onMounted(async (): Promise<void> => {
    log('Component <<AiEditor>> mounted.');
    await initEditor(props.options as AiEditorOptions).then();
});

onBeforeUnmount(async (): Promise<void> => {
    log('Component <<AiEditor>> onBeforeUnmount.');
    await destroyEditor().then();
});
</script>

<style lang="scss" scoped>
.editor {
    @apply w-full min-w-full;
}
</style>
