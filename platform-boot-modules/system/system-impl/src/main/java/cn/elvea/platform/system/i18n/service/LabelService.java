package cn.elvea.platform.system.i18n.service;

import cn.elvea.platform.system.i18n.enums.LabelTypeEnum;

/**
 * @author elvea
 * @since 0.0.1
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
