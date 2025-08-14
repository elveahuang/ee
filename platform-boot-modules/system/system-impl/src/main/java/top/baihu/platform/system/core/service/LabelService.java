package top.baihu.platform.system.core.service;

import top.baihu.platform.system.commons.enums.LabelTypeEnum;

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
