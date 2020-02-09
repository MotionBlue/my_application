package com.example.proyoJon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ShowKormiActivity extends AppCompatActivity {
    private ListView listView;
    DatabaseReference databaseReference;
    private List<DataRetrive> kormiList;
    String location,occupation,v;
    CustorAdapter custorAdapter;
    ProgressBar pt;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_kormi);
        databaseReference = FirebaseDatabase.getInstance().getReference("Kormi");
        mAuth = FirebaseAuth.getInstance();
        listView = findViewById(R.id.simpleListView);
         pt = findViewById(R.id.probarId);
        kormiList = new ArrayList<>();
        Intent intent = getIntent();
        location = intent.getStringExtra("location");
        occupation = intent.getStringExtra("workName");
        v = intent.getStringExtra("lang");
        custorAdapter = new CustorAdapter(ShowKormiActivity.this,kormiList,v);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ShowKormiActivity.this);
                view = getLayoutInflater().inflate(R.layout.simple_layout2,null);
                TextView name,age,work,workExp,gender,division,district,thana,address,phon;
                ImageView imk;
                final ProgressBar pb;
                pb = view.findViewById(R.id.pbrt);
                name = view.findViewById(R.id.nameSTv);
                age = view.findViewById(R.id.ageSTv);
                work = view.findViewById(R.id.workSTv);
                workExp = view.findViewById(R.id.experiSTv);
                gender = view.findViewById(R.id.genderSTv);
                district = view.findViewById(R.id.DistrictSTv);
                division = view.findViewById(R.id.divisionSTv);
                thana = view.findViewById(R.id.thanaSTv);
                address = view.findViewById(R.id.addressSTv);
                phon = view.findViewById(R.id.phoneSTv);
                imk = view.findViewById(R.id.imgViewId);
                String str = kormiList.get(i).getImagelink();
                pb.setVisibility(View.VISIBLE);
                Picasso.with(ShowKormiActivity.this).load(str).transform(new Transform()).into(imk, new Callback() {
                    @Override
                    public void onSuccess() {
                        pb.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });
                if(v.equals("bn")){
                    name.setText("নাম: "+kormiList.get(i).getName());
                    age.setText("বয়স: "+kormiList.get(i).getAge());
                    work.setText("কাজ: "+kormiList.get(i).getOccupation());
                    workExp.setText("অভিজ্ঞতা: "+kormiList.get(i).getExperience());
                    gender.setText("লিঙ্গ: "+kormiList.get(i).getGender());
                    district.setText("জেলা: "+kormiList.get(i).getDistrict());
                    division.setText("বিভাগ: "+kormiList.get(i).getDivision());
                    thana.setText("থানা/উপজেলা: "+kormiList.get(i).getThana());
                    address.setText("ঠিকানা: "+kormiList.get(i).getAddress());
                    phon.setText("মোবাইল: "+kormiList.get(i).getPhn());
                }
                else{
                    name.setText("Name: "+kormiList.get(i).getName());
                    age.setText("Age: "+kormiList.get(i).getAge());
                    work.setText("Work: "+kormiList.get(i).getOccupation());
                    workExp.setText("Experience: "+kormiList.get(i).getExperience());
                    gender.setText("Gender: "+kormiList.get(i).getGender());
                    district.setText("District: "+kormiList.get(i).getDistrict());
                    division.setText("Division: "+kormiList.get(i).getDivision());
                    thana.setText("Thana/Upozila: "+kormiList.get(i).getThana());
                    address.setText("Address: "+kormiList.get(i).getAddress());
                    phon.setText("Contact Number: "+kormiList.get(i).getPhn());
                }
                builder.setView(view);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
    @Override
    protected void onStart() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 pt.setVisibility(View.VISIBLE);
                kormiList.clear();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    DataRetrive dataRetrive = dataSnapshot1.getValue(DataRetrive.class);
                   if(dataRetrive.getThana().equals(location)&&dataRetrive.getOccupation().equals(occupation)){
                        kormiList.add(dataRetrive);
                    }
                }
                listView.setAdapter(custorAdapter);
                pt.setVisibility(View.GONE);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        super.onStart();
    }

}
