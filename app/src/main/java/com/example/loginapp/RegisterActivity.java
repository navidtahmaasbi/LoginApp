package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private EditText editTextRegisterFullName, editTextRegisterEmail, editTextRegisterDoB, editTextRegisterMobile, editTextRegisterPwd, editTextRegisterConfirmPwd;
    private ProgressBar progressBar;
    private RadioGroup radioGroupRegisterGender;
    private RadioButton radioButtonRegisterGenderSelected;
    private DatePickerDialog picker;
    private static final String TAG = "RegisterActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Register");
        Toast.makeText(RegisterActivity.this, "live motherfucker", Toast.LENGTH_SHORT).show();


        progressBar = findViewById(R.id.progressBar);
        editTextRegisterFullName = findViewById(R.id.editText_register_fullname);
        editTextRegisterEmail = findViewById(R.id.editText_register_email);
        editTextRegisterDoB = findViewById(R.id.editText_register_dob);
        editTextRegisterMobile = findViewById(R.id.editText_register_mobile);
        editTextRegisterPwd = findViewById(R.id.editText_register_password);
        editTextRegisterConfirmPwd = findViewById(R.id.editText_register_confirm_password);

        //Radio button for Gender
        radioGroupRegisterGender = findViewById(R.id.radio_group_register_gender);
        radioGroupRegisterGender.clearCheck();

        //setting up DatePicker on Edittext
        editTextRegisterDoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                // Date Picker Dialog
                picker = new DatePickerDialog(RegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayofMonth) {
                        editTextRegisterDoB.setText(dayofMonth + "/" + (month + 1) + "/" + year);

                    }

                }, year, day, month);
                picker.show();

            }
        });


        Button buttonRegister = findViewById(R.id.button_register);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedGenderId = radioGroupRegisterGender.getCheckedRadioButtonId();
                radioButtonRegisterGenderSelected = findViewById(selectedGenderId);

                //obtain the entered data
                String textFullName = editTextRegisterFullName.getText().toString();
                String textEmail = editTextRegisterEmail.getText().toString();
                String textDoB = editTextRegisterDoB.getText().toString();
                String textMobile = editTextRegisterMobile.getText().toString();
                String textPwd = editTextRegisterPwd.getText().toString();
                String textConfirmPwd = editTextRegisterConfirmPwd.getText().toString();
                String textGender;  //value will be obtained only after selection


                //Validate Mobile Number using Matcher and Pattern (Regular Expression)
//                String mobileRegex = "\\+98[0-9]{10}"; //first no. can be {6,8,9} and rest 9 nos. can be any no.
//                Matcher mobileMatcher;
//                Pattern mobilePattern = Pattern.compile(mobileRegex);
//                mobileMatcher = mobilePattern.matcher(textMobile);

                if (TextUtils.isEmpty(textFullName)) {
                    Toast.makeText(RegisterActivity.this, "write your goddamn name motherfucker", Toast.LENGTH_SHORT).show();
                    editTextRegisterFullName.setError("full name is required bitch");
                    editTextRegisterFullName.requestFocus();

                } else if (TextUtils.isEmpty(textEmail)) {
                    Toast.makeText(RegisterActivity.this, "write your goddamn email motherfucker", Toast.LENGTH_SHORT).show();
                    editTextRegisterEmail.setError("email is required bitch");
                    editTextRegisterEmail.requestFocus();

                } else if (!Patterns.EMAIL_ADDRESS.matcher(textEmail).matches()) {
                    Toast.makeText(RegisterActivity.this, "write your goddamn email again motherfucker", Toast.LENGTH_SHORT).show();
                    editTextRegisterEmail.setError("valid email is required bitch");
                    editTextRegisterEmail.requestFocus();

                } else if (TextUtils.isEmpty(textDoB)) {
                    Toast.makeText(RegisterActivity.this, "write your goddamn date of birth motherfucker", Toast.LENGTH_SHORT).show();
                    editTextRegisterDoB.setError("date of birth is required bitch");
                    editTextRegisterDoB.requestFocus();

                } else if (radioGroupRegisterGender.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(RegisterActivity.this, "select your goddamn gender motherfucker", Toast.LENGTH_SHORT).show();
                    editTextRegisterFullName.setError("gender is required bitch");
                    editTextRegisterFullName.requestFocus();

                } else if (TextUtils.isEmpty(textMobile)) {
                    Toast.makeText(RegisterActivity.this, "write your goddamn mobile motherfucker", Toast.LENGTH_SHORT).show();
                    editTextRegisterMobile.setError("valid mobile is required bitch");
                    editTextRegisterMobile.requestFocus();

                }
//                else if (textMobile.length() != 10) {
//                    Toast.makeText(RegisterActivity.this, "write your goddamn mobile motherfucker", Toast.LENGTH_SHORT).show();
//                    editTextRegisterMobile.setError("10 digit mobile bitch");
//                    editTextRegisterMobile.requestFocus();}

//                else if(!mobileMatcher.find()){
//                    Toast.makeText(RegisterActivity.this, "Re Enter Mobile No.", Toast.LENGTH_SHORT).show();
//                    editTextRegisterMobile.setError("Mobile No is not valid");
//                    editTextRegisterMobile.requestFocus();}

                else if (textPwd.length() < 6) {
                    Toast.makeText(RegisterActivity.this, "6 digit password motherfucker", Toast.LENGTH_SHORT).show();
                    editTextRegisterPwd.setError("weak password bitch");
                    editTextRegisterPwd.requestFocus();

                } else if (TextUtils.isEmpty(textPwd)) {
                    Toast.makeText(RegisterActivity.this, "write your goddamn name motherfucker", Toast.LENGTH_SHORT).show();
                    editTextRegisterPwd.setError("full name is required bitch");
                    editTextRegisterPwd.requestFocus();

                } else if (TextUtils.isEmpty(textConfirmPwd)) {
                    Toast.makeText(RegisterActivity.this, "write your goddamn password again motherfucker", Toast.LENGTH_SHORT).show();
                    editTextRegisterPwd.setError("valid password is required bitch");
                    editTextRegisterPwd.requestFocus();

                } else if (!textPwd.equals(textConfirmPwd)) {
                    Toast.makeText(RegisterActivity.this, "Same password  motherfucker", Toast.LENGTH_SHORT).show();
                    editTextRegisterPwd.setError("valid password is required bitch");
                    editTextRegisterPwd.requestFocus();
                    //delete entered passwords
                    editTextRegisterPwd.clearComposingText();
                    editTextRegisterConfirmPwd.clearComposingText();

                } else {
                    textGender = radioButtonRegisterGenderSelected.getText().toString();
                    progressBar.setVisibility(View.VISIBLE);
                    registerUser(textFullName, textEmail, textDoB, textMobile, textGender, textPwd);


                }


            }

        });
    }

    //Register user by the given credentials
    private void registerUser(String textFullName, String textEmail, String textDoB, String textMobile, String textGender, String textPwd) {

        AuthService authService = ApiClient.getRetrofitInstance().create(AuthService.class);

        RegisterRequest registerRequest = new RegisterRequest(textFullName, textEmail, textDoB, textGender, textMobile, textPwd);

        authService.register(registerRequest).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(@NonNull Call<AuthResponse> call, @NonNull Response<AuthResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    AuthResponse authResponse = response.body();
                    assert authResponse != null;
                    String token = authResponse.getAccessToken();

                    SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("token", "Bearer " + token);
                    editor.apply();
                    Toast.makeText(RegisterActivity.this, "User registered successfully.", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(RegisterActivity.this, UserProfileActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                } else {
                    Log.e("RegisterActivity", "Registration failed with code: " + response.code() + " - " + response.message());
                    Toast.makeText(RegisterActivity.this, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<AuthResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(RegisterActivity.this, "Error:" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}


