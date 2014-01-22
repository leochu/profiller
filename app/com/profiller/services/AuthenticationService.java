package com.profiller.services;

public interface AuthenticationService
{
    public boolean authenticate( String user, String secret );
}
