package com.damianfanaro.baycakes.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TODO: complete with description
 *
 * @author dfanaro
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
