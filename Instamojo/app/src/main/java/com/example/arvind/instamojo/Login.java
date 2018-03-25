package com.example.arvind.instamojo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class Login extends AppCompatActivity {

    EditText uname,pword;
    Button login,signup;
    String un,pw;
    String responseString ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        uname=(EditText)findViewById(R.id.uname);
        pword=(EditText)findViewById(R.id.pword);
        login=(Button)findViewById(R.id.login);
        signup=(Button)findViewById(R.id.signup);


        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                un=uname.getText().toString();
                pw=pword.getText().toString();


                try {
                    RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
                    String URL = "http://192.168.43.233:9999/signup";
                    JSONObject jsonBody = new JSONObject();
                    jsonBody.put("email", un);
                    jsonBody.put("password", pw);
                    final String requestBody = jsonBody.toString();

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Toast.makeText(Login.this,"message1"+response,Toast.LENGTH_LONG).show();
                            if(response.equals("201")){
                                Intent intent=new Intent(Login.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }

                            Log.i("VOLLEY", response);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("VOLLEY", error.toString());
                            Login.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    Toast.makeText(Login.this,"Sign Failed",Toast.LENGTH_LONG).show();
                                    //  Toast.makeText(Login.this, "Hello", Toast.LENGTH_SHORT).show();
                                    //        Toast.makeText(Login.this,"message"+responseString,Toast.LENGTH_LONG).show();
                                }
                            });

                        }

                    }) {
                        @Override
                        public String getBodyContentType() {
                            return "application/json; charset=utf-8";
                        }

                        @Override
                        public byte[] getBody() throws AuthFailureError {
                            try {
                                return requestBody == null ? null : requestBody.getBytes("utf-8");
                            } catch (UnsupportedEncodingException uee) {
                                VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                                return null;
                            }
                        }

                        @Override
                        protected Response<String> parseNetworkResponse(NetworkResponse response) {
                            responseString = "";
                            if (response != null) {
                                responseString = String.valueOf(response.statusCode);
                                Login.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                        //  Toast.makeText(Login.this, "Hello", Toast.LENGTH_SHORT).show();
                                        //        Toast.makeText(Login.this,"message"+responseString,Toast.LENGTH_LONG).show();
                                    }
                                });

                                // can get more details such as response.headers
                            }
//                            Toast.makeText(Login.this,"Success",Toast.LENGTH_LONG).show();
                            return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));

                        }
                    };

                    requestQueue.add(stringRequest);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });


        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                un=uname.getText().toString();
                pw=pword.getText().toString();


                try {
                    RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
                    String URL = "http://192.168.43.233:9999/login";
                    JSONObject jsonBody = new JSONObject();
                    jsonBody.put("email", un);
                    jsonBody.put("password", pw);
                    final String requestBody = jsonBody.toString();

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                          // Toast.makeText(Login.this,"message1"+response,Toast.LENGTH_LONG).show();
                            if(response.equals("201")){
                                Intent intent=new Intent(Login.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }

                            Log.i("VOLLEY", response);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("VOLLEY", error.toString());
                            Login.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                        Toast.makeText(Login.this,"Login Failed",Toast.LENGTH_LONG).show();
                                        //  Toast.makeText(Login.this, "Hello", Toast.LENGTH_SHORT).show();
                                        //        Toast.makeText(Login.this,"message"+responseString,Toast.LENGTH_LONG).show();
                                    }
                                });

                            }

                    }) {
                        @Override
                        public String getBodyContentType() {
                            return "application/json; charset=utf-8";
                        }

                        @Override
                        public byte[] getBody() throws AuthFailureError {
                            try {
                                return requestBody == null ? null : requestBody.getBytes("utf-8");
                            } catch (UnsupportedEncodingException uee) {
                                VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                                return null;
                            }
                        }

                        @Override
                        protected Response<String> parseNetworkResponse(NetworkResponse response) {
                             responseString = "";
                            if (response != null) {
                                responseString = String.valueOf(response.statusCode);
                                Login.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                      //  Toast.makeText(Login.this, "Hello", Toast.LENGTH_SHORT).show();
                                //        Toast.makeText(Login.this,"message"+responseString,Toast.LENGTH_LONG).show();
                                    }
                                });

                                // can get more details such as response.headers
                            }
//                            Toast.makeText(Login.this,"Success",Toast.LENGTH_LONG).show();
                            return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));

                        }
                    };

                    requestQueue.add(stringRequest);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });


    }
}
