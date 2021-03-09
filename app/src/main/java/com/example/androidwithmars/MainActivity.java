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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        final FirebaseUser user = firebaseAuth.getCurrentUser();


//        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.drawer_open, R.string.drawer_closed);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.home:
                        Toast.makeText(MainActivity.this, "Home Selected Selected", Toast.LENGTH_SHORT).show();
                        Intent next2= new Intent(MainActivity.this, MainActivity.class);
                        startActivity(next2);
                        break;



                    case R.id.InterviewQuestion:
                        Toast.makeText(MainActivity.this, "Interview Question  Selected", Toast.LENGTH_SHORT).show();
                        Intent next1= new Intent(MainActivity.this, InterviewQuestion.class);
                        startActivity(next1);
                        break;

                    case R.id.setting:
                        Toast.makeText(MainActivity.this, "Setting Selected", Toast.LENGTH_SHORT).show();
                        Intent next= new Intent(MainActivity.this, Setting.class);
                        startActivity(next);
                        break;







                    case R.id.about:

                        Toast.makeText(MainActivity.this, "About Us Selected", Toast.LENGTH_SHORT).show();
                        Fragment fragment4 = new About_Us1();
                        moveToFragment(fragment4);
                        break;





                    case R.id.help:
                        Toast.makeText(MainActivity.this, "Help Selected", Toast.LENGTH_SHORT).show();
                        Fragment fragment5 = new Help1();
                        moveToFragment(fragment5);
                        break;






                    case R.id.logout: {

//                        Toast.makeText(MainActivity.this, "Logout  Selected", Toast.LENGTH_SHORT).show();
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

            private void moveToFragment(Fragment fragment){
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
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
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

    }
}