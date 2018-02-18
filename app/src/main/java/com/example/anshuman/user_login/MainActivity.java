package com.example.anshuman.user_login;

import android.accounts.Account;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {
    EditText name;
    EditText phone;
    ProgressBar spinner;
    Intent i;
    TextView text;
    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("MIICWwIBAAKBgHmWdWg+x0bP8L67ma359xb")
                .server("http://18.220.188.73:1337/parse/")
                .build()
        );

        name=(EditText)findViewById(R.id.text_name);
        phone=(EditText)findViewById(R.id.text_phone);
        spinner= (ProgressBar)findViewById(R.id.progress);
        spinner.setVisibility(View.GONE);
        text=(TextView)findViewById(R.id.text);
        signup();

    }

    private void signup() {

    }

    public void submit(View view) {
        final String nameStr= String.valueOf(name.getText());
        String passStr=String.valueOf("1234");
        ParseUser user=new ParseUser();
        user.setUsername(nameStr);
        user.setPassword(passStr);
        i=new Intent(this,ActivityLogin.class);

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e==null){
                    Toast.makeText(MainActivity.this, "logged in"+nameStr, Toast.LENGTH_SHORT).show();
                    spinner.setVisibility(View.VISIBLE);
                    startActivity(i);
                }
                else {
                    String error= String.valueOf(e);
                    Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
                    Log.e(TAG,error);
                    String user_error="com.parse.ParseRequest$ParseRequestException: Account already exists for this username.";
                    text.setText(user_error);
                    if (error==user_error){
                        ParseUser.logInInBackground(nameStr, "1234", new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {
                                if(user!=null){
                                    Toast.makeText(MainActivity.this, "logged in", Toast.LENGTH_SHORT).show();
                                    startActivity(i);
                                }
                                else {
                                    String err=String.valueOf(e);
                                    Toast.makeText(MainActivity.this, err, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                    else{
                        Toast.makeText(MainActivity.this, "user not found", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
