package com.profiller.commons;

import play.Play;

public class Configuration
{
    public static String get( String key )
    {
        return Play.application().configuration().getString( key );
    }

    public static String getApplicationSecret()
    {
        return get( "application.secret" );
    }
}
