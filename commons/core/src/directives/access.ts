import { useUserStore } from '@commons/core/store';
import { isEmpty, isString } from 'radash';
import { Directive, DirectiveBinding } from 'vue';

/**
 * 指令 - 用于检查是否拥有指定的权限
 */
function checkAuthority(el: HTMLElement, binding: DirectiveBinding): void {
    const { value } = binding;
    const { user } = useUserStore();
    if (Array.isArray(value)) {
        if (value.length > 0) {
            const hasAuthority: boolean = user?.authorities?.some((authority: string) => {
                return value.includes(authority);
            });
            if (!hasAuthority && el.parentNode) {
                el.parentNode.removeChild(el);
            }
        }
    } else if (isString(value) && !isEmpty(value)) {
        const hasAuthority: boolean = user?.authorities?.includes(value);
        if (!hasAuthority && el.parentNode) {
            el.parentNode.removeChild(el);
        }
    }
}

/**
 * 指令 - 用于检查是否拥有指定的角色
 */
function checkRole(el: HTMLElement, binding: DirectiveBinding): void {
    const { value } = binding;
    const { user } = useUserStore();
    if (Array.isArray(value)) {
        if (value.length > 0) {
            const hasRole: boolean = user?.roles?.some((role: string) => {
                return value.includes(role);
            });
            if (!hasRole && el.parentNode) {
                el.parentNode.removeChild(el);
            }
        }
    } else if (isString(value) && !isEmpty(value)) {
        const hasRole: boolean = user?.roles?.includes(value);
        if (!hasRole && el.parentNode) {
            el.parentNode.removeChild(el);
        }
    }
}

export const hasAuthority: Directive = {
    mounted(el: HTMLElement, binding: DirectiveBinding): void {
        checkAuthority(el, binding);
    },
    updated(el: HTMLElement, binding: DirectiveBinding): void {
        checkAuthority(el, binding);
    },
};

export const hasRole: Directive = {
    mounted(el: HTMLElement, binding: DirectiveBinding): void {
        checkRole(el, binding);
    },
    updated(el: HTMLElement, binding: DirectiveBinding): void {
        checkRole(el, binding);
    },
};
