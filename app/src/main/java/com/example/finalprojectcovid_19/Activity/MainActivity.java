package com.example.finalprojectcovid_19.Activity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.finalprojectcovid_19.Fragment.HomeFragment;
import com.example.finalprojectcovid_19.Fragment.MessageFragment;
import com.example.finalprojectcovid_19.Fragment.SearchFragment;
import com.example.finalprojectcovid_19.R;
import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    BubbleNavigationConstraintView bubbleNavigation;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Fragment fragment = null;

    public static DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menu_icon;

    public static TextView menuTitle;

    FirebaseAuth auth = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        auth = FirebaseAuth.getInstance();



        menuTitle = findViewById(R.id.menu_title_tv);
        //Menu
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view1);
        navigationView.setNavigationItemSelectedListener(this);
        menu_icon = findViewById(R.id.menu_icon);

        menu_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.drawerLayout.openDrawer(GravityCompat.START);
            }
        });


        //Fragments
        bubbleNavigation = findViewById(R.id.bottom_navigation_constraint);

        if (fragment == null) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, new HomeFragment());
            fragmentTransaction.commit();
        }

        bubbleNavigation.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                switch (view.getId()) {
                    case R.id.item_home:
                        fragment = new HomeFragment();
                        break;

                    case R.id.item_search:
                        fragment = new SearchFragment();
                        break;

                    case R.id.item_message:
                        fragment = new MessageFragment();
                        break;

                }
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
            }
        });

    }


    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_covid_19:
                startActivity(new Intent(getApplicationContext(),AboutCoronaActivity.class));
            break;

            case R.id.nav_prevention_methods:
                startActivity(new Intent(getApplicationContext(),PreventionMethodsActivity.class));
                break;

            case R.id.nav_corona_symptoms:
                startActivity(new Intent(getApplicationContext(),CoronaSymptomsActivity.class));
                break;

            case R.id.nav_logout:
                if (auth.getCurrentUser().getEmail() != null) {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    auth.signOut();
                }
                break;
        }

        return false;
    }
}
