import { isEqual } from 'lodash-es';

const env = {
    mode: import.meta.env.MODE || 'development',
    production: isEqual(import.meta.env.MODE, 'production'),
};

export default env;
