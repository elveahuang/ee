package cc.elvea.platform.commons.oapis.translator;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.commons.enums.LangTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
public class TranslatorTests extends BaseTests {

    @Autowired
    private TranslatorManager translatorManager;

    public void base(Translator translator) throws Exception {
        String text = "你好";
        String enText = translator.translate(LangTypeEnum.ZH_CN, LangTypeEnum.EN, text);
        String jpText = translator.translate(LangTypeEnum.ZH_CN, LangTypeEnum.JA, text);
        Assertions.assertNotNull(enText);
        Assertions.assertNotNull(jpText);
    }

    @Test
    public void base() throws Exception {
        this.base(this.translatorManager.getAliyunTranslator());
        this.base(this.translatorManager.getBaiduTranslator());
    }

}
