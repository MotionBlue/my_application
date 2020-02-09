package com.example.proyoJon;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class deleteFrag extends Fragment {
     FirebaseAuth mAuth;
     DatabaseReference databaseReference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
          View v = inflater.inflate(R.layout.fragment_delete, container, false);
          perform(v);
        return v;
    }

    private void perform(View v) {
        v = getLayoutInflater().inflate(R.layout.delete_layout,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        Button b1,b2;
        b1 = v.findViewById(R.id.yesBtn);
        b2 = v.findViewById(R.id.noBtn);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("Kormi").child(uid);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                databaseReference.removeValue();
                Toast.makeText(getActivity(), "Account Delete Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(),ShoProfileAcivity.class);
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }

}
