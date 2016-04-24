package com.example.wimrew.cardbox;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by JC Snider on 4/23/2016.
 */
public class DeckLayout extends RelativeLayout implements View.OnClickListener {

    private CheckBox completedCheckBox;
    private TextView nameTextView;
    private TextView cardCountTextView;
    SharedPreferences pref;

    private Deck deck;
    private DeckDatabase db;
    private Context context;

    public DeckLayout(Context context) {   // used by Android tools
        super(context);
    }

    public DeckLayout(Context context, Deck t) {
        super(context);
        pref = PreferenceManager.getDefaultSharedPreferences(context);
        // set context and get db object
        this.context = context;
        db = new DeckDatabase(context);

        // inflate the layout
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.deck_layout, this, true);

        // get references to widgets
        completedCheckBox = (CheckBox) findViewById(R.id.completedCheckBox);
        nameTextView = (TextView) findViewById(R.id.nameTextView);
        cardCountTextView = (TextView) findViewById(R.id.cardCountTextView);

        // set listeners
        completedCheckBox.setOnClickListener(this);
        this.setOnClickListener(this);

        // set player data on widgets
        setDeck(t);
    }

    public Boolean isChecked() {
        return completedCheckBox.isChecked();
    }

    public void setDeck(Deck t) {
        deck = t;
        nameTextView.setText(deck.getName());
        completedCheckBox.setChecked(deck.getListChecked());
        // Remove the notes if empty
        cardCountTextView.setText(deck.count() + " Cards");
    }

    public void setChecked(Boolean val) {
        completedCheckBox.setChecked(val);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.completedCheckBox:
                if (completedCheckBox.isChecked()){
                    deck.setListChecked(true);
                }
                else {
                    deck.setListChecked(false);
                }
                break;
            default:
                AlertDialog.Builder builder = new AlertDialog.Builder((DeckListActivity)context);
                builder.setTitle("Deck Action");
                builder.setItems(new CharSequence[] {"Learn!", "Edit", "Cancel"},
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        SharedPreferences.Editor editor = pref.edit();
                                        editor.putInt("DeckSelection",deck.getDeckId());
                                        editor.putBoolean("DeckModif",true);
                                        editor.apply();
                                        Intent intent = new Intent((DeckListActivity)context, PlayActivity.class);
                                        ((DeckListActivity)context).startActivity(intent);
                                        break;
                                    case 1:
                                        Intent editIntent = new Intent(context,CardListActivity.class);
                                        editIntent.putExtra("deck", deck.getDeckId());
                                        ((DeckListActivity)context).startActivity(editIntent);
                                        break;
                                    default:
                                        break;
                                }

                            }
                        });

                builder.show();
                break;
        }
    }
}
