import { Rule } from '@form-create/element-ui';
import { localeProps } from '../utils';

export const label: string = '间隔';
export const name: string = 'div';
export const space = {
    icon: 'icon-space',
    label,
    name,
    rule(): Rule {
        return {
            type: name,
            wrap: {
                show: false,
            },
            native: false,
            style: {
                width: '100%',
                height: '20px',
            },
            children: [],
        };
    },
    props(_, { t }): Rule[] {
        return [
            {
                type: 'object',
                field: 'formCreateStyle',
                native: true,
                props: {
                    rule: localeProps(t, name + '.props', [
                        {
                            type: 'input',
                            field: 'height',
                            title: 'height',
                        },
                    ]),
                },
            },
        ];
    },
};
