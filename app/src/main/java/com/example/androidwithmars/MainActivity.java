package com.example.androidwithmars;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private MaterialToolbar toolbar;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    private Button resendCode;
    private TextView verifyMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        final FirebaseUser user = firebaseAuth.getCurrentUser();
      //  if (!Objects.requireNonNull(user).isEmailVerified()) {
/*            verifyMessage.setVisibility(View.VISIBLE);
            resendCode.setVisibility(View.VISIBLE);


            resendCode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(v.getContext(), "Verification Email Sent", Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onFailure: Failed" + e.getMessage());

                        }
                    });
                }
            });

        }*/

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.drawer_open, R.string.drawer_closed);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.documents:
                        Toast.makeText(MainActivity.this, "Documents Selected", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.quiz:
                        Toast.makeText(MainActivity.this, "Quiz Selected", Toast.LENGTH_SHORT).show();
                        Fragment fragment = new Fragment();
                        moveToFragment(fragment);
                        break;



                    case R.id.notes:
                        Toast.makeText(MainActivity.this, "Notes Selected", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.about:
                        Toast.makeText(MainActivity.this, "About Us Selected", Toast.LENGTH_SHORT).show();
                        break;


                    case R.id.help:
                        Toast.makeText(MainActivity.this, "Help Selected", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.videos:
                        Toast.makeText(MainActivity.this, "Videos Selected", Toast.LENGTH_SHORT).show();
                        Fragment fragment1 = new VideoFragment();
                        moveToFragment(fragment1);
                        break;


                    case R.id.settings:
                        Toast.makeText(MainActivity.this, "Settings Selected", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.logout: {

                        Toast.makeText(MainActivity.this, "Logout  Selected", Toast.LENGTH_SHORT).show();
                        NavigationView navigationView = findViewById(R.id.navigationView);
                        navigationView.getMenu().findItem(R.id.logout).setOnMenuItemClickListener(MenuItem ->
                        {
                            logout();
                            return true;

                        });
                        break;
                    }

                    default:
                        break;
                }
                return false;
            }

        });

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, new MainFragment());
        transaction.commit();
    }


    public void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), UserLogin.class));
        finish();
    }

    private void initViews() {
        drawer = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);
        resendCode = findViewById(R.id.resendCode);
        verifyMessage = findViewById(R.id.verifyMessage);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

    }
    private void moveToFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
    }
    }