package com.damianfanaro.baycakes.security;

import com.damianfanaro.baycakes.security.dto.AuthenticationRequest;
import com.damianfanaro.baycakes.security.dto.AuthenticationResponse;
import com.damianfanaro.baycakes.security.token.TokenUtils;
import com.damianfanaro.baycakes.user.BayCakesUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO: complete with description
 *
 * @author dfanaro
 */
@RestController
public class AuthenticationController {

    @Value("${baycakes.token.header}")
    private String tokenHeader;

    private final AuthenticationManager authenticationManager;
    private final TokenUtils tokenUtils;
    private final UserDetailsService userDetailsService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, TokenUtils tokenUtils, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.tokenUtils = tokenUtils;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping(value = "${baycakes.route.authentication}")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest authenticationRequest, Device device) throws AuthenticationException {
        performAuthentication(authenticationRequest);
        String token = getToken(authenticationRequest, device);
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    @GetMapping(value = "${baycakes.route.authentication.refresh}")
    public ResponseEntity<?> refreshUserToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = tokenUtils.getUsernameFromToken(token);
        BayCakesUserDetails user = (BayCakesUserDetails) userDetailsService.loadUserByUsername(username);
        if (tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordReset())) {
            String refreshedToken = tokenUtils.refreshToken(token);
            return ResponseEntity.ok(new AuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    private void performAuthentication(AuthenticationRequest authenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(getAuthenticationFromClientRequest(authenticationRequest));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationFromClientRequest(AuthenticationRequest authenticationRequest) {
        return new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
    }

    private String getToken(@RequestBody AuthenticationRequest authenticationRequest, Device device) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        return tokenUtils.generateToken(userDetails, device);
    }

}
