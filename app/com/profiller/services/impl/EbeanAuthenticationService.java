package com.profiller.services.impl;

import javax.inject.Inject;

import com.profiller.commons.utils.CryptoUtils;
import com.profiller.models.ebean.User;
import com.profiller.services.AuthenticationService;
import com.profiller.services.UserService;

public class EbeanAuthenticationService
    implements AuthenticationService
{
    private UserService userService;

    @Inject
    public EbeanAuthenticationService( UserService userService )
    {
        this.userService = userService;
    }

    @Override
    public boolean authenticate( String user, String secret )
    {
        String emailMD5 = CryptoUtils.MD5( user.trim().toLowerCase() );

        User registeredUser = this.userService.getUserByEmailMD5(emailMD5);

        if ( registeredUser != null )
        {
            String hashedSecret = CryptoUtils.hashSecret( secret, registeredUser.getSalt() );

            if ( hashedSecret.equals( registeredUser.getSecret() ) )
            {
                return true;
            }
        }

        return false;
    }

}
