import { env } from '@commons/core/env';
import { Config, DotLottie } from '@lottiefiles/dotlottie-web';
import { AnimationConfigWithData, AnimationConfigWithPath, RendererType } from 'lottie-web';

export type LottieOptions<T extends RendererType = 'svg'> = AnimationConfigWithPath<T> | AnimationConfigWithData<T>;

export type DotLottieOptions = Config;

export const setupDotLottie = async (): Promise<void> => {
    const path: string = env.base + 'wasm/dotlottie-player.wasm';
    console.log(path);
    DotLottie.setWasmUrl(path);
};
