package com.thetimekeepers.timeit;

import com.parse.ParseClassName;

import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Plist")

public class Plist extends ParseObject{

    public static final String KEY_ACTION = "action";
    public static final String KEY_USER = "user";

    public String getAction(){ return getString(KEY_ACTION); }
    public void setAction(String description){
        put(KEY_ACTION, description);
    }
    public ParseUser getUser(){
        return getParseUser(KEY_USER);
    }

}
