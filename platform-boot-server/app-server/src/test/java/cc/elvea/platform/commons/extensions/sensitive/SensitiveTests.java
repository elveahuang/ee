package cc.elvea.platform.commons.extensions.sensitive;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.commons.annotations.Sensitive;
import cc.elvea.platform.commons.utils.JacksonUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.Serializable;

/**
 * @author elvea
 */
@Slf4j
public class SensitiveTests extends BaseTests {

    @Test
    public void base() throws Exception {
        User user = User.builder().id(1L).idCard("445121200001011234").mobileNumber("13800138000").build();
        String json = JacksonUtils.toJson(user);
        Assertions.assertNotNull(json);
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class User implements Serializable {
        private Long id;
        @Sensitive(strategy = SensitiveStrategy.ID_CARD)
        private String idCard;
        @Sensitive(strategy = SensitiveStrategy.MOBILE)
        private String mobileNumber;
    }

}
