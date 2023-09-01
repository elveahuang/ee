import axios from 'axios';
import { log } from '@/utils';

export const setupHttp = async () => {
    log('Axios init...');
    axios.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    window.axios = axios;
    log('Axios initialized.');
};
