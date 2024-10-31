package com.example.loginapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private EditText editTextRegisterFullName, editTextRegisterEmail, editTextRegisterDoB, editTextRegisterMobile, editTextRegisterPwd, editTextRegisterConfirmPwd;
    private ProgressBar progressBar;
    private RadioGroup radioGroupRegisterGender;
    private RadioButton radioButtonRegisterGenderSelected;
    private static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
}


//Register user by the given credentials
private void registerUser(String textFullName, String textEmail, String textDoB, String textGender, String textMobile, String textPwd)

FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(textEmail, textPwd).addonCompleteListener(RegisterActivity .this,new OnCompleteListener<authResult>() {
    @Override
    public void onComplete (@NonNull Task < AuthResult > task)
})




