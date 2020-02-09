package com.example.proyoJon;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustorAdapter extends ArrayAdapter<DataRetrive> {

    private Activity context;
    private List<DataRetrive> kormiList;
    String v;
    public CustorAdapter( Activity context,List<DataRetrive> kormiList,String v) {
        super(context,R.layout.simple_layout,kormiList);
        this.context = context;
        this.kormiList = kormiList;
        this.v = v;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();

        View view  = layoutInflater.inflate(R.layout.simple_layout,null,true);
        DataRetrive dataRetrive = kormiList.get(position);

        TextView t1 = view.findViewById(R.id.nameTv);
        TextView t2 = view.findViewById(R.id.workTv);
        TextView t3 = view.findViewById(R.id.addressTv);
        TextView t4 = view.findViewById(R.id.phoneTv);
        TextView t5 = view.findViewById(R.id.statustv);

        if(v.equals("bn")){
            t1.setText("নাম: "+dataRetrive.getName());
            t2.setText("কাজ: "+dataRetrive.getOccupation());
            t3.setText("ঠিকানা: "+dataRetrive.getAddress());
            t4.setText("মোবাইল: "+dataRetrive.getPhn());
            t5.setText(dataRetrive.getActiveStatus());
            if(dataRetrive.activeStatus.equals("Inactive")){
                t5.setTextColor(Color.parseColor("#ff0000"));
            }
            else if(dataRetrive.activeStatus.equals("Active")){
                t5.setTextColor(Color.parseColor("#008000"));
            }

        }
        else{
            t1.setText("Name: "+dataRetrive.getName());
            t2.setText("Work: "+dataRetrive.getOccupation());
            t3.setText("Address: "+dataRetrive.getAddress());
            t4.setText("Phone: "+dataRetrive.getPhn());
            t5.setText(dataRetrive.getActiveStatus());
            if(dataRetrive.activeStatus.equals("Inactive")){
                t5.setTextColor(Color.parseColor("#ff0000"));
            }
            else if(dataRetrive.activeStatus.equals("Active")){
                t5.setTextColor(Color.parseColor("#008000"));
            }
        }
        return view;

    }
}
