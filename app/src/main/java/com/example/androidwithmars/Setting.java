package com.example.androidwithmars;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class Setting extends AppCompatActivity {
    private CircleImageView profileImageView;
    private Button closeButton, saveButton;
    private TextView profileChaneBtn;


    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private String userID;

    private Uri imageUri;
    private String myUri = "https://androidwithmars-default-rtdb.firebaseio.com/";
    private StorageTask uploadTask;
    private StorageReference storageProfilePicsRef;

    private TextView profileChangeBtn;
    private EditText edtNamee, edtPhonee, edtEmaill, edtPasswordd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        edtNamee = findViewById(R.id.edt_name);
        edtPhonee = findViewById(R.id.phone_number);
        edtEmaill = findViewById(R.id.email_id);
        edtPasswordd = findViewById(R.id.edt_password);


        user = FirebaseAuth.getInstance().getCurrentUser();

        //Log.d("user Test ----->",user.toString()); //works
        databaseReference = FirebaseDatabase.getInstance().getReference("user");
        userID = user.getUid();
        /*Log.d("db Test ---->",databaseReference.toString()); //works
        userID=user.getUid();
        Log.d("id Test------->",user.getUid()); // id different than doc id in DB
        //Log.d("id2 Test------>",user.getTenantId()); //fail
        Log.d("id3 Test------>",user.getDisplayName()); //no output
        Log.d("id4 Test------>",user.getProviderId()); // work OP -> firebase
        Log.d("id5 test------->",user.zzg());*/


        closeButton = findViewById(R.id.btnClose);
        saveButton = findViewById(R.id.btnSave);
        edtNamee = findViewById(R.id.edt_name);
        databaseReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null) {
                    String email = userProfile.email;
                    String name = userProfile.name;
                    String password = userProfile.password;
                    String phone = userProfile.phone;

                    edtEmaill.setText(email);
                    edtNamee.setText(name);
                    edtPasswordd.setText(password);
                    edtPhonee.setText(phone);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Setting.this, MainActivity.class));
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                updateData();
            }
        });


    }

    private void updateData() {

        databaseReference.child("user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //  Log.d("snapshot Test------->",snapshot.toString()); // @snapshot empty Null value
                User userProfile = (User) snapshot.getValue(User.class); // null value


                //Log.d("userProf Test------->",userProfile.toString()); // Fail
                if (userProfile != null) {
                    String email = userProfile.getEmail();
                    String name = userProfile.getName();
                    String password = userProfile.getPassword();
                    String phone = userProfile.getPhone();

                    edtEmaill.setText(email);
                    edtNamee.setText(name);
                    edtPasswordd.setText(password);
                    edtPhonee.setText(phone);

                    HashMap<String, Object> userMap = new HashMap<>();

                    userMap.put("email", edtEmaill.getText().toString());
                    userMap.put("name", edtNamee.getText().toString());
                    userMap.put("password", edtPasswordd.getText().toString());
                    userMap.put("phone", edtPhonee.getText().toString());


                    databaseReference.child(firebaseAuth.getCurrentUser().getUid()).updateChildren(userMap)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

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


                    //  FirebaseDatabase.getInstance().getReference().child("user");


                   // databaseReference.child(firebaseAuth.getCurrentUser().getUid()).updateChildren(userMap);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Setting.this, "Something wrong", Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void validateAndsave() {
        if (TextUtils.isEmpty(edtNamee.getText().toString())) {
            Toast.makeText(this, "Please Enter Your Name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(edtPhonee.getText().toString())) {
            Toast.makeText(this, "Please Enter Your Phone No. ", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(edtEmaill.getText().toString())) {
            Toast.makeText(this, "Please Enter Your Email id. ", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(edtPasswordd.getText().toString())) {
            Toast.makeText(this, "Please Enter Your password", Toast.LENGTH_SHORT).show();
        } else {
           // User userProfile = snapshot.getValue(User.class);

           /* String email = userProfile.getEmail();
            String name = userProfile.getName();
            String password = userProfile.getPassword();
            String phonenum = userProfile.getPhone();

                    edtEmaill.setText(email);
                    edtNamee.setText(name);
                    edtPasswordd.setText(password);
                    edtPhonee.setText(phonenum);*/
            HashMap<String, Object> userMap = new HashMap<>();

            userMap.put("email", edtNamee.getText().toString());
            userMap.put("name", edtEmaill.getText().toString());
            userMap.put("password", edtPasswordd.getText().toString());
            userMap.put("phone", edtPhonee.getText().toString());

            databaseReference.child(firebaseAuth.getCurrentUser().getUid()).updateChildren(userMap)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

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






         /*   databaseReference.child(firebaseAuth.getCurrentUser().getUid()).updateChildren(userMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(Setting.this,"data sycceeed",Toast.LENGTH_SHORT).show();
                }
            });
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Set your profile");
            progressDialog.setMessage("Please wait, while we are setting your data ");
            progressDialog.show();*/


        }



    }
}



