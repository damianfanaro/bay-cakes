package com.damianfanaro.baycakes.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

/**
 * TODO: complete with description
 *
 * @author dfanaro
 */
public class BayCakesUserFactory {

    public static BayCakesUser create(User user) {
        Collection<? extends GrantedAuthority> authorities;
        try {
            authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities());
        } catch (Exception e) {
            authorities = null;
        }
        return new BayCakesUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getLastPasswordReset(),
                authorities
        );
    }

}