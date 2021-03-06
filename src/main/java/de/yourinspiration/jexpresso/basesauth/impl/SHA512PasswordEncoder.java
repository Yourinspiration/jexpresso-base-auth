package de.yourinspiration.jexpresso.basesauth.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.pmw.tinylog.Logger;

import de.yourinspiration.jexpresso.baseauth.PasswordEncoder;

/**
 * Password encoder for SHA-512 algorithm.
 * 
 * @author Marcel Härle
 *
 */
public class SHA512PasswordEncoder implements PasswordEncoder {

    private final String SHA512_ALGORITHM = "SHA-512";

    @Override
    public boolean checkpw(final String plaintext, final String encoded) {
        try {
            final MessageDigest sha512Digest = MessageDigest.getInstance(SHA512_ALGORITHM);
            final byte[] bytes = sha512Digest.digest(plaintext.getBytes());

            final String encodedPlainText = SecurityUtils.toHex(bytes);

            return encodedPlainText.equals(encoded);
        } catch (NoSuchAlgorithmException e) {
            Logger.error("Error creating sha512 message digest");
            return false;
        }
    }

    @Override
    public String encode(String plaintext) {
        try {
            final MessageDigest sha512Digest = MessageDigest.getInstance(SHA512_ALGORITHM);
            final byte[] bytes = sha512Digest.digest(plaintext.getBytes());
            return SecurityUtils.toHex(bytes);
        } catch (NoSuchAlgorithmException ex) {
            Logger.error("Error creating sha512 message digest");
            return null;
        }
    }

}
