package com.example.wimrew.cardbox;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by JC Snider on 4/23/2016.
 */
public class CardListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Card> cards;

    public CardListAdapter(Context context, ArrayList<Card> cards){
        this.context = context;
        this.cards = cards;
    }

    @Override
    public int getCount() {
        return cards.size();
    }

    @Override
    public Object getItem(int position) {
        return cards.get(position);
    }

    @Override    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CardLayout cardLayout = null;
        Card card = cards.get(position);

        if (convertView == null) {
            cardLayout = new CardLayout(context, card);
        }
        else {
            cardLayout = (CardLayout) convertView;
            cardLayout.setCard(card);
        }
        card.setLayoutElement(cardLayout);
        cardLayout.setChecked(card.getListChecked());
        return cardLayout;
    }
}
