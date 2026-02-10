package cc.wdev.platform.system.security.feign;

import cc.wdev.platform.commons.domain.R;
import cc.wdev.platform.system.commons.constants.SystemServiceConstants;
import cc.wdev.platform.system.security.domain.dto.ClientDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static cc.wdev.platform.system.commons.constants.SystemMappingConstants.*;

/**
 * @author elvea
 */
@Component
@FeignClient(name = SystemServiceConstants.SYSTEM_SERVICE, fallback = ClientFeignClient.ClientFeignClientFallback.class)
public interface ClientFeignClient {

    @PostMapping(API_V1_FEIGN__SECURITY__CLIENT__SAVE)
    R<?> save(@SpringQueryMap ClientDto clientDto);

    @PostMapping(API_V1_FEIGN__SECURITY__CLIENT__FIND_BY_ID)
    R<ClientDto> findById(@RequestParam("id") Long id);

    @PostMapping(API_V1_FEIGN__SECURITY__CLIENT__FIND_BY_CLIENT_ID)
    R<ClientDto> findByClientId(@RequestParam("clientId") String clientId);

    // -----------------------------------------------------------------------------------------------------------------
    // Fallback
    // -----------------------------------------------------------------------------------------------------------------

    @Slf4j
    @Component
    class ClientFeignClientFallback implements ClientFeignClient {

        @Override
        public R<?> save(ClientDto clientDto) {
            return R.success();
        }

        @Override
        public R<ClientDto> findById(Long id) {
            return R.success();
        }

        @Override
        public R<ClientDto> findByClientId(String clientId) {
            return R.success();
        }

    }

}
