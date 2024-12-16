import { useAppStoreExternal } from '@commons/core/store';
import { DarkMode, getDarkValue } from '@commons/core/utils/dark.ts';

export const useDark = (): {
    setDark: (dark: boolean) => Promise<void>;
    setDarkMode: (darkMode: DarkMode) => Promise<void>;
} => {
    return {
        setDark: async (dark: boolean): Promise<void> => {
            const { setDark } = useAppStoreExternal();
            setDark(dark);
        },
        setDarkMode: async (mode: DarkMode): Promise<void> => {
            const { setDarkMode, setDark } = useAppStoreExternal();
            setDarkMode(mode);
            setDark(getDarkValue(mode));
        },
    };
};
