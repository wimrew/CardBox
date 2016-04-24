package com.example.wimrew.cardbox;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;


import com.google.tabmanager.TabManager;

import java.util.ArrayList;

public class DeckListActivity extends AppCompatActivity {
    DeckDatabase db;
    ArrayList<Deck> decks;
    private ListView taskListView;
    SharedPreferences pref;
    boolean paused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_list);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        taskListView = (ListView)findViewById(R.id.taskListView);
        // get database
        db = new DeckDatabase(getApplicationContext());
        if (savedInstanceState != null) {
            onResume();
            return;
        }
        if (decks == null) decks = db.getDecks();
        refreshTaskList();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_deck_list, menu);
        return true;
    }

    public void refreshTaskList() {
        // get task list for current tab from database
        Context context = getApplicationContext();
        // create adapter and set it in the ListView widget
        DeckListAdapter adapter = new DeckListAdapter(this, decks);
        taskListView.setAdapter(adapter);
    }

    public Deck getLayoutDeck(int index) {
        return (Deck)taskListView.getAdapter().getItem(index);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final SharedPreferences settings;
        switch (item.getItemId()){
            case R.id.menuAddTask:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                final EditText input = new EditText(this);
                input.setHint("Deck Name");
                alert.setView(input);
                String out;
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    //@Override
                    public void onClick(DialogInterface dialog, int which) {
                        String value = input.getText().toString().trim();
                        if (!value.equals("")){
                            long index = db.addDeck(value);
                            Intent editIntent = new Intent(DeckListActivity.this,CardListActivity.class);
                            editIntent.putExtra("deck", (int)index);
                            startActivity(editIntent);
                            decks = db.getDecks();
                            refreshTaskList();
                        }
                    }
                });
                alert.show();
                break;
            case R.id.menuDelete:
                for (int i = 0; i < decks.size(); i++){
                    if (getLayoutDeck(i).getLayoutElement() != null && getLayoutDeck(i).getListChecked()){
                        db.deleteDeck(decks.get(i).getDeckId());
                    }
                }
                decks = db.getDecks();
                // Refresh list
                refreshTaskList();
                break;
            case R.id.action_settings:
                Intent intent = new Intent(DeckListActivity.this, SettingsActivity.class);
                DeckListActivity.this.startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause(){
        paused=true;
        SharedPreferences.Editor editor = pref.edit();
        for (int i = 0; i < decks.size(); i++) {
            editor.putBoolean("deck" + decks.get(i).getDeckId(), decks.get(i).getListChecked());
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
            decks = db.getDecks();
            for (int i = 0; i < decks.size(); i++) {
                decks.get(i).setListChecked(pref.getBoolean("deck" + decks.get(i).getDeckId(),false));
            }
            refreshTaskList();
            paused = false;
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("Paused", paused);
            editor.apply();
        }
    }
}
