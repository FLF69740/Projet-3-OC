package com.example.francoislf.moodtracker.Models;

import android.util.Log;
import org.joda.time.DateTime;

/**
 * Created by slaye on 23/03/2018.
 */

public class Clock {


    private DateTime mLastPoint;
    private DateTime mToday;

    public Clock(int yearLastSave, int dayLastSave){
        this.mToday = new DateTime();
        this.mLastPoint = new DateTime();

        if (yearLastSave != 0) {
            mLastPoint = mLastPoint.year().setCopy(yearLastSave);
            mLastPoint = mLastPoint.dayOfYear().setCopy(dayLastSave);
        }

        Log.i("TRANSFERT", " INIT : LastPoint : " + mLastPoint.getDayOfYear() + " (" + mLastPoint.getYear() + ") - TODAY : " +
        mToday.getDayOfYear() + " (" + mToday.getYear() + ")");


    }

    public int getPeriod(){
        int result;

        if (mLastPoint.getYear() != mToday.getYear()){
            DateTime lastDayOfThisYear = new DateTime();
            lastDayOfThisYear = lastDayOfThisYear.year().setCopy(mLastPoint.getYear());
            lastDayOfThisYear = lastDayOfThisYear.dayOfMonth().setCopy(31);
            lastDayOfThisYear = lastDayOfThisYear.monthOfYear().setCopy(12);

            result = lastDayOfThisYear.getDayOfYear() - mLastPoint.getDayOfYear();
            result += mToday.getDayOfYear();
        }

        else {
            result = mToday.getDayOfYear() - mLastPoint.getDayOfYear();
            if (result<0) result =0;
        }

        return result;
    }

    public int getThisYear() {return mToday.getYear();}

    public int getThisDay(){return mToday.getDayOfYear();}

    public Boolean dateChange(){
        boolean result = false;

        if (mToday.getYear() != mLastPoint.getYear() || mToday.getDayOfYear() != mLastPoint.getDayOfYear())
            result = true;

        Log.i("TRANSFERT", " BOOL DATE CHANGE : " + result);

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
