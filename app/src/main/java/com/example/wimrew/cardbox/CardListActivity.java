package com.example.wimrew.cardbox;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Map;

public class CardListActivity extends AppCompatActivity {

    EditText nameEditText;
    DeckDatabase db;
    Deck deck;
    private ListView taskListView;
    SharedPreferences pref;
    ArrayList<Card> cards;
    Map<Integer,Boolean> checks;
    boolean paused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        // get edit mode from intent
        Intent intent = getIntent();
        int deckId = intent.getIntExtra("deck", 0);
        nameEditText = (EditText)findViewById(R.id.deckEditText);
        db = new DeckDatabase(this);
        taskListView = (ListView)findViewById(R.id.taskListView);
        if (savedInstanceState != null) {
            onResume();
            return;
        }
        deck = db.getDeck(deckId);
        cards = deck.getCards();
        nameEditText.setText(deck.getName());
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("Paused", false);
        editor.apply();
        refreshTaskList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_card_list, menu);
        return true;
    }


    public void refreshTaskList() {
        // get task list for current tab from database
        Context context = getApplicationContext();
        cards = deck.getCards();

        // create adapter and set it in the ListView widget
        CardListAdapter adapter = new CardListAdapter(this, cards);
        taskListView.setAdapter(adapter);
    }

    public Card getLayoutCard(int index) {
        return (Card)taskListView.getAdapter().getItem(index);
    }

    private void saveToDB() {
        // get data from widgets
        String deckName = nameEditText.getText().toString();

        // if no player name, exit method
        if (deckName == null || deckName.trim().equals("")) {
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(this, "Deck must have a name!", duration);
            toast.show();
            return;
        }

        // put data in player
        deck.setName(deckName.trim());

        db.updateDeck(deck);
        this.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final SharedPreferences settings;
        switch (item.getItemId()){
            case R.id.menuAddTask:
                Intent intent = new Intent(this, AddEditActivity.class);
                intent.putExtra("editing", false);
                intent.putExtra("deckid", deck.getDeckId());
                intent.putExtra("cardid", -1);
                startActivity(intent);
                break;
            case R.id.menuDelete:
                for (int i = 0; i < cards.size(); i++){
                    if (getLayoutCard(i).getLayoutElement() != null && getLayoutCard(i).getListChecked()){
                        db.deleteCard(cards.get(i).getId());
                    }
                }
                deck = db.getDeck(deck.getDeckId());
                cards = deck.getCards();
                // Refresh list
                refreshTaskList();
                break;
            case R.id.menuSave:
                saveToDB();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause(){
        paused=true;
        SharedPreferences.Editor editor = pref.edit();

        editor.putInt("editingDeck", deck.getDeckId());
        editor.putString("deckName", nameEditText.getText().toString());
        for (int i = 0; i < cards.size(); i++) {
            editor.putBoolean("card" + cards.get(i).getId(), cards.get(i).getListChecked());
        }

        editor.putBoolean("Paused", paused);
        editor.apply();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        paused = pref.getBoolean("Paused", false);
        if (paused) {

            int deckId = pref.getInt("editingDeck", 0);
            deck = db.getDeck(deckId);
            nameEditText.setText(pref.getString("deckName", "Deck Name"));
            cards = deck.getCards();
            for (int i = 0; i < cards.size(); i++) {
                cards.get(i).setListChecked(pref.getBoolean("card" + cards.get(i).getId(),false));
            }
            refreshTaskList();
            paused = false;
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("Paused", paused);
            editor.apply();
        }
    }
}
