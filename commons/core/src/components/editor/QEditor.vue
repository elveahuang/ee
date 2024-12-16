<template>
    <div class="editor">
        <div ref="editorRef" class="quill-editor" />
    </div>
</template>

<script setup lang="ts">
import { useAppStore } from '@commons/core/store';
import { log } from '@commons/core/utils';
import { getQuillEditorOptions } from '@commons/core/utils/editor';
import { Locale } from '@commons/core/utils/locale';
import Quill from 'quill';
import { QuillOptions } from 'quill/core/quill';
import 'quill/dist/quill.core.css';
import 'quill/dist/quill.snow.css';
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
const { locale } = useAppStore();
const appLocale = computed<Locale>(() => locale);
const editorRef = ref();
const content = computed<string>(() => props.value);
const editor = shallowRef<Quill>();
const initEditor = async (options: QuillOptions): Promise<void> => {
    const defaultOptions = {} as QuillOptions;
    editor.value = new Quill(editorRef.value, getQuillEditorOptions({ ...defaultOptions, ...options }));
};
const destroyEditor = async (): Promise<void> => {
    if (editor.value) {
        //
    }
};

watch(content, (value: string): void => {
    if (editor.value && !isEqual(editor.value.getSemanticHTML(), value)) {
        editor.value.getText();
        editor.value.focus();
        editor.value.setText(value);
    }
});

watch(appLocale, (value: Locale): void => {
    //
});

onMounted(async (): Promise<void> => {
    log('Component <<QuillEditor>> mounted.');
    await initEditor(props.options as QuillOptions).then();
});

onBeforeUnmount(async (): Promise<void> => {
    log('Component <<QuillEditor>> onBeforeUnmount.');
    await destroyEditor().then();
});
</script>

<style lang="scss" scoped>
.editor {
    @apply w-full min-w-full;
}
</style>
