package com.example.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class labTestSetDetailsActivity extends AppCompatActivity {
    TextView tvpackagename,tvtotalcost;
    EditText ed;
    Button btnaddtocard,btnback;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_set_details);

        tvpackagename=findViewById(R.id.textviewLabTest2);
        tvtotalcost=findViewById(R.id.textviewtotalcost);
        ed=findViewById(R.id.editTextTextMultiline);
        btnback=findViewById(R.id.buttonLabTestbacksetDetails);
        btnaddtocard=findViewById(R.id.buttonLabTestGotocardsetDetails);
        db=new Database(this);
        ed.setKeyListener(null);

        Intent intent=getIntent();
        tvpackagename.setText(intent.getStringExtra("text1"));
        ed.setText(intent.getStringExtra("text2"));
        tvtotalcost.setText("Total Cost:  "+intent.getStringExtra("text3")+"Rs");

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(labTestSetDetailsActivity.this, "Back", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(labTestSetDetailsActivity.this,labTestActivity.class));
            }
        });

        btnaddtocard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();
                String product=   tvpackagename.getText().toString();
                float price =Float.parseFloat(intent.getStringExtra("text3").toString());

                if(db.checkcard(username,product)==1){
                    Toast.makeText(labTestSetDetailsActivity.this, "Product already added", Toast.LENGTH_SHORT).show();
                }
                else{
                    db.addcard(username,product,price,"lab");
                    Toast.makeText(labTestSetDetailsActivity.this, "Record insert to card", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(labTestSetDetailsActivity.this,labTestActivity.class));
                }
            }
        });


    }
}