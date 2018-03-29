package com.example.francoislf.moodtracker.Models;

import android.util.Log;
import java.util.ArrayList;

public class StickDiagram {

    private ArrayList<Stick> mListStick = new ArrayList<>();
    int mPosition;
    int mDay;
    String mCommentary;

    String[] mTableTitle;

    // constructor by default
    public StickDiagram(){}

    // constructor with attributes
    public StickDiagram(int day, int position, String commentary){
        this.mDay = day;
        this.mPosition = position;
        this.mCommentary = commentary;

        Log.i("TRANSFERT", "StickDiagram declaration : MOOD = " + mPosition);

        mTableTitle = new String[7];
        mTableTitle[0] = "Il y a une semaine";
        mTableTitle[1] = "Il y a six jours";
        mTableTitle[2] = "Il y a cinq jours";
        mTableTitle[3] = "Il y a quatre jours";
        mTableTitle[4] = "Il y a trois jours";
        mTableTitle[5] = "Avant-hier";
        mTableTitle[6] = "Hier";
    }

    // Default configuration with Empty SharedPreferences
    public void resetFactory(){
        Log.i("TRANSFERT", "LOAD RESET FACTORY");

        mListStick = new ArrayList<>();
        mListStick.add(new Stick(mTableTitle[0],0,false, "EMPTY"));
        mListStick.add(new Stick(mTableTitle[1],0,false, "EMPTY"));
        mListStick.add(new Stick(mTableTitle[2],0,false, "EMPTY"));
        mListStick.add(new Stick(mTableTitle[3],0,false, "EMPTY"));
        mListStick.add(new Stick(mTableTitle[4],0,false, "EMPTY"));
        mListStick.add(new Stick(mTableTitle[5],0,false, "EMPTY"));
        mListStick.add(new Stick(mTableTitle[6],0,false, "EMPTY"));

        Log.i("TRANSFERT", "RESET FACTORY OK");
    }

    // Configure if Event and date changed
    public void upLoad(){
        if (mDay > 0) {
            Log.i("TRANSFERT", "CALCULATING GOING ON");
            for (int i = 0 ; i < mDay ; i++){
                if (i < (mTableTitle.length+1)) {
                    mListStick.remove(0);
                    mListStick.add(new Stick("Title", mPosition + 1, false, "EMPTY"));
                    Log.i("TRANSFERT", "One line with : MOOD = " + mPosition);
                }
                if (i == 0 && mCommentary != null){
                    Log.i("TRANSFERT","COMMENTARY");
                    mListStick.get(mListStick.size()-1).setTextExist(true);
                    mListStick.get(mListStick.size()-1).setTextToast(mCommentary);
                    Log.i("TRANSFERT", "AND Text = " + mCommentary);
                }
            }
            for (int j = 0 ; j < mListStick.size() ; j++) mListStick.get(j).setTitle(mTableTitle[j]);
        }
    }

    // set the ArrayList of Sticks
    public void setListStick(ArrayList<Stick> listStick) {
        mListStick = listStick;
    }

    // send the ArrayList of Sticks
    public ArrayList<Stick> getListStick() {
        return mListStick;
    }
}
