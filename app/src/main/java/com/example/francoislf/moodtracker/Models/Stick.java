package com.example.francoislf.moodtracker.Models;

import android.graphics.Color;
import android.widget.LinearLayout;

/**
 * Created by slaye on 21/03/2018.
 */

public class Stick {

    private LinearLayout.LayoutParams mId_blank;
    private LinearLayout.LayoutParams mId_stick;
    private int mMood;
    private boolean mTextExist;
    private String mTextToast, mTitle;
    private int mStickParam, mBlankParam;
    private int mColor;

    public Stick(String title, int mood, boolean textExist, String text){

        this.mMood = mood;
        this.mTextExist = textExist;
        this.mTextToast = text;
        this.mTitle = title;

        linearLayoutConfiguration();

        mId_stick = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mId_stick.weight = mStickParam;

        mId_blank = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mId_blank.weight = mBlankParam;

    }

    // Define the weight parameter of layouts and color
    private void linearLayoutConfiguration(){
        switch (mMood){
            case 1:mStickParam = 8;mBlankParam=2; mColor = Color.parseColor("#ffde3c50");break;
            case 2:mStickParam = 6;mBlankParam=4; mColor = Color.parseColor("#ff9b9b9b");break;
            case 3:mStickParam = 4;mBlankParam=6; mColor = Color.parseColor("#a5468ad9");break;
            case 4:mStickParam = 2;mBlankParam=8; mColor = Color.parseColor("#ffb8e986");break;
            case 5:mStickParam = 0;mBlankParam=10; mColor = Color.parseColor("#fff9ec4f");break;
            default:mStickParam = 10;mBlankParam=0; mColor = Color.parseColor("#ffde3c50");break;
        }
    }

    // getter and setter :

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public LinearLayout.LayoutParams getId_blank() {
        return mId_blank;
    }

    public void setId_blank(LinearLayout.LayoutParams id_blank) {
        mId_blank = id_blank;
    }

    public LinearLayout.LayoutParams getId_stick() {
        return mId_stick;
    }

    public void setId_stick(LinearLayout.LayoutParams id_stick) {
        mId_stick = id_stick;
    }

    public int getMood() {
        return mMood;
    }

    public void setMood(int mood) {
        mMood = mood;
    }

    public boolean isTextExist() {
        return mTextExist;
    }

    public void setTextExist(boolean textExist) {
        mTextExist = textExist;
    }

    public String getTextToast() {
        return mTextToast;
    }

    public void setTextToast(String text) {
        mTextToast = text;
    }

    public int getColor(){
        return mColor;
    }

    @Override
    public String toString() {
        return "Stick{" +
                "mId_blank=" + mId_blank +
                ", mId_stick=" + mId_stick +
                ", mMood=" + mMood +
                ", mTextExist=" + mTextExist +
                ", mText='" + mTextToast + '\'' +
                '}';
    }
}
