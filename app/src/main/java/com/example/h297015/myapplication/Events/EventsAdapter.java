package com.example.h297015.myapplication.Events;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.h297015.myapplication.R;

import java.util.List;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class EventsAdapter extends
    RecyclerView.Adapter<EventsAdapter.ViewHolder> {

    private Context mContext;
    private List<Event> mEvents;


    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public TextView dateTextView;
        public TextView locationTextView;
        public TextView registerTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.event_name);
            dateTextView = (TextView) itemView.findViewById(R.id.event_date);
            locationTextView = (TextView) itemView.findViewById(R.id.event_location);
            registerTextView = (TextView) itemView.findViewById(R.id.event_registration);
        }
    }


    // Pass in the contact array into the constructor
    public EventsAdapter(Context context, List<Event> contacts) {
        this.mEvents = contacts;
        this.mContext = context;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public EventsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.events, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(EventsAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        final Event event = mEvents.get(position);

        // Set item views based on your views and data model
        TextView tvName = viewHolder.nameTextView;
        TextView tvDate = viewHolder.dateTextView;
        TextView tvLocation = viewHolder.locationTextView;
        TextView tvRegister = viewHolder.registerTextView;

        tvName.setText(event.getName());
        tvDate.setText(event.getDate());
        tvLocation.setText(event.getLocation());
        tvRegister.setText(event.getRegister());

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(mContext).create(); //Read Update
                alertDialog.setTitle(event.getName());
                alertDialog.setMessage("Are You sure you want to register for" + event.getName() + " on " + event.getDate() + "?");

                alertDialog.setButton("Register", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // here you can add functions
                    }
                });

                alertDialog.show();
            }
        });
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mEvents.size();
    }
}
