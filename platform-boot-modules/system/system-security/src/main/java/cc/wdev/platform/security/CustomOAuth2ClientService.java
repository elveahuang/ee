package cc.wdev.platform.security;

import cc.wdev.platform.security.utils.OAuth2Utils;
import cc.wdev.platform.system.security.api.ClientApi;
import cc.wdev.platform.system.security.domain.dto.ClientDto;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Service
@AllArgsConstructor
public class CustomOAuth2ClientService implements RegisteredClientRepository {

    private final ClientApi clientApi;

    private final TokenSettings tokenSettings;

    @Override
    public void save(RegisteredClient registeredClient) {
        this.clientApi.save(OAuth2Utils.toRegisteredClientDto(registeredClient));
    }

    @Override
    public RegisteredClient findById(String id) {
        ClientDto clientDto = this.clientApi.findById(Long.valueOf(id));
        return OAuth2Utils.toRegisteredClient(clientDto, tokenSettings);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        ClientDto clientDto = this.clientApi.findByClientId(clientId);
        return OAuth2Utils.toRegisteredClient(clientDto, tokenSettings);
    }

}
