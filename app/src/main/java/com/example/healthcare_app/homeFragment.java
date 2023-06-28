package com.example.healthcare_app;

import static com.example.healthcare_app.R.*;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;


public class homeFragment extends Fragment implements  View.OnClickListener{
   // Button bg,bback;







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v= inflater.inflate(layout.fragment_home, container, false);



    /*   bback=v.findViewById(id.phisician);
        bg=v.findViewById(id.buttonhome);



        bg.setOnClickListener(this);
        bback.setOnClickListener(this);*/

     /*   bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),HomeActivity.class);
                startActivity(intent);

            }
        });*/
        return v;




    }

   @Override
    public void onClick(View view) {
            switch (view.getId()) {
                case id.buttonhome:
                    Intent intent=new Intent(getActivity(),HomeActivity.class);
                    startActivity(intent);

                    //Start activity one
                    break;
                case id.phisician:
                    //Start activiy two
                    break;
                // Do this for all buttons.
            }
        }
        }

