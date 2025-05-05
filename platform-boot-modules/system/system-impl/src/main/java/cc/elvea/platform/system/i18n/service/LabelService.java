package cc.elvea.platform.system.i18n.service;

import cc.elvea.platform.system.i18n.enums.LabelTypeEnum;

/**
 * @author elvea
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
