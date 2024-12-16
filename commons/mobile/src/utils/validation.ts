import { checkEmailApi, checkUsernameApi } from '@commons/core/api/user.ts';
import { R } from '@commons/core/types';
import { isValidEmail, isValidPassword, isValidUsername } from '@commons/core/utils/validations';
import { FieldRuleValidator } from 'vant';

export const PasswordValidator: FieldRuleValidator = async (value: string): Promise<boolean> => {
    return Promise.resolve(isValidPassword(value));
};

export const EmailValidator: FieldRuleValidator = async (value: string): Promise<boolean> => {
    if (isValidEmail(value)) {
        const result: R<boolean> = await checkEmailApi({ email: value, id: 0 });
        if (result.code == '200' && result.data === true) {
            return Promise.resolve(true);
        }
    }
    return Promise.resolve(false);
};

export const UsernameValidator: FieldRuleValidator = async (value: string): Promise<boolean> => {
    if (isValidUsername(value)) {
        const result: R<boolean> = await checkUsernameApi({ username: value, id: 0 });
        if (result.code == '200' && result.data === true) {
            return Promise.resolve(true);
        }
    }
    return Promise.resolve(false);
};
