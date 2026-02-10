package cc.wdev.platform.auth.security;

import cc.wdev.platform.system.security.domain.dto.ClientDto;
import cc.wdev.platform.system.security.feign.ClientFeignClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

import static cc.wdev.platform.auth.security.utils.OAuth2Utils.toRegisteredClient;
import static cc.wdev.platform.auth.security.utils.OAuth2Utils.toRegisteredClientDto;

/**
 * @author elvea
 */
@Service
public class CustomOAuth2ClientService implements RegisteredClientRepository {

    private final ClientFeignClient clientFeignClient;

    public CustomOAuth2ClientService(ClientFeignClient clientFeignClient) {
        this.clientFeignClient = clientFeignClient;
    }

    @Override
    public void save(RegisteredClient registeredClient) {
        this.clientFeignClient.save(toRegisteredClientDto(registeredClient));
    }

    @Override
    public RegisteredClient findById(String id) {
        ClientDto clientDto = this.clientFeignClient.findById(Long.valueOf(id)).getData();
        return toRegisteredClient(clientDto);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        ClientDto clientDto = this.clientFeignClient.findByClientId(clientId).getData();
        return toRegisteredClient(clientDto);
    }

}
