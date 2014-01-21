package profiller;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class test
{
    @Test
    public void test()
    {
        System.out.println( DigestUtils.sha256Hex( "MyEmailAddress@example.com ".trim().toLowerCase() ) );
        System.out.println( DigestUtils.sha512Hex( "MyEmailAddress@example.com ".trim().toLowerCase() ) );

        System.out.println( DigestUtils.md5Hex( "MyEmailAddress@example.com ".trim().toLowerCase() ) );
    }
}
