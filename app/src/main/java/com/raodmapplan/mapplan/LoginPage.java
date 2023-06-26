package com.raodmapplan.mapplan;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginPage extends AppCompatActivity {

    private EditText username , password  ;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);
        username = findViewById(R.id.userInput1);
        password = findViewById(R.id.passwordInput1);
        Button loginButt = findViewById(R.id.login_bto);




        /*go to home page*/
        loginButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
            }
        });



        /*go to register page */
        TextView register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerPage = new Intent(LoginPage.this , RegisterPage.class);
                startActivity(registerPage);
            }
        });




    }// end of onCreate


}// End of LoginPage

