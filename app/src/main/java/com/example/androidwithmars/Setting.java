package com.example.androidwithmars;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class Setting extends AppCompatActivity {
    private CircleImageView profileImageView;
    private Button closeButton,saveButton;
    private TextView profileChaneBtn;


    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private String userID;

    private Uri imageUri;
    private String myUri="https://androidwithmars-default-rtdb.firebaseio.com/";
    private StorageTask uploadTask;
    private StorageReference storageProfilePicsRef;

    private TextView profileChangeBtn;
    private EditText edtName,edtPhone,edtEmail,edtPassword;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


       firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance("https://androidwithmars-default-rtdb.firebaseio.com/").getReference().child("user");
        storageProfilePicsRef = FirebaseStorage.getInstance().getReference().child("Profile Pic");

        profileImageView = findViewById(R.id.profile_image);

        closeButton = findViewById(R.id.btnClose);
        saveButton = findViewById(R.id.btnSave);
       /* edtName = findViewById(R.id.edt_name);
        edtPhone = findViewById(R.id.phone_number);
        edtEmail = findViewById(R.id.email_id);
        edtPassword = findViewById(R.id.edt_password    );*/

        profileChangeBtn = findViewById(R.id.change_profile_btn);







        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Setting.this,MainActivity.class));
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndsave();
            }
        });

        profileChangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user=FirebaseAuth.getInstance().getCurrentUser();
                databaseReference=FirebaseDatabase.getInstance("https://androidwithmars-default-rtdb.firebaseio.com/").getReference().child("user");
                userID=user.getUid();
              /*  databaseReference.child(firebaseAuth.getCurrentUser().getUid())*/

                edtName = findViewById(R.id.edt_name);
                edtPhone = findViewById(R.id.phone_number);
                edtEmail = findViewById(R.id.email_id);
                edtPassword = findViewById(R.id.edt_password);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0)


                            String email = dataSnapshot.child("email").getValue().toString();
                            String name = dataSnapshot.child("name").getValue().toString();
                            String  password = dataSnapshot.child("password").getValue().toString();
                            String  phone = dataSnapshot.child("phone").getValue().toString();


                            edtEmail.setText(email);
                            edtName.setText(name);
                            edtPassword.setText(password);
                            edtPhone.setText(phone);


                            if (dataSnapshot.hasChild("image"))
                            {
                                String image = dataSnapshot.child("image").getValue().toString();
                                Picasso.get().load(image).into(profileImageView);
                            }


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

               // CropImage.activity().setAspectRatio(1,1).start(Setting.this);
            }
        });

       // getUserinfo();

    }

    private void validateAndsave() {
        if (TextUtils.isEmpty(edtName.getText().toString()))
        {
            Toast.makeText(this, "Please Enter Your Name", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(edtPhone.getText().toString()))
        {
            Toast.makeText(this, "Please Enter Your Phone No. ", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(edtEmail.getText().toString()))
        {
            Toast.makeText(this, "Please Enter Your Email id. ", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(edtPassword.getText().toString()))
        {
            Toast.makeText(this, "Please Enter Your password", Toast.LENGTH_SHORT).show();
        }
        else {
            HashMap<String,Object> userMap = new HashMap<>();
            userMap.put("name",edtName.getText().toString());
            userMap.put("phone",edtPhone.getText().toString());
            userMap.put("email",edtEmail.getText().toString());
            userMap.put("password",edtPassword.getText().toString());

            databaseReference.child(firebaseAuth.getCurrentUser().getUid()).updateChildren(userMap);

            uploadProfileImage();
        }
    }


  /*  private void getUserinfo() {
        databaseReference.child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0)
                {
                    String email = dataSnapshot.child("email").getValue().toString();
                    String  password = dataSnapshot.child("password").getValue().toString();
                    String name = dataSnapshot.child("name").getValue().toString();
                    String  phone = dataSnapshot.child("phone").getValue().toString();


                    edtEmail.setText(email);
                    edtPassword.setText(password);
                    edtName.setText(name);
                    edtPhone.setText(phone);


                    if (dataSnapshot.hasChild("image"))
                    {
                        String image = dataSnapshot.child("image").getValue().toString();
                        Picasso.get().load(image).into(profileImageView);
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode  == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK && data != null)
        {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            imageUri = result.getUri();

            profileImageView.setImageURI(imageUri);
        }
        else {
            Toast.makeText(this, "Error, Try again", Toast.LENGTH_SHORT).show();
        }

    }

    private void uploadProfileImage() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Set your profile");
        progressDialog.setMessage("Please wait, while we are setting your data ");
        progressDialog.show();

        if (imageUri != null)
        {
            final StorageReference fileRef = storageProfilePicsRef
                    .child(firebaseAuth.getCurrentUser().getUid()+ ".jpg");

            uploadTask = fileRef.putFile(imageUri);


            uploadTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {
                    if (!task.isSuccessful())
                    {
                        throw task.getException();
                    }
                    return fileRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful())
                    {
                        Uri downloadUrl =task.getResult();
                        myUri = downloadUrl.toString();

                        HashMap<String, Object> userMap = new HashMap<>();
                        userMap.put("image",myUri);

                        databaseReference.child(firebaseAuth.getCurrentUser().getUid()).updateChildren(userMap);

                        progressDialog.dismiss();


                    }

                }
            });
        }
        else {
            progressDialog.dismiss();
            Toast.makeText(this, "Data update", Toast.LENGTH_SHORT).show();
        }

    }
}

