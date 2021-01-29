package com.example.myblooddonor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myblooddonor.Entity.Donation;
import com.example.myblooddonor.Entity.Request;
import com.example.myblooddonor.Services.DonationServices;
import com.example.myblooddonor.Services.UserServices;

public class DetailsAllRequest extends AppCompatActivity {



    ImageView btnDonate;

    LinearLayout image;

    TextView titre;
    TextView type;
    TextView description;


    public Request a = new Request();
    public Donation d = new Donation();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_all_request);



        //btnDonate = findViewById(R.id.btn_donate_detail);

        image = findViewById(R.id.detail_annonce_image);

        titre = findViewById(R.id.detail_annonce_titre);
        type = findViewById(R.id.detail_annonce_type);
        description = findViewById(R.id.detail_annonce_description);

        checkAndFillData(getIntent());


        titre.setText(a.getFirstNameRequest());
        description.setText(a.getDescription());
        type.setText(a.getBloodTypeRequest());





        Fragment mapFrame = new MapFragment();

        Bundle b=new Bundle();


        System.out.println("detail :"+ a.getCityRequest_lan() +", "+ a.getCityRequest_lon());

     //  b.putString("firstNameRequest",a.getFirstNameRequest());
        b.putString("lat",a.getCityRequest_lan());

        b.putString("long",a.getCityRequest_lon());

        mapFrame.setArguments(b);
        getSupportFragmentManager().beginTransaction().replace(R.id.detail_request_frame_map, mapFrame).commit();


    }

    public void checkAndFillData(Intent intent){

        if( intent.hasExtra("firstNameRequest") ){
            a.setFirstNameRequest( intent.getStringExtra("firstNameRequest"));
        }
        if( intent.hasExtra("description") ){
            a.setDescription( intent.getStringExtra("description"));
        }
        if( intent.hasExtra("bloodTypeRequest") ){
            a.setBloodTypeRequest( intent.getStringExtra("bloodTypeRequest"));
        }

        if( intent.hasExtra("lat") ){
            a.setCityRequest_lan( intent.getStringExtra("lat"));
        }

        if( intent.hasExtra("long") ){
            a.setCityRequest_lon( intent.getStringExtra("long"));
        }



        /*if( intent.hasExtra("date") ){
            a.setDate( intent.getStringExtra("date"));
        }
        if( intent.hasExtra("type") ){
            a.setType(intent.getStringExtra("type"));
        }
        if( intent.hasExtra("id") ){
            a.setId( intent.getIntExtra("id",0));
        }
        if( intent.hasExtra("lat") ){
            a.setEmplacement_lat( intent.getStringExtra("lat"));
        }
        if( intent.hasExtra("long") ){
            a.setEmplacement_long( intent.getStringExtra("long"));
        }*/


    }






    /*public void donate(View view){
        Intent intent=new Intent(this,AddDonationActivity.class);
        intent.putExtra("id", a.getId());
        startActivity(intent);

    }*/
    public void goBack(View view)
    {
        onBackPressed();
    }

    public void call (View view)
    {

                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+getIntent().getStringExtra("phone_request"))));


    }

}
