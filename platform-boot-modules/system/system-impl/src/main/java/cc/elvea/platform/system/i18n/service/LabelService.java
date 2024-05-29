package cc.elvea.platform.system.i18n.service;

import cc.elvea.platform.system.i18n.enums.LabelTypeEnum;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface LabelService {

    /**
     * 多语言翻译
     */
    void translate();

    /**
     * 多语言翻译
     */
    void generate(LabelTypeEnum labelType) throws Exception;

}
