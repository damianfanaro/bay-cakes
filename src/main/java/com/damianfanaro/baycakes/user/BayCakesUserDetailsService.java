package com.damianfanaro.baycakes.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * TODO: complete with description
 *
 * @author dfanaro
 */
@Service
public class BayCakesUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public BayCakesUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return BayCakesUserFactory.create(user);
        }
    }

}
