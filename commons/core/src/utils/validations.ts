import validator from 'validator';

export const isValidEmail = (value: string): boolean => {
    return validator.isEmail(value);
};

export const isValidUsername = (value: string): boolean => {
    return !validator.isEmpty(value) && /^[a-zA-Z0-9_\u4e00-\u9fa5]{3,30}$/.test(value);
};

export const isValidPassword = (value: string): boolean => {
    return !validator.isEmpty(value) && /^[A-Za-z0-9]{6,30}$/.test(value);
};
