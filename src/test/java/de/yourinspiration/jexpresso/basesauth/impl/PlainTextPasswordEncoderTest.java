package de.yourinspiration.jexpresso.basesauth.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Test case for {@link PlainTextPasswordEncoder}.
 * 
 * @author Marcel Härle
 *
 */
public class PlainTextPasswordEncoderTest {

    private PlainTextPasswordEncoder passwordEncoder;

    @Before
    public void setUp() {
        passwordEncoder = new PlainTextPasswordEncoder();
    }

    @Test
    public void testCheckpw() {
        final String plaintext = "test";
        final String encoded = "test";
        assertTrue(passwordEncoder.checkpw(plaintext, encoded));
    }

    @Test
    public void testEncode() {
        final String plaintext = "test";
        final String expected = "test";
        assertEquals(expected, passwordEncoder.encode(plaintext));
    }

}
