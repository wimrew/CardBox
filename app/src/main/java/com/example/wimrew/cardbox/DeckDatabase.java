package com.example.wimrew.cardbox;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.BitmapFactory;
import android.util.Log;
/**
 * Created by JC Snider on 4/21/2016.
 */
public class DeckDatabase {
    // database constants
    public static final String DB_NAME = "cardbox.db";
    public static final int    DB_VERSION = 4;

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

    public static final String CARD_NAME_ID = "card_name";
    public static final int    CARD_NAME_ID_COL = 2;

    public static final String CARD_FRONT_TEXT = "front_text";
    public static final int    CARD_FRONT_TEXT_COL = 3;

    public static final String CARD_BACK_TEXT = "back_text";
    public static final int    CARD_BACK_TEXT_COL = 4;

    public static final String CARD_FRONT_IMAGE = "front_image";
    public static final int    CARD_FRONT_IMAGE_COL = 5;

    public static final String CARD_BACK_IMAGE = "back_image";
    public static final int    CARD_BACK_IMAGE_COL = 6;

    // CREATE and DROP TABLE statements
    public static final String CREATE_DECK_TABLE =
            "CREATE TABLE " + DECK_TABLE + " (" +
                    DECK_ID   + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DECK_NAME + " TEXT    UNIQUE)";

    public static final String CREATE_CARD_TABLE =
            "CREATE TABLE " + CARD_TABLE + " (" +
                    CARD_ID         + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CARD_DECK_ID    + " INTEGER, " +
                    CARD_NAME_ID    + " TEXT, " +
                    CARD_FRONT_TEXT       + " TEXT, " +
                    CARD_BACK_TEXT      + " TEXT, " +
                    CARD_FRONT_IMAGE  + " TEXT, " +
                    CARD_BACK_IMAGE     + " TEXT)";

    public static final String DROP_DECK_TABLE =
            "DROP TABLE IF EXISTS " + DECK_TABLE;

    public static final String DROP_CARD_TABLE =
            "DROP TABLE IF EXISTS " + CARD_TABLE;

    private static class DBHelper extends SQLiteOpenHelper {
        private Context context;
        public DBHelper(Context context, String name,
                        CursorFactory factory, int version) {
            super(context, name, factory, version);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // create tables
            db.execSQL(CREATE_DECK_TABLE);
            db.execSQL(CREATE_CARD_TABLE);

            //Insert Shape Deck
            db.execSQL("INSERT INTO " + DECK_TABLE + " VALUES (1, 'Shapes')");
            //Insert Shape Cards
            addToDeck(db,new Card(0,"Triangle","", "Triangle", Card.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.triangle)), Card.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.triangle))),1);
            addToDeck(db,new Card(0,"Square","", "Square", Card.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.square)), Card.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.square))),1);
            addToDeck(db,new Card(0,"Oval","", "Oval", Card.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.oval)), Card.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.oval))),1);
            addToDeck(db,new Card(0,"Heart","", "Heart", Card.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.heart)), Card.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.heart))),1);
            addToDeck(db,new Card(0,"Hexagon","", "Hexagon", Card.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.hexagon)), Card.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.hexagon))),1);
            addToDeck(db, new Card(0,"Parallelgram","", "Parallelgram", Card.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.parallelogram)), Card.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.parallelogram))), 1);
            addToDeck(db, new Card(0,"Pentagon","", "Pentagon", Card.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.pentagon)), Card.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.pentagon))), 1);
            addToDeck(db, new Card(0,"Rhombus","", "Rhombus", Card.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.rhombus)), Card.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.rhombus))), 1);
            addToDeck(db,new Card(0,"Star","", "Star", Card.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.star)), Card.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.star))),1);

            //Insert President Deck
            db.execSQL("INSERT INTO " + DECK_TABLE + " VALUES (2, 'Presidents')");
            addToDeck(db, new Card(0,"George  Washington","George  Washington", "1st"), 2);
            addToDeck(db, new Card(0,"John Adams","John Adams", "2nd"), 2);
            addToDeck(db, new Card(0,"Thomas Jefferson","Thomas Jefferson", "3rd"), 2);
            addToDeck(db, new Card(0,"James Madison","James Madison", "4th"), 2);
            addToDeck(db, new Card(0,"James Monroe","James Monroe", "5th"), 2);
            addToDeck(db, new Card(0,"John Quincy Adams","John Quincy Adams", "6th"), 2);
            addToDeck(db, new Card(0,"Andrew Jackson","Andrew Jackson", "7th"), 2);
            addToDeck(db, new Card(0,"Martin Van Buren","Martin Van Buren", "8th"), 2);
            addToDeck(db, new Card(0,"William Henry Harrison","William Henry Harrison", "9th"), 2);
            addToDeck(db, new Card(0,"John Tyler","John Tyler", "10th"), 2);
			addToDeck(db, new Card(0,"James K. Polk","James K. Polk", "11th"), 2);
			addToDeck(db, new Card(0,"Zachary Taylor","Zachary Taylor", "12th"), 2);
			addToDeck(db, new Card(0,"Millard Fillmore","Millard Fillmore", "13th"), 2);
			addToDeck(db, new Card(0,"Franklin Pierce","Franklin Pierce", "14th"), 2);
			addToDeck(db, new Card(0,"James Buchana","James Buchana", "15th"), 2);
			addToDeck(db, new Card(0,"Abraham Lincoln","Abraham Lincoln", "16th"), 2);
			addToDeck(db, new Card(0,"Andrew Johnson","Andrew Johnson", "17th"), 2);
			addToDeck(db, new Card(0,"Ulysses S. Grant","Ulysses S. Grant", "18th"), 2);
			addToDeck(db, new Card(0,"Rutherford B. Hayes","Rutherford B. Hayes", "19th"), 2);
			addToDeck(db, new Card(0,"James A. Garfield","James A. Garfield", "20th"), 2);
			addToDeck(db, new Card(0,"Chester A. Arthur","Chester A. Arthur", "21sh"), 2);
			addToDeck(db, new Card(0,"Grover Cleveland","Grover Cleveland", "22nd"), 2);
			addToDeck(db, new Card(0,"Benjamin Harrison","Benjamin Harrison", "23rd"), 2);
			addToDeck(db, new Card(0,"Grover Cleveland","Grover Cleveland", "24th"), 2);
			addToDeck(db, new Card(0,"William McKinley","William McKinley", "25th"), 2);
			addToDeck(db, new Card(0,"Theodore Roosevelt","Theodore Roosevelt", "26th"), 2);
			addToDeck(db, new Card(0,"William Howard Taft","William Howard Taft", "27th"), 2);
			addToDeck(db, new Card(0,"Woodrow Wilson","Woodrow Wilson", "28th"), 2);
			addToDeck(db, new Card(0,"Warren G. Harding","Warren G. Harding", "29th"), 2);
			addToDeck(db, new Card(0,"Calvin Coolidge","Calvin Coolidge", "30th"), 2);
			addToDeck(db, new Card(0,"Herbert Hoover","Herbert Hoover", "31st"), 2);
			addToDeck(db, new Card(0,"Franklin D. Roosevelt","Franklin D. Roosevelt", "32nd"), 2);
			addToDeck(db, new Card(0,"Harry S. Truman","Harry S. Truman", "33rd"), 2);
			addToDeck(db, new Card(0,"Dwight D. Eisenhower","Dwight D. Eisenhower", "34th"), 2);
			addToDeck(db, new Card(0,"John F. Kennedy","John F. Kennedy", "35th"), 2);
			addToDeck(db, new Card(0,"Lyndon B. Johnson","Lyndon B. Johnson", "36th"), 2);
			addToDeck(db, new Card(0,"Richard Nixon","Richard Nixon", "37th"), 2);
			addToDeck(db, new Card(0,"Gerald Ford","Gerald Ford", "38th"), 2);
			addToDeck(db, new Card(0,"Jimmy Carter","Jimmy Carter", "39th"), 2);
			addToDeck(db, new Card(0,"Ronald Reagan","Ronald Reagan", "40th"), 2);
			addToDeck(db, new Card(0,"George H. W. Bush","George H. W. Bush", "41st"), 2);
			addToDeck(db, new Card(0,"Bill Clinton","Bill Clinton", "42nd"), 2);
			addToDeck(db, new Card(0,"George W. Bush","George W. Bush", "43th"), 2);
			addToDeck(db, new Card(0,"Barack Obama","Barack Obama", "44th"), 2);


            //Insert our spanish color deck
            db.execSQL("INSERT INTO " + DECK_TABLE + " VALUES (3, 'Spanish Colors')");
            //Insert the Default Spanish Color Cards
            addToDeck(db, new Card(0,"Red","Red", "Rojo", Card.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.red)), Card.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.red))),3);
            addToDeck(db, new Card(0,"Orange","Orange", "Naranja", Card.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.orange)), Card.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.orange))),3);
            addToDeck(db, new Card(0,"Yellow","Yellow", "Amarillo", Card.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.yellow)), Card.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.yellow))),3);
            addToDeck(db, new Card(0,"Green","Green", "Verde", Card.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.green)), Card.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.green))),3);
            addToDeck(db, new Card(0,"Blue","Blue", "Azul", Card.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.blue)), Card.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.blue))), 3);
            addToDeck(db, new Card(0,"Indigo","Indigo", "Indigo",Card.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.indigo)), Card.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.indigo))), 3);
            addToDeck(db, new Card(0,"Violet","Violet", "Violeta", Card.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.violet)), Card.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.violet))), 3);
        }

        public void addToDeck(SQLiteDatabase db, Card card, int deckIndex) {
            db.execSQL("INSERT INTO " + CARD_TABLE + " (" + CARD_DECK_ID + "," + CARD_NAME_ID + "," + CARD_FRONT_TEXT + "," + CARD_BACK_TEXT + "," +
                    CARD_FRONT_IMAGE + "," + CARD_BACK_IMAGE + ") VALUES (" + deckIndex + ",'" + card.getName() + "','" + card.getFrontText() + "','" + card.getBackText() + "','" +
                    card.getFrontImagePath() + "','" + card.getBackImagePath() + "')");
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

    public long addDeck(String name) {
        ContentValues cv = new ContentValues();
        cv.put(DECK_NAME, name);

        this.openWriteableDB();
        long rowID = db.insert(DECK_TABLE, null, cv);
        this.closeDB();

        return rowID;
    }

    public void updateDeck(Deck deck) {
        ContentValues cv = new ContentValues();
        cv.put(DECK_NAME, deck.getName());
        String where = DECK_ID + "= ?";
        String[] whereArgs = { String.valueOf(deck.getDeckId()) };
        this.openWriteableDB();
        db.update(DECK_TABLE, cv, where, whereArgs);
        this.closeDB();
    }

    public ArrayList<Deck> getDecks() {
        this.openReadableDB();
        Cursor cursor = db.query(DECK_TABLE, null,
                null, null,
                null, null, null);
        ArrayList<Deck> decks = new ArrayList<Deck>();
        while (cursor.moveToNext()) {
            decks.add(getDeck(cursor.getInt(DECK_ID_COL)));
        }
        if (cursor != null)
            cursor.close();
        this.closeDB();
        return decks;
    }

    public Deck getDeck(int id) {
        String where = DECK_ID + "=" + id;

        openReadableDB();
        Cursor cursor = db.query(DECK_TABLE, null,
                where, null, null, null, null);
        Deck deck = null;
        cursor.moveToFirst();
        deck = new Deck(id,cursor.getString(DECK_NAME_COL));
        ArrayList<Card> deckCards = getCards(cursor.getInt(DECK_ID_COL));
        for (int i = 0; i < deckCards.size(); i++) {
            deck.addCard(deckCards.get(i));
        }
        cursor.close();
        this.closeDB();

        return deck;
    }

    public void deleteDeck(int id) {
        String where = DECK_ID + "= ?";
        String[] whereArgs = { String.valueOf(id) };

        this.openWriteableDB();
        int rowCount = db.delete(DECK_TABLE, where, whereArgs);
        this.closeDB();
    }

    public void deleteCard(int id) {
        String where = CARD_ID + "= ?";
        String[] whereArgs = { String.valueOf(id) };

        this.openWriteableDB();
        int rowCount = db.delete(CARD_TABLE, where, whereArgs);
        this.closeDB();
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

    public Card getCard(int cardId) {
        String where =
                CARD_ID + "=" + cardId;

        this.openReadableDB();
        Cursor cursor = db.query(CARD_TABLE, null,
                where, null,
                null, null, null);
        Card card = null;
        cursor.moveToFirst();
        card = getCardFromCursor(cursor);
        if (cursor != null)
            cursor.close();
        this.closeDB();
        return card;
    }

    public void updateCard(Card card) {
        ContentValues cv = new ContentValues();
        cv.put(CARD_ID, card.getId());
        cv.put(CARD_NAME_ID, card.getName());
        cv.put(CARD_FRONT_TEXT, card.getFrontText());
        cv.put(CARD_BACK_TEXT, card.getBackText());
        cv.put(CARD_FRONT_IMAGE, card.getFrontImagePath());
        cv.put(CARD_BACK_IMAGE, card.getBackImagePath());
        String where = CARD_ID + "= ?";
        String[] whereArgs = { String.valueOf(card.getId()) };
        this.openWriteableDB();
        db.update(CARD_TABLE, cv,where,whereArgs);
        this.closeDB();
    }

    public long insertCard(Card card, int deckId) {
        ContentValues cv = new ContentValues();
        cv.put(CARD_NAME_ID, card.getName());
        cv.put(CARD_DECK_ID, deckId);
        cv.put(CARD_FRONT_TEXT, card.getFrontText());
        cv.put(CARD_BACK_TEXT, card.getBackText());
        cv.put(CARD_FRONT_IMAGE, card.getFrontImagePath());
        cv.put(CARD_BACK_IMAGE, card.getBackImagePath());

        this.openWriteableDB();
        long rowID = db.insert(CARD_TABLE, null, cv);
        this.closeDB();

        return rowID;
    }

    private static Card getCardFromCursor(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0){
            return null;
        }
        else {
            try {
                Card card = new Card(
                        cursor.getInt(CARD_ID_COL),
                        cursor.getString(CARD_NAME_ID_COL),
                        cursor.getString(CARD_FRONT_TEXT_COL),
                        cursor.getString(CARD_BACK_TEXT_COL),
                        cursor.getString(CARD_FRONT_IMAGE_COL),
                        cursor.getString(CARD_BACK_IMAGE_COL));
                return card;
            }
            catch(Exception e) {
                return null;
            }
        }
    }

}
