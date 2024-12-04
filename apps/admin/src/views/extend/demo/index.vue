<template>
    <div class="p-6">
        <app-divider />
        <el-space>
            <el-button link @click="router.push('/demo/editor/')">Editor</el-button>
            <el-button link @click="router.push('/demo/player/')">Player</el-button>
            <el-button link @click="router.push('/demo/builder/')">Builder</el-button>
            <el-button link @click="router.push('/demo/media/')">Media</el-button>
            <el-button link @click="router.push('/demo/magic-builder/')">Magic Builder</el-button>
            <el-button link @click="router.push('/demo/banner/')">Banner</el-button>
        </el-space>
        <app-divider />
    </div>
</template>

<script setup lang="ts">
import { log } from '@commons/core/utils';
import { AppDivider } from '@commons/webapp/components';
import { onMounted } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();

onMounted(async () => {
    log('Page <<Demo>> mounted.');
});
</script>
