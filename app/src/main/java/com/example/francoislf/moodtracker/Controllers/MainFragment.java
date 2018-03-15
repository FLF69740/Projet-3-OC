package com.example.francoislf.moodtracker.Controllers;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
public class MainFragment extends Fragment {


    // Keys for Bundle
    public static final String KEY_IMAGE = "Image";
    public static final String KEY_COLOR = "Color";

    public MainFragment() {
        // Required empty public constructor
    }

    // Method that will create a new instance of PageFragment, and add data to its bundle
    public static MainFragment newInstance(int position, int color){

        MainFragment fragment = new MainFragment();

        // Create Bundle and add data
        Bundle arg = new Bundle();
        arg.putInt(KEY_IMAGE, position);
        arg.putInt(KEY_COLOR, color);
        fragment.setArguments(arg);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // Widget configuration

        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.fragment_page_LinearLayout);
        ImageView imageView = (ImageView) view.findViewById(R.id.dynamic_smyley);

        int position = getArguments().getInt(KEY_IMAGE, 3);
        int color = getArguments().getInt(KEY_COLOR, 3);

        linearLayout.setBackgroundColor(color);

        // ImageView configuration
        switch (position){
            case 0 : imageView.setImageResource(R.drawable.smiley_sad); break;
            case 1 : imageView.setImageResource(R.drawable.smiley_disappointed); break;
            case 2 : imageView.setImageResource(R.drawable.smiley_normal); break;
            case 3 : imageView.setImageResource(R.drawable.smiley_happy); break;
            case 4 : imageView.setImageResource(R.drawable.smiley_super_happy); break;
            default: imageView.setImageResource(R.drawable.smiley_happy); break;
        }

        return view;
    }

}
