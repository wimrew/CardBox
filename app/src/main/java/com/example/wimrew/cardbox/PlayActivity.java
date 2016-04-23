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
    boolean paused, front, deckModif;
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
        front=true;

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
        String deckString=objectToString(currentDeck);

        editor.putString("Card", cardString);
        editor.putString("Session", sessionString);
        editor.putString("DeckString", deckString);

        editor.putBoolean("Paused", paused);
        editor.putBoolean("Front", front);

        editor.putBoolean("DeckModif", false);
        editor.apply();


super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        deckModif=pref.getBoolean("DeckModif",true);
        Log.d("stuff","before if");
        if (deckModif==true){
            getDeckSelectionFromPreferences();
            getCorrectAnswerNumberFromPreferences();
            initSessionAndDeck();
            setFirstCard();
            paused=false;
            front=true;
        } else {

            try {
                Card currentCardTemp = (Card) stringToObject(pref.getString("Card", ""));
                StudySession studysessionTemp = (StudySession) stringToObject(pref.getString("Session", ""));
                Deck currentDeckTemp = (Deck) stringToObject(pref.getString("DeckString",""));
                boolean frontTemp = pref.getBoolean("Front", false);
                if ((currentCardTemp!=null)&&(studysessionTemp!=null)) {
                    currentDeck=currentDeckTemp;
                    currentCard=currentCardTemp;
                    front=frontTemp;
                    setFrontCard(currentCard);
                    studysession=studysessionTemp;
                    if (front==false){
                        switchButtonsVisibility();
                        setBackCard(currentCard);
                    }
                }
                Log.d("stuff", "got here");
            } catch (Exception e){
                Log.d("stuff","exceptionstuff");
            }

        }


        getCorrectAnswerNumberFromPreferences();
        studysession.setCorrectLimit(correct);
    }

    private void initPreferencesObject() {
        pref = PreferenceManager.getDefaultSharedPreferences(this);
    }

    private void getDeckSelectionFromPreferences() {
try {
    chosenDeck = pref.getInt("DeckSelection", 0);
} catch (Exception e){Log.d("stuff", "getDeckSelectionFromPreferences exception");
    }}

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
                    front=false;
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
                        front=true;
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
                        front=true;
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
