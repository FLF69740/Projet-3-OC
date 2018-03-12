package com.example.francoislf.moodtracker.Controllers;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.francoislf.moodtracker.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    // Keys for Bundle
    public static final String KEY_POSITION = "Position";
    public static final String KEY_COLOR = "Color";

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

        // Widget configuration
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.fragment_page_LinearLayout);
        TextView textView = (TextView) view.findViewById(R.id.fragment_page_title);

        int position = getArguments().getInt(KEY_POSITION, -1);
        int color = getArguments().getInt(KEY_COLOR, -1);

        linearLayout.setBackgroundColor(color);
        textView.setText("Page number " + position);

        return view;
    }

}
