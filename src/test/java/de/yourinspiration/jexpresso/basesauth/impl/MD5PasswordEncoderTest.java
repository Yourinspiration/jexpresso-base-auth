package de.yourinspiration.jexpresso.basesauth.impl;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Test case for {@link MD5PasswordEncoder}.
 * 
 * @author Marcel Härle
 *
 */
public class MD5PasswordEncoderTest {

    private MD5PasswordEncoder passwordEncoder;

    @Before
    public void setUp() {
        passwordEncoder = new MD5PasswordEncoder();
    }

    @Test
    public void testEncodeAndCheck() {
        final String plaintext = "test";
        final String encoded = passwordEncoder.encode(plaintext);
        assertTrue(passwordEncoder.checkpw(plaintext, encoded));
    }

}
