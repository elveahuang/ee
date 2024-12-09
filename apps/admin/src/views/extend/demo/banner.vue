<template>
    <div class="home-container">
        <div class="home-module home-nav">
            <div class="home-module-content">
                <div class="home-nav-logo">Logo</div>
                <div class="home-nav-menu">Menu</div>
                <div class="home-nav-extra home-nav-extra-fixed">Extra</div>
            </div>
        </div>
        <div class="home-module home-banner">
            <div class="home-module-content">Banner</div>
        </div>
        <div class="home-module"></div>
    </div>
</template>

<script setup lang="ts">
import { log } from '@commons/core/utils';
import { onMounted } from 'vue';

onMounted(async () => {
    log('Page <<Banner>> mounted.');
});
</script>

<style scoped lang="scss">
.home-container {
    width: 100%;
    position: relative;
    padding-top: 50px;

    .home-module {
        width: 100%;
        background-color: #0bc15f;

        .home-module-content {
            width: 1180px;
            background-color: #00ffff;
            margin: 0 auto 15px;
            position: relative;
        }
    }

    .home-module.home-nav {
        position: absolute;
        left: 0;
        top: 0;
        height: 50px;

        .home-module-content {
            width: 1180px;
            height: 50px;
            margin-bottom: 0;
            display: flex;

            .home-nav-logo {
                width: 200px;
                height: 50px;
                background-color: red;
                flex: none;
            }

            .home-nav-menu {
                width: 200px;
                height: 50px;
                background-color: blue;
                flex: 1;
            }

            .home-nav-extra {
                width: 200px;
                height: 50px;
                flex: none;
                background-color: yellow;
            }

            .home-nav-extra-fixed {
                position: fixed;
                right: 0;
                top: 0;
            }
        }
    }

    .home-module.home-banner {
        width: 100%;
        height: 350px;

        .home-module-content {
            width: 1180px;
            height: 350px;
        }
    }
}
</style>
