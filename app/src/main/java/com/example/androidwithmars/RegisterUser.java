package com.example.androidwithmars;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
    private Button btnRegister,browse;
    private TextView signIn;
    private FirebaseAuth firebaseAuth;
//    private Button browse;
    private Uri filepath;
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
                            FirebaseDatabase.getInstance().getReference("user").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
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


        browse.setOnClickListener(new View.OnClickListener() {
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
        });


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterUser.this,UserLogin.class);
                startActivity(intent);
            }
        });
    }

//    private void uploadToFireBase() {
//
//        ProgressDialog dialog = new ProgressDialog(this);
//        dialog.setTitle("File Uploading");
//        dialog.show();
//
//
//        mFullName = findViewById(R.id.fullName);
//        mEmail = findViewById(R.id.userEmail);
//        mPhone = findViewById(R.id.phone);
//        mPassword=  findViewById(R.id.password);
//
//        FirebaseStorage storage = FirebaseStorage.getInstance();
//        StorageReference uploader = storage.getReference("Image1"+ new Random().nextInt(50));
//        uploader.putFile(filepath)
//                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                        uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                            @Override
//                            public void onSuccess(Uri uri) {
//                                dialog.dismiss();
//                                FirebaseDatabase  db = FirebaseDatabase.getInstance();
//                                DatabaseReference root =  db.getReference("user");
//
//                                DataHolder obj = new DataHolder(mFullName.getText().toString(),
//                                        mEmail.getText().toString(),
//                                        mPhone.getText().toString(),
//                                        uri.toString());
//                                root.child(mEmail.getText().toString()).setValue(obj);
//
//                                Toast.makeText(RegisterUser.this, "Uploaded", Toast.LENGTH_SHORT).show();
//
//                            }
//                        });
//
//
//                    }
//                })
//                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
//
//                        float percent = (100*snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
//                          dialog.setMessage("Uploaded :" +(int)percent+ " % ");
//
//                    }
//                });
//
//
//
//
//
//
//
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {

            filepath = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filepath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                img.setImageBitmap(bitmap);
            }
            catch (Exception e){
            }


        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void initViews() {
        mFullName = findViewById(R.id.fullName);
        mEmail = findViewById(R.id.userEmail);
        mPassword = findViewById(R.id.password);
        btnRegister = findViewById(R.id.btnRegister);

        img = findViewById(R.id.imageView);
        browse = findViewById(R.id.btnBrowse);


        mPhone = findViewById(R.id.phone);

        signIn = findViewById(R.id.signIn);
        firebaseAuth = FirebaseAuth.getInstance();

    }


   /* private void processinsert() {
        Map<String,Object> map=new HashMap<>();
        map.put("email",mEmail.getText().toString());
        map.put("password",mPassword.getText().toString());
        map.put("name",mFullName.getText().toString());
        map.put("phone",mPhone.getText().toString());


//        uploadToFireBase();

        FirebaseDatabase.getInstance("https://androidwithmars-default-rtdb.firebaseio.com/").getReference().child("user").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        mEmail.setText("");
                        mPassword.setText("");
                        mFullName.setText("");
                        mPhone.setText("");

                        Toast.makeText(getApplicationContext(),"Inserted Successfully",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(getApplicationContext(),"Could not insert",Toast.LENGTH_LONG).show();
                    }
                });

    }*/
}