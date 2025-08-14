package top.baihu.platform.commons.oapis.translator;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.baihu.platform.BaseTests;
import top.baihu.platform.commons.enums.LangTypeEnum;

/**
 * @author elvea
 */
@Slf4j
public class TranslatorTests extends BaseTests {

    @Autowired
    private TranslatorFactory translatorFactory;

    public void base(Translator translator) throws Exception {
        String text = "你好";
        String enText = translator.translate(LangTypeEnum.ZH_CN, LangTypeEnum.EN, text);
        String jpText = translator.translate(LangTypeEnum.ZH_CN, LangTypeEnum.JA, text);
        Assertions.assertNotNull(enText);
        Assertions.assertNotNull(jpText);
    }

    @Test
    public void base() throws Exception {
        this.base(this.translatorFactory.getAliyunTranslator());
    }

}
