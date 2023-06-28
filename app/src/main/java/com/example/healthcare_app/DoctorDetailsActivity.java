package com.example.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    TextView tv;
    Button bt;
    private String[][] Doctor_details1=
            {
                    {"Doctor name: Ajit pawar","Hospital Address: vikas Nagar","Exp: 5 Years","Mobile No: 901011121314","600"},
                    {"Doctor name: Soham Tonpe","Hospital Address: Navi peth","Exp: 10 Years","Mobile No: 901011121516","300"},
                    {"Doctor name: Onkar Marbe","Hospital Address: Budhavar peth","Exp: 8 Years","Mobile No: 901011121910","800"},
                    {"Doctor name: Kedar Swami","Hospital Address: JunaPuna Naka","Exp: 6 Years","Mobile No: 901011127659","200"},
                    {"Doctor name: Om parshetti","Hospital Address: Gandhi Nagar","Exp: 15 Years","Mobile No: 901011129047","900"}
            };
    private String[][] Doctor_details2=
            {
                    {"Doctor name: Nagesh Mali","Hospital Address: vikas Nagar","Exp: 4 Years","Mobile No: 901011121314","500"},
                    {"Doctor name: Om Jatala","Hospital Address: Navi peth","Exp: 1 Years","Mobile No: 901011121516","700"},
                    {"Doctor name: Pushkar Jatala","Hospital Address: Budhavar peth","Exp: 7 Years","Mobile No: 901011121910","800"},
                    {"Doctor name: Vikas Das","Hospital Address: JunaPuna Naka","Exp: 2 Years","Mobile No: 901011127659","900"},
                    {"Doctor name: Akshay Malage","Hospital Address: Gandhi Nagar","Exp: 5 Years","Mobile No: 901011129047","300"}
            };
    private String[][] Doctor_details3=
            {
                    {"Doctor name: Onkar yalgonde","Hospital Address: vikas Nagar","Exp: 7 Years","Mobile No: 901011121314","200"},
                    {"Doctor name: Varun Rajpandhare","Hospital Address: Navi peth","Exp: 13 Years","Mobile No: 901011121516","300"},
                    {"Doctor name: Akshay Mashale","Hospital Address: Budhavar peth","Exp: 1 Years","Mobile No: 901011121910","600"},
                    {"Doctor name: Vinod Bhave","Hospital Address: JunaPuna Naka","Exp: 5 Years","Mobile No: 901011127659","900"},
                    {"Doctor name: Yash Thakare","Hospital Address: Gandhi Nagar","Exp: 5 Years","Mobile No: 901011129047","400"}
            };
    private String[][] Doctor_details4=
            {
                    {"Doctor name: Shambhu Shinde","Hospital Address: vikas Nagar","Exp: 3 Years","Mobile No: 901011121314","700"},
                    {"Doctor name: Om Kalshetti","Hospital Address: Navi peth","Exp: 17 Years","Mobile No: 901011121516","300"},
                    {"Doctor name: Rushikesh Koli","Hospital Address: Budhavar peth","Exp: 9 Years","Mobile No: 901011121910","900"},
                    {"Doctor name: Sourabh Lakade","Hospital Address: JunaPuna Naka","Exp: 1 Years","Mobile No: 901011127659","100"},
                    {"Doctor name: Onkar Mali","Hospital Address: Gandhi Nagar","Exp: 9 Years","Mobile No: 901011129047","700"}
            };
    private String[][] Doctor_details5=
            {
                    {"Doctor name: Vinod Shinde","Hospital Address: vikas Nagar","Exp: 2 Years","Mobile No: 901011121314","700"},
                    {"Doctor name: Vikram Dhotre","Hospital Address: Navi peth","Exp: 5 Years","Mobile No: 901011121516","200"},
                    {"Doctor name: Nagesh Mali","Hospital Address: Budhavar peth","Exp: 8 Years","Mobile No: 901011121910","500"},
                    {"Doctor name: Yash Metre","Hospital Address: JunaPuna Naka","Exp: 2 Years","Mobile No: 901011127659","200"},
                    {"Doctor name: Vishal Bhutada","Hospital Address: Gandhi Nagar","Exp: 4 Years","Mobile No: 901011129047","900"}
            };

    String[][] Doctor_details={};
    ArrayList list;
    SimpleAdapter sa;
    HashMap<String,String> item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctordetails);

        tv=findViewById(R.id.doctor_details);
        bt=findViewById(R.id.ddbutton);


        Intent it=getIntent();
        String title=it.getStringExtra("title");
        tv.setText(title);


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DoctorDetailsActivity.this, " Back", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DoctorDetailsActivity.this,findDoctorActivity.class));
            }
        });


        if(title.compareTo("Family_Physician")==0){
            Doctor_details = Doctor_details1;
        }
        else if(title.compareTo("Dietician")==0){
            Doctor_details = Doctor_details2;
        }
        else if(title.compareTo("Dentist")==0){
            Doctor_details = Doctor_details3;
        }
        else if(title.compareTo("Surgen")==0){
            Doctor_details = Doctor_details4;
        }
        else{
            Doctor_details = Doctor_details5;
        }

        list=new ArrayList();
        for(int i=0;i<Doctor_details.length;i++){
            item=new HashMap<String,String>();

            item.put("line1",Doctor_details[i][0]);
            item.put("line2",Doctor_details[i][1]);
            item.put("line3",Doctor_details[i][2]);
            item.put("line4",Doctor_details[i][3]);
            item.put("line5","Cons Fees :"+Doctor_details[i][4]+"Rs");
            list.add(item);

        }
        sa=new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
               new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e} );

        ListView lst=findViewById(R.id.listviewDD);
        lst.setAdapter(sa);


        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(DoctorDetailsActivity.this,Book_Appointment_Activity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",Doctor_details[i][0]);
                it.putExtra("text3",Doctor_details[i][1]);
                it.putExtra("text4",Doctor_details[i][3]);
                it.putExtra("text5",Doctor_details[i][4]);
                startActivity(it);

            }
        });


    }
}