package com.android.khaled.egyptbloodbank;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,AdapterView.OnItemSelectedListener {

    String Blood;
Spinner spinner2;
    Spinner spinner;
    String [] sub1;
    String [] con=new String[3];
    Button search;
    RecyclerView Rc;
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        search=(Button)findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Blood==null)
                    Toast.makeText(getBaseContext(),"please choice Blood Groop First",Toast.LENGTH_LONG).show();
                else {
                    con[0] = Blood;
                    con[1] = spinner.getSelectedItem().toString();
                    con[2] = spinner2.getSelectedItem().toString();

                    Intent intent = new Intent(getBaseContext(), Donores.class);

                    intent.putExtra("condition", con);
                    startActivity(intent);
                }
            }
        });
        /////////////////////////////////////////////////////////////////

        // Spinner element
         spinner = (Spinner) findViewById(R.id.spinner1);
         spinner2 = (Spinner) findViewById(R.id.spinner2);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);


        final String []catogries=getResources().getStringArray(R.array.city);


        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, catogries);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        // Spinner Drop down elements
        }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position==0) {

            sub1=getResources().getStringArray(R.array.c_city);
            spinner2.setEnabled(true);
        }
        if(position==1) {

            sub1=getResources().getStringArray(R.array.G_city);
            spinner2.setEnabled(true);

        }
        else if (position==2) {

            sub1=getResources().getStringArray(R.array.A_city);
            spinner2.setEnabled(true);

        }


        else if (position==3) {

            sub1=getResources().getStringArray(R.array.s_city);
            spinner2.setEnabled(true);

        }
        else if (position==4) {

            sub1=getResources().getStringArray(R.array.Gena_city);
            spinner2.setEnabled(true);

        }
        else if (position==5) {

            sub1=getResources().getStringArray(R.array.l_city);
            spinner2.setEnabled(true);

        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sub1);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner2.setAdapter(dataAdapter);



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /////////////////////////////////////////////////////////////////

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_search)
        {


        }
        else if (id == R.id.nav_register)
        {
            Intent intent = new Intent(getBaseContext(), Registeration.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_Edit)
        {
            Intent intent = new Intent(getBaseContext(),LoginActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_tips)
        {

        }
        else if (id == R.id.nav_blood)
        {

        }
        else if (id == R.id.nav_share)
        {

        }
        else if (id == R.id.nav_send)
        {

        }
        else if (id == R.id.nav_about)
        {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @FromXML
    public void onClick(View v)
    {
        TextView Main_A_Plus = (TextView)findViewById(R.id.Main_A_Plus);
        TextView Main_A_minus = (TextView)findViewById(R.id.Main_A_minus);
        TextView Main_B_Plus = (TextView)findViewById(R.id.Main_B_Plus);
        TextView Main_B_Minus = (TextView)findViewById(R.id.Main_B_Minus);
        TextView Main_O_Plus = (TextView)findViewById(R.id.Main_O_Plus);
        TextView Main_O_Minus = (TextView)findViewById(R.id.Main_O_Minus);
        TextView Main_AB_Plus = (TextView)findViewById(R.id.Main_AB_Plus);
        TextView Main_AB_Minus = (TextView)findViewById(R.id.Main_AB_Minus);
        TextView Main_Any = (TextView)findViewById(R.id.Main_Any);

        Main_A_Plus.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        Main_A_Plus.setBackgroundDrawable(getResources().getDrawable(R.drawable.border));

        Main_A_minus.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        Main_A_minus.setBackgroundDrawable(getResources().getDrawable(R.drawable.border));

        Main_B_Plus.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        Main_B_Plus.setBackgroundDrawable(getResources().getDrawable(R.drawable.border));

        Main_B_Minus.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        Main_B_Minus.setBackgroundDrawable(getResources().getDrawable(R.drawable.border));

        Main_O_Plus.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        Main_O_Plus.setBackgroundDrawable(getResources().getDrawable(R.drawable.border));

        Main_O_Minus.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        Main_O_Minus.setBackgroundDrawable(getResources().getDrawable(R.drawable.border));

        Main_AB_Plus.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        Main_AB_Plus.setBackgroundDrawable(getResources().getDrawable(R.drawable.border));

        Main_AB_Minus.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        Main_AB_Minus.setBackgroundDrawable(getResources().getDrawable(R.drawable.border));

        Main_Any.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        Main_Any.setBackgroundDrawable(getResources().getDrawable(R.drawable.border));

        TextView textView = (TextView)v;
        textView.setTextColor(getResources().getColor(R.color.White));
        textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.red_border));

        Blood = textView.getText().toString();
        // Showing selected blood
        Toast.makeText(getBaseContext(), "Selected: " + Blood, Toast.LENGTH_SHORT).show();


    }
}


