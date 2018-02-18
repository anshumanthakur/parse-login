package com.example.anshuman.user_login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseUser;

public class LoginConfirm extends AppCompatActivity {
    Intent main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_confirm);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("MIICWwIBAAKBgHmWdWg+x0bP8L67ma359xb")
                .server("http://18.220.188.73:1337/parse/")
                .build()
        );
        main=new Intent(this,MainActivity.class);
    }

    public void logout(View view) {
        ParseUser.logOut();
        Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();
        startActivity(main);
    }
}
