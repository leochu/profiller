package com.profiller.controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class MainViewController
    extends Controller
{
    public Result index()
    {
        return redirect( "site/index.html" );
    }

    public Result profile()
    {
        return redirect( "site/profile.html" );
    }
}
