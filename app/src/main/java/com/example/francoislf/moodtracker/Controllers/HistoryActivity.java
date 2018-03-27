package com.example.francoislf.moodtracker.Controllers;


import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.francoislf.moodtracker.Models.Stick;
import com.example.francoislf.moodtracker.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import static com.example.francoislf.moodtracker.Controllers.MainActivity.LAST_POSITION;
import static com.example.francoislf.moodtracker.Controllers.MainActivity.PERIOD_TO_UPLOAD;
import static com.example.francoislf.moodtracker.Controllers.MainActivity.COMMENTARY_OF_THE_DAY;


public class HistoryActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;

    private ArrayList<Stick> mListStick = new ArrayList<>();

    SharedPreferences mDefaultPreferencesStick;
    public static final String SHARED_DEFAULT_STICK_SCALE = "SHARED_DEFAULT_STICK_SCALE";

    int mPosition;
    int mDay;
    String mCommentary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        mPosition = (int) getIntent().getSerializableExtra(LAST_POSITION);
        mDay = (int) getIntent().getSerializableExtra(PERIOD_TO_UPLOAD);
        mCommentary = (String) getIntent().getSerializableExtra(COMMENTARY_OF_THE_DAY);

        Log.i("TRANSFERT : ", "MOOD : " + mPosition + " - Nb Day : " + mDay + " - Commentaire : " + mCommentary);



        load();

        mRecyclerView = (RecyclerView) findViewById(R.id.MonRecyclerView);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this,mListStick.size(),0,true));
        mRecyclerView.setOverScrollMode(2);

        mRecyclerView.setAdapter(new MyAdapter(mListStick));

        save();


    }

    // Default configuration with Empty SharedPreferences
    private void ajouterStick(){
        mListStick.add(new Stick("Il y a une semaine",0,false, "EMPTY"));
        mListStick.add(new Stick("Il y a six jours",0,false, "EMPTY"));
        mListStick.add(new Stick("Il y a cinq jours",0,false, "EMPTY"));
        mListStick.add(new Stick("Il y a quatrte jours",0,false, "EMPTY"));
        mListStick.add(new Stick("Il y a trois jours",0,false, "EMPTY"));
        mListStick.add(new Stick("Avant-hier",0,false, "EMPTY"));
        mListStick.add(new Stick("Hier",0,false, "EMPTY"));
    }

    // Save current class object to SharedPreferences with Gson library (JSon format)
    private void save(){

        mDefaultPreferencesStick = getSharedPreferences(SHARED_DEFAULT_STICK_SCALE, MODE_PRIVATE);
        SharedPreferences.Editor editor = mDefaultPreferencesStick.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mListStick);
        editor.putString(SHARED_DEFAULT_STICK_SCALE, json);
    //    editor.putString(SHARED_DEFAULT_STICK_SCALE, null);
        editor.apply();
    }

    // Load JSon in order to create class object with Gson library
    private void load(){

        mDefaultPreferencesStick = getSharedPreferences(SHARED_DEFAULT_STICK_SCALE, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mDefaultPreferencesStick.getString(SHARED_DEFAULT_STICK_SCALE, null);
        Type type = new TypeToken<ArrayList<Stick>>() {}.getType();
        mListStick = gson.fromJson(json, type);

        if (mListStick == null) {
            mListStick = new ArrayList<>();
            ajouterStick();
        }
    }


}
