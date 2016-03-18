package com.example.wimrew.cardbox;

/**
 * Created by JC Snider on 2/14/2016.
 */
public class Card {

    private String _frontText = "";
    private String _backText = "";
    private String _frontImagePath = "";
    private String _backImagePath = "";

    public Card(){

    }

    public Card(String frontText, String backText, String frontImagePath, String backImagePath){
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

    public void setFrontImagePath(String value) {
        _frontImagePath = value;
    }

    public void setBackImagePath(String value) {
        _backImagePath = value;
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
}
