package com.damianfanaro.baycakes.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * TODO: complete with description
 *
 * @author dfanaro
 */
@Service
public class AdminRoleSecurityService implements SecurityService {

    private GrantedAuthority adminRoleAuthority = new SimpleGrantedAuthority("ADMIN");

    @Override
    public Boolean hasProtectedAccess() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(adminRoleAuthority);
    }

}
