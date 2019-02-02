package com.vinsol.companymeetingschedulerapp.ui.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.vinsol.companymeetingschedulerapp.MeetingSchedularApplication;
import com.vinsol.companymeetingschedulerapp.models.MeetingScheduleDetailsResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MeetingScheduleDetailsViewModel extends ViewModel {

    //this is the data that we will fetch asynchronously
    private MutableLiveData<List<MeetingScheduleDetailsResponseModel>> mMeetingDetailsList;

    //we will call this method to get the data
    public LiveData<List<MeetingScheduleDetailsResponseModel>> getMeetingListDetails(String date) {
        //if the list is null
        if (mMeetingDetailsList == null) {
            mMeetingDetailsList = new MutableLiveData<>();
            //we will load it asynchronously from server in this method
            loadMeetingDetails(date);
        }

        //finally we will return the list
        return mMeetingDetailsList;
    }


    //This method is using Retrofit to get the JSON data from URL
    private void loadMeetingDetails(String date) {

        Call<List<MeetingScheduleDetailsResponseModel>> call = MeetingSchedularApplication.getClient().getMeetingScheduleDetails(date);

        call.enqueue(new Callback<List<MeetingScheduleDetailsResponseModel>>() {
            @Override
            public void onResponse(Call<List<MeetingScheduleDetailsResponseModel>> call,
                                   Response<List<MeetingScheduleDetailsResponseModel>> response) {

                //finally we are setting the list to our MutableLiveData
                mMeetingDetailsList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<MeetingScheduleDetailsResponseModel>> call, Throwable t) {

            }
        });
    }
}
