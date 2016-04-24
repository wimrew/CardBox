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
public class CardLayout extends RelativeLayout implements View.OnClickListener {

    private CheckBox completedCheckBox;
    private TextView nameTextView;

    private Card card;
    private DeckDatabase db;
    private Context context;
    private Boolean checked = false;

    public CardLayout(Context context) {   // used by Android tools
        super(context);
    }

    public CardLayout(Context context, Card t) {
        super(context);
        // set context and get db object
        this.context = context;
        db = new DeckDatabase(context);

        // inflate the layout
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.card_layout, this, true);

        // get references to widgets
        completedCheckBox = (CheckBox) findViewById(R.id.completedCheckBox);
        nameTextView = (TextView) findViewById(R.id.nameTextView);

        // set listeners
        completedCheckBox.setOnClickListener(this);
        this.setOnClickListener(this);

        // set player data on widgets
        setCard(t);
    }

    public Boolean isChecked() {
        return checked;
    }

    public void setCard(Card t) {
        card = t;
        nameTextView.setText(card.getName());
    }

    public void setChecked(Boolean val) {
        completedCheckBox.setChecked(val);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.completedCheckBox:
                if (completedCheckBox.isChecked()){
                    card.setListChecked(true);
                }
                else {
                    card.setListChecked(false);
                }
                break;
            default:
                Intent intent = new Intent(context, AddEditActivity.class);
                intent.putExtra("editing", true);
                intent.putExtra("cardid",card.getId());
                ((CardListActivity)context).startActivity(intent);
                break;
        }
    }
}
