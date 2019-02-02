package com.vinsol.companymeetingschedulerapp.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.vinsol.companymeetingschedulerapp.MeetingSchedularApplication;
import com.vinsol.companymeetingschedulerapp.models.MeetingSchedulDetailsResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MeetingScheduleDetailsViewModel extends ViewModel {

    //this is the data that we will fetch asynchronously
    private MutableLiveData<List<MeetingSchedulDetailsResponseModel>> mMeetingDetailsList;

    //we will call this method to get the data
    public LiveData<List<MeetingSchedulDetailsResponseModel>> getMeetingListDetails() {
        //if the list is null
        if (mMeetingDetailsList == null) {
            mMeetingDetailsList = new MutableLiveData<>();
            //we will load it asynchronously from server in this method
            loadMeetingDetails();
        }

        //finally we will return the list
        return mMeetingDetailsList;
    }


    //This method is using Retrofit to get the JSON data from URL
    private void loadMeetingDetails() {

        Call<List<MeetingSchedulDetailsResponseModel>> call = MeetingSchedularApplication.getClient().getMeetingScheduleDetails("7/8/2015");

        call.enqueue(new Callback<List<MeetingSchedulDetailsResponseModel>>() {
            @Override
            public void onResponse(Call<List<MeetingSchedulDetailsResponseModel>> call,
                                   Response<List<MeetingSchedulDetailsResponseModel>> response) {

                //finally we are setting the list to our MutableLiveData
                mMeetingDetailsList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<MeetingSchedulDetailsResponseModel>> call, Throwable t) {

            }
        });
    }
}
