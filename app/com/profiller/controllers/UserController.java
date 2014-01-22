package com.profiller.controllers;

import javax.inject.Inject;

import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.profiller.models.ebean.User;
import com.profiller.services.AuthenticationService;
import com.profiller.services.UserService;

public class UserController
    extends Controller
{
    private UserService userService;

    private AuthenticationService authenticationService;

    @Inject
    public UserController( UserService userService, AuthenticationService authenticationService )
    {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    public Result getUser( String id )
    {
        User user = this.userService.getUserByEmailMD5( id );

        JsonNode userNode = Json.toJson( user );

        return ok( userNode );
    }

    public Result login()
    {
        JsonNode requestBody = Controller.request().body().asJson();

        String userName = requestBody.get( "username" ).asText();
        String secret = requestBody.get( "secret" ).asText();

        boolean authenticated = this.authenticationService.authenticate( userName, secret );

        if ( authenticated )
        {
            return ok( JsonNodeFactory.instance.objectNode() );
        }

        return badRequest( JsonNodeFactory.instance.objectNode() );
    }

    public Result registerUser()
    {
        JsonNode requestBody = Controller.request().body().asJson();

        String email = requestBody.get( "reg_username" ).asText();
        String secret = requestBody.get( "reg_secret" ).asText();
        String secretConfirmation = requestBody.get( "reg_secret_confirm" ).asText();

        
        
        User user = new User();
        
        user.setEmail( email );
        user.setSecret( secret );
                        
        this.userService.createUser( user );

        JsonNode userNode = Json.toJson( user );

        return ok( userNode );
    }
    
    private void validateRegistration(String email, String secret, String secretConfirmation)
    {
        // invalid email
        // email already taken
        // password doesn't match
        // password match
        throw new RuntimeException("test error message");
    }
}
