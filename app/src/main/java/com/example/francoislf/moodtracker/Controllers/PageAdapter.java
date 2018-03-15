package com.example.francoislf.moodtracker.Controllers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.ImageView;

/**
 * Created by slaye on 12/03/2018.
 */

public class PageAdapter extends FragmentPagerAdapter {

    // Array of colors that will be passed to PageFragment
    private int[] color;

    public PageAdapter(FragmentManager fm, int[] color) {
        super(fm);
        this.color = color;
    }

    @Override
    public Fragment getItem(int position) {
        return (MainFragment.newInstance(position, this.color[position]));
    }

    @Override
    public int getCount() {
        return this.color.length;
    }
}
