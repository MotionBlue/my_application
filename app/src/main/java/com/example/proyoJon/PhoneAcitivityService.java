package com.example.proyoJon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PhoneAcitivityService extends AppCompatActivity {
    EditText e1;
    Button b1;
    String v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_acitivity_service);
        e1 = findViewById(R.id.pEt);
        b1 = findViewById(R.id.cotinurBtn);
        Intent intent = getIntent();
        v = intent.getStringExtra("lang");
        if(v.equals("bn")){
            e1.setHint("আপনার ফোন নাম্বার ");
            b1.setText("প্রবেশ");
        }
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = "+88";
                String number  = e1.getText().toString().trim();

                if(number.isEmpty()||number.length()<11){
                    e1.setError("Invalid Number");
                    e1.requestFocus();
                    return;
                }

                String phoneNUmber = "+" +code+number;

                Intent intent = new Intent(PhoneAcitivityService.this,VerifyActivityService.class);
                intent.putExtra("lang",v);
                intent.putExtra("phonenumber",phoneNUmber);
                startActivity(intent);

            }
        });

    }


}
