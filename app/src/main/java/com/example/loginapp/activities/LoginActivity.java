package com.example.loginapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.loginapp.Web.ApiClient;
import com.example.loginapp.Web.AuthResponse;
import com.example.loginapp.Web.AuthService;
import com.example.loginapp.LoginRequest;
import com.example.loginapp.R;
import com.example.loginapp.UserProfileActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText edittextLoginEmail, edittextLoginPwd;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle("login");

        edittextLoginEmail = findViewById(R.id.edittext_login_email);
        edittextLoginPwd = findViewById(R.id.edittext_login_password);
        progressBar = findViewById(R.id.progressBar);


        //Login User
        Button buttonLogin = findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textEmail = edittextLoginEmail.getText().toString();
                String textPwd = edittextLoginPwd.getText().toString();

                if (TextUtils.isEmpty(textEmail)) {
                    Toast.makeText(LoginActivity.this, "Enter Email bitch", Toast.LENGTH_SHORT).show();
                    edittextLoginEmail.setError("Email required bitch");
                    edittextLoginEmail.requestFocus();}
//                 else if (!Patterns.EMAIL_ADDRESS.matcher(textEmail).matches()) {
//                    Toast.makeText(LoginActivity.this, "Re-Enter Email bitch", Toast.LENGTH_SHORT).show();
//                    edittextLoginEmail.setError("Valid Email required bitch");
//                    edittextLoginEmail.requestFocus();}
                 else if (TextUtils.isEmpty(textPwd)) {
                    Toast.makeText(LoginActivity.this, "Enter Password bitch", Toast.LENGTH_SHORT).show();
                    edittextLoginPwd.setError("Password required bitch");
                    edittextLoginPwd.requestFocus();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    loginuser(textEmail, textPwd);
                }
            }
        });

    }

    private void loginuser(String textEmail, String textPwd) {
        AuthService authService = ApiClient.getRetrofitInstance(null).create(AuthService.class);

        LoginRequest loginRequest = new LoginRequest(textEmail, textPwd);

        authService.login(loginRequest).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(@NonNull Call<AuthResponse> call, @NonNull Response<AuthResponse> response) {
                progressBar.setVisibility(View.GONE); // Hide the progress bar

                if (response.isSuccessful()) {
                    AuthResponse authResponse = response.body();
                    if (authResponse != null) {
                        String token = authResponse.getAccessToken();

                        // Save the token in SharedPreferences
                        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("token", "Bearer " + token);
                        editor.apply();

                        Toast.makeText(LoginActivity.this, "Login successful.", Toast.LENGTH_LONG).show();
                        // Navigate to the User Profile Activity or another destination
                        Intent intent = new Intent(LoginActivity.this, UserProfileActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Login failed. Please check your credentials.", Toast.LENGTH_SHORT).show();
                    Log.e("LoginActivity", "Login failed with code: " + response.code() + " - " + response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<AuthResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE); // Hide the progress bar
                Toast.makeText(LoginActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("LoginActivity", "Login error: " + t.getMessage());
            }


        });

    }
}
