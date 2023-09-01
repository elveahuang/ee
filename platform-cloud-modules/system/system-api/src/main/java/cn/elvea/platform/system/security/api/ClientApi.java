package cn.elvea.platform.system.security.api;

import cn.elvea.platform.commons.core.web.R;
import cn.elvea.platform.system.security.model.dto.ClientDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static cn.elvea.platform.system.commons.constants.SystemMappingConstants.*;
import static cn.elvea.platform.system.commons.constants.SystemServiceConstants.SYSTEM_SERVICE;

/**
 * @author elvea
 * @since 0.0.1
 */
@Component
@FeignClient(name = SYSTEM_SERVICE, fallback = ClientApi.ClientApiFallback.class)
public interface ClientApi {

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
    class ClientApiFallback implements ClientApi {

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
