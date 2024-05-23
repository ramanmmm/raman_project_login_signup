package com.example.ramanmishraproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class splashScreen extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_LENGTH = 2000; // 2000 ms = 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Check if the user is logged in
                SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
                boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

                Intent intent;
                if (isLoggedIn) {
                    // User is logged in, navigate to LoginActivity
                    intent = new Intent(splashScreen.this, HomeActivity.class);
                } else {
                    // User is not logged in, navigate to LoginActivity
                    intent = new Intent(splashScreen.this, LoginActivity.class);
                }
                startActivity(intent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
