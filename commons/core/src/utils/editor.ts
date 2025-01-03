import { StarterKit } from '@tiptap/starter-kit';
import { AiEditorOptions } from 'aieditor';
import { QuillOptions } from 'quill/core/quill';

/**
 * ========================================================================================================================
 * AiEditor
 * https://aieditor.dev/
 * ========================================================================================================================
 */

export const defaultToolbarKeys: string[] = [
    'undo',
    'redo',
    'brush',
    'eraser',
    '|',
    'heading',
    'font-family',
    'font-size',
    '|',
    'bold',
    'italic',
    'underline',
    'strike',
    'link',
    'code',
    'subscript',
    'superscript',
    'hr',
    'todo',
    'emoji',
    '|',
    'highlight',
    'font-color',
    '|',
    'align',
    'line-height',
    '|',
    'bullet-list',
    'ordered-list',
    'indent-decrease',
    'indent-increase',
    'break',
    '|',
    'quote',
    'code-block',
    'table',
];

export const getEditorToolbarKeys = (): string[] => {
    return defaultToolbarKeys;
};

export const defaultEditorOptions: AiEditorOptions = {
    element: '',
    content: '',
    toolbarKeys: getEditorToolbarKeys(),
};

export const getEditorOptions = (options: AiEditorOptions): AiEditorOptions => {
    return { ...defaultEditorOptions, ...options };
};

/**
 * ========================================================================================================================
 * Quill
 * https://quilljs.com/
 * ========================================================================================================================
 */

export const defaultQuillEditorOptions: QuillOptions = {
    modules: {
        toolbar: true,
    },
    theme: 'snow',
};

export const getQuillEditorOptions = (options: QuillOptions): QuillOptions => {
    return { ...defaultQuillEditorOptions, ...options };
};

/**
 * ========================================================================================================================
 * TipTap
 * https://tiptap.dev/
 * ========================================================================================================================
 */

declare type TipTapEditorOptions = {};

export { TipTapEditorOptions };

export const defaultTipTapEditorOptions: TipTapEditorOptions = {
    content: '',
    extensions: [StarterKit],
};

export const getTipTapEditorOptions = (options: TipTapEditorOptions = {}): TipTapEditorOptions => {
    return { ...defaultTipTapEditorOptions, ...options };
};
