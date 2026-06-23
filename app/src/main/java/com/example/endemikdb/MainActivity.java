package com.example.endemikdb;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.endemikdb.fragment.FavoritFragment;
import com.example.endemikdb.fragment.HomeFragment;
import com.example.endemikdb.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation =
                findViewById(R.id.bottomNavigation);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(
                        R.id.fragmentContainer,
                        new HomeFragment()
                )
                .commit();

        bottomNavigation.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.nav_home) {

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(
                                R.id.fragmentContainer,
                                new HomeFragment()
                        )
                        .commit();

                return true;
            }

            if (item.getItemId() == R.id.nav_favorit) {

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(
                                R.id.fragmentContainer,
                                new FavoritFragment()
                        )
                        .commit();

                return true;
            }

            if (item.getItemId() == R.id.nav_profile) {

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(
                                R.id.fragmentContainer,
                                new ProfileFragment()
                        )
                        .commit();

                return true;
            }

            return false;
        });
    }
}