package com.osamasaqr.khaled.egyptbloodbank;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.osamasaqr.khaled.egyptbloodbank.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class Edit_profile extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText name,phone,date,uname,pass,repass;
    Spinner city,region;
Button update;
    Firebase ref;
    user user;
   String groop_str;

    public static Activity fa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


            fa = this;
        final String []cities=getResources().getStringArray(R.array.city);
        city=(Spinner)findViewById(R.id.spinner_city);
        region=(Spinner)findViewById(R.id.spinner_region);
        city.setOnItemSelectedListener(this);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cities);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        city.setAdapter(dataAdapter);


        name=(EditText)findViewById(R.id.name);
        phone=(EditText)findViewById(R.id.phone);
        date=(EditText)findViewById(R.id.date);
        uname=(EditText)findViewById(R.id.uname);


        pass=(EditText)findViewById(R.id.password);
        repass=(EditText)findViewById(R.id.re_password);
        user=new user();
        Firebase.setAndroidContext(this);
        ref = new Firebase(Config.FIREBASE_URL);
        Intent intent=getIntent();
        final Long id = intent.getLongExtra("uid",0);


        ref.child("users").child("user"+id).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {


                user = (user) snapshot.getValue(user.class);

                name.setText(user.getName());
                phone.setText(user.getPhone());
                date.setText(user.getDate());
                uname.setText(user.getUname());
                pass.setText(user.getPass());
                repass.setText(user.getPass());

                groop_str=user.getGroop();
            city.setSelection(user.getCity_index());
            region.setSelection(user.getRegion_index());
            }

            @Override
            public void onCancelled(FirebaseError error) {
            }
        });

        update=(Button)findViewById(R.id.register);
update.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        if (isNetworkAvailable()) {

            String name_str = name.getText().toString();
            String phone_str = phone.getText().toString();
            String date_str = date.getText().toString();
            String city_str = city.getSelectedItem().toString();
            String region_str = region.getSelectedItem().toString();
            String uname_str = uname.getText().toString();
            String pass_str = pass.getText().toString();
            String repass_str = repass.getText().toString();

            user user = new user();
            user.setName(name_str);
            user.setPhone(phone_str);
            user.setDate(date_str);
            user.setCity(city_str);
            user.setCity_index(city.getSelectedItemPosition());
            user.setRegion_index(region.getSelectedItemPosition());
            user.setRegion(region_str);
            user.setGroop(groop_str);

            user.setUname(uname_str);
            user.setPass(pass_str);
            user.setRe_pass(repass_str);

            user.setUser_id(id);

            ref.child("users").child("user" + id).setValue(user);

            //                name.setText("");
            //                phone.setText("");
            //                date.setText("");
            //

            fa.finish();
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
            Toast.makeText(getBaseContext(), "You are update your profile Successfully ", Toast.LENGTH_LONG).show();

        } else
            Toast.makeText(getBaseContext(), "Make sure For Network Connection ", Toast.LENGTH_LONG).show();


    }
});





    }

    String []regions;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position==0) {

            regions=getResources().getStringArray(R.array.c_city);
            region.setEnabled(true);
        }
        if(position==1) {

            regions=getResources().getStringArray(R.array.G_city);
            region.setEnabled(true);

        }
        else if (position==2) {

            regions=getResources().getStringArray(R.array.A_city);
            region.setEnabled(true);

        }

        else if (position==3) {

            regions=getResources().getStringArray(R.array.s_city);
            region.setEnabled(true);

        }
        else if (position==4) {

            regions=getResources().getStringArray(R.array.Gena_city);
            region.setEnabled(true);

        }
        else if (position==5) {

            regions=getResources().getStringArray(R.array.l_city);
            region.setEnabled(true);

        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, regions);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        region.setAdapter(dataAdapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    }

