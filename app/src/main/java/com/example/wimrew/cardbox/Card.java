package com.example.wimrew.cardbox;

import java.io.Serializable;

/**
 * Created by JC Snider on 2/14/2016.
 */
public class Card implements Serializable{

    private String _frontText = "";
    private String _backText = "";
    private int _frontImagePath = 0;
    private int _backImagePath = 0;

    public Card(){

    }

    public Card(String frontText, String backText, int frontImagePath, int backImagePath){
        _frontText = frontText;
        _backText = backText;
        _frontImagePath = frontImagePath;
        _backImagePath = backImagePath;
    }
    public Card(String frontText, String backText){
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

    public void setFrontImagePath(int value) {
        _frontImagePath = value;
    }

    public void setBackImagePath(int value) {
        _backImagePath = value;
    }

    //Getters
    public String getFrontText() {
        return _frontText;
    }

    public String getBackText() {
        return _backText;
    }

    public int getFrontImagePath() {
        return _frontImagePath;
    }

    public int getBackImagePath() {
        return _backImagePath;
    }
}
