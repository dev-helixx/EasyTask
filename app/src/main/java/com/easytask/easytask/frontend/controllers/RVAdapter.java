package com.easytask.easytask.frontend.controllers;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.easytask.easytask.R;

import java.util.List;

/**
 * Created by Silas on 12-10-2017.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>{

    List<Task> tasks;

    public RVAdapter(List<Task> tasks){
        this.tasks = tasks;
    }



    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_item_layout, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int i) {


        holder.card_subject.setText(tasks.get(i).card_subject);
        holder.card_description.setText(tasks.get(i).card_description);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    public static class PersonViewHolder extends RecyclerView.ViewHolder {
//        CardView cv;
        TextView card_subject;
        TextView card_description;

        PersonViewHolder(View itemView) {
            super(itemView);
//            cv = (CardView)itemView.findViewById(R.id.cv);
            card_subject = (TextView)itemView.findViewById(R.id.card_subject);
            card_description = (TextView)itemView.findViewById(R.id.card_description);
        }
    }


}