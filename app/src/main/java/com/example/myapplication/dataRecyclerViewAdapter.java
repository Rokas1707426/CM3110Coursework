package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class dataRecyclerViewAdapter extends RecyclerView.Adapter<dataRecyclerViewAdapter.dataViewHolder> {
    private Context context;
    private List<tableData> mData;
    // data is passed into the constructor
    public dataRecyclerViewAdapter(Context context, List<tableData> data) {
        this.context = context;
        this.mData = data;
    }
    // inflates the row layout from xml when needed
    @Override
    public dataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewlayout, parent, false);
        return new dataViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(dataViewHolder holder, int position) {
        tableData data = mData.get(position);
        holder.myTextName.setText(data.getName());
        holder.myTextData.setText(data.getPlayerData());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class dataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextData, myTextName;

        dataViewHolder(View itemView) {
            super(itemView);
            myTextData = itemView.findViewById(R.id.textData);
            myTextName = itemView.findViewById(R.id.textName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            tableData data = mData.get(getAdapterPosition());

            Intent intent = new Intent(context, addhistory.class);
            intent.putExtra("data", data);

            context.startActivity(intent);

        }
    }
}