package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //Register your parse models
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("qYrzSOTpQiNAsQgJDUh7ip1IyqiidVke7ny0xrgn")
                .clientKey("e2law5zg6owPtfeGmztayRsfc2wN2T62hyEJHW85")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
