package com.example.francoislf.moodtracker.Controllers;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.francoislf.moodtracker.Models.Stick;
import com.example.francoislf.moodtracker.R;

/**
 * Created by slaye on 22/03/2018.
 */

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private LinearLayout mStickLinear, mBlankLinear;
    private TextView mTextView;
    private ImageView mImageView;

    private Stick mStick;

    //itemView is the view representation
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        mTextView = (TextView) itemView.findViewById(R.id.day_title);
        mImageView = (ImageView) itemView.findViewById(R.id.logo_comment);
        mStickLinear = (LinearLayout) itemView.findViewById(R.id.stick_day);
        mBlankLinear = (LinearLayout) itemView.findViewById(R.id.blank_day);

    }

    // function in order to create the view with the help of the class Stick
    public void bind(Stick stick){

        this.mStick = stick;

        mTextView.setText(stick.getTitle());
        if (!stick.isTextExist()) mImageView.setVisibility(View.INVISIBLE);
        mStickLinear.setLayoutParams(stick.getId_stick());
        mStickLinear.setBackgroundColor(stick.getColor());
        mBlankLinear.setLayoutParams(stick.getId_blank());

        mImageView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        String call = mStick.getTextToast();



    }
}
