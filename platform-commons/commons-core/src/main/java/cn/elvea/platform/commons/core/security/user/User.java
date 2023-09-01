package cn.elvea.platform.commons.core.security.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

/**
 * @author elvea
 * @since 0.0.1
 */
@Getter
@Setter
public class User implements UserDetails {

    private String sid;

    private final Long id;

    private final String password;

    private final String username;

    private final Set<GrantedAuthority> authorities;

    private final boolean enabled;

    private final boolean accountNonExpired;

    private final boolean accountNonLocked;

    private final boolean credentialsNonExpired;

    public User(Long id, String username, String password, Set<GrantedAuthority> authorities) {
        this(id, username, password, authorities, true, true, true, true);
    }

    public User(Long id, String username, String password,
                Set<GrantedAuthority> authorities,
                boolean enabled, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
    }

    private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
        SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<>(new User.AuthorityComparator());
        for (GrantedAuthority grantedAuthority : authorities) {
            Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
            sortedAuthorities.add(grantedAuthority);
        }
        return sortedAuthorities;
    }

    private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {

        @Serial
        private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

        @Override
        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
            if (g2.getAuthority() == null) {
                return -1;
            }
            if (g1.getAuthority() == null) {
                return 1;
            }
            return g1.getAuthority().compareTo(g2.getAuthority());
        }

    }

}
