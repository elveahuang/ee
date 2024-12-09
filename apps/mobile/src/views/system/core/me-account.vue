<template>
    <van-nav-bar :title="t('app.meMessage')" left-arrow @click-left="goBack()" />

    <div class="divider" />

    <van-cell-group inset>
        <van-cell :title="t('app.photo')" to="/setting" is-link>
            <van-image class="align-middle" round width="1.5rem" height="1.5rem" src="https://fastly.jsdelivr.net/npm/@vant/assets/cat.jpeg" />
        </van-cell>
        <van-cell :title="t('app.adress')" to="/setting" is-link>
            {{ user.adress }}
        </van-cell>
        <van-cell :title="t('app.name')">
            {{ user.username }}
        </van-cell>
        <van-cell :title="t('app.nickname')" to="/setting" is-link>
            {{ user.displayName }}
        </van-cell>
        <van-cell :title="t('app.role')" to="/setting" is-link>
            {{ user.role }}
        </van-cell>
        <van-cell :title="t('app.email')">
            {{ user.email }}
        </van-cell>
        <van-cell :title="t('app.mobile')" to="/setting" is-link>
            {{ user.mobileNumber }}
        </van-cell>
        <van-cell :title="t('app.signature')" to="/setting" is-link>
            {{ user.signature }}
        </van-cell>
    </van-cell-group>
</template>

<script setup lang="ts">
import { useUserStore } from '@commons/core/store';
import { goBack, log } from '@commons/core/utils';
import { onMounted, reactive } from 'vue';
import { useI18n } from 'vue-i18n';

const { t } = useI18n();
const { getUserInfo } = useUserStore();

const user = reactive({
    address: 'https://www.baidu.com',
    email: '2390046201@qq.com',
    role: '黄金会员',
    username: '钱多多',
    displayName: '代码根本敲不完',
    mobileNumber: '17876515985',
    signature: '暂无签名',
});

onMounted(async () => {
    log('Page <<Me-MessagePage>> mounted.');
    getUserInfo().then((result) => {
        log('个人中心获取用户结果是：');
        log(result);
        user.username = result.data.username;
        user.displayName = result.data.displayName;
        user.email = result.data.email;
    });
});
</script>
