package com.example.chapter03.recycler_view_demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.chapter03.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {

    private List<String> data;
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;

    public static void start(Context context){
        context.startActivity(new Intent(context,RecyclerActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

        setContentView(R.layout.ac_recycler);
        data = new ArrayList<>();

        recyclerView = findViewById(R.id.recycler_view);

        initData();

        recyclerAdapter = new RecyclerAdapter(getBaseContext(), data);
        recyclerView.setAdapter(recyclerAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }


    private void initData() {
        data.clear();
        for (int j=1 ;j<1024; j++){
            for (int i='A'; i<='Z'; i++){
                char c = (char) i;
                data.add(String.valueOf(c));
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                recyclerAdapter.addData(1);
                break;
            case R.id.delete:
                recyclerAdapter.deleteData(1);
                break;
            case R.id.listview:
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                        LinearLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(linearLayoutManager);
                break;
            case R.id.grid_view:
                recyclerView.setLayoutManager(new GridLayoutManager(this,3));
                break;
            case R.id.hor_grad_view:
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(
                        5, StaggeredGridLayoutManager.HORIZONTAL));
                break;
            case R.id.staggered:
                startActivity(new Intent(this,StaggeredActivity.class));
                break;
            case R.id.reset:
                initData();
                recyclerAdapter.notifyDataSetChanged();
                break;
            case R.id.grid_with_head:
                final GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
                gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        int re = 1;
                        if (position == 0)
                            re=gridLayoutManager.getSpanCount();
                        if (position == 1)
                            re=gridLayoutManager.getSpanCount();
                        return re;

                    }
                });
                recyclerView.setLayoutManager(gridLayoutManager);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
}
