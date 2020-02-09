package com.example.proyoJon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    CardView Electrician,plumber,wood,beauty,internet,interior,home,tutor,myprofile,emergency,elakaCard;
    TextView electTv,plumTv,beautyTv,internetTv,interiorTv,homeTv,tutorTv,myprofiletv,emerTv,woodrTv;
    Spinner location,location2;
    String locate="",worksName;
    String v;
    String selecShohor[] = {"আপনার শহর","ঢাকা","চট্টগ্রাম","রাজশাহী","খুলনা","বরিশাল","সিলেট","রংপুর"};
    String selecShohorEng[] ={"Select City", "Dhaka", "Chottogram", "Rajshahi", "khulna", "Borishal", "syllhet", "rangpur"};
    String locationName[] = {"এলাকার নাম","আদাবর","আজীমপুর","বাড্ডা","চকবাজার","ক্যান্টনমেন্ট","ধানমন্ডি","ডেমরা","গুনশান","হাজারীবাগ","তেজগাও","শেরে বাংলা নগর","কলাবাগান","নিউমার্কেট"};
    String locationNameeng[] = {"Select Your Area", "Adabor", "Ajimpur", "Badda", "Cokbazar", "Cantonment", "Dhanmondi", "Demra", "Gulshan", "Hajaribag", "Tejgao", "Shere bangla nogor", "Kolabagan", "New Market"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = getIntent();
        v = intent.getStringExtra("lang");
        Electrician = findViewById(R.id.electricianCardId);
        plumber = findViewById(R.id.plumberCardId);
        wood = findViewById(R.id.woodMechanicId);
        beauty = findViewById(R.id.beautCardId);
        internet = findViewById(R.id.internetCardId);
        interior = findViewById(R.id.interiorCardId);
        home = findViewById(R.id.homeServiceCardId);
        tutor = findViewById(R.id.homeTutorCardId);
        myprofile = findViewById(R.id.myprofileCardId);
        emergency = findViewById(R.id.EmergencyCardId);
        location = findViewById(R.id.locationSP);
        location2 = findViewById(R.id.location2SP);
        elakaCard = findViewById(R.id.eladaId);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,R.layout.itemview,R.id.textViewId,locationNameeng);
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(this,R.layout.itemview,R.id.textViewId,selecShohorEng);
        location2.setAdapter(arrayAdapter1);

        location2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    Toast.makeText(HomeActivity.this, "select your city", Toast.LENGTH_LONG).show();
                    return;
                }
                if(i==1){
                    elakaCard.setVisibility(View.VISIBLE);
                    location.setAdapter(arrayAdapter);
                    location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            locate = adapterView.getItemAtPosition(i).toString();
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

        electTv = findViewById(R.id.elecTv);
        plumTv = findViewById(R.id.plumberTv);
        woodrTv = findViewById(R.id.woodTv);
        beautyTv = findViewById(R.id.BeautyServiceTv);
        internetTv = findViewById(R.id.internetServiceTv);
        interiorTv = findViewById(R.id.interiorDesignTv);
        homeTv = findViewById(R.id.HomeServiceTv);
        tutorTv = findViewById(R.id.tutorTv);
        myprofiletv = findViewById(R.id.myProfileTv);
        emerTv = findViewById(R.id.EmergencyTv);
        if(v.equals("bn")){
            electTv.setText("ইলেক্ট্রিশিয়ান");
            plumTv.setText("প্লাম্বার");
            woodrTv.setText("কাঠ-মিস্ত্রি");
            beautyTv.setText("বিউটি সার্ভিস");
            internetTv.setText("ইন্টারনেট সার্ভিস");
            interiorTv.setText("ইন্টেরিয়ার ডিজাইনার");
            homeTv.setText("গৃহকর্মী");
            tutorTv.setText("হোম-টিউটর");
            myprofiletv.setText("আমার প্রোফাইল");
            emerTv.setText("জরুরী সেবা");

            final ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(HomeActivity.this,R.layout.itemview,R.id.textViewId,locationName);
            ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<>(HomeActivity.this,R.layout.itemview,R.id.textViewId,selecShohor);
            location2.setAdapter(arrayAdapter3);
            location2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(i==0){
                        Toast.makeText(HomeActivity.this, "আপনার শহর নির্বাচন করুন", Toast.LENGTH_LONG).show();
                        return;
                    }
                    if(i==1){
                        elakaCard.setVisibility(View.VISIBLE);
                        location.setAdapter(arrayAdapter2);
                        location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                locate = locationNameeng[i];
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
        Electrician.setOnClickListener(this);
        plumber.setOnClickListener(this);
        wood.setOnClickListener(this);
        home.setOnClickListener(this);
        beauty.setOnClickListener(this);
        tutor.setOnClickListener(this);
        emergency.setOnClickListener(this);
        interior.setOnClickListener(this);
        internet.setOnClickListener(this);
        emergency.setOnClickListener(this);
        myprofile.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.electricianCardId:


                if(v.equals("bn")){
                    if(locate.equals("Select Your Area")||locate.equals("")){
                        Toast.makeText(HomeActivity.this, "এলাকা নির্বাচন করুন", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                else{
                    if(locate.equals("Select Your Area")||locate.equals("")){
                        Toast.makeText(HomeActivity.this, "Select Your Area", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                worksName = "Electrician";
                Intent intent = new Intent(HomeActivity.this,ShowKormiActivity.class);
                intent.putExtra("location",locate);
                intent.putExtra("workName",worksName);
                intent.putExtra("lang",v);
                startActivity(intent);
                break;

            case R.id.plumberCardId:
                if(v.equals("bn")){
                    if(locate.equals("Select Your Area")||locate.equals("")){
                        Toast.makeText(HomeActivity.this, "এলাকা নির্বাচন করুন", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                else{
                    if(locate.equals("Select Your Area")||locate.equals("")){
                        Toast.makeText(HomeActivity.this, "Select Your Area", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                worksName = "Plumber";
                Intent intent1 = new Intent(HomeActivity.this,ShowKormiActivity.class);
                intent1.putExtra("location",locate);
                intent1.putExtra("workName",worksName);
                intent1.putExtra("lang",v);
                startActivity(intent1);
                break;

            case R.id.woodMechanicId:
                if(v.equals("bn")){
                    if(locate.equals("Select Your Area")||locate.equals("")){
                        Toast.makeText(HomeActivity.this, "এলাকা নির্বাচন করুন", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                else{
                    if(locate.equals("Select Your Area")||locate.equals("")){
                        Toast.makeText(HomeActivity.this, "Select Your Area", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                worksName = "Wood Mechanic";
                Intent intent2 = new Intent(HomeActivity.this,ShowKormiActivity.class);
                intent2.putExtra("location",locate);
                intent2.putExtra("workName",worksName);
                intent2.putExtra("lang",v);
                startActivity(intent2);
                break;

            case R.id.homeTutorCardId:
                if(v.equals("bn")){
                    if(locate.equals("Select Your Area")||locate.equals("")){
                        Toast.makeText(HomeActivity.this, "এলাকা নির্বাচন করুন", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                else{
                    if(locate.equals("Select Your Area")||locate.equals("")){
                        Toast.makeText(HomeActivity.this, "Select Your Area", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                worksName = "Home Tutor";
                Intent intent3 = new Intent(HomeActivity.this,ShowKormiActivity.class);
                intent3.putExtra("location",locate);
                intent3.putExtra("workName",worksName);
                intent3.putExtra("lang",v);
                startActivity(intent3);
                break;

            case R.id.homeServiceCardId:
                if(v.equals("bn")){
                    if(locate.equals("Select Your Area")||locate.equals("")){
                        Toast.makeText(HomeActivity.this, "এলাকা নির্বাচন করুন", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                else{
                    if(locate.equals("Select Your Area")||locate.equals("")){
                        Toast.makeText(HomeActivity.this, "Select Your Area", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                worksName = "Home Service";
                Intent intent4 = new Intent(HomeActivity.this,ShowKormiActivity.class);
                intent4.putExtra("location",locate);
                intent4.putExtra("workName",worksName);
                intent4.putExtra("lang",v);
                startActivity(intent4);
                break;

            case R.id.internetCardId:
                if(v.equals("bn")){
                    if(locate.equals("Select Your Area")||locate.equals("")){
                        Toast.makeText(HomeActivity.this, "এলাকা নির্বাচন করুন", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                else{
                    if(locate.equals("Select Your Area")||locate.equals("")){
                        Toast.makeText(HomeActivity.this, "Select Your Area", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                worksName = "Internet Service";
                Intent intent5 = new Intent(HomeActivity.this,ShowKormiActivity.class);
                intent5.putExtra("location",locate);
                intent5.putExtra("workName",worksName);
                intent5.putExtra("lang",v);
                startActivity(intent5);
                break;

            case R.id.interiorCardId:
                if(v.equals("bn")){
                    if(locate.equals("Select Your Area")||locate.equals("")){
                        Toast.makeText(HomeActivity.this, "এলাকা নির্বাচন করুন", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                else{
                    if(locate.equals("Select Your Area")||locate.equals("")){
                        Toast.makeText(HomeActivity.this, "Select Your Area", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                worksName = "Interior Designer";
                Intent intent6 = new Intent(HomeActivity.this,ShowKormiActivity.class);
                intent6.putExtra("location",locate);
                intent6.putExtra("workName",worksName);
                intent6.putExtra("lang",v);
                startActivity(intent6);
                break;


            case R.id.beautCardId:
                if(v.equals("bn")){
                    if(locate.equals("Select Your Area")||locate.equals("")){
                        Toast.makeText(HomeActivity.this, "এলাকা নির্বাচন করুন", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                else{
                    if(locate.equals("Select Your Area")||locate.equals("")){
                        Toast.makeText(HomeActivity.this, "Select Your Area", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                worksName = "Beauty Service";
                Intent intent7 = new Intent(HomeActivity.this,ShowKormiActivity.class);
                intent7.putExtra("location",locate);
                intent7.putExtra("workName",worksName);
                intent7.putExtra("lang",v);
                startActivity(intent7);
                break;

            case R.id.EmergencyCardId:
                Intent intent8 = new Intent(HomeActivity.this,EmergencyService.class);
                intent8.putExtra("lang",v);
                startActivity(intent8);
                break;

            case R.id.myprofileCardId:

                break;

        }

    }
}
