import eslintParser from '@typescript-eslint/parser';
import vuePrettier from '@vue/eslint-config-prettier';
import vueTypescript from '@vue/eslint-config-typescript';
import vuePlugin from 'eslint-plugin-vue';
import globals from 'globals';
import eslintTypescript from 'typescript-eslint';
import vueParser from 'vue-eslint-parser';

/** @type {import("eslint").Config} */
export default [
    ...eslintTypescript.configs.recommended,
    ...vuePlugin.configs['flat/essential'],
    ...vueTypescript(),
    vuePrettier,
    {
        ignores: ['**/node_modules/', '**/dist/'],
    },
    {
        languageOptions: {
            parser: vueParser,
            parserOptions: {
                parser: eslintParser,
                ecmaFeatures: {
                    jsx: true,
                },
            },
            sourceType: 'module',
            ecmaVersion: 'latest',
            globals: {
                ...globals.browser,
                ...globals.es2021,
                ...globals.node,
            },
        },
    },
    {
        rules: {
            'no-console': process.env.NODE_ENV === 'production' ? 'warn' : 'off',
            'no-debugger': process.env.NODE_ENV === 'production' ? 'warn' : 'off',
            '@typescript-eslint/ban-types': 'off',
            '@typescript-eslint/no-explicit-any': 'off',
            '@typescript-eslint/no-unused-vars': 'off',
            '@typescript-eslint/ban-ts-comment': 'off',
            '@typescript-eslint/no-require-imports': 'off',
            '@typescript-eslint/no-var-requires': 'off',
            '@typescript-eslint/no-unsafe-function-type': 'off',
            '@typescript-eslint/no-empty-object-type': 'off',
            'vue/multi-word-component-names': 'off',
            'vue/require-valid-default-prop': 'off',
            'vue/no-mutating-props': 'off',
            'vue/valid-v-for': 'off',
            'no-async-promise-executor': 'off',
        },
    },
];
