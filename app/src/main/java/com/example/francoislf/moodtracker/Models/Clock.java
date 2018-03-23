package com.example.francoislf.moodtracker.Models;

import org.joda.time.DateTime;

import java.util.Calendar;

/**
 * Created by slaye on 23/03/2018.
 */

public class Clock {


    private DateTime mLastPoint;
    private DateTime mToday;

    public Clock(int yearLastSave, int dayLastSave){
        this.mToday = new DateTime();
        //mToday = mToday.plusDays(1);
        if (yearLastSave == 0) mLastPoint = new DateTime();
        else {
            mLastPoint = mLastPoint.year().setCopy(yearLastSave);
            mLastPoint = mLastPoint.dayOfYear().setCopy(dayLastSave);
        }
    }

/*
    public int test(){

        int dif = 0;

        DateTime dateTime = new DateTime();

        dif= dateTime.getDayOfYear();

        DateTime date1 = new DateTime();
        DateTime date2 = new DateTime();

        date1 = date1.dayOfMonth().setCopy(24);
        date1 = date1.monthOfYear().setCopy(3);


        dif = date1.getDayOfYear() - date2.getDayOfYear();

       // dif = date1.getYear();

        return dif;

    }
*/

    public int getSaveYear(){
        return mLastPoint.getYear();
    }

    public int getSaveDay(){
        return mLastPoint.getDayOfYear();
    }


    public Boolean dateDontChange(){
        boolean result = true;

        if (mToday.getYear() != mLastPoint.getYear() || mToday.getDayOfYear() != mLastPoint.getDayOfYear())
            result = false;

        return result;
    }

    @Override
    public String toString() {
        return "Clock{" +
                "mLastPoint=" + mLastPoint +
                ", mToday=" + mToday +
                '}';
    }
}
