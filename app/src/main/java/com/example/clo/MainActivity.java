package com.example.clo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //Button button = findViewById(R.id.Rate);
        //button.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View v) {

            //}
        //});
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }
    public void SignUpPage(View v){
        Intent intent= new Intent(MainActivity.this,counselor_signup.class);
        startActivity(intent);
    }
    public void clientSignUpPage(View v){
        Intent intent= new Intent(MainActivity.this,client_SignUp.class);
        startActivity(intent);
    }
}