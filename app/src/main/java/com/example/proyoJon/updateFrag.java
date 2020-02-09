package com.example.proyoJon;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import static android.app.Activity.RESULT_OK;

public class updateFrag extends Fragment {
    private FirebaseAuth mAuth;
     private Uri imguri;
     private static final int IMAGE_REQUEST =1;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    boolean flag = false,flag2 = false;
    ImageView img;
    String genderSet[] = {"Male","Female"};
    String expSet[] = {"Yes","No"};
    String DhakaThanaEng[] = {"Adabor", "Ajimpur", "Badda", "Cokbazar", "Cantonment", "Dhanmondi", "Demra", "Gulshan", "Hajaribag", "Tejgao", "Shere bangla nogor", "Kolabagan", "New Market"};
    String districtnameEng[] = { "Dhaka", "Foridpur", "Gazipur", "Gopalgong", "Jalampur", "Kisorgong", "Madaripur", "Manikgong"};
    String shohorNameEng[] = { "Dhaka", "Chottogram", "Rajshahi", "khulna", "Borishal", "syllhet", "rangpur"};
    String peshanameEng[] = { "Electrician", "Plumber", "Wood Mechanic", "Home Tutor", "Internet Service", "Interior Designer", "Beauty Service","Home Service"};

    public updateFrag() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.fragment_update, container, false);
        perform(v);
        return v;
    }
    public void perform (View v){
        mAuth = FirebaseAuth.getInstance();
        final EditText name,age,pn,add;
        final TextView g,w,t,dis,div,exps;
        final Button pic,gen,thana,division,district,exp,work,save;
         final ProgressBar pb;
         pb = v.findViewById(R.id.pbrt2);
        name = v.findViewById(R.id.nameUpEt);
        age = v.findViewById(R.id.ageUpEt);
        pn = v.findViewById(R.id.phoneUpEt);
        add = v.findViewById(R.id.addressUpEt);

        g = v.findViewById(R.id.genderUpEt);
        w = v.findViewById(R.id.workUpEt);
        t = v.findViewById(R.id.thanaUpEt);
        dis = v.findViewById(R.id.districtUpEt);
        div = v.findViewById(R.id.divitionUpEt);
        exps = v.findViewById(R.id.expeUpEt);

        pic = v.findViewById(R.id.changepicUpBtn);
        gen = v.findViewById(R.id.changegenUpBtn);
        work = v.findViewById(R.id.changeworkUpBtn);
        thana = v.findViewById(R.id.changethanaUpBtn);
        division = v.findViewById(R.id.changedivisionUpBtn);
        district = v.findViewById(R.id.changedistrictUpBtn);
        exp = v.findViewById(R.id.chaneexpUpBtn);
        save = v.findViewById(R.id.saveUpBtn);
        img = v.findViewById(R.id.imageViewUpIdr);
        FirebaseUser user = mAuth.getCurrentUser();
        final String uid = user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("Kormi");
        storageReference = FirebaseStorage.getInstance().getReference("Kormi");
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
                Picasso.with(getActivity()).load(dataSnapshot.child(uid).child("imageLink").getValue(String.class)).transform(new Transform()).into(img, new Callback() {
                    @Override
                    public void onSuccess() {
                        pb.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });
                name.setText(user_name);
                age.setText(user_age);
                pn.setText(user_phone);
                add.setText(user_address);
                g.setText(user_gender);
                w.setText(user_occupation);
                t.setText(user_thana);
                dis.setText(user_district);
                div.setText(user_division);
                exps.setText(user_experience);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChooseFile();
                flag = true;
            }
        });
        final ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(getActivity(),R.layout.itemview,R.id.textViewId,genderSet);
        final ArrayAdapter<String> workAdapter = new ArrayAdapter<>(getActivity(),R.layout.itemview,R.id.textViewId,peshanameEng);
        final ArrayAdapter<String> divAdapter = new ArrayAdapter<>(getActivity(),R.layout.itemview,R.id.textViewId,shohorNameEng);
        final ArrayAdapter<String> disAdapter = new ArrayAdapter<>(getActivity(),R.layout.itemview,R.id.textViewId,districtnameEng);
        final ArrayAdapter<String> thanaAdapter = new ArrayAdapter<>(getActivity(),R.layout.itemview,R.id.textViewId,DhakaThanaEng);
        final ArrayAdapter<String> expAdapter = new ArrayAdapter<>(getActivity(),R.layout.itemview,R.id.textViewId,expSet);
        gen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                view = getLayoutInflater().inflate(R.layout.simple_layout3,null);
                ListView lv = view.findViewById(R.id.simplelistView2);
                lv.setAdapter(genderAdapter);
                builder.setView(view);
                final AlertDialog dialog = builder.create();
                dialog.show();
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String s = adapterView.getItemAtPosition(i).toString();
                        g.setText(s);
                        dialog.dismiss();
                    }
                });
            }
        });

        work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                view = getLayoutInflater().inflate(R.layout.simple_layout3,null);
                ListView lv = view.findViewById(R.id.simplelistView2);
                lv.setAdapter(workAdapter);
                builder.setView(view);
                final AlertDialog dialog = builder.create();
                dialog.show();
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String s = adapterView.getItemAtPosition(i).toString();
                        w.setText(s);
                        dialog.dismiss();
                    }
                });
            }
        });

        division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                view = getLayoutInflater().inflate(R.layout.simple_layout3,null);
                ListView lv = view.findViewById(R.id.simplelistView2);
                lv.setAdapter(divAdapter);
                builder.setView(view);
                final AlertDialog dialog = builder.create();
                dialog.show();
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String s = adapterView.getItemAtPosition(i).toString();
                        div.setText(s);
                        dialog.dismiss();
                    }
                });
            }
        });

        district.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                view = getLayoutInflater().inflate(R.layout.simple_layout3,null);
                ListView lv = view.findViewById(R.id.simplelistView2);
                lv.setAdapter(disAdapter);
                builder.setView(view);
                final AlertDialog dialog = builder.create();
                dialog.show();
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String s = adapterView.getItemAtPosition(i).toString();
                        dis.setText(s);
                        dialog.dismiss();
                    }
                });
            }
        });
        thana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                view = getLayoutInflater().inflate(R.layout.simple_layout3,null);
                ListView lv = view.findViewById(R.id.simplelistView2);
                lv.setAdapter(thanaAdapter);
                builder.setView(view);
                final AlertDialog dialog = builder.create();
                dialog.show();
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String s = adapterView.getItemAtPosition(i).toString();
                        t.setText(s);
                        dialog.dismiss();
                    }
                });
            }
        });
        exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                view = getLayoutInflater().inflate(R.layout.simple_layout3,null);
                ListView lv = view.findViewById(R.id.simplelistView2);
                lv.setAdapter(expAdapter);
                builder.setView(view);
                final AlertDialog dialog = builder.create();
                dialog.show();
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String s = adapterView.getItemAtPosition(i).toString();
                        exps.setText(s);
                        dialog.dismiss();
                    }
                });
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String getName,getAge,getPhone,getDivi,getDist,getThan,getAdd,getWork,getExp,getgen;
                getName = name.getText().toString();
                getAge = age.getText().toString();
                getPhone = pn.getText().toString();
                getAdd = add.getText().toString();
                getgen = g.getText().toString();
                getExp = exps.getText().toString();
                getWork = w.getText().toString();
                getThan = t.getText().toString();
                getDist = dis.getText().toString();
                getDivi = div.getText().toString();
                if(getName.isEmpty()){
                   name.setError("Name Required");
                   name.requestFocus();
                   return;
                }
                if(getAdd.isEmpty()){
                    add.setError("Address Required");
                    add.requestFocus();
                    return;
                }
                if(getPhone.isEmpty()){
                    pn.setError("Phone Required");
                    pn.requestFocus();
                    return;
                }
                if(getAge.isEmpty()){
                    age.setError("Age required");
                    age.requestFocus();
                    return;
                }
                final ProgressDialog progressDialog = ProgressDialog.show(getActivity(),"","Updating, please wait....",true);
                if (!TextUtils.isEmpty(getName)&&flag==true) {
                    StorageReference ref = storageReference.child(System.currentTimeMillis() + "." + getFileExtention(imguri));
                    FirebaseUser user = mAuth.getCurrentUser();
                    final String id = user.getUid();
                    ref.putFile(imguri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                            while (!uriTask.isSuccessful()) ;
                            Uri downloadUrl = uriTask.getResult();
                            String imageLink = downloadUrl.toString();
                            signupData at = new signupData(getName, getAge, getgen,getWork, getDivi, getDist, getThan, getAdd,getExp, getPhone, imageLink,"Active");
                            databaseReference.child(id).setValue(at);
                            Toast.makeText(getActivity(), "Update Successfully", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                            Intent intent = new Intent(getActivity(), ShoProfileAcivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), "Image is not upload Successfully", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                else if(!TextUtils.isEmpty(getName)&&flag==false){
                  FirebaseUser mUser = mAuth.getCurrentUser();
                  String id = mUser.getUid();
                  DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Kormi");
                  mDatabase.child(id).addValueEventListener(new ValueEventListener() {
                      @Override
                      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                          dataSnapshot.getRef().child("name").setValue(getName);
                          dataSnapshot.getRef().child("age").setValue(getAge);
                          dataSnapshot.getRef().child("address").setValue(getAdd);
                          dataSnapshot.getRef().child("gender").setValue(getgen);
                          dataSnapshot.getRef().child("occupation").setValue(getWork);
                          dataSnapshot.getRef().child("division").setValue(getDivi);
                          dataSnapshot.getRef().child("district").setValue(getDist);
                          dataSnapshot.getRef().child("thana").setValue(getThan);
                          dataSnapshot.getRef().child("phn").setValue(getPhone);
                          dataSnapshot.getRef().child("experience").setValue(getExp);
                          progressDialog.dismiss();
                          Toast.makeText(getActivity(), "Updated", Toast.LENGTH_LONG).show();

                      }

                      @Override
                      public void onCancelled(@NonNull DatabaseError databaseError) {

                      }
                  });
                }
            }
        });



    }
    public String getFileExtention(Uri imageUrl){
       ContentResolver contentResolver = getActivity().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imageUrl));
    }
    public void openChooseFile() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==IMAGE_REQUEST && resultCode == RESULT_OK && data!=null&& data.getData()!=null){
            imguri = data.getData();
            Picasso.with(getActivity()).load(imguri).transform(new Transform()).into(img);
        }
    }

}
