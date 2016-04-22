package com.example.wimrew.cardbox;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/**
 * Created by JC Snider on 4/21/2016.
 */
public class DeckDatabase {
    // database constants
    public static final String DB_NAME = "cardbox.db";
    public static final int    DB_VERSION = 1;

    // deck table constants
    public static final String DECK_TABLE = "deck";

    public static final String DECK_ID = "_id";
    public static final int    DECK_ID_COL = 0;

    public static final String DECK_NAME = "deck_name";
    public static final int    DECK_NAME_COL = 1;

    // card table constants
    public static final String CARD_TABLE = "cards";

    public static final String CARD_ID = "_id";
    public static final int    CARD_ID_COL = 0;

    public static final String CARD_DECK_ID = "deck_id";
    public static final int    CARD_DECK_ID_COL = 1;

    public static final String CARD_FRONT_TEXT = "front_text";
    public static final int    CARD_FRONT_TEXT_COL = 2;

    public static final String CARD_BACK_TEXT = "back_text";
    public static final int    CARD_BACK_TEXT_COL = 3;

    public static final String CARD_FRONT_IMAGE = "front_image";
    public static final int    CARD_FRONT_IMAGE_COL = 4;

    public static final String CARD_BACK_IMAGE = "back_image";
    public static final int    CARD_BACK_IMAGE_COL = 5;

    // CREATE and DROP TABLE statements
    public static final String CREATE_DECK_TABLE =
            "CREATE TABLE " + DECK_TABLE + " (" +
                    DECK_ID   + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DECK_NAME + " TEXT    UNIQUE)";

    public static final String CREATE_CARD_TABLE =
            "CREATE TABLE " + CARD_TABLE + " (" +
                    CARD_ID         + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CARD_DECK_ID    + " INTEGER, " +
                    CARD_FRONT_TEXT       + " TEXT, " +
                    CARD_BACK_TEXT      + " TEXT, " +
                    CARD_FRONT_IMAGE  + " INT, " +
                    CARD_BACK_IMAGE     + " INT)";

    public static final String DROP_DECK_TABLE =
            "DROP TABLE IF EXISTS " + DECK_TABLE;

    public static final String DROP_CARD_TABLE =
            "DROP TABLE IF EXISTS " + CARD_TABLE;

    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name,
                        CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // create tables
            db.execSQL(CREATE_DECK_TABLE);
            db.execSQL(CREATE_CARD_TABLE);

            //Insert Shape Deck
            //Insert Shape Cards

            //Insert President Deck

            //Insert our spanish color deck
            //Insert the Default Spanish Color Cards
        }

        public void addToDeck(SQLiteDatabase db, Card card, int deckIndex) {
            db.execSQL("INSERT INTO " + CARD_TABLE + " (" + CARD_DECK_ID + "," + CARD_FRONT_TEXT + "," + CARD_BACK_TEXT + "," +
                    CARD_FRONT_IMAGE + "," + CARD_BACK_IMAGE + ") VALUES (" + deckIndex + ", '" + card.getFrontText() + "','" + card.getBackText() + "'," +
                    card.getFrontImagePath() + "," + card.getBackImagePath() + ")");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db,
                              int oldVersion, int newVersion) {

            Log.d("Task list", "Upgrading db from version "
                    + oldVersion + " to " + newVersion);

            Log.d("Task list", "Deleting all data!");
            db.execSQL(DeckDatabase.DROP_DECK_TABLE);
            db.execSQL(DeckDatabase.DROP_CARD_TABLE);
            onCreate(db);
        }
    }

    // database object and database helper object
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    // constructor
    public DeckDatabase(Context context) {
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
    }

    // private methods
    private void openReadableDB() {
        db = dbHelper.getReadableDatabase();
    }

    private void openWriteableDB() {
        db = dbHelper.getWritableDatabase();
    }

    private void closeDB() {
        if (db != null)
            db.close();
    }

    public ArrayList<Deck> getDecks() {
        this.openReadableDB();
        Cursor cursor = db.query(DECK_TABLE, null,
                null, null,
                null, null, null);
        ArrayList<Deck> decks = new ArrayList<Deck>();
        while (cursor.moveToNext()) {
            decks.add(getDeck(cursor.getString(DECK_NAME_COL)));
        }
        if (cursor != null)
            cursor.close();
        this.closeDB();
        return decks;
    }

    public Deck getDeck(String name) {
        String where = DECK_NAME + "= ?";
        String[] whereArgs = { name };

        openReadableDB();
        Cursor cursor = db.query(DECK_TABLE, null,
                where, whereArgs, null, null, null);
        Deck deck = null;
        cursor.moveToFirst();
        deck = new Deck();
        ArrayList<Card> deckCards = getCards(cursor.getInt(DECK_ID_COL));
        for (int i = 0; i < deckCards.size(); i++) {
            deck.addCard(deckCards.get(i));
        }
        cursor.close();
        this.closeDB();

        return deck;
    }

    public ArrayList<Card> getCards(int deckId) {
        String where =
                CARD_DECK_ID + "=" + deckId;

        this.openReadableDB();
        Cursor cursor = db.query(CARD_TABLE, null,
                where, null,
                null, null, null);
        ArrayList<Card> cards = new ArrayList<Card>();
        while (cursor.moveToNext()) {
            cards.add(getCardFromCursor(cursor));
        }
        if (cursor != null)
            cursor.close();
        this.closeDB();
        return cards;
    }

    private static Card getCardFromCursor(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0){
            return null;
        }
        else {
            try {
                Card card = new Card(
                        cursor.getString(CARD_FRONT_TEXT_COL),
                        cursor.getString(CARD_BACK_TEXT_COL),
                        cursor.getInt(CARD_FRONT_IMAGE_COL),
                        cursor.getInt(CARD_BACK_IMAGE_COL));
                return card;
            }
            catch(Exception e) {
                return null;
            }
        }
    }

}