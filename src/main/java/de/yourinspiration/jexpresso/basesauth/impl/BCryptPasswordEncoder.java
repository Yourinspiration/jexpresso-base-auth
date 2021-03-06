package de.yourinspiration.jexpresso.basesauth.impl;

import org.mindrot.jbcrypt.BCrypt;

import de.yourinspiration.jexpresso.baseauth.PasswordEncoder;

/**
 * Password encoder for BCrypt algorithm.
 * 
 * @author Marcel Härle
 *
 */
public class BCryptPasswordEncoder implements PasswordEncoder {

    @Override
    public boolean checkpw(final String plaintext, final String encoded) {
        return BCrypt.checkpw(plaintext, encoded);
    }

    @Override
    public String encode(String plaintext) {
        return BCrypt.hashpw(plaintext, BCrypt.gensalt());
    }

}
