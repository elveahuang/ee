package cc.wdev.platform.system.core.api;

import cc.wdev.platform.commons.security.user.SocialUser;
import cc.wdev.platform.system.commons.social.SocialProvider;

import java.util.Map;

/**
 * @author elvea
 */
public interface SocialApi {

    /**
     *
     */
    SocialProvider getProvider(String socialType);

    /**
     *
     */
    SocialUser retrieveSocialUser(String socialType,
                                  Map<String, Object> parameters);

}
