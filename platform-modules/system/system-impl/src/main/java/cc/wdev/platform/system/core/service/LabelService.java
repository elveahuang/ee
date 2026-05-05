package cc.wdev.platform.system.core.service;

import cc.wdev.platform.system.commons.enums.LabelTypeEnum;

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
