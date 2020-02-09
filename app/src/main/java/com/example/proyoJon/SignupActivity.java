package com.example.proyoJon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class SignupActivity extends AppCompatActivity {
    String division, district, thana, occu;
    private FirebaseAuth mAuth;
    String m, f, y, no;
    private Spinner spinner, spinner2, spinner3, spinner4;
    RadioButton radioButton1, radioButton2, radioButton3, radioButton4;
    RadioGroup radioGroup, radioGroup2;
    TextView gender,oc,wx;
    String v;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    private EditText name, age, address, phone;
    private Button signup,choseImg;
    private Uri imguri;
    private static final int IMAGE_REQUEST = 1;
    ImageView img;
    boolean flag = false,flag2 = false;
    String peshaname[] = {"আপনার কাজ","ইলেক্ট্রিশিয়ান","প্লাম্বার","কাঠ-মিস্ত্রি","হোম-টিউটর","ইন্টারনেট সার্ভিস","ইন্টেরিয়ার ডিজাইনার","বিউটি সার্ভিস","গৃহকর্মী"};
    String peshanameEng[] = {"Select works", "Electrician", "Plumber", "Wood Mechanic", "Home Tutor", "Internet Service", "Interior Designer", "Beauty Service","Home Service"};
    String shohorName[] = {"বিভাগ","ঢাকা","চট্টগ্রাম","রাজশাহী","খুলনা","বরিশাল","সিলেট","রংপুর"};
    String shohorNameEng[] = {"Select Division", "Dhaka", "Chottogram", "Rajshahi", "khulna", "Borishal", "syllhet", "rangpur"};
    String districtName[] = {"জেলা","ঢাকা","ফরিদপুর","গাজীপুর","গোপালগঞ্জ","জামালপুর","কিশোরগঞ্জ","মাদারীপুর","মানিকগঞ্জ"};
    String districtnameEng[] = {"Select District", "Dhaka", "Foridpur", "Gazipur", "Gopalgong", "Jalampur", "Kisorgong", "Madaripur", "Manikgong"};
    String DhakaThana[] = {"থানা","আদাবর","আজীমপুর","বাড্ডা","চকবাজার","ক্যান্টনমেন্ট","ধানমন্ডি","ডেমরা","গুনশান","হাজারীবাগ","তেজগাও","শেরে বাংলা নগর","কলাবাগান","নিউমার্কেট"};
    String DhakaThanaEng[] = {"Select Thana", "Adabor", "Ajimpur", "Badda", "Cokbazar", "Cantonment", "Dhanmondi", "Demra", "Gulshan", "Hajaribag", "Tejgao", "Shere bangla nogor", "Kolabagan", "New Market"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        spinner = findViewById(R.id.peshaSP);
        spinner2 = findViewById(R.id.shohorSP);
        spinner3 = findViewById(R.id.jilaSP);
        spinner4 = findViewById(R.id.thanaSP);

        gender = findViewById(R.id.genId);
        wx = findViewById(R.id.wrxId);
        name = findViewById(R.id.nameEt);
        age = findViewById(R.id.ageET);
        address = findViewById(R.id.addressEt);
        phone = findViewById(R.id.phoneEt);
        choseImg = findViewById(R.id.chosseBtn);
        img = findViewById(R.id.profilePic);
        radioGroup = findViewById(R.id.radiogroup1);
        radioGroup2 = findViewById(R.id.radiogroup2);
        signup = findViewById(R.id.signupBtn);
        radioButton1 = findViewById(R.id.maleRB);
        radioButton2 = findViewById(R.id.femaleRB);
        radioButton3 = findViewById(R.id.yesRB);
        radioButton4 = findViewById(R.id.noRB);
        Intent intent = getIntent();
        v = intent.getStringExtra("lang");
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Kormi");
        storageReference = FirebaseStorage.getInstance().getReference("Kormi");
        choseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChooseFile();
                flag = true;
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpInformation();
            }
        });
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.itemview, R.id.textViewId, peshanameEng);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    Toast.makeText(SignupActivity.this, "Select Works", Toast.LENGTH_SHORT).show();
                    occu = "";
                    return;
                }
                occu = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(this, R.layout.itemview, R.id.textViewId, shohorNameEng);
        spinner2.setAdapter(arrayAdapter1);

        final ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(this, R.layout.itemview, R.id.textViewId, districtnameEng);
        final ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<>(this, R.layout.itemview, R.id.textViewId, DhakaThanaEng);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    Toast.makeText(SignupActivity.this, "Select Division", Toast.LENGTH_SHORT).show();
                    division = "";
                    return;
                }
                division = adapterView.getItemAtPosition(i).toString();
                if (i == 1) {
                    spinner3.setAdapter(arrayAdapter2);
                    spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                            if(i==0){
                                Toast.makeText(SignupActivity.this, "Select District", Toast.LENGTH_SHORT).show();
                                district = "";
                                return;
                            }
                            district = adapterView.getItemAtPosition(i).toString();
                            if (i == 1) {
                                spinner4.setAdapter(arrayAdapter3);
                                spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        if(i==0){
                                            Toast.makeText(SignupActivity.this, "Select Thana", Toast.LENGTH_SHORT).show();
                                            thana = "";
                                            return;
                                        }
                                        thana = adapterView.getItemAtPosition(i).toString();
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }
                                });
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        if(v.equals("bn")){
            name.setHint("আপনার নাম");
            age.setHint("বয়স");
            address.setHint("বাড়ী নং, রোড নং, গ্রাম, ডাকঘর");
            phone.setHint("ফোন নাম্বার");
            radioButton1.setText("পুরুষ");
            radioButton2.setText("নারী");
            radioButton3.setText("হ্যাঁ" );
            radioButton4.setText("না");
            gender.setText("লিঙ্গ:");
            wx.setText("কাজের অভিজ্ঞতা?");
            signup.setText("ঠিক আছে");
            choseImg.setText("ছবি নির্বাচন");
            ArrayAdapter<String> barrayworks = new ArrayAdapter<>(this,R.layout.itemview,R.id.textViewId,peshaname);
            ArrayAdapter<String> shohorArray = new ArrayAdapter<>(this,R.layout.itemview,R.id.textViewId,shohorName);
            final ArrayAdapter<String> districarray = new ArrayAdapter<>(this,R.layout.itemview,R.id.textViewId,districtName);
            final ArrayAdapter<String> thanaarray = new ArrayAdapter<>(this,R.layout.itemview,R.id.textViewId,DhakaThana);
            spinner.setAdapter(barrayworks);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(i==0){
                        Toast.makeText(SignupActivity.this, "কাজ নির্বাচন করুন", Toast.LENGTH_SHORT).show();
                        occu = "";
                        return;
                    }
                    occu = peshanameEng[i];
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            spinner2.setAdapter(shohorArray);
            spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(i==0){
                        Toast.makeText(SignupActivity.this, "বিভাগ নির্বাচন করুন", Toast.LENGTH_SHORT).show();
                        division = "";
                        return;
                    }
                    division = shohorNameEng[i];
                    if(i==1){
                        spinner3.setAdapter(districarray);
                        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                if(i==0){
                                    Toast.makeText(SignupActivity.this, "জেলা নির্বাচন করুন", Toast.LENGTH_SHORT).show();
                                    district = "";
                                    return;
                                }
                                district = districtnameEng[i];
                                if(i==1){
                                    spinner4.setAdapter(thanaarray);
                                    spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                            if(i==0){
                                                Toast.makeText(SignupActivity.this, "থানা নির্বাচন করুন", Toast.LENGTH_SHORT).show();
                                                thana = "";
                                                return;
                                            }
                                            thana = DhakaThanaEng[i];
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> adapterView) {

                                        }
                                    });
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }


    }
    private String getFileExtention(Uri imageUrl){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imageUrl));
    }
    private void openChooseFile() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==IMAGE_REQUEST && resultCode == RESULT_OK && data!=null&& data.getData()!=null){
            imguri = data.getData();
            Picasso.with(this).load(imguri).into(img);
            flag2 = false;
        }
        else{
            flag2 = true;
        }
    }
    private void signUpInformation() {
        final String n = name.getText().toString();
        if (n.isEmpty()) {
            name.setError("required!");
            name.requestFocus();
            return;
        } else {

        }
        final String a = age.getText().toString();
        if (a.isEmpty()) {
            age.setError(" required!");
            age.requestFocus();
            return;
        }
        if (radioButton1.isChecked()) {
            m = "Male";
        }
        if (radioButton2.isChecked()) {
            m = "Female";
        }
        if((!radioButton1.isChecked())&&(!radioButton2.isChecked())){
            if(v.equals("bn")){
                Toast.makeText(this, "লিঙ্গ নির্বাচন করুন", Toast.LENGTH_SHORT).show();
                return;
            }
            else{
                Toast.makeText(this, "Select Gender", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if (radioButton3.isChecked()) {
            y = "Yes";
        }
        if (radioButton4.isChecked()) {
            y = "No";
        }
        if((!radioButton3.isChecked())&&(!radioButton4.isChecked())){
            if(v.equals("bn")){
                Toast.makeText(this, "অভিজ্ঞতা নির্বাচন করুন", Toast.LENGTH_SHORT).show();
                return;
            }
            else{
                Toast.makeText(this, "Select Experience", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        final String add = address.getText().toString();
        if (add.isEmpty()) {
            address.setError(" required!");
            address.requestFocus();
            return;
        }
        final String p = phone.getText().toString();
        if (p.isEmpty()) {
            phone.setError(" required!");
            phone.requestFocus();
            return;
        }
        if(v.equals("bn")){
            if(thana.equals("")){
                Toast.makeText(SignupActivity.this, "থানা নির্বাচন করুন", Toast.LENGTH_SHORT).show();
                return;
            }
            if(district.equals("")){
                Toast.makeText(SignupActivity.this, "জেলা নির্বাচন করুন", Toast.LENGTH_SHORT).show();
                return;
            }
            if(division.equals("")){
                Toast.makeText(SignupActivity.this, "বিভাগ নির্বাচন করুন", Toast.LENGTH_SHORT).show();
                return;
            }
            if(occu.equals("")){
                Toast.makeText(SignupActivity.this, "কাজ নির্বাচন করুন", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        else{
            if(thana.equals("")){
                Toast.makeText(SignupActivity.this, "Select Thana", Toast.LENGTH_SHORT).show();
                return;
            }
            if(district.equals("")){
                Toast.makeText(SignupActivity.this, "Select District", Toast.LENGTH_SHORT).show();
                return;
            }
            if(division.equals("")){
                Toast.makeText(SignupActivity.this, "Select Division", Toast.LENGTH_SHORT).show();
                return;
            }
            if(occu.equals("")){
                Toast.makeText(SignupActivity.this, "Select Works", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if(flag==false||flag2==true){
            if(v.equals("bn")){
                Toast.makeText(this, "ছবি দিন", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this, "Select Photo", Toast.LENGTH_SHORT).show();
            return;
        }
        final ProgressDialog progressDialog = ProgressDialog.show(SignupActivity.this,"","Creating Account, please wait....",true);
        StorageReference ref = storageReference.child(System.currentTimeMillis() + "." + getFileExtention(imguri));
        if (!TextUtils.isEmpty(n)) {
            FirebaseUser user = mAuth.getCurrentUser();
            final String id = user.getUid();
            ref.putFile(imguri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                    while (!uriTask.isSuccessful()) ;
                    Uri downloadUrl = uriTask.getResult();
                    String imageLink = downloadUrl.toString();
                    String active_status = "Active";
                    signupData at = new signupData(n, a, m, occu, division, district, thana, add, y, p, imageLink,active_status);
                    databaseReference.child(id).setValue(at);
                    Toast.makeText(SignupActivity.this, "Account Created Successfully", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                    Intent intent = new Intent(SignupActivity.this, ShoProfileAcivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SignupActivity.this, "Image is not upload Successfully", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
