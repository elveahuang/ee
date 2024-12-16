import { v4 } from 'uuid';

const uuid = (): string => {
    return v4();
};

export { uuid };
