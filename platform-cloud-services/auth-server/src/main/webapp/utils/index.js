import env from '@/utils/env';

/**
 * domReady
 */
export const domReady = (callback = () => {}) => {
    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', callback);
    } else {
        callback();
    }
};

/**
 * windowReady
 */
export const windowReady = (callback = () => {}) => {
    if (document.readyState === 'complete') {
        callback();
    } else {
        window.addEventListener('load', callback);
    }
};

/**
 * 输出日志
 */
export function log(log) {
    if (!env.production) {
        console.log(log);
    } else {
        console.log(log);
    }
}

const Index = {};
Index.domReady = domReady;
Index.windowReady = windowReady;
Index.log = log;

export default Index;
