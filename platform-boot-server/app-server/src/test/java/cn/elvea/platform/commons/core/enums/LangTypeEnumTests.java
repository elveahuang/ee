package cn.elvea.platform.commons.core.enums;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public class LangTypeEnumTests {

    @Test
    public void base() {
        Assertions.assertEquals(LangTypeEnum.ZH_CN, LangTypeEnum.getLangType("zh"));
        Assertions.assertEquals(LangTypeEnum.ZH_CN, LangTypeEnum.getLangType("zh_CN"));
        Assertions.assertEquals(LangTypeEnum.ZH_TW, LangTypeEnum.getLangType("zh_HK"));
        Assertions.assertEquals(LangTypeEnum.ZH_TW, LangTypeEnum.getLangType("zh_TW"));
        Assertions.assertEquals(LangTypeEnum.ZH_TW, LangTypeEnum.getLangType("zh_mo"));
        Assertions.assertEquals(LangTypeEnum.EN, LangTypeEnum.getLangType("en"));
        Assertions.assertEquals(LangTypeEnum.EN, LangTypeEnum.getLangType("en_us"));
        Assertions.assertEquals(LangTypeEnum.EN, LangTypeEnum.getLangType("en_UK"));
        Assertions.assertEquals(LangTypeEnum.FR, LangTypeEnum.getLangType("fr"));
        Assertions.assertEquals(LangTypeEnum.JA, LangTypeEnum.getLangType("jp"));
    }

}
