package com.raodmapplan.mapplan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText TextInputEditText_ID, TextInputEditText_Password;
    private RelativeLayout RelativeLayout_Login, RelativeLayout_LoginGoogle;
    private Button Button_SignUp, Button_FindPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextInputEditText_ID = findViewById(R.id.TextInputEditText_ID);
        TextInputEditText_Password = findViewById(R.id.TextInputEditText_Password);
        RelativeLayout_Login = findViewById(R.id.RelativeLayout_Login);


        //GO to signUp or RegisterActivity page
        Button_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, HomePage.class);
                startActivity(intent);
            }
        });

        RelativeLayout_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //store the data
                String userID = TextInputEditText_ID.getText().toString();
                String userPassword = TextInputEditText_Password.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);   //Returns whether the communication status was successful
                            boolean success = jsonObject.getBoolean("success");
                            //If login is successful
                            if(success){
                                String userID = jsonObject.getString("userID");
                                String userPassword = jsonObject.getString("userPassword");

                                Toast.makeText(getApplicationContext(), "Login Succeed.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("userID", userID);
                                intent.putExtra("userPassword", userPassword);
                                startActivity(intent);
                            }
                            else{        //If login fails
                                Toast.makeText(getApplicationContext(), "Login Failed.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                //Login processing complete
                LoginRequest loginRequest = new LoginRequest(userID, userPassword, responseListener);
                RequestQueue  queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });




    }// End of onCreate method



} // End of the LoginActivity class
