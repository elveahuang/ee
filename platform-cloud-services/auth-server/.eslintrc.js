const { defineConfig } = require('eslint-define-config');

module.exports = defineConfig({
    root: true,
    env: {
        node: true,
        browser: true,
        jquery: true,
    },
    parserOptions: {
        ecmaVersion: 'latest',
        sourceType: 'module',
    },
    plugins: ['html', 'prettier'],
    extends: ['eslint:recommended', 'prettier'],
    rules: {},
});
