package com.example.wimrew.cardbox;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64InputStream;
import android.util.Base64OutputStream;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity  {
    LinearLayout frontLayout, backLayout;
    Button gotit, flip, again;
    TextView cardtext;
    Deck currentDeck, a, b, c;
    StudySession studysession;
    ArrayList<Deck> decks;
    SharedPreferences pref;
    int chosenDeck, correct;
    boolean paused;
    DeckDatabase deckDatabase;

    ImageView cardimage;
    Card currentCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card);
        deckDatabase = new DeckDatabase(this);
        initGraphics();
        initPreferencesObject();
        getDeckSelectionFromPreferences();
        getCorrectAnswerNumberFromPreferences();
        initSessionAndDeck();
        setFirstCard();
        paused=false;
/*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
    }



    @Override
    protected void onPause(){
        paused=true;
        SharedPreferences.Editor editor = pref.edit();
        String cardString=objectToString(currentCard);
        String sessionString=objectToString(studysession);
        editor.putString("Card", cardString);
        editor.putString("Session", sessionString);
        editor.putBoolean("Paused", paused);
        editor.apply();


super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*
        paused=pref.getBoolean("Paused",false);
        Log.d("stuff","before if");
        if (paused==true) {
            currentCard = (Card) stringToObject(pref.getString("Card", ""));
            studysession = (StudySession) stringToObject(pref.getString("Session", ""));
            setFrontCard(currentCard);
            Log.d("stuff","got here");
        }
        */

        getCorrectAnswerNumberFromPreferences();
        studysession.setCorrectLimit(correct);
    }

    private void initPreferencesObject() {
        pref = PreferenceManager.getDefaultSharedPreferences(this);
    }

    private void getDeckSelectionFromPreferences() {

          chosenDeck = pref.getInt("Deck", 0);
    }

    private void getCorrectAnswerNumberFromPreferences() {

        correct = pref.getInt("correct", 1);
    }

    private void setFirstCard() {

        currentCard=studysession.getNextCard();
        setFrontCard(currentCard);
    }

    private void setFrontCard(Card card) {
        cardimage.setVisibility(View.VISIBLE);
        cardtext.setVisibility(View.VISIBLE);

        if (card.getFrontImagePath()==0){
         cardimage.setVisibility(View.GONE);
        } else {

            cardimage.setImageResource(currentCard.getFrontImagePath());
        }

        if (card.getFrontText().equals("")){
            cardtext.setVisibility(View.GONE);
        } else {

            cardtext.setText(currentCard.getFrontText());
        }
    }

    private void setBackCard(Card card){
        cardimage.setVisibility(View.VISIBLE);
        cardtext.setVisibility(View.VISIBLE);
        if (card.getBackImagePath()==0){
            cardimage.setVisibility(View.GONE);
        } else {

            cardimage.setImageResource(currentCard.getBackImagePath());
        }

        if (card.getBackText().equals("")){
            cardtext.setVisibility(View.GONE);
        } else {

            cardtext.setText(currentCard.getBackText());
        }
    }

    private void initSessionAndDeck() {
        decks=deckDatabase.getDecks();
        //chosenDeck, a value between 0 and the number of decks
        currentDeck=decks.get(chosenDeck);
        studysession=new StudySession(currentDeck);
        studysession.setCorrectLimit(correct);

    }


    private void initGraphics() {

            frontLayout= (LinearLayout) findViewById(R.id.layoutfront);
            backLayout=(LinearLayout) findViewById(R.id.layoutback);
            flip = (Button) findViewById(R.id.flip);
            flip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switchButtonsVisibility();
                    setBackCard(currentCard);
                }
            });
            gotit = (Button) findViewById(R.id.gotit);
            gotit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    studysession.markCorrect();
                    if (studysession.isDone()){
                        PlayActivity.this.finish();
                    } else {
                        switchButtonsVisibility();
                        currentCard = studysession.getNextCard();
                        setFrontCard(currentCard);
                    }
                }
            });
            again = (Button) findViewById(R.id.again);
            again.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    studysession.markIncorrect();
                    if (studysession.isDone()){
                        PlayActivity.this.finish();
                    } else {
                        switchButtonsVisibility();
                        currentCard = studysession.getNextCard();
                        setFrontCard(currentCard);
                    }
                }
            });
            cardtext= (TextView) findViewById(R.id.cardtext);
            cardimage=(ImageView) findViewById(R.id.cardimage);
        }




    public void switchButtonsVisibility(){
        if (frontLayout.getVisibility()==View.VISIBLE){
            frontLayout.setVisibility(View.GONE);
        } else
        {
            frontLayout.setVisibility(View.VISIBLE);
        }
        if (backLayout.getVisibility()==View.VISIBLE){
            backLayout.setVisibility(View.GONE);
        }else
        {
            backLayout.setVisibility(View.VISIBLE);
        }
    }

    public static String objectToString(Serializable object) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            new ObjectOutputStream(out).writeObject(object);
            byte[] data = out.toByteArray();
            out.close();

            out = new ByteArrayOutputStream();
            Base64OutputStream b64 = new Base64OutputStream(out, 0);
            b64.write(data);
            b64.close();
            out.close();

            return new String(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object stringToObject(String encodedObject) {
        try {
            return new ObjectInputStream(new Base64InputStream(
                    new ByteArrayInputStream(encodedObject.getBytes()), 0)).readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
