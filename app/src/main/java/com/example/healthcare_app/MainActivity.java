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

public class MainActivity extends AppCompatActivity {
    EditText edusername,edpassward;
    Button blogin;
    TextView tv;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edusername=findViewById(R.id.PersonName);
        edpassward=findViewById(R.id.passward);
        blogin=findViewById(R.id.button1);
        tv=findViewById(R.id.textview3);
        db=new Database(this);

        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edusername.getText().toString();
                String passward = edpassward.getText().toString();

                if (username.length() == 0 || passward.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill all details ", Toast.LENGTH_SHORT).show();
                }else {
                    Boolean checkuserpass=db.checkusernamepassward(username,passward);
                    if(checkuserpass==true && isvalid(passward)){
                        SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor= sharedPreferences.edit();
                        editor.putString("username",username);
                        editor.apply();
                        Toast.makeText(getApplicationContext(), "Login Success ", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,HomeActivity.class));


                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Invalid User Id and Passward ", Toast.LENGTH_SHORT).show();

                    }




                }
            }

        });

    /*    blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edusername.getText().toString();
                String passward = edpassward.getText().toString();
                Database db=new Database(getApplicationContext(),"healthcare_App",null,1);
                if(db.login(username,passward)==1){
                    SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor= sharedPreferences.edit();
                    editor.putString("username",username);
                    editor.apply();
                    startActivity(new Intent(MainActivity.this,HomeActivity.class));
                }
                else{
                    Toast.makeText(getApplicationContext(), "Invalid username and passward ", Toast.LENGTH_SHORT).show();


                }


            }
        });*/

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,RegisterActivity2.class));

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

}