package com.example.wimrew.cardbox;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

/**
 * Created by JC Snider on 2/14/2016.
 */
public class Card implements Serializable{

    private int _id = 0;
    private String _name = "";
    private String _frontText = "";
    private String _backText = "";
    private String _frontImagePath = "";
    private String _backImagePath = "";
    private CardLayout layoutElement;
    private Boolean listChecked = false;

    public Card(){

    }

    public Card(int id, String name, String frontText, String backText, String frontImagePath, String backImagePath){
        _id = id;
        _name = name;
        _frontText = frontText;
        _backText = backText;
        _frontImagePath = frontImagePath;
        _backImagePath = backImagePath;
    }
    public Card(int id, String name, String frontText, String backText){
        _id = id;
        _name = name;
        _frontText = frontText;
        _backText = backText;
    }
    //Setters
    public void setFrontText(String value) {
        _frontText = value;
    }

    public void setBackText(String value) {
         _backText = value;
    }

    public void setFrontImagePath(String value) {
        _frontImagePath = value;
    }

    public void setBackImagePath(String value) {
        _backImagePath = value;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    //Getters
    public String getFrontText() {
        return _frontText;
    }

    public String getBackText() {
        return _backText;
    }

    public String getFrontImagePath() {
        return _frontImagePath;
    }

    public String getBackImagePath() {
        return _backImagePath;
    }

    public final static String bitmapToString(Bitmap in){
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        in.compress(Bitmap.CompressFormat.PNG, 100, bytes);
        return Base64.encodeToString(bytes.toByteArray(), Base64.DEFAULT);
    }
    public final static Bitmap stringToBitmap(String in){
        byte[] bytes = Base64.decode(in, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    public String getName() {
        return _name;
    }

    public void setLayoutElement(CardLayout layoutElement) {
        this.layoutElement = layoutElement;
    }

    public CardLayout getLayoutElement() {
        return layoutElement;
    }

    public Boolean getListChecked() {
        return listChecked;
    }

    public void setListChecked(Boolean listChecked) {
        this.listChecked = listChecked;
    }

    public int getId() {
        return _id;
    }
}
