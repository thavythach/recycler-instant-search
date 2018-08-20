package com.example.h297015.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.h297015.myapplication.Events.Event;
import com.example.h297015.myapplication.Events.EventsAdapter;
import com.example.h297015.myapplication.Records.Record;
import com.example.h297015.myapplication.Records.RecordsAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventsActivity extends Activity {

    @BindView(R.id.my_recycler_view) RecyclerView mRecyclerView;

    ArrayList<Event> events;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        ButterKnife.bind(this);

        events = Event.createEventsList(40);


        EventsAdapter eventsAdapter = new EventsAdapter(this, events);
        mRecyclerView.setAdapter(eventsAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
