package com.example.francoislf.moodtracker.Controllers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.francoislf.moodtracker.Models.Stick;
import com.example.francoislf.moodtracker.R;

import java.util.List;

/**
 * Created by slaye on 22/03/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    List<Stick> mList;
    View view;


    // Constructor
    public MyAdapter(List<Stick> list){
        this.mList = list;
    }


    //ViewHolders creation
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stick_day_1, parent, false);
        return new MyViewHolder(view);
    }

    //Link between ViewHolders and class object list of Stick
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Stick stick = mList.get(position);
        holder.bind(stick);
    }

    // Define the number of ViewHolders
    @Override
    public int getItemCount() {
        return mList.size();
    }

}
