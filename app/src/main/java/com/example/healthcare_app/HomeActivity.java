package com.example.healthcare_app;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {


    /*
    BottomNavigationView bottomNavigationView;

    profileFragment profileFragment=new profileFragment();
    settingFragment settingFragment=new settingFragment();
    logoutFragment logoutFragment=new logoutFragment();
    homeFragment homeFragment=new homeFragment();

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;                                     */




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);







/*
        drawerLayout=findViewById(R.id.drawerlayout);
        navigationView=findViewById(R.id.nevigationview);
        toolbar=findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);


        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_open,R.string.navigation_Close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id=item.getItemId();
                if(id==R.id.home_menu){
                    Toast.makeText(HomeActivity.this, "Home", Toast.LENGTH_SHORT).show();                                 */
                  /*  loadfragment(new homeFragment());*/
      /*              getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,homeFragment).commit();
                    return  true;
                }
                else if(id==R.id.logout_menu){
                    loadfragment(new logoutFragment());
                    Toast.makeText(HomeActivity.this, "Logout Page", Toast.LENGTH_SHORT).show();

                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
                                                                                                  */












/*


        //Bottom nevigation view process
        bottomNavigationView =findViewById((R.id.pj));

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homebutton:
                        Toast.makeText(HomeActivity.this, "Home ", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,homeFragment).commit();
                        return  true;
                    case R.id.account:
                        Toast.makeText(HomeActivity.this, "Account ", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,profileFragment).commit();
                        return  true;
                    case R.id.setting:
                        Toast.makeText(HomeActivity.this, "Setting ", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,settingFragment).commit();
                        return  true;
                    case R.id.logout:
                        Toast.makeText(HomeActivity.this, "Logout Page ", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,logoutFragment).commit();
                        return  true;

                }
                return false;
            }
        });*/




















        //real code

        SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username=sharedPreferences.getString("username","").toString();
        Toast.makeText(getApplicationContext(), "Welcome "+username, Toast.LENGTH_SHORT).show();



        CardView exit=findViewById(R.id.cardofexit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(HomeActivity.this,MainActivity.class));

            }
        });

        CardView findDoctor=findViewById(R.id.cardoffindDoctor);
        findDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "Find Doctor", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomeActivity.this,findDoctorActivity.class));
            }
        });

        CardView labtest=findViewById(R.id.cardoflabtest);
        labtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "Lab Test", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomeActivity.this,labTestActivity.class));

            }
        });

        CardView orderDetails=findViewById(R.id.cardoforderDetails);
        orderDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "Order Details", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomeActivity.this,orderDetailsActivity.class));

            }
        });

       CardView buymedicine=findViewById(R.id.cardofbymedicine);
        buymedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "Buy Medicine", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomeActivity.this,buyMEdicineActivity.class));

            }
        });

        CardView health=findViewById(R.id.cardofhealthDoctor);
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "Buy Medicine", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomeActivity.this,healthArticleActivity.class));

            }
        });







    }

 /*   @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    private void loadfragment(Fragment fragment){
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();

        ft.add(R.id.frame_layout,homeFragment);
        ft.commit();
    }*/
}