package com.example.univerasalwallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    EditText nameSignUp, phoneSignUp, emailSignUp, pwdSignUp, confpwdSingUp;
    CheckBox terms;
    Button btnSignUp;
    TextView login;
    TextView trmsCond;
    DataBaseHelper db;
    String name, phone, email, pwdString, confPwdString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        nameSignUp = (EditText)findViewById(R.id.nameSignUp);
        phoneSignUp = (EditText)findViewById(R.id.phoneSignUp);
        emailSignUp = (EditText)findViewById(R.id.emailSignUp);
        pwdSignUp = (EditText)findViewById(R.id.pwdSignUp);
        confpwdSingUp = (EditText)findViewById(R.id.confpwdSignUp);
        terms = (CheckBox)findViewById(R.id.terms);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);
        login = (TextView)findViewById(R.id.login);
        trmsCond = (TextView)findViewById(R.id.termsAndConditions);

        db = new DataBaseHelper(SignUpActivity.this);

        // to check if email exists or not
        emailSignUp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                email = emailSignUp.getText().toString();

            }
        });

        // to check if the pwd match once leaving the confirm pwd edit text
        confpwdSingUp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                // variable should read the text inside the event to get
                // the most recent text not the empty one if it reads the
                // view inside the oncreate()= onload() method

                pwdString = pwdSignUp.getText().toString();
                confPwdString = confpwdSingUp.getText().toString();

                if(!hasFocus)
                {
                    if(!confPwdString.equals(pwdString))
                    {
                        Toast.makeText(getApplicationContext(), "Passwards are not matched", Toast.LENGTH_LONG).show();
                        pwdSignUp.getText().clear();
                        confpwdSingUp.getText().clear();
                        nameSignUp.clearFocus();
                    }
                }
            }
        });

        // to create new account once clicking the button
        btnSignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                name = nameSignUp.getText().toString();
                phone = phoneSignUp.getText().toString();
                email = emailSignUp.getText().toString();
                pwdString = pwdSignUp.getText().toString();

                if(terms.isChecked())
                {
                    if(name.equals("")||phone.equals("")||email.equals("")||pwdString.equals(""))
                    {
                        Toast.makeText(getApplicationContext(), "Some or all fields are empty", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Boolean mailexist = db.isExist(email);
                        if(!mailexist)
                        {
                            if(!confPwdString.equals(pwdString))
                            {
                                Toast.makeText(getApplicationContext(), "Passwards are not matched", Toast.LENGTH_LONG).show();
                                pwdSignUp.getText().clear();
                                confpwdSingUp.getText().clear();
                                nameSignUp.clearFocus();
                            }
                            else
                            {
                                Boolean insert = db.addUser(name, phone, email,pwdString);
                                if(insert)
                                {
                                    Toast.makeText(getApplicationContext(), "User is added sucessfully", Toast.LENGTH_LONG).show();
                                    nameSignUp.getText().clear();
                                    phoneSignUp.getText().clear();
                                    emailSignUp.getText().clear();
                                    pwdSignUp.getText().clear();
                                    confpwdSingUp.getText().clear();
                                    Intent myIntent = new Intent(v.getContext(), MainActivity.class);
                                    startActivity(myIntent);
                                }
                            }
                        }

                        else
                        {
                            Toast.makeText(getApplicationContext(), "Email is already exist. If you have an account please login", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                elses
                {
                    Toast.makeText(getApplicationContext(), "Please check if you agree with terms and conditions", Toast.LENGTH_LONG).show();
                }

            }
        });

        // click the Terms and conditions link to go back to login activity
        trmsCond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent myIntent = new Intent(v.getContext(), TermsAndConditionsActivity.class);
                startActivity(myIntent);
            }
        });

        // click the login link to go back to login activity
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent myIntent = new Intent(v.getContext(), MainActivity.class);
                startActivity(myIntent);
            }
        });

    }







//    public void pwdMatch(View v)
//    {
//        if (confPwdString.equals(pwdString))
//        {
//            Toast.makeText(getApplicationContext(), "Perfect match", Toast.LENGTH_LONG).show();
//        }
//        else {
//            Toast.makeText(getApplicationContext(), "Passwards are not matched", Toast.LENGTH_LONG).show();
//            pwdSignUp.getText().clear();
//            confpwdSingUp.getText().clear();
//
//        }
//    }
}