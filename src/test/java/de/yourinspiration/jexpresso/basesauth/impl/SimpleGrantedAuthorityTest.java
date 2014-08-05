package de.yourinspiration.jexpresso.basesauth.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test case for {@link SimpleGrantedAuthority}.
 * 
 * @author Marcel Härle
 *
 */
public class SimpleGrantedAuthorityTest {

    private SimpleGrantedAuthority simpleGrantedAuthority;

    private final String authority = "USER";

    @Before
    public void setUp() {
        simpleGrantedAuthority = new SimpleGrantedAuthority(authority);
    }

    @Test
    public void testGetAuthority() {
        assertEquals(authority, simpleGrantedAuthority.getAuthority());
    }

}
