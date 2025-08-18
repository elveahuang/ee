package cc.wdev.platform.system.core.web;

import cc.wdev.platform.commons.constants.DateTimeConstants;
import cc.wdev.platform.commons.domain.R;
import cc.wdev.platform.commons.utils.DateUtils;
import cc.wdev.platform.commons.utils.JacksonUtils;
import cc.wdev.platform.system.BaseWebTests;
import cc.wdev.platform.system.core.domain.dto.UserRegisterDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static cc.wdev.platform.system.commons.constants.SystemMappingConstants.API_V1__USER__REGISTER;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author elvea
 */
public class UserControllerTests extends BaseWebTests {

    @Test
    public void registerTests() throws Exception {
        Date now = new Date();
        MockHttpServletResponse resp = mockMvc.perform(MockMvcRequestBuilders.post(API_V1__USER__REGISTER)
            .param("defaultDate", DateUtils.format(now, DateTimeConstants.Pattern.DATE_TIME))
            .param("date", DateUtils.format(now, DateTimeConstants.Pattern.DATE))
        ).andExpect(status().isOk()).andReturn().getResponse();
        System.out.println(resp.getContentAsString());
    }

    @Test
    public void testRegister() throws Exception {
        UserRegisterDto dto = UserRegisterDto
            .builder()
            .username("")
            .password("")
            .build();

        EntityExchangeResult<?> result = client.post().uri(API_V1__USER__REGISTER)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(JacksonUtils.toJson(dto))
            .exchange()
            .expectStatus().isOk()
            .expectBody(R.class)
            .returnResult();
        Assertions.assertNotNull(result.getResponseBody());
    }

}
