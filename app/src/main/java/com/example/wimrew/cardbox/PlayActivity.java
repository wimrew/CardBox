package com.example.wimrew.cardbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity  {
    LinearLayout frontLayout, backLayout;
    Button gotit, flip, again;
    TextView cardtext;
    Deck currentDeck, a, b, c;
    StudySession studysession;
    ArrayList<Deck> decks;

    ImageView cardimage;
    Card currentCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card);
        initGraphics();
        initSessionAndDeck();
        setFirstCard();
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

        // a =new ShapeDeck().getDeck();
        // b= new PresidentDeck().getDeck();
       //  c=new SpanishColorDeck().getDeck();
decks=new ArrayList<Deck>();
        decks.add(new ShapeDeck().getDeck());
        decks.add(new PresidentDeck().getDeck());
        decks.add(new SpanishColorDeck().getDeck());
        currentDeck=decks.get(0);
        studysession=new StudySession(currentDeck);

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
}
