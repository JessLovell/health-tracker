package com.example.healthtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        CookieManager cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);

        sharedPref= this.getSharedPreferences(getString(R.string.logged_in_user), Context.MODE_PRIVATE);
    }

    public void onLoginSubmit(View v){

        //login to account,
        //get cookie
        //store cookie in shared pref

        String username = ((EditText)findViewById(R.id.editText)).getText().toString();
        String password = ((EditText)findViewById(R.id.editText2)).getText().toString();
        loginToServer(username, password);
    }

    public void loginToServer(final String username, final String password){

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://stormy-bayou-86086.herokuapp.com/perform_login";

        Log.i("TAG", "About to send");
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Login", "GOT RESPONSE " + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Login", error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String>  params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                // since we don't know which of the two underlying network vehicles
                // will Volley use, we have to handle and store session cookies manually
                Log.i("response",response.headers.toString());
//                Map<String, String> responseHeaders = response.headers;
//                String rawCookies = responseHeaders.get("Set-Cookie");

                //save to shared pref
//                SharedPreferences.Editor editor = sharedPref.edit();
//                editor.putString(getString(R.string.Stored_cookie), rawCookies);
//                editor.commit();
//
//
//                Log.i("cookies", sharedPref.getString(getString(R.string.Stored_cookie), "cocoloco"));
                return super.parseNetworkResponse(response);
            }

        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
