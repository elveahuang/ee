<template>
    <van-sticky>
        <van-nav-bar :title="$t('common.label_settings')" left-arrow @click-left="goBack()" />
    </van-sticky>

    <div class="app-divider" />

    <van-cell-group inset>
        <app-dark-mode-cell-picker />
        <app-locale-cell-picker />
        <app-theme-cell-picker />
    </van-cell-group>
</template>

<script setup lang="ts">
import { goBack } from '@commons/core/utils';
import { AppDarkModeCellPicker, AppLocaleCellPicker, AppThemeCellPicker } from '@commons/mobile/components';
</script>
