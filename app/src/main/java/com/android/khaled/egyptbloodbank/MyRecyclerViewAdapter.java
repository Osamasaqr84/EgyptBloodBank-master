package com.android.khaled.egyptbloodbank;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by osama saqr on 11/23/2016.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.CustomViewHolder> {
    private ArrayList<user> feedItemList;
    private Context mContext;

    public MyRecyclerViewAdapter(Context context, ArrayList<user> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.donor, viewGroup,false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {
        final user feedItem = feedItemList.get(position);


       viewHolder.Name.setText(feedItem.getName());
       viewHolder.phone.setText(feedItem.getPhone());
       viewHolder.blood.setText(feedItem.getGroop());
       viewHolder.address.setText(feedItem.getCity()+"-"+feedItem.getRegion());
       viewHolder.address.setText(feedItem.getCity()+"-"+feedItem.getRegion());
       viewHolder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCALL(feedItem.getPhone());
            }
        });

        viewHolder.message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareTextUrl(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView Name,phone,address,blood;

        ImageView call,message;

        public CustomViewHolder(View view) {
            super(view);
            phone = (TextView)view.findViewById(R.id.phone);
            Name = (TextView)view.findViewById(R.id.name);
            address = (TextView)view.findViewById(R.id.address);
            blood = (TextView)view.findViewById(R.id.blood);
            call=(ImageView)view.findViewById(R.id.call);
            message=(ImageView)view.findViewById(R.id.sms);
            message=(ImageView)view.findViewById(R.id.sms);
            message=(ImageView)view.findViewById(R.id.sms);
        }
    }

    public void makeCALL(String number)    {

      //  FragmentManager manager = ((Activity) mContext).getFragmentManager();


           String Phone_Number = number;
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + Phone_Number));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);

    }


    private void shareTextUrl(int index) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Add data to the intent, the receiving app will decide
        // what to do with it.

        String data = "Name // " +feedItemList.get(index).getName()+"\n\nAddress // "+ feedItemList.get(index).getCity()+"-"+feedItemList.get(index).getRegion()+"\n\n Phone Number  // "+feedItemList.get(index).getPhone();

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + feedItemList.get(index).getPhone()));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Egyptbloodbank");
        intent.putExtra(Intent.EXTRA_TEXT, data);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            mContext.startActivity(intent);
//
//        share.putExtra(Intent.EXTRA_TEXT, data);
//        mContext.startActivity(Intent.createChooser(share, "Share link!"));
    }


}