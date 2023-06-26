package com.raodmapplan.mapplan;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    //Server URL setting (PHP file integration)
    final static private String URL = "http://192.168.68.172.com.raodmapplan.mapplan/Register.php";
    private Map<String, String> map;

    public RegisterRequest(String userName, String userBirthDate, String userPhoneNumber, String userID, String userPassword, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userName", userName);
        map.put("userBirthDate", userBirthDate);
        map.put("userPhoneNumber", userPhoneNumber);
        map.put("userID", userID);
        map.put("userPassword", userPassword);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }


}// End of RegisterRequest class
