<template>
    <template v-for="item in props.items" :key="item.id">
        <app-divider />
        <van-cell-group inset @click="gotoDetailPage(item.id)">
            <van-cell-group inset>
                <div class="app-text app-text-bold app-text-ellipsis-1 mt-2" v-html="item.title" />
                <div class="app-line" />
                <div class="app-text-muted app-text-ellipsis-2 mb-4" v-html="item.content" />
            </van-cell-group>
        </van-cell-group>
    </template>
    <app-divider />
</template>

<script setup lang="ts">
import { router } from '@commons/core/router';
import { Announcement } from '@commons/core/types/announcement';
import { AppDivider } from '@commons/mobile/components';

const props = defineProps({ items: Array<Announcement> });

const gotoDetailPage = (id: number | string) => {
    router.push({ path: '/announcement/details', query: { id: id } });
};
</script>
