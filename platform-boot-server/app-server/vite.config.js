import { defineConfig, loadEnv, splitVendorChunkPlugin } from 'vite';
import jsConfigPaths from 'vite-jsconfig-paths';
import { resolve } from 'path';

export default defineConfig(({ command, mode }) => {
    const env = loadEnv(mode, process.cwd());
    console.log(`command - ${command}. mode - ${mode}.`);
    console.log(`-------------------------- env start --------------------------`);
    console.log(env);
    console.log(`-------------------------- env end --------------------------`);
    return {
        resolve: {
            alias: {
                '~bootstrap': 'bootstrap',
                '~bootstrap-icons': 'bootstrap-icons',
            },
        },
        build: {
            outDir: resolve(__dirname, './src/main/resources/static'),
            manifest: false,
            rollupOptions: {
                input: {
                    main: resolve(__dirname, './src/main/webapp/main.js'),
                },
                output: {
                    entryFileNames: `assets/[name].js`,
                    chunkFileNames: `assets/[name].js`,
                    assetFileNames: `assets/[name].[ext]`,
                },
            },
        },
        plugins: [jsConfigPaths(), splitVendorChunkPlugin()],
        esbuild: {
            target: 'ES2015',
        },
    };
});
