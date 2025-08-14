package top.baihu.platform.auth.security;

import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;
import top.baihu.platform.system.security.api.ClientApi;
import top.baihu.platform.system.security.domain.dto.ClientDto;

import static top.baihu.platform.auth.security.utils.OAuth2Utils.toRegisteredClient;
import static top.baihu.platform.auth.security.utils.OAuth2Utils.toRegisteredClientDto;

/**
 * @author elvea
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
