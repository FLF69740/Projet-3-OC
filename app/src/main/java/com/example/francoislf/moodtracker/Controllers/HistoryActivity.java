package com.example.francoislf.moodtracker.Controllers;


import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.example.francoislf.moodtracker.Models.Stick;
import com.example.francoislf.moodtracker.Models.StickDiagram;
import com.example.francoislf.moodtracker.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class HistoryActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;

    StickDiagram mStickDiagram;

    SharedPreferences mDefaultPreferencesStick;
    public static final String SHARED_DEFAULT_STICK_SCALE = "SHARED_DEFAULT_STICK_SCALE";

    int mPosition;
    int mDay;
    String mCommentary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        mStickDiagram = new StickDiagram();

        Log.i("TRANSFERT", " ------- MOOD : " + mPosition + " - Nb Day : " + mDay + " - Commentary : " + mCommentary);

        load();

        Log.i("TRANSFERT", "RECYCLERVIEW RUNNING");
        mRecyclerView = (RecyclerView) findViewById(R.id.MonRecyclerView);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this,mStickDiagram.getListStick().size(),0,true));
        mRecyclerView.setOverScrollMode(2);

        mRecyclerView.setAdapter(new MyAdapter(mStickDiagram.getListStick()));

    }

    // Load JSon in order to create class object with Gson library
    private void load(){

        Log.i("TRANSFERT", "LOAD MODE");
        mDefaultPreferencesStick = getSharedPreferences(SHARED_DEFAULT_STICK_SCALE, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mDefaultPreferencesStick.getString(SHARED_DEFAULT_STICK_SCALE, null);
        Type type = new TypeToken<ArrayList<Stick>>() {}.getType();
        ArrayList<Stick> listStick = gson.fromJson(json, type);
        mStickDiagram.setListStick(listStick);

        if (mStickDiagram.getListStick() == null) {
            mStickDiagram.resetFactory();
        }

        Log.i("TRANSFERT", "LOAD MODE to " + getClass().getName().toString());
    }
}
