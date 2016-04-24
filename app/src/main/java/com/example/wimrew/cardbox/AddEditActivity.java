package com.example.wimrew.cardbox;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddEditActivity extends AppCompatActivity {

    private int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    DeckDatabase db;
    EditText cardNameEditText;
    EditText frontEditText;
    EditText backEditText;
    String frontImage = "";
    String backImage = "";
    ImageView frontImageView;
    ImageView backImageView;
    Button setFrontImageButton;
    Button setBackImageButton;
    Boolean editing;
    Boolean selectingFront = false;
    int cardId = 0;
    int deckId = 0;
    SharedPreferences pref;
    boolean paused = false;
    Card card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        cardNameEditText = (EditText)findViewById(R.id.cardNameEditText);
        frontEditText = (EditText)findViewById(R.id.frontEditText);
        backEditText = (EditText)findViewById(R.id.backEditText);
        frontImageView = (ImageView)findViewById(R.id.frontImageView);
        backImageView = (ImageView)findViewById(R.id.backImageView);
        setFrontImageButton = (Button)findViewById(R.id.btnFrontImage);
        setBackImageButton = (Button)findViewById(R.id.btnBackImage);
        setFrontImageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectingFront = true;
                showImageOptions();
            }
        });
        setBackImageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectingFront = false;
                showImageOptions();
            }
        });
        db = new DeckDatabase(this);

        if (savedInstanceState != null) {
            onResume();
            return;
        }

        Intent intent = getIntent();
        cardId = intent.getIntExtra("cardid", 0);
        editing = intent.getBooleanExtra("editing", false);
        if (editing) {
            card = db.getCard(cardId);
        }
        else {
            card = new Card(0,"New Card","","","","");
            deckId = intent.getIntExtra("deckid",0);

        }

        cardNameEditText.setText(card.getName());
        frontEditText.setText(card.getFrontText());
        backEditText.setText(card.getBackText());
        frontImage = card.getFrontImagePath();
        backImage = card.getBackImagePath();
        if (frontImage != null && !frontImage.equals("")){
            frontImageView.setImageBitmap(Card.stringToBitmap(frontImage));
        }
        else{
            frontImageView.setImageBitmap(null);
        }
        if (backImage != null && !backImage.equals("")){
            backImageView.setImageBitmap(Card.stringToBitmap(backImage));
        }
        else{
            backImageView.setImageBitmap(null);
        }
        paused = false;
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("Paused", paused);
        editor.apply();
    }

    private void saveToDB() {
        // get data from widgets
        String cardName = cardNameEditText.getText().toString();
        String frontText = frontEditText.getText().toString();
        String backText = backEditText.getText().toString();

        // if no player name, exit method
        if (cardName == null || cardName.trim().equals("")) {
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(this, "Card must have a name!", duration);
            toast.show();
            return;
        }

        // put data in player
        card.setName(cardName.trim());
        card.setFrontText(frontText.trim());
        card.setBackText(backText.trim());
        card.setFrontImagePath(frontImage);
        card.setBackImagePath(backImage);

        // update or insert player
        if (editing) {
            db.updateCard(card);
        }
        else {
            db.insertCard(card, deckId);
        }
        this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_edit_card, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final SharedPreferences settings;
        switch (item.getItemId()){
            case R.id.menuSave:
                saveToDB();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showImageOptions() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Image Action");
        builder.setItems(new CharSequence[] {"Take Photo", "Select from Gallery", "Clear Photo", "Cancel"},
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Boolean updated = false;
                        switch (which) {
                            case 0:
                                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(cameraIntent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
                                break;
                            case 1:
                                openImageIntent();
                                break;
                            case 2:
                                if (selectingFront) {
                                    frontImageView.setImageBitmap(null);
                                    frontImage = "";
                                    SharedPreferences.Editor editor = pref.edit();
                                    editor.putString("frontImage", frontImage);
                                    editor.apply();
                                }
                                else {
                                    backImageView.setImageBitmap(null);
                                    backImage = "";
                                    SharedPreferences.Editor editor = pref.edit();
                                    editor.putString("backImage", backImage);
                                    editor.apply();
                                }
                                break;
                            case 3:
                            default:
                                break;
                        }

                    }
                });

        builder.show();
    }

    private void openImageIntent(){
        //FileSystem
        final Intent galleryIntent = new Intent();
        galleryIntent.setType("image/");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

        // Chooser of filesystem options.
        final Intent chooserIntent = Intent.createChooser(galleryIntent, "Select Source");
        startActivityForResult(chooserIntent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
                final boolean isCamera;
                if (data == null) {
                    isCamera = true;
                } else {
                    final String action = data.getAction();
                    if (action == null) {
                        isCamera = false;
                    } else {
                        isCamera = action.equals("inline-data");
                    }
                }

                Uri selectedImageUri;
                if (isCamera) {
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    if (selectingFront) {
                        frontImageView.setImageBitmap(photo);
                        frontImage = Card.bitmapToString(photo);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("frontImage", frontImage);
                        editor.apply();
                    }
                    else {
                        backImageView.setImageBitmap(photo);
                        backImage = Card.bitmapToString(photo);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("backImage", backImage);
                        editor.apply();
                    }
                } else {
                    selectedImageUri = data == null ? null : data.getData();
                    Log.d("ImageURI", selectedImageUri.getLastPathSegment());
                    // /Bitmap factory
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    // downsizing image as it throws OutOfMemory Exception for larger
                    // images
                    options.inSampleSize = 8;
                    try {//Using Input Stream to get uri did the trick
                        InputStream input = getContentResolver().openInputStream(selectedImageUri);
                        final Bitmap bitmap = BitmapFactory.decodeStream(input);
                        if (selectingFront) {
                            frontImageView.setImageBitmap(bitmap);
                            frontImage = Card.bitmapToString(bitmap);
                            selectingFront = false;
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("frontImage",frontImage);
                            editor.apply();
                        }
                        else {
                            backImageView.setImageBitmap(bitmap);
                            backImage = Card.bitmapToString(bitmap);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("backImage", backImage);
                            editor.apply();
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (resultCode == RESULT_CANCELED){
            // user cancelled Image capture
            Toast.makeText(getApplicationContext(),
                    "User cancelled image capture", Toast.LENGTH_SHORT)
                    .show();
        } else {
            // failed to capture image
            Toast.makeText(getApplicationContext(),
                    "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    protected void onPause(){
        paused=true;
        SharedPreferences.Editor editor = pref.edit();

        editor.putInt("cardId",card.getId());
        editor.putInt("deckId",deckId);
        editor.putBoolean("editing", editing);
        editor.putString("cardName", cardNameEditText.getText().toString());
        editor.putString("frontText", frontEditText.getText().toString());
        editor.putString("backText", backEditText.getText().toString());
        editor.putString("frontImage", frontImage);
        editor.putString("backImage", backImage);

        editor.putBoolean("Paused", paused);
        editor.apply();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        paused = pref.getBoolean("Paused", false);
        if (paused) {

            card = db.getCard(pref.getInt("cardId", 0));
            deckId = pref.getInt("deckId", 0);
            editing = pref.getBoolean("editing",false);
            cardNameEditText.setText(pref.getString("cardName", "Card Name"));
            frontEditText.setText(pref.getString("frontText", ""));
            backEditText.setText(pref.getString("backText", ""));
            frontImage = (pref.getString("frontImage", ""));
            backImage = (pref.getString("backImage", ""));

            if (!frontImage.equals("")) {
                frontImageView.setImageBitmap(Card.stringToBitmap(frontImage));
            } else {
                frontImageView.setImageBitmap(null);
            }
            if (!backImage.equals("")) {
                backImageView.setImageBitmap(Card.stringToBitmap(backImage));
            } else {
                backImageView.setImageBitmap(null);
            }
            paused = false;
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("Paused", paused);
            editor.apply();
        }
    }

}
