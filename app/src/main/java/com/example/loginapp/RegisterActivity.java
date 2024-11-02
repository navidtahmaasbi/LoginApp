package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    private EditText editTextRegisterFullName, editTextRegisterEmail, editTextRegisterDoB, editTextRegisterMobile, editTextRegisterPwd, editTextRegisterConfirmPwd;
    private ProgressBar progressBar;
    private RadioGroup radioGroupRegisterGender;
    private RadioButton radioButtonRegisterGenderSelected;
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
                String mobileRegex = "[9][0-9][9]";  //first no. can be {6,8,9} and rest 9 nos. can be any no.
                Matcher mobileMatcher;
                Pattern mobilePattern = Pattern.compile(mobileRegex);
                mobileMatcher = mobilePattern.matcher(textMobile);

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
                    editTextRegisterMobile.setError("full name is required bitch");
                    editTextRegisterMobile.requestFocus();

                } else if (textMobile.length() != 10) {
                    Toast.makeText(RegisterActivity.this, "write your goddamn mobile motherfucker", Toast.LENGTH_SHORT).show();
                    editTextRegisterMobile.setError("10 digit mobile bitch");
                    editTextRegisterMobile.requestFocus();

                }else if(!mobileMatcher.find()){
                    Toast.makeText(RegisterActivity.this, "Re Enter Mobile No.", Toast.LENGTH_SHORT).show();
                    editTextRegisterMobile.setError("Mobile No is not valid");
                    editTextRegisterMobile.requestFocus();


                } else if (textPwd.length() < 6) {
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
    private void registerUser(String textFullName, String textEmail, String textDoB, String textGender, String textMobile, String textPwd) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        // Create user Profile
        auth.createUserWithEmailAndPassword(textEmail, textPwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
//                            Toast.makeText(RegisterActivity.this, "user registered succesfuly", Toast.LENGTH_SHORT).show();
                    FirebaseUser firebaseUser = auth.getCurrentUser();

                    //Update Display Name of User
                    UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(textFullName).build();
                    firebaseUser.updateProfile(profileChangeRequest);


                    //Enter User Data into the Firebase Realtime Database.
                    ReadWriteUserDetails writeuserDetails = new ReadWriteUserDetails(textFullName, textDoB, textGender, textMobile);


                    //Extracting User reference from DB for "registered users"
                    DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Registered Users");

                    referenceProfile.child(firebaseUser.getUid()).setValue(writeuserDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                //send verification Email
                                firebaseUser.sendEmailVerification();
                                Toast.makeText(RegisterActivity.this, "user registered succesfuly. Please verify your Email", Toast.LENGTH_SHORT).show();


                                //Open User Profile after successful registration
                                Intent intent = new Intent(RegisterActivity.this, UserProfileActivity.class);
                                // to prevent user from returning back to register activity on pressing back button after registration
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();  // to close register activity
                            } else {
                                try {
                                    throw task.getException();
                                } catch (FirebaseAuthWeakPasswordException e) {
                                    editTextRegisterPwd.setError("Weak Password");
                                    editTextRegisterPwd.requestFocus();
                                } catch (FirebaseAuthInvalidCredentialsException e) {
                                    editTextRegisterPwd.setError("Wrong Email");
                                    editTextRegisterPwd.requestFocus();
                                } catch (FirebaseAuthUserCollisionException e) {
                                    editTextRegisterPwd.setError("User is already existing, use another email");
                                    editTextRegisterPwd.requestFocus();
                                } catch (Exception e) {
                                    Log.e(TAG, e.getMessage());
                                    Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();


                                }
                                progressBar.setVisibility(View.GONE);


                            }


                        }


                    });
                }
            }

        });
    }
}
