import { Rule } from '@form-create/element-ui';
import { localeProps } from '../utils';

export const label: string = '分割线';
export const name: string = 'el-divider';
export const divider = {
    icon: 'icon-divider',
    label,
    name,
    rule(): Rule {
        return {
            type: name,
            props: {},
            wrap: { show: false },
            native: false,
            children: [''],
        };
    },
    props(_, { t }): Rule[] {
        return localeProps(t, name + '.props', [
            {
                type: 'input',
                field: 'formCreateChild',
                title: '设置分割线文案',
            },
            {
                type: 'select',
                field: 'contentPosition',
                title: '设置分割线文案的位置',
                options: [
                    { label: 'left', value: 'left' },
                    { label: 'right', value: 'right' },
                    {
                        label: 'center',
                        value: 'center',
                    },
                ],
            },
        ]);
    },
};
