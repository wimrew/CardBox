package com.example.wimrew.cardbox;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.wimrew.cardbox.R;

public class SettingsActivity extends AppCompatActivity {
    RadioGroup rgroup;
    RadioButton a, b, c;
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initGraphics();
    }
    @Override
    protected void onResume(){
        super.onResume();
        restoreSelectedDeck();
    }

    private void restoreSelectedDeck() {
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        int selection = pref.getInt("correct",1);
        if (selection==1){
            a.setChecked(true);
        }

        if (selection==2){
            b.setChecked(true);
        }

        if (selection==3){
            c.setChecked(true);
        }
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
                editor.putInt("correct",1);
                editor.apply();
            }
        });
        b= (RadioButton) findViewById(R.id.b);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("correct",2);
                editor.apply();
            }
        });
        c = (RadioButton) findViewById(R.id.c);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("correct",3);
                editor.apply();
            }
        });


    }

}
