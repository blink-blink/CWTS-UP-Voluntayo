package com.example.upvoluntaryo.ui.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SearchViewModel extends ViewModel {
    private final MutableLiveData<String> query = new MutableLiveData<String>();

    public void setQuery(String queryData){
        query.setValue(queryData);
    }

    public LiveData<String> getQuery(){
        return query;
    }
}
