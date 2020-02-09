package com.example.proyoJon;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActiveStatus extends Fragment {
    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    TextView t1,t2;
    FirebaseUser user;
    String uid;
    boolean flag = false;
    Button on,off;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_active_status, container, false);
        perform(v);
        return v;
    }

    private void perform(View v) {
          mAuth = FirebaseAuth.getInstance();
          t1 = v.findViewById(R.id.showacSt);
         t2 = v.findViewById(R.id.showWarningTv);
        on = v.findViewById(R.id.onBtn);
        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeStatus2();
            }
        });
        off = v.findViewById(R.id.offBtn);
        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeStatus();
                flag = true;
            }
        });
        user = mAuth.getCurrentUser();
        uid = user.getUid();
    }
    private void changeStatus()
    {
        databaseReference = FirebaseDatabase.getInstance().getReference("Kormi");
        databaseReference.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataSnapshot.getRef().child("activeStatus").setValue("Inactive");
                t1.setText("Inactive");
                on.setVisibility(View.GONE);
                off.setVisibility(View.GONE);
                t2.setText("For change, shutdown app correctly!");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private  void changeStatus2()
    {
        databaseReference = FirebaseDatabase.getInstance().getReference("Kormi");
        databaseReference.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataSnapshot.getRef().child("activeStatus").setValue("Active");
                t1.setText("Active");
                on.setVisibility(View.GONE);
                off.setVisibility(View.GONE);
                t2.setText("For change, shutdown app correctly!");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    private void updateData()
    {
        databaseReference = FirebaseDatabase.getInstance().getReference("Kormi");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String status = dataSnapshot.child(uid).child("activeStatus").getValue(String.class);
                t1.setText(status);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void onStart() {
        updateData();
        super.onStart();
    }

}