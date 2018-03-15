package com.example.francoislf.moodtracker.Controllers;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.francoislf.moodtracker.R;

public class MainActivity extends AppCompatActivity implements MainFragment.OnButtonClickedListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.configureViewPager();
    }

    private void configureViewPager(){
        ViewPager viewPager = (ViewPager) findViewById(R.id.activity_main_viewpager);

        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), getResources().getIntArray(R.array.ColorPageFragment)));
    }

    @Override
    public void onButtonClicked(View view) {
        Log.i("Button clicked ", " BUTTON " + view.getTag().toString() );
    }
}
