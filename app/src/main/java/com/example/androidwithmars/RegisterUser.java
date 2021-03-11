package com.example.androidwithmars;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.slider.Slider;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import IntroSlider.SliderActivity;

public class RegisterUser extends AppCompatActivity {
    private static final String TAG = "RegisterUser";
    private EditText mFullName, mEmail, mPassword, mPhone;

    private ImageView img;
    static int PReqcode=1;
    static int REQUESCODE=1;
    private Button btnRegister,browse;
    private TextView signIn;
    private FirebaseAuth firebaseAuth;
//    private Button browse;
   private Uri selectedImageUri;
    private Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        initViews();

        if (firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT>= 22){
                    checkAndRequestPermission();
                }
                else{
                    openGallery();
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String phone = mPhone.getText().toString().trim();
                String name  = mFullName.getText().toString().trim();

//                uploadToFireBase();


                //processinsert();

                if ( TextUtils.isEmpty(email)){
                    mEmail.setError("Enter email");
                    return;
                }
                if ( TextUtils.isEmpty(password)){
                    mPassword.setError("Enter Password");
                    return;
                }
                if ( TextUtils.isEmpty(phone)){
                    mPassword.setError("Enter Phone");
                    return;
                }
                if ( TextUtils.isEmpty(name)){
                    mPassword.setError("Enter Full Name");
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
                            //to do upload user
                            User user1=new User(email,name,password,phone);
                           // User user2=new User(selectedImageUri);
                            updateUserInfo(name,selectedImageUri,firebaseAuth.getCurrentUser());
                            FirebaseDatabase.getInstance()
                                    .getReference("user")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                }
                            });
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
                           startActivity(new Intent(getApplicationContext(), SliderActivity.class));

                        }else {
                            Toast.makeText(RegisterUser.this, "Error..!!" +
                                          task.getException().getMessage()
                                    , Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });


       /*browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withActivity(RegisterUser.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser
                                        (intent, "Please select  Image"), 1);
                            }
                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        }).check();

            }
        });*/


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterUser.this,UserLogin.class);
                startActivity(intent);
            }
        });
    }
    public void updateUserInfo(String name ,Uri selectedImageUri, FirebaseUser currentUser){
        //upload userimage to storage and get url
        StorageReference mstorage = FirebaseStorage.getInstance().getReference().child("user photo");
        StorageReference imageFilepath = mstorage.child(selectedImageUri.getLastPathSegment());
        imageFilepath.putFile(selectedImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //image updated , get image url
                imageFilepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        // URI contains image
                        UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                                .setDisplayName(name)
                                .setPhotoUri(uri)
                                .build();
                        currentUser.updateProfile(profileUpdate)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                    }
                                });
                    }
                });

            }
        });

    }

    private void openGallery() {
        // Open gallery and wait for user permission
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, REQUESCODE);

    }

    private void checkAndRequestPermission() {
        if(ContextCompat.checkSelfPermission(RegisterUser.this, Manifest.permission.READ_EXTERNAL_STORAGE )
                != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(RegisterUser.this,Manifest.permission.READ_EXTERNAL_STORAGE))
            {
                Toast.makeText(RegisterUser.this ,"Please accept for required permission",Toast.LENGTH_SHORT).show();
            }
            else {
                ActivityCompat.requestPermissions(RegisterUser.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PReqcode);

            }

        }
        else{
            openGallery();
        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUESCODE  && data != null) {
            // the user has selected picture, now saved in storage

            selectedImageUri = data.getData();
            img.setImageURI(selectedImageUri);



        }

    }


    private void initViews() {
        mFullName = findViewById(R.id.fullName);
        mEmail = findViewById(R.id.userEmail);
        mPassword = findViewById(R.id.password);
        btnRegister = findViewById(R.id.btnRegister);

        img = findViewById(R.id.userImage);



        mPhone = findViewById(R.id.phone);

        signIn = findViewById(R.id.signIn);
        firebaseAuth = FirebaseAuth.getInstance();

    }


}