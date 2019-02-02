package com.vinsol.companymeetingschedulerapp.network;

import com.vinsol.companymeetingschedulerapp.constants.ApiConstants;
import com.vinsol.companymeetingschedulerapp.models.MeetingScheduleDetailsResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiClientInterface {

    @GET(ApiConstants.MEETING_SCHEDULE_DETAILS_API)
    Call<List<MeetingScheduleDetailsResponseModel>> getMeetingScheduleDetails(
            @Query(ApiConstants.API_PARAM_CONSTANT.DATE_KEY) String date);
}
