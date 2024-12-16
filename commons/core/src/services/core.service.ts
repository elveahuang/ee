import PlatformService from '@commons/core/services/platform.serviice';
import { log } from '@commons/core/utils';

class CoreService {
    /**
     * 页面初始化
     */
    async initialize(): Promise<void> {
        PlatformService.initialize().then(() => {
            log('PlatformService initialize successfully.');
        });
        return;
    }
}

export default new CoreService();
