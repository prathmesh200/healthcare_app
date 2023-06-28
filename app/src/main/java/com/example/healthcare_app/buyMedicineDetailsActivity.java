package com.example.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class buyMedicineDetailsActivity extends AppCompatActivity {


    TextView tvpackagename,tvtotalcost;
    EditText eddetails;
    Button btnback,btnAddtocard;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_details);

        tvpackagename=findViewById(R.id.textviewbuymedicinedetails2);
        tvtotalcost=findViewById(R.id.textviewtotalcostbuymedicinedetails);
        eddetails=findViewById(R.id.editTextTextMultilinebuymedicinedetails);
        eddetails.setKeyListener(null);
        btnback=findViewById(R.id.buttonbuymedicinedetailsbacksetDetails);
        btnAddtocard=findViewById(R.id.buttonbuymedicinedetailsGotocardsetDetails);
        db=new Database(this);

        Intent intent=getIntent();
        tvpackagename.setText(intent.getStringExtra("text1"));
        eddetails.setText(intent.getStringExtra("text2"));
        tvtotalcost.setText("Total Cost:  "+intent.getStringExtra("text3")+"/-");

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(buyMedicineDetailsActivity.this,buyMEdicineActivity.class));
            }
        });

        btnAddtocard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();
                String product= tvpackagename.getText().toString();
                float price =Float.parseFloat(intent.getStringExtra("text3").toString());

                if(db.checkcard(username,product)==1){
                    Toast.makeText(buyMedicineDetailsActivity.this, "Product already added", Toast.LENGTH_SHORT).show();
                }
                else{
                    //db=new Database(this);
                    db.addcard(username,product,price,"medicine");
                    Toast.makeText(buyMedicineDetailsActivity.this, "Record insert to card", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(buyMedicineDetailsActivity.this,buyMEdicineActivity.class));
                }
            }
        });





    }
}