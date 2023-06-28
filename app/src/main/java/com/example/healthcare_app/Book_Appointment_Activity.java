package com.example.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Book_Appointment_Activity extends AppCompatActivity {
EditText ed1,ed2,ed3,ed4;
TextView t1;
Database db;
private DatePickerDialog datePickerDialog;
private TimePickerDialog timePickerDialog;
private Button dateButton,timeButton,btnbook,btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        t1=findViewById(R.id.TextviewApptitle);
        ed1=findViewById(R.id.fullNameBA);
        ed2=findViewById(R.id.AddressBA);
        ed3=findViewById(R.id.phonenoBA);
        ed4=findViewById(R.id.FeesBA);
        dateButton=findViewById(R.id.buttonBADATE);
        timeButton=findViewById(R.id.buttonBATIME);
        btnbook=findViewById(R.id.buttonBookAppointment);
        btnback=findViewById(R.id.buttonBookAppointmentback);
        db=new Database(this);

        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        Intent it=getIntent();
        String title=it.getStringExtra("text1");
        String fullname=it.getStringExtra("text2");
        String address=it.getStringExtra("text3");
        String contact=it.getStringExtra("text4");
        String fees=it.getStringExtra("text5");

        t1.setText(title);
        ed1.setText(fullname);
        ed2.setText(address);
        ed3.setText(contact);
        ed4.setText("cons Fees:"+fees+"Rs");

        //datepicker
        initDatePicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        //timepicker
        initTimePicker();
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });

       btnback.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(Book_Appointment_Activity.this,findDoctorActivity.class));
           }
       });
        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username =sharedPreferences.getString("username","").toString();

                if(db.checkAppointmentExists(username,title+" => "+fullname,address,contact,dateButton.getText().toString(),timeButton.getText().toString())==1){
                    Toast.makeText(Book_Appointment_Activity.this, "Appointment already Booked ", Toast.LENGTH_SHORT).show();
                }
                else{
                    db.addorder(username,title+" => "+fullname,address,contact,0,dateButton.getText().toString(),timeButton.getText().toString(),Float.parseFloat(fees),"appointment");
                    Toast.makeText(Book_Appointment_Activity.this, "Your appointment is done successfully ", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Book_Appointment_Activity.this,HomeActivity.class));
                }


            }
        });

    }
    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1=i1+1;
                dateButton.setText(i2+"/"+i1+"/"+i);
            }
        };

        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DAY_OF_MONTH);

        int style= AlertDialog.THEME_HOLO_DARK;
        datePickerDialog=new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);
    }

    private void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                timeButton.setText(i+":"+i1);

            }
        };
        Calendar cal=Calendar.getInstance();
        int hrs=cal.get(Calendar.HOUR);
        int mins=cal.get(Calendar.MINUTE);

        int style=AlertDialog.THEME_HOLO_DARK;
        timePickerDialog=new TimePickerDialog(this,style,timeSetListener,hrs,mins,true);

    }
}