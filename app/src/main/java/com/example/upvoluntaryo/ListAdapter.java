package com.example.upvoluntaryo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Event> {

    public ListAdapter(Context context, ArrayList<Event> eventArrayList){
        super(context, R.layout.event_item, eventArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Event event = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.eventImageHeader);
        TextView eventName = convertView.findViewById(R.id.eventName);
        TextView eventDetails = convertView.findViewById(R.id.eventDetails);

        imageView.setImageResource(event.imageId);
        eventName.setText(event.eventName);
        eventDetails.setText(event.eventDetails);

        return super.getView(position, convertView, parent);
    }
}
