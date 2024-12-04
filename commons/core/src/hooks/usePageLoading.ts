import { useAppStoreExternal } from '@commons/core/store';

export const usePageLoading = (): {
    pageLoadStart: () => void;
    pageLoadDone: () => void;
} => {
    return {
        pageLoadStart: (): void => {
            const { setLoading } = useAppStoreExternal();
            setLoading(true);
        },
        pageLoadDone: (): void => {
            const { setLoading } = useAppStoreExternal();
            setLoading(false);
        },
    };
};
