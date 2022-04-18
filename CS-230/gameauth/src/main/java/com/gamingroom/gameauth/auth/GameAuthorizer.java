package com.gamingroom.gameauth.auth;

import io.dropwizard.auth.Authorizer;

public class GameAuthorizer implements Authorizer<GameUser> 
{
    @Override
    public boolean authorize(GameUser user, String role) {
    	
    	// Return a boolean - true if the user is authorized and false if the user is not authorized
    	return user.getRoles() != null && user.getRoles().contains(role);
    	
    }
}