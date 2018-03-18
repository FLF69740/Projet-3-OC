package com.example.francoislf.moodtracker.Controllers;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.francoislf.moodtracker.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener {


    // Keys for Bundle
    public static final String KEY_POSITION = "Position";
    public static final String KEY_COLOR = "Color";

    private int mPosition;

    // callback
    private OnButtonClickedListener mCallBack;

    public MainFragment() {
        // Required empty public constructor
    }

    // Method that will create a new instance of PageFragment, and add data to its bundle
    public static MainFragment newInstance(int position, int color){

        MainFragment fragment = new MainFragment();

        // Create Bundle and add data
        Bundle arg = new Bundle();
        arg.putInt(KEY_POSITION, position);
        arg.putInt(KEY_COLOR, color);
        fragment.setArguments(arg);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // Set onClikedListener for ImageViews

        view.findViewById(R.id.note_button).setOnClickListener(this);
        view.findViewById(R.id.history_button).setOnClickListener(this);

        // Widget configuration

        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.fragment_page_LinearLayout);
        ImageView imageView = (ImageView) view.findViewById(R.id.dynamic_smyley);

        mPosition = getArguments().getInt(KEY_POSITION, 3);
        int color = getArguments().getInt(KEY_COLOR, 3);

        linearLayout.setBackgroundColor(color);

        // ImageView configuration
        switch (mPosition){
            case 0 : imageView.setImageResource(R.drawable.smiley_sad); break;
            case 1 : imageView.setImageResource(R.drawable.smiley_disappointed); break;
            case 2 : imageView.setImageResource(R.drawable.smiley_normal); break;
            case 3 : imageView.setImageResource(R.drawable.smiley_happy); break;
            case 4 : imageView.setImageResource(R.drawable.smiley_super_happy); break;
            default: imageView.setImageResource(R.drawable.smiley_happy); break;
        }

        return view;
    }

    // spread the click to MainActivity
    @Override
    public void onClick(View v) {

        // communicate the listener to MainActivity
        mCallBack.onButtonClicked(v);

    }


    // Declare interface that will be implemented by any container activity
    public interface OnButtonClickedListener {
        void onButtonClicked(View view);
    }


    // callback to parent activity
    private void createCallbackToParentActivity(){
        try {
            //Parent activity will automatically subscribe to callback
            mCallBack = (OnButtonClickedListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(e.toString()+ " must implement OnButtonClickedListener");
        }
    }

    // Call the method that creating callback after being attached to parent activity
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.createCallbackToParentActivity();
    }
}
