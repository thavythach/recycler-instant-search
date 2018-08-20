package com.example.h297015.myapplication;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.h297015.myapplication.Records.Record;
import com.example.h297015.myapplication.Records.RecordsAdapter;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class RecordBookActivity extends AppCompatActivity {



    @BindView(R.id.recordRecyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.ctvALL) CheckedTextView ctvALL;
    @BindView(R.id.ctvFSE) CheckedTextView ctvFSE;
    @BindView(R.id.ctvTS) CheckedTextView ctvTS;

    private boolean sAll = true;
    private boolean sFSE = false;
    private boolean sTS = false;


    private RecordsAdapter recordsAdapter;
    private ArrayList<Record> records;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        ButterKnife.bind(this);


        records = Record.createRecordsList(40);

        recordsAdapter = new RecordsAdapter(this, records);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(recordsAdapter);
    }

    @OnClick(R.id.ctvALL)
    public void submitAll(View view){
        ctvChange(true, false, false);
    }

    @OnClick(R.id.ctvFSE)
    public void submitFSE(View view){
        ctvChange(false, true, false);
    }

    @OnClick(R.id.ctvTS)
    public void submitTS(View view){
        ctvChange(false, false, true);
    }

    public void ctvChange(boolean all, boolean fse, boolean ts){

        this.sAll = all;
        this.sFSE = fse;
        this.sTS = ts;

        ctvALL.setChecked(this.sAll);
        ctvFSE.setChecked(this.sFSE);
        ctvTS.setChecked(this.sTS);

        recordsAdapter.setFilters(this.sAll, this.sFSE, this.sTS);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setQueryHint("Search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                recordsAdapter.getFilter().filter(s);
                return false;
            }

            // anything can be done here to query the result
            @Override
            public boolean onQueryTextChange(String s) {
                recordsAdapter.getFilter().filter(s);
                return false;
            }


        });

        return super.onCreateOptionsMenu(menu);

    }
}

