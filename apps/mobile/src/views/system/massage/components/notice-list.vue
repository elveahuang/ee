<template>
    <template v-for="item in props.items" :key="item.id">
        <van-cell @click="gotoDetailPage(item.id)">
            <template #title>
                <div v-if="item.readInd === true" class="container">
                    <p style="color: #6a6a6a">
                        {{ item.content }}
                    </p>
                    <p class="indicator" style="color: green">已读</p>
                </div>
                <div v-else class="container">
                    <p style="color: #000">
                        {{ item.content }}
                    </p>
                    <p class="indicator" style="color: red">未读</p>
                </div>
            </template>
        </van-cell>
    </template>
</template>

<script setup lang="ts">
import { router } from '@commons/core/router';
import { Notice } from '@commons/core/types/notice.ts';

const props = defineProps({ items: Array<Notice> });

const gotoDetailPage = (id: number) => {
    router.push({ path: '/notifications/details', query: { id: id } });
};
</script>

<style scoped>
.container {
    display: flex;
    align-items: center;
}

.indicator {
    margin-left: auto;
}
</style>
