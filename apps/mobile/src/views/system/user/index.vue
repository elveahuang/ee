<template>
    <div>
        <Icon icon="ep:add-location" width="36px" height="36px" />
        <div class="text-center">
            {{ username }}
        </div>
    </div>
</template>

<script setup lang="ts">
import { useUserStore } from '@commons/core/store';
import { Icon } from '@iconify/vue';
import { computed } from 'vue';

const userStore = useUserStore();
const username = computed(() => userStore.user?.username || '');
</script>
