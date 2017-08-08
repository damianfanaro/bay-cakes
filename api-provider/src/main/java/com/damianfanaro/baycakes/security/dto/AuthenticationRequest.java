package com.damianfanaro.baycakes.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * TODO: complete with description
 *
 * @author dfanaro
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {

    private static final long serialVersionUID = 6624726180748515507L;

    private String username;
    private String password;

}
