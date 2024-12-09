import { store } from '@commons/core/store';
import { defineStore } from 'pinia';

export interface SocketState {
    connected: boolean;
}

export const useSocketStore = defineStore('socket', {
    persist: false,
    state: (): SocketState => ({
        connected: false,
    }),
    actions: {
        async initializeWebSocket(): Promise<void> {},
    },
});

export const useSocketStoreExternal = () => {
    return useSocketStore(store);
};
