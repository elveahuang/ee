<template>
    <el-container>
        <el-header>
            <el-button @click="preview()"> 预览</el-button>
        </el-header>
        <el-main>
            <app-form-designer ref="designer" :menu="designerMenu" :config="designerConfig" />
        </el-main>
    </el-container>
    <el-dialog v-model="previewModalOpen" class="app-modal block" title="预览" destroy-on-close>
        <app-form ref="form" v-model:api="api" :rule="rule" :option="options" />
    </el-dialog>
</template>

<script lang="ts">
import { log } from '@commons/core/utils';
import { banner, divider, layout, navbar, space } from '@commons/webapp/components/builder/rules';
import { default as FcDesigner, Menu } from '@form-create/designer';
import { default as FormCreate } from '@form-create/element-ui';
import { defineComponent } from 'vue';
import { useI18n } from 'vue-i18n';

export default defineComponent({
    name: 'HomeBuilder',
    components: {
        AppForm: FormCreate.$form(),
        AppFormDesigner: FcDesigner,
    },
    setup() {
        const { t } = useI18n();
        // 编辑器相关
        const designer = ref();
        const designerConfig = reactive({
            showBaseForm: false,
            showFormConfig: true,
            submitBtn: {
                show: false,
            },
            resetBtn: {
                show: false,
            },
        });
        const designerMenu = ref<Menu[]>([
            {
                name: 'module',
                title: '模块',
                list: [
                    {
                        icon: banner.icon,
                        name: banner.name,
                        label: banner.label,
                    },
                    {
                        icon: navbar.icon,
                        name: navbar.name,
                        label: navbar.label,
                    },
                ],
            },
            {
                name: 'layout',
                title: '布局',
                list: [
                    {
                        icon: layout.icon,
                        name: layout.name,
                        label: layout.label,
                    },
                    {
                        icon: divider.icon,
                        name: divider.name,
                        label: divider.label,
                    },
                    {
                        icon: space.icon,
                        name: space.name,
                        label: space.label,
                    },
                ],
            },
        ]);
        // 表单相关
        const form = ref();
        const rule = ref([]);
        const api = ref({});
        const options = ref({
            submitBtn: {
                show: false,
            },
            resetBtn: {
                show: false,
            },
        });
        // 预览相关
        const previewModalOpen = ref(false);
        const preview = () => {
            previewModalOpen.value = !previewModalOpen.value;

            const designerRule = designer.value.getRule();
            const designerOption = designer.value.getOption();
            const designerJson = designer.value.getJson();
            log(designerRule);
            log(designerOption);
            log(designerJson);
            rule.value = designerRule;
        };

        return {
            t,
            designer,
            form,
            designerConfig,
            designerMenu,
            rule,
            api,
            options,
            previewModalOpen,
            preview,
        };
    },
    mounted() {
        log('Component <<HomeBuilder>> mounted.');
        this.designer.addComponent(layout);
        this.designer.addComponent(banner);
        this.designer.addComponent(navbar);
        this.designer.addComponent(divider);
        this.designer.addComponent(space);
    },
});
</script>
