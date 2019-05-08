package com.sanu.duceretask.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sanu.duceretask.R;
import com.sanu.duceretask.models.RecyclerModel;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private List<RecyclerModel> recyclerModels = new ArrayList<>();



    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
  public  MyRecyclerViewAdapter(Context context, List<RecyclerModel> recyclerModels) {
        this.mInflater = LayoutInflater.from(context);
        this.recyclerModels = recyclerModels;


    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //  holder.myTextView.setText(mData[position]);
        int count =recyclerModels.get(position).getColourcount() ;
        if (count == 1) {
            holder.myTextView.setBackgroundResource(R.color.blue);
        } else {
            holder.myTextView.setBackgroundResource(R.color.brown);
        }
        holder.myTextView.setText(""+recyclerModels.get(position).getCount());
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return recyclerModels.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.info_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }



    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}