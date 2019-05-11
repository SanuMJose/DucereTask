package com.sanu.duceretask.Activities;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.sanu.duceretask.Fragments.PuzzleFragment;
import com.sanu.duceretask.Interfaces.ButtonInterface;
import com.sanu.duceretask.Interfaces.Button_fragment_interface;
import com.sanu.duceretask.R;

public class MainActivity extends AppCompatActivity implements ButtonInterface,Button_fragment_interface{

Button_fragment_interface button_fragment_interface;
    Button generate_puzzle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intilaize_views();
        generate_puzzle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generate_puzzle.setBackgroundResource(R.color.grey);
                button_fragment_interface.button_colour_fragment();

                PuzzleFragment frag = new PuzzleFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.add(R.id.fragment_container, frag);
                fragmentTransaction.commit();

            }
        });

    }

    public void intilaize_views() {
        generate_puzzle = (Button) findViewById(R.id.btn_generatepuzzle);
        button_fragment_interface=(Button_fragment_interface)this;


    }


    @Override
    public void button_colour() {
        generate_puzzle.setBackgroundResource(R.color.blue);

    }

    @Override
    public void button_colour_fragment() {

    }
}