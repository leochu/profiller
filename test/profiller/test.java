package profiller;

import junit.framework.Assert;

import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;

public class test
{
    @Test
    public void test()
    {

        String salt = BCrypt.gensalt();

        System.out.println(salt);
        
        String hash1 = BCrypt.hashpw( "abcd", salt );

        String hash2 = BCrypt.hashpw( "abcd", salt );

        System.out.println(hash1);

        
        Assert.assertEquals( hash1, hash2 );

    }
}
