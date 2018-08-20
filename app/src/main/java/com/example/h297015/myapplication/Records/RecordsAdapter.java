package com.example.h297015.myapplication.Records;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.h297015.myapplication.Events.Event;
import com.example.h297015.myapplication.Events.EventsAdapter;
import com.example.h297015.myapplication.R;
import com.example.h297015.myapplication.RecordBookActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.security.AccessController.getContext;

public class RecordsAdapter extends RecyclerView.Adapter<RecordsAdapter.ViewHolder>
    implements Filterable {

    private List<Record> mRecords;
    private List<Record> mRecordsFiltered;
    private Context mContext;
    private boolean filters[] = { true, false, false };

    public void setFilters(boolean all, boolean fse, boolean ts){
        filters[0] = all;
        filters[1] = fse;
        filters[2] = ts;
        getFilter().filter("");
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvRecordName) TextView tvRecordName;
        @BindView(R.id.tvRecordRole) TextView tvRecordRole;
        @BindView(R.id.ivEnvelope) ImageView ivEnvelope;
        @BindView(R.id.ivPhone) ImageView ivPhone;
        @BindView(R.id.ivLocation) ImageView ivLocation;
        @BindView(R.id.tvRecordTag) TextView tvRecordTag;

        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public RecordsAdapter(Context context, List<Record> records) {
        this.mContext = context;
        this.mRecords = records;
        this.mRecordsFiltered = records;
    }


    public RecordsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View recordView = inflater.inflate(R.layout.records, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(recordView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecordsAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        final Record record = mRecordsFiltered.get(position);

        TextView tvRecordName = viewHolder.tvRecordName;
        TextView tvRecordRole = viewHolder.tvRecordRole;
        ImageView ivEnvelope = viewHolder.ivEnvelope;
        ImageView ivPhone = viewHolder.ivPhone;
        ImageView ivLocation = viewHolder.ivLocation;
        TextView tvRecordTag = viewHolder.tvRecordTag;

        tvRecordName.setText(record.getName());
        tvRecordRole.setText(record.getRole());
        ivEnvelope.setImageResource(R.drawable.ic_envelope);
        ivPhone.setImageResource(R.drawable.ic_telephone);
        ivLocation.setImageResource(R.drawable.ic_location);
        tvRecordTag.setText(record.isFSE() ? "Field Service" : "Tech Support"); // TODO: make this more robust
        tvRecordTag.setBackgroundColor( record.isFSE() ? Color.parseColor("#0A92FB") : Color.parseColor("#8CA336") );

        ivPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(mContext).create(); //Read Update
                alertDialog.setTitle("Call " + record.getName());
                alertDialog.setMessage("Are You sure you want to call " + record.getName() + "?");

                alertDialog.setButton("Call " + record.getPhone(), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // here you can add functions
                        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_CALENDAR)
                                != PackageManager.PERMISSION_GRANTED) {
                            // Permission is not granted
                            String uri = "tel:" + record.getPhone();
                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse(uri));
                            mContext.startActivity(intent);
                            dialog.dismiss();
                        }
                    }
                });

                alertDialog.show();
            }
        });

        ivEnvelope.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(mContext).create(); //Read Update
                alertDialog.setTitle("Email " + record.getName());
                alertDialog.setMessage("Are You sure you want to email " + record.getName() + "?");

                alertDialog.setButton("Email " + record.getEmail(), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // here you can add functions
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("plain/text");
                        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { record.getEmail() });
                        intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
                        intent.putExtra(Intent.EXTRA_TEXT, "mail body");
                        mContext.startActivity(Intent.createChooser(intent, ""));
                        dialog.dismiss();
                    }
                });

                alertDialog.show();
            }
        });

        ivLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(mContext).create(); //Read Update
                alertDialog.setTitle("Location");
                alertDialog.setMessage(record.getLocation());
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRecordsFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String query = charSequence.toString();

                List<Record> filtered = new ArrayList<>();

                if (query.isEmpty()) {
                    //filtered = mRecords;
                    if (filters[0]) filtered = mRecords;
                    else {
                        for (Record record : mRecords) {
                            if (filters[1] == record.isFSE()) filtered.add(record);
                            else if (filters[2] == record.isTS()) filtered.add(record);
                        }
                    }

                } else {
                    for (Record record : mRecords) {

                        if (filters[0] && (record.getName().toLowerCase().contains(query.toLowerCase())
                                || String.valueOf(record.getZipCode()).contains(query))) {
                            filtered.add(record);
                        }
                        else if (filters[1] == record.isFSE() &&
                                (record.getName().toLowerCase().contains(query.toLowerCase())
                                || String.valueOf(record.getZipCode()).contains(query))) {
                            filtered.add(record);
                        }
                        else if (filters[2] == record.isTS() &&
                                (record.getName().toLowerCase().contains(query.toLowerCase())
                                        || String.valueOf(record.getZipCode()).contains(query))) {
                            filtered.add(record);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.count = filtered.size();
                results.values = filtered;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {
                mRecordsFiltered = (ArrayList<Record>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
