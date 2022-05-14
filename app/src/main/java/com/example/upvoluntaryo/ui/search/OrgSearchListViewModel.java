package com.example.upvoluntaryo.ui.search;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.upvoluntaryo.DBHelper;
import com.example.upvoluntaryo.objects.Event;
import com.example.upvoluntaryo.objects.Orgs;

import java.util.ArrayList;

public class OrgSearchListViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Orgs>> orgListData;
    private DBHelper DB;

    public void setOrgListData(ArrayList<Orgs> orgListData) {
        this.orgListData.setValue(orgListData);
    }

    LiveData<ArrayList<Orgs>> getEventListData(Context context){
        if (orgListData == null){
            DB = new DBHelper(context);
            orgListData = new MutableLiveData<>();
            orgListData.setValue(DB.listOrgs());
        }
        return orgListData;
    }
}
