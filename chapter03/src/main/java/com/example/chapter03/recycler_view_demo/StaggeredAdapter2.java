package com.example.chapter03.recycler_view_demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.chapter03.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by LiFan on 2016/11/8.
 */
public class StaggeredAdapter2 extends RecyclerView.Adapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<String> data;

    private List<Integer> width;

    public StaggeredAdapter2(Context context, List<String> data){
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
        width = new ArrayList<>();
        Random random = new Random();
        for (int i=0; i<data.size(); i++){
            width.add(100+random.nextInt(300));
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.simple_textview_t,parent,false);
        MyViewHolder MyViewHolder = new MyViewHolder(view);

        return MyViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int position) {
        final MyViewHolder holder = (MyViewHolder) viewHolder;
        ViewGroup.LayoutParams lp = holder.tv.getLayoutParams();
        lp.width = width.get(position);
        holder.tv.setLayoutParams(lp);
        holder.tv.setText(data.get(position));
        holder.tv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                data.remove(adapterPosition);
                notifyItemRemoved(adapterPosition);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
