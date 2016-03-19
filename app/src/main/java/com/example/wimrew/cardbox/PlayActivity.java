package com.example.wimrew.cardbox;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout frontLayout, backLayout;
    Button gotit, flip, again;
    TextView cardtext;
    Deck currentDeck;
    StudySession studysession;

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
        currentDeck=new SpanishColor().getDeck();
        studysession=new StudySession(currentDeck);

    }


    private void initGraphics() {

            frontLayout= (LinearLayout) findViewById(R.id.layoutfront);
            backLayout=(LinearLayout) findViewById(R.id.layoutback);
            flip = (Button) findViewById(R.id.flip);
            flip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    frontLayout.setVisibility(View.GONE);
                    backLayout.setVisibility(View.VISIBLE);
                    setBackCard(currentCard);
                }
            });
            gotit = (Button) findViewById(R.id.gotit);
            gotit.setOnClickListener(this);
            again = (Button) findViewById(R.id.again);
            again.setOnClickListener(this);
            cardtext= (TextView) findViewById(R.id.cardtext);
            cardimage=(ImageView) findViewById(R.id.cardimage);
        }


    @Override
    public void onClick(View v) {
        //the gotit and again buttons, for now
        frontLayout.setVisibility(View.VISIBLE);
        backLayout.setVisibility(View.GONE);
        currentCard=studysession.getNextCard();

    }
}