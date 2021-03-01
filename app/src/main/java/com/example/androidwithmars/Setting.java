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

    private  TextView profileChangeBtn;
   //private EditText edtName,edtPhone,edtEmail,edtPassword;


   /* TextView edtNamee;
    TextView edtPhonee;
    TextView edtEmaill;
    TextView edtPasswordd;*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


       user = FirebaseAuth.getInstance().getCurrentUser();

        //Log.d("user Test ----->",user.toString()); //works
        databaseReference = FirebaseDatabase.getInstance().getReference("user");
        userID=user.getUid();
        /*Log.d("db Test ---->",databaseReference.toString()); //works
        userID=user.getUid();
        Log.d("id Test------->",user.getUid()); // id different than doc id in DB
        //Log.d("id2 Test------>",user.getTenantId()); //fail
        Log.d("id3 Test------>",user.getDisplayName()); //no output
        Log.d("id4 Test------>",user.getProviderId()); // work OP -> firebase
        Log.d("id5 test------->",user.zzg());*/


       // profileImageView = findViewById(R.id.profile_image);

        closeButton = findViewById(R.id.btnClose);
        saveButton = findViewById(R.id.btnSave);
       final EditText edtNamee = findViewById(R.id.edt_name);
        final EditText edtPhonee = findViewById(R.id.phone_number);
        final EditText edtEmaill = findViewById(R.id.email_id);
        final EditText edtPasswordd = findViewById(R.id.edt_password );

        databaseReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User  userProfile =snapshot.getValue(User.class);

                if( userProfile!=null)
                {
                    String email=userProfile.email;
                    String name= userProfile.name;
                    String password=userProfile.password;
                    String phone=userProfile.phone;

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
        /*
       final TextView edtNamee = findViewById(R.id.edt_name);
        final TextView edtPhonee = findViewById(R.id.phone_number);
        final TextView edtEmaill = findViewById(R.id.email_id);
        final TextView edtPasswordd = findViewById(R.id.edt_password );*/
        /*
        databaseReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("snapshot Test------->",snapshot.toString()); // @snapshot empty Null value
                User userProfile=snapshot.getValue(User.class); // null value
                Log.d("userProf Test------->",userProfile.toString()); // Fail
                if(userProfile!=null){
                    String email=userProfile.email;
                    String name=userProfile.name;
                    String password=userProfile.password;
                    String phonenum=userProfile.phone;

                    edtEmaill.setText(email);
                    edtNamee.setText(name);
                    edtPasswordd.setText(password);
                    edtPhonee.setText(phonenum);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Setting.this,"Something wrong",Toast.LENGTH_SHORT).show();

            }
        });
*/
        // updateData();
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Setting.this,MainActivity.class));
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              updateData();
            }
        });




    }

    private void updateData()
    {

        databaseReference.child("user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
              //  Log.d("snapshot Test------->",snapshot.toString()); // @snapshot empty Null value
                User userProfile=(User) snapshot.getValue(User.class); // null value

                final EditText edtNamee = findViewById(R.id.edt_name);
                final EditText edtPhonee = findViewById(R.id.phone_number);
                final EditText edtEmaill = findViewById(R.id.email_id);
                final EditText edtPasswordd = findViewById(R.id.edt_password );
                //Log.d("userProf Test------->",userProfile.toString()); // Fail
                if(userProfile!=null){
                    String email=userProfile.getEmail();
                    String name=userProfile.getName();
                    String password=userProfile.getPassword();
                    String phonenum=userProfile.getPhone();

                    /*edtEmaill.setText(email);
                    edtNamee.setText(name);
                    edtPasswordd.setText(password);
                    edtPhonee.setText(phonenum);*/

                    HashMap<String,Object> userMap = new HashMap<>();

                    userMap.put("email",edtEmaill.getText().toString());
                    userMap.put("name",edtNamee.getText().toString());
                    userMap.put("password",edtPasswordd.getText().toString());
                    userMap.put("phone",edtPhonee.getText().toString());




                  //  FirebaseDatabase.getInstance().getReference().child("user");


                    databaseReference.child(firebaseAuth.getCurrentUser().getUid()).updateChildren(userMap);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Setting.this,"Something wrong",Toast.LENGTH_SHORT).show();

            }
        });
/*
        databaseReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("snapshot Test------->",snapshot.toString()); // @snapshot empty Null value
                User userProfile=(User) snapshot.getValue(User.class); // null value
                Log.d("userProf Test------->",userProfile.toString()); // Fail
                if(userProfile!=null){
                    String email=userProfile.email;
                    String name=userProfile.name;
                    String password=userProfile.password;
                    String phonenum=userProfile.phone;

                    edtEmaill.setText(email);
                    edtNamee.setText(name);
                    edtPasswordd.setText(password);
                    edtPhonee.setText(phonenum);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Setting.this,"Something wrong",Toast.LENGTH_SHORT).show();

            }
        });*/

    }

    /*private void validateAndsave() {
      /*  if (TextUtils.isEmpty(edtName.getText().toString()))
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

            userMap.put("email",edt.getText().toString());
            userMap.put("name",edtName.getText().toString());
            userMap.put("password",edtPassword.getText().toString());
            userMap.put("phone",edtPhone.getText().toString());




            FirebaseDatabase.getInstance().getReference().child("user");

            databaseReference.child(firebaseAuth.getCurrentUser().getUid()).updateChildren(userMap);

            //uploadProfileImage();

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

