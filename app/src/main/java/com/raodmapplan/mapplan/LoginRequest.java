package com.raodmapplan.mapplan;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    //this is the URl
    final static private String URL = "http://192.168.68.172.com.raodmapplan.mapplan/Login.php";
    private Map<String, String> map;

    public LoginRequest(String userID, String userPassword, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID", userID);
        map.put("userPassword", userPassword);
    }// LoginRequest method

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;

    } // Map method


}// End of LoginRequest class
