package com.example.proyoJon;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private Button signup,confirm,confirm1;
    private RadioGroup radioGroup;
    private RadioButton radioButton1,radioButton2,radioButton3,radioButton4;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    public static final String MY_PREFS_NAM = "MyPrefsFil";
    Spinner language;
    String lang[] = {"বাংলা","English"};
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signup = findViewById(R.id.signUpBtn);
        t1 = findViewById(R.id.re);
        language = findViewById(R.id.languageSelect);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,R.layout.itemview,R.id.textViewId,lang);
        language.setAdapter(arrayAdapter);
        language.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==1){
                    t1.setText("Are you new here? Register");
                    signup.setText("Here");
                }
                if(i==0){
                    t1.setText("আপনি কি নতুন?  নিবন্ধন করুন");
                    signup.setText("এখানে");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(signup.getText().toString().equals("Here")){
                   showDialog2();
                }
                else{
                    showDialog();
                }
            }
        });




    }

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null) {
            SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
            String name = prefs.getString("name", "No name defined");
            if(name.equals("OK")){
                Intent intent = new Intent(this,ShoProfileAcivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
            else{
                SharedPreferences pref = getSharedPreferences(MY_PREFS_NAM, MODE_PRIVATE);
                String nam = pref.getString("nam", "No name defined");
                if(nam.equals("EngOk")){
                    Intent intent = new Intent(this,HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("lang","en");
                    startActivity(intent);
                }
               else{
                    Intent intent = new Intent(this,HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("lang","bn");
                    startActivity(intent);
                }
            }
        }
    }

    public void showDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View v = getLayoutInflater().inflate(R.layout.selectdialog,null);
        confirm = v.findViewById(R.id.dialogConfirmBtn);
        radioButton1 = v.findViewById(R.id.KRB2);
        radioButton2 = v.findViewById(R.id.SRB2);

        builder.setView(v);
        final AlertDialog dialog = builder.create();
        dialog.show();

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(radioButton1.isChecked()){
                    Intent intent = new Intent(MainActivity.this,PhoneActivityWorker.class);
                    intent.putExtra("lang","bn");
                    startActivity(intent);
                    dialog.dismiss();
                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putString("name","OK");
                    editor.apply();
                }
             if(radioButton2.isChecked()){
                 Intent intent = new Intent(MainActivity.this,PhoneAcitivityService.class);
                 intent.putExtra("lang","bn");
                 startActivity(intent);
                 dialog.dismiss();
             }
            }
        });
    }
   public void showDialog2()
   {
       AlertDialog.Builder builder = new AlertDialog.Builder(this);
       View v = getLayoutInflater().inflate(R.layout.selectdialogeng, null);
       confirm1 = v.findViewById(R.id.dialogBtn);
       radioButton3 = v.findViewById(R.id.KRB3);
       radioButton4 = v.findViewById(R.id.SRB3);
       builder.setView(v);
       final AlertDialog dialog = builder.create();
       dialog.show();
       confirm1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(radioButton3.isChecked()){
                   Intent intent = new Intent(MainActivity.this,PhoneActivityWorker.class);
                   intent.putExtra("lang","en");
                   startActivity(intent);
                   dialog.dismiss();
                   SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                   editor.putString("name","OK");
                   editor.apply();
               }
              if(radioButton4.isChecked()){
                  Intent intent = new Intent(MainActivity.this,PhoneAcitivityService.class);
                  intent.putExtra("lang","en");
                  startActivity(intent);
                  dialog.dismiss();
                  SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAM, MODE_PRIVATE).edit();
                  editor.putString("nam","EngOk");
                  editor.apply();
              }
           }
       });
   }
}
