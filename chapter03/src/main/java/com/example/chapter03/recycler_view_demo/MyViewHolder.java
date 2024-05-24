package com.example.chapter03.recycler_view_demo;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.chapter03.R;

class MyViewHolder extends RecyclerView.ViewHolder {
    TextView tv;
    TextView del;

    public MyViewHolder(View itemView) {
        super(itemView);
        tv = (TextView) itemView.findViewById(R.id.tv);
        del = (TextView) itemView.findViewById(R.id.delete);
    }
}
