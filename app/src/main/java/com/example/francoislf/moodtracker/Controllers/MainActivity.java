package com.example.francoislf.moodtracker.Controllers;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.francoislf.moodtracker.R;

public class MainActivity extends AppCompatActivity implements MainFragment.OnButtonClickedListener{



    SharedPreferences mPreferences;

    int mPosition;
    boolean mDialogOpen;

    public static final String LAST_POSITION = "LAST_POSITION";
    public static final String COMMENTARY_OF_THE_DAY = "COMMENTARY_OF_THE_DAY";

    public static final String BUNDLE_STATE_OUTSTATE = "Outstate";
    public static final String BUNDLE_STATE_DIALOG = "Dialog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPreferences = getPreferences(MODE_PRIVATE);

        mDialogOpen = false;

        if (savedInstanceState != null) {
            mPosition = savedInstanceState.getInt(BUNDLE_STATE_OUTSTATE);
            mDialogOpen = savedInstanceState.getBoolean(BUNDLE_STATE_DIALOG);
            if (mDialogOpen) createDialog();
        }

        this.configureViewPager();

    }

    private void configureViewPager(){
        ViewPager viewPager = (ViewPager) findViewById(R.id.activity_main_viewpager);
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), getResources().getIntArray(R.array.ColorPageFragment)));

        // Load the page registered in mPreferences (or page happy by default)
        viewPager.setCurrentItem(getPreferences(MODE_PRIVATE).getInt(LAST_POSITION,3));

        // Attach the page change listener inside the activity
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageSelected(int position) {
                mPreferences.edit().putInt(LAST_POSITION, position).apply();

                // Delete a commentary before if mood change
                if (getPreferences(MODE_PRIVATE).getString(COMMENTARY_OF_THE_DAY, null) != null && mPosition != position)
                    mPreferences.edit().putString(COMMENTARY_OF_THE_DAY, null).apply();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
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

            case "10" : mDialogOpen = true; createDialog(); break;
            case "20" : Log.i("Button 20 ", " NEW ACTIVITY "  ); break;
            default: break;
        }

    }

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
                        mPreferences.edit().putString(COMMENTARY_OF_THE_DAY, input.getText().toString()).apply();
                    }
                })
                .create()
                .show();



    }



}
