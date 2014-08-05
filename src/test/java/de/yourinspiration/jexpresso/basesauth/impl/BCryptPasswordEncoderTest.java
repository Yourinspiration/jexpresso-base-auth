package de.yourinspiration.jexpresso.basesauth.impl;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Test case for {@link BCryptPasswordEncoder}.
 * 
 * @author Marcel Härle
 *
 */
public class BCryptPasswordEncoderTest {

    private BCryptPasswordEncoder passwordEncoder;

    @Before
    public void setUp() {
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Test
    public void testEncodeAndCheck() {
        final String plaintext = "test";
        final String encoded = passwordEncoder.encode(plaintext);
        assertTrue(passwordEncoder.checkpw(plaintext, encoded));
    }

}
