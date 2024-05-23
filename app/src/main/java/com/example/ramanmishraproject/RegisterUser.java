package com.example.ramanmishraproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class RegisterUser extends AppCompatActivity {

    private TextInputEditText textInputEditTextEmail, textInputEditTextCreatePassword, textInputEditTextConfirmPassword;
    private MaterialButton buttonRegister;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private boolean doubleBackToExitPressedOnce = false;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Initialize UI components
        textInputEditTextEmail = findViewById(R.id.textInputEditTextEmail);
        textInputEditTextCreatePassword = findViewById(R.id.textInputEditTextCreatePassword);
        textInputEditTextConfirmPassword = findViewById(R.id.textInputEditTextConfirmPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
        progressBar = findViewById(R.id.progressBar);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String email = textInputEditTextEmail.getText().toString().trim();
        String password = textInputEditTextCreatePassword.getText().toString().trim();
        String confirmPassword = textInputEditTextConfirmPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            textInputEditTextEmail.setError("Email is required");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            textInputEditTextCreatePassword.setError("Password is required");
            return;
        }

        if (password.length() < 6) {
            textInputEditTextCreatePassword.setError("Password must be at least 6 characters");
            return;
        }

        if (!password.equals(confirmPassword)) {
            textInputEditTextConfirmPassword.setError("Passwords do not match");
            return;
        }

        // Show the progress bar
        progressBar.setVisibility(View.VISIBLE);

        // Create user with email and password
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(RegisterUser.this, HomeActivity.class);
                        startActivity(intent);
                        Toast.makeText(RegisterUser.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        // If registration fails, display a message to the user.
                        try {
                            throw task.getException();
                        } catch (FirebaseAuthWeakPasswordException e) {
                            textInputEditTextCreatePassword.setError("Weak password");
                            textInputEditTextCreatePassword.requestFocus();
                        } catch (FirebaseAuthInvalidCredentialsException e) {
                            textInputEditTextEmail.setError("Invalid email");
                            textInputEditTextEmail.requestFocus();
                        } catch (FirebaseAuthUserCollisionException e) {
                            textInputEditTextEmail.setError("User already exists");
                            textInputEditTextEmail.requestFocus();
                        } catch (Exception e) {
                            Toast.makeText(RegisterUser.this, "Registration failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                    // Hide the progress bar
                    progressBar.setVisibility(View.GONE);
                });
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
