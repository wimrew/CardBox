package com.example.wimrew.cardbox;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by JC Snider on 4/23/2016.
 */
public class DeckListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Deck> decks;

    public DeckListAdapter(Context context, ArrayList<Deck> decks){
        this.context = context;
        this.decks = decks;
    }

    @Override
    public int getCount() {
        return decks.size();
    }

    @Override
    public Object getItem(int position) {
        return decks.get(position);
    }

    @Override    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DeckLayout deckLayout = null;
        Deck deck = decks.get(position);

        if (convertView == null) {
            deckLayout = new DeckLayout(context, deck);
        }
        else {
            deckLayout = (DeckLayout) convertView;
            deckLayout.setDeck(deck);
        }
        deck.setLayoutElement(deckLayout);
        deckLayout.setChecked(deck.getListChecked());
        return deckLayout;
    }
}
