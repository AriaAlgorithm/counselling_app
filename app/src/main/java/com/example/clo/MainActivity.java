package com.example.clo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;



import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
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
        Intent intent= new Intent(MainActivity.this,user_signup.class);
        startActivity(intent);
    }
}