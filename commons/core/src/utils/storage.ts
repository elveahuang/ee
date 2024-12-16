import { isEmpty } from 'radash';

export const ACCESS_TOKEN_KEY: string = 'ACCESS_TOKEN';

export const REFRESH_TOKEN_KEY: string = 'REFRESH_TOKEN';

export const getItem = (key: string): string => {
    return localStorage.getItem(key) ?? '';
};

export const setItem = (key: string, val: string): void => {
    if (!isEmpty(val)) {
        localStorage.setItem(key, val);
    } else {
        removeItem(key);
    }
};

export const removeItem = (key: string): void => {
    localStorage.removeItem(key);
};

export const getAccessToken = (): string => {
    return getItem(ACCESS_TOKEN_KEY) ?? '';
};

export const setAccessToken = (val: string): void => {
    setItem(ACCESS_TOKEN_KEY, val);
};

export const removeAccessToken = (): void => {
    removeItem(ACCESS_TOKEN_KEY);
};

export const getRefreshToken = (): string => {
    return getItem(REFRESH_TOKEN_KEY) ?? '';
};

export const setRefreshToken = (val: string): void => {
    setItem(REFRESH_TOKEN_KEY, val);
};

export const removeRefreshToken = (): void => {
    removeItem(REFRESH_TOKEN_KEY);
};

export const clear = () => {
    localStorage.clear();
};

export const storage = {
    getItem,
    setItem,
    removeItem,
    getAccessToken,
    setAccessToken,
    removeAccessToken,
    getRefreshToken,
    setRefreshToken,
    removeRefreshToken,
    clear,
};

export default storage;
