import videoJs from 'video.js';
import { IPlayerOptions, IPluginOptions, Sniffer } from 'xgplayer';
import Thumbnail from 'xgplayer/es/plugins/common/thumbnail';
import CssFullScreen from 'xgplayer/es/plugins/cssFullScreen';
import Enter from 'xgplayer/es/plugins/enter';
import ErrorPlugin from 'xgplayer/es/plugins/error';
import Fullscreen from 'xgplayer/es/plugins/fullscreen';
import Keyboard from 'xgplayer/es/plugins/keyboard';
import Loading from 'xgplayer/es/plugins/loading';
import XGLogger from 'xgplayer/es/plugins/logger';
import MobilePlugin from 'xgplayer/es/plugins/mobile';
import PCPlugin from 'xgplayer/es/plugins/pc';
import PIP from 'xgplayer/es/plugins/pip';
import Play from 'xgplayer/es/plugins/play';
import Poster from 'xgplayer/es/plugins/poster';
import Progress from 'xgplayer/es/plugins/progress';
import MiniProgress from 'xgplayer/es/plugins/progress/miniProgress';
import Prompt from 'xgplayer/es/plugins/prompt';
import Replay from 'xgplayer/es/plugins/replay';
import Start from 'xgplayer/es/plugins/start';
import Stats from 'xgplayer/es/plugins/stats';
import TestSpeed from 'xgplayer/es/plugins/testspeed';
import Time from 'xgplayer/es/plugins/time';
import Volume from 'xgplayer/es/plugins/volume';

/**
 * VPlayer
 * https://videojs.com/
 */
export interface VPlayerSource {
    src?: string;
    type?: string;
}

export interface VPlayerOptions {
    debug?: boolean;
    autoplay?: boolean;
    controls?: boolean;
    src?: string;
    sources?: VPlayerSource[];
    youtube?: {
        enabled: boolean;
    };
    disablePictureInPicture?: boolean;
}

export const VPlayerButton = videoJs.getComponent('Button');

export class VPlayerFullScreenBtn extends VPlayerButton {
    isFullscreen: boolean = false;

    constructor(player: any, options: object) {
        super(player, options);
        this.setIcon('fullscreen-enter');
    }

    buildCSSClass(): string {
        return `vjs-fullscreen-control ${super.buildCSSClass()}`;
    }

    handleClick(): void {
        this.isFullscreen = !this.isFullscreen;
        if (this.isFullscreen) {
            this.setIcon('fullscreen-exit');
            this.player_.el_.classList.add('app-video-player-fullscreen');
        } else {
            this.setIcon('fullscreen-enter');
            this.player_.el_.classList.remove('app-video-player-fullscreen');
        }
    }

    toggleFullScreen() {
        if (this.isFullscreen) {
            // document.exitFullscreen().then();
        } else {
            this.player_.el_.requestFullscreen().then((): void => {
                this.isFullscreen = !this.isFullscreen;
            });
        }
    }
}

export const getVPlayerOptions = (options: VPlayerOptions = {}, isEnableYoutube?: boolean): VPlayerOptions => {
    return {
        ...({
            responsive: true,
            autoplay: true,
            controls: true,
            enableSmoothSeeking: true,
            disablePictureInPicture: true,
            src: options.src,
            sources: [{ src: options.src, type: isEnableYoutube ? 'video/youtube' : 'video/mp4' }],
        } as VPlayerOptions),
        ...options,
    } as VPlayerOptions;
};

/**
 * XPlayer
 * https://h5player.bytedance.com/
 */
export class XPlayerPreset {
    plugins: any[];

    constructor(options: IPluginOptions, config: IPlayerOptions) {
        const icons: any[] = [Play, Progress, Time, Volume, PIP];
        const layers: any[] = [Replay, Poster, Start, Loading, Enter, ErrorPlugin, Prompt, Thumbnail, MiniProgress];
        this.plugins = [XGLogger, Stats].concat(icons).concat(layers);

        const simulateMode: boolean = config && config.isMobileSimulateMode === 'mobile';
        const mode: 'pc' | 'mobile' = simulateMode ? 'mobile' : Sniffer.device;
        switch (mode) {
            case 'pc':
                this.plugins.push(Keyboard, CssFullScreen, Fullscreen, TestSpeed, PCPlugin);
                break;
            case 'mobile':
                this.plugins.push(CssFullScreen, MobilePlugin);
                break;
            default:
                this.plugins.push(Keyboard, CssFullScreen, Fullscreen, TestSpeed, PCPlugin);
        }

        if (Sniffer.os.isIpad) {
            this.plugins.push(PCPlugin);
        }
    }
}

export interface XPlayerOptions extends IPlayerOptions {
    debug?: boolean;
    src?: string;
}

export const getXPlayerOptions = (options: XPlayerOptions): XPlayerOptions => {
    return {
        ...{
            debug: true,
            fluid: true,
            autoplay: true,
            playsinline: true,
            presets: [XPlayerPreset],
        },
        ...options,
    } as XPlayerOptions;
};
