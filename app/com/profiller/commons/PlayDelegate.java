package com.profiller.commons;

import play.GlobalSettings;
import play.libs.F.Function0;
import play.libs.F.Promise;
import play.mvc.Http.RequestHeader;
import play.mvc.Results;
import play.mvc.SimpleResult;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class PlayDelegate
    extends GlobalSettings
{
    public final static Injector INJECTOR = Guice.createInjector( new RuntimeModule() );

    @Override
    public <A> A getControllerInstance( Class<A> clazz )
        throws Exception
    {
        return INJECTOR.getInstance( clazz );
    }

    @Override
    public Promise<SimpleResult> onError( RequestHeader header, Throwable t )
    {

        return super.onError( header, t );
    }

    @Override
    public Promise<SimpleResult> onHandlerNotFound( RequestHeader header )
    {
        final String path = header.path();
        final int lastCharIndex = path.length() - 1;

        if ( path.charAt( lastCharIndex ) == '/' )
        {
            return Promise.promise( new Function0<SimpleResult>()
            {
                @Override
                public SimpleResult apply()
                    throws Throwable
                {
                    return Results.redirect( path.substring( 0, lastCharIndex ) );
                }
            } );
        }

        return Promise.promise( new Function0<SimpleResult>()
        {
            @Override
            public SimpleResult apply()
                throws Throwable
            {
                return Results.redirect( "/site/404.html" );
            }
        } );
    }

}
