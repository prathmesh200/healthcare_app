package com.example.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LabTestBookActivity extends AppCompatActivity {

    EditText ename,eaddress,econtact,epincode;
    Button bbook;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);

        ename=findViewById(R.id.fullNamelabtest);
        eaddress=findViewById(R.id.addresslabtest);
        econtact=findViewById(R.id.contactnolabtest);
        epincode=findViewById(R.id.pincodelabtest);
        bbook=findViewById(R.id.booklabtest);
        db=new Database(this);

        Intent intent=getIntent();
        String[] price=intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date=intent.getStringExtra("date");
        String time=intent.getStringExtra("time");

        bbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username =sharedPreferences.getString("username","").toString();

                db.addorder(username,ename.getText().toString(),eaddress.getText().toString(),econtact.getText().toString(),Integer.parseInt(epincode.getText().toString()),date.toString(),time.toString(),Float.parseFloat(price[1].toString()),"lab");
                db.removecard(username,"lab");
                Toast.makeText(getApplicationContext(),"Your Booking is done successfully",Toast.LENGTH_LONG).show();
                startActivity(new Intent(LabTestBookActivity.this,HomeActivity.class));
            }
        });
    }
}