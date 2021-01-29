package com.example.myblooddonor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.myblooddonor.Fragments.AllRequestFragment;
import com.example.myblooddonor.Fragments.FragmentDonor;
import com.example.myblooddonor.Fragments.FragmentProfil;
import com.example.myblooddonor.Fragments.FragmentRequest;
import com.example.myblooddonor.Fragments.defaultProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Bloodrequest extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloodrequest);

       // System.out.println(UserServices.userconnecte.toString());



        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch ((menuItem.getItemId()))
                {
                    case R.id.action_acc:
                        showFragment(new AllRequestFragment());
                       // Toast.makeText(Bloodrequest.this, "Make a Request for Blood !", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_requests:
                        showFragment(new FragmentRequest());
                        //Toast.makeText(Bloodrequest.this, "Make a Request for Blood !", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_donors:
                        showFragment(new FragmentDonor());
                        //Toast.makeText(Bloodrequest.this, "Check Donor's List !", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.action_menu:
                        showFragment(new defaultProfileFragment());
                        //Toast.makeText(Bloodrequest.this, "My Account ! ", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }

        });

        bottomNavigationView.setSelectedItemId(R.id.action_acc);

    }
    public void showFragment (Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.frmlyt1,fragment).commit();
    }
}
