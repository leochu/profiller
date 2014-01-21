package com.profiller.commons.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.mindrot.jbcrypt.BCrypt;

public class CryptoUtils
{
    public static String generateSalt()
    {
        return BCrypt.gensalt();
    }

    public static String MD5( String raw )
    {
        return DigestUtils.md5Hex( raw );
    }

    public static String hashSecret( String rawSecret, String salt )
    {
        return BCrypt.hashpw( rawSecret, BCrypt.gensalt() );
    }
}
