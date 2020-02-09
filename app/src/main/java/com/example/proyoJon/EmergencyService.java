package com.example.proyoJon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class EmergencyService extends AppCompatActivity {
      TextView t1,t2,t3,num1,num2,num3,num4,num5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_service);
        Intent intent = getIntent();
        String v = intent.getStringExtra("lang");
        t1 = findViewById(R.id.fireTv);
        t2 = findViewById(R.id.ambuTv);
        t3 = findViewById(R.id.policeTv);
        num1 = findViewById(R.id.num1Tv);
        num2 = findViewById(R.id.num2Tv);
        num3 = findViewById(R.id.num3Tv);
        num4 = findViewById(R.id.num4Tv);
        num5 = findViewById(R.id.num5Tv);
        t1.setText("Fire Service");
        num1.setText("02-9001055");
        num2.setText("0196906555");
        t2.setText("Ambulance Service");
        num3.setText("01712244294");
        num4.setText("01515239678");
        t3.setText("Police Service");
        num5.setText("999");
        if(v.equals("bn")){
            t1.setText("অগ্নি - নির্বাপক" );
            t2.setText("অ্যাম্বুল্যান্স");
            t3.setText("পুলিশ/ যেকোন প্রয়োজনে ");
        }
    }
}
