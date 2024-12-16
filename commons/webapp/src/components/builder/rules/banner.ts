import { Rule } from '@form-create/element-ui';

export const label: string = '宣传栏';
export const name: string = 'x-banner';
export const banner = {
    icon: 'icon-row',
    label,
    name,
    rule(): Rule {
        return {
            type: 'x-banner',
            props: {
                text: 'Hello World!',
            },
        };
    },
    props(): Rule[] {
        return [{ type: 'input', field: 'text', title: '文本' }];
    },
};
