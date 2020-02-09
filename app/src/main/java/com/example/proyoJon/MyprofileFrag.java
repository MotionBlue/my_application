package com.example.proyoJon;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;


public class MyprofileFrag extends Fragment {
    private FirebaseAuth mAuth;

    DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_myprofile,container,false);
        perfom(v);
        return v;
    }
    public void perfom(final View v){

        final TextView name,age,gender,occupation,division,district,thana,address,experience,phone;
        final ProgressBar pb;
        mAuth  = FirebaseAuth.getInstance();
        pb = v.findViewById(R.id.pbrt1);
        name = v.findViewById(R.id.showNameEt);
        age = v.findViewById(R.id.showageEt);
        gender = v.findViewById(R.id.showgenderEt);
        occupation = v.findViewById(R.id.showOccuEt);
        division = v.findViewById(R.id.showDivisionEt);
        district = v.findViewById(R.id.showDistrictEt);
        thana = v.findViewById(R.id.showthanaEt);
        address = v.findViewById(R.id.showAddressEt);
        experience = v.findViewById(R.id.showexperiEt);
        phone = v.findViewById(R.id.showContactNumberEt);
       final ImageView it = v.findViewById(R.id.img);
        FirebaseUser user = mAuth.getCurrentUser();
        final String uid = user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("Kormi");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String user_name = dataSnapshot.child(uid).child("name").getValue(String.class);
                String user_age = dataSnapshot.child(uid).child("age").getValue(String.class);
                String user_gender = dataSnapshot.child(uid).child("gender").getValue(String.class);
                String user_occupation = dataSnapshot.child(uid).child("occupation").getValue(String.class);
                String user_division = dataSnapshot.child(uid).child("division").getValue(String.class);
                String user_district = dataSnapshot.child(uid).child("district").getValue(String.class);
                String user_thana = dataSnapshot.child(uid).child("thana").getValue(String.class);
                String user_address = dataSnapshot.child(uid).child("address").getValue(String.class);
                String user_experience = dataSnapshot.child(uid).child("experience").getValue(String.class);
                String user_phone = dataSnapshot.child(uid).child("phn").getValue(String.class);
                pb.setVisibility(View.VISIBLE);
                Picasso.with(getActivity()).load(dataSnapshot.child(uid).child("imageLink").getValue(String.class)).transform(new Transform()).into(it, new Callback() {
                    @Override
                    public void onSuccess() {
                        pb.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });
                   name.setText("Name: "+user_name);
                   age.setText("Age: "+user_age);
                   gender.setText("Gender: "+user_gender);
                   occupation.setText("Work: "+user_occupation);
                   division.setText("Division: "+user_division);
                   district.setText("District: "+user_district);
                   thana.setText("Thana: "+user_thana);
                   address.setText("Full Address: "+user_address);
                   experience.setText("Previous Working Experience: "+user_experience);
                   phone.setText("Contact Number: "+user_phone);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
