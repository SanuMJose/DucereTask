package com.sanu.duceretask.Activities;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import com.sanu.duceretask.Fragments.PuzzleFragment;
import com.sanu.duceretask.R;

public class MainActivity extends AppCompatActivity {

    Button generate_puzzle,solve_puzzle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intilaize_views();
        generate_puzzle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generate_puzzle.setBackgroundResource(R.color.grey);
                solve_puzzle.setBackgroundResource(R.color.red);

                PuzzleFragment frag = new PuzzleFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.add(R.id.fragment_container, frag);
                fragmentTransaction.commit();

            }
        });
        solve_puzzle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generate_puzzle.setBackgroundResource(R.color.blue);
                solve_puzzle.setBackgroundResource(R.color.grey);

            }
        });

    }

    public void intilaize_views() {
        generate_puzzle = (Button) findViewById(R.id.btn_generatepuzzle);
        solve_puzzle= (Button) findViewById(R.id.btn_solvefarm);

    }


}