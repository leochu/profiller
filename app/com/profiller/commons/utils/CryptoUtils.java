package com.profiller.commons.utils;

import java.security.SecureRandom;

import org.apache.commons.codec.digest.DigestUtils;
import org.mindrot.jbcrypt.BCrypt;

import com.profiller.commons.Configuration;

public class CryptoUtils
{
    private static final int SALT_SIZE = 256;

    public static String generateSalt()
    {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_SIZE];
        random.nextBytes( salt );

        return new String( salt );
    }

    public static String MD5( String raw )
    {
        return DigestUtils.md5Hex( raw );
    }

    public static String hashSecret( String rawSecret, String salt )
    {
        return BCrypt.hashpw( rawSecret, salt + Configuration.getApplicationSecret() );
    }
}
