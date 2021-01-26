package com.example.androidwithmars;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class RegisterUser extends AppCompatActivity {
    private static final String TAG = "RegisterUser";
    private EditText fullName, mEmail, mPassword;
    private Button btnRegister;
    private TextView signIn;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        initViews();

        if (firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();

        }

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                if ( TextUtils.isEmpty(email)){
                    mEmail.setError("Enter email");
                    return;
                }
                if ( TextUtils.isEmpty(password)){
                    mPassword.setError("Enter Password");
                    return;
                }
                if (password.length()<6){
                    mPassword.setError("Password Must have 6 Characters");
                    return;
                }


                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            //verify user
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(RegisterUser.this, "Verification Email Sent", Toast.LENGTH_SHORT).show();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: Failed"+e.getMessage());

                                }
                            });




                            Toast.makeText(RegisterUser.this, "Profile Created", Toast.LENGTH_SHORT).show();

                           startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }else {
                            Toast.makeText(RegisterUser.this, "Error..!!"+
                                          task.getException().getMessage()
                                    , Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterUser.this,UserLogin.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        fullName = findViewById(R.id.fullName);
        mEmail = findViewById(R.id.userEmail);
        mPassword = findViewById(R.id.password);
        btnRegister = findViewById(R.id.btnRegister);
        signIn = findViewById(R.id.signIn);
        firebaseAuth = FirebaseAuth.getInstance();
    }
}