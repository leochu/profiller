package com.profiller.services.impl;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.avaje.ebean.Ebean;
import com.profiller.commons.Persistence;
import com.profiller.commons.utils.CryptoUtils;
import com.profiller.models.ebean.User;
import com.profiller.services.UserService;

public class UserServiceImpl
    implements UserService
{
    private Persistence persistence;

    @Inject
    public UserServiceImpl( Persistence persistence )
    {
        this.persistence = persistence;
    }

    @Override
    public void createUser( User user )
    {
        String rawSecret = user.getSecret();

        String salt = CryptoUtils.generateSalt();

        String hashedSecret = CryptoUtils.hashSecret( rawSecret, salt );

        String emailMD5 = CryptoUtils.MD5( user.getEmail().trim().toLowerCase() );

        user.setSalt( salt );
        user.setSecret( hashedSecret );
        user.setEmailMD5( emailMD5 );

        this.persistence.save( user );
    }

    @Override
    public User getUserByEmailMD5( String emailMD5 )
    {
        Map<String, Object> criteria = new HashMap<String, Object>();
        criteria.put( "emailMD5", emailMD5 );

        return (User) this.persistence.findOne( User.class, criteria );
    }

    @Override
    public User getUserByEmail( String email )
    {
        Map<String, Object> criteria = new HashMap<String, Object>();
        criteria.put( "email", email );

        return (User) this.persistence.findOne( User.class, criteria );
    }

    @Override
    public User updateUser( User user )
    {
        // TODO: write a generic update method in persistence class

        User oldUser = this.getUserByEmailMD5( user.getEmailMD5() );
        oldUser.setFirstName( user.getFirstName() );
        oldUser.setLastName( user.getLastName() );

        Ebean.update( oldUser );

        return oldUser;
    }

}
