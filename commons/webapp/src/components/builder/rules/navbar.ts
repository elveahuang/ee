import { Rule } from '@form-create/element-ui';

export const label: string = '导航栏';
export const name: string = 'x-navbar';
export const navbar = {
    icon: 'icon-checkbox',
    label,
    name,
    // 不显示拖拽按钮，固定导航栏
    dragBtn: false,
    rule(): Rule {
        return {
            type: name,
            props: {
                text: 'Hello World!',
                color: 'green',
            },
        };
    },
    props(): Rule[] {
        return [
            {
                type: 'input',
                field: 'text',
                title: '文本',
            },
            {
                type: 'select',
                field: 'color',
                title: '颜色',
                options: [
                    { label: '绿色', value: 'green' },
                    { label: '蓝色', value: 'blue' },
                    { label: '红色', value: 'red' },
                ],
            },
        ];
    },
};
