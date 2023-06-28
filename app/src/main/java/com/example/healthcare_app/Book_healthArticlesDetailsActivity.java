package com.example.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Book_healthArticlesDetailsActivity extends AppCompatActivity {

    TextView tv1;
    ImageView img;
    Button btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_health_articles_details);

        tv1=findViewById(R.id.textViewhealtharticledetails);
        img=findViewById(R.id.imageViewhealtharticledetail);
        btnback=findViewById(R.id.buttonhealtharticledetailsback);


        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Book_healthArticlesDetailsActivity.this,healthArticleActivity.class));
            }
        });

        Intent intent=getIntent();
        tv1.setText(intent.getStringExtra("text1"));

        Bundle bundle=getIntent().getExtras();
        if(bundle !=null){
            int resId=bundle.getInt("text2");
            img.setImageResource(resId);
        }

    }
}