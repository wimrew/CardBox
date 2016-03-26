package com.example.wimrew.cardbox;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ManageDecksActivity extends AppCompatActivity {
RadioGroup rgroup;
    RadioButton a, b, c;
    SharedPreferences pref;

@Override
    protected void onResume(){
        super.onResume();
        restoreSelectedDeck();
    }

    private void restoreSelectedDeck() {
         pref = PreferenceManager.getDefaultSharedPreferences(this);
        int selection = pref.getInt("Deck",0);
        if (selection==0){
            a.setChecked(true);
        }

        if (selection==1){
            b.setChecked(true);
        }

        if (selection==2){
            c.setChecked(true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.managedecks);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
        initGraphics();
        initPreference();
    }

    private void initPreference() {
         pref = PreferenceManager.getDefaultSharedPreferences(this);
    }

    private void initGraphics() {
        rgroup= (RadioGroup) findViewById(R.id.rgroup);
        a= (RadioButton) findViewById(R.id.a);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("Deck",0);
                editor.apply();
            }
        });
        b= (RadioButton) findViewById(R.id.b);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("Deck",1);
                editor.apply();
            }
        });
        c = (RadioButton) findViewById(R.id.c);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("Deck",2);
                editor.apply();
            }
        });


    }

}
