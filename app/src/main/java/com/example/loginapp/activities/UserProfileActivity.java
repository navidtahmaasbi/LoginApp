package com.example.loginapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.loginapp.R;
import com.example.loginapp.Web.ApiClient;
import com.example.loginapp.Web.AuthService;
import com.example.loginapp.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileActivity extends AppCompatActivity {
    private EditText editTextName, editTextEmail;
    private ImageView imageViewProfilePicture;
    private Button buttonSaveChanges, buttonLogout;
    private AuthService authService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        imageViewProfilePicture = findViewById(R.id.imageViewProfilePicture);
        buttonSaveChanges = findViewById(R.id.buttonSaveChanges);
        buttonLogout = findViewById(R.id.buttonLogout);

        // Initialize AuthService
        String authToken = getSharedPreferences("MyAppPrefs", MODE_PRIVATE).getString("auth_token", null);
        authService = ApiClient.getRetrofitInstance(authToken).create(AuthService.class);

        // Load user profile
        loadUserProfile();

        // Set up save button to update profile
        buttonSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfileChanges();
            }
        });

        // Set up log out button to clear session and go to login screen
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }

    private void loadUserProfile() {
        // API call to fetch user profile
        authService.getUserProfile(null).enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    User user = response.body();
                    editTextName.setText(user.getName());
                    editTextEmail.setText(user.getEmail());
                    // Optional: Load profile picture if available
                } else {
                    Toast.makeText(UserProfileActivity.this, "Failed to load profile", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(UserProfileActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveProfileChanges() {
        String updatedName = editTextName.getText().toString();
        String updatedEmail = editTextEmail.getText().toString();
        String authToken = getSharedPreferences("MyAppPrefs", MODE_PRIVATE).getString("auth_token", null);

        User updatedUser = new User(updatedName, updatedEmail); // Ensure User model has this constructor

        authService.updateUserProfile(authToken,updatedUser).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(UserProfileActivity.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UserProfileActivity.this, "Failed to update profile", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(UserProfileActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void logout() {
        // Clear stored auth token
        SharedPreferences.Editor editor = getSharedPreferences("MyAppPrefs", MODE_PRIVATE).edit();
        editor.remove("auth_token");
        editor.apply();

        // Go to LoginActivity
        Intent intent = new Intent(UserProfileActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
