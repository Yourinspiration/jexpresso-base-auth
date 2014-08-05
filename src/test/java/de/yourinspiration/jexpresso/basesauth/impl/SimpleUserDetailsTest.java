package de.yourinspiration.jexpresso.basesauth.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import de.yourinspiration.jexpresso.baseauth.GrantedAuthority;

/**
 * Test case for {@link SimpleUserDetails}.
 * 
 * @author Marcel Härle
 *
 */
public class SimpleUserDetailsTest {

    private SimpleUserDetails simpleUserDetails;

    private final String username = "username";
    private final String password = "password";
    private final Collection<GrantedAuthority> authorities = new ArrayList<>();
    private final boolean enabled = true;

    @Before
    public void setUp() {
        simpleUserDetails = new SimpleUserDetails(username, password, authorities, enabled);
    }

    @Test
    public void testGetAuthorities() {
        assertEquals(authorities, simpleUserDetails.getAuthorities());
    }

    @Test
    public void testGetPassword() {
        assertEquals(password, simpleUserDetails.getPassword());
    }

    @Test
    public void testGetUsername() {
        assertEquals(username, simpleUserDetails.getUsername());
    }

    @Test
    public void testIsEnabled() {
        assertEquals(enabled, simpleUserDetails.isEnabled());
    }

}
