package com.profiller.commons;

import com.google.inject.AbstractModule;
import com.profiller.services.UserService;
import com.profiller.services.impl.UserServiceImpl;

public class RuntimeModule
    extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind( UserService.class ).to( UserServiceImpl.class );
        bind( Persistence.class ).to( EbeanPersistence.class );
    }
}
