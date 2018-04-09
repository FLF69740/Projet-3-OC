package com.example.francoislf.moodtracker.Controllers;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.example.francoislf.moodtracker.Models.Clock;
import com.example.francoislf.moodtracker.Models.Stick;
import com.example.francoislf.moodtracker.Models.StickDiagram;
import com.example.francoislf.moodtracker.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainFragment.OnButtonClickedListener{

    private SharedPreferences mPreferences, mDialogPreferences, mLastPosPreferences, mJSonPreferences;

    private int mPosition;
    private boolean mDialogOpen;

    private Clock mClock;
    private StickDiagram mStickDiagram;

    private static final String LAST_POSITION = "LAST_POSITION";
    private static final String COMMENTARY_OF_THE_DAY = "COMMENTARY_OF_THE_DAY";
    private static final String BUNDLE_STATE_OUTSTATE = "Outstate";
    private static final String BUNDLE_STATE_DIALOG = "Dialog";
    private static final String LAST_SAVE_OF_YEAR = "yearLastSave";
    private static final String LAST_SAVE_OF_DAY = "dayLastSave";
    private static final String SHARED_DEFAULT_STICK_SCALE = "SHARED_DEFAULT_STICK_SCALE";

    private MediaPlayer mPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPreferences = getPreferences(MODE_PRIVATE);
        mDialogPreferences = getPreferences(MODE_PRIVATE);
        mLastPosPreferences = getPreferences(MODE_PRIVATE);
        mJSonPreferences = getPreferences(MODE_PRIVATE);
        mDialogOpen = false;

        if (savedInstanceState != null) {
            mPosition = savedInstanceState.getInt(BUNDLE_STATE_OUTSTATE);
            mDialogOpen = savedInstanceState.getBoolean(BUNDLE_STATE_DIALOG);
            if (mDialogOpen) createDialog();
        }

        clockInit(getPreferences(MODE_PRIVATE).getInt(LAST_POSITION,3));

        this.configureViewPager();
    }

    // Configure ViewPager
    private void configureViewPager(){
        ViewPager viewPager = (ViewPager) findViewById(R.id.activity_main_viewpager);
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), getResources().getIntArray(R.array.ColorPageFragment)));

        // Load the page registered in mPreferences
        viewPager.setCurrentItem(getPreferences(MODE_PRIVATE).getInt(LAST_POSITION,3));

        // Attach the page change listener inside the activity
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageSelected(int position) {
                mLastPosPreferences.edit().putInt(LAST_POSITION, position).apply();
                playSound(getPreferences(MODE_PRIVATE).getInt(LAST_POSITION,4));

                // Delete a commentary before if mood change
                if (getPreferences(MODE_PRIVATE).getString(COMMENTARY_OF_THE_DAY, null) != null && mPosition != position)
                    mDialogPreferences.edit().putString(COMMENTARY_OF_THE_DAY, null).apply();

            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(BUNDLE_STATE_OUTSTATE, mPosition);
        outState.putBoolean(BUNDLE_STATE_DIALOG, mDialogOpen);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onButtonClicked(View view) {
        mPosition = getPreferences(MODE_PRIVATE).getInt(LAST_POSITION,3);

        switch (view.getTag().toString()){
            case "10" :
                mDialogOpen = true;
                createDialog();
                break;
            case "20" :
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                mPreferences.edit().putInt(LAST_SAVE_OF_YEAR, mClock.getThisYear()).apply();
                mPreferences.edit().putInt(LAST_SAVE_OF_DAY, mClock.getThisDay()).apply();
                startActivity(intent);
                break;
            default: break;
        }
    }

    // AlertDialog construction
    public void createDialog(){

        String messageSaved = getPreferences(MODE_PRIVATE).getString(COMMENTARY_OF_THE_DAY, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Commentaire");

        final EditText input = new EditText(this);
        input.setText(messageSaved);
        builder.setView(input);

        builder.setNegativeButton("ANNULER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDialogOpen = false;
                    }
                })
                .setPositiveButton("VALIDER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDialogOpen = false;
                        mDialogPreferences.edit().putString(COMMENTARY_OF_THE_DAY, input.getText().toString()).apply();
                    }
                })
                .create()
                .show();
    }

    // Initialisation of Sticks state with the current clock
    private void clockInit(int mood){
        mClock = new Clock(getPreferences(MODE_PRIVATE).getInt(LAST_SAVE_OF_YEAR, 0), getPreferences(MODE_PRIVATE).getInt(LAST_SAVE_OF_DAY, 0));
        mStickDiagram = new StickDiagram(mClock.getPeriod(), mood, getPreferences(MODE_PRIVATE).getString(COMMENTARY_OF_THE_DAY, null));

        load();

        if (mClock.dateChange()) {
            mStickDiagram.upLoad();

            mDialogPreferences.edit().putString(COMMENTARY_OF_THE_DAY, null).apply();
            mLastPosPreferences.edit().putInt(LAST_POSITION, 3).apply();
        }
        save();
    }

    // Load JSon in order to create class object with Gson library
    private void load(){

        mJSonPreferences = getSharedPreferences(SHARED_DEFAULT_STICK_SCALE, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mJSonPreferences.getString(SHARED_DEFAULT_STICK_SCALE, null);
        Type type = new TypeToken<ArrayList<Stick>>() {}.getType();
        ArrayList<Stick> listStick = gson.fromJson(json, type);
        mStickDiagram.setListStick(listStick);

        if (mStickDiagram.getListStick() == null) {
            mStickDiagram.resetFactory();
        }

        Log.i("TRANSFERT", "LOAD MODE to " + getClass().getName().toString());
    }

    // Save current class object to SharedPreferences with Gson library (JSon format)
    private void save(){

        mJSonPreferences = getSharedPreferences(SHARED_DEFAULT_STICK_SCALE, MODE_PRIVATE);
        SharedPreferences.Editor editor = mJSonPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mStickDiagram.getListStick());
        editor.putString(SHARED_DEFAULT_STICK_SCALE, json);
      //      editor.putString(SHARED_DEFAULT_STICK_SCALE, null);
        editor.apply();
        if (getPreferences(MODE_PRIVATE).getString(SHARED_DEFAULT_STICK_SCALE,null) == null) load();

        Log.i("TRANSFERT","SAVE MODE to " + getClass().getName().toString());
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPlayer != null){
            mPlayer.stop();
            mPlayer.release();
        }
    }

    // Method for playing sounds
    private void playSound(int mood){
        if (mPlayer != null){
            mPlayer.stop();
            mPlayer.release();
        }
        switch (mood){
            case 0: mPlayer=MediaPlayer.create(this,R.raw.mood1); mPlayer.start();break;
            case 1: mPlayer=MediaPlayer.create(this,R.raw.mood2); mPlayer.start();break;
            case 2: mPlayer=MediaPlayer.create(this,R.raw.mood3); mPlayer.start();break;
            case 3: mPlayer=MediaPlayer.create(this,R.raw.mood4); mPlayer.start();break;
            case 4: mPlayer=MediaPlayer.create(this,R.raw.mood5); mPlayer.start();break;
            default:break;
        }
    }
}
