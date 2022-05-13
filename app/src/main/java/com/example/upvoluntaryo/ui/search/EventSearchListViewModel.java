package com.example.upvoluntaryo.ui.search;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.upvoluntaryo.DBHelper;
import com.example.upvoluntaryo.objects.Event;

import java.util.ArrayList;

public class EventSearchListViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Event>> eventListData;
    private DBHelper DB;

    public void setEventListData(ArrayList<Event> eventListData){
        this.eventListData.setValue(eventListData);
    }

    LiveData<ArrayList<Event>> getEventListData(Context context){
        if (eventListData == null){
            DB = new DBHelper(context);
            eventListData = new MutableLiveData<>();
            eventListData.setValue(DB.listEvents());
        }
        return eventListData;
    }
}
