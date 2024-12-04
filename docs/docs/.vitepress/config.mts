import { defineConfig } from 'vitepress';

export default defineConfig({
    title: 'docs',
    description: 'A VitePress Site',
    outDir: 'dist',
    themeConfig: {
        nav: [
            { text: 'Home', link: '/' },
            { text: 'Reference', link: '/reference/', activeMatch: '^/reference/' },
            { text: 'Manuel', link: '/manual/', activeMatch: '^/manual/' },
        ],
        sidebar: {
            '/reference/': [
                {
                    text: 'Reference',
                    items: [{ text: 'Instruction', link: '/reference/' }],
                },
                {
                    text: 'Guide',
                    items: [{ text: '多语言文本', link: '/reference/guide/label' }],
                },
            ],
            '/manual/': [
                {
                    text: 'Manuel',
                    items: [{ text: 'Instruction', link: '/manual/' }],
                },
            ],
        },
    },
});
