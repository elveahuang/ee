<template>
    <div class="block h-full w-full">
        <div class="app-text-bold block h-full w-full">
            <el-image :src="image" />
        </div>
        <div class="app-text-bold block h-full w-full py-4 text-center">
            <div v-html="text" />
        </div>
        <div class="app-text-bold block h-full w-full py-4 text-center">
            <div v-html="title" />
        </div>
    </div>
</template>

<script lang="ts">
import { bannerBlue, bannerGreen, bannerRed } from '@commons/core/constants/images.ts';
import { log } from '@commons/core/utils';
import { computed, defineComponent } from 'vue';

interface Props {
    text: string;
    color: string;
}

export default defineComponent({
    name: 'XNavbar',
    props: {
        text: { type: String, required: false },
        color: { type: String, required: false },
    },
    setup(props: Props) {
        const title = computed(() => props.text);
        const image = computed(() => {
            let image: any;
            switch (props.color) {
                case 'red':
                    image = bannerRed;
                    break;
                case 'blue':
                    image = bannerBlue;
                    break;
                default:
                    image = bannerGreen;
                    break;
            }
            return image;
        });
        return {
            title,
            image,
        };
    },
    onMounted() {
        log('Builder Component <<Navbar>> mounted.');
    },
});
</script>
