package cn.elvea.platform.auth.security;

import cn.elvea.platform.system.security.api.ClientApi;
import cn.elvea.platform.system.security.model.dto.ClientDto;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

import static cn.elvea.platform.auth.security.utils.OAuth2Utils.toRegisteredClient;
import static cn.elvea.platform.auth.security.utils.OAuth2Utils.toRegisteredClientDto;

/**
 * @author elvea
 * @since 0.0.1
 */
@Service
public class CustomOAuth2ClientService implements RegisteredClientRepository {

    private final ClientApi clientApi;

    public CustomOAuth2ClientService(ClientApi clientApi) {
        this.clientApi = clientApi;
    }

    @Override
    public void save(RegisteredClient registeredClient) {
        this.clientApi.save(toRegisteredClientDto(registeredClient));
    }

    @Override
    public RegisteredClient findById(String id) {
        ClientDto clientDto = this.clientApi.findById(Long.valueOf(id)).getData();
        return toRegisteredClient(clientDto);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        ClientDto clientDto = this.clientApi.findByClientId(clientId).getData();
        return toRegisteredClient(clientDto);
    }

}
