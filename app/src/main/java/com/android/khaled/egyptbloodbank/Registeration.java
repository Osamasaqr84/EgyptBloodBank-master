package com.android.khaled.egyptbloodbank;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.util.Calendar;

public class Registeration extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText name,e_mail,password,repassword,phone,date;
    String Blood;

    Spinner city,region;
    Button register;
    String []sub1;


    private int mYear;
    private int mMonth;
    private int mDay;
    static final int DATE_DIALOG_ID = 0;
    boolean true_mail;
Firebase ref;
    public static Activity fa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        fa=this;
        final String []catogries=getResources().getStringArray(R.array.city);

        region=(Spinner)findViewById(R.id.spinner_region);
        city=(Spinner)findViewById(R.id.spinner_city);

        // Spinner click listener
        city.setOnItemSelectedListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, catogries);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        city.setAdapter(dataAdapter);
        name=(EditText)findViewById(R.id.name);
        e_mail=(EditText)findViewById(R.id.mail);
        password=(EditText)findViewById(R.id.pass);
        repassword=(EditText)findViewById(R.id.repass);
        phone=(EditText)findViewById(R.id.phone);
        date=(EditText)findViewById(R.id.date);
        // get the current date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        // display the current date
        updateDisplay();
        city=(Spinner)findViewById(R.id.spinner_city);
        region=(Spinner)findViewById(R.id.spinner_region);
        date.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

                register=(Button)findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isEmailValid(e_mail.getText().toString()))
                    true_mail=true;
                else
                     e_mail.setError("Invalid Email");


                if(true_mail==true&&password.getText().toString().equals(repassword.getText().toString())&&password.getText().toString().length()>5) {
                    user user = new user();
                    user.setName(name.getText().toString());
                    user.setUname(e_mail.getText().toString());
                    user.setPass(password.getText().toString());
                    user.setRe_pass(repassword.getText().toString());
                    user.setDate(date.getText().toString());
                    user.setPhone(phone.getText().toString());
                    user.setCity(city.getSelectedItem().toString());
                    user.setRegion(region.getSelectedItem().toString());
                    user.setGroop(Blood);
                    user.setCity_index(city.getSelectedItemPosition());
                    user.setRegion_index(region.getSelectedItemPosition());
                    Long tsLong = System.currentTimeMillis() / 1000;

                    user.setUser_id(tsLong);

                    FireBaseClient fbc = new FireBaseClient(getBaseContext(), Config.FIREBASE_URL);
                    fbc.saveOnline(user);

                    name.setText("");
                    e_mail.setText("");
                    password.setText("");
                    repassword.setText("");
                    phone.setText("");

                    Toast.makeText(getBaseContext(), "You are added successfully to EgyptBloodBank", Toast.LENGTH_LONG).show();

                    fa.finish();
                    Intent intent=new Intent(getBaseContext(),MainActivity.class);
                    startActivity(intent);

                }
                else
                    repassword.setError("repass should equal pass and at least 6char");
            }
        });
    }
    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@")&&email.contains(" ")==false;
    }

    private void updateDisplay() {
        this.date.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(mMonth + 1).append("-")
                        .append(mDay).append("-")
                        .append(mYear).append(" "));
    }
    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    updateDisplay();
                }
            };


    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        mDateSetListener,
                        mYear, mMonth, mDay);
        }
        return null;
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

        TextView textView = (TextView)v;
        textView.setTextColor(getResources().getColor(R.color.White));
        textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.red_border));

        Blood = textView.getText().toString();
        // Showing selected blood
        Toast.makeText(getBaseContext(), "Selected: " + Blood, Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(position==0) {

            sub1=getResources().getStringArray(R.array.c_city);
            region.setEnabled(true);
        }
        if(position==1) {

            sub1=getResources().getStringArray(R.array.G_city);
            region.setEnabled(true);

        }
        else if (position==2) {

            sub1=getResources().getStringArray(R.array.A_city);
            region.setEnabled(true);

        }


        else if (position==3) {

            sub1=getResources().getStringArray(R.array.s_city);
            region.setEnabled(true);

        }
        else if (position==4) {

            sub1=getResources().getStringArray(R.array.Gena_city);
            region.setEnabled(true);

        }
        else if (position==5) {

            sub1=getResources().getStringArray(R.array.l_city);
            region.setEnabled(true);

        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sub1);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        region.setAdapter(dataAdapter);



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
