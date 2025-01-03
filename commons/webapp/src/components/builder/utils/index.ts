export const localeProps = (t: any, prefix: string, rules: any[]) => {
    return rules.map((rule) => {
        if (rule.field === 'formCreate$required') {
            rule.title = t('props.required') || rule.title;
        } else if (rule.field && rule.field !== '_optionType') {
            rule.title = t('components.' + prefix + '.' + rule.field) || rule.title;
        }
        return rule;
    });
};
