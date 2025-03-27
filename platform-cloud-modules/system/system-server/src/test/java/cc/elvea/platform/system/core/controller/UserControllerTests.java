package cc.elvea.platform.system.core.controller;

import cc.elvea.platform.commons.base.R;
import cc.elvea.platform.commons.constants.DateTimeConstants;
import cc.elvea.platform.commons.utils.DateUtils;
import cc.elvea.platform.commons.utils.JacksonUtils;
import cc.elvea.platform.system.BaseWebTests;
import cc.elvea.platform.system.core.model.dto.UserRegisterDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static cc.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1__USER__REGISTER;
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
