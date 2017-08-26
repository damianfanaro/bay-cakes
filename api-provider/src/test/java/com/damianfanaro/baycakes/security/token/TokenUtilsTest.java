package com.damianfanaro.baycakes.security.token;

import io.jsonwebtoken.lang.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.userdetails.UserDetails;

import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link TokenUtils}.
 *
 * @author dfanaro
 */
public class TokenUtilsTest {

    private UserDetails userDetails;
    private Device device;

    @Before
    public void setUp() {
        userDetails = mock(UserDetails.class);
        device = mock(Device.class);

        when(userDetails.getUsername()).thenReturn("admin");
        when(device.isNormal()).thenReturn(true);
    }

    @Test
    public void shouldPasswordEncodingMatch() {
        TokenUtils tokenUtils = new TokenUtils();
        tokenUtils.setSecret("baycakes-secret");
        tokenUtils.setExpiration(604800L);
        String token = tokenUtils.generateToken(userDetails, device);
        Assert.notNull(token);
    }

}
