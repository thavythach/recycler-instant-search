package com.example.h297015.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    // Binding Buttons to Views to IDS
    @BindView(R.id.btnRecordBook) Button btnRecordBook;
    @BindView(R.id.btnOrderStatus) Button btnOrderStatus;
    @BindView(R.id.btnEvents) Button btnEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this); // instantiation of buttons
    }

    @OnClick(R.id.btnRecordBook)
    public void openRecordBookActivity(){
        startActivity(new Intent(MainActivity.this, RecordBookActivity.class));
    }

    @OnClick(R.id.btnEvents)
    public void openEventActivity(){
        startActivity(new Intent(MainActivity.this, EventsActivity.class));
    }

    @OnClick(R.id.btnOrderStatus)
    public void openLoginActivity(){
        startActivity(new Intent(MainActivity.this, OrderStatusActivity.class));
    }
}
