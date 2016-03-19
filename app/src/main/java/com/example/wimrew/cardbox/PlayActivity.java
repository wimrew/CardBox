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
    int currentCardPos;
    ImageView cardimage;
    Card currentCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card);
        initGraphics();
        initDeck();
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
        currentCardPos=0;
        currentCard=currentDeck.getCard(currentCardPos);
        setFrontCard(currentCard);
    }

    private void setFrontCard(Card card) {
        cardimage.setVisibility(View.VISIBLE);
        cardtext.setVisibility(View.VISIBLE);

        if (card.getFrontImagePath()==0){
         cardimage.setVisibility(View.INVISIBLE);
        } else {

            cardimage.setImageResource(currentCard.getFrontImagePath());
        }

        if (card.getFrontText().equals("")){
            cardtext.setVisibility(View.INVISIBLE);
        } else {

            cardtext.setText(currentCard.getFrontText());
        }
    }

    private void setBackCard(Card card){
        cardimage.setVisibility(View.VISIBLE);
        cardtext.setVisibility(View.VISIBLE);

    }

    private void initDeck() {
        currentDeck=new SpanishColor().getDeck();
    }


    private void initGraphics() {

            frontLayout= (LinearLayout) findViewById(R.id.layoutfront);
            backLayout=(LinearLayout) findViewById(R.id.layoutback);
            flip = (Button) findViewById(R.id.flip);
            flip.setOnClickListener(this);
            gotit = (Button) findViewById(R.id.gotit);
            gotit.setOnClickListener(this);
            again = (Button) findViewById(R.id.again);
            again.setOnClickListener(this);
            cardtext= (TextView) findViewById(R.id.cardtext);
            cardimage=(ImageView) findViewById(R.id.cardimage);
        }


    @Override
    public void onClick(View v) {

    }
}
