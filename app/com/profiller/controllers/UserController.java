package com.profiller.controllers;

import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import com.profiller.models.ebean.User;
import com.profiller.services.UserService;

public class UserController
    extends Controller
{
    private UserService userService;

    @Inject
    public UserController( UserService userService )
    {
        this.userService = userService;
    }

    public Result getUser( String id )
    {
        User user = this.userService.getUserByEmailMD5( id );

        JsonNode userNode = Json.toJson( user );

        return ok( userNode );
    }

    public Result registerUser()
    {
        Form<User> form = Form.form( User.class );
        User user = form.bindFromRequest().get();

        this.userService.createUser( user );

        JsonNode userNode = Json.toJson( user );

        return ok( userNode );
    }
}
