package com.profiller.controllers;

import java.util.regex.Pattern;

import javax.inject.Inject;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.profiller.commons.ProfillerConstants;
import com.profiller.commons.ProfillerException;
import com.profiller.commons.utils.CryptoUtils;
import com.profiller.models.ebean.User;
import com.profiller.services.AuthenticationService;
import com.profiller.services.UserService;

public class UserController
    extends Controller
{
    private UserService userService;

    private AuthenticationService authenticationService;

    private static final Pattern pattern =
        Pattern.compile( "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$" );

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

    public Result saveProfile()
    {
        JsonNode requestBody = Controller.request().body().asJson();

        String firstName = requestBody.get( "firstName" ).asText();
        String lastName = requestBody.get( "lastName" ).asText();
        
        String emailMD5 = Controller.session( ProfillerConstants.SESSION_KEY_USER );
        
        User user = new User();
        user.setFirstName( firstName );
        user.setLastName( lastName );
        user.setEmailMD5( emailMD5 );
        
        this.userService.updateUser( user );
        
        JsonNode userNode = Json.toJson( user );

        return ok( userNode );
    }
    
    public Result getUserInSession()
    {
        String emailMD5 = Controller.session( ProfillerConstants.SESSION_KEY_USER );

        ObjectNode userNode = JsonNodeFactory.instance.objectNode();
        userNode.put( "loggedIn", emailMD5 != null );
        userNode.put( "emailMD5", emailMD5 );

        return ok( userNode );
    }

    public Result logout()
    {
        Controller.session().clear();
        return redirect( "site/login.html" );
    }

    public Result login()
    {
        JsonNode requestBody = Controller.request().body().asJson();

        String userName = requestBody.get( "username" ).asText();
        String secret = requestBody.get( "secret" ).asText();

        userName = userName.toLowerCase().trim();

        boolean authenticated = this.authenticationService.authenticate( userName, secret );

        if ( authenticated )
        {
            Controller.session( ProfillerConstants.SESSION_KEY_USER, CryptoUtils.MD5( userName ) );

            return ok( JsonNodeFactory.instance.objectNode() );
        }

        return badRequest( JsonNodeFactory.instance.objectNode() );
    }

    public Result registerUser()
    {
        JsonNode requestBody = Controller.request().body().asJson();

        String email = requestBody.get( "username" ).asText();
        String secret = requestBody.get( "secret" ).asText();
        String secretConfirmation = requestBody.get( "secretConfirm" ).asText();

        this.validateRegistration( email, secret, secretConfirmation );

        User user = new User();

        user.setEmail( email.trim().toLowerCase() );
        user.setSecret( secret );

        this.userService.createUser( user );

        JsonNode userNode = Json.toJson( user );

        return ok( userNode );
    }

    private void validateRegistration( String email, String secret, String secretConfirmation )
    {
        if ( !this.validateEmail( email ) )
            throw new ProfillerException( "Email is invalid" );

        if ( !this.validateEmailExist( email ) )
            throw new ProfillerException( "Email is already taken" );

        // password match
        this.validateSecret( secret );

        // password doesn't match
        if ( !this.secretMatches( secret, secretConfirmation ) )
            throw new ProfillerException( "Password doesn't match with the confirmation" );
    }

    private void validateSecret( String secret )
    {
        if ( secret == null || secret.length() < 8 )
            throw new ProfillerException( "Password is too short (minimum is 8 characters)" );

        if ( !secret.matches( ".*[^0-9].*" ) )
            throw new ProfillerException( "Password needs at least one number" );

        if ( !secret.matches( ".*[^a-zA-Z].*" ) )
            throw new ProfillerException( "Password needs at least one letter" );

    }

    private boolean secretMatches( String secret, String secretConfirmation )
    {
        return secret.equals( secretConfirmation );
    }

    private boolean validateEmail( String email )
    {
        return pattern.matcher( email ).matches();

    }

    private boolean validateEmailExist( String email )
    {
        return ( this.userService.getUserByEmail( email ) == null );
    }
}
