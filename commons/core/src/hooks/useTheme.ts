import { useAppStoreExternal } from '@commons/core/store';
import { Theme } from '@commons/core/utils/theme.ts';

export const useTheme = (): { setTheme: (theme: Theme) => Promise<void> } => {
    return {
        setTheme: async (theme: Theme): Promise<void> => {
            const { setTheme } = useAppStoreExternal();
            setTheme(theme);
        },
    };
};
