package com.example.anshuman.user_login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class ActivityLogin extends AppCompatActivity {
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        i= new Intent(this,LoginConfirm.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("MIICWwIBAAKBgHmWdWg+x0bP8L67ma359xb")
                .server("http://18.220.188.73:1337/parse/")
                .build()
        );
    }


    public void next(View view) {
        startActivity(i);

    }
}
