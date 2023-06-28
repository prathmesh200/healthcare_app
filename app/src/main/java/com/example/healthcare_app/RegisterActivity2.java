package com.example.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity2 extends AppCompatActivity {

    EditText edusername,edpassward,edconpassward,edemail,efullname;
    TextView tregister;
     Database db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        edusername=findViewById(R.id.PersonName);
        edpassward=findViewById(R.id.passward);
        edconpassward=findViewById(R.id.confirmpassward);
        edemail=findViewById(R.id.email);
        efullname=findViewById(R.id.fullName);
        final EditText edphoneno=findViewById(R.id.phoneno);
       final Button bregister=findViewById(R.id.registerbutton1);
        tregister=findViewById(R.id.textview3);
        db=new Database(this);




        tregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity2.this,MainActivity.class));

            }
        });



        edphoneno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (validaMobile(edphoneno.getText().toString())) {
                    bregister.setEnabled(true);
                } else {
                    bregister.setEnabled(false);
                    edphoneno.setError("Invalid Phone no ");
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });



        bregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname=efullname.getText().toString();
                String username=edusername.getText().toString();
                String passward=edpassward.getText().toString();
                String conpassward=edconpassward.getText().toString();
                String email=edemail.getText().toString();
                String phonono=edphoneno.getText().toString();

                if(fullname.length()==0 || username.length()==0 || passward.length()==0 || conpassward.length()==0 || email.length()==0 || phonono.length()==0){
                    Toast.makeText(getApplicationContext(), "Please Fill All details ",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(passward.compareTo(conpassward)==0){
                        if(isvalid(passward) && valiateEmailAddress(edemail)) {


                            Boolean checkuser = db.checkusername(username);
                            if (checkuser == false) {
                                Boolean insert = db.insertData(username,fullname, passward, email, phonono);
                                if (insert == true) {

                                    Toast.makeText(getApplicationContext(), " Successfully Register ", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(RegisterActivity2.this, MainActivity.class));
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"Registration failed",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"User already exist! Please sign in",Toast.LENGTH_SHORT).show();

                            }
                        }

                        else{
                            Toast.makeText(getApplicationContext(),"Passward must contain at least 8 character,having letter,digit and specific symbol",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Passeard and Confirm Passward didn't match ",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });






    }
    public static boolean isvalid(String passwardere){
        int f1=0,f2=0,f3=0;
        if(passwardere.length()<8){
            return false;
        }
        else{
            for(int p=0;p<passwardere.length();p++){
                if(Character.isLetter(passwardere.charAt(p))){
                    f1=1;
                }
            }
            for(int r=0;r<passwardere.length();r++){
                if(Character.isDigit(passwardere.charAt(r))){
                    f2=1;
                }
            }
            for(int s=0;s<passwardere.length();s++){
                char c=passwardere.charAt(s);
                if(c>=33&&c<=46||c==64){
                    f3=1;
                }
            }
            if(f1==1 && f2==1 && f3==1)
                return true;
            return false;
        }
    }


    private boolean valiateEmailAddress(EditText edemail){
        String emilInput=edemail.getText().toString();

        if(!emilInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emilInput).matches()){

            return true;
        }else{
            Toast.makeText(getApplicationContext(),"Invalid Email Address  ",Toast.LENGTH_SHORT).show();
            return false;

        }
    }


    boolean validaMobile(String input){
        Pattern p=Pattern.compile("[6-9][0-9]{9}");

        Matcher m=p.matcher(input);
        return m.matches();
    }


}