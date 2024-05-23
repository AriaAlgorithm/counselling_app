package com.example.clo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class user_signup extends AppCompatActivity {
    EditText Username, Email, Pass, EPass, Problems;
    Button SignUp;
    String name, email, password, confirmPassword, problems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_signup);

        Username = findViewById(R.id.Name);
        Email = findViewById(R.id.Email);
        Problems = findViewById(R.id.Problems);
        Pass = findViewById(R.id.Password);
        EPass = findViewById(R.id.ConfirmPassword);
        SignUp = findViewById(R.id.SignUp);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = Username.getText().toString();
                email = Email.getText().toString();
                problems = Problems.getText().toString();
                password = Pass.getText().toString();
                confirmPassword = EPass.getText().toString();

                if (password.equals(confirmPassword)) {
                    registerUser();
                } else {
                    Toast.makeText(user_signup.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void registerUser() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://lamp.ms.wits.ac.za/home/s2689889/Users_Sign_Up.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("success")) {
                            Toast.makeText(user_signup.this, "Signup successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(user_signup.this, user_login.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(user_signup.this, "Signup failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(user_signup.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("email", email);
                params.put("problems", problems);
                params.put("password", password);
                return params;
            }
        };
        queue.add(stringRequest);
    }
}
