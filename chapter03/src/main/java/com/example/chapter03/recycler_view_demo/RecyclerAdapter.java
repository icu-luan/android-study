package com.example.chapter03.recycler_view_demo;

import android.content.Context;
import android.graphics.PointF;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.chapter03.R;

import java.util.List;

/**
 * Created by LiFan on 2016/11/8.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<String> data;

    private PointF point = new PointF();

    public  RecyclerAdapter(Context context, List<String> data){
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.simple_textview,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        setItem(holder,position);
    }

    private void setItem(final MyViewHolder holder, final int position) {
        holder.del.setVisibility(View.GONE);
        holder.tv.setText(data.get(position));
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int layoutPosition = holder.getLayoutPosition();
                Toast.makeText(context,data.get(layoutPosition), Toast.LENGTH_SHORT).show();
            }
        });
//        holder.tv.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                holder.del.setVisibility(View.VISIBLE);
//                return true;
//            }
//        });
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int layoutPosition = holder.getLayoutPosition();
                deleteData(layoutPosition);
            }
        });
        holder.tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction()== MotionEvent.ACTION_DOWN){
                    point = new PointF(motionEvent.getX(),motionEvent.getY());
                }
                if (motionEvent.getAction()== MotionEvent.ACTION_UP){
                    if (point.x-motionEvent.getX()>=3)
                        holder.del.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addData(int position){
        data.add(position,"NEW");
        notifyItemInserted(position);
    }

    public void deleteData(int position){
        data.remove(position);
        notifyItemRemoved(position);
    }
}

