package com.thetimekeepers.timeit.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.thetimekeepers.timeit.R;
import com.thetimekeepers.timeit.list;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClockFragment extends Fragment {

    Button btnadd, btnstop, btnschedule;
    ImageView icanchor;
    Animation roundingalone;
    Chronometer timerHere;

    public list l;


    String test[] = new String [] {"Running","Reading","Studying","Dancing","Reading","Studying","Dancing"};

//        String test[] = (String[]) l.items.toArray();



    public ClockFragment() {
        //required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_stop_watch_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        btnstart = view.findViewById(R.id.btnstart);
        btnstop = view.findViewById(R.id.btn_stop);
        icanchor = view.findViewById(R.id.icanchor);
        timerHere = view.findViewById(R.id.timerHere);
        btnadd = view.findViewById(R.id.btn_add);
        btnschedule = view.findViewById(R.id.btn_schedule);


        //load animations
        roundingalone = AnimationUtils.loadAnimation(getContext(), R.anim.roundingalone);
        ListView listView= (ListView) view.findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,test);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Toast.makeText(getContext(),"starting " +test[position],Toast.LENGTH_SHORT).show();
                timerHere.stop();
                icanchor.startAnimation(roundingalone);
                timerHere.setBase(SystemClock.elapsedRealtime());
                timerHere.start();
            }
        });

        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                icanchor.clearAnimation();
                timerHere.stop();
                Toast.makeText(getContext(),"stopped!",Toast.LENGTH_SHORT).show();
            }
        });

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), list.class);
                startActivity(intent);
            }
        });

        // Create dedicated button with an intent that takes the list of toDo's and takes you back
        // to the original ClickFragment

        btnschedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}