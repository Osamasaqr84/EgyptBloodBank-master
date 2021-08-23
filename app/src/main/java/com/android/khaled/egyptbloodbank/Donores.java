package com.android.khaled.egyptbloodbank;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;

public class Donores extends AppCompatActivity {

    RecyclerView Rc;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donores);

        String [] con=getIntent().getStringArrayExtra("condition");
        Rc=(RecyclerView)findViewById(R.id.Rc);
        Rc.setLayoutManager(new LinearLayoutManager(this));
        pb=(ProgressBar)findViewById(R.id.progressBar);
        FireBaseClient fireBaseClient=new FireBaseClient(getBaseContext(),Config.FIREBASE_URL,Rc,pb,con);
        fireBaseClient.refreshData();
        Log.d("sd","da");
        Log.d("sd","da");
        Log.d("sd","da");
    }
}
