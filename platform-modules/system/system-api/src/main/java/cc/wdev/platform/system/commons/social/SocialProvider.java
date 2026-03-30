package cc.wdev.platform.system.commons.social;

import cc.wdev.platform.commons.security.user.SocialUser;

import java.util.Map;

/**
 * @author elvea
 */
public interface SocialProvider {

    SocialUser auth(Map<String, Object> parameters);

}
