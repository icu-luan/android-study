package com.example.chapter03.recycler_view_demo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.chapter03.R;

import java.util.ArrayList;
import java.util.List;

public class StaggeredActivity extends AppCompatActivity {

    private List<String> data;
    private RecyclerView recyclerView;
    private StaggeredAdapter staggeredAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_recycler);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        initData();

        staggeredAdapter = new StaggeredAdapter(getBaseContext(), data);
        recyclerView.setAdapter(staggeredAdapter);

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));

    }


    private void initData() {
        data = new ArrayList<>();
        for (int j=1 ;j<1024; j++){
            for (int i='A'; i<='Z'; i++){
                char c = (char) i;
                data.add(String.valueOf(c));
            }
        }
    }
}
