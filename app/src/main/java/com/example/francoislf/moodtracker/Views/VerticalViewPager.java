package com.example.francoislf.moodtracker.Views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by slaye on 15/03/2018.
 */

public class VerticalViewPager extends ViewPager{



    // implement method
    public VerticalViewPager(Context context) {
        super(context);
        configureViewPager();
    }

    // implement method
    public VerticalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        configureViewPager();
    }

    // in order to configure the ViewPager [Horizontal -> Vertical]
    private void configureViewPager(){

        // in order to hide the shadow to the left or right side at the beginning or the end
        setOverScrollMode(2);

        // Implement anonym class PageTransformer in order to set vertical translation
        setPageTransformer(true, new PageTransformer() {
            @Override
            public void transformPage(View page, float position) {

                // Hide the page (except if the page going on the position [-1 , 1])
                page.setVisibility(View.INVISIBLE);

                if (position >= -1 && position <= 1) {

                    page.setVisibility(View.VISIBLE);

                    page.setTranslationX(page.getWidth() * position * -1);
                    page.setTranslationY(page.getHeight() * position * 1);
                }

            }
        });

    }

    


}


