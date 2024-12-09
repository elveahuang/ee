import { checkUsernameApi } from '@commons/core/api/user.ts';
import { R } from '@commons/core/types';
import { log } from '@commons/core/utils';
import { InternalRuleItem } from 'async-validator';
import { FormRules as Rule } from 'element-plus';
import { isEmpty } from 'radash';

export declare type CheckUsernamePayload = {
    id: number;
    username: string;
};

export const checkUsername = async (rule: Rule, payload: CheckUsernamePayload): Promise<void> => {
    const result: R<boolean> = await checkUsernameApi({ username: payload.username, id: payload.id });
    if (result.code == '200' && result.data === true) {
        return Promise.resolve();
    }
    return Promise.reject();
};

export const checkPassword = (rule: InternalRuleItem, value: string, callback: Function): void => {
    const p: RegExp = /^.*(?=.{6,})(?=.*\d)(?=.*[A-Z])(?=.*[a-z]).*$/;
    if (p.test(value)) {
        callback(new Error(rule.message as string));
    } else {
        callback();
    }
};

export const editorNotEmpty = (rule: InternalRuleItem, value: string, callback: Function): void => {
    log(`editorNotEmpty - value : ${value}`);
    if (isEmpty(value)) {
        callback(new Error(rule.message as string));
    } else {
        callback();
    }
};
