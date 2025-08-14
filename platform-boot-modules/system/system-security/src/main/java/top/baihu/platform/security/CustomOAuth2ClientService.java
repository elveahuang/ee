package top.baihu.platform.security;

import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Service;
import top.baihu.platform.security.utils.OAuth2Utils;
import top.baihu.platform.system.security.domain.dto.ClientDto;
import top.baihu.platform.system.security.manager.ClientManager;

/**
 * @author elvea
 */
@Service
@AllArgsConstructor
public class CustomOAuth2ClientService implements RegisteredClientRepository {

    private final ClientManager clientManager;

    private final TokenSettings tokenSettings;

    @Override
    public void save(RegisteredClient registeredClient) {
        this.clientManager.save(OAuth2Utils.toRegisteredClientDto(registeredClient));
    }

    @Override
    public RegisteredClient findById(String id) {
        ClientDto clientDto = this.clientManager.findById(Long.valueOf(id));
        return OAuth2Utils.toRegisteredClient(clientDto, tokenSettings);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        ClientDto clientDto = this.clientManager.findByClientId(clientId);
        return OAuth2Utils.toRegisteredClient(clientDto, tokenSettings);
    }

}
