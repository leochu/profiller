package com.profiller.services;

import com.profiller.models.ebean.User;

public interface UserService
{
    public void createUser( User user );

    public User getUserByEmailMD5( String emailMD5 );
    
    public User getUserByEmail( String emailMD5 );
}
