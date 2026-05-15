package cc.wdev.platform.commons.security.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

/**
 * @author elvea
 */
@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class Role implements Serializable {

    private final Long id;

    private final String code;

    private final String name;

    private final Set<String> scopes;

}
