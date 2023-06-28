package com.example.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class findDoctorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finddoctor);

        CardView back=findViewById(R.id.cardfdback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(findDoctorActivity.this, "Successfully Back", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(findDoctorActivity.this,HomeActivity.class));

            }
        });

        CardView familyphisician=findViewById(R.id.cardfdfamilyphysician);
        familyphisician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(findDoctorActivity.this,DoctorDetailsActivity.class);
                it.putExtra("title","Family_Physician");
                startActivity(it);
            }
        });

        CardView dietician=findViewById(R.id.cardfddiatician);
        dietician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(findDoctorActivity.this,DoctorDetailsActivity.class);
                it.putExtra("title","Dietician");
                startActivity(it);
            }
        });

        CardView dentist=findViewById(R.id.cardfdDentist);
        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(findDoctorActivity.this,DoctorDetailsActivity.class);
                it.putExtra("title","Dentist");
                startActivity(it);
            }
        });

        CardView surgeen=findViewById(R.id.cardfdsurgen);
        surgeen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(findDoctorActivity.this,DoctorDetailsActivity.class);
                it.putExtra("title","Surgen");
                startActivity(it);
            }
        });
        CardView cardiologists=findViewById(R.id.cardfdcardidlogists);
        cardiologists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(findDoctorActivity.this,DoctorDetailsActivity.class);
                it.putExtra("title","Cardiologists");
                startActivity(it);
            }
        });



    }
}