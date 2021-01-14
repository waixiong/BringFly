package com.example.univerasalwallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText emailSignIn, pwdSignIn;
    Button signIn;
    TextView forgotpwd, signUp;
    DataBaseHelper db;
    protected static int uid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailSignIn = (EditText)findViewById(R.id.emailSignIn);
        pwdSignIn = (EditText)findViewById(R.id.pwdSignIn);
        signIn = (Button)findViewById(R.id.signIn);
        forgotpwd = (TextView)findViewById(R.id.forgotPwd);
        signUp = (TextView)findViewById(R.id.signUp);

        db = new DataBaseHelper(MainActivity.this);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String email = emailSignIn.getText().toString();
                String pwd = pwdSignIn.getText().toString();
                Boolean login = db.login(email, pwd);

                if(login == true)
                {
                // Take the email so it can show a welcome toast in home activity with user name.
                    Intent myIntent = new Intent(v.getContext(), HomeActivity.class);
                    startActivity(myIntent);

//                    uid = db.getUid(email);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Wrong Email or password!", Toast.LENGTH_LONG).show();
                    pwdSignIn.getText().clear();
                }
           }
        });

        forgotpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent myIntent = new Intent(v.getContext(), ForgotPwdActivity.class);
                startActivity(myIntent);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent myIntent = new Intent(v.getContext(), SignUpActivity.class);
                startActivity(myIntent);
            }
        });
    }

//    public void forgotpwdOnClick(View v)
//    {
//        Intent myIntent = new Intent(this, ForgotPwdActivity.class);
//        startActivity(myIntent);
//    }
//
//    public void signUpOnClick(View v)
//    {
//        Intent myIntent = new Intent(this, SignUpActivity.class);
//        startActivity(myIntent);
//    }
}