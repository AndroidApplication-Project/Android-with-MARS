package com.example.androidwithmars;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
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
    private Toolbar toolbar;
    private Uri selectedImageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar actionbar=getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_nav_menu);

        edtNamee = findViewById(R.id.edt_name);
        edtPhonee = findViewById(R.id.phone_number);
        edtEmaill = findViewById(R.id.email_id);
        edtPasswordd = findViewById(R.id.edt_password);
        profileChaneBtn=findViewById(R.id.change_profile_btn);



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


                changedata();
            }
        });



    }

    private void changedata(){


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference = FirebaseDatabase.getInstance().getReference("user");
                userID = user.getUid();
                String uemail, uname, upassword, uphone;

                uemail = edtEmaill.getText().toString();
                uname = edtNamee.getText().toString();
                upassword = edtPasswordd.getText().toString();
                uphone = edtPhonee.getText().toString();
                User userdata = new User(uemail, uname, upassword, uphone);
                FirebaseDatabase.getInstance().getReference("user").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .setValue(userdata);
                // databaseReference.setValue(userdata);
                Toast.makeText(Setting.this, "update succssfully", Toast.LENGTH_SHORT).show();
                //Glide.with(this).load(currentUser.getPhotoUrl()).into(navImage);
                finish();


            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                Intent next2= new Intent(Setting.this, MainActivity.class);
                startActivity(next2);
                break;


        }
        return super.onOptionsItemSelected(item);
    }




}



