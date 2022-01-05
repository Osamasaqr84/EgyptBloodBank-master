package com.osamasaqr.khaled.egyptbloodbank;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

/**
 * Created by osama saqr on 11/24/2016.
 */
public class FireBaseClient {

    Context c;
    String DB_URL;
    RecyclerView rv;
    Firebase fire;
    ArrayList<user> users=new ArrayList<>();
    ArrayList<user> results=new ArrayList<>();
    String [] conditions;
    MyRecyclerViewAdapter adapter;
    ProgressBar PB;
    user user;
    public FireBaseClient(Context c, String DB_URL, RecyclerView rv,ProgressBar progressBar,String[] con) {
        this.c = c;
        this.DB_URL = DB_URL;
        this.rv = rv;
        conditions=con;
        PB=progressBar;
        //INITIALIZE
        Firebase.setAndroidContext(c);
        //INSTANTIATE
        fire=new Firebase(DB_URL);
        fire=new Firebase(DB_URL);
    }

    public FireBaseClient(Context c,String DB_URL) {
        this.c = c;
        this.DB_URL = DB_URL;


        Firebase.setAndroidContext(c);
        //INSTANTIATE
        fire=new Firebase(DB_URL);
    }

    //SAVE
    public void saveOnline(user u)
    {

        fire.child("users").child("user"+u.getUser_id()).setValue(u);

    }
    //RETRIEVE
    public void refreshData()
    {
        fire.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                getUpdates(dataSnapshot);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                getUpdates(dataSnapshot);
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }
    private void getUpdates(DataSnapshot dataSnapshot)
    {
        users.clear();
        for(DataSnapshot ds : dataSnapshot.getChildren())
        {
            user m=new user();
            m.setName(ds.getValue(user.class).getName());
            m.setPhone(ds.getValue(user.class).getPhone());
            m.setCity(ds.getValue(user.class).getCity());
            m.setRegion(ds.getValue(user.class).getRegion());
            m.setGroop(ds.getValue(user.class).getGroop());
            users.add(m);
        }

        boolean ss1,ss2,ss3;
        int j=0;
        for (int i=0;i<users.size();i++)
        {
            String s1=users.get(i).getGroop();
            String s2=users.get(i).getCity();
            String s3=users.get(i).getRegion();
            ss1=s1.equalsIgnoreCase(conditions[0]);
            ss2=s2.equalsIgnoreCase(conditions[1]);
            ss3=s3.equalsIgnoreCase(conditions[2]);

            if (conditions[0].equalsIgnoreCase("Any Blood Group"))
            {
                if (ss2 == true && ss3 == true)
                {
                    j++;

                    Log.d("res0", "okkkkk");

                    results.add(users.get(i));


                }
            }
            else {
                if (ss1 == true && ss2 == true && ss3 == true) {
                    j++;

                    Log.d("res0", "okkkkk");

                    results.add(users.get(i));


                }
            }
        }
        if(results.size()>0)
        {
            adapter=new MyRecyclerViewAdapter(c,results);
            rv.setAdapter(adapter);

            PB.setVisibility(View.GONE);
        }

        else
        {
            Toast.makeText(c, "No data", Toast.LENGTH_SHORT).show();
            PB.setVisibility(View.GONE);
        }

    }

}
