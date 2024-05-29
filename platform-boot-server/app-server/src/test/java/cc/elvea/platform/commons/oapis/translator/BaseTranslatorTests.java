package cc.elvea.platform.commons.oapis.translator;

import cc.elvea.platform.BaseTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 * @since 24.1.0
 */
public class BaseTranslatorTests extends BaseTests {

    @Autowired(required = false)
    TranslatorManager translatorFactory;

    @Test
    public void test() throws Exception {
        String text = "Add a survey";
        String zh_text = "添加调查问卷";
        System.out.println(translatorFactory.getTranslator().translate("en", "yue", text));
        Thread.sleep(1000);
        System.out.println(translatorFactory.getTranslator().translate("en", "cht", text));
        Thread.sleep(1000);
        System.out.println(translatorFactory.getTranslator().translate("en", "fra", text));
        Thread.sleep(1000);
        System.out.println(translatorFactory.getTranslator().translate("en", "ar", text));
        Thread.sleep(1000);
        System.out.println(translatorFactory.getTranslator().translate("en", "th", text));
        Thread.sleep(1000);
        System.out.println(translatorFactory.getTranslator().translate("en", "ru", text));
        Thread.sleep(1000);
        System.out.println(translatorFactory.getTranslator().translate("en", "vi", text));
        Thread.sleep(1000);
        System.out.println(translatorFactory.getTranslator().translate("en", "id", text));
        Thread.sleep(1000);
        System.out.println(translatorFactory.getTranslator().translate("zh", "fil", zh_text));
        Thread.sleep(1000);
        System.out.println(translatorFactory.getTranslator().translate("zh", "my", zh_text));
        Thread.sleep(1000);
        System.out.println(translatorFactory.getTranslator().translate("zh", "ur", zh_text));
    }

}
