package com.raodmapplan.mapplan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText TextInputEditText_SignUp_Name, TextInputEditText_SignUp_BirthDate, TextInputEditText_SignUp_PhoneNumber, TextInputEditText_SignUp_ID, TextInputEditText_SignUp_Password;
    private RelativeLayout RelativeLayout_Continue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextInputEditText_SignUp_Name = findViewById(R.id.TextInputEditText_SignUp_Name);
        TextInputEditText_SignUp_BirthDate = findViewById(R.id.TextInputEditText_SignUp_BirthDate);
        TextInputEditText_SignUp_PhoneNumber = findViewById(R.id.TextInputEditText_SignUp_PhoneNumber);
        TextInputEditText_SignUp_ID = findViewById(R.id.TextInputEditText_SignUp_ID);
        TextInputEditText_SignUp_Password = findViewById(R.id.TextInputEditText_SignUp_Password);

        //Executed when the member registration button is clicked
        RelativeLayout_Continue = findViewById(R.id.RelativeLayout_Continue);
        RelativeLayout_Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get input information
                String userName = TextInputEditText_SignUp_Name.getText().toString();
                String userBirthDate = TextInputEditText_SignUp_BirthDate.getText().toString();
                String userPhoneNumber = TextInputEditText_SignUp_PhoneNumber.getText().toString();
                String userID = TextInputEditText_SignUp_ID.getText().toString();
                String userPassword = TextInputEditText_SignUp_Password.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //Returns whether the communication status was successful
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = (boolean) jsonObject.get("success");
                            //If membership registration is successful
                            if (success) {
                                Toast.makeText(getApplicationContext(), "Registration Succeeded.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            } else {        //If membership registration fails
                                Toast.makeText(getApplicationContext(), "Registration Failed.", Toast.LENGTH_SHORT).show();
                                return;

                            }// End of onResponse method

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                //Make a request using volley as the server
                RegisterRequest registerRequest = new RegisterRequest(userName, userBirthDate, userPhoneNumber, userID, userPassword, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);

            }
        });



    }// End of onCreate method


}/// end of RegisterActivity class
